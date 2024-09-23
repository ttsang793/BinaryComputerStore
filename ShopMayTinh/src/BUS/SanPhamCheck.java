package BUS;
import javax.swing.Icon;

public interface SanPhamCheck {
	static String TenSP(String tenSP) {
		return (tenSP.isEmpty()) ? "Không được để trống" : "";
	}
	
	static String DonGia(String donGia) {
		if (donGia.isEmpty()) return "Vui lòng nhập giá sản phẩm";
		try {
			long gia = Long.parseLong(donGia);
			if (gia <= 0) return "Vui lòng nhập giá là số dương";
			else if (gia % 500 != 0) return "Vui lòng nhập giá chia hết cho 500";
		}
		catch (Exception e) {
			return "Vui lòng nhập đơn giá là số";
		}
		return "";
	}
	
	static String SoLuong(String soLuong) {
		if (soLuong.isEmpty()) return "Vui lòng nhập số lượng sản phẩm";
		try {
			int gia = Integer.parseInt(soLuong);
			if (gia <= 0) return "Vui lòng nhập số lượng là số dương";
		}
		catch (Exception e) {
			return "Vui lòng nhập số lượng là số";
		}
		return "";
	}
	
	static String AnhSP(Icon icon) {
		return icon == null ? "Vui lòng chọn hình" : "";
	}
}