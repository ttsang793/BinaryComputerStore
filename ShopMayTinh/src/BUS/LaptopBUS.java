package BUS;

import DTO.Laptop;
import DAO.LaptopDAO;
import java.util.LinkedList;
import javax.swing.Icon;

public abstract class LaptopBUS {
	private static LinkedList<Laptop> danhSachLaptop = new LinkedList<>();
	private enum prop {
		TenSP(0), DonGia(1), AnhSP(2), ThuongHieu(3), TrongLuong(4), NamRaMat(5),
		MauSac(6), ChatLieu(7), HeDieuHanh(8), BaoMat(9), Bluetooth(10),
		Pin(11), CPU(12), RAM(13), NangCapRAM(14), GPU(15),
		OCung(16), ManHinh(17), TanSo(18), DoPhanGiai(19), CongNghe(20),
		TamNen(21);
		private int value;
		prop(int value) { this.value = value; }
		public int index() { return value; }
	}	
	
	public static LinkedList<Laptop> getDanhSachLaptop() {
		return danhSachLaptop;
	}

	private static int timSoLuong(String thuongHieu) {
		int count = 0;
		for (Laptop lap: danhSachLaptop)
			if (lap.getThuongHieu() == thuongHieu) count++;
		return count;
	}
	
	private static String taoMa(String thuongHieu) {
		String chuSo = Integer.toString(timSoLuong(thuongHieu) + 1);
		while (chuSo.length() < 4) chuSo = "0" + chuSo;
		return thuongHieu + chuSo;
	}
	
	public static void load() {
		danhSachLaptop = LaptopDAO.select();
	}
	
	public static String[] kiemTra(Laptop lap, Icon icon) {
		boolean error = false;
		String[] result = new String[prop.values().length];
		
		result[prop.TenSP.index()] = SanPhamCheck.TenSP(lap.getTenSP());
		error = error ? true : !result[prop.TenSP.index()].isEmpty();
		
		result[prop.DonGia.index()] = SanPhamCheck.DonGia(lap.getDonGia());
		error = error ? true : !result[prop.DonGia.index()].isEmpty();
		
		result[prop.AnhSP.index()] = SanPhamCheck.AnhSP(icon);
		error = error ? true : !result[prop.AnhSP.index()].isEmpty();
		
		result[prop.ThuongHieu.index()] = LaptopCheck.ThuongHieu(lap.getThuongHieu());
		error = error ? true : !result[prop.ThuongHieu.index()].isEmpty();
		
		result[prop.TrongLuong.index()] = LaptopCheck.TrongLuong(lap.getTrongLuong());
		error = error ? true : !result[prop.TrongLuong.index()].isEmpty();
		
		result[prop.NamRaMat.index()] = LaptopCheck.NamRaMat(lap.getNamRaMat());
		error = error ? true : !result[prop.NamRaMat.index()].isEmpty();
		
		result[prop.MauSac.index()] = LaptopCheck.MauSac(lap.getMauSac());
		error = error ? true : !result[prop.MauSac.index()].isEmpty();
		
		result[prop.ChatLieu.index()] = LaptopCheck.ChatLieu(lap.getChatLieu());
		error = error ? true : !result[prop.ChatLieu.index()].isEmpty();
		
		result[prop.HeDieuHanh.index()] = LaptopCheck.HeDieuHanh(lap.getHeDieuHanh(), lap.getThuongHieu());
		error = error ? true : !result[prop.HeDieuHanh.index()].isEmpty();
		
		result[prop.BaoMat.index()] = LaptopCheck.BaoMat(lap.getBaoMat());
		error = error ? true : !result[prop.BaoMat.index()].isEmpty();
		
		result[prop.Bluetooth.index()] = LaptopCheck.Bluetooth(lap.getBluetooth());
		error = error ? true : !result[prop.Bluetooth.index()].isEmpty();
		
		result[prop.Pin.index()] = LaptopCheck.Pin(lap.getPin());
		error = error ? true : !result[prop.Pin.index()].isEmpty();
		
		result[prop.CPU.index()] = LaptopCheck.CPU(lap.getCPU());
		error = error ? true : !result[prop.CPU.index()].isEmpty();
		
		result[prop.RAM.index()] = LaptopCheck.RAM(lap.getRAM());
		error = error ? true : !result[prop.RAM.index()].isEmpty();
		
		result[prop.NangCapRAM.index()] = LaptopCheck.NangCapRAM(lap.getNangCapRAM(), lap.getRAM());
		error = error ? true : !result[prop.NangCapRAM.index()].isEmpty();
		
		result[prop.GPU.index()] = LaptopCheck.GPU(lap.getGPU());
		error = error ? true : !result[prop.GPU.index()].isEmpty();
		
		result[prop.OCung.index()] = LaptopCheck.OCung(lap.getDungLuongOCung(), lap.getLoaiOCung());
		error = error ? true : !result[prop.OCung.index()].isEmpty();
		
		result[prop.ManHinh.index()] = LaptopCheck.ManHinh(lap.getManHinh());
		error = error ? true : !result[prop.ManHinh.index()].isEmpty();
		
		result[prop.TanSo.index()] = LaptopCheck.TanSo(lap.getTanSo());
		error = error ? true : !result[prop.TanSo.index()].isEmpty();
		
		result[prop.DoPhanGiai.index()] = LaptopCheck.DoPhanGiai(lap.getDoPhanGiai());
		error = error ? true : !result[prop.DoPhanGiai.index()].isEmpty();
		
		result[prop.CongNghe.index()] = LaptopCheck.CongNghe(lap.getCongNghe());
		error = error ? true : !result[prop.CongNghe.index()].isEmpty();
		
		result[prop.TamNen.index()] = LaptopCheck.TamNen(lap.getTamNen());
		error = error ? true : !result[prop.TamNen.index()].isEmpty();
		
		return error ? result : new String[0];
	}
	
	public static boolean insert(Laptop lap, Icon icon, String anh) {
		lap.setMaSP(taoMa(lap.getThuongHieu()));
		lap.setAnhSP("..\\img\\" + lap.getMaSP() + "_1" + anh.substring(anh.length()-4));
		if (!LaptopDAO.insert(lap)) return false;
		Anh.copy(anh, lap.getAnhSP());
		return true;
	}
	
	public static boolean update(Laptop lap, Icon icon, String anh) {
		if (!anh.isEmpty()) lap.setAnhSP("..\\img\\" + lap.getMaSP() + "_1" + anh.substring(anh.length()-4));
		if (!LaptopDAO.update(lap, anh)) return false;
		if (!anh.isEmpty()) Anh.copy(anh, lap.getAnhSP());
		return true;
	}
}
