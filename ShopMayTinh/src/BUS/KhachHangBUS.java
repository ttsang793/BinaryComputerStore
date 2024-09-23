package BUS;
import java.util.LinkedList;
import DAO.KhachHangDAO;
import DTO.KhachHang;

public abstract class KhachHangBUS {
	protected static LinkedList<KhachHang> danhSachKH = new LinkedList<>();
	
	public static LinkedList<KhachHang> getDanhSachKH() {
		return danhSachKH;
	}
	
	public static LinkedList<KhachHang> search(String tieuChi, String value) {
		LinkedList<KhachHang> result = new LinkedList<>();
		for (KhachHang kh: danhSachKH)
			if ((tieuChi.equals("Tên KH") && kh.getTenKH().contains(value)) ||
					(tieuChi.equals("CMND") && kh.getCMND().contains(value)) ||
					(tieuChi.equals("Số điện thoại") && kh.getSoDienThoai().contains(value)))
				result.add(kh);
		return result;
	}
	
	public static void load() {
		danhSachKH = KhachHangDAO.select();
	}

	public static String[] kiemTra(KhachHang kh) {
		boolean error = false;
		String[] result = new String[4];
		for (int i=0; i<4; i++) result[i] = "";
		
		if (kh.getCMND().isEmpty()) { error = true; result[0] = "Vui lòng nhập số chứng minh nhân dân"; }
		else if (!Check.soCMND(kh.getCMND())) { error = true; result[0] = "Vui lòng nhập đúng cú pháp số chứng minh nhân dân"; }
		
		if (kh.getTenKH().isEmpty()) { error = true; result[1] = "Vui lòng nhập tên khách hàng"; }
		
		if (Time.isFuture(kh.getNgaySinh())) { error = true; result[2] = "Vui lòng không nhập ngày sinh tương lai"; }
		
		if (kh.getSoDienThoai().isEmpty()) { error = true; result[3] = "Vui lòng nhập đầy đủ thành phần"; }
		else if (!Check.soDT(kh.getSoDienThoai())) { error = true; result[3] = "Vui lòng nhập đúng cú pháp số điện thoại"; }
		
		return error ? result : new String[0];
	}
	
	public static boolean insert(KhachHang kh) {
		return KhachHangDAO.insert(kh);
	}
}
