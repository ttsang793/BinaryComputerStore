package DTO;

public class ChiTiet {
	private String maGT;
	private String maSP;
	private String tenSP;
	private String donGia;
	private String soLuong;
	
	public ChiTiet(String maGT, String maSP, String donGia, String soLuong) {
		this.maGT = maGT;
		this.maSP = maSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}
	
	public ChiTiet(String maGT, String maSP, String tenSP, String donGia, String soLuong) {
		this.maGT = maGT;
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}
	
	public String getMaGT() {
		return maGT;
	}
	
	public String getMaSP() {
		return maSP;
	}
	
	public String getTenSP() {
		return tenSP;
	}
	
	public String getDonGia() {
		return donGia;
	}
	
	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}
	
	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}	
}