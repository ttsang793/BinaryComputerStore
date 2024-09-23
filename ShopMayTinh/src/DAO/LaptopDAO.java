package DAO;
import DTO.Laptop;
import java.sql.*;
import java.util.LinkedList;

public abstract class LaptopDAO {
	public static LinkedList<Laptop> select() {
		try {
			Connection con = DBConnection.connect();
			ResultSet rs = con.createStatement().executeQuery("Select * from Laptop join SanPham on Laptop.maSP = SanPham.maSP where LoaiSP='Laptop'");
			LinkedList<Laptop> result = new LinkedList<>();
			while (rs.next())
				result.add(new Laptop(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("anhSP"), rs.getString("donGia"), rs.getString("soLuong"),
					rs.getInt("trangThai"), rs.getString("trongLuong"), rs.getString("namRaMat"), rs.getString("mauSac"), rs.getString("chatLieu"),
					rs.getString("heDieuHanh"), rs.getString("thuongHieu"), rs.getString("baoMat"), rs.getString("Bluetooth"), rs.getString("dungLuongPin"),
					rs.getString("CPU"), rs.getString("RAM"), rs.getString("ncRAM"), rs.getString("cardDoHoa"), rs.getString("oCung"), rs.getString("ktManHinh"),
					rs.getString("tanSo"), rs.getString("doPhanGiai"), rs.getString("congNghe"), rs.getString("tamNen")
				));
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insert(Laptop lap) {
		try {
			if (!SanPhamDAO.insert(lap)) return false;
			Connection con = DBConnection.connect();					
			PreparedStatement st = con.prepareStatement("Insert into Laptop values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			st.setString(1, lap.getMaSP());
			st.setString(2, lap.getThuongHieu());
			st.setString(3, lap.getNamRaMat());
			st.setString(4, lap.getTrongLuong());
			st.setString(5, lap.getMauSac());
			st.setString(6, lap.getChatLieu());
			st.setString(7, lap.getCPU());
			st.setString(8, lap.getRAM());
			st.setString(9, lap.getNangCapRAM());
			st.setString(10, lap.getManHinh());
			st.setString(11, lap.getDoPhanGiai());
			st.setString(12, lap.getTanSo());
			st.setString(13, lap.getCongNghe());
			st.setString(14, lap.getTamNen());
			st.setString(15, lap.getGPU());
			st.setString(16, lap.getOCung());
			st.setString(17, lap.getBaoMat());
			st.setString(18, lap.getBluetooth());
			st.setString(19, lap.getPin());
			st.setString(20, lap.getHeDieuHanh());
			
			boolean result = st.executeUpdate() > 0;
			con.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public static boolean update(Laptop lap, String anh) {
		try {
			if (!SanPhamDAO.update(lap, anh)) return false;
			Connection con = DBConnection.connect();
			PreparedStatement st = con.prepareStatement("Update Laptop set ThuongHieu=?, NamRaMat=?, TrongLuong=?, MauSac=?," +
				"ChatLieu=?, CPU=?, RAM=?, NCRAM=?, KTManHinh=?, DoPhanGiai=?, TanSo=?, CongNghe=?, TamNen=?, CardDoHoa=?," +
				"OCung=?, BaoMat=?, Bluetooth=?, DungLuongPin=?, HeDieuHanh=? where MaSP=?");
			st.setString(1, lap.getThuongHieu());
			st.setString(2, lap.getNamRaMat());
			st.setString(3, lap.getTrongLuong());
			st.setString(4, lap.getMauSac());
			st.setString(5, lap.getChatLieu());
			st.setString(6, lap.getCPU());
			st.setString(7, lap.getRAM());
			st.setString(8, lap.getNangCapRAM());
			st.setString(9, lap.getManHinh());
			st.setString(10, lap.getDoPhanGiai());
			st.setString(11, lap.getTanSo());
			st.setString(12, lap.getCongNghe());
			st.setString(13, lap.getTamNen());
			st.setString(14, lap.getGPU());
			st.setString(15, lap.getOCung());
			st.setString(16, lap.getBaoMat());
			st.setString(17, lap.getBluetooth());
			st.setString(18, lap.getPin());
			st.setString(19, lap.getHeDieuHanh());
			st.setString(20, lap.getMaSP());
			
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