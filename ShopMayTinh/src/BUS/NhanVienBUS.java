package BUS;

import DTO.NhanVien;
import DAO.NhanVienDAO;
import java.util.LinkedList;

public abstract class NhanVienBUS {
	private static LinkedList<NhanVien> danhSachNV = new LinkedList<>();
	
	public static LinkedList<NhanVien> getDanhSachNV() { return danhSachNV; }
	
	public static void load(String maQL) {
		danhSachNV = NhanVienDAO.select(maQL);
	}
	
	public static String[] kiemTra(NhanVien nv) {
		boolean error = false;
		String[] result = new String[3];
		for (int i=0; i<result.length; i++) result[i] = "";
		
		if (nv.getTenNV().isEmpty()) { error = true; result[0] = "Vui lòng nhập tên nhân viên"; }
		
		if (nv.getNgayVaoLam().isEmpty())
			{ error = true; result[1] = "Vui lòng nhập ngày vào làm"; }
		else if (Time.isFuture(nv.getNgayVaoLam()))
			{ error = true; result[1] = "Vui lòng không nhập ngày tháng tương lai"; }
		
		if (nv.getNgayVaoLam().isEmpty())
			{ error = true; result[2] = "Vui lòng nhập ngày sinh"; }
		else if (Time.isFuture(nv.getNgaySinh()))
			{ error = true; result[2] = "Vui lòng không nhập ngày tháng tương lai"; }
		return error ? result : new String[0];
	}
	
	public static boolean update(NhanVien nv) {	
		return NhanVienDAO.update(nv);	
	}
	
	public static boolean delete(String maNV) {
		return NhanVienDAO.delete(maNV);
	}
	
	public static boolean dieuPhoi(String maNV, String maQL) {
		return NhanVienDAO.dieuPhoi(maNV, maQL);
	}
	
	public static boolean thangChuc(String maNV) {
		return NhanVienDAO.thangChuc(maNV);
	}
}