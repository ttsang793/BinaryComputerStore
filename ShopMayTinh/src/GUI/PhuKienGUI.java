package GUI;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.PhuKienBUS;
import DTO.SanPham;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.FlowLayout;

class PhuKienGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel infoPanel;
	private JScrollPane scrollPane;
	private JLabel lblPic;
	private JTable table;
	private JFileChooser imageChooser;
	private LinkedList<SanPham> sanPham = new LinkedList<>();

	private JLabel lblTitle;
	private JLabel lblMaSP;
	private JLabel lblTenSP;
	private JLabel lblLoaiSP;
	private JLabel lblDonGia;
	private JLabel lblTinhTrang;
	private JLabel lblHinhAnh;
	
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JComboBox<String> cbbLoaiSP;
	private JTextField txtDonGia;
	private JComboBox<String> cbbTinhTrang;
	
	private JButton btnHinhAnh;
	private JButton btnLuu;
	private JButton btnClear;
	
	private JLabel lblErrorTenSP;
	private JLabel lblErrorLoaiSP;
	private JLabel lblErrorDonGia;
	private JLabel lblErrorHinhAnh;
	private LinkedList<JLabel> errorLabels = new LinkedList<>();
	
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "Mã SP", "Tên SP", "Loại SP", "Đơn giá", "Tồn kho", "" });
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public PhuKienGUI() {
		setBounds(0, 0, 1200, 600);
		setBorder(new EmptyBorder(20, 0, 10, 0));
		load();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[]{0.4, 0.75};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_infoPanel.anchor = GridBagConstraints.NORTH;
		gbc_infoPanel.insets = new Insets(0, 0, 0, 30);
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 0;
		this.add(infoPanel, gbc_infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{80, 160, 0};
		gbl_infoPanel.rowHeights = new int[]{30, 10, 25, 30, 25, 30, 25, 30, 25, 30, 150, 30, 25, 25, 30};
		gbl_infoPanel.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		lblTitle = new JLabel("QUẢN LÝ PHỤ KIỆN");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 10, 0);
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		infoPanel.add(lblTitle, gbc_lblTitle);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMaSP = new JLabel("Mã SP:");
		lblMaSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMaSP = new GridBagConstraints();
		gbc_lblMaSP.fill = GridBagConstraints.BOTH;
		gbc_lblMaSP.insets = new Insets(0, 0, 0, 0);
		gbc_lblMaSP.gridx = 0;
		gbc_lblMaSP.gridy = 2;
		infoPanel.add(lblMaSP, gbc_lblMaSP);
		
		txtMaSP = new JTextField();
		GridBagConstraints gbc_txtMaSP = new GridBagConstraints();
		gbc_txtMaSP.anchor = GridBagConstraints.NORTH;
		gbc_txtMaSP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMaSP.insets = new Insets(0, 0, 0, 0);
		gbc_txtMaSP.gridwidth = 2;
		gbc_txtMaSP.gridx = 1;
		gbc_txtMaSP.gridy = 2;
		infoPanel.add(txtMaSP, gbc_txtMaSP);
		txtMaSP.setEnabled(false);
		txtMaSP.setColumns(10);
		
		lblTenSP = new JLabel("Tên SP:");
		lblTenSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTenSP = new GridBagConstraints();
		gbc_lblTenSP.fill = GridBagConstraints.BOTH;
		gbc_lblTenSP.insets = new Insets(0, 0, 0, 0);
		gbc_lblTenSP.gridx = 0;
		gbc_lblTenSP.gridy = 4;
		infoPanel.add(lblTenSP, gbc_lblTenSP);
		
		txtTenSP = new JTextField();
		GridBagConstraints gbc_txtTenSP = new GridBagConstraints();
		gbc_txtTenSP.anchor = GridBagConstraints.NORTH;
		gbc_txtTenSP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTenSP.insets = new Insets(0, 0, 0, 0);
		gbc_txtTenSP.gridwidth = 2;
		gbc_txtTenSP.gridx = 1;
		gbc_txtTenSP.gridy = 4;
		infoPanel.add(txtTenSP, gbc_txtTenSP);
		txtTenSP.setColumns(10);
		
		lblErrorTenSP = new JLabel("");
		lblErrorTenSP.setForeground(new Color(250, 0, 0));
		lblErrorTenSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorTenSP = new GridBagConstraints();
		gbc_lblErrorTenSP.fill = GridBagConstraints.BOTH;
		gbc_lblErrorTenSP.insets = new Insets(0, 0, 10, 0);
		gbc_lblErrorTenSP.gridwidth = 2;
		gbc_lblErrorTenSP.gridx = 1;
		gbc_lblErrorTenSP.gridy = 5;
		infoPanel.add(lblErrorTenSP, gbc_lblErrorTenSP);
		
		lblLoaiSP = new JLabel("Loại SP:");
		lblLoaiSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLoaiSP = new GridBagConstraints();
		gbc_lblLoaiSP.fill = GridBagConstraints.BOTH;
		gbc_lblLoaiSP.insets = new Insets(0, 0, 0, 0);
		gbc_lblLoaiSP.gridx = 0;
		gbc_lblLoaiSP.gridy = 6;
		infoPanel.add(lblLoaiSP, gbc_lblLoaiSP);
		
		cbbLoaiSP = new JComboBox<>();
		GridBagConstraints gbc_cbbLoaiSP = new GridBagConstraints();
		gbc_cbbLoaiSP.fill = GridBagConstraints.BOTH;
		gbc_cbbLoaiSP.insets = new Insets(0, 0, 0, 0);
		gbc_cbbLoaiSP.gridwidth = 2;
		gbc_cbbLoaiSP.gridx = 1;
		gbc_cbbLoaiSP.gridy = 6;
		infoPanel.add(cbbLoaiSP, gbc_cbbLoaiSP);
		cbbLoaiSP.setModel(new DefaultComboBoxModel<>(new String[] {"Chọn loại sản phẩm", "Bàn phím", "Chuột", "Phần mềm", "Vệ sinh"}));
		cbbLoaiSP.setSelectedIndex(0);
		
		lblErrorLoaiSP = new JLabel("");
		lblErrorLoaiSP.setForeground(Color.RED);
		lblErrorLoaiSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorLoaiSP = new GridBagConstraints();
		gbc_lblErrorLoaiSP.fill = GridBagConstraints.BOTH;
		gbc_lblErrorLoaiSP.insets = new Insets(0, 0, 10, 0);
		gbc_lblErrorLoaiSP.gridwidth = 2;
		gbc_lblErrorLoaiSP.gridx = 1;
		gbc_lblErrorLoaiSP.gridy = 7;
		infoPanel.add(lblErrorLoaiSP, gbc_lblErrorLoaiSP);
		
		lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDonGia = new GridBagConstraints();
		gbc_lblDonGia.fill = GridBagConstraints.BOTH;
		gbc_lblDonGia.insets = new Insets(0, 0, 0, 0);
		gbc_lblDonGia.gridx = 0;
		gbc_lblDonGia.gridy = 8;
		infoPanel.add(lblDonGia, gbc_lblDonGia);
		
		txtDonGia = new JTextField();
		GridBagConstraints gbc_txtDonGia = new GridBagConstraints();
		gbc_txtDonGia.anchor = GridBagConstraints.NORTH;
		gbc_txtDonGia.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDonGia.insets = new Insets(0, 0, 0, 0);
		gbc_txtDonGia.gridwidth = 2;
		gbc_txtDonGia.gridx = 1;
		gbc_txtDonGia.gridy = 8;
		infoPanel.add(txtDonGia, gbc_txtDonGia);
		txtDonGia.setColumns(10);
		
		lblErrorDonGia = new JLabel("");
		lblErrorDonGia.setForeground(Color.RED);
		lblErrorDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorDonGia = new GridBagConstraints();
		gbc_lblErrorDonGia.fill = GridBagConstraints.BOTH;
		gbc_lblErrorDonGia.insets = new Insets(0, 0, 10, 0);
		gbc_lblErrorDonGia.gridwidth = 2;
		gbc_lblErrorDonGia.gridx = 1;
		gbc_lblErrorDonGia.gridy = 9;
		infoPanel.add(lblErrorDonGia, gbc_lblErrorDonGia);
		
		lblHinhAnh = new JLabel("Hình ảnh:");
		lblHinhAnh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblHinhAnh = new GridBagConstraints();
		gbc_lblHinhAnh.anchor = GridBagConstraints.NORTH;
		gbc_lblHinhAnh.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHinhAnh.insets = new Insets(0, 0, 0, 0);
		gbc_lblHinhAnh.gridx = 0;
		gbc_lblHinhAnh.gridy = 10;
		infoPanel.add(lblHinhAnh, gbc_lblHinhAnh);
		
		lblPic = new JLabel("");
		lblPic.setPreferredSize(new Dimension(150, 150));
		lblPic.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		GridBagConstraints gbc_lblPic = new GridBagConstraints();
		gbc_lblPic.fill = GridBagConstraints.BOTH;
		gbc_lblPic.insets = new Insets(0, 0, 0, 10);
		gbc_lblPic.gridx = 1;
		gbc_lblPic.gridy = 10;
		infoPanel.add(lblPic, gbc_lblPic);
		
		btnHinhAnh = new JButton("Upload");
		Redesign.SubmitButton(btnHinhAnh);
		btnHinhAnh.setPreferredSize(new Dimension(100,30));
		GridBagConstraints gbc_btnHinhAnh = new GridBagConstraints();
		gbc_btnHinhAnh.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnHinhAnh.insets = new Insets(0, 0, 0, 0);
		gbc_btnHinhAnh.gridx = 2;
		gbc_btnHinhAnh.gridy = 10;
		infoPanel.add(btnHinhAnh, gbc_btnHinhAnh);
		btnHinhAnh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				imageChooser = new JFileChooser();
				imageChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				imageChooser.setMultiSelectionEnabled(false);
				imageChooser.setDialogTitle("Chọn hình minh họa");
				int result = imageChooser.showOpenDialog(e.getComponent());
				if (result == JFileChooser.APPROVE_OPTION)
					lblPic.setIcon(new ImageIcon(new ImageIcon(imageChooser.getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
			}
		});
		
		lblErrorHinhAnh = new JLabel("");
		lblErrorHinhAnh.setForeground(Color.RED);
		lblErrorHinhAnh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorHinhAnh = new GridBagConstraints();
		gbc_lblErrorHinhAnh.fill = GridBagConstraints.BOTH;
		gbc_lblErrorHinhAnh.insets = new Insets(0, 0, 10, 0);
		gbc_lblErrorHinhAnh.gridwidth = 2;
		gbc_lblErrorHinhAnh.gridx = 1;
		gbc_lblErrorHinhAnh.gridy = 11;
		infoPanel.add(lblErrorHinhAnh, gbc_lblErrorHinhAnh);
		
		lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTinhTrang = new GridBagConstraints();
		gbc_lblTinhTrang.fill = GridBagConstraints.BOTH;
		gbc_lblTinhTrang.insets = new Insets(0, 0, 0, 0);
		gbc_lblTinhTrang.gridx = 0;
		gbc_lblTinhTrang.gridy = 12;
		infoPanel.add(lblTinhTrang, gbc_lblTinhTrang);
		
		cbbTinhTrang = new JComboBox<>();
		cbbTinhTrang.setEnabled(false);
		cbbTinhTrang.setModel(new DefaultComboBoxModel<>(new String[] {"Ngừng kinh doanh", "Kinh doanh", "Sắp ra mắt", "Kinh doanh (Không có tồn kho)"}));
		cbbTinhTrang.setSelectedIndex(0);
		cbbTinhTrang.setPreferredSize(new Dimension(330, 25));
		cbbTinhTrang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbTinhTrang.setBackground(Color.WHITE);
		cbbTinhTrang.setSelectedIndex(2);
		GridBagConstraints gbc_cbbTinhTrang = new GridBagConstraints();
		gbc_cbbTinhTrang.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbTinhTrang.anchor = GridBagConstraints.NORTH;
		gbc_cbbTinhTrang.insets = new Insets(0, 0, 0, 0);
		gbc_cbbTinhTrang.gridwidth = 2;
		gbc_cbbTinhTrang.gridx = 1;
		gbc_cbbTinhTrang.gridy = 12;
		infoPanel.add(cbbTinhTrang, gbc_cbbTinhTrang);
		
		panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 14;
		infoPanel.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		btnLuu = new JButton("LƯU");
		Redesign.SubmitButton(btnLuu);
		btnLuu.setPreferredSize(new Dimension(120,30));
		panel.add(btnLuu);
		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SanPham sp = new SanPham(txtTenSP.getText(), cbbLoaiSP.getSelectedItem().toString(), "", txtDonGia.getText());
				String[] checking = PhuKienBUS.kiemTra(sp, lblPic.getIcon());

				if (checking.length > 0) {
					for (int i=0; i<errorLabels.size(); i++) errorLabels.get(i).setText(checking[i]);
					return;
				}
				
				if (txtMaSP.getText().isEmpty()) {	
					if (MessageBox.cauHoi("Bạn có muốn thêm sản phẩm này?") == JOptionPane.YES_OPTION) {
						String anh = (imageChooser == null) ? "" : imageChooser.getSelectedFile().getAbsolutePath();
						if (PhuKienBUS.insert(sp, lblPic.getIcon(), anh))
						{
							MessageBox.thanhCong("Thêm sản phẩm thành công");
							PhuKienBUS.load(); load(); clear();
						}
						else MessageBox.loi("Đã có lỗi xảy ra, thêm sản phẩm thất bại");
					}
				}
				else {
					if (MessageBox.cauHoi("Bạn có muốn cập nhật sản phẩm này?") == JOptionPane.YES_OPTION) {
						String anh = (imageChooser == null) ? "" : imageChooser.getSelectedFile().getAbsolutePath();
						if (PhuKienBUS.update(sp, lblPic.getIcon(), anh))
						{
							MessageBox.thanhCong("Cập nhật sản phẩm thành công");
							PhuKienBUS.load(); load(); clear();
						}
						else MessageBox.loi("Đã có lỗi xảy ra, cập nhật sản phẩm thất bại");
					}
				}
			}
		});
		
		btnClear = new JButton("HỦY BỎ");
		Redesign.SubmitButton(btnClear);
		btnClear.setPreferredSize(new Dimension(120,30));
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.gridheight = GridBagConstraints.REMAINDER;
		this.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SanPham sp = search(model.getValueAt(table.getSelectedRow(),0).toString());
				txtMaSP.setText(sp.getMaSP());
				txtTenSP.setText(sp.getTenSP());
				cbbLoaiSP.setSelectedItem(sp.getLoaiSP().toString());
				txtDonGia.setText(sp.getDonGia());
				ImageIcon anh = new ImageIcon(new ImageIcon(sp.getAnhSP()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				lblPic.setIcon(anh);
				cbbTinhTrang.setSelectedIndex(sp.getTrangThai());
				cbbTinhTrang.setEnabled(true);
				clearError();
			}
		});
		scrollPane.setViewportView(table);
		
		initError();
		repainting();
	}
	
	private void load() {
		sanPham = PhuKienBUS.getDanhSachSP();
		model.setRowCount(0);
		Object[] data = new Object[model.getColumnCount()];
		
		if (sanPham != null)
			for (SanPham sp: sanPham) {
				data[0] = sp.getMaSP(); data[1] = sp.getTenSP(); data[2] = sp.getLoaiSP(); data[3] = sp.getDonGia();
				data[4] = sp.getSoLuong(); data[5] = sp.getTrangThai();
				for (int i=0; i<model.getColumnCount(); i++) if (data[i] == null) data[i] = "";
				model.addRow(data);
			}
	}
	
	private void clear() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		cbbLoaiSP.setSelectedIndex(0);
		cbbTinhTrang.setEnabled(false);
		cbbTinhTrang.setSelectedIndex(2);
		txtDonGia.setText("");
		lblPic.setIcon(null);
		clearError();
	}
	
	private void clearError() {
		for (JLabel label: errorLabels) label.setText("");
	}
	
	private SanPham search(String maSP) {
		for (SanPham sp: sanPham) if (sp.getMaSP() == maSP) return sp;
		return null;
	}
	
	private void initError() {
		errorLabels.add(lblErrorTenSP);
		errorLabels.add(lblErrorLoaiSP);
		errorLabels.add(lblErrorDonGia);
		errorLabels.add(lblErrorHinhAnh);
	}
	
	void repainting() {
		this.setBackground(ThemeColor.LIGHT_1);
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(table);
		
		lblTitle.setForeground(ThemeColor.TEXT);
		lblMaSP.setForeground(ThemeColor.TEXT);
		lblTenSP.setForeground(ThemeColor.TEXT);
		lblLoaiSP.setForeground(ThemeColor.TEXT);
		lblDonGia.setForeground(ThemeColor.TEXT);
		lblTinhTrang.setForeground(ThemeColor.TEXT);
		lblHinhAnh.setForeground(ThemeColor.TEXT);
		
		Redesign.TextField(txtMaSP);
		Redesign.TextField(txtTenSP);
		Redesign.ComboBox(cbbLoaiSP);
		Redesign.TextField(txtDonGia);
		Redesign.ComboBox(cbbTinhTrang);

		Redesign.SubmitButton(btnHinhAnh);
		Redesign.SubmitButton(btnLuu);
		Redesign.SubmitButton(btnClear);
	}
}
