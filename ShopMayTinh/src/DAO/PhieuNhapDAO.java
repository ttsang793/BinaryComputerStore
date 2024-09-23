package DAO;

import DTO.PhieuNhap;
import DTO.ChiTiet;
import java.sql.*;
import java.util.*;

public abstract class PhieuNhapDAO {
	public static LinkedList<PhieuNhap> selectPN() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select * from PhieuNhap");
			LinkedList<PhieuNhap> result = new LinkedList<>();
			while (rs.next())
				result.add(new PhieuNhap(rs.getString("maPN"), rs.getString("ngayNhap"), rs.getString("maNCC"),
						rs.getLong("tongTien"),rs.getString("maNV")));
			con.close();
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static LinkedList<ChiTiet> selectCTPN() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select C.maPN, C.maSP, S.tenSP, C.donGia, C.soLuong from ChiTietPhieuNhap C join SanPham S on C.maSP = S.maSP");
			LinkedList<ChiTiet> result = new LinkedList<>();
			while (rs.next())
				result.add(new ChiTiet(rs.getString("maPN"), rs.getString("maSP"), rs.getString("tenSP"), rs.getString("donGia"), rs.getString("soLuong")));
			con.close();
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;			
		}
	}
	
	public static boolean insert(PhieuNhap pn, LinkedList<ChiTiet> chiTiet) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Insert into PhieuNhap value (?,?,?,?,?)");
			st.setString(1, pn.getMaPN());
			st.setString(2, pn.getNgayNhap());
			st.setString(3, pn.getMaNCC());
			st.setLong(4, pn.getTongTien());
			st.setString(5, pn.getMaNV());
			boolean result1 = st.executeUpdate() > 0;
			
			String listSP = "Insert into ChiTietPhieuNhap values ";
			for (int i=0; i<chiTiet.size(); i++) {				
				ChiTiet ct = chiTiet.get(i);

				st = con.prepareStatement("Update SanPham set soLuong=soLuong+? where maSP=?");
				st.setString(1, ct.getSoLuong());
				st.setString(2, ct.getMaSP());
				st.executeUpdate();
				
				listSP += "('" + pn.getMaPN() + "', '" + ct.getMaSP() + "', " + ct.getDonGia() + ", " + ct.getSoLuong() + ")";
				if (i<chiTiet.size()-1) listSP += ", ";
			}
			boolean result2 = con.createStatement().executeUpdate(listSP) > 0;
			con.close();
			return result1 & result2;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;			
		}
	}
	public static LinkedList<PhieuNhap> fillPN(String maNCC, String maNV, String maQL, String from, String to, String minTong, String maxTong) {
		try {
			Connection con = DBConnection.connect();
			String query = "";
			if (maNCC.contains("NCC")) query = " and maNCC = '" + maNCC + "'";
			if (maNV.contains("NV")) query = " and maNV = '" + maNV + "'";
			query = " and ngayNhap >= '" + from + "' and ngayNhap <= '" + to + "'";
			if (!minTong.isEmpty() & !maxTong.isEmpty())
				query = " and tongTien >= " + minTong + " and tongTien < " + maxTong;
						
			ResultSet rs = con.createStatement().executeQuery("Select maPN, ngayNhap, maNCC, tongTien, p.maNV from PhieuNhap p join NhanVien n on p.maNV = n.maNV where quanLy = '" + maQL + "'" + query);
			LinkedList<PhieuNhap> result = new LinkedList<>();
			while (rs.next())
				result.add(new PhieuNhap(rs.getString("maPN"), rs.getString("ngayNhap"), rs.getString("maNCC"),
						rs.getLong("tongTien"),rs.getString("maNV")));
			con.close();
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}