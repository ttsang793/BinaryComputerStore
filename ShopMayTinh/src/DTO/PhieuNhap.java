package DTO;

public class PhieuNhap {
	private String maPN;
	private String ngayNhap;
	private String maNCC;
	private long tongTien;
	private String maNV;
	
	public PhieuNhap(String maPN, String ngayNhap, String maNCC, long tongTien, String maNV) {
		this.maPN = maPN;
		this.ngayNhap = ngayNhap;
		this.maNCC = maNCC;
		this.tongTien = tongTien;
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaPN() {
		return maPN;
	}

	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}

	public String getNgayNhap() {
		return ngayNhap;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public long getTongTien() {
		return tongTien;
	}
}