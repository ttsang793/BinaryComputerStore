package DTO;

public class SanPham {
	private String maSP;
	private String tenSP;
	private String loaiSP;
	private String anhSP;
	private String donGia;
	private String soLuong;
	private int trangThai;
	
	SanPham() {}
	
	public SanPham(String tenSP, String loaiSP, String anhSP, String donGia) {
		this.maSP = "";
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		this.anhSP = anhSP;
		this.donGia = donGia;
	}
	
	public SanPham(String maSP, String tenSP, String loaiSP, String anhSP, String donGia, int trangThai) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		this.anhSP = anhSP;
		this.donGia = donGia;
		this.trangThai = trangThai;
	}
	
	public SanPham(String maSP, String tenSP, String loaiSP, String anhSP, String donGia, String soLuong, int trangThai) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		this.anhSP = anhSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getAnhSP() {
		return anhSP;
	}

	public void setAnhSP(String anhSP) {
		this.anhSP = anhSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public String getLoaiSP() {
		return loaiSP;
	}

	public String getDonGia() {
		return donGia;
	}

	public String getSoLuong() {
		return soLuong;
	}

	public int getTrangThai() {
		return trangThai;
	}
	
	public String toString() {
		return tenSP + " - " + maSP;
	}
}