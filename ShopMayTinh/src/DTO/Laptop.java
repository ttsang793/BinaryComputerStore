package DTO;

public class Laptop extends SanPham {
	private String trongLuong;
	private String namRaMat;
	private String mauSac;
	private String chatLieu;
	private String heDieuHanh;
	private String thuongHieu;
	private String baoMat;
	private String bluetooth;
	private String pin;
	private String CPU;
	private String RAM;
	private String nangCapRAM;
	private String GPU;
	private String oCung;
	private String manHinh;
	private String tanSo;
	private String doPhanGiai;
	private String congNghe;
	private String tamNen;
	
	public Laptop(String maSP, String tenSP, String anhSP, String donGia, int trangThai, String trongLuong, String namRaMat,
			String mauSac, String chatLieu, String heDieuHanh, String thuongHieu, String baoMat, String bluetooth, String pin, String CPU,
			String RAM, String nangCapRAM, String GPU, String oCung, String manHinh, String tanSo, String doPhanGiai, String congNghe, String tamNen) {
		super(maSP, tenSP, "Laptop", anhSP, donGia, trangThai);
		this.trongLuong = trongLuong;
		this.namRaMat = namRaMat;
		this.mauSac = mauSac;
		this.chatLieu = chatLieu;
		this.heDieuHanh = heDieuHanh;
		this.thuongHieu = thuongHieu;
		this.baoMat = baoMat;
		this.bluetooth = bluetooth;
		this.pin = pin;
		this.CPU = CPU;
		this.RAM = RAM;
		this.nangCapRAM = nangCapRAM;
		this.GPU = GPU;
		this.oCung = oCung;
		this.manHinh = manHinh;
		this.tanSo = tanSo;
		this.doPhanGiai = doPhanGiai;
		this.congNghe = congNghe;
		this.tamNen = tamNen;
	}
	
	public Laptop(String maSP, String tenSP, String anhSP, String donGia, String soLuong, int trangThai, String trongLuong, String namRaMat,
			String mauSac, String chatLieu, String heDieuHanh, String thuongHieu, String baoMat, String bluetooth, String pin, String CPU,
			String RAM, String nangCapRAM, String GPU, String oCung, String manHinh, String tanSo, String doPhanGiai, String congNghe, String tamNen) {
		super(maSP, tenSP, "Laptop", anhSP, donGia, soLuong, trangThai);
		this.trongLuong = trongLuong;
		this.namRaMat = namRaMat;
		this.mauSac = mauSac;
		this.chatLieu = chatLieu;
		this.heDieuHanh = heDieuHanh;
		this.thuongHieu = thuongHieu;
		this.baoMat = baoMat;
		this.bluetooth = bluetooth;
		this.pin = pin;
		this.CPU = CPU;
		this.RAM = RAM;
		this.nangCapRAM = nangCapRAM;
		this.GPU = GPU;
		this.oCung = oCung;
		this.manHinh = manHinh;
		this.tanSo = tanSo;
		this.doPhanGiai = doPhanGiai;
		this.congNghe = congNghe;
		this.tamNen = tamNen;
	}
	
	public String getThuongHieu() {
		return thuongHieu;
	}
	public String getNamRaMat() {
		return namRaMat;
	}
	public String getTrongLuong() {
		return trongLuong;
	}
	public String getMauSac() {
		return mauSac;
	}
	public String getChatLieu() {
		return chatLieu;
	}
	public String getCPU() {
		return CPU;
	}
	public String getRAM() {
		return RAM;
	}
	public String getNangCapRAM() {
		return nangCapRAM;
	}
	public String getManHinh() {
		return manHinh;
	}
	public String getDoPhanGiai() {
		return doPhanGiai;
	}
	public String getTanSo() {
		return tanSo;
	}
	public String getCongNghe() {
		return congNghe;
	}
	public String getTamNen() {
		return tamNen;
	}
	public String getGPU() {
		return GPU;
	}
	public String getOCung() {
		return oCung;
	}
	public String getDungLuongOCung() {
		return oCung.substring(0,oCung.indexOf(" "));
	}
	public String getLoaiOCung() {
		return oCung.substring(oCung.indexOf(" ")+1);
	}
	public String getBaoMat() {
		return baoMat;
	}
	public String getBluetooth() {
		return bluetooth;
	}
	public String getPin() {
		return pin;
	}
	public String getHeDieuHanh() {
		return heDieuHanh;
	}
}
