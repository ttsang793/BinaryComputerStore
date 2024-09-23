package GUI;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import BUS.ThongKeBUS;
import BUS.Time;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class ThongKeGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JXDatePicker dtpFrom;
	private JXDatePicker dtpTo;
	private JLabel lblXem;
	private JButton btnXem;
	
	private JPanel banChayPanel;
	private JLabel lblSanPham;
	private JPanel choosePanel;
	private JPanel resultPanel;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public ThongKeGUI() {
		setBounds(0,0,1200,600);
		setBorder(new EmptyBorder(20, 0, 20, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {25, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		setLayout(gridBagLayout);
		
		choosePanel = new JPanel();
		choosePanel.setOpaque(false);
		GridBagConstraints gbc_choosePanel = new GridBagConstraints();
		gbc_choosePanel.fill = GridBagConstraints.BOTH;
		gbc_choosePanel.insets = new Insets(0, 0, 5, 0);
		gbc_choosePanel.gridx = 0;
		gbc_choosePanel.gridy = 0;
		add(choosePanel, gbc_choosePanel);
		choosePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		
		lblXem = new JLabel("Chọn thời gian:");
		choosePanel.add(lblXem);
		lblXem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		dtpFrom = new JXDatePicker();
		choosePanel.add(dtpFrom);
		dtpFrom.setDate(new Date());
		
		dtpTo = new JXDatePicker();
		choosePanel.add(dtpTo);
		dtpTo.setDate(new Date());
		
		btnXem = new JButton("Tìm kiếm");
		btnXem.setPreferredSize(new Dimension(100,25));
		choosePanel.add(btnXem);
		
		resultPanel = new JPanel();
		resultPanel.setOpaque(false);
		GridBagConstraints gbc_resultPanel = new GridBagConstraints();
		gbc_resultPanel.fill = GridBagConstraints.BOTH;
		gbc_resultPanel.gridx = 0;
		gbc_resultPanel.gridy = 1;
		add(resultPanel, gbc_resultPanel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		resultPanel.add(scrollPane);
		
		banChayPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) banChayPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		scrollPane.setViewportView(banChayPanel);
		
		lblSanPham = new JLabel("");
		lblSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		banChayPanel.add(lblSanPham);
		resultPanel.setLayout(new GridLayout(2, 2, 10, 10));
		
		ChartPanel chartPanel_Laptop = new ChartPanel(null);
		chartPanel_Laptop.setOpaque(false);
		chartPanel_Laptop.setBorder(new LineBorder(ThemeColor.BORDER, 2));
		resultPanel.add(chartPanel_Laptop);
		
		ChartPanel chartPanel_ThuChi = new ChartPanel(null);
		chartPanel_ThuChi.setOpaque(false);
		chartPanel_ThuChi.setBorder(new LineBorder(ThemeColor.BORDER, 2));
		resultPanel.add(chartPanel_ThuChi);
		
		ChartPanel chartPanel_DoanhThu = new ChartPanel(null);
		chartPanel_DoanhThu.setOpaque(false);
		chartPanel_DoanhThu.setBorder(new LineBorder(ThemeColor.BORDER, 2));
		resultPanel.add(chartPanel_DoanhThu);

		
		btnXem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dtpFrom.getDate().compareTo(dtpTo.getDate()) >= 0) {
					MessageBox.loi("Vui lòng chọn ngày bên trái nhỏ hơn ngày bên phải");
					return;
				}
				
				String from = Time.toString(dtpFrom.getDate());
				String to = Time.toString(dtpTo.getDate());
				
				int yearFrom = Integer.parseInt(from.split("-")[0]);
				int monthFrom = Integer.parseInt(from.split("-")[1]);
				int yearTo = Integer.parseInt(to.split("-")[0]);
				int monthTo = Integer.parseInt(to.split("-")[1]);				

				HashMap<String, String> sanPham = ThongKeBUS.sanPhamBanChay(yearFrom, monthFrom, yearTo, monthTo);
				Set<String> sanPhamKey = sanPham.keySet();
				String sanPhamText = "<html>";
				for (String key: sanPhamKey) sanPhamText += key + " - " + sanPham.get(key) + "<br>";
				lblSanPham.setText(sanPhamText + "</html>");

				chartPanel_Laptop.setChart(ThongKeBUS.thuongHieuLaptop(yearFrom, monthFrom, yearTo, monthTo));
				
				JFreeChart[] doanhThuChart = ThongKeBUS.doanhThu(yearFrom, monthFrom, yearTo, monthTo);
				chartPanel_ThuChi.setChart(doanhThuChart[0]);
				chartPanel_DoanhThu.setChart(doanhThuChart[1]);
			}
		});
		repainting();
	}
    
	void repainting() {
		setBackground(ThemeColor.LIGHT_1);
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		
		Redesign.DatePicker(dtpFrom);
		dtpFrom.setFormats("yyyy-MM");
		Redesign.DatePicker(dtpTo);
		dtpTo.setFormats("yyyy-MM");
		lblXem.setForeground(ThemeColor.TEXT);
		Redesign.SubmitButton(btnXem);
		Redesign.GroupBox(banChayPanel, " Danh sách hàng bán chạy: ");
		lblSanPham.setForeground(ThemeColor.TEXT);
	}
}
