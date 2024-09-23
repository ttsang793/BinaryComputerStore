package BUS;
import java.time.LocalDate;

public interface LaptopCheck extends SanPhamCheck {
	int RAM_MIN = 2;
	int TANSO_MIN = 60;
	
	static String ThuongHieu(String thuongHieu) {
		if (thuongHieu.equals("Chọn thương hiệu"))
			return "Vui lòng chọn thương hiệu";
		return "";		
	}
	
	static String TrongLuong(String trongLuong) {
		if (trongLuong.isEmpty()) return "Không được để trống";
		try {
			if (Float.parseFloat(trongLuong) <= 0)
				return "Vui lòng nhập số > 0";
		}
		catch (Exception e) {
			return "Vui lòng nhập số";
		}
		return "";
	}
	
	static String NamRaMat(String namRaMat) {
		if (namRaMat.isEmpty()) return "Không được để trống";
		try {
			int nam = Integer.parseInt(namRaMat);
			if (nam <= 2000) return "Năm phải lớn hơn 2000";
			if (nam > LocalDate.now().getYear())
				return "Năm phải nhỏ hơn " + (LocalDate.now().plusYears(1));
		}
		catch (Exception e) {
			return "Vui lòng nhập số";
		}
		return "";
	}
	
	static String MauSac(String mauSac) {
		if (mauSac.isEmpty()) return "Không được để trống";
		return "";
	}
	
	static String ChatLieu(String chatLieu) {
		if (chatLieu.isEmpty()) return "Không được để trống";
		return "";		
	}
	
	static String HeDieuHanh(String heDieuHanh, String thuongHieu) {
		if (heDieuHanh.isEmpty()) return "Không được để trống";
		if ((thuongHieu.contains("Mac") && heDieuHanh.contains("Windows")) ||
			(!thuongHieu.contains("Mac") && heDieuHanh.contains("Mac")))
			return "Sai hệ điều hành";
		return "";
	}
	
	static String BaoMat(String baoMat) {
		if (baoMat.isEmpty()) return "Không được để trống";
		return "";		
	}
	
	static String Bluetooth(String bluetooth) {
		if (bluetooth.isEmpty()) return "Không được để trống";
		try {
			if (Float.parseFloat(bluetooth) <= 0)
				return "Phiên bản Bluetooth phải > 0";
		}
		catch (Exception e) {
			return "Vui lòng nhập số";
		}
		return "";		
	}
	
	static String Pin(String pin) {
		if (pin.isEmpty()) return "Không được để trống";
		return "";
	}
	
	static String CPU(String CPU) {
		if (CPU.isEmpty()) return "Không được để trống";
		return "";		
	}
	
	static String RAM(String RAM) {
		if (RAM.isEmpty()) return "Không được để trống";
		try {
			if (Integer.parseInt(RAM) < RAM_MIN)
				return "RAM phải >= " + RAM_MIN + "GB";
		}
		catch (Exception e) {
			return "Vui lòng nhập số";
		}
		return "";
	}
	
	static String NangCapRAM(String RAMNC, String RAMHT) {
		if (RAMNC.isEmpty()) return "Không được để trống";
		
		if (!RAMHT.isEmpty())
			try {
				if (Integer.parseInt(RAMHT) >= Integer.parseInt(RAMNC))
					return "Phải lớn hơn RAM hiện tại";
			}
			catch (Exception e) {
				return "Vui lòng nhập số";
			}
		return "";
	}
	
	static String GPU(String GPU) {
		if (GPU.isEmpty()) return "Không được để trống";
		return "";
	}
	
	static String OCung(String dungLuong, String loaiOCung) {
		if (dungLuong.isEmpty()) return "Không được để trống";
		
		try {
			int conSo = Integer.parseInt(dungLuong.substring(0, dungLuong.length() - 2));
			String donViDo = dungLuong.substring(dungLuong.length() - 2);
			if (conSo < 0)
				return "Vui lòng nhập dung lượng ổ cứng của máy lớn hơn 0";
			if (!donViDo.equals("GB") && !donViDo.equals("TB"))
				return "Vui lòng nhập đúng đơn vị ổ cứng (TB hoặc GB)";
			if (loaiOCung.equals("Chọn loại ổ cứng"))
				return "Vui lòng chọn loại ổ cứng";
		}
		catch (Exception e) {
			return "Ổ cứng nhập không hợp lệ";
		}
		
		return "";
	}
	
	static String ManHinh(String manHinh) {
		if (manHinh.isEmpty()) return "Không được để trống";
		try {
			if (Float.parseFloat(manHinh) <= 0)
				return "Vui lòng nhập số > 0";
		}
		catch (Exception e) {
			return "Vui lòng nhập số";
		}
		return "";
	}
	
	static String TanSo(String tanSo) {
		if (tanSo.isEmpty()) return "Không được để trống";
		try {
			if (Integer.parseInt(tanSo) < TANSO_MIN)
				return "Vui lòng nhập >= " + TANSO_MIN + "Hz";
		}
		catch (Exception e) {
			return "Vui lòng nhập số";
		}
		return "";
	}
	
	static boolean KiemSoDo(String soDo) {
		try {
			Integer.parseInt(soDo);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	static String DoPhanGiai(String doPhanGiai) {
		if (doPhanGiai.split("x").length == 0) return "Không được để trống chiều dài và chiều rộng";
		else if (doPhanGiai.split("x").length == 1) return "Không được để trống chiều rộng";
		
		String chieuDai = doPhanGiai.split("x")[0];
		String chieuRong = doPhanGiai.split("x")[1];
		
		if (chieuDai.isEmpty()) return "Không được để trống chiều dài";
		
		if (!KiemSoDo(chieuDai)) return "Vui lòng nhập chiều dài là số";
		if (!KiemSoDo(chieuRong)) return "Vui lòng nhập chiều rộng là số";
		
		int dai = Integer.parseInt(chieuDai); 
		int rong = Integer.parseInt(chieuRong);
		
		if (dai <= 0) return "Vui lòng nhập chiều dài > 0";
		if (rong <= 0) return "Vui lòng nhập chiều rộng > 0";
		if (dai < rong) return "Chiều dài phải lớn hơn chiều rộng";
		
		return "";		
	}
	
	static String CongNghe(String congNghe) {
		if (congNghe.isEmpty()) return "Không được để trống";
		return "";
	}
	
	static String TamNen(String tamNen) {
		if (tamNen.isEmpty()) return "Không được để trống";
		return "";
	}
}