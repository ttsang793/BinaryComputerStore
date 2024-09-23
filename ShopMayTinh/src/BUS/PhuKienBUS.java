package BUS;

import java.util.LinkedList;
import DAO.SanPhamDAO;
import DTO.SanPham;
import javax.swing.Icon;

public abstract class PhuKienBUS {
	private static LinkedList<SanPham> danhSachSP = new LinkedList<SanPham>();
	private enum prop {
		TenSP(0), LoaiSP(1), DonGia(2), AnhSP(3);
		private int value;
		prop(int value) { this.value = value; }
		public int index() { return value; }
	}
	
	public static LinkedList<SanPham> getDanhSachSP() {
		return danhSachSP;
	}

	private static String taoMa() {
		String chuSo = Integer.toString(danhSachSP.size() + 1);
		while (chuSo.length() < 6) chuSo = "0" + chuSo;
		return "SP" + chuSo;		
	}
	
	public static String[] kiemTra(SanPham sp, Icon icon) {
		boolean error = false;
		String[] result = new String[prop.values().length];
		
		result[prop.TenSP.index()] = SanPhamCheck.TenSP(sp.getTenSP());
		error = !result[prop.TenSP.index()].isEmpty();
		
		result[prop.LoaiSP.index()] = sp.getLoaiSP().equals("Chọn loại sản phẩm") ? "Vui lòng chọn loại sản phẩm" : "";
		error = !result[prop.LoaiSP.index()].isEmpty();
		
		result[prop.DonGia.index()] = SanPhamCheck.DonGia(sp.getDonGia());
		error = !result[prop.DonGia.index()].isEmpty();
		
		result[prop.AnhSP.index()] = SanPhamCheck.AnhSP(icon);
		error = !result[prop.AnhSP.index()].isEmpty();
		
		return error ? result : new String[0];
	}
	
	public static void load() {
		danhSachSP = SanPhamDAO.select();
	}
	
	public static boolean insert(SanPham sp, Icon icon, String anh) {
		sp.setMaSP(taoMa());
		sp.setAnhSP("..\\img\\" + sp.getMaSP() + anh.substring(anh.length()-4));
		if (!SanPhamDAO.insert(sp)) return false;
		Anh.copy(anh, sp.getAnhSP());
		return true;
	}
	
	public static boolean update(SanPham sp, Icon icon, String anh) {
		if (!anh.isEmpty()) sp.setAnhSP("..\\img\\" + sp.getMaSP() + anh.substring(anh.length()-4));
		if (!SanPhamDAO.update(sp, anh)) return false;
		if (!anh.isEmpty()) Anh.copy(anh, sp.getAnhSP());
		return true;
	}
}