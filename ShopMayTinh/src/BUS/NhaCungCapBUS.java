package BUS;

import java.util.LinkedList;
import DTO.NhaCungCap;
import DAO.NhaCungCapDAO;

public abstract class NhaCungCapBUS {
	private static LinkedList<NhaCungCap> danhSachNCC = new LinkedList<NhaCungCap>();
	
	public static LinkedList<NhaCungCap> getDanhSachNCC() {
		return danhSachNCC;
	}

	public static void setDanhSachNCC(LinkedList<NhaCungCap> danhSachNCC) {
		NhaCungCapBUS.danhSachNCC = danhSachNCC;
	}

	private static String taoMa() {
		String chuSo = Integer.toString(danhSachNCC.size() + 1);
		while (chuSo.length() < 4) chuSo = "0" + chuSo;
		return "NCC" + chuSo;		
	}
	
	public static void load() {
		danhSachNCC = NhaCungCapDAO.select();
	}
	
	public static String[] kiemTra(NhaCungCap ncc) {
		boolean error = false;
		String[] result = new String[4];
		for (int i=0; i<result.length; i++) result[i] = "";
		
		if (ncc.getTenNCC().isEmpty()) { error = true; result[0] = "Vui lòng nhập tên nhà cung cấp"; }
		if (ncc.getDiaChiNCC().isEmpty()) { error = true; result[1] = "Vui lòng nhập địa chỉ của nhà cung cấp"; }
		if (ncc.getSoDTNCC().isEmpty()) { error = true; result[2] = "Vui lòng nhập số điện thoại"; }
		else if (!Check.soDT(ncc.getSoDTNCC())) { error = true; result[2] = "Vui lòng nhập đúng cú pháp số điện thoại"; }	
		if (ncc.getEmailNCC().isEmpty()) { error = true; result[3] = "Vui lòng nhập email của nhà cung cấp"; }
		else if (!Check.email(ncc.getEmailNCC())) { error = true; result[3] = "Vui lòng nhập đúng cú pháp email"; }
		
		return error ? result : new String[0];
	}
	
	public static boolean insert(NhaCungCap ncc) {
		ncc.setMaNCC(taoMa());
		return NhaCungCapDAO.insert(ncc);
	}
	
	public static boolean update(NhaCungCap ncc) {
		return NhaCungCapDAO.update(ncc);
	}
}