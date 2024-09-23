package DTO;

public class DonHang {
	private String maDH;
	private String ngayDH;
	private String khMua;
	private String khNhan;
	private String maNV;
	private String thangTraGop;
	private String gopHangThang;
	private String tongTien;
	
	public DonHang(String maDH, String ngayDH, String thangTraGop, String gopHangThang, String tongTien) {
		this.maDH = maDH;
		this.ngayDH = ngayDH;
		this.thangTraGop = thangTraGop;
		this.gopHangThang = gopHangThang;
		this.tongTien = tongTien;
	}
	
	public DonHang(String maDH, String ngayDH, String khMua, String khNhan, String maNV, String thangTraGop, String gopHangThang) {
		this.maDH = maDH;
		this.ngayDH = ngayDH;
		this.khMua = khMua;
		this.khNhan = khNhan;
		this.maNV = maNV;
		this.thangTraGop = thangTraGop;
		this.gopHangThang = gopHangThang;
	}

	public String getMaDH() {
		return maDH;
	}

	public void setMaDH(String maDH) {
		this.maDH = maDH;
	}

	public String getNgayDH() {
		return ngayDH;
	}

	public String getKHMua() {
		return khMua;
	}

	public void setKHMua(String khMua) {
		this.khMua = khMua;
	}

	public String getKHNhan() {
		return khNhan;
	}

	public void setKHNhan(String khNhan) {
		this.khNhan = khNhan;
	}

	public String getMaNV() {
		return maNV;
	}

	public String getThangTraGop() {
		return thangTraGop;
	}

	public String getGopHangThang() {
		return gopHangThang;
	}

	public String getTongTien() {
		return tongTien;
	}
}