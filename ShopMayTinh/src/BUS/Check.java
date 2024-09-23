package BUS;

interface Check {
	//temporary
	static boolean ngayThang(String chuoiNgay) {
		if (!chuoiNgay.matches("\\d{1,4}-(0\\d|1[0-2])-(0\\d|[1-3][0-1]|[1-2][2-9])")) return false;
		String[] mangNgay = chuoiNgay.split("-");
		int nam = Integer.parseInt(mangNgay[0]);
		int thang = Integer.parseInt(mangNgay[1]);
		int ngay = Integer.parseInt(mangNgay[2]);
		
		switch (thang) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12: return ngay <= 31;
			case 4: case 6: case 9: case 11: return ngay <= 30;
			default:
				if ((nam%4==0 && nam%100!=0) || (nam%400==0)) return ngay <= 29;
				return ngay <= 28;
		}
	}
	
	static boolean soCMND(String soCMND) {
		return soCMND.matches("\\d{9}|\\d{12}");
	}
	
	static boolean soDT(String soDT) {
		return soDT.matches("0\\d{9}|02\\d{9}");
	}
	
	static boolean email(String email) {
		return email.matches(".+@[a-zA-Z]+(\\.[a-zA-Z]+)+");
	}
}