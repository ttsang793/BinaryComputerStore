package DAO;

import DTO.ChiTiet;
import DTO.KhachHang;
import DTO.DonHang;
import java.sql.*;
import java.util.LinkedList;

public abstract class DonHangDAO {
	public static LinkedList<DonHang> selectDH() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select * from DonHang");
			LinkedList<DonHang> result = new LinkedList<>();
			while (rs.next())
				result.add(new DonHang(rs.getString("MaDH"), rs.getString("NgayDH"), rs.getString("KHMua"), rs.getString("KHNhan"),
						rs.getString("MaNV"), rs.getString("ThangTraGop"), rs.getString("GopHangThang")));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static LinkedList<ChiTiet> selectCTDH() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select * from ChiTietDonHang");
			LinkedList<ChiTiet> result = new LinkedList<>();
			while (rs.next())
				result.add(new ChiTiet(rs.getString("MaDH"), rs.getString("MaSP"), rs.getString("DonGia"), rs.getString("SoLuong")));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static DonHang searchDH(String maDH) {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select * from DonHang where maDH = '" + maDH + "'");
			DonHang result = null;
			while (rs.next())
				result = new DonHang(maDH, rs.getString("NgayDH"), rs.getString("KHMua"), rs.getString("KHNhan"),
						rs.getString("MaNV"), rs.getString("ThangTraGop"), rs.getString("GopHangThang"));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static LinkedList<ChiTiet> searchCTDH(String maDH) {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select C.MaSP, TenSP, C.DonGia, C.SoLuong from ChiTietDonHang C join SanPham S on C.MaSP = S.MaSP where maDH = '" + maDH + "'");
			LinkedList<ChiTiet> result = new LinkedList<>();
			while (rs.next())
				result.add(new ChiTiet(maDH, rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("DonGia"), rs.getString("SoLuong")));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insert(DonHang dh, LinkedList<ChiTiet> chiTiet, KhachHang khMua, KhachHang khNhan, String thanhToan) {
		try {
			Connection con = DBConnection.connect();
			if (khMua.getMaKH() == 0) {
				String maKH = KhachHangDAO.LapKHMoi(khMua);
				if (maKH == null) { con.close(); return false; }
				System.out.println(maKH);
				dh.setKHMua(maKH);
			}
			if (khNhan.getMaKH() == 0) {
				String maKH = KhachHangDAO.LapKHMoi(khNhan);
				if (maKH == null) { con.close(); return false; }
				dh.setKHNhan(maKH);
			}
			PreparedStatement st = con.prepareStatement("Insert into DonHang values(?,?,?,?,?,?,?)");
			st.setString(1, dh.getMaDH());
			st.setString(2, dh.getNgayDH());
			st.setString(3, dh.getKHMua());
			st.setString(4, khNhan.getMaKH() > -1 ? dh.getKHNhan() : dh.getKHMua());
			st.setString(5, dh.getMaNV());
			st.setString(6, dh.getThangTraGop());
			st.setString(7, dh.getGopHangThang());
			
			boolean result1 = st.executeUpdate() > 0;
			
			String listSP = "Insert into ChiTietDonHang values ";
			for (int i=0; i<chiTiet.size(); i++) {				
				ChiTiet ct = chiTiet.get(i);

				st = con.prepareStatement("Update SanPham set soLuong=soLuong-? where maSP=? and trangThai=1");
				st.setString(1, ct.getSoLuong());
				st.setString(2, ct.getMaSP());
				st.executeUpdate();
				
				listSP += "('" + dh.getMaDH() + "', '" + ct.getMaSP() + "', " + ct.getDonGia() + ", " + ct.getSoLuong() + ")";
				if (i<chiTiet.size()-1) listSP += ", ";
			}
			boolean result2 = con.createStatement().executeUpdate(listSP) > 0;
			
			st = con.prepareStatement("Insert into ThanhToan values(?,?,?,?,1)");
			st.setString(1, dh.getMaDH());
			st.setString(2, dh.getNgayDH());
			st.setString(3, dh.getMaNV());
			st.setString(4, thanhToan);
			boolean result3 = st.executeUpdate() > 0;
			
			con.close();
			return result1 & result2 & result3;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}