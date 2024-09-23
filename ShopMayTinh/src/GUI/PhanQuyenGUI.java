package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import java.util.LinkedList;
import DTO.PhanQuyen;
import BUS.PhanQuyenBUS;
import BUS.Time;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.jdesktop.swingx.JXDatePicker;

class PhanQuyenGUI extends JPanel implements BUS.Time {
	private LinkedList<PhanQuyen> PhanQuyen = new LinkedList<>();
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel(
		new Object[][] {}, new String[] {"Mã NV", "Tên NV", "Chức vụ", ""}
	);
	private JScrollPane scrollPane;
	private JPanel infoPanel;
	
	private JLabel lblMaNV;
	private JLabel lblHoTen;
	private JLabel lblChucVu;
	private JLabel lblNgayVaoLam;
	private JLabel lblNgaySinh;
	private JLabel lblQuanLy;
	
	private JButton btnLuu;
	private JButton btnReset;
	private JButton btnClear;
	
	private JPanel quyenPanel;
	private DefaultComboBoxModel<PhanQuyen> nvModel = new DefaultComboBoxModel<>(new PhanQuyen[] {new PhanQuyen("Chọn nhân viên")});
	

	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JComboBox<String> cbbChucVu;
	private JXDatePicker dtpNgayVaoLam;
	private JXDatePicker dtpNgaySinh;
	private JComboBox<PhanQuyen> cbbQuanLy;
	
	private JLabel lblErrorHoTen;
	private JLabel lblErrorChucVu;
	private JLabel lblErrorNgayVaoLam;
	private JLabel lblErrorNgaySinh;
	private JLabel lblErrorQuanLy;
	private LinkedList<JLabel> errorLabels = new LinkedList<>();
	private JPanel personPanel;
	private JScrollPane scrollPane_1;
	
	private JCheckBox chkChamSocKH;
	private JCheckBox chkDonHang;
	private JCheckBox chkNhapHang;
	private JCheckBox chkQuanLy;
	private JCheckBox chkAdmin;
	private LinkedList<JCheckBox> quyenChecks = new LinkedList<>();	

	/**
	 * Create the frame.
	 */
	public PhanQuyenGUI() {
		this.setBounds(0,0,1200,600);
		this.setBorder(new EmptyBorder(20, 0, 20, 0));
		this.setBackground(ThemeColor.LIGHT_1);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {580, 0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0};
		this.setLayout(gridBagLayout);
		
		infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_infoPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_infoPanel.insets = new Insets(0, 0, 5, 30);
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 0;
		this.add(infoPanel, gbc_infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] {120, 0, 120};
		gbl_infoPanel.rowHeights = new int[] {40, 0, 0, 30};
		gbl_infoPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_infoPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		lblTitle = new JLabel("THÔNG TIN VÀ PHÂN QUYỀN NHÂN VIÊN");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.NORTH;
		gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitle.insets = new Insets(0, 0, 10, 0);
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		infoPanel.add(lblTitle, gbc_lblTitle);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new EmptyBorder(0,0,0,0));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		infoPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		personPanel = new JPanel();
		scrollPane_1.setViewportView(personPanel);
		GridBagLayout gbl_personPanel = new GridBagLayout();
		gbl_personPanel.columnWidths = new int[] {100, 0};
		gbl_personPanel.rowHeights = new int[] {30, 25, 30, 25, 30, 25, 30, 25, 30, 25};
		gbl_personPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_personPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		personPanel.setLayout(gbl_personPanel);
		
		lblMaNV = new JLabel("Mã NV:");
		GridBagConstraints gbc_lblMaNV = new GridBagConstraints();
		gbc_lblMaNV.fill = GridBagConstraints.BOTH;
		gbc_lblMaNV.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaNV.gridx = 0;
		gbc_lblMaNV.gridy = 0;
		personPanel.add(lblMaNV, gbc_lblMaNV);
		lblMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtMaNV = new JTextField();
		GridBagConstraints gbc_txtMaNV = new GridBagConstraints();
		gbc_txtMaNV.anchor = GridBagConstraints.NORTH;
		gbc_txtMaNV.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMaNV.insets = new Insets(0, 0, 5, 0);
		gbc_txtMaNV.gridx = 1;
		gbc_txtMaNV.gridy = 0;
		personPanel.add(txtMaNV, gbc_txtMaNV);
		txtMaNV.setPreferredSize(new Dimension(txtMaNV.getWidth(), 25));
		txtMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtMaNV.setEnabled(false);
		txtMaNV.setColumns(10);
		
		lblHoTen = new JLabel("Họ tên: ");
		GridBagConstraints gbc_lblHoTen = new GridBagConstraints();
		gbc_lblHoTen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHoTen.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoTen.gridx = 0;
		gbc_lblHoTen.gridy = 2;
		personPanel.add(lblHoTen, gbc_lblHoTen);
		lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHoTen.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtHoTen = new JTextField();
		GridBagConstraints gbc_txtHoTen = new GridBagConstraints();
		gbc_txtHoTen.fill = GridBagConstraints.BOTH;
		gbc_txtHoTen.insets = new Insets(0, 0, 5, 0);
		gbc_txtHoTen.gridx = 1;
		gbc_txtHoTen.gridy = 2;
		personPanel.add(txtHoTen, gbc_txtHoTen);
		
		lblErrorHoTen = new JLabel("");
		GridBagConstraints gbc_lblErrorHoTen = new GridBagConstraints();
		gbc_lblErrorHoTen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorHoTen.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrorHoTen.gridx = 1;
		gbc_lblErrorHoTen.gridy = 3;
		personPanel.add(lblErrorHoTen, gbc_lblErrorHoTen);
		lblErrorHoTen.setForeground(Color.RED);
		lblErrorHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblChucVu = new JLabel("Chức vụ:");
		GridBagConstraints gbc_lblChucVu = new GridBagConstraints();
		gbc_lblChucVu.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblChucVu.insets = new Insets(0, 0, 5, 5);
		gbc_lblChucVu.gridx = 0;
		gbc_lblChucVu.gridy = 4;
		personPanel.add(lblChucVu, gbc_lblChucVu);
		lblChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbbChucVu = new JComboBox<>();
		cbbChucVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbbChucVu.getSelectedItem().toString().equals("Nhân viên")) {
					chkQuanLy.setSelected(false);
					chkAdmin.setSelected(false);
				}
				else if (cbbChucVu.getSelectedItem().toString().equals("Quản lý")) {
					chkQuanLy.setSelected(true);
					chkAdmin.setSelected(false);					
				}
				else if (cbbChucVu.getSelectedItem().toString().equals("Admin")) {
					clearCheck();
					chkAdmin.setSelected(true);
				}
			}
		});
		GridBagConstraints gbc_cbbChucVu = new GridBagConstraints();
		gbc_cbbChucVu.fill = GridBagConstraints.BOTH;
		gbc_cbbChucVu.insets = new Insets(0, 0, 5, 0);
		gbc_cbbChucVu.gridx = 1;
		gbc_cbbChucVu.gridy = 4;
		personPanel.add(cbbChucVu, gbc_cbbChucVu);
		cbbChucVu.setPreferredSize(new Dimension(cbbChucVu.getWidth(), 25));
		cbbChucVu.setBackground(new Color(255, 255, 255));
		cbbChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbChucVu.setModel(new DefaultComboBoxModel<>(new String[] {"Chọn chức vụ", "Nhân viên", "Quản lý", "Admin"}));
		cbbChucVu.setSelectedIndex(0);
		
		lblErrorChucVu = new JLabel("");
		GridBagConstraints gbc_lblErrorChucVu = new GridBagConstraints();
		gbc_lblErrorChucVu.fill = GridBagConstraints.BOTH;
		gbc_lblErrorChucVu.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrorChucVu.gridx = 1;
		gbc_lblErrorChucVu.gridy = 5;
		personPanel.add(lblErrorChucVu, gbc_lblErrorChucVu);
		lblErrorChucVu.setForeground(Color.RED);
		lblErrorChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblNgayVaoLam = new JLabel("Vào làm:");
		GridBagConstraints gbc_lblNgayVaoLam = new GridBagConstraints();
		gbc_lblNgayVaoLam.fill = GridBagConstraints.BOTH;
		gbc_lblNgayVaoLam.insets = new Insets(0, 0, 5, 5);
		gbc_lblNgayVaoLam.gridx = 0;
		gbc_lblNgayVaoLam.gridy = 6;
		personPanel.add(lblNgayVaoLam, gbc_lblNgayVaoLam);
		lblNgayVaoLam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNgayVaoLam.setHorizontalAlignment(SwingConstants.LEFT);
		
		dtpNgayVaoLam = new JXDatePicker();
		GridBagConstraints gbc_dtpNgayVaoLam = new GridBagConstraints();
		gbc_dtpNgayVaoLam.fill = GridBagConstraints.BOTH;
		gbc_dtpNgayVaoLam.insets = new Insets(0, 0, 5, 0);
		gbc_dtpNgayVaoLam.gridx = 1;
		gbc_dtpNgayVaoLam.gridy = 6;
		personPanel.add(dtpNgayVaoLam, gbc_dtpNgayVaoLam);
		Redesign.DatePicker(dtpNgayVaoLam);
		dtpNgayVaoLam.setPreferredSize(new Dimension(dtpNgayVaoLam.getWidth(), 25));
		dtpNgayVaoLam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblErrorNgayVaoLam = new JLabel("");
		GridBagConstraints gbc_lblErrorNgayVaoLam = new GridBagConstraints();
		gbc_lblErrorNgayVaoLam.fill = GridBagConstraints.BOTH;
		gbc_lblErrorNgayVaoLam.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrorNgayVaoLam.gridx = 1;
		gbc_lblErrorNgayVaoLam.gridy = 7;
		personPanel.add(lblErrorNgayVaoLam, gbc_lblErrorNgayVaoLam);
		lblErrorNgayVaoLam.setForeground(Color.RED);
		lblErrorNgayVaoLam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblNgaySinh = new JLabel("Ngày sinh:");
		GridBagConstraints gbc_lblNgaySinh = new GridBagConstraints();
		gbc_lblNgaySinh.fill = GridBagConstraints.BOTH;
		gbc_lblNgaySinh.insets = new Insets(0, 0, 5, 5);
		gbc_lblNgaySinh.gridx = 0;
		gbc_lblNgaySinh.gridy = 8;
		personPanel.add(lblNgaySinh, gbc_lblNgaySinh);
		lblNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		
		dtpNgaySinh = new JXDatePicker();
		GridBagConstraints gbc_dtpNgaySinh = new GridBagConstraints();
		gbc_dtpNgaySinh.fill = GridBagConstraints.BOTH;
		gbc_dtpNgaySinh.insets = new Insets(0, 0, 5, 0);
		gbc_dtpNgaySinh.gridx = 1;
		gbc_dtpNgaySinh.gridy = 8;
		personPanel.add(dtpNgaySinh, gbc_dtpNgaySinh);
		dtpNgaySinh.setPreferredSize(new Dimension(dtpNgaySinh.getWidth(), 25));
		dtpNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblQuanLy = new JLabel("Quản lý:");
		lblQuanLy.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuanLy.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblQuanLy = new GridBagConstraints();
		gbc_lblQuanLy.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuanLy.insets = new Insets(0, 0, 0, 5);
		gbc_lblQuanLy.gridx = 0;
		gbc_lblQuanLy.gridy = 10;
		personPanel.add(lblQuanLy, gbc_lblQuanLy);
		
		lblErrorNgaySinh = new JLabel("");
		GridBagConstraints gbc_lblErrorNgaySinh = new GridBagConstraints();
		gbc_lblErrorNgaySinh.fill = GridBagConstraints.BOTH;
		gbc_lblErrorNgaySinh.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrorNgaySinh.gridx = 1;
		gbc_lblErrorNgaySinh.gridy = 9;
		personPanel.add(lblErrorNgaySinh, gbc_lblErrorNgaySinh);
		lblErrorNgaySinh.setForeground(Color.RED);
		lblErrorNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		cbbQuanLy = new JComboBox<>(nvModel);
		cbbQuanLy.setBackground(new Color(255, 255, 255));
		cbbQuanLy.setPreferredSize(new Dimension(0, 25));
		cbbQuanLy.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_cbbQuanLy = new GridBagConstraints();
		gbc_cbbQuanLy.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbQuanLy.gridx = 1;
		gbc_cbbQuanLy.gridy = 10;
		personPanel.add(cbbQuanLy, gbc_cbbQuanLy);
		
		lblErrorQuanLy = new JLabel("");
		GridBagConstraints gbc_lblErrorQuanLy = new GridBagConstraints();
		gbc_lblErrorQuanLy.fill = GridBagConstraints.BOTH;
		gbc_lblErrorQuanLy.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrorQuanLy.gridx = 1;
		gbc_lblErrorQuanLy.gridy = 9;
		personPanel.add(lblErrorQuanLy, gbc_lblErrorQuanLy);
		lblErrorQuanLy.setForeground(Color.RED);
		lblErrorQuanLy.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		quyenPanel = new JPanel();
		Redesign.GroupBox(quyenPanel, " Chọn quyền: ");
		GridBagConstraints gbc_quyenPanel = new GridBagConstraints();
		gbc_quyenPanel.fill = GridBagConstraints.BOTH;
		gbc_quyenPanel.insets = new Insets(0, 0, 20, 0);
		gbc_quyenPanel.gridwidth = 3;
		gbc_quyenPanel.gridx = 0;
		gbc_quyenPanel.gridy = 2;
		infoPanel.add(quyenPanel, gbc_quyenPanel);
		GridBagLayout gbl_quyenPanel = new GridBagLayout();
		gbl_quyenPanel.columnWidths = new int[]{0, 0};
		gbl_quyenPanel.rowHeights = new int[]{23, 23, 23, 0};
		gbl_quyenPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_quyenPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		quyenPanel.setLayout(gbl_quyenPanel);
		
		chkQuanLy = new JCheckBox("Quản lý");
		chkQuanLy.setOpaque(false);
		chkQuanLy.setEnabled(false);
		chkQuanLy.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_chkQuanLy = new GridBagConstraints();
		gbc_chkQuanLy.fill = GridBagConstraints.BOTH;
		gbc_chkQuanLy.insets = new Insets(0, 0, 5, 5);
		gbc_chkQuanLy.gridx = 1;
		gbc_chkQuanLy.gridy = 1;
		quyenPanel.add(chkQuanLy, gbc_chkQuanLy);
		
		chkChamSocKH = new JCheckBox("Chăm sóc khách hàng");
		chkChamSocKH.setOpaque(false);
		chkChamSocKH.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_chkChamSocKH = new GridBagConstraints();
		gbc_chkChamSocKH.fill = GridBagConstraints.BOTH;
		gbc_chkChamSocKH.insets = new Insets(0, 0, 5, 0);
		gbc_chkChamSocKH.gridx = 0;
		gbc_chkChamSocKH.gridy = 0;
		quyenPanel.add(chkChamSocKH, gbc_chkChamSocKH);
		
		chkDonHang = new JCheckBox("Đơn hàng");
		chkDonHang.setOpaque(false);
		chkDonHang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_chkDonHang = new GridBagConstraints();
		gbc_chkDonHang.fill = GridBagConstraints.BOTH;
		gbc_chkDonHang.insets = new Insets(0, 0, 5, 5);
		gbc_chkDonHang.gridx = 1;
		gbc_chkDonHang.gridy = 0;
		quyenPanel.add(chkDonHang, gbc_chkDonHang);
		
		chkNhapHang = new JCheckBox("Nhập hàng");
		chkNhapHang.setOpaque(false);
		chkNhapHang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_chkNhapHang = new GridBagConstraints();
		gbc_chkNhapHang.anchor = GridBagConstraints.WEST;
		gbc_chkNhapHang.fill = GridBagConstraints.VERTICAL;
		gbc_chkNhapHang.insets = new Insets(0, 0, 5, 0);
		gbc_chkNhapHang.gridx = 0;
		gbc_chkNhapHang.gridy = 1;
		quyenPanel.add(chkNhapHang, gbc_chkNhapHang);
		
		chkAdmin = new JCheckBox("Admin");
		chkAdmin.setOpaque(false);
		chkAdmin.setEnabled(false);
		chkAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_chkAdmin = new GridBagConstraints();
		gbc_chkAdmin.fill = GridBagConstraints.BOTH;
		gbc_chkAdmin.insets = new Insets(0, 0, 0, 5);
		gbc_chkAdmin.gridx = 0;
		gbc_chkAdmin.gridy = 2;
		quyenPanel.add(chkAdmin, gbc_chkAdmin);
		
		btnLuu = new JButton("LƯU");
		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int quyen = 0;
				for (int i=quyenChecks.size() - 1; i>=0; i--)
					quyen = (quyen << 1) | (quyenChecks.get(i).isSelected() ? 1 : 0);
				PhanQuyen nv = new PhanQuyen(txtMaNV.getText(), txtHoTen.getText(), cbbChucVu.getItemAt(cbbChucVu.getSelectedIndex()),
						Time.toString(dtpNgayVaoLam.getDate()), Time.toString(dtpNgaySinh.getDate()), 
						cbbQuanLy.getItemAt(cbbQuanLy.getSelectedIndex()).getMaQL(), quyen);
				String[] checking = PhanQuyenBUS.kiemTra(nv);
				if (checking.length > 0) {
					for (int i=0; i<errorLabels.size(); i++) errorLabels.get(i).setText(checking[i]);
					return;
				}
				
				if (txtMaNV.getText().isEmpty()) {
					if (MessageBox.cauHoi("Bạn có muốn thêm nhân viên này") == JOptionPane.YES_OPTION) {
						if (PhanQuyenBUS.insert(nv)) {
							MessageBox.thanhCong("Thêm nhân viên thành công");
							PhanQuyenBUS.load(); load(); clear();
						}
						else MessageBox.loi("Đã có lỗi xảy ra, thêm nhân viên thất bại");
					}
				}
				else {
					if (PhanQuyenBUS.conMotAdmin(nv)) {
						MessageBox.loi("Hệ thống phải có 1 admin, không thể sửa");
						return;					
					}
					
					if (MessageBox.cauHoi("Bạn có muốn cập nhật nhân viên này?") == JOptionPane.YES_OPTION) {
						if (PhanQuyenBUS.update(nv)) {
							MessageBox.thanhCong("Cập nhật nhân viên thành công");
							PhanQuyenBUS.load(); load(); clear();
						}
						else MessageBox.loi("Đã có lỗi xảy ra, cập nhật nhân viên thất bại");
					}
				}
			}
		});
		GridBagConstraints gbc_btnLuu = new GridBagConstraints();
		gbc_btnLuu.fill = GridBagConstraints.BOTH;
		gbc_btnLuu.insets = new Insets(0, 0, 0, 5);
		gbc_btnLuu.gridx = 0;
		gbc_btnLuu.gridy = 3;
		infoPanel.add(btnLuu, gbc_btnLuu);
		
		btnReset = new JButton("ĐẶT LẠI MẬT KHẨU");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMaNV.getText().isEmpty())
					MessageBox.loi("Vui lòng chọn nhân viên");
				else {
					if (MessageBox.cauHoi("Bạn có muốn đặt lại mật khẩu cho nhân viên này?") == JOptionPane.YES_OPTION) {
						PhanQuyenBUS.resetPassword(txtMaNV.getText(), Time.toString(dtpNgayVaoLam.getDate()));
						MessageBox.thanhCong("Reset mật khẩu thành công");
						PhanQuyenBUS.load(); load(); clear();						
					}
				}
				
				
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill = GridBagConstraints.BOTH;
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 3;
		infoPanel.add(btnReset, gbc_btnReset);
		
		btnClear = new JButton("HỦY BỎ");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.fill = GridBagConstraints.BOTH;
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 3;
		infoPanel.add(btnClear, gbc_btnClear);		
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.gridheight = GridBagConstraints.REMAINDER;
		this.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model);
		load();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearError();
				PhanQuyen nv = search(model.getValueAt(table.getSelectedRow(), 0).toString());
				txtMaNV.setText(nv.getMaNV());
				txtHoTen.setText(nv.getTenNV());
				cbbChucVu.setSelectedItem(nv.getChucVu());
				dtpNgayVaoLam.setDate(Time.parseDate(nv.getNgayVaoLam()));
				dtpNgaySinh.setDate(Time.parseDate(nv.getNgaySinh()));
				
				check(nv.getQuyen());
				
				if (nv.getChucVu().equals("Admin") || nv.getTrangThai() == false) {
					cbbQuanLy.setSelectedIndex(0);
					btnReset.setEnabled(false);
					btnLuu.setEnabled(false);
				}
				else {
					cbbQuanLy.setSelectedItem(search(nv.getMaQL()));
					btnReset.setEnabled(true);
					btnLuu.setEnabled(true);
				}
			}
		});
		

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_KP_UP || e.getKeyCode() == KeyEvent.VK_KP_DOWN) {
				}
			}
		});
		initComponent();
		repainting();
	}
	
	private void load() {
		PhanQuyen = PhanQuyenBUS.getDanhSachNV();
		model.setRowCount(0);
		Object[] data = new Object[model.getColumnCount()];
		
		if (PhanQuyen != null)
			for (PhanQuyen nv: PhanQuyen) {
				data[0] = nv.getMaNV(); data[1] = nv.getTenNV(); data[2] = nv.getChucVu(); data[3] = nv.getTrangThai() ? 1 : 0;
				for (int i=0; i<model.getColumnCount(); i++) if (data[i] == null) data[i] = "";
				model.addRow(data);
			}

		Redesign.Table(table);
		loadQuanLy();
	}
	
	private void loadQuanLy() {
		for (PhanQuyen nv: PhanQuyenBUS.getDanhSachNV())
			if (nv.getChucVu().equals("Quản lý")) nvModel.addElement(nv);		
	}
	
	private void clear() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		cbbChucVu.setSelectedIndex(0);
		dtpNgayVaoLam.setDate(null);
		dtpNgaySinh.setDate(null);
		btnReset.setEnabled(true);
		btnLuu.setEnabled(true);
		clearCheck();
		clearError();
	}
	
	private void clearError() {
		for (JLabel label: errorLabels) label.setText("");
	}
	
	private PhanQuyen search(String maNV) {
		if (maNV == null) return new PhanQuyen();
		for (PhanQuyen nv: PhanQuyen) if (nv.getMaNV().equals(maNV)) return nv;
		return new PhanQuyen();
	}
	
	private void initComponent() {
		quyenChecks.add(chkChamSocKH);
		quyenChecks.add(chkDonHang);
		quyenChecks.add(chkNhapHang);
		quyenChecks.add(chkQuanLy);
		quyenChecks.add(chkAdmin);
		
		errorLabels.add(lblErrorHoTen);
		errorLabels.add(lblErrorChucVu);
		errorLabels.add(lblErrorNgayVaoLam);
		errorLabels.add(lblErrorNgaySinh);
	}
	
	private void check(int quyen) {
		clearCheck();
		int mask = 1; int i = 0;
		while (mask <= quyen) {
			quyenChecks.get(i).setSelected((quyen & mask) == mask);			
			mask <<= 1; i++;
		}
	}
	
	private void clearCheck() {
		for (JCheckBox check: quyenChecks) check.setSelected(false);		
	}
	
	void repainting() {
		this.setBackground(ThemeColor.LIGHT_1);
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		personPanel.setBackground(ThemeColor.LIGHT_1);
		Redesign.GroupBox(quyenPanel, " Chọn quyền: ");

		lblTitle.setForeground(ThemeColor.TEXT);
		lblMaNV.setForeground(ThemeColor.TEXT);
		lblHoTen.setForeground(ThemeColor.TEXT);
		lblChucVu.setForeground(ThemeColor.TEXT);
		lblNgayVaoLam.setForeground(ThemeColor.TEXT);
		lblNgaySinh.setForeground(ThemeColor.TEXT);
		lblQuanLy.setForeground(ThemeColor.TEXT);
		
		Redesign.TextField(txtMaNV);
		Redesign.TextField(txtHoTen);
		Redesign.ComboBox(cbbChucVu);
		Redesign.DatePicker(dtpNgayVaoLam);
		Redesign.DatePicker(dtpNgaySinh);
		Redesign.ComboBox(cbbQuanLy);
		
		chkChamSocKH.setForeground(ThemeColor.TEXT);
		chkDonHang.setForeground(ThemeColor.TEXT);
		chkNhapHang.setForeground(ThemeColor.TEXT);
		chkQuanLy.setForeground(ThemeColor.TEXT);
		chkAdmin.setForeground(ThemeColor.TEXT);
		
		Redesign.Table(table);

		Redesign.SubmitButton(btnLuu);
		Redesign.SubmitButton(btnReset);
		Redesign.SubmitButton(btnClear);
	}
}
