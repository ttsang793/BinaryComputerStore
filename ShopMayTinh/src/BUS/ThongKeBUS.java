package BUS;
import java.awt.Color;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;

import DAO.ThongKeDAO;

public abstract class ThongKeBUS {
	public static JFreeChart thuongHieuLaptop(int yearFrom, int monthFrom, int yearTo, int monthTo) {
		HashMap<String, Integer> result = ThongKeDAO.thuongHieuLaptop(yearFrom, monthFrom, yearTo, monthTo);
		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
		JFreeChart bieuDo = ChartFactory.createPieChart("THỊ PHẦN LAPTOP", dataset, false, true, false);
		
		
		Set<String> doanhThuKey = result.keySet();
		for (String key: doanhThuKey) {
			dataset.setValue(key, result.get(key));
			//plot.setSectionPaint(key, new Color(31, 73, 125)); TO MAU CHO BIEU DO TRON
		}
		return bieuDo;
	}
	
	public static HashMap<String, String> sanPhamBanChay(int yearFrom, int monthFrom, int yearTo, int monthTo) {
		return ThongKeDAO.sanPhamBanChay(yearFrom, monthFrom, yearTo, monthTo);
	}
	
	public static JFreeChart[] doanhThu(int yearFrom, int monthFrom, int yearTo, int monthTo) {
		HashMap<String, long[]> doanhThu = ThongKeDAO.doanhThu(yearFrom, monthFrom, yearTo, monthTo);
		
		DefaultCategoryDataset[] dataset = new DefaultCategoryDataset[2];
		for (int i=0; i<2; i++) dataset[i] = new DefaultCategoryDataset();
		
		JFreeChart[] bieuDo = new JFreeChart[2];		
		bieuDo[0] = ChartFactory.createBarChart("THU CHI", "Thời gian", "VND", dataset[0], PlotOrientation.VERTICAL, true, true, false);
		bieuDo[1] = ChartFactory.createLineChart("LỢI NHUẬN", "Thời gian", "VND", dataset[1], PlotOrientation.VERTICAL, false, true, false);
		
		dataset[0].clear();
		dataset[1].clear();
		Set<String> doanhThuKey = doanhThu.keySet();
		for (String key: doanhThuKey) {
			long[] tien = doanhThu.get(key);
			dataset[0].addValue(tien[0], "Đơn hàng", key);
			dataset[0].addValue(tien[1], "Nhập hàng", key);
			dataset[1].addValue(tien[2], "Doanh thu", key);
		}
		
		return bieuDo;
	}
}
