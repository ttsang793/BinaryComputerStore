package DAO;
import java.sql.*;
import java.util.LinkedList;

import DTO.SanPham;

public abstract class SanPhamDAO {
	public static LinkedList<SanPham> select() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select * from SanPham where LoaiSP!='Laptop' and LoaiSP!='Smartphone'");
			LinkedList<SanPham> result = new LinkedList<>();
			while (rs.next())
				result.add(new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("loaiSP"), rs.getString("anhSP"),
						rs.getString("donGia"), rs.getString("soLuong"), rs.getInt("trangThai")));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insert(SanPham sp) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Insert into SanPham values(?,?,?,?,?,0,'2')");
			st.setString(1, sp.getMaSP());
			st.setString(2, sp.getTenSP());
			st.setString(3, sp.getLoaiSP());
			st.setString(4, sp.getAnhSP());
			st.setString(5, sp.getDonGia());
			
			boolean result = st.executeUpdate() > 0;
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean update(SanPham sp, String anh) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update SanPham set TenSP=?, LoaiSP=?, DonGia=?, TrangThai=? where MaSP=?");
			st.setString(1, sp.getTenSP());
			st.setString(2, sp.getLoaiSP());
			st.setString(3, sp.getDonGia());
			st.setInt(4, sp.getTrangThai());
			st.setString(5, sp.getMaSP());
			boolean result = st.executeUpdate() > 0;
			
			if (!anh.isEmpty()) {
				st = con.prepareStatement("Update SanPham set AnhSP=? where MaSP=?");
				st.setString(1, sp.getAnhSP());
				st.setString(2, sp.getMaSP());
				result = st.executeUpdate() > 0;			
			}
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	static boolean delete(String maSP) {
		try {
			Connection con = DBConnection.connect();
			
			boolean result = con.createStatement().executeUpdate("Update SanPham set TrangThai='0' where maSP='" + maSP + "'") > 0;
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}