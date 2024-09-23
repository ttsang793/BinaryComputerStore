package GUI;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import DTO.ChiTiet;
import DTO.NhanVien;
import DTO.PhieuNhap;
import DTO.NhaCungCap;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JButton;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

class XemPhieuNhapGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private LinkedList<PhieuNhap> phieuNhap = new LinkedList<>();
	
	private JLabel lblLocNCC;
	private JLabel lblLocNV;
	private JLabel lblLocNgayNhap;
	private JLabel lblNoi;
	private JLabel lblLocTongTien;
	private JLabel lblNoi2;
	
	private JTable tablePN;
	private DefaultTableModel modelPN = new DefaultTableModel(new Object[][] {}, new String[] {"Mã PN", "Ngày nhập", "Nhà cung cấp", "Tổng chi phí"});
	private JComboBox<NhanVien> cbbNhanVien;
	private DefaultComboBoxModel<NhanVien> modelNV = new DefaultComboBoxModel<>(new NhanVien[] {new NhanVien("Tất cả nhân viên")});
	private JComboBox<NhaCungCap> cbbNhaCungCap;
	private DefaultComboBoxModel<NhaCungCap> modelNCC = new DefaultComboBoxModel<>(new NhaCungCap[] {new NhaCungCap("Tất cả nhà cung cấp")});
	private JXDatePicker fromPicker;
	private JXDatePicker toPicker;
	private JTextField txtMin;
	private JTextField txtMax;
	private JButton btnSearch;
	private JButton btnReset;
	private JScrollPane danhSachDHPane;
	private JTable tableSP;
	private DefaultTableModel modelSP = new DefaultTableModel(new Object[][] {}, new String[] {"Mã SP", "Tên SP", "Đơn giá", "Số lượng"});
	
	private JTextField txtMaPN;
	private JTextField txtNgayNhap;
	private JTextField txtNCC;
	private JTextField txtNhanVien;
	private JTextField txtTongTien;	

	private JLabel lblTitle;
	private JLabel lblMaPN;
	private JLabel lblNgayNhap;
	private JLabel lblNCC;
	private JLabel lblNhanVien;
	private JLabel lblTongTien;
	private JLabel lblVND;
	
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public XemPhieuNhapGUI(String maQL) {
		this.setBounds(0,0,1200,600);
		this.setBorder(new EmptyBorder(20, 0, 20,0));
		loadPN();
		setLayout(new GridLayout(0, 2, 30, 0));
		
		JPanel listPanel = new JPanel();
		listPanel.setOpaque(false);
		add(listPanel);
		GridBagLayout gbl_listPanel = new GridBagLayout();
		gbl_listPanel.columnWidths = new int[] {100, 0, 10, 0, 100};
		gbl_listPanel.rowHeights = new int[]{25, 25, 25, 25, 0};
		gbl_listPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0};
		gbl_listPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		listPanel.setLayout(gbl_listPanel);
		
		lblLocNCC = new JLabel("Nhà cung cấp:");
		lblLocNCC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLocNCC = new GridBagConstraints();
		gbc_lblLocNCC.fill = GridBagConstraints.BOTH;
		gbc_lblLocNCC.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocNCC.gridx = 0;
		gbc_lblLocNCC.gridy = 0;
		listPanel.add(lblLocNCC, gbc_lblLocNCC);
		
		cbbNhaCungCap = new JComboBox<>();
		loadNCC();
		cbbNhaCungCap.setModel(modelNCC);
		GridBagConstraints gbc_cbbNhaCungCap = new GridBagConstraints();
		gbc_cbbNhaCungCap.gridwidth = 3;
		gbc_cbbNhaCungCap.insets = new Insets(0, 0, 5, 0);
		gbc_cbbNhaCungCap.fill = GridBagConstraints.BOTH;
		gbc_cbbNhaCungCap.gridx = 1;
		gbc_cbbNhaCungCap.gridy = 0;
		listPanel.add(cbbNhaCungCap, gbc_cbbNhaCungCap);
		
		btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fill(maQL);
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.insets = new Insets(0, 5, 5, 0);
		gbc_btnSearch.gridx = 4;
		gbc_btnSearch.gridy = 0;
		listPanel.add(btnSearch, gbc_btnSearch);
		
		lblLocNV = new JLabel("Nhân viên:");
		lblLocNV.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLocNV = new GridBagConstraints();
		gbc_lblLocNV.fill = GridBagConstraints.BOTH;
		gbc_lblLocNV.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocNV.gridx = 0;
		gbc_lblLocNV.gridy = 1;
		listPanel.add(lblLocNV, gbc_lblLocNV);
		
		cbbNhanVien = new JComboBox<>();
		loadNV();
		cbbNhanVien.setModel(modelNV);
		GridBagConstraints gbc_cbbNhanVien = new GridBagConstraints();
		gbc_cbbNhanVien.gridwidth = 3;
		gbc_cbbNhanVien.insets = new Insets(0, 0, 5, 0);
		gbc_cbbNhanVien.fill = GridBagConstraints.BOTH;
		gbc_cbbNhanVien.gridx = 1;
		gbc_cbbNhanVien.gridy = 1;
		listPanel.add(cbbNhanVien, gbc_cbbNhanVien);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				loadPN();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill = GridBagConstraints.BOTH;
		gbc_btnReset.insets = new Insets(0, 5, 5, 0);
		gbc_btnReset.gridx = 4;
		gbc_btnReset.gridy = 1;
		listPanel.add(btnReset, gbc_btnReset);
		
		lblLocNgayNhap = new JLabel("Ngày nhập:");
		lblLocNgayNhap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLocNgayNhap = new GridBagConstraints();
		gbc_lblLocNgayNhap.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLocNgayNhap.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocNgayNhap.gridx = 0;
		gbc_lblLocNgayNhap.gridy = 2;
		listPanel.add(lblLocNgayNhap, gbc_lblLocNgayNhap);
		
		fromPicker = new JXDatePicker();
		fromPicker.setDate(new Date());
		GridBagConstraints gbc_fromPicker = new GridBagConstraints();
		gbc_fromPicker.fill = GridBagConstraints.BOTH;
		gbc_fromPicker.insets = new Insets(0, 0, 5, 5);
		gbc_fromPicker.gridx = 1;
		gbc_fromPicker.gridy = 2;
		listPanel.add(fromPicker, gbc_fromPicker);
		
		lblNoi = new JLabel("-");
		lblNoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNoi = new GridBagConstraints();
		gbc_lblNoi.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNoi.insets = new Insets(0, 0, 0, 0);
		gbc_lblNoi.gridx = 2;
		gbc_lblNoi.gridy = 2;
		listPanel.add(lblNoi, gbc_lblNoi);
		
		toPicker = new JXDatePicker();
		toPicker.setDate(new Date());	
		GridBagConstraints gbc_toPicker = new GridBagConstraints();
		gbc_toPicker.fill = GridBagConstraints.BOTH;
		gbc_toPicker.insets = new Insets(0, 5, 5, 0);
		gbc_toPicker.gridx = 3;
		gbc_toPicker.gridy = 2;
		listPanel.add(toPicker, gbc_toPicker);
		
		lblLocTongTien = new JLabel("Tổng chi phí:");
		lblLocTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLocTongTien = new GridBagConstraints();
		gbc_lblLocTongTien.fill = GridBagConstraints.BOTH;
		gbc_lblLocTongTien.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocTongTien.gridx = 0;
		gbc_lblLocTongTien.gridy = 3;
		listPanel.add(lblLocTongTien, gbc_lblLocTongTien);
		
		txtMin = new JTextField();
		GridBagConstraints gbc_txtMin = new GridBagConstraints();
		gbc_txtMin.insets = new Insets(0, 0, 5, 5);
		gbc_txtMin.fill = GridBagConstraints.BOTH;
		gbc_txtMin.gridx = 1;
		gbc_txtMin.gridy = 3;
		listPanel.add(txtMin, gbc_txtMin);
		txtMin.setColumns(10);
		
		lblNoi2 = new JLabel("-");
		lblNoi2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNoi2 = new GridBagConstraints();
		gbc_lblNoi2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNoi2.insets = new Insets(0, 0, 0, 0);
		gbc_lblNoi2.gridx = 2;
		gbc_lblNoi2.gridy = 3;
		listPanel.add(lblNoi2, gbc_lblNoi2);
		
		txtMax = new JTextField();
		GridBagConstraints gbc_txtMax = new GridBagConstraints();
		gbc_txtMax.insets = new Insets(0, 5, 5, 0);
		gbc_txtMax.fill = GridBagConstraints.BOTH;
		gbc_txtMax.gridx = 3;
		gbc_txtMax.gridy = 3;
		listPanel.add(txtMax, gbc_txtMax);
		txtMax.setColumns(10);
		
		danhSachDHPane = new JScrollPane();
		GridBagConstraints gbc_danhSachDHPane = new GridBagConstraints();
		gbc_danhSachDHPane.gridwidth = 5;
		gbc_danhSachDHPane.fill = GridBagConstraints.BOTH;
		gbc_danhSachDHPane.gridx = 0;
		gbc_danhSachDHPane.gridy = 4;
		listPanel.add(danhSachDHPane, gbc_danhSachDHPane);
		
		tablePN = new JTable(modelPN);
		tablePN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PhieuNhap pn = search(modelPN.getValueAt(tablePN.getSelectedRow(), 0).toString());
				loadSP(pn.getMaPN());
				txtMaPN.setText(pn.getMaPN());
				txtNgayNhap.setText(pn.getNgayNhap());
				txtTongTien.setText(Long.toString(pn.getTongTien()));
				txtNhanVien.setText(pn.getMaNV());
				txtNCC.setText(pn.getMaNCC());
			}
		});
		danhSachDHPane.setViewportView(tablePN);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		add(infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{100, 0, 20};
		gbl_infoPanel.rowHeights = new int[]{25, 25, 25, 25, 25, 0, 25};
		gbl_infoPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		lblTitle = new JLabel("CHI TIẾT NHẬP HÀNG");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		infoPanel.add(lblTitle, gbc_lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		
		lblMaPN = new JLabel("Mã PN:");
		GridBagConstraints gbc_lblMaPN = new GridBagConstraints();
		gbc_lblMaPN.anchor = GridBagConstraints.WEST;
		gbc_lblMaPN.fill = GridBagConstraints.VERTICAL;
		gbc_lblMaPN.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaPN.gridx = 0;
		gbc_lblMaPN.gridy = 1;
		infoPanel.add(lblMaPN, gbc_lblMaPN);
		lblMaPN.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtMaPN = new JTextField();
		GridBagConstraints gbc_txtMaPN = new GridBagConstraints();
		gbc_txtMaPN.gridwidth = 2;
		gbc_txtMaPN.fill = GridBagConstraints.BOTH;
		gbc_txtMaPN.insets = new Insets(0, 0, 5, 0);
		gbc_txtMaPN.gridx = 1;
		gbc_txtMaPN.gridy = 1;
		infoPanel.add(txtMaPN, gbc_txtMaPN);
		txtMaPN.setColumns(10);
		
		lblNgayNhap = new JLabel("Ngày nhập:");
		GridBagConstraints gbc_lblNgayNhap = new GridBagConstraints();
		gbc_lblNgayNhap.anchor = GridBagConstraints.WEST;
		gbc_lblNgayNhap.fill = GridBagConstraints.VERTICAL;
		gbc_lblNgayNhap.insets = new Insets(0, 0, 5, 5);
		gbc_lblNgayNhap.gridx = 0;
		gbc_lblNgayNhap.gridy = 2;
		infoPanel.add(lblNgayNhap, gbc_lblNgayNhap);
		lblNgayNhap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtNgayNhap = new JTextField();
		GridBagConstraints gbc_txtNgayNhap = new GridBagConstraints();
		gbc_txtNgayNhap.anchor = GridBagConstraints.NORTH;
		gbc_txtNgayNhap.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNgayNhap.insets = new Insets(0, 0, 5, 0);
		gbc_txtNgayNhap.gridwidth = 2;
		gbc_txtNgayNhap.gridx = 1;
		gbc_txtNgayNhap.gridy = 2;
		infoPanel.add(txtNgayNhap, gbc_txtNgayNhap);
		txtNgayNhap.setColumns(10);
		
		txtNhanVien = new JTextField();
		GridBagConstraints gbc_txtNhanVien = new GridBagConstraints();
		gbc_txtNhanVien.gridwidth = 2;
		gbc_txtNhanVien.anchor = GridBagConstraints.NORTH;
		gbc_txtNhanVien.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNhanVien.insets = new Insets(0, 0, 5, 0);
		gbc_txtNhanVien.gridx = 1;
		gbc_txtNhanVien.gridy = 3;
		infoPanel.add(txtNhanVien, gbc_txtNhanVien);
		txtNhanVien.setColumns(10);
		
		lblNhanVien = new JLabel("Nhân viên:");
		GridBagConstraints gbc_lblNhanVien = new GridBagConstraints();
		gbc_lblNhanVien.anchor = GridBagConstraints.WEST;
		gbc_lblNhanVien.fill = GridBagConstraints.VERTICAL;
		gbc_lblNhanVien.insets = new Insets(0, 0, 5, 5);
		gbc_lblNhanVien.gridx = 0;
		gbc_lblNhanVien.gridy = 3;
		infoPanel.add(lblNhanVien, gbc_lblNhanVien);
		lblNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblNCC = new JLabel("Nhà cung cấp:");
		GridBagConstraints gbc_lblNCC = new GridBagConstraints();
		gbc_lblNCC.anchor = GridBagConstraints.WEST;
		gbc_lblNCC.fill = GridBagConstraints.VERTICAL;
		gbc_lblNCC.insets = new Insets(0, 0, 5, 5);
		gbc_lblNCC.gridx = 0;
		gbc_lblNCC.gridy = 4;
		infoPanel.add(lblNCC, gbc_lblNCC);
		lblNCC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtNCC = new JTextField();
		GridBagConstraints gbc_txtNCC = new GridBagConstraints();
		gbc_txtNCC.gridwidth = 2;
		gbc_txtNCC.anchor = GridBagConstraints.NORTH;
		gbc_txtNCC.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNCC.insets = new Insets(0, 0, 5, 0);
		gbc_txtNCC.gridx = 1;
		gbc_txtNCC.gridy = 4;
		infoPanel.add(txtNCC, gbc_txtNCC);
		txtNCC.setColumns(10);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		infoPanel.add(scrollPane, gbc_scrollPane);
		
		tableSP = new JTable(modelSP);
		scrollPane.setViewportView(tableSP);
		
		lblTongTien = new JLabel("Tổng tiền:");
		GridBagConstraints gbc_lblTongTien = new GridBagConstraints();
		gbc_lblTongTien.anchor = GridBagConstraints.WEST;
		gbc_lblTongTien.fill = GridBagConstraints.VERTICAL;
		gbc_lblTongTien.insets = new Insets(0, 0, 0, 5);
		gbc_lblTongTien.gridx = 0;
		gbc_lblTongTien.gridy = 6;
		infoPanel.add(lblTongTien, gbc_lblTongTien);
		lblTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtTongTien = new JTextField();
		GridBagConstraints gbc_txtTongTien = new GridBagConstraints();
		gbc_txtTongTien.anchor = GridBagConstraints.NORTH;
		gbc_txtTongTien.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTongTien.insets = new Insets(0, 0, 0, 5);
		gbc_txtTongTien.gridx = 1;
		gbc_txtTongTien.gridy = 6;
		infoPanel.add(txtTongTien, gbc_txtTongTien);
		txtTongTien.setColumns(10);
		
		lblVND = new JLabel("VND");
		GridBagConstraints gbc_lblVND = new GridBagConstraints();
		gbc_lblVND.anchor = GridBagConstraints.WEST;
		gbc_lblVND.fill = GridBagConstraints.VERTICAL;
		gbc_lblVND.gridx = 2;
		gbc_lblVND.gridy = 6;
		infoPanel.add(lblVND, gbc_lblVND);
		lblVND.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		repainting();
	}
	
	private void loadPN() {
		phieuNhap = PhieuNhapBUS.getDanhSachPN();
		modelPN.setRowCount(0);
		Object[] data = new Object[modelPN.getColumnCount()];
		
		if (phieuNhap != null)
			for (PhieuNhap pn: phieuNhap) {
				data[0] = pn.getMaPN(); data[1] = pn.getNgayNhap(); data[2] = pn.getMaNCC(); data[3] = pn.getTongTien();
				for (int i=0; i<modelPN.getColumnCount(); i++) if (data[i] == null) data[i] = "";
				modelPN.addRow(data);
			}		
	}
	
	private void fill(String maQL) {
		if (fromPicker.getDate().compareTo(new Date()) > 0) {
			MessageBox.loi("Ngày bên trái phải nhỏ hơn ngày hiện tại");
			return;
		}
		
		if (toPicker.getDate().compareTo(new Date()) > 0) {
			MessageBox.loi("Ngày bên phải phải nhỏ hơn ngày hiện tại");
			return;
		}
		
		if (txtMin.getText().isEmpty() ^ txtMax.getText().isEmpty()) {
			MessageBox.loi("Nhập đầy đủ các tổng đơn hàng, hoặc để trống cả 2");
			return;			
		}
		
		if (fromPicker.getDate().compareTo(toPicker.getDate()) > 0) {
			MessageBox.loi("Vui lòng chọn ngày bên trái nhỏ hơn ngày bên phải");
			return;
		}
		
		phieuNhap = PhieuNhapBUS.fillPN(cbbNhaCungCap.getItemAt(cbbNhaCungCap.getSelectedIndex()).getMaNCC(),
				cbbNhanVien.getItemAt(cbbNhanVien.getSelectedIndex()).getMaNV(), maQL,
				fromPicker.getDate(), toPicker.getDate(), txtMin.getText(), txtMax.getText());
		modelPN.setRowCount(0);
		Object[] data = new Object[modelPN.getColumnCount()];
		
		if (phieuNhap != null)
			if (phieuNhap.size() > 0) {
				for (PhieuNhap pn: phieuNhap) {
					data[0] = pn.getMaPN(); data[1] = pn.getNgayNhap(); data[2] = pn.getMaNCC(); data[3] = pn.getTongTien();
					for (int i=0; i<modelPN.getColumnCount(); i++) if (data[i] == null) data[i] = "";
					modelPN.addRow(data);
				}
				return;
			}
		MessageBox.loi("Không tìm thấy phiếu nhập");
	}
	
	private void loadSP(String maPN) {
		modelSP.setRowCount(0);
		Object[] data = new Object[modelSP.getColumnCount()];
		
		for (ChiTiet ct: PhieuNhapBUS.getChiTietMaPN(maPN)) {
			data[0] = ct.getMaSP(); data[1] = ct.getTenSP(); data[2] = ct.getDonGia(); data[3] = ct.getSoLuong();
			for (int i=0; i<modelSP.getColumnCount(); i++) if (data[i] == null) data[i] = "";
			modelSP.addRow(data);
		}	
	}
	
	private void loadNV() {
		for (NhanVien nv: NhanVienBUS.getDanhSachNV()) modelNV.addElement(nv);
		AutoCompleteDecorator.decorate(cbbNhanVien);		
	}
	
	private void loadNCC() {
		for (NhaCungCap ncc: NhaCungCapBUS.getDanhSachNCC()) modelNCC.addElement(ncc);
		AutoCompleteDecorator.decorate(cbbNhaCungCap);
	}
	
	private PhieuNhap search(String maPN) {
		for (PhieuNhap pn: phieuNhap) if (pn.getMaPN().equals(maPN)) return pn;
		return null;
	}
	
	private void clear() {
		cbbNhanVien.setSelectedIndex(0);
		cbbNhaCungCap.setSelectedIndex(0);
		fromPicker.setDate(new Date());
		toPicker.setDate(new Date());
		txtMin.setText("");
		txtMax.setText("");
	}
	
	void repainting() {
		setBackground(ThemeColor.LIGHT_1);
		danhSachDHPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(tablePN);
		
		Redesign.ComboBox(cbbNhaCungCap);
		Redesign.ComboBox(cbbNhanVien);
		Redesign.DatePicker(fromPicker);
		Redesign.DatePicker(toPicker);
		Redesign.TextField(txtMin);
		Redesign.TextField(txtMax);
		Redesign.SubmitButton(btnSearch);
		Redesign.SubmitButton(btnReset);
		
		lblLocNCC.setForeground(ThemeColor.TEXT);
		lblLocNV.setForeground(ThemeColor.TEXT);
		lblLocNgayNhap.setForeground(ThemeColor.TEXT);
		lblLocTongTien.setForeground(ThemeColor.TEXT);
		
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(tableSP);
		Redesign.TextField(txtMaPN);
		Redesign.TextField(txtNgayNhap);
		Redesign.TextField(txtNCC);
		Redesign.TextField(txtNhanVien);
		Redesign.TextField(txtTongTien);
		
		lblTitle.setForeground(ThemeColor.TEXT);
		lblMaPN.setForeground(ThemeColor.TEXT);
		lblNgayNhap.setForeground(ThemeColor.TEXT);
		lblNCC.setForeground(ThemeColor.TEXT);
		lblNhanVien.setForeground(ThemeColor.TEXT);
		lblTongTien.setForeground(ThemeColor.TEXT);
		lblVND.setForeground(ThemeColor.TEXT);
	}
}
