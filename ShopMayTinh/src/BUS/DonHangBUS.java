package BUS;

import DAO.DonHangDAO;
import DTO.ChiTiet;
import DTO.DonHang;
import DTO.KhachHang;

import java.util.LinkedList;

public abstract class DonHangBUS {
	private static LinkedList<DonHang> danhSachDH = new LinkedList<>();
	private static LinkedList<ChiTiet> danhSachCTDH = new LinkedList<>();
	
	public static LinkedList<DonHang> getDanhSachDH() {
		return danhSachDH;
	}

	public static LinkedList<ChiTiet> getDanhSachCTDH() {
		return danhSachCTDH;
	}
	
	public static DonHang getDanhSachDH(String maDH) {
		return DonHangDAO.searchDH(maDH);
	}

	public static LinkedList<ChiTiet> getDanhSachCTDH(String maDH) {
		return DonHangDAO.searchCTDH(maDH);
	}
	
	public static void load() {
		danhSachDH = DonHangDAO.selectDH();
		danhSachCTDH = DonHangDAO.selectCTDH();
	}
	
	private static String taoMa() {
		String chuSo = Integer.toString(danhSachDH.size() + 1);
		while (chuSo.length() < 6) chuSo = "0" + chuSo;
		return "DH" + chuSo;		
	}
	
	public static boolean insert(DonHang dh, LinkedList<ChiTiet> chiTiet, KhachHang khMua, KhachHang khNhan, String thanhToan) {
		dh.setMaDH(taoMa());
		dh.setKHMua(Integer.toString(khMua.getMaKH()));
		dh.setKHNhan(Integer.toString(khNhan.getMaKH()));
		return DonHangDAO.insert(dh, chiTiet, khMua, khNhan, thanhToan);
	}
}