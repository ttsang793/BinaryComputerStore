package DTO;

public class PhanQuyen extends NhanVien {
	private int quyen;
	
	public PhanQuyen() {}
	
	public PhanQuyen(String maNV) { super(maNV); }
	
	public PhanQuyen(String maNV, String tenNV, String chucVu, String ngayVaoLam, String ngaySinh, String maQL, int quyen) {
		super(maNV, tenNV, chucVu, ngayVaoLam, ngaySinh, maQL);
		this.quyen = quyen;
	}
	
	public PhanQuyen(String maNV, String tenNV, String chucVu, String ngayVaoLam, String ngaySinh, String maQL, boolean trangThai, int quyen) {
		super(maNV, tenNV, chucVu, ngayVaoLam, ngaySinh, maQL, trangThai);
		this.quyen = quyen;
	}
	
	public int getQuyen() {
		return quyen;
	}
}
