package DAO;

import java.sql.*;
import java.util.LinkedList;

import DTO.PhanQuyen;

public abstract class PhanQuyenDAO {
	private static int soNV;
	
	public static int getSoNV() { return soNV; }
	
	public static LinkedList<PhanQuyen> select() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select MaNV, TenNV, ChucVu, NgayVaoLam, NgaySinh, QuanLy, hex(PhanQuyen), TrangThai from NhanVien");
			LinkedList<PhanQuyen> nv = new LinkedList<>();
			soNV = 0;
			while (rs.next()) {
				nv.add(new PhanQuyen(rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("ChucVu"), rs.getString("NgayVaoLam"),
					rs.getString("NgaySinh"), rs.getString("QuanLy"), rs.getBoolean("TrangThai"), Integer.parseInt(rs.getString("hex(PhanQuyen)"))));
				if (rs.getString(1).contains("Admin")) soNV++;
			}
			con.close();
			return nv;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static boolean insert(PhanQuyen nv, String password) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Insert into NhanVien values(?,?,?,?,?,?,?,0x?,1)");
			st.setString(1, nv.getMaNV());
			st.setString(2, nv.getTenNV());
			st.setString(3, password);
			st.setString(4, nv.getChucVu());
			st.setString(5, nv.getNgayVaoLam());
			st.setString(6, nv.getNgaySinh());
			st.setString(7, nv.getMaQL());
			st.setInt(8, nv.getQuyen());
			
			boolean result = st.executeUpdate() > 0;
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean update(PhanQuyen nv) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update NhanVien set tenNV=?, chucVu=?, ngayVaoLam=?, ngaySinh=?, quanLy=?, phanQuyen=0x? where MaNV=?");
			st.setString(1, nv.getTenNV());
			st.setString(2, nv.getChucVu());
			st.setString(3, nv.getNgayVaoLam());
			st.setString(4, nv.getNgaySinh());
			st.setString(5, nv.getMaQL());
			st.setInt(6, nv.getQuyen());
			st.setString(7, nv.getMaNV());
			
			boolean result = st.executeUpdate() > 0;
			con.close();			
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean resetPassword(String maNV, String password) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update NhanVien set Password=? where MaNV=?");
			st.setString(1, password);
			st.setString(2, maNV);
			
			boolean result = st.executeUpdate() > 0;
			con.close();			
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static PhanQuyen dangNhap(String maNV, String password) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Select TenNV, ChucVu, NgayVaoLam, NgaySinh, QuanLy, hex(PhanQuyen), TrangThai from NhanVien where MaNV=? and Password=?");
			st.setString(1, maNV);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery();
			PhanQuyen nv = null;
			while (rs.next())
				nv = new PhanQuyen(maNV, rs.getString("TenNV"), rs.getString("ChucVu"), rs.getString("NgayVaoLam"),
					rs.getString("NgaySinh"), rs.getString("QuanLy"), rs.getBoolean("TrangThai"), Integer.parseInt(rs.getString("hex(PhanQuyen)")));
			con.close();
			return nv;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
