package imgCopyer;
import java.util.Date;

class Program {
	public static int tinhThang(Date date) {
		int nam = date.getYear();
		int thang = date.getMonth();
		int ngay = date.getDate();
		
		int namHT = new Date().getYear();
		int thangHT = new Date().getMonth();
		int ngayHT = new Date().getDate();
		
		int result = 0;
		
		if (thang == thangHT) result = 12*(nam - namHT);
		else if (thang > thangHT) result = 12*(nam - namHT) + thang - thangHT;
		else result = 12*(nam - namHT - 1) + thang + (12 - thangHT);
		
		if (ngay < ngayHT) result -= 1;
		
		return result;
	}
	
	public static void main(String[] args) {
		Date[] d = new Date[10];
		d[0] = new Date(124, 1, 29);
		for(Date i: d) System.out.println(tinhThang(i));
		/*
		DecimalFormat f = new DecimalFormat("###,###.##");
		String re = f.format(123456789.12);
		System.out.println(re);
		try {
			System.out.println(Double.parseDouble(re));
		}
		catch(Exception e) {
			System.out.println("Hello World");
		}
		*/
	}
}