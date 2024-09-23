package BUS;

import java.util.LinkedList;

import DAO.PhanQuyenDAO;
import DTO.PhanQuyen;

public abstract class PhanQuyenBUS {
	private static LinkedList<PhanQuyen> danhSachNV = new LinkedList<>();
	
	public static LinkedList<PhanQuyen> getDanhSachNV() { return danhSachNV; }
	
	public static void load() {
		danhSachNV = PhanQuyenDAO.select();
	}
	
	public static String[] kiemTra(PhanQuyen nv) {
		boolean error = false;
		String[] result = new String[5];
		for (int i=0; i<result.length; i++) result[i] = "";
		
		if (nv.getTenNV().isEmpty()) { error = true; result[0] = "Vui lòng nhập tên nhân viên"; }
		
		if (nv.getChucVu().equals("Chọn chức vụ")) { error = true; result[1] = "Vui lòng chọn chức vụ"; }
		
		if (!nv.getChucVu().equals("Admin")) {
			if (nv.getNgayVaoLam() == null || nv.getNgayVaoLam().isEmpty())
				{ error = true; result[2] = "Vui lòng nhập ngày vào làm"; }
			else if (Time.isFuture(nv.getNgayVaoLam()))
				{ error = true; result[2] = "Vui lòng không nhập ngày tháng tương lai"; }
			
			if (nv.getNgayVaoLam() == null || nv.getNgayVaoLam().isEmpty())
				{ error = true; result[3] = "Vui lòng nhập ngày sinh"; }
			else if (Time.isFuture(nv.getNgaySinh()))
				{ error = true; result[3] = "Vui lòng không nhập ngày tháng tương lai"; }
		}
		return error ? result : new String[0];
	}
	
	private static int demChucVu(String chucVu) {
		int count = 0;
		for (PhanQuyen nv: danhSachNV) if (nv.getMaNV().contains(chucVu) && nv.getTrangThai()) count++;
		return count;
	}
	
	public static boolean conMotAdmin(PhanQuyen nv) {
		return nv.getMaNV().contains("AD") && demChucVu("AD") <= 1 && !nv.getTrangThai();
	}
	
	private static String taoMa(String chucVu) {
		String chuSo = Integer.toString(demChucVu(chucVu) + 1);
		if (chucVu != "Admin") while (chuSo.length() < 4) chuSo = "0" + chuSo;
		return chucVu + chuSo;
	}
	
	public static boolean insert(PhanQuyen nv) {
		nv.setMaNV(nv.getChucVu() == "Admin" ? taoMa("AD") : taoMa("NV"));	
		String[] mangNS = nv.getNgaySinh().split("-");
		String password = nv.getMaNV().toLowerCase() + "@";
		for (String s: mangNS) password += s;			
		return PhanQuyenDAO.insert(nv, password);
	}
	
	public static boolean update(PhanQuyen nv) {	
		return PhanQuyenDAO.update(nv);	
	}
	
	public static boolean resetPassword(String maNV, String ngaySinh) {		
		String[] mangNS = ngaySinh.split("-");		
		String password = maNV.toLowerCase() + "@";
		for (String s: mangNS) password += s;
		
		return PhanQuyenDAO.resetPassword(maNV, password);
	}
	
	public static PhanQuyen dangNhap(String maNV, String password) {
		return PhanQuyenDAO.dangNhap(maNV, password);
	}
}
