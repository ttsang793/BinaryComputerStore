package BUS;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public interface Time {
	static String toString(Date date) {
		try {
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	static Date parseDate(String string) {
		try {
			return new Date(LocalDate.parse(string).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
		}
		catch (Exception e) {
			return null;
		}
	}
	
	static boolean isFuture(String string) {
		return parseDate(string).compareTo(new Date()) > 0;
	}
	
	@SuppressWarnings("deprecation")
	static boolean isBirthday(String string) {
		return (new Date().getMonth() == parseDate(string).getMonth()) && (new Date().getDate() == parseDate(string).getDate());
	}
	
	@SuppressWarnings("deprecation")
	static int getAge(String string) {
		return new Date().getYear() - parseDate(string).getYear();
	}
}