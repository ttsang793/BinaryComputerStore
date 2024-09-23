package GUI;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.LinkedList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import DTO.ChiTiet;
import DTO.SanPham;
import BUS.LaptopBUS;
import BUS.PhuKienBUS;
import BUS.SanPhamCheck;
import BUS.PhieuNhapBUS;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

class ChonSP extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblSanPham;
	private JLabel lblDonGia;
	private JLabel lblSoLuong;
	private JComboBox<SanPham> cbbSanPham;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Mã SP", "Tên SP", "Đơn giá", "Số lượng"});
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnHuyBo;
	private LapDonHangGUI donHangPanel;
	private LinkedList<SanPham> danhSachSP = new LinkedList<>();
	private LinkedList<ChiTiet> danhSachNhap = new LinkedList<>();
	private JPanel buttonPanel;
	private JButton btnTonKho;

	/**
	 * Create the frame.
	 */
	public ChonSP(LapDonHangGUI donHangPanel) {
		this.donHangPanel = donHangPanel;
		setBackground(ThemeColor.LIGHT_1);
		setBounds(0, 0, 700, 560);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{90, 0, 0};
		gridBagLayout.rowHeights = new int[]{40, 40, 40, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblSanPham = new JLabel("Sản phẩm:");
		lblSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSanPham = new GridBagConstraints();
		gbc_lblSanPham.fill = GridBagConstraints.BOTH;
		gbc_lblSanPham.insets = new Insets(0, 0, 15, 5);
		gbc_lblSanPham.gridx = 0;
		gbc_lblSanPham.gridy = 0;
		this.add(lblSanPham, gbc_lblSanPham);
		
		cbbSanPham = new JComboBox<SanPham>(spModel);
		cbbSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (donHangPanel instanceof LapDonHangGUI) {
					txtDonGia.setText(danhSachSP.get(cbbSanPham.getSelectedIndex()).getDonGia());
				}
			}
		});
		cbbSanPham.setBackground(new Color(255,255,255));
		cbbSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_cbbSanPham = new GridBagConstraints();
		gbc_cbbSanPham.fill = GridBagConstraints.BOTH;
		gbc_cbbSanPham.insets = new Insets(0, 0, 15, 5);
		gbc_cbbSanPham.gridx = 1;
		gbc_cbbSanPham.gridy = 0;
		this.add(cbbSanPham, gbc_cbbSanPham);
		
		btnTonKho = new JButton("Xem tồn kho");
		btnTonKho.setBackground(new Color(255, 255, 255));
		btnTonKho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPham sp = danhSachSP.get(cbbSanPham.getSelectedIndex());
				String info = "";
				switch (sp.getTrangThai()) {
					case 0: info = "Sản phẩm ngừng kinh doanh"; break;
					case 1: {
						if (Integer.parseInt(sp.getSoLuong()) > 0) info = "Sản phẩm có tồn kho là " + sp.getSoLuong();
						else info = "Sản phẩm đang hết hàng";
						break;
					}
					case 2: info = "Sản phẩm sắp kinh doanh"; break;
					case 3: info = "Sản phẩm đặc biệt, tồn kho không giới hạn"; break;
					default: info = "Có lỗi xảy ra";
				}
				MessageBox.thongBao(info);
			}
		});
		btnTonKho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnTonKho = new GridBagConstraints();
		gbc_btnTonKho.anchor = GridBagConstraints.NORTH;
		gbc_btnTonKho.insets = new Insets(0, 0, 15, 0);
		gbc_btnTonKho.gridx = 2;
		gbc_btnTonKho.gridy = 0;
		add(btnTonKho, gbc_btnTonKho);
		
		lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDonGia = new GridBagConstraints();
		gbc_lblDonGia.fill = GridBagConstraints.BOTH;
		gbc_lblDonGia.insets = new Insets(0, 0, 15, 5);
		gbc_lblDonGia.gridx = 0;
		gbc_lblDonGia.gridy = 1;
		this.add(lblDonGia, gbc_lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		GridBagConstraints gbc_txtDonGia = new GridBagConstraints();
		gbc_txtDonGia.gridwidth = 2;
		gbc_txtDonGia.fill = GridBagConstraints.BOTH;
		gbc_txtDonGia.insets = new Insets(0, 0, 15, 0);
		gbc_txtDonGia.gridx = 1;
		gbc_txtDonGia.gridy = 1;
		this.add(txtDonGia, gbc_txtDonGia);
		
		lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSoLuong = new GridBagConstraints();
		gbc_lblSoLuong.fill = GridBagConstraints.BOTH;
		gbc_lblSoLuong.insets = new Insets(0, 0, 15, 5);
		gbc_lblSoLuong.gridx = 0;
		gbc_lblSoLuong.gridy = 2;
		this.add(lblSoLuong, gbc_lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSoLuong.setColumns(10);
		GridBagConstraints gbc_txtSoLuong = new GridBagConstraints();
		gbc_txtSoLuong.gridwidth = 2;
		gbc_txtSoLuong.fill = GridBagConstraints.BOTH;
		gbc_txtSoLuong.insets = new Insets(0, 0, 15, 0);
		gbc_txtSoLuong.gridx = 1;
		gbc_txtSoLuong.gridy = 2;
		this.add(txtSoLuong, gbc_txtSoLuong);
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setVgap(0);
		fl_buttonPanel.setHgap(10);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPanel.gridwidth = 3;
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 3;
		add(buttonPanel, gbc_buttonPanel);
		
		btnThem = new JButton("THÊM");
		btnThem.setPreferredSize(new Dimension(120, 30));
		buttonPanel.add(btnThem);
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				them();
			}
		});
		
		btnSua = new JButton("SỬA");
		btnSua.setPreferredSize(new Dimension(120, 30));
		buttonPanel.add(btnSua);
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sua();
			}
		});
		
		btnXoa = new JButton("XÓA");
		btnXoa.setPreferredSize(new Dimension(120, 30));
		buttonPanel.add(btnXoa);
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoa();
			}
		});		
		
		btnHuyBo = new JButton("HỦY BỎ");
		btnHuyBo.setPreferredSize(new Dimension(120, 30));
		buttonPanel.add(btnHuyBo);
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				danhSachNhap.removeAll(danhSachNhap);
				model.setRowCount(0);
				setTongTien();
				clear();
			}
		});
		btnHuyBo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnHuyBo.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		gbc_scrollPane.insets = new Insets(20, 0, 0, 0);
		this.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model);
		Redesign.Table(table);		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				cbbSanPham.setSelectedItem(searchList(table.getValueAt(row, 0).toString()));
				txtDonGia.setText(table.getValueAt(row, 2).toString());
				txtSoLuong.setText(table.getValueAt(row, 3).toString());
			}
		});
		scrollPane.setViewportView(table);
		loadSP();
	}
	
	private void clear() {
		cbbSanPham.setSelectedIndex(0);
		txtSoLuong.setText("");
	}
	
	private int search(String maSP) {
		for (int i=0; i<model.getRowCount(); i++)
			if (model.getValueAt(i, 0).equals(maSP)) return i;
		return -1;
	}
	
	private SanPham searchList(String maSP) {
		for (SanPham sp: danhSachSP) if (sp.getMaSP().equals(maSP)) return sp;
		return null;
	}
	
	private boolean kiemTra() {
		if (danhSachSP.get(cbbSanPham.getSelectedIndex()).getTrangThai() == 0) {
			MessageBox.loi("Sản phẩm đang không kinh doanh, không thể thực hiện");
			return true;				
		}
		
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
		
		if (danhSachSP.get(cbbSanPham.getSelectedIndex()).getTrangThai() == 2) {
			MessageBox.loi("Sản phẩm đang không kinh doanh, không thể thực hiện");
			return true;				
		}
		
		int tonKho = Integer.parseInt(danhSachSP.get(cbbSanPham.getSelectedIndex()).getSoLuong());
		if (Integer.parseInt(txtSoLuong.getText()) > tonKho && danhSachSP.get(cbbSanPham.getSelectedIndex()).getTrangThai() == 1) {
			MessageBox.loi("Quá tồn kho, không thể thêm sản phẩm");
			return true;				
		}
		
		return false;
	}
	
	private void them() {
		if (kiemTra()) return;
		SanPham sp = cbbSanPham.getItemAt(cbbSanPham.getSelectedIndex());
		
		int index = search(sp.getMaSP());
		if (index != -1) {
			int soLuongMoi = Integer.parseInt(danhSachNhap.get(index).getSoLuong()) + Integer.parseInt(txtSoLuong.getText());
			danhSachNhap.get(index).setSoLuong(Integer.toString(soLuongMoi));
			model.setValueAt(soLuongMoi, index, 3);
			
			MessageBox.thanhCong("Cập nhật sản phẩm thành công");
		}
		else {
			danhSachNhap.add(new ChiTiet("", cbbSanPham.getSelectedItem().toString().split(" - ")[1], txtDonGia.getText(), txtSoLuong.getText()));
			model.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), txtDonGia.getText(), txtSoLuong.getText()});
		}
		setTongTien(); Redesign.Table(table); clear();
	}
	
	private void sua() {
		if (kiemTra()) return;
		String maSP = cbbSanPham.getItemAt(cbbSanPham.getSelectedIndex()).getMaSP();
		int index = search(maSP);
		if (index != -1) {
			danhSachNhap.get(index).setDonGia(txtDonGia.getText());
			model.setValueAt(txtDonGia.getText(), index, 2);
			
			danhSachNhap.get(index).setSoLuong(txtSoLuong.getText());
			model.setValueAt(txtSoLuong.getText(), index, 3);
			
			MessageBox.thanhCong("Cập nhật sản phẩm thành công");
			setTongTien(); Redesign.Table(table); clear();
		}
		else MessageBox.loi("Sản phẩm này chưa được thêm");
	}
	
	private void xoa() {
		String maSP = cbbSanPham.getItemAt(cbbSanPham.getSelectedIndex()).getMaSP();
		int index = search(maSP);
		if (index != -1) {
			danhSachNhap.remove(index);
			MessageBox.thanhCong("Xóa sản phẩm thành công");
			model.removeRow(index);
			setTongTien(); Redesign.Table(table); clear();
		}
		else MessageBox.loi("Sản phẩm này chưa được thêm");
	}
	
	private String tinhTongTien() {
		long tongTien = 0;
		for (ChiTiet ct: danhSachNhap)
			tongTien = tongTien + Long.parseLong(ct.getDonGia()) * Integer.parseInt(ct.getSoLuong());
		return tongTien == 0 ? "" : Long.toString(tongTien);
	}
	
	void setTongTien() {
		donHangPanel.setTongTien(tinhTongTien());
	}
	
	public LinkedList<ChiTiet> getChiTiet() {
		return danhSachNhap;
	}
	
	private DefaultComboBoxModel<SanPham> spModel = new DefaultComboBoxModel<>(); 
	
	private void loadSP() {
		for (SanPham sp: LaptopBUS.getDanhSachLaptop()) {
			danhSachSP.add(sp);
			spModel.addElement(sp);
		}
		for (SanPham sp: PhuKienBUS.getDanhSachSP()) {
			danhSachSP.add(sp);
			spModel.addElement(sp);
		}
	}
	
	void setModel(String maPN) {
		LinkedList<ChiTiet> chiTiet = PhieuNhapBUS.getChiTietMaPN(maPN);
		model.setRowCount(0);
		Object[] data = new Object[model.getColumnCount()];
		
		if (chiTiet != null)
			for (ChiTiet ct: chiTiet) {
				data[0] = ct.getMaSP(); data[1] = ct.getTenSP(); data[2] = ct.getDonGia(); data[3] = ct.getSoLuong();
				for (int i=0; i<model.getColumnCount(); i++) if (data[i] == null) data[i] = "";
				model.addRow(data);
			}
	}
	
	void repainting() {
		setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(table);
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		
		lblSanPham.setForeground(ThemeColor.TEXT);
		lblDonGia.setForeground(ThemeColor.TEXT);
		lblSoLuong.setForeground(ThemeColor.TEXT);
		
		Redesign.ComboBox(cbbSanPham);
		Redesign.Button(btnTonKho);
		Redesign.TextField(txtDonGia);
		Redesign.TextField(txtSoLuong);
		
		Redesign.SubmitButton(btnThem);
		Redesign.SubmitButton(btnSua);
		Redesign.SubmitButton(btnXoa);
		Redesign.SubmitButton(btnHuyBo);
	}
}
