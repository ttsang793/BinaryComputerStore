package GUI;

import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.NhanVienBUS;
import BUS.Time;
import DTO.NhanVien;
import DTO.PhanQuyen;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Dimension;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class NhanVienGUI extends JPanel {
	private LinkedList<NhanVien> nhanVien = new LinkedList<>();
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {}, new String[] {"Mã NV", "Tên NV", "Ngày vào làm", "Ngày sinh", ""}
		);
	private JTable table;
	
	private JLabel lblTitle;
	private JLabel lblMaNV;
	private JLabel lblHoTen;
	private JLabel lblNgayVaoLam;
	private JLabel lblNgaySinh;
	
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JXDatePicker dtpNgayVaoLam;
	private JXDatePicker dtpNgaySinh;

	private JLabel lblErrorHoTen;
	private JLabel lblErrorNgayVaoLam;
	private JLabel lblErrorNgaySinh;
	private LinkedList<JLabel> errorLabels = new LinkedList<>();
	
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnHuyBo;
	private JButton btnDieuPhoi;
	private JButton btnThangChuc;
	
	private JScrollPane scrollPane;
	
	private ChonQLMoi formChonQLMoi;
	PhanQuyen pq;

	public NhanVienGUI(String maNV) {
		this.setBounds(0, 0, 1200, 600);
		setBorder(new EmptyBorder(20, 0, 20, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {480, 0};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(0,0,20,0));
		infoPanel.setOpaque(false);
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.anchor = GridBagConstraints.NORTH;
		gbc_infoPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_infoPanel.insets = new Insets(0, 0, 0, 30);
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 0;
		add(infoPanel, gbc_infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{100, 0};
		gbl_infoPanel.rowHeights = new int[] {40, 30, 30, 30, 30, 30, 30, 30, 40, 40};
		gbl_infoPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 35, 0);
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		infoPanel.add(lblTitle, gbc_lblTitle);
		
		lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMaNV = new GridBagConstraints();
		gbc_lblMaNV.fill = GridBagConstraints.BOTH;
		gbc_lblMaNV.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaNV.gridx = 0;
		gbc_lblMaNV.gridy = 1;
		infoPanel.add(lblMaNV, gbc_lblMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setEnabled(false);
		txtMaNV.setColumns(10);
		GridBagConstraints gbc_txtMaNV = new GridBagConstraints();
		gbc_txtMaNV.fill = GridBagConstraints.BOTH;
		gbc_txtMaNV.insets = new Insets(0, 0, 5, 0);
		gbc_txtMaNV.gridx = 1;
		gbc_txtMaNV.gridy = 1;
		infoPanel.add(txtMaNV, gbc_txtMaNV);
		
		lblHoTen = new JLabel("Họ tên: ");
		lblHoTen.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblHoTen = new GridBagConstraints();
		gbc_lblHoTen.anchor = GridBagConstraints.WEST;
		gbc_lblHoTen.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoTen.gridx = 0;
		gbc_lblHoTen.gridy = 3;
		infoPanel.add(lblHoTen, gbc_lblHoTen);
		
		txtHoTen = new JTextField();
		GridBagConstraints gbc_txtHoTen = new GridBagConstraints();
		gbc_txtHoTen.fill = GridBagConstraints.BOTH;
		gbc_txtHoTen.insets = new Insets(0, 0, 5, 0);
		gbc_txtHoTen.gridx = 1;
		gbc_txtHoTen.gridy = 3;
		infoPanel.add(txtHoTen, gbc_txtHoTen);
		
		lblErrorHoTen = new JLabel("");
		lblErrorHoTen.setForeground(Color.RED);
		lblErrorHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorHoTen = new GridBagConstraints();
		gbc_lblErrorHoTen.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorHoTen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorHoTen.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorHoTen.gridx = 1;
		gbc_lblErrorHoTen.gridy = 4;
		infoPanel.add(lblErrorHoTen, gbc_lblErrorHoTen);
		
		lblNgayVaoLam = new JLabel("Vào làm:");
		lblNgayVaoLam.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayVaoLam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNgayVaoLam = new GridBagConstraints();
		gbc_lblNgayVaoLam.fill = GridBagConstraints.BOTH;
		gbc_lblNgayVaoLam.insets = new Insets(0, 0, 5, 5);
		gbc_lblNgayVaoLam.gridx = 0;
		gbc_lblNgayVaoLam.gridy = 5;
		infoPanel.add(lblNgayVaoLam, gbc_lblNgayVaoLam);
		
		dtpNgayVaoLam = new JXDatePicker();
		dtpNgayVaoLam.setDate(null);
		dtpNgayVaoLam.setPreferredSize(new Dimension(330, 25));
		GridBagConstraints gbc_dtpNgayVaoLam = new GridBagConstraints();
		gbc_dtpNgayVaoLam.fill = GridBagConstraints.BOTH;
		gbc_dtpNgayVaoLam.insets = new Insets(0, 0, 5, 0);
		gbc_dtpNgayVaoLam.gridx = 1;
		gbc_dtpNgayVaoLam.gridy = 5;
		infoPanel.add(dtpNgayVaoLam, gbc_dtpNgayVaoLam);
		
		lblErrorNgayVaoLam = new JLabel("");
		lblErrorNgayVaoLam.setForeground(Color.RED);
		lblErrorNgayVaoLam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorNgayVaoLam = new GridBagConstraints();
		gbc_lblErrorNgayVaoLam.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorNgayVaoLam.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorNgayVaoLam.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorNgayVaoLam.gridx = 1;
		gbc_lblErrorNgayVaoLam.gridy = 6;
		infoPanel.add(lblErrorNgayVaoLam, gbc_lblErrorNgayVaoLam);
		
		lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNgaySinh = new GridBagConstraints();
		gbc_lblNgaySinh.fill = GridBagConstraints.BOTH;
		gbc_lblNgaySinh.insets = new Insets(0, 0, 5, 5);
		gbc_lblNgaySinh.gridx = 0;
		gbc_lblNgaySinh.gridy = 7;
		infoPanel.add(lblNgaySinh, gbc_lblNgaySinh);
		
		dtpNgaySinh = new JXDatePicker();
		Redesign.DatePicker(dtpNgaySinh);
		dtpNgaySinh.setDate(null);
		dtpNgaySinh.setPreferredSize(new Dimension(330, 25));
		GridBagConstraints gbc_dtpNgaySinh = new GridBagConstraints();
		gbc_dtpNgaySinh.fill = GridBagConstraints.BOTH;
		gbc_dtpNgaySinh.insets = new Insets(0, 0, 5, 0);
		gbc_dtpNgaySinh.gridx = 1;
		gbc_dtpNgaySinh.gridy = 7;
		infoPanel.add(dtpNgaySinh, gbc_dtpNgaySinh);
		
		lblErrorNgaySinh = new JLabel("");
		lblErrorNgaySinh.setForeground(Color.RED);
		lblErrorNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorNgaySinh = new GridBagConstraints();
		gbc_lblErrorNgaySinh.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorNgaySinh.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorNgaySinh.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorNgaySinh.gridx = 1;
		gbc_lblErrorNgaySinh.gridy = 8;
		infoPanel.add(lblErrorNgaySinh, gbc_lblErrorNgaySinh);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.insets = new Insets(0, 0, 10, 0);
		gbc_buttonPanel.gridwidth = 2;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 9;
		infoPanel.add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		
		btnCapNhat = new JButton("CẬP NHẬT");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMaNV.getText().isEmpty()) {
					MessageBox.loi("Vui lòng chọn nhân viên");
					return;
				}
				NhanVien nv = new NhanVien(txtMaNV.getText(), txtHoTen.getText(), "",
						Time.toString(dtpNgayVaoLam.getDate()), Time.toString(dtpNgaySinh.getDate()), maNV);
				String[] checking = NhanVienBUS.kiemTra(nv);
				if (checking.length > 0) {
					for (int i=0; i<errorLabels.size(); i++) errorLabels.get(i).setText(checking[i]);
					return;
				}				
				if (MessageBox.cauHoi("Bạn có muốn cập nhật nhân viên này?") == JOptionPane.YES_OPTION) {
					if (NhanVienBUS.update(nv)) {
						MessageBox.thanhCong("Cập nhật nhân viên thành công");
						NhanVienBUS.load(maNV); load(); clear();
					}
					else MessageBox.loi("Đã có lỗi xảy ra, cập nhật nhân viên thất bại");
				}
			}
		});
		btnCapNhat.setBackground(new Color(255, 255, 255));
		btnCapNhat.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnCapNhat);
		btnCapNhat.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		btnXoa = new JButton("XÓA");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMaNV.getText().isEmpty()) {
					MessageBox.loi("Vui lòng chọn nhân viên");
					return;
				}			
				if (MessageBox.cauHoi("Bạn có muốn xóa nhân viên này? Một khi đã xóa thì không thể hồi phục") == JOptionPane.YES_OPTION) {
					if (NhanVienBUS.delete(txtMaNV.getText())) {
						MessageBox.thanhCong("Xóa nhân viên thành công");
						NhanVienBUS.load(maNV); load(); clear();
					}
					else MessageBox.loi("Đã có lỗi xảy ra, xóa nhân viên thất bại");
				}
			}
		});
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnXoa);
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		btnHuyBo = new JButton("HỦY BỎ");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnHuyBo.setBackground(new Color(255, 255, 255));
		btnHuyBo.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnHuyBo);
		btnHuyBo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		JPanel buttonPanel_1 = new JPanel();
		buttonPanel_1.setOpaque(false);
		GridBagConstraints gbc_buttonPanel_1 = new GridBagConstraints();
		gbc_buttonPanel_1.anchor = GridBagConstraints.NORTH;
		gbc_buttonPanel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPanel_1.gridwidth = 2;
		gbc_buttonPanel_1.gridx = 0;
		gbc_buttonPanel_1.gridy = 10;
		infoPanel.add(buttonPanel_1, gbc_buttonPanel_1);
		buttonPanel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		btnDieuPhoi = new JButton("ĐỔI QUẢN LÝ");
		btnDieuPhoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMaNV.getText().isEmpty()) {
					MessageBox.loi("Vui lòng chọn nhân viên");
					return;
				}
				openSubform();
			}
		});
		btnDieuPhoi.setBackground(new Color(255, 255, 255));
		btnDieuPhoi.setPreferredSize(new Dimension(130,30));
		buttonPanel_1.add(btnDieuPhoi);
		btnDieuPhoi.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		btnThangChuc = new JButton("THĂNG CHỨC");
		btnThangChuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMaNV.getText().isEmpty()) {
					MessageBox.thanhCong("Vui lòng chọn nhân viên");
					return;
				}
				if (MessageBox.cauHoi("Bạn có muốn thăng chức nhân viên này?") == JOptionPane.YES_OPTION) {
					if (NhanVienBUS.thangChuc(txtMaNV.getText())) {
						MessageBox.thanhCong("Thăng chức nhân viên thành công");
						NhanVienBUS.load(maNV); load(); clear();
					}
					else MessageBox.loi("Đã có lỗi xảy ra, thăng chức nhân viên thất bại");
				}
			}
		});
		btnThangChuc.setBackground(new Color(255, 255, 255));
		btnThangChuc.setPreferredSize(new Dimension(130,30));
		buttonPanel_1.add(btnThangChuc);
		btnThangChuc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NhanVien nv = search(model.getValueAt(table.getSelectedRow(), 0).toString());
				txtMaNV.setText(nv.getMaNV());
				txtHoTen.setText(nv.getTenNV());
				dtpNgayVaoLam.setDate(Time.parseDate(nv.getNgayVaoLam()));
				dtpNgaySinh.setDate(Time.parseDate(nv.getNgaySinh()));				
			}
		});
		Redesign.Table(table);
		scrollPane.setViewportView(table);
		load();
		initError();
		
		repainting();
	}
	
	private void load() {
		nhanVien = NhanVienBUS.getDanhSachNV();
		model.setRowCount(0);
		Object[] data = new Object[model.getColumnCount()];
		
		if (nhanVien != null)
			for (NhanVien nv: nhanVien) {
				data[0] = nv.getMaNV(); data[1] = nv.getTenNV(); data[2] = nv.getNgayVaoLam(); 
				data[3] = nv.getNgaySinh(); data[4] = nv.getTrangThai() ? 1 : 0;
				for (int i=0; i<model.getColumnCount(); i++) if (data[i] == null) data[i] = "";
				model.addRow(data);
			}
		Redesign.Table(table);
	}
	
	private void clear() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		dtpNgayVaoLam.setDate(null);
		dtpNgaySinh.setDate(null);
		clearError();
	}
	
	private void clearError() {
		for (JLabel label: errorLabels) label.setText("");
	}
	
	private NhanVien search(String maNV) {
		for (NhanVien nv: nhanVien) if (nv.getMaNV().equals(maNV)) return nv;
		return null;
	}
	
	private void initError() {
		errorLabels.add(lblErrorHoTen);
		errorLabels.add(lblErrorNgayVaoLam);
		errorLabels.add(lblErrorNgaySinh);
	}
	
	private void openSubform() {
		formChonQLMoi = new ChonQLMoi(txtMaNV.getText(), this);
		formChonQLMoi.setLocationRelativeTo(null);
		formChonQLMoi.setVisible(true);
	}
	
	void dieuPhoi(String maNV, String maQL) {
		if (NhanVienBUS.dieuPhoi(maNV, maQL)) {
			MessageBox.thanhCong("Điều phối nhân viên thành công");
			NhanVienBUS.load("NV000003"); load(); clear();			
		}
		else MessageBox.loi("Đã có lỗi xảy ra, điều phối nhân viên thất bại");
	}
	
	void repainting() {
		setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(table);
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		
		lblTitle.setForeground(ThemeColor.TEXT);
		lblMaNV.setForeground(ThemeColor.TEXT);
		lblHoTen.setForeground(ThemeColor.TEXT);
		lblNgayVaoLam.setForeground(ThemeColor.TEXT);
		lblNgaySinh.setForeground(ThemeColor.TEXT);
		
		Redesign.TextField(txtMaNV);
		Redesign.TextField(txtHoTen);
		Redesign.DatePicker(dtpNgayVaoLam);
		Redesign.DatePicker(dtpNgaySinh);
		
		Redesign.SubmitButton(btnCapNhat);
		Redesign.SubmitButton(btnXoa);
		Redesign.SubmitButton(btnHuyBo);
		Redesign.SubmitButton(btnDieuPhoi);
		Redesign.SubmitButton(btnThangChuc);
	}
}
