package DTO;

public class KhachHang {
	private int maKH;
	private String tenKH;
	private String ngaySinh;
	private String gioiTinh;
	private String soDienThoai;
	private String CMND;
	
	public KhachHang() {
		this.maKH = -1;
	}
	
	public KhachHang(int maKH, String tenKH, String ngaySinh, String gioiTinh, String soDienThoai, String CMND) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.CMND = CMND;
	}

	public int getMaKH() {
		return maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}
	
	public String getCMND() {
		return CMND;
	}
}