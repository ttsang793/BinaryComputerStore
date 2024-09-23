package DAO;
import DTO.KhachHang;
import DTO.TreEm;
import java.util.LinkedList;
import java.sql.*;

public abstract class KhachHangDAO {
	public static LinkedList<KhachHang> select() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("select k.maKH, k.tenKH, k.ngaySinh, k.gioiTinh, k.soDienThoai,"
					+ "k.cmnd, k.muaHo, h.cmnd from KhachHang k left join KhachHang h on k.muaHo = h.maKH");
			LinkedList<KhachHang> result = new LinkedList<>();
			while (rs.next()) {
				if (rs.getString(7) == null)
					result.add(new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
				else
					result.add(new TreEm(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(8), rs.getInt(7)));
			}
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static LinkedList<KhachHang> search(String cmnd) {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("select k.maKH, k.tenKH, k.ngaySinh, k.gioiTinh, k.soDienThoai,"
					+ "k.cmnd, k.muaHo, h.cmnd from KhachHang k left join KhachHang h on k.muaHo = h.maKH"
					+ "where where k.cmnd='%" + cmnd + "%' or h.cmnd='%" + cmnd + "%'");
			LinkedList<KhachHang> result = new LinkedList<>();
			while (rs.next()) {
				if (rs.getString(7) == null)
					result.add(new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
				else
					result.add(new TreEm(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(8), rs.getInt(7)));
			}
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insert(KhachHang kh) {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Insert into KhachHang (tenKH, ngaySinh, gioiTinh, soDienThoai, cmnd, muaHo) values(?,?,?,?,?,?)");
			st.setString(1, kh.getTenKH());
			st.setString(2, kh.getNgaySinh());
			st.setString(3, kh.getGioiTinh());
			st.setString(4, kh.getSoDienThoai());
			if (kh instanceof TreEm) {
				st.setString(5, null);
				st.setInt(6, ((TreEm) kh).getMaNL());
			}
			else {
				st.setString(5, kh.getCMND());
				st.setString(6, null);
			}
			
			boolean result = st.executeUpdate() > 0;
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	static String LapKHMoi(KhachHang kh) {
		String result = null;
		try {
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Insert into KhachHang (tenKH, ngaySinh, gioiTinh, soDienThoai, cmnd, muaHo) values(?,?,?,?,?,?)");
			st.setString(1, kh.getTenKH());
			st.setString(2, kh.getNgaySinh());
			st.setString(3, kh.getGioiTinh());
			st.setString(4, kh.getSoDienThoai());
			if (kh instanceof TreEm) {
				st.setString(5, null);
				st.setInt(6, ((TreEm) kh).getMaNL());
			}
			else {
				st.setString(5, kh.getCMND());
				st.setString(6, null);
			}
			
			if (st.executeUpdate() > 0) {
				ResultSet rs = con.createStatement().executeQuery("select maKH from KhachHang order by maKH desc limit 0,1");
				while (rs.next()) result = rs.getString(1);
			}
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
}