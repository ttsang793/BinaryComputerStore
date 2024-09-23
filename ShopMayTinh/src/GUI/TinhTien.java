package GUI;
import java.lang.Math;
//import java.text.DecimalFormat;

class TinhTien {
	private long tong;
	private double phanTram;
	private int thang;
	
	//private final DecimalFormat decimal = new DecimalFormat("###,###.##");

	public TinhTien(String tong, String phanTram, String thang) {
		this.tong = Long.parseLong(tong);
		this.phanTram = Double.parseDouble(phanTram.substring(0, phanTram.length() - 1)) / 100;
		this.thang = Integer.parseInt(thang);

		tinhTraTruoc();
		tinhLaiSuat();
		tinhBaoHiem();
		tinhTienTraHangThang();
	}

	private long tienTraTruoc;
	private double laiSuat = 0.02;
	private long baoHiem;
	private long tienTraHangThang;

	private void tinhTraTruoc() {
		double chuaLamTron = tong * phanTram;
		tienTraTruoc = Math.round(chuaLamTron / 500) * 500;
	}

	private void tinhLaiSuat() {
		if (phanTram == 1) {
			laiSuat = 0;
			return;
		}
		if (thang == 6 || phanTram >= 0.5) laiSuat -= 0.0005;
		laiSuat = laiSuat + 0.001*(thang - 6);
	}

	private void tinhBaoHiem() {
		if ((thang <= 12 && tong <= 15000000) || (phanTram == 1)) {
			baoHiem = 0;
			return;
		}
		baoHiem = Math.round(tong * 0.001 / 500) * 500;
		baoHiem = baoHiem + baoHiem * (tong / 15000000 - 1) / 2;
		if (thang > 12) baoHiem *= 1.5;
	}

	private void tinhTienTraHangThang() {
		if (phanTram == 1) {
			tienTraHangThang = 0;
			return;
		}
		double result = Math.ceil((tong - tienTraTruoc) * 1.0 / thang / 1000) * 1000;
		result = Math.ceil((result*(1 + laiSuat) + baoHiem) / 1000) * 1000;
		tienTraHangThang = (long)result;
	}

	String getTraTruoc() {
		return Long.toString(tienTraTruoc);
	}

	String getLaiSuat() {
		return Double.toString(laiSuat * 100);
	}

	String getBaoHiem() {
		return Long.toString(baoHiem);
	}

	String getTienTraHangThang() {
		return Long.toString(tienTraHangThang);
	}
	
	String getChenhLech() {
		return Long.toString(tienTraHangThang * thang + tienTraTruoc - tong);
	}
	
	String getTongGop() {
		return Long.toString(tienTraHangThang * thang + tienTraTruoc);
	}
}