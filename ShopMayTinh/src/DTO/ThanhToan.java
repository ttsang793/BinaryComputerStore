package DTO;

public class ThanhToan {
	private String maDH;
	private String ngayTT;
	private String maNV;
	private String soThang;
	private String soTien;
	
	public ThanhToan(String maDH, String ngayTT, String maNV, String soThang, String soTien) {
		this.maDH = maDH;
		this.ngayTT = ngayTT;
		this.maNV = maNV;
		this.soThang = soThang;
		this.soTien = soTien;
	}
	public String getMaDH() {
		return maDH;
	}
	
	public String getNgayTT() {
		return ngayTT;
	}
	
	public String getMaNV() {
		return maNV;
	}
	
	public String getSoThang() {
		return soThang;
	}
	
	public String getSoTien() {
		return soTien;
	}
}
