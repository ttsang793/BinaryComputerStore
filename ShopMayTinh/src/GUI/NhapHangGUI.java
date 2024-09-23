package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

import DTO.PhieuNhap;
import DTO.SanPham;
import DTO.ChiTiet;
import DTO.NhaCungCap;
import BUS.PhieuNhapBUS;
import BUS.PhuKienBUS;
import BUS.SanPhamCheck;
import BUS.LaptopBUS;
import BUS.NhaCungCapBUS;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class NhapHangGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JLabel lblNgayNhap;
	private JLabel lblTongTien;
	private JLabel lblVND;
	private JLabel lblNCC;
	private JTextField txtNgayNhap;
	private JTextField txtTongTien;
	private JComboBox<NhaCungCap> cbbNCC;
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Mã SP", "Tên sản phẩm", "Đơn giá", "Số lượng"});
	private DefaultComboBoxModel<NhaCungCap> nccModel = new DefaultComboBoxModel<>();	
	
	private JPanel productPanel;
	private LinkedList<SanPham> danhSachSP = new LinkedList<>();
	private JLabel lblSanPham;
	private JComboBox<String> cbbSanPham;
	private LinkedList<ChiTiet> danhSachNhap = new LinkedList<>();
	private DefaultComboBoxModel<String> spModel = new DefaultComboBoxModel<>(); 
	private JLabel lblDonGia;
	private JLabel lblSoLuong;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnHuyBo;
	
	private JScrollPane scrollPane;
	private JTable table;
	
	private JButton btnLap;
	private JButton btnClear;

	/**
	 * Create the panel.
	 */
	public NhapHangGUI(String maNV) {
		this.setBounds(0, 0, 1200, 600);
		this.setBorder(new EmptyBorder(20, 0, 20, 0));
		String date = java.time.LocalDate.now().toString();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0, 100, 0, 20};
		gridBagLayout.rowHeights = new int[] {40, 25, 0, 0, 25, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		lblTitle = new JLabel("LẬP PHIẾU NHẬP");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 15, 0);
		gbc_lblTitle.gridwidth = 5;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		
		lblNgayNhap = new JLabel("Ngày nhập:");
		GridBagConstraints gbc_lblNgayNhap = new GridBagConstraints();
		gbc_lblNgayNhap.fill = GridBagConstraints.BOTH;
		gbc_lblNgayNhap.insets = new Insets(0, 0, 15, 5);
		gbc_lblNgayNhap.gridx = 0;
		gbc_lblNgayNhap.gridy = 1;
		add(lblNgayNhap, gbc_lblNgayNhap);
		lblNgayNhap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtNgayNhap = new JTextField();
		txtNgayNhap.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtNgayNhap = new GridBagConstraints();
		gbc_txtNgayNhap.fill = GridBagConstraints.BOTH;
		gbc_txtNgayNhap.insets = new Insets(0, 0, 15, 5);
		gbc_txtNgayNhap.gridx = 1;
		gbc_txtNgayNhap.gridy = 1;
		add(txtNgayNhap, gbc_txtNgayNhap);		
		txtNgayNhap.setText(date);
		txtNgayNhap.setColumns(10);
		
		lblNCC = new JLabel("Nhà cung cấp:");
		GridBagConstraints gbc_lblNCC = new GridBagConstraints();
		gbc_lblNCC.fill = GridBagConstraints.BOTH;
		gbc_lblNCC.insets = new Insets(0, 0, 15, 5);
		gbc_lblNCC.gridx = 2;
		gbc_lblNCC.gridy = 1;
		add(lblNCC, gbc_lblNCC);
		lblNCC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		cbbNCC = new JComboBox<>(nccModel);
		cbbNCC.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_cbbNCC = new GridBagConstraints();
		gbc_cbbNCC.fill = GridBagConstraints.BOTH;
		gbc_cbbNCC.insets = new Insets(0, 0, 15, 0);
		gbc_cbbNCC.gridwidth = 2;
		gbc_cbbNCC.gridx = 3;
		gbc_cbbNCC.gridy = 1;
		add(cbbNCC, gbc_cbbNCC);
		
		productPanel = new JPanel();
		productPanel.setOpaque(false);
		GridBagConstraints gbc_productPanel = new GridBagConstraints();
		gbc_productPanel.fill = GridBagConstraints.BOTH;
		gbc_productPanel.gridwidth = 5;
		gbc_productPanel.insets = new Insets(0, 0, 15, 0);
		gbc_productPanel.gridx = 0;
		gbc_productPanel.gridy = 2;
		add(productPanel, gbc_productPanel);
		GridBagLayout gbl_productPanel = new GridBagLayout();
		gbl_productPanel.columnWidths = new int[]{100, 0, 100, 0, 100, 0, 20, 0};
		gbl_productPanel.rowHeights = new int[] {25, 25};
		gbl_productPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_productPanel.rowWeights = new double[]{0.0, 0.0};
		productPanel.setLayout(gbl_productPanel);
		
		lblSanPham = new JLabel("Sản phẩm:");
		GridBagConstraints gbc_lblSanPham = new GridBagConstraints();
		gbc_lblSanPham.fill = GridBagConstraints.BOTH;
		gbc_lblSanPham.insets = new Insets(0, 0, 15, 5);
		gbc_lblSanPham.gridx = 0;
		gbc_lblSanPham.gridy = 0;
		productPanel.add(lblSanPham, gbc_lblSanPham);
		lblSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		cbbSanPham = new JComboBox<>(spModel);
		cbbSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbSanPham.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_cbbSanPham = new GridBagConstraints();
		gbc_cbbSanPham.anchor = GridBagConstraints.SOUTH;
		gbc_cbbSanPham.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbSanPham.gridwidth = 3;
		gbc_cbbSanPham.insets = new Insets(0, 0, 15, 0);
		gbc_cbbSanPham.gridx = 1;
		gbc_cbbSanPham.gridy = 0;
		productPanel.add(cbbSanPham, gbc_cbbSanPham);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.anchor = GridBagConstraints.SOUTH;
		gbc_buttonPanel.gridheight = 2;
		gbc_buttonPanel.insets = new Insets(0, 0, 15, 0);
		gbc_buttonPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPanel.gridwidth = 3;
		gbc_buttonPanel.gridx = 4;
		gbc_buttonPanel.gridy = 0;
		productPanel.add(buttonPanel, gbc_buttonPanel);
		
		btnThem = new JButton("THÊM");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				them();
			}
		});
		btnThem.setPreferredSize(new Dimension(120, 30));
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnThem.setBackground(Color.WHITE);
		buttonPanel.add(btnThem);
		
		btnSua = new JButton("SỬA");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sua();
			}
		});
		btnSua.setPreferredSize(new Dimension(120, 30));
		btnSua.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSua.setBackground(Color.WHITE);
		buttonPanel.add(btnSua);
		
		btnXoa = new JButton("XÓA");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoa();
			}
		});
		btnXoa.setPreferredSize(new Dimension(120, 30));
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnXoa.setBackground(Color.WHITE);
		buttonPanel.add(btnXoa);
		
		btnHuyBo = new JButton("HỦY BỎ");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSP();
			}
		});
		btnHuyBo.setPreferredSize(new Dimension(120, 30));
		btnHuyBo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnHuyBo.setBackground(Color.WHITE);
		buttonPanel.add(btnHuyBo);
		
		lblDonGia = new JLabel("Đơn giá:");
		GridBagConstraints gbc_lblDonGia = new GridBagConstraints();
		gbc_lblDonGia.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDonGia.insets = new Insets(0, 0, 0, 15);
		gbc_lblDonGia.gridx = 0;
		gbc_lblDonGia.gridy = 1;
		productPanel.add(lblDonGia, gbc_lblDonGia);
		lblDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtDonGia = new JTextField();
		txtDonGia.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtDonGia = new GridBagConstraints();
		gbc_txtDonGia.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDonGia.insets = new Insets(0, 0, 0, 15);
		gbc_txtDonGia.gridx = 1;
		gbc_txtDonGia.gridy = 1;
		productPanel.add(txtDonGia, gbc_txtDonGia);
		txtDonGia.setColumns(10);
		
		lblSoLuong = new JLabel("Số lượng:");
		GridBagConstraints gbc_lblSoLuong = new GridBagConstraints();
		gbc_lblSoLuong.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSoLuong.insets = new Insets(0, 0, 0, 15);
		gbc_lblSoLuong.gridx = 2;
		gbc_lblSoLuong.gridy = 1;
		productPanel.add(lblSoLuong, gbc_lblSoLuong);
		lblSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtSoLuong = new JTextField();
		txtSoLuong.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtSoLuong = new GridBagConstraints();
		gbc_txtSoLuong.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSoLuong.gridx = 3;
		gbc_txtSoLuong.gridy = 1;
		productPanel.add(txtSoLuong, gbc_txtSoLuong);
		txtSoLuong.setColumns(10);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 15, 0);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String maSP = table.getValueAt(row, 1).toString() + " - " + table.getValueAt(row, 0).toString();
				cbbSanPham.setSelectedItem(maSP);
				txtDonGia.setText(table.getValueAt(row, 2).toString());
				txtSoLuong.setText(table.getValueAt(row, 3).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		lblTongTien = new JLabel("Tổng tiền:");
		GridBagConstraints gbc_lblTongTien = new GridBagConstraints();
		gbc_lblTongTien.fill = GridBagConstraints.BOTH;
		gbc_lblTongTien.insets = new Insets(0, 0, 15, 5);
		gbc_lblTongTien.gridwidth = 2;
		gbc_lblTongTien.gridx = 2;
		gbc_lblTongTien.gridy = 4;
		add(lblTongTien, gbc_lblTongTien);
		lblTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtTongTien = new JTextField();
		GridBagConstraints gbc_txtTongTien = new GridBagConstraints();
		gbc_txtTongTien.fill = GridBagConstraints.BOTH;
		gbc_txtTongTien.insets = new Insets(0, 0, 15, 5);
		gbc_txtTongTien.gridx = 3;
		gbc_txtTongTien.gridy = 4;
		add(txtTongTien, gbc_txtTongTien);
		txtTongTien.setEnabled(false);
		txtTongTien.setColumns(10);
		
		lblVND = new JLabel("VND");
		GridBagConstraints gbc_lblVND = new GridBagConstraints();
		gbc_lblVND.anchor = GridBagConstraints.WEST;
		gbc_lblVND.fill = GridBagConstraints.VERTICAL;
		gbc_lblVND.insets = new Insets(0, 0, 15, 0);
		gbc_lblVND.gridx = 4;
		gbc_lblVND.gridy = 4;
		add(lblVND, gbc_lblVND);
		lblVND.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JPanel buttonPanel_1 = new JPanel();
		buttonPanel_1.setOpaque(false);
		GridBagConstraints gbc_buttonPanel_1 = new GridBagConstraints();
		gbc_buttonPanel_1.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel_1.gridwidth = 5;
		gbc_buttonPanel_1.gridx = 0;
		gbc_buttonPanel_1.gridy = 5;
		add(buttonPanel_1, gbc_buttonPanel_1);
		buttonPanel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		btnLap = new JButton("LẬP PHIẾU");
		btnLap.setPreferredSize(new Dimension(120, 30));
		btnLap.setBackground(new Color(255, 255, 255));
		btnLap.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = PhieuNhapBUS.kiemTra(cbbNCC.getSelectedItem().toString(), danhSachNhap.size());
				if (!result.isEmpty()) {
					MessageBox.loi(result);
					return;
				}
				
				result = PhieuNhapBUS.insert(new PhieuNhap("", txtNgayNhap.getText(), cbbNCC.getItemAt(cbbNCC.getSelectedIndex()).getMaNCC(), 
						Long.parseLong(txtTongTien.getText()), maNV), danhSachNhap);
				if (result.isEmpty()) {
					MessageBox.loi("Lập phiếu nhập thành công");
					danhSachNhap = new LinkedList<>();
					PhieuNhapBUS.load(); model.setRowCount(0); clear();
				}
				else MessageBox.loi(result);
			}
		});
		buttonPanel_1.add(btnLap);
		
		btnClear = new JButton("HỦY BỎ");
		btnClear.setBackground(new Color(255, 255, 255));
		btnClear.setPreferredSize(new Dimension(120, 30));
		btnClear.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		buttonPanel_1.add(btnClear);	
		
		loadNCC();
		loadSP();
		repainting();
	}
	
	private void loadNCC() {
		for (NhaCungCap ncc: NhaCungCapBUS.getDanhSachNCC()) nccModel.addElement(ncc);
		AutoCompleteDecorator.decorate(cbbNCC);
	}
	
	private void clear() {
		cbbNCC.setSelectedIndex(0);
		txtTongTien.setText("");
		clearSP();
	}
	
	
	//Form chọn sản phẩm
	private void clearSP() {
		cbbSanPham.setSelectedIndex(0);
		txtDonGia.setText("");
		txtSoLuong.setText("");
	}
	
	private int search(String maSP) {
		for (int i=0; i<model.getRowCount(); i++)
			if (model.getValueAt(i, 0).equals(maSP)) return i;
		return -1;
	}
	
	private boolean kiemTra() {
		String result = SanPhamCheck.DonGia(txtDonGia.getText());
		if (result.isEmpty() == false) {
			MessageBox.loi(result);
			return true;
		}
		
		result = SanPhamCheck.SoLuong(txtSoLuong.getText());
		if (result.isEmpty() == false) {
			MessageBox.loi(result);
			return true;
		}
		
		return false;
	}
	
	private void them() {
		if (kiemTra()) return;		
		String maSP = cbbSanPham.getSelectedItem().toString().split(" - ")[1];
		String tenSP = cbbSanPham.getSelectedItem().toString().split(" - ")[0];
		int index = search(maSP);
		if (index != -1) {
			int soLuongMoi = Integer.parseInt(danhSachNhap.get(index).getSoLuong()) + Integer.parseInt(txtSoLuong.getText());
			danhSachNhap.get(index).setSoLuong(Integer.toString(soLuongMoi));
			model.setValueAt(soLuongMoi, index, 3);
			
			MessageBox.thanhCong("Cập nhật sản phẩm thành công");
		}
		else {
			danhSachNhap.add(new ChiTiet("", cbbSanPham.getSelectedItem().toString().split(" - ")[1], txtDonGia.getText(), txtSoLuong.getText()));
			model.addRow(new Object[] {maSP, tenSP, txtDonGia.getText(), txtSoLuong.getText()});
		}
		setTongTien(); Redesign.Table(table); clearSP();
	}
	
	private void sua() {
		if (kiemTra()) return;
		String maSP = cbbSanPham.getSelectedItem().toString().split(" - ")[1];
		int index = search(maSP);
		if (index != -1) {
			danhSachNhap.get(index).setDonGia(txtDonGia.getText());
			model.setValueAt(txtDonGia.getText(), index, 2);
			
			danhSachNhap.get(index).setSoLuong(txtSoLuong.getText());
			model.setValueAt(txtSoLuong.getText(), index, 3);
			
			MessageBox.thanhCong("Cập nhật sản phẩm thành công");
			setTongTien(); Redesign.Table(table); clearSP();
		}
		else MessageBox.loi("Sản phẩm này chưa được thêm");
	}
	
	private void xoa() {
		String maSP = cbbSanPham.getSelectedItem().toString().split(" - ")[1];
		int index = search(maSP);
		if (index != -1) {
			danhSachNhap.remove(index);
			MessageBox.thanhCong("Xóa sản phẩm thành công");
			model.removeRow(index);
			setTongTien(); Redesign.Table(table); clearSP();
		}
		else MessageBox.loi("Sản phẩm này chưa được thêm");
	}
	
	private void loadSP() {
		for (SanPham sp: LaptopBUS.getDanhSachLaptop()) {
			if (sp.getTrangThai() != 0 && sp.getTrangThai() != 3) {
				danhSachSP.add(sp);
				spModel.addElement(sp.getTenSP() + " - " + sp.getMaSP());
			}
		}
		for (SanPham sp: PhuKienBUS.getDanhSachSP()) {
			if (sp.getTrangThai() != 0) {
				danhSachSP.add(sp);
				spModel.addElement(sp.getTenSP() + " - " + sp.getMaSP());
			}
		}
		AutoCompleteDecorator.decorate(cbbSanPham);
	}
	
	private String tinhTongTien() {
		long tongTien = 0;
		for (ChiTiet ct: danhSachNhap)
			tongTien = tongTien + Long.parseLong(ct.getDonGia()) * Integer.parseInt(ct.getSoLuong());
		return tongTien == 0 ? "" : Long.toString(tongTien);
	}
	
	void setTongTien() {
		txtTongTien.setText(tinhTongTien());
	}
	
	void repainting() {
		setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(table);
		Redesign.GroupBox(productPanel, " Thông tin sản phẩm: ");
		
		lblTitle.setForeground(ThemeColor.TEXT);
		lblNgayNhap.setForeground(ThemeColor.TEXT);
		lblTongTien.setForeground(ThemeColor.TEXT);
		lblVND.setForeground(ThemeColor.TEXT);
		lblNCC.setForeground(ThemeColor.TEXT);
		lblSanPham.setForeground(ThemeColor.TEXT);
		lblDonGia.setForeground(ThemeColor.TEXT);
		lblSoLuong.setForeground(ThemeColor.TEXT);
		
		Redesign.TextField(txtNgayNhap);
		Redesign.TextField(txtTongTien);
		Redesign.ComboBox(cbbNCC);
		Redesign.ComboBox(cbbSanPham);
		Redesign.TextField(txtDonGia);
		Redesign.TextField(txtSoLuong);
		
		Redesign.Button(btnThem);
		Redesign.Button(btnSua);
		Redesign.Button(btnXoa);
		Redesign.Button(btnHuyBo);
		
		Redesign.SubmitButton(btnLap);
		Redesign.SubmitButton(btnClear);
		
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);		
	}
}
