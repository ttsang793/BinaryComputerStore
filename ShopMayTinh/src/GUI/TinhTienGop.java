package GUI;
import java.util.Date;

class TinhTienGop {
  private long tienGop;
  private Date ngayMuaHang;
  private int thang;

  public TinhTienGop(String tienGop, String ngayMuaHang, String thang) {
    this.tienGop = Long.parseLong(tienGop);
    this.ngayMuaHang = BUS.Time.parseDate(ngayMuaHang);
    this.thang = Integer.parseInt(thang);

    tinhThangTraMuon();
    tinhPhiTraMuon();
    tinhGiamGia();
  }

  private int soThangTraMuon;
  private long phiTraMuon;
  private long giamGia;
  @SuppressWarnings("deprecation")
  private void tinhThangTraMuon() {
	int nam = ngayMuaHang.getYear();
	int thang = ngayMuaHang.getMonth();
	int ngay = ngayMuaHang.getDate();
	
	int namHT = new Date().getYear();
	int thangHT = new Date().getMonth();
	int ngayHT = new Date().getDate();
	
	soThangTraMuon = 0;
	
	if (thangHT == thang) soThangTraMuon = 12*(namHT - nam);
	else if (thangHT > thang) soThangTraMuon = 12*(namHT - nam) + thangHT - thang;
	else soThangTraMuon = 12*(namHT - nam - 1) + thangHT + (12 - thang);

	if (ngayHT < ngay) --soThangTraMuon;
  }
  
  private void tinhPhiTraMuon() {
	  phiTraMuon = Math.round(tienGop * soThangTraMuon * 0.05 / 1000) * 1000;
  }
  
  private void tinhGiamGia() {
	  giamGia = (long)Math.floor(tienGop * thang * 0.015 / 1000) * 1000;
  }

  private long tinhTienGop() {    
    if (soThangTraMuon > 0) return tienGop * thang + phiTraMuon;
    if (thang >= 2) return tienGop * thang - giamGia;
    return tienGop;
  }

  public String getThangTraMuon() {
    return Integer.toString(soThangTraMuon);
  }
  
  public String getPhiTraMuon() {
	  return Long.toString(phiTraMuon);
  }

  public String getGiamGia() {
    return (thang >= 2 && soThangTraMuon == 0) ? Long.toString(giamGia) : Long.toString(0);
  }

  public String getTienGop() {
    return Long.toString(tinhTienGop());
  }
}