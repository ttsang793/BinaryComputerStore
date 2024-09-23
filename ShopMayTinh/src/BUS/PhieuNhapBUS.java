package BUS;

import DTO.PhieuNhap;
import DTO.ChiTiet;
import DAO.PhieuNhapDAO;
import java.util.LinkedList;
import java.util.Date;

public abstract class PhieuNhapBUS {
	private static LinkedList<PhieuNhap> danhSachPN = new LinkedList<>();
	private static LinkedList<ChiTiet> danhSachCTPN = new LinkedList<>();	
	
	public static LinkedList<PhieuNhap> getDanhSachPN() {
		return danhSachPN;
	}

	public static LinkedList<ChiTiet> getDanhSachCTPN() {
		return danhSachCTPN;
	}

	public static LinkedList<ChiTiet> getChiTietMaPN(String maPN) {
		LinkedList<ChiTiet> result = new LinkedList<>();
		for (ChiTiet ct: danhSachCTPN) if (ct.getMaGT().equals(maPN)) result.add(ct);
		return result;
	}

	public static LinkedList<ChiTiet> getChiTietMaSP(String maSP) {
		LinkedList<ChiTiet> result = new LinkedList<>();
		for (ChiTiet ct: danhSachCTPN) if (ct.getMaSP().equals(maSP)) result.add(ct);
		return result;
	}

	public static LinkedList<PhieuNhap> getChiTietMaNCC(String maNCC) {
		LinkedList<PhieuNhap> result = new LinkedList<>();
		for (PhieuNhap pn: danhSachPN) if (pn.getMaNCC().equals(maNCC)) result.add(pn);
		return result;
	}

	private static String taoMa() {
		String chuSo = Integer.toString(danhSachPN.size() + 1);
		while (chuSo.length() < 6) chuSo = "0" + chuSo;
		return "PN" + chuSo;		
	}
	
	public static LinkedList<PhieuNhap> fillPN(String maNCC, String maNV, String maQL, Date fromDate, Date toDate, String minTong, String maxTong) {
		String from = Time.toString(fromDate);
		String to = Time.toString(toDate);
		return PhieuNhapDAO.fillPN(maNCC, maNV, maQL, from, to, minTong, maxTong);
	}
	
	public static void load() {
		danhSachPN = PhieuNhapDAO.selectPN();
		danhSachCTPN = PhieuNhapDAO.selectCTPN();
	}
	
	public static String kiemTra(String ncc, int size) {
		if (ncc.equals("Chọn nhà cung cấp")) return "Vui lòng chọn nhà cung cấp";
		if (size == 0) return "Vui lòng chọn ít nhất 1 sản phẩm";
		return "";
	}
	
	public static String insert(PhieuNhap pn, LinkedList<ChiTiet> chiTiet) {
		pn.setMaPN(taoMa());
		return (PhieuNhapDAO.insert(pn, chiTiet)) ? "" : "Đã có lỗi xảy ra, lập phiếu nhập thất bại";
	}
}
