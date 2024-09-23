package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import DTO.NhaCungCap;

public abstract class NhaCungCapDAO {
	public static LinkedList<NhaCungCap> select() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select * from NhaCungCap");
			LinkedList<NhaCungCap> result = new LinkedList<>();
			while (rs.next())
				result.add(new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"), rs.getString("diaChi"),
						rs.getString("soDienThoai"), rs.getString("email"), rs.getBoolean("trangThai")));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insert(NhaCungCap ncc) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Insert into NhaCungCap values(?,?,?,?,?,1)");
			st.setString(1, ncc.getMaNCC());
			st.setString(2, ncc.getTenNCC());
			st.setString(3, ncc.getDiaChiNCC());
			st.setString(4, ncc.getSoDTNCC());
			st.setString(5, ncc.getEmailNCC());
			
			boolean result = st.executeUpdate() > 0;
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean update(NhaCungCap ncc) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update NhaCungCap set tenNCC=?, diaChi=?, soDienThoai=?, email=?, trangThai=? where maNCC=?");
			st.setString(1, ncc.getTenNCC());
			st.setString(2, ncc.getDiaChiNCC());
			st.setString(3, ncc.getSoDTNCC());
			st.setString(4, ncc.getEmailNCC());
			st.setBoolean(5, ncc.getTrangThai());
			st.setString(6, ncc.getMaNCC());

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
