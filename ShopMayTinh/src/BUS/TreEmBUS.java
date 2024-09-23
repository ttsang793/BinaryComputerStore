package BUS;
import DAO.KhachHangDAO;
import DTO.TreEm;

public abstract class TreEmBUS extends KhachHangBUS {
	private static int getGiamHo(TreEm kh) {
		for (int i=0; i<danhSachKH.size(); i++)
			if (danhSachKH.get(i).getCMND().equals(kh.getCMND()))
				return danhSachKH.get(i).getMaKH();
		return -1;
	}
	
	public static String[] kiemTra(TreEm kh) {
		boolean error = false;
		String[] result = new String[4];
		for (int i=0; i<4; i++) result[i] = "";
		
		if (kh.getCMND().isEmpty()) { error = true; result[0] = "Vui lòng nhập số chứng minh nhân dân"; }
		else if (!Check.soCMND(kh.getCMND())) { error = true; result[0] = "Vui lòng nhập đúng cú pháp số chứng minh nhân dân"; }
		else if (getGiamHo(kh) == -1) { error = true; result[0] = "Vui lòng chọn lại giám hộ"; }
		
		if (kh.getTenKH().isEmpty()) { error = true; result[1] = "Vui lòng nhập tên khách hàng"; }
		
		if (Time.isFuture(kh.getNgaySinh())) { error = true; result[2] = "Vui lòng không nhập ngày sinh tương lai"; }
		
		if (kh.getSoDienThoai().isEmpty()) { error = true; result[3] = "Vui lòng nhập đầy đủ thành phần"; }
		else if (!Check.soDT(kh.getSoDienThoai())) { error = true; result[3] = "Vui lòng nhập đúng cú pháp số điện thoại"; }
		
		return error ? result : new String[0];
	}
	
	public static boolean insert(TreEm kh) {
		int giamHo = getGiamHo(kh);
		kh.setMaNL(giamHo);			
		return KhachHangDAO.insert(kh);
	}
}
