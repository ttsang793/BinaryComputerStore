package DAO;
import java.util.*;
import java.sql.*;

public abstract class ThongKeDAO {
	public static HashMap<String, Integer> thuongHieuLaptop(int yearFrom, int monthFrom, int yearTo, int monthTo) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("select thuongHieu, SUM(c.soLuong) from ChiTietDonHang c join Laptop l on c.maSP = l.maSP join DonHang d on c.maDH = d.maDH "
					+ "where (year(ngayDH)>=? and month(ngayDH)>=?) or (year(ngayDH)<=? and month(ngayDH)<=?) group by thuongHieu");
			st.setInt(1, yearFrom);
			st.setInt(2, monthFrom);
			st.setInt(3, yearTo);
			st.setInt(4, monthTo);
			HashMap<String, Integer> result = new HashMap<>();
			ResultSet rs = st.executeQuery();
			while (rs.next()) result.put(rs.getString("thuongHieu"), rs.getInt("SUM(c.soLuong)"));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<String, String> sanPhamBanChay(int yearFrom, int monthFrom, int yearTo, int monthTo) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("select s.maSP, tenSP, SUM(c.soLuong) from SanPham s join ChiTietDonHang c on s.maSP = c.maSP join DonHang d on c.maDH = d.maDH "
					+ "where (year(ngayDH)>=? and month(ngayDH)>=?) or (year(ngayDH)<=? and month(ngayDH)<=?) group by s.maSP order by SUM(c.soLuong) desc");
			st.setInt(1, yearFrom);
			st.setInt(2, monthFrom);
			st.setInt(3, yearTo);
			st.setInt(4, monthTo);
			HashMap<String, String> result = new HashMap<>();
			int max = 0;
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getInt("SUM(c.soLuong)") > max) max = rs.getInt("SUM(c.soLuong)");
				if (rs.getInt("SUM(c.soLuong)") < max) break;
				result.put(rs.getString("maSP"), rs.getString("tenSP"));
			}
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<String, long[]> doanhThu(int yearFrom, int monthFrom, int yearTo, int monthTo) {
		try {
			int year = yearFrom, month = monthFrom;
			HashMap<String, long[]> result = new HashMap<>();
			while (true) {
				long loiNhuan = 0, tienNhap = 0;
				Connection con = DBConnection.connect();
				PreparedStatement st = con.prepareStatement("select SUM(soTien) from ThanhToan where year(ngayTT)=? and month(ngayTT)=?");
				st.setInt(1, year);
				st.setInt(2, month);
				ResultSet rs = st.executeQuery();
				while (rs.next()) loiNhuan = (rs.getString("SUM(soTien)") == null) ? 0 : rs.getInt("SUM(soTien)");
				
				st = con.prepareStatement("select SUM(tongTien) from PhieuNhap where year(ngayNhap)=? and month(ngayNhap)=?");
				st.setInt(1, year);
				st.setInt(2, month);
				rs = st.executeQuery();
				while (rs.next()) tienNhap = (rs.getString("SUM(tongTien)") == null) ? 0 : rs.getInt("SUM(soTien)");
				
				result.put(Integer.toString(year) + "-" + Integer.toString(month), new long[] {loiNhuan, tienNhap, loiNhuan - tienNhap});
				
				if (month == 12) {
					if (year + 1 > yearTo) break;
					year++; month = 1;
				}
				else {
					if (year == yearTo && month + 1 > monthTo) break;
					month++;
				}
			}
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
