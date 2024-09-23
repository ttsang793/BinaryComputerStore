package DTO;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String diaChiNCC;
	private String soDTNCC;
	private String emailNCC;
	private boolean trangThai;
	
	public NhaCungCap(String maNCC) {
		this.maNCC = maNCC;
		this.tenNCC = "";
	}
	
	public NhaCungCap(String tenNCC, String diaChiNCC, String soDTNCC, String emailNCC) {
		this.maNCC = "";
		this.tenNCC = tenNCC;
		this.diaChiNCC = diaChiNCC;
		this.soDTNCC = soDTNCC;
		this.emailNCC = emailNCC;
		this.trangThai = true;
	}
	
	public NhaCungCap(String maNCC, String tenNCC, String diaChiNCC, String soDTNCC, String emailNCC, boolean trangThai) {
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChiNCC = diaChiNCC;
		this.soDTNCC = soDTNCC;
		this.emailNCC = emailNCC;
		this.trangThai = trangThai;
	}
	
	public String getMaNCC() {
		return maNCC;
	}
	
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	
	public String getTenNCC() {
		return tenNCC;
	}
	
	public String getDiaChiNCC() {
		return diaChiNCC;
	}
	
	public String getSoDTNCC() {
		return soDTNCC;
	}
	
	public String getEmailNCC() {
		return emailNCC;
	}
	
	public boolean getTrangThai() {
		return trangThai;
	}
	
	public String toString() {
		if (tenNCC.isEmpty()) return maNCC;
		return tenNCC + " - " + maNCC;
	}
}
