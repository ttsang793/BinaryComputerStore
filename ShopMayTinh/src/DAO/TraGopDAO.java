package DAO;

import java.sql.*;
import java.util.LinkedList;

import DTO.DonHang;
import DTO.ThanhToan;

public abstract class TraGopDAO {
	public static LinkedList<DonHang> selectDH() {
		try {
			Connection con = DBConnection.connect();
			String query = "Select d.MaDH, Max(NgayTT) as NgayTT, ThangTraGop - Coalesce(Sum(SoThang),0) + 1 as ConLai, GopHangThang, TongTien "
					+ "from DonHang d, ThanhToan t, (select c.maDH, sum(DonGia*soLuong) as tongTien from DonHang d join ChiTietDonHang c on d.maDH = c.maDH group by c.maDH) c "
					+ "where d.maDH = t.maDH and t.maDH = c.maDH group by d.maDH";
			ResultSet rs = con.createStatement().executeQuery(query);
			LinkedList<DonHang> result = new LinkedList<>();
			while (rs.next())
				result.add(new DonHang(rs.getString("MaDH"), rs.getString("NgayTT"), rs.getString("ConLai"), rs.getString("GopHangThang"), rs.getString("TongTien")));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insert(ThanhToan tg) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Insert into ThanhToan values(?,?,?,?,?)");
			st.setString(1, tg.getMaDH());
			st.setString(2, tg.getNgayTT());
			st.setString(3, tg.getMaNV());
			st.setString(4, tg.getSoThang());
			st.setString(5, tg.getSoTien());
			
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
