package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import BUS.DonHangBUS;
import DTO.DonHang;
import DTO.KhachHang;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

class LapDonHangGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel detailPanel;
	private JPanel muaHangPanel;
	private JPanel traGopPanel;
	private JScrollPane scrollPane;

	private JLabel lblTitle;
	private JLabel lblNgayLap;
	private JLabel lblKHMua;
	private JLabel lblKHNhan;
	private JRadioButton rdbSame;
	private JLabel lblTongTien;
	private JLabel lblThanhToan;
	private JLabel lblThangTraGop;
	private JLabel lblLaiSuat;
	private JLabel lblBaoHiem;
	private JLabel lblTienGop;
	private JLabel lblChenhLech;
	private JLabel lblTongGop;
	
	private JTextField txtNgayLap;
	private JTextField txtKHMua;
	private JTextField txtKHNhan;
	private JTextField txtTongTien;
	private JComboBox<String> cbbTruoc;
	private JTextField txtThanhToan;
	private JComboBox<Integer> cbbThangTraGop;
	private JTextField txtLaiSuat;
	private JTextField txtBaoHiem;
	private JTextField txtTienGop;
	private JTextField txtChenhLech;
	private JTextField txtTongGop;
	
	private JButton btnChonKHMua;
	private JButton btnLapKHMua;
	private JButton btnChonKHNhan;
	private JButton btnLapKHNhan;
	private KhachHang khMua = new KhachHang();
	private KhachHang khNhan = new KhachHang();
	
	private JButton btnLapDon;
	private JButton btnHuy;
	
	private ChonSP chonSP = new ChonSP(this);
	private TimKHGUI timKHMua = new TimKHGUI(this, 'M');
	private ThemKHGUI themKHMua = new ThemKHGUI(this, 'M');
	private TimKHGUI timKHNhan = new TimKHGUI(this, 'N');
	private ThemKHGUI themKHNhan = new ThemKHGUI(this, 'N');

	/**
	 * Create the panel.
	 */
	public LapDonHangGUI(String maNV) {
		setBounds(0,0,1200,600);
		setBorder(new EmptyBorder(20,0,20,0));
		setLayout(new GridLayout(0, 2, 30, 0));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		add(infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{100};
		gbl_infoPanel.rowHeights = new int[] {40, 0, 30};
		gbl_infoPanel.columnWeights = new double[]{1.0};
		gbl_infoPanel.rowWeights = new double[]{0.0, 1.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		lblTitle = new JLabel("LẬP ĐƠN HÀNG");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 20, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		infoPanel.add(lblTitle, gbc_lblTitle);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		infoPanel.add(scrollPane, gbc_scrollPane);
		
		detailPanel = new JPanel();
		detailPanel.setBorder(new EmptyBorder(0,0,0,10));
		scrollPane.setViewportView(detailPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		GridBagLayout gbl_detailPanel = new GridBagLayout();
		gbl_detailPanel.columnWidths = new int[] {120, 100, 0};
		gbl_detailPanel.rowHeights = new int[] {0, 0, 0, 0, 25, 25, 0};
		gbl_detailPanel.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_detailPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		detailPanel.setLayout(gbl_detailPanel);
		
		muaHangPanel = new JPanel();
		GridBagConstraints gbc_muaHangPanel = new GridBagConstraints();
		gbc_muaHangPanel.insets = new Insets(0, 0, 20, 0);
		gbc_muaHangPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_muaHangPanel.gridwidth = 3;
		gbc_muaHangPanel.gridx = 0;
		gbc_muaHangPanel.gridy = 0;
		detailPanel.add(muaHangPanel, gbc_muaHangPanel);
		muaHangPanel.setOpaque(false);
		GridBagLayout gbl_muaHangPanel = new GridBagLayout();
		gbl_muaHangPanel.columnWidths = new int[]{80, 20, 0, 100, 100, 0};
		gbl_muaHangPanel.rowHeights = new int[] {25, 20, 20, 20};
		gbl_muaHangPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_muaHangPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		muaHangPanel.setLayout(gbl_muaHangPanel);
		
		lblNgayLap = new JLabel("Ngày lập:");
		GridBagConstraints gbc_lblNgayLap = new GridBagConstraints();
		gbc_lblNgayLap.fill = GridBagConstraints.BOTH;
		gbc_lblNgayLap.insets = new Insets(0, 0, 20, 0);
		gbc_lblNgayLap.gridx = 0;
		gbc_lblNgayLap.gridy = 0;
		muaHangPanel.add(lblNgayLap, gbc_lblNgayLap);
		lblNgayLap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtNgayLap = new JTextField();
		txtNgayLap.setEnabled(false);
		txtNgayLap.setText(java.time.LocalDate.now().toString());
		GridBagConstraints gbc_txtNgayLap = new GridBagConstraints();
		gbc_txtNgayLap.fill = GridBagConstraints.BOTH;
		gbc_txtNgayLap.insets = new Insets(0, 0, 20, 0);
		gbc_txtNgayLap.gridwidth = 4;
		gbc_txtNgayLap.gridx = 1;
		gbc_txtNgayLap.gridy = 0;
		muaHangPanel.add(txtNgayLap, gbc_txtNgayLap);
		txtNgayLap.setColumns(10);
		
		lblKHMua = new JLabel("KH mua:");
		GridBagConstraints gbc_lblKHMua = new GridBagConstraints();
		gbc_lblKHMua.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKHMua.insets = new Insets(0, 0, 20, 0);
		gbc_lblKHMua.gridx = 0;
		gbc_lblKHMua.gridy = 1;
		muaHangPanel.add(lblKHMua, gbc_lblKHMua);
		lblKHMua.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtKHMua = new JTextField();
		txtKHMua.setEnabled(false);
		GridBagConstraints gbc_txtKHMua = new GridBagConstraints();
		gbc_txtKHMua.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtKHMua.insets = new Insets(0, 0, 20, 5);
		gbc_txtKHMua.gridwidth = 2;
		gbc_txtKHMua.gridx = 1;
		gbc_txtKHMua.gridy = 1;
		muaHangPanel.add(txtKHMua, gbc_txtKHMua);
		txtKHMua.setColumns(10);
		
		btnChonKHMua = new JButton("Chọn khách cũ");
		btnChonKHMua.setMinimumSize(new Dimension(0, 25));
		btnChonKHMua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKHMua.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnChonKHMua = new GridBagConstraints();
		gbc_btnChonKHMua.fill = GridBagConstraints.BOTH;
		gbc_btnChonKHMua.insets = new Insets(0, 0, 20, 5);
		gbc_btnChonKHMua.gridx = 3;
		gbc_btnChonKHMua.gridy = 1;
		muaHangPanel.add(btnChonKHMua, gbc_btnChonKHMua);
		btnChonKHMua.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnChonKHMua.setBackground(Color.WHITE);
		
		btnLapKHMua = new JButton("Lập khách mới");
		GridBagConstraints gbc_btnLapKHMua = new GridBagConstraints();
		gbc_btnLapKHMua.insets = new Insets(0, 0, 20, 0);
		gbc_btnLapKHMua.fill = GridBagConstraints.BOTH;
		gbc_btnLapKHMua.gridx = 4;
		gbc_btnLapKHMua.gridy = 1;
		muaHangPanel.add(btnLapKHMua, gbc_btnLapKHMua);
		btnLapKHMua.setBackground(new Color(255, 255, 255));
		btnLapKHMua.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnLapKHMua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themKHMua.setVisible(true);
			}
		});
		
		lblKHNhan = new JLabel("KH nhận:");
		GridBagConstraints gbc_lblKHNhan = new GridBagConstraints();
		gbc_lblKHNhan.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKHNhan.gridx = 0;
		gbc_lblKHNhan.gridy = 2;
		muaHangPanel.add(lblKHNhan, gbc_lblKHNhan);
		lblKHNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		rdbSame = new JRadioButton("Trùng với khách hàng mua");
		rdbSame.setOpaque(false);
		GridBagConstraints gbc_rdbSame = new GridBagConstraints();
		gbc_rdbSame.fill = GridBagConstraints.BOTH;
		gbc_rdbSame.gridwidth = 4;
		gbc_rdbSame.gridx = 1;
		gbc_rdbSame.gridy = 2;
		muaHangPanel.add(rdbSame, gbc_rdbSame);
		rdbSame.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbSame.setSelected(true);
		
		JRadioButton rdbDiff = new JRadioButton("");
		rdbDiff.setOpaque(false);
		GridBagConstraints gbc_rdbDiff = new GridBagConstraints();
		gbc_rdbDiff.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbDiff.gridx = 1;
		gbc_rdbDiff.gridy = 3;
		muaHangPanel.add(rdbDiff, gbc_rdbDiff);
		rdbDiff.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbDiff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbSame.setSelected(false);
				rdbDiff.setSelected(true);
				btnChonKHNhan.setEnabled(true);
				btnLapKHNhan.setEnabled(true);
			}
		});
		
		rdbSame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbSame.setSelected(true);
				rdbDiff.setSelected(false);
				btnChonKHNhan.setEnabled(false);
				btnLapKHNhan.setEnabled(false);
			}
		});
		
		txtKHNhan = new JTextField();
		txtKHNhan.setEnabled(false);
		GridBagConstraints gbc_txtKHNhan = new GridBagConstraints();
		gbc_txtKHNhan.insets = new Insets(0, 0, 0, 5);
		gbc_txtKHNhan.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtKHNhan.gridx = 2;
		gbc_txtKHNhan.gridy = 3;
		muaHangPanel.add(txtKHNhan, gbc_txtKHNhan);
		txtKHNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtKHNhan.setColumns(10);
		
		btnChonKHNhan = new JButton("Chọn khách cũ");
		GridBagConstraints gbc_btnChonKHNhan = new GridBagConstraints();
		gbc_btnChonKHNhan.fill = GridBagConstraints.BOTH;
		gbc_btnChonKHNhan.insets = new Insets(0, 0, 0, 5);
		gbc_btnChonKHNhan.gridx = 3;
		gbc_btnChonKHNhan.gridy = 3;
		muaHangPanel.add(btnChonKHNhan, gbc_btnChonKHNhan);
		btnChonKHNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnChonKHNhan.setEnabled(false);
		btnChonKHNhan.setBackground(Color.WHITE);
		btnChonKHNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKHNhan.setVisible(true);
			}
		});
		
		btnLapKHNhan = new JButton("Lập khách mới");
		GridBagConstraints gbc_btnLapKHNhan = new GridBagConstraints();
		gbc_btnLapKHNhan.fill = GridBagConstraints.BOTH;
		gbc_btnLapKHNhan.gridx = 4;
		gbc_btnLapKHNhan.gridy = 3;
		muaHangPanel.add(btnLapKHNhan, gbc_btnLapKHNhan);
		btnLapKHNhan.setBackground(new Color(255, 255, 255));
		btnLapKHNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnLapKHNhan.setEnabled(false);
		btnLapKHNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themKHNhan.setVisible(true);
			}
		});
		
		lblTongTien = new JLabel("Tổng tiền:");
		GridBagConstraints gbc_lblTongTien = new GridBagConstraints();
		gbc_lblTongTien.anchor = GridBagConstraints.WEST;
		gbc_lblTongTien.insets = new Insets(0, 0, 20, 0);
		gbc_lblTongTien.gridx = 0;
		gbc_lblTongTien.gridy = 1;
		detailPanel.add(lblTongTien, gbc_lblTongTien);
		lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		txtTongTien = new JTextField();
		GridBagConstraints gbc_txtTongTien = new GridBagConstraints();
		gbc_txtTongTien.gridwidth = 2;
		gbc_txtTongTien.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTongTien.insets = new Insets(0, 0, 20, 0);
		gbc_txtTongTien.gridx = 1;
		gbc_txtTongTien.gridy = 1;
		detailPanel.add(txtTongTien, gbc_txtTongTien);
		txtTongTien.setEnabled(false);
		txtTongTien.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtTongTien.setColumns(10);
		
		lblThanhToan = new JLabel("Thanh toán:");
		GridBagConstraints gbc_lblThanhToan = new GridBagConstraints();
		gbc_lblThanhToan.anchor = GridBagConstraints.WEST;
		gbc_lblThanhToan.insets = new Insets(0, 0, 20, 5);
		gbc_lblThanhToan.gridx = 0;
		gbc_lblThanhToan.gridy = 2;
		detailPanel.add(lblThanhToan, gbc_lblThanhToan);
		lblThanhToan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		cbbTruoc = new JComboBox<>();
		GridBagConstraints gbc_cbbTruoc = new GridBagConstraints();
		gbc_cbbTruoc.fill = GridBagConstraints.BOTH;
		gbc_cbbTruoc.insets = new Insets(0, 0, 20, 5);
		gbc_cbbTruoc.gridx = 1;
		gbc_cbbTruoc.gridy = 2;
		detailPanel.add(cbbTruoc, gbc_cbbTruoc);
		cbbTruoc.setModel(new DefaultComboBoxModel<>(new String[] {"0%", "10%", "20%", "30%", "40%", "50%", "60%", "100%"}));
		cbbTruoc.setSelectedItem("100%");
		
				cbbTruoc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tinhTien();
					}
				});		
		
		txtThanhToan = new JTextField();
		GridBagConstraints gbc_txtThanhToan = new GridBagConstraints();
		gbc_txtThanhToan.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtThanhToan.insets = new Insets(0, 0, 20, 0);
		gbc_txtThanhToan.gridx = 2;
		gbc_txtThanhToan.gridy = 2;
		detailPanel.add(txtThanhToan, gbc_txtThanhToan);
		txtThanhToan.setEnabled(false);
		txtThanhToan.setColumns(10);
		
		traGopPanel = new JPanel();
		GridBagConstraints gbc_traGopPanel = new GridBagConstraints();
		gbc_traGopPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_traGopPanel.gridwidth = 3;
		gbc_traGopPanel.anchor = GridBagConstraints.NORTH;
		gbc_traGopPanel.gridx = 0;
		gbc_traGopPanel.gridy = 3;
		detailPanel.add(traGopPanel, gbc_traGopPanel);
		traGopPanel.setOpaque(false);
		GridBagLayout gbl_traGopPanel = new GridBagLayout();
		gbl_traGopPanel.columnWidths = new int[] {80, 90, 100, 0};
		gbl_traGopPanel.rowHeights = new int[] {25, 25, 25, 25, 25};
		gbl_traGopPanel.columnWeights = new double[]{0.0, 0.1, 0.0, 1.0};
		gbl_traGopPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		traGopPanel.setLayout(gbl_traGopPanel);
		
		lblThangTraGop = new JLabel("Tháng:");
		GridBagConstraints gbc_lblThangTraGop = new GridBagConstraints();
		gbc_lblThangTraGop.fill = GridBagConstraints.BOTH;
		gbc_lblThangTraGop.insets = new Insets(0, 0, 20, 0);
		gbc_lblThangTraGop.gridx = 0;
		gbc_lblThangTraGop.gridy = 0;
		traGopPanel.add(lblThangTraGop, gbc_lblThangTraGop);
		lblThangTraGop.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		cbbThangTraGop = new JComboBox<>();
		cbbThangTraGop.setModel(new DefaultComboBoxModel<>(new Integer[] {0, 6, 9, 12, 15, 18, 24}));
		GridBagConstraints gbc_cbbThangTraGop = new GridBagConstraints();
		gbc_cbbThangTraGop.fill = GridBagConstraints.BOTH;
		gbc_cbbThangTraGop.insets = new Insets(0, 0, 20, 5);
		gbc_cbbThangTraGop.gridx = 1;
		gbc_cbbThangTraGop.gridy = 0;
		traGopPanel.add(cbbThangTraGop, gbc_cbbThangTraGop);
		
		lblLaiSuat = new JLabel("Lãi suất (%):");
		GridBagConstraints gbc_lblLaiSuat = new GridBagConstraints();
		gbc_lblLaiSuat.fill = GridBagConstraints.BOTH;
		gbc_lblLaiSuat.insets = new Insets(0, 0, 20, 0);
		gbc_lblLaiSuat.gridx = 2;
		gbc_lblLaiSuat.gridy = 0;
		traGopPanel.add(lblLaiSuat, gbc_lblLaiSuat);
		lblLaiSuat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtLaiSuat = new JTextField();
		txtLaiSuat.setEnabled(false);
		GridBagConstraints gbc_txtLaiSuat = new GridBagConstraints();
		gbc_txtLaiSuat.fill = GridBagConstraints.BOTH;
		gbc_txtLaiSuat.insets = new Insets(0, 0, 20, 0);
		gbc_txtLaiSuat.gridx = 3;
		gbc_txtLaiSuat.gridy = 0;
		traGopPanel.add(txtLaiSuat, gbc_txtLaiSuat);
		txtLaiSuat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtLaiSuat.setColumns(10);
		
		lblBaoHiem = new JLabel("Bảo hiểm:");
		GridBagConstraints gbc_lblBaoHiem = new GridBagConstraints();
		gbc_lblBaoHiem.fill = GridBagConstraints.BOTH;
		gbc_lblBaoHiem.insets = new Insets(0, 0, 20, 0);
		gbc_lblBaoHiem.gridx = 0;
		gbc_lblBaoHiem.gridy = 1;
		traGopPanel.add(lblBaoHiem, gbc_lblBaoHiem);
		lblBaoHiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtBaoHiem = new JTextField();
		txtBaoHiem.setEnabled(false);
		GridBagConstraints gbc_txtBaoHiem = new GridBagConstraints();
		gbc_txtBaoHiem.gridwidth = 3;
		gbc_txtBaoHiem.fill = GridBagConstraints.BOTH;
		gbc_txtBaoHiem.insets = new Insets(0, 0, 20, 0);
		gbc_txtBaoHiem.gridx = 1;
		gbc_txtBaoHiem.gridy = 1;
		traGopPanel.add(txtBaoHiem, gbc_txtBaoHiem);
		txtBaoHiem.setColumns(10);
		
		lblTienGop = new JLabel("Góp hàng tháng:");
		GridBagConstraints gbc_lblTienGop = new GridBagConstraints();
		gbc_lblTienGop.gridwidth = 2;
		gbc_lblTienGop.fill = GridBagConstraints.BOTH;
		gbc_lblTienGop.insets = new Insets(0, 0, 20, 0);
		gbc_lblTienGop.gridx = 0;
		gbc_lblTienGop.gridy = 2;
		traGopPanel.add(lblTienGop, gbc_lblTienGop);
		lblTienGop.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtTienGop = new JTextField();
		txtTienGop.setEnabled(false);
		GridBagConstraints gbc_txtTienGop = new GridBagConstraints();
		gbc_txtTienGop.gridwidth = 2;
		gbc_txtTienGop.fill = GridBagConstraints.BOTH;
		gbc_txtTienGop.insets = new Insets(0, 0, 20, 0);
		gbc_txtTienGop.gridx = 2;
		gbc_txtTienGop.gridy = 2;
		traGopPanel.add(txtTienGop, gbc_txtTienGop);
		txtTienGop.setColumns(10);
		
		lblChenhLech = new JLabel("Chênh lệch so với trả thẳng:");
		lblChenhLech.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblChenhLech = new GridBagConstraints();
		gbc_lblChenhLech.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblChenhLech.gridwidth = 2;
		gbc_lblChenhLech.insets = new Insets(0, 0, 20, 0);
		gbc_lblChenhLech.gridx = 0;
		gbc_lblChenhLech.gridy = 3;
		traGopPanel.add(lblChenhLech, gbc_lblChenhLech);
		
		txtChenhLech = new JTextField();
		txtChenhLech.setEnabled(false);
		txtChenhLech.setColumns(10);
		GridBagConstraints gbc_txtChenhLech = new GridBagConstraints();
		gbc_txtChenhLech.insets = new Insets(0, 0, 20, 0);
		gbc_txtChenhLech.gridwidth = 2;
		gbc_txtChenhLech.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtChenhLech.gridx = 2;
		gbc_txtChenhLech.gridy = 3;
		traGopPanel.add(txtChenhLech, gbc_txtChenhLech);
		
		lblTongGop = new JLabel("TỔNG TRẢ GÓP:");
		lblTongGop.setFont(new Font("Segoe UI", Font.BOLD, 14));
		GridBagConstraints gbc_lblTongGop = new GridBagConstraints();
		gbc_lblTongGop.fill = GridBagConstraints.BOTH;
		gbc_lblTongGop.gridwidth = 2;
		gbc_lblTongGop.insets = new Insets(0, 0, 0, 5);
		gbc_lblTongGop.gridx = 0;
		gbc_lblTongGop.gridy = 4;
		traGopPanel.add(lblTongGop, gbc_lblTongGop);
		
		txtTongGop = new JTextField();
		txtTongGop.setEnabled(false);
		txtTongGop.setColumns(10);
		GridBagConstraints gbc_txtTongGop = new GridBagConstraints();
		gbc_txtTongGop.gridwidth = 2;
		gbc_txtTongGop.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTongGop.gridx = 2;
		gbc_txtTongGop.gridy = 4;
		traGopPanel.add(txtTongGop, gbc_txtTongGop);
		
				cbbThangTraGop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tinhTien();
					}
				});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 2;
		infoPanel.add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		btnLapDon = new JButton("LẬP ĐƠN");
		btnLapDon.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnLapDon);
		btnLapDon.setBackground(new Color(255, 255, 255));
		btnLapDon.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		btnHuy = new JButton("HỦY BỎ");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnHuy.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnHuy);
		btnHuy.setBackground(new Color(255, 255, 255));
		btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLapDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonHang dh = new DonHang("", txtNgayLap.getText(), "", "", maNV, cbbThangTraGop.getSelectedItem().toString(), txtTienGop.getText());
										
				if (txtTongTien.getText().isEmpty()) {
					MessageBox.loi("Vui lòng chọn ít nhất 1 sản phẩm");
					return;
				}
				if (txtKHMua.getText().isEmpty()) {
					MessageBox.loi("Vui lòng cho biết khách hàng mua");
					return;
				}
				if (rdbDiff.isSelected() && txtKHNhan.getText().isEmpty()) {
					MessageBox.loi("Vui lòng cho biết khách hàng nhận");
					return;
				}
				
				if (rdbSame.isSelected()) khNhan = new KhachHang();
				if (DonHangBUS.insert(dh, chonSP.getChiTiet(), khMua, khNhan, txtThanhToan.getText())) {
					MessageBox.thanhCong("Lập hóa đơn thành công");
					clear();
					resetSubform();
				}
				else MessageBox.loi("Đã có lỗi xảy ra, lập đơn hàng thất bại");			
			}
		});
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(240, 240, 240));
		desktopPane.setLayout(new CardLayout(0, 0));
		desktopPane.add(chonSP, "name_259965710166400");
		
		add(desktopPane);
		
		repainting();
	}
	
	private void clear() {
		txtKHMua.setText("");
		txtKHNhan.setText("");
		txtTongTien.setText("");
		
		clearThanhToan();
		resetSubform();
	}
	
	private void clearThanhToan() {
		txtThanhToan.setText("");
		txtLaiSuat.setText("");
		txtBaoHiem.setText("");
		txtTienGop.setText("");
		txtChenhLech.setText("");
		txtTongGop.setText("");
		
		cbbTruoc.setSelectedItem("100%");
		cbbThangTraGop.setSelectedIndex(0);
	}
	
	private void resetSubform() {
		chonSP = new ChonSP(this);
		themKHMua = new ThemKHGUI(this, 'M');
		themKHNhan = new ThemKHGUI(this, 'N');
	}
	
	void setTongTien(String tongTien) {
		txtTongTien.setText(tongTien);
		tinhTien();
	}
	
	void setKHMua(KhachHang kh) {
		khMua = kh;
		txtKHMua.setText(kh.getCMND() + " - " + kh.getTenKH());
	}
	
	void setKHNhan(KhachHang kh) {
		khNhan = kh;
		txtKHNhan.setText(kh.getCMND() + " - " + kh.getTenKH());
	}
	
	private void tinhTien() {
		if (cbbTruoc.getSelectedItem().toString().equals("100%"))
			cbbThangTraGop.setSelectedItem(0);
		else if (cbbThangTraGop.getSelectedIndex() == 0) cbbThangTraGop.setSelectedIndex(1);

		if (txtTongTien.getText().isEmpty()) {
			clearThanhToan();
			return;
		}
		
		TinhTien t = new TinhTien(txtTongTien.getText(), cbbTruoc.getSelectedItem().toString(), cbbThangTraGop.getSelectedItem().toString());
		txtThanhToan.setText(t.getTraTruoc());
		txtLaiSuat.setText(t.getLaiSuat());
		txtBaoHiem.setText(t.getBaoHiem());
		txtTienGop.setText(t.getTienTraHangThang());
		txtChenhLech.setText(t.getChenhLech());
		txtTongGop.setText(t.getTongGop());
	}
	
	void repainting() {
		lblTitle.setForeground(ThemeColor.TEXT);
		setBackground(ThemeColor.LIGHT_1);
		scrollPane.getVerticalScrollBar().setBackground(ThemeColor.LIGHT_1);
		detailPanel.setBackground(ThemeColor.LIGHT_1);
		
		Redesign.GroupBox(muaHangPanel, " Thông tin mua hàng: ");
		Redesign.TextField(txtNgayLap);
		Redesign.TextField(txtKHMua);
		Redesign.TextField(txtKHNhan);
		Redesign.TextField(txtTongTien);
		Redesign.ComboBox(cbbTruoc);
		Redesign.TextField(txtThanhToan);
		Redesign.ComboBox(cbbThangTraGop);
		Redesign.TextField(txtLaiSuat);
		lblNgayLap.setForeground(ThemeColor.TEXT);
		lblKHMua.setForeground(ThemeColor.TEXT);
		lblKHNhan.setForeground(ThemeColor.TEXT);
		rdbSame.setForeground(ThemeColor.TEXT);
		lblTongTien.setForeground(ThemeColor.TEXT);
		lblThanhToan.setForeground(ThemeColor.TEXT);
		lblThangTraGop.setForeground(ThemeColor.TEXT);
		lblLaiSuat.setForeground(ThemeColor.TEXT);
		

		Redesign.GroupBox(traGopPanel, " Trả góp: ");
		Redesign.TextField(txtBaoHiem);
		Redesign.TextField(txtTienGop);
		Redesign.TextField(txtChenhLech);
		Redesign.TextField(txtTongGop);		
		lblBaoHiem.setForeground(ThemeColor.TEXT);
		lblTienGop.setForeground(ThemeColor.TEXT);
		lblChenhLech.setForeground(ThemeColor.TEXT);
		lblTongGop.setForeground(ThemeColor.TEXT);
		
		Redesign.Button(btnChonKHMua);
		Redesign.Button(btnLapKHMua);
		Redesign.Button(btnChonKHNhan);
		Redesign.Button(btnLapKHNhan);
		
		Redesign.SubmitButton(btnLapDon);
		Redesign.SubmitButton(btnHuy);
		
		txtTongGop.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		chonSP.repainting();
	}
}
