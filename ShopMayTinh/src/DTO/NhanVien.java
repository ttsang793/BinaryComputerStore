package DTO;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String chucVu;
	private String ngayVaoLam;
	private String ngaySinh;
	private String maQL;
	private boolean trangThai;
	
	public NhanVien() {}
	
	public NhanVien(String maNV) {
		this.maNV = maNV;
		this.tenNV = "";
	}
	
	public NhanVien(String maNV, String chucVu) {
		this.maNV = maNV;
		this.chucVu = chucVu;		
	}
	
	public NhanVien(String tenNV, String chucVu, String ngayVaoLam, String ngaySinh, String maQL) {
		this.maNV = "";
		this.tenNV = tenNV;
		this.chucVu = chucVu;
		this.ngayVaoLam = ngayVaoLam;
		this.ngaySinh = ngaySinh;
		this.maQL = maQL;
	}

	public NhanVien(String maNV, String tenNV, String chucVu, String ngayVaoLam, String ngaySinh, String maQL) {
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.chucVu = chucVu;
		this.ngayVaoLam = ngayVaoLam;
		this.ngaySinh = ngaySinh;
		this.maQL = maQL;
	}

	public NhanVien(String maNV, String tenNV, String chucVu, String ngayVaoLam, String ngaySinh, String maQL, boolean trangThai) {
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.chucVu = chucVu;
		this.ngayVaoLam = ngayVaoLam;
		this.ngaySinh = ngaySinh;
		this.maQL = maQL;
		this.trangThai = trangThai;
	}

	public String getMaNV() {
		return maNV;
	}
	
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public String getChucVu() {
		return chucVu;
	}

	public String getNgayVaoLam() {
		return ngayVaoLam;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}
	
	public String getMaQL() {
		return maQL;
	}
	
	public boolean getTrangThai() {
		return trangThai;
	}
	
	public String toString() {
		if (tenNV.isEmpty()) return maNV;
		return maNV + " - " + tenNV;
	}
}