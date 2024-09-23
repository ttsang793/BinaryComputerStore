package DTO;

public class TreEm extends KhachHang {
	private int maNL;
	
	public TreEm(int maKH, String tenKH, String ngaySinh, String gioiTinh, String soDienThoai, String CMND, int maNL) {
		super(maKH, tenKH, ngaySinh, gioiTinh, soDienThoai, CMND);
		this.maNL = maNL;
	}

	public int getMaNL() {
		return maNL;
	}

	public void setMaNL(int maNL) {
		this.maNL = maNL;
	}
}
