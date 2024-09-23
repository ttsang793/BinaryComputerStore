package GUI;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.DonHangBUS;
import DTO.ChiTiet;
import DTO.DonHang;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class ChiTietDonHangGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField txtNgayLap;
	private JTextField txtKHMua;
	private JTextField txtKHNhan;
	private JTextField txtTongTien;
	private JTextField txtTienGop;
	
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnOK;
	
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Mã SP", "Tên sản phẩm", "Đơn giá", "Số lượng"});

	/**
	 * Create the panel.
	 */
	public ChiTietDonHangGUI(String maDH, String tongTien) {
		setTitle("Chi tiết đơn hàng " + maDH);
		setBounds(0,0,700,800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20,20,20,20));
		this.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {120, 0};
		gbl_contentPane.rowHeights = new int[] {50, 25, 25, 25, 25, 0, 25, 25, 30};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("CHI TIẾT ĐƠN HÀNG");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.NORTH;
		gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitle.insets = new Insets(0, 0, 25, 0);
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNgayLap = new GridBagConstraints();
		gbc_lblNgayLap.anchor = GridBagConstraints.NORTH;
		gbc_lblNgayLap.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNgayLap.insets = new Insets(0, 0, 25, 0);
		gbc_lblNgayLap.gridx = 0;
		gbc_lblNgayLap.gridy = 1;
		contentPane.add(lblNgayLap, gbc_lblNgayLap);
		
		txtNgayLap = new JTextField();
		txtNgayLap.setText("2024-02-15");
		txtNgayLap.setEnabled(false);
		txtNgayLap.setColumns(10);
		GridBagConstraints gbc_txtNgayLap = new GridBagConstraints();
		gbc_txtNgayLap.anchor = GridBagConstraints.NORTH;
		gbc_txtNgayLap.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNgayLap.insets = new Insets(0, 0, 25, 0);
		gbc_txtNgayLap.gridx = 1;
		gbc_txtNgayLap.gridy = 1;
		contentPane.add(txtNgayLap, gbc_txtNgayLap);
		
		JLabel lblKHMua = new JLabel("KH mua:");
		lblKHMua.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblKHMua = new GridBagConstraints();
		gbc_lblKHMua.anchor = GridBagConstraints.NORTH;
		gbc_lblKHMua.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKHMua.insets = new Insets(0, 0, 25, 0);
		gbc_lblKHMua.gridx = 0;
		gbc_lblKHMua.gridy = 2;
		contentPane.add(lblKHMua, gbc_lblKHMua);
		
		txtKHMua = new JTextField();
		txtKHMua.setEnabled(false);
		txtKHMua.setColumns(10);
		GridBagConstraints gbc_txtKHMua = new GridBagConstraints();
		gbc_txtKHMua.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtKHMua.anchor = GridBagConstraints.NORTH;
		gbc_txtKHMua.insets = new Insets(0, 0, 25, 0);
		gbc_txtKHMua.gridx = 1;
		gbc_txtKHMua.gridy = 2;
		contentPane.add(txtKHMua, gbc_txtKHMua);
		
		JLabel lblKHNhan = new JLabel("KH nhận:");
		lblKHNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblKHNhan = new GridBagConstraints();
		gbc_lblKHNhan.anchor = GridBagConstraints.NORTH;
		gbc_lblKHNhan.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKHNhan.insets = new Insets(0, 0, 25, 0);
		gbc_lblKHNhan.gridx = 0;
		gbc_lblKHNhan.gridy = 3;
		contentPane.add(lblKHNhan, gbc_lblKHNhan);
		
		txtKHNhan = new JTextField();
		txtKHNhan.setEnabled(false);
		txtKHNhan.setColumns(10);
		GridBagConstraints gbc_txtKHNhan = new GridBagConstraints();
		gbc_txtKHNhan.anchor = GridBagConstraints.NORTH;
		gbc_txtKHNhan.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtKHNhan.insets = new Insets(0, 0, 25, 0);
		gbc_txtKHNhan.gridx = 1;
		gbc_txtKHNhan.gridy = 3;
		contentPane.add(txtKHNhan, gbc_txtKHNhan);
		
		JLabel lblDanhSach = new JLabel("Danh sách sản phẩm:");
		lblDanhSach.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDanhSach = new GridBagConstraints();
		gbc_lblDanhSach.anchor = GridBagConstraints.NORTH;
		gbc_lblDanhSach.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDanhSach.gridwidth = 2;
		gbc_lblDanhSach.gridx = 0;
		gbc_lblDanhSach.gridy = 4;
		contentPane.add(lblDanhSach, gbc_lblDanhSach);
		
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTongTien = new GridBagConstraints();
		gbc_lblTongTien.anchor = GridBagConstraints.NORTH;
		gbc_lblTongTien.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTongTien.insets = new Insets(0, 0, 25, 0);
		gbc_lblTongTien.gridx = 0;
		gbc_lblTongTien.gridy = 6;
		contentPane.add(lblTongTien, gbc_lblTongTien);
		
		txtTongTien = new JTextField();
		txtTongTien.setEnabled(false);
		txtTongTien.setColumns(10);
		GridBagConstraints gbc_txtTongTien = new GridBagConstraints();
		gbc_txtTongTien.anchor = GridBagConstraints.NORTH;
		gbc_txtTongTien.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTongTien.insets = new Insets(0, 0, 25, 0);
		gbc_txtTongTien.gridx = 1;
		gbc_txtTongTien.gridy = 6;
		contentPane.add(txtTongTien, gbc_txtTongTien);
		
		JLabel lblTienGop = new JLabel("Góp hàng tháng:");
		lblTienGop.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTienGop = new GridBagConstraints();
		gbc_lblTienGop.anchor = GridBagConstraints.NORTH;
		gbc_lblTienGop.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTienGop.insets = new Insets(0, 0, 25, 0);
		gbc_lblTienGop.gridx = 0;
		gbc_lblTienGop.gridy = 7;
		contentPane.add(lblTienGop, gbc_lblTienGop);
		
		txtTienGop = new JTextField();
		txtTienGop.setEnabled(false);
		txtTienGop.setColumns(10);
		GridBagConstraints gbc_txtTienGop = new GridBagConstraints();
		gbc_txtTienGop.anchor = GridBagConstraints.NORTH;
		gbc_txtTienGop.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTienGop.insets = new Insets(0, 0, 25, 0);
		gbc_txtTienGop.gridx = 1;
		gbc_txtTienGop.gridy = 7;
		contentPane.add(txtTienGop, gbc_txtTienGop);
		
		btnOK = new JButton("OK");
		btnOK.setMinimumSize(new Dimension(120,30));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.fill = GridBagConstraints.VERTICAL;
		gbc_btnOK.gridwidth = 2;
		gbc_btnOK.gridx = 0;
		gbc_btnOK.gridy = 8;
		contentPane.add(btnOK, gbc_btnOK);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 25, 0);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);

		load(maDH, tongTien);
		table = new JTable(model);
		Redesign.Table(table);
		scrollPane.setViewportView(table);
		
		repainting();
	}
	
	private void load(String maDH, String tongTien) {
		DonHang donHang = DonHangBUS.getDanhSachDH(maDH);
		txtNgayLap.setText(donHang.getMaDH());
		txtKHMua.setText(donHang.getKHMua());
		txtKHNhan.setText(donHang.getKHNhan());
		txtTongTien.setText(tongTien);
		txtTienGop.setText(donHang.getGopHangThang());
		
		LinkedList<ChiTiet> chiTiet = DonHangBUS.getDanhSachCTDH(maDH);
		model.setRowCount(0);
		Object[] data = new Object[model.getColumnCount()];
		
		if (chiTiet != null)
			for (ChiTiet ct: chiTiet) {
				data[0] = ct.getMaSP(); data[1] = ct.getTenSP(); data[2] = ct.getDonGia(); data[3] = ct.getSoLuong();
				model.addRow(data);
			}
	}
	
	private void repainting() {
		contentPane.setBackground(ThemeColor.LIGHT_1);
		Redesign.TextField(txtNgayLap);
		Redesign.TextField(txtKHMua);
		Redesign.TextField(txtKHNhan);
		Redesign.TextField(txtTongTien);
		Redesign.TextField(txtTienGop);
		
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		Redesign.SubmitButton(btnOK);
	}
}
