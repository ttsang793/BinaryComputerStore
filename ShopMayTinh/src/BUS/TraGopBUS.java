package BUS;

import java.util.LinkedList;
import DAO.TraGopDAO;
import DTO.DonHang;
import DTO.ThanhToan;

public abstract class TraGopBUS {
	private static LinkedList<DonHang> danhSachDH = new LinkedList<>();
	
	public static LinkedList<DonHang> getDanhSachDH() {
		return danhSachDH;
	}
	
	public static void load() {
		danhSachDH = TraGopDAO.selectDH();
	}
	
	public static boolean insert(ThanhToan tg) {
		return TraGopDAO.insert(tg);
	}
}