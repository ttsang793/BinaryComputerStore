package BUS;
import java.io.*;

abstract class Anh {
	static boolean copy(String nguon, String dich) {
		FileInputStream input = null; FileOutputStream output = null;
		try {
			File fileNguon = new File(nguon);
			File fileDich = new File(dich);
			input = new FileInputStream(fileNguon);
			output = new FileOutputStream(fileDich);
			byte[] buffer = new byte[8192]; //để chất lượng ảnh 98%
			int length;
			while ((length = input.read(buffer)) > 0)
				output.write(buffer, 0, length);
			input.close(); output.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}