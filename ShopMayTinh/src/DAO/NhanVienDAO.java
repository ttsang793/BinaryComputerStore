package DAO;
import DTO.NhanVien;

import java.sql.*;
import java.util.*;

public abstract class NhanVienDAO {
	public static LinkedList<NhanVien> select(String maQL) {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select * from NhanVien where QuanLy='" + maQL + "' and TrangThai=1");
			LinkedList<NhanVien> nv = new LinkedList<>();
			while (rs.next())
				nv.add(new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("ChucVu"), rs.getString("NgayVaoLam"),
						rs.getString("NgaySinh"), rs.getString("QuanLy"), rs.getBoolean("TrangThai")));
			con.close();
			return nv;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static boolean update(NhanVien nv) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update NhanVien set tenNV=?, ngayVaoLam=?, ngaySinh=? where MaNV=?");
			st.setString(1, nv.getTenNV());
			st.setString(2, nv.getNgayVaoLam());
			st.setString(3, nv.getNgaySinh());
			st.setString(4, nv.getMaNV());
			
			boolean result = st.executeUpdate() > 0;
			con.close();			
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean delete(String maNV) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update NhanVien set TrangThai=0, QuanLy=null where MaNV=?");
			st.setString(1, maNV);
			
			boolean result = st.executeUpdate() > 0;
			con.close();			
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public static boolean dieuPhoi(String maNV, String maQL) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update NhanVien set QuanLy=? where MaNV=?");
			st.setString(1, maQL);
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
	
	public static boolean thangChuc(String maNV) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update NhanVien set ChucVu='Quản lý', QuanLy=? where MaNV=?");
			st.setString(1, maNV);
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
}
