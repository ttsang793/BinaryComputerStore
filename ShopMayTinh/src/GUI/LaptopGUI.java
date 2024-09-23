package GUI;

import java.util.LinkedList;
import BUS.LaptopBUS;
import DTO.Laptop;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;

class LaptopGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private LinkedList<Laptop> laptop = new LinkedList<>();
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Mã SP", "Tên SP", "Đơn giá", "Tồn kho",  ""});
	private DefaultComboBoxModel<String> trangThai = new DefaultComboBoxModel<>(new String[] {"Ngừng kinh doanh", "Kinh doanh", "Sắp ra mắt"});
	private DefaultComboBoxModel<String> thuongHieu = new DefaultComboBoxModel<>(new String[] {"Chọn thương hiệu", "Asus", "Acer", "Dell", "HP", "Macbook (Apple)"});
	private DefaultComboBoxModel<String> loaiOCung = new DefaultComboBoxModel<>(new String[] {"Chọn loại ổ cứng", "HDD", "SSD", "SSD M.2 PCIe", "SSD M.2 NVMe"});

	private JLabel lblTitle;
	private JLabel lblMaSP;
	private JTextField txtMaSP;
	private JLabel lblTrangThai;
	private JComboBox<String> cbbTrangThai;
	private JLabel lblDonGia;
	private JTextField txtDonGia;
	private JLabel lblTenSP;
	private JTextField txtTenSP;
	private JLabel lblNamRaMat;
	private JTextField txtNamRaMat;
	private JLabel lblTrongLuong;
	private JTextField txtTrongLuong;	
	private JLabel lblMauSac;
	private JTextField txtMauSac;
	private JLabel lblChatLieu;
	private JTextField txtChatLieu;
	private JLabel lblCPU;
	private JTextField txtCPU;
	private JLabel lblRAM;
	private JTextField txtRAM;
	private JLabel lblNangCapRAM;
	private JTextField txtNangCapRAM;
	private JLabel lblManHinh;
	private JTextField txtManHinh;
	private JLabel lblDoPhanGiai;
	private JTextField txtChieuDai;
	private JLabel lblTanSo;
	private JTextField txtTanSo;
	private JLabel lblCongNghe;
	private JTextField txtCongNghe;
	private JLabel lblTamNen;
	private JTextField txtTamNen;
	private JLabel lblGPU;
	private JTextField txtGPU;
	private JLabel lblBaoMat;
	private JTextField txtBaoMat;
	private JLabel lblBluetooth;
	private JTextField txtBluetooth;
	private JLabel lblPin;
	private JTextField txtPin;
	private JLabel lblOS;
	private JTextField txtOS;
	private JLabel lblOCung;
	private JTextField txtDungLuong;
	private JComboBox<String> cbbLoaiOCung;
	private JLabel lblThuongHieu;
	private JComboBox<String> cbbThuongHieu;
	private JButton btnHinhAnh;
	private JButton btnLuu;
	private JButton btnClear;
	private JLabel lblPic;
	private JFileChooser imageChooser;
	private LinkedList<JTextField> listTextfield = new LinkedList<>();
	private LinkedList<JLabel> listLabel = new LinkedList<>();
	private JPanel productPanel;
	private JScrollPane scrollNhapLieu;
	private JPanel infoPanel;
	private JPanel panelChung;
	private JPanel panelKyThuat;
	private JPanel panelManHinh;
	private JTextField txtChieuCao;
	private JScrollPane scrollPane;
	private JRadioButton rdbKhong;
	
	private LinkedList<JLabel> errorLabels = new LinkedList<>();
	private JLabel lblErrorDonGia;
	private JLabel lblErrorTenSP;
	private JLabel lblErrorHinhAnh;
	private JLabel lblErrorTrongLuong;
	private JLabel lblErrorNamRaMat;
	private JLabel lblErrorMauSac;
	private JLabel lblErrorChatLieu;
	private JLabel lblErrorOS;
	private JLabel lblErrorThuongHieu;
	private JLabel lblErrorBaoMat;
	private JLabel lblErrorBluetooth;
	private JLabel lblErrorPin;
	private JLabel lblErrorCPU;
	private JLabel lblErrorRAM;
	private JLabel lblErrorNangCapRAM;
	private JLabel lblErrorGPU;
	private JLabel lblErrorOCung;
	private JLabel lblErrorManHinh;
	private JLabel lblErrorTanSo;
	private JLabel lblErrorDoPhanGiai;
	private JLabel lblErrorCongNghe;
	private JLabel lblErrorTamNen;

	/**
	 * Create the frame.
	 */
	public LaptopGUI() {
		setBounds(0, 0, 1200, 1800);
		load();
		this.setBorder(new EmptyBorder(20, 0, 20, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 700};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_infoPanel.insets = new Insets(0, 0, 0, 10);
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 0;
		add(infoPanel, gbc_infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] {0};
		gbl_infoPanel.rowHeights = new int[] {40, 0, 40};
		gbl_infoPanel.columnWeights = new double[]{1.0};
		gbl_infoPanel.rowWeights = new double[]{0.0, 1.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		lblTitle = new JLabel("QUẢN LÝ LAPTOP");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTitle.insets = new Insets(0, 0, 0, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		infoPanel.add(lblTitle, gbc_lblTitle);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollNhapLieu = new JScrollPane();
		scrollNhapLieu.getVerticalScrollBar().setUnitIncrement(16);
		scrollNhapLieu.setBorder(new EmptyBorder(0,0,0,0));
		GridBagConstraints gbc_scrollNhapLieu = new GridBagConstraints();
		gbc_scrollNhapLieu.fill = GridBagConstraints.BOTH;
		gbc_scrollNhapLieu.insets = new Insets(0, 0, 0, 0);
		gbc_scrollNhapLieu.gridx = 0;
		gbc_scrollNhapLieu.gridy = 1;
		infoPanel.add(scrollNhapLieu, gbc_scrollNhapLieu);
		
		productPanel = new JPanel();
		scrollNhapLieu.setViewportView(productPanel);
		productPanel.setBorder(new EmptyBorder(0, 0, 10, 15));
		GridBagLayout gbl_productPanel = new GridBagLayout();
		gbl_productPanel.columnWidths = new int[]{0, 150};
		gbl_productPanel.rowHeights = new int[]{25, 50, 25, 50, 25, 25, 25, 25, 25, 25, 0, 0, 0, 0};
		gbl_productPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_productPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		productPanel.setLayout(gbl_productPanel);
		
		lblMaSP = new JLabel("Mã SP:");
		lblMaSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMaSP = new GridBagConstraints();
		gbc_lblMaSP.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMaSP.insets = new Insets(0, 0, 0, 10);
		gbc_lblMaSP.gridx = 0;
		gbc_lblMaSP.gridy = 0;
		productPanel.add(lblMaSP, gbc_lblMaSP);
		
		lblPic = new JLabel("");
		lblPic.setPreferredSize(new Dimension(150,150));
		lblPic.setBorder(new LineBorder(new Color(0,0,0),1));
		lblPic.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPic.setBackground(new Color(255, 0, 128));
		lblPic.setIcon(null);
		GridBagConstraints gbc_lblPic = new GridBagConstraints();
		gbc_lblPic.fill = GridBagConstraints.BOTH;
		gbc_lblPic.insets = new Insets(0, 0, 0, 0);
		gbc_lblPic.gridheight = 4;
		gbc_lblPic.gridx = 1;
		gbc_lblPic.gridy = 1;
		productPanel.add(lblPic, gbc_lblPic);
		
		txtMaSP = new JTextField();
		txtMaSP.setEnabled(false);
		txtMaSP.setColumns(10);
		GridBagConstraints gbc_txtMaSP = new GridBagConstraints();
		gbc_txtMaSP.fill = GridBagConstraints.BOTH;
		gbc_txtMaSP.insets = new Insets(0, 0, 25, 10);
		gbc_txtMaSP.gridx = 0;
		gbc_txtMaSP.gridy = 1;
		productPanel.add(txtMaSP, gbc_txtMaSP);
		
		lblTrangThai = new JLabel("Tình trạng:");
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTrangThai = new GridBagConstraints();
		gbc_lblTrangThai.anchor = GridBagConstraints.WEST;
		gbc_lblTrangThai.insets = new Insets(0, 0, 0, 10);
		gbc_lblTrangThai.gridx = 0;
		gbc_lblTrangThai.gridy = 2;
		productPanel.add(lblTrangThai, gbc_lblTrangThai);
		
		cbbTrangThai = new JComboBox<>();
		cbbTrangThai.setBackground(new Color(255, 255, 255));
		cbbTrangThai.setPreferredSize(new Dimension(0, 25));
		cbbTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbTrangThai.setModel(trangThai);
		GridBagConstraints gbc_cbbTrangThai = new GridBagConstraints();
		gbc_cbbTrangThai.fill = GridBagConstraints.BOTH;
		gbc_cbbTrangThai.insets = new Insets(0, 0, 25, 10);
		gbc_cbbTrangThai.gridx = 0;
		gbc_cbbTrangThai.gridy = 3;
		productPanel.add(cbbTrangThai, gbc_cbbTrangThai);
		
		lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDonGia = new GridBagConstraints();
		gbc_lblDonGia.anchor = GridBagConstraints.WEST;
		gbc_lblDonGia.insets = new Insets(0, 0, 0, 10);
		gbc_lblDonGia.gridx = 0;
		gbc_lblDonGia.gridy = 4;
		productPanel.add(lblDonGia, gbc_lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		GridBagConstraints gbc_txtDonGia = new GridBagConstraints();
		gbc_txtDonGia.fill = GridBagConstraints.BOTH;
		gbc_txtDonGia.insets = new Insets(0, 0, 0, 10);
		gbc_txtDonGia.gridx = 0;
		gbc_txtDonGia.gridy = 5;
		productPanel.add(txtDonGia, gbc_txtDonGia);
		
		btnHinhAnh = new JButton("Upload");
		btnHinhAnh.setPreferredSize(new Dimension(0, 25));
		Redesign.SubmitButton(btnHinhAnh);
		GridBagConstraints gbc_btnHinhAnh = new GridBagConstraints();
		gbc_btnHinhAnh.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnHinhAnh.insets = new Insets(0, 0, 0, 0);
		gbc_btnHinhAnh.gridx = 1;
		gbc_btnHinhAnh.gridy = 5;
		productPanel.add(btnHinhAnh, gbc_btnHinhAnh);
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
		
		lblErrorDonGia = new JLabel("");
		lblErrorDonGia.setPreferredSize(new Dimension(0, 25));
		lblErrorDonGia.setForeground(new Color(255, 0, 0));
		lblErrorDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorDonGia = new GridBagConstraints();
		gbc_lblErrorDonGia.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorDonGia.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorDonGia.insets = new Insets(0, 0, 0, 0);
		gbc_lblErrorDonGia.gridx = 0;
		gbc_lblErrorDonGia.gridy = 6;
		productPanel.add(lblErrorDonGia, gbc_lblErrorDonGia);
		
		lblErrorHinhAnh = new JLabel("");
		lblErrorHinhAnh.setPreferredSize(new Dimension(0, 25));
		lblErrorHinhAnh.setForeground(Color.RED);
		lblErrorHinhAnh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorHinhAnh = new GridBagConstraints();
		gbc_lblErrorHinhAnh.fill = GridBagConstraints.BOTH;
		gbc_lblErrorHinhAnh.insets = new Insets(0, 0, 0, 0);
		gbc_lblErrorHinhAnh.gridx = 1;
		gbc_lblErrorHinhAnh.gridy = 6;
		productPanel.add(lblErrorHinhAnh, gbc_lblErrorHinhAnh);
		
		lblTenSP = new JLabel("Tên laptop:");
		lblTenSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTenSP = new GridBagConstraints();
		gbc_lblTenSP.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTenSP.insets = new Insets(0, 0, 0, 0);
		gbc_lblTenSP.gridx = 0;
		gbc_lblTenSP.gridy = 7;
		productPanel.add(lblTenSP, gbc_lblTenSP);
		
		txtTenSP = new JTextField();
		txtTenSP.setColumns(10);
		GridBagConstraints gbc_txtTenSP = new GridBagConstraints();
		gbc_txtTenSP.gridwidth = 2;
		gbc_txtTenSP.fill = GridBagConstraints.BOTH;
		gbc_txtTenSP.insets = new Insets(0, 0, 0, 0);
		gbc_txtTenSP.gridx = 0;
		gbc_txtTenSP.gridy = 8;
		productPanel.add(txtTenSP, gbc_txtTenSP);
		
		lblErrorTenSP = new JLabel("");
		lblErrorTenSP.setPreferredSize(new Dimension(0, 25));
		lblErrorTenSP.setForeground(Color.RED);
		lblErrorTenSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorTenSP = new GridBagConstraints();
		gbc_lblErrorTenSP.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorTenSP.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorTenSP.insets = new Insets(0, 0, 15, 0);
		gbc_lblErrorTenSP.gridx = 0;
		gbc_lblErrorTenSP.gridy = 9;
		productPanel.add(lblErrorTenSP, gbc_lblErrorTenSP);
		
		panelChung = new JPanel();
		GridBagConstraints gbc_panelChung = new GridBagConstraints();
		gbc_panelChung.gridwidth = 2;
		gbc_panelChung.insets = new Insets(0, 0, 20, 0);
		gbc_panelChung.fill = GridBagConstraints.BOTH;
		gbc_panelChung.gridx = 0;
		gbc_panelChung.gridy = 10;
		productPanel.add(panelChung, gbc_panelChung);
		GridBagLayout gbl_panelChung = new GridBagLayout();
		gbl_panelChung.columnWidths = new int[]{0, 0};
		gbl_panelChung.rowHeights = new int[]{25, 25, 25, 25, 25, 0, 25, 25, 0, 25, 25, 0, 25, 25, 0, 0};
		gbl_panelChung.columnWeights = new double[]{1.0, 1.0};
		gbl_panelChung.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelChung.setLayout(gbl_panelChung);
		
		lblTrongLuong = new JLabel("Trọng lượng (kg):");
		GridBagConstraints gbc_lblTrongLuong = new GridBagConstraints();
		gbc_lblTrongLuong.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTrongLuong.insets = new Insets(0, 0, 0, 10);
		gbc_lblTrongLuong.gridx = 0;
		gbc_lblTrongLuong.gridy = 0;
		panelChung.add(lblTrongLuong, gbc_lblTrongLuong);
		lblTrongLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblNamRaMat = new JLabel("Năm ra mắt:");
		GridBagConstraints gbc_lblNamRaMat = new GridBagConstraints();
		gbc_lblNamRaMat.insets = new Insets(0, 0, 0, 0);
		gbc_lblNamRaMat.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNamRaMat.gridx = 1;
		gbc_lblNamRaMat.gridy = 0;
		panelChung.add(lblNamRaMat, gbc_lblNamRaMat);
		lblNamRaMat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtTrongLuong = new JTextField();
		txtTrongLuong.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtTrongLuong = new GridBagConstraints();
		gbc_txtTrongLuong.fill = GridBagConstraints.BOTH;
		gbc_txtTrongLuong.insets = new Insets(0, 0, 0, 10);
		gbc_txtTrongLuong.gridx = 0;
		gbc_txtTrongLuong.gridy = 1;
		panelChung.add(txtTrongLuong, gbc_txtTrongLuong);
		txtTrongLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTrongLuong.setColumns(10);
		
		txtNamRaMat = new JTextField();
		txtNamRaMat.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtNamRaMat = new GridBagConstraints();
		gbc_txtNamRaMat.insets = new Insets(0, 0, 0, 0);
		gbc_txtNamRaMat.fill = GridBagConstraints.BOTH;
		gbc_txtNamRaMat.gridx = 1;
		gbc_txtNamRaMat.gridy = 1;
		panelChung.add(txtNamRaMat, gbc_txtNamRaMat);
		txtNamRaMat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNamRaMat.setColumns(10);
		
		lblErrorTrongLuong = new JLabel("");
		lblErrorTrongLuong.setPreferredSize(new Dimension(0, 25));
		lblErrorTrongLuong.setForeground(Color.RED);
		lblErrorTrongLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorTrongLuong = new GridBagConstraints();
		gbc_lblErrorTrongLuong.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorTrongLuong.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorTrongLuong.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrorTrongLuong.gridx = 0;
		gbc_lblErrorTrongLuong.gridy = 2;
		panelChung.add(lblErrorTrongLuong, gbc_lblErrorTrongLuong);
		
		lblErrorNamRaMat = new JLabel("");
		lblErrorNamRaMat.setPreferredSize(new Dimension(0, 25));
		lblErrorNamRaMat.setForeground(Color.RED);
		lblErrorNamRaMat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorNamRaMat = new GridBagConstraints();
		gbc_lblErrorNamRaMat.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorNamRaMat.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorNamRaMat.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorNamRaMat.gridx = 1;
		gbc_lblErrorNamRaMat.gridy = 2;
		panelChung.add(lblErrorNamRaMat, gbc_lblErrorNamRaMat);
		
		lblMauSac = new JLabel("Màu sắc:");
		GridBagConstraints gbc_lblMauSac = new GridBagConstraints();
		gbc_lblMauSac.anchor = GridBagConstraints.SOUTH;
		gbc_lblMauSac.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMauSac.insets = new Insets(0, 0, 0, 10);
		gbc_lblMauSac.gridx = 0;
		gbc_lblMauSac.gridy = 3;
		panelChung.add(lblMauSac, gbc_lblMauSac);
		lblMauSac.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblChatLieu = new JLabel("Chất liệu:");
		GridBagConstraints gbc_lblChatLieu = new GridBagConstraints();
		gbc_lblChatLieu.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblChatLieu.insets = new Insets(0, 0, 0, 0);
		gbc_lblChatLieu.gridx = 1;
		gbc_lblChatLieu.gridy = 3;
		panelChung.add(lblChatLieu, gbc_lblChatLieu);
		lblChatLieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtMauSac = new JTextField();
		txtMauSac.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtMauSac = new GridBagConstraints();
		gbc_txtMauSac.fill = GridBagConstraints.BOTH;
		gbc_txtMauSac.insets = new Insets(0, 0, 0, 10);
		gbc_txtMauSac.gridx = 0;
		gbc_txtMauSac.gridy = 4;
		panelChung.add(txtMauSac, gbc_txtMauSac);
		txtMauSac.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtMauSac.setColumns(10);
		
		txtChatLieu = new JTextField();
		txtChatLieu.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtChatLieu = new GridBagConstraints();
		gbc_txtChatLieu.fill = GridBagConstraints.BOTH;
		gbc_txtChatLieu.insets = new Insets(0, 0, 0, 0);
		gbc_txtChatLieu.gridx = 1;
		gbc_txtChatLieu.gridy = 4;
		panelChung.add(txtChatLieu, gbc_txtChatLieu);
		txtChatLieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtChatLieu.setColumns(10);
		
		lblErrorMauSac = new JLabel("");
		lblErrorMauSac.setPreferredSize(new Dimension(0, 25));
		lblErrorMauSac.setForeground(Color.RED);
		lblErrorMauSac.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorMauSac = new GridBagConstraints();
		gbc_lblErrorMauSac.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorMauSac.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorMauSac.insets = new Insets(0, 0, 5, 10);
		gbc_lblErrorMauSac.gridx = 0;
		gbc_lblErrorMauSac.gridy = 5;
		panelChung.add(lblErrorMauSac, gbc_lblErrorMauSac);
		
		lblErrorChatLieu = new JLabel("");
		lblErrorChatLieu.setPreferredSize(new Dimension(0, 25));
		lblErrorChatLieu.setForeground(Color.RED);
		lblErrorChatLieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorChatLieu = new GridBagConstraints();
		gbc_lblErrorChatLieu.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorChatLieu.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorChatLieu.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorChatLieu.gridx = 1;
		gbc_lblErrorChatLieu.gridy = 5;
		panelChung.add(lblErrorChatLieu, gbc_lblErrorChatLieu);
		
		lblOS = new JLabel("Hệ điều hành:");
		GridBagConstraints gbc_lblOS = new GridBagConstraints();
		gbc_lblOS.anchor = GridBagConstraints.NORTH;
		gbc_lblOS.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOS.insets = new Insets(0, 0, 0, 10);
		gbc_lblOS.gridx = 0;
		gbc_lblOS.gridy = 6;
		panelChung.add(lblOS, gbc_lblOS);
		lblOS.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblThuongHieu = new JLabel("Thương hiệu:");
		GridBagConstraints gbc_lblThuongHieu = new GridBagConstraints();
		gbc_lblThuongHieu.anchor = GridBagConstraints.SOUTH;
		gbc_lblThuongHieu.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblThuongHieu.insets = new Insets(0, 0, 0, 0);
		gbc_lblThuongHieu.gridx = 1;
		gbc_lblThuongHieu.gridy = 6;
		panelChung.add(lblThuongHieu, gbc_lblThuongHieu);
		lblThuongHieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtOS = new JTextField();
		txtOS.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtOS = new GridBagConstraints();
		gbc_txtOS.fill = GridBagConstraints.BOTH;
		gbc_txtOS.insets = new Insets(0, 0, 0, 10);
		gbc_txtOS.gridx = 0;
		gbc_txtOS.gridy = 7;
		panelChung.add(txtOS, gbc_txtOS);
		txtOS.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtOS.setColumns(10);
		
		cbbThuongHieu = new JComboBox<>();
		cbbThuongHieu.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_cbbThuongHieu = new GridBagConstraints();
		gbc_cbbThuongHieu.fill = GridBagConstraints.BOTH;
		gbc_cbbThuongHieu.insets = new Insets(0, 0, 0, 0);
		gbc_cbbThuongHieu.gridx = 1;
		gbc_cbbThuongHieu.gridy = 7;
		panelChung.add(cbbThuongHieu, gbc_cbbThuongHieu);
		cbbThuongHieu.setBackground(new Color(255, 255, 255));
		cbbThuongHieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbThuongHieu.setModel(thuongHieu);
		
		lblErrorOS = new JLabel("");
		lblErrorOS.setPreferredSize(new Dimension(0, 25));
		lblErrorOS.setForeground(Color.RED);
		lblErrorOS.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorOS = new GridBagConstraints();
		gbc_lblErrorOS.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorOS.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorOS.insets = new Insets(0, 0, 5, 10);
		gbc_lblErrorOS.gridx = 0;
		gbc_lblErrorOS.gridy = 8;
		panelChung.add(lblErrorOS, gbc_lblErrorOS);
		
		lblErrorThuongHieu = new JLabel("");
		lblErrorThuongHieu.setPreferredSize(new Dimension(0, 25));
		lblErrorThuongHieu.setForeground(Color.RED);
		lblErrorThuongHieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorThuongHieu = new GridBagConstraints();
		gbc_lblErrorThuongHieu.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorThuongHieu.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorThuongHieu.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorThuongHieu.gridx = 1;
		gbc_lblErrorThuongHieu.gridy = 8;
		panelChung.add(lblErrorThuongHieu, gbc_lblErrorThuongHieu);
		
		lblBaoMat = new JLabel("Bảo mật:");
		GridBagConstraints gbc_lblBaoMat = new GridBagConstraints();
		gbc_lblBaoMat.anchor = GridBagConstraints.SOUTH;
		gbc_lblBaoMat.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBaoMat.insets = new Insets(0, 0, 0, 0);
		gbc_lblBaoMat.gridx = 0;
		gbc_lblBaoMat.gridy = 9;
		gbc_lblBaoMat.gridwidth = 2;
		panelChung.add(lblBaoMat, gbc_lblBaoMat);
		lblBaoMat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtBaoMat = new JTextField();
		txtBaoMat.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtBaoMat = new GridBagConstraints();
		gbc_txtBaoMat.fill = GridBagConstraints.BOTH;
		gbc_txtBaoMat.gridwidth = 2;
		gbc_txtBaoMat.insets = new Insets(0, 0, 0, 0);
		gbc_txtBaoMat.gridx = 0;
		gbc_txtBaoMat.gridy = 10;
		panelChung.add(txtBaoMat, gbc_txtBaoMat);
		txtBaoMat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBaoMat.setColumns(10);
		
		lblErrorBaoMat = new JLabel("");
		lblErrorBaoMat.setPreferredSize(new Dimension(0, 25));
		lblErrorBaoMat.setForeground(Color.RED);
		lblErrorBaoMat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorBaoMat = new GridBagConstraints();
		gbc_lblErrorBaoMat.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorBaoMat.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorBaoMat.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorBaoMat.gridx = 0;
		gbc_lblErrorBaoMat.gridy = 11;
		gbc_lblBaoMat.gridwidth = 2;
		panelChung.add(lblErrorBaoMat, gbc_lblErrorBaoMat);
		
		lblBluetooth = new JLabel("Bluetooth:");
		GridBagConstraints gbc_lblBluetooth = new GridBagConstraints();
		gbc_lblBluetooth.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBluetooth.insets = new Insets(0, 0, 0, 10);
		gbc_lblBluetooth.gridx = 0;
		gbc_lblBluetooth.gridy = 12;
		panelChung.add(lblBluetooth, gbc_lblBluetooth);
		lblBluetooth.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblPin = new JLabel("Pin:");
		GridBagConstraints gbc_lblPin = new GridBagConstraints();
		gbc_lblPin.anchor = GridBagConstraints.NORTH;
		gbc_lblPin.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPin.insets = new Insets(0, 0, 0, 0);
		gbc_lblPin.gridx = 1;
		gbc_lblPin.gridy = 12;
		panelChung.add(lblPin, gbc_lblPin);
		lblPin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtBluetooth = new JTextField();
		txtBluetooth.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtBluetooth = new GridBagConstraints();
		gbc_txtBluetooth.fill = GridBagConstraints.BOTH;
		gbc_txtBluetooth.insets = new Insets(0, 0, 0, 10);
		gbc_txtBluetooth.gridx = 0;
		gbc_txtBluetooth.gridy = 13;
		panelChung.add(txtBluetooth, gbc_txtBluetooth);
		txtBluetooth.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBluetooth.setColumns(10);
		
		txtPin = new JTextField();
		txtPin.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtPin = new GridBagConstraints();
		gbc_txtPin.insets = new Insets(0, 0, 0, 0);
		gbc_txtPin.fill = GridBagConstraints.BOTH;
		gbc_txtPin.gridx = 1;
		gbc_txtPin.gridy = 13;
		panelChung.add(txtPin, gbc_txtPin);
		txtPin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPin.setColumns(10);
		
		lblErrorBluetooth = new JLabel("");
		lblErrorBluetooth.setPreferredSize(new Dimension(0, 25));
		lblErrorBluetooth.setForeground(Color.RED);
		lblErrorBluetooth.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorBluetooth = new GridBagConstraints();
		gbc_lblErrorBluetooth.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorBluetooth.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorBluetooth.insets = new Insets(0, 0, 0, 10);
		gbc_lblErrorBluetooth.gridx = 0;
		gbc_lblErrorBluetooth.gridy = 14;
		panelChung.add(lblErrorBluetooth, gbc_lblErrorBluetooth);
		
		lblErrorPin = new JLabel("");
		lblErrorPin.setPreferredSize(new Dimension(0, 25));
		lblErrorPin.setForeground(Color.RED);
		lblErrorPin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorPin = new GridBagConstraints();
		gbc_lblErrorPin.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorPin.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorPin.gridx = 1;
		gbc_lblErrorPin.gridy = 14;
		panelChung.add(lblErrorPin, gbc_lblErrorPin);
		
		panelKyThuat = new JPanel();
		GridBagConstraints gbc_panelKyThuat = new GridBagConstraints();
		gbc_panelKyThuat.insets = new Insets(0, 0, 20, 0);
		gbc_panelKyThuat.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelKyThuat.gridwidth = 2;
		gbc_panelKyThuat.gridx = 0;
		gbc_panelKyThuat.gridy = 11;
		productPanel.add(panelKyThuat, gbc_panelKyThuat);
		
		GridBagLayout gbl_panelKyThuat = new GridBagLayout();
		gbl_panelKyThuat.columnWidths = new int[] {0, 20, 0};
		gbl_panelKyThuat.rowHeights = new int[]{25, 25, 0, 25, 25, 25, 0, 25, 25, 0, 25, 25, 0, 0};
		gbl_panelKyThuat.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_panelKyThuat.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelKyThuat.setLayout(gbl_panelKyThuat);
		
		lblCPU = new JLabel("CPU:");
		GridBagConstraints gbc_lblCPU = new GridBagConstraints();
		gbc_lblCPU.anchor = GridBagConstraints.SOUTH;
		gbc_lblCPU.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCPU.insets = new Insets(0, 0, 5, 5);
		gbc_lblCPU.gridx = 0;
		gbc_lblCPU.gridy = 0;
		panelKyThuat.add(lblCPU, gbc_lblCPU);
		lblCPU.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtCPU = new JTextField();
		txtCPU.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtCPU = new GridBagConstraints();
		gbc_txtCPU.fill = GridBagConstraints.BOTH;
		gbc_txtCPU.gridwidth = 3;
		gbc_txtCPU.insets = new Insets(0, 0, 0, 0);
		gbc_txtCPU.gridx = 0;
		gbc_txtCPU.gridy = 1;
		panelKyThuat.add(txtCPU, gbc_txtCPU);
		txtCPU.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCPU.setColumns(10);
		
		lblErrorCPU = new JLabel("");
		lblErrorCPU.setPreferredSize(new Dimension(0, 25));
		lblErrorCPU.setForeground(Color.RED);
		lblErrorCPU.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorCPU = new GridBagConstraints();
		gbc_lblErrorCPU.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorCPU.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorCPU.insets = new Insets(0, 0, 0, 0);
		gbc_lblErrorCPU.gridx = 0;
		gbc_lblErrorCPU.gridy = 2;
		panelKyThuat.add(lblErrorCPU, gbc_lblErrorCPU);
		
		lblRAM = new JLabel("RAM (GB):");
		GridBagConstraints gbc_lblRAM = new GridBagConstraints();
		gbc_lblRAM.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRAM.insets = new Insets(0, 0, 0, 10);
		gbc_lblRAM.gridx = 0;
		gbc_lblRAM.gridy = 3;
		panelKyThuat.add(lblRAM, gbc_lblRAM);
		lblRAM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblNangCapRAM = new JLabel("Nâng cấp:");
		GridBagConstraints gbc_lblNangCapRAM = new GridBagConstraints();
		gbc_lblNangCapRAM.gridwidth = 2;
		gbc_lblNangCapRAM.anchor = GridBagConstraints.NORTH;
		gbc_lblNangCapRAM.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNangCapRAM.insets = new Insets(0, 0, 0, 0);
		gbc_lblNangCapRAM.gridx = 1;
		gbc_lblNangCapRAM.gridy = 3;
		panelKyThuat.add(lblNangCapRAM, gbc_lblNangCapRAM);
		lblNangCapRAM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtRAM = new JTextField();
		txtRAM.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtRAM = new GridBagConstraints();
		gbc_txtRAM.fill = GridBagConstraints.BOTH;
		gbc_txtRAM.insets = new Insets(0, 0, 0, 10);
		gbc_txtRAM.gridx = 0;
		gbc_txtRAM.gridy = 4;
		panelKyThuat.add(txtRAM, gbc_txtRAM);
		txtRAM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtRAM.setColumns(10);
		
		rdbKhong = new JRadioButton("Không");
		rdbKhong.setOpaque(false);
		rdbKhong.setPreferredSize(new Dimension(0, 25));
		rdbKhong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbKhong = new GridBagConstraints();
		gbc_rdbKhong.gridwidth = 2;
		gbc_rdbKhong.fill = GridBagConstraints.BOTH;
		gbc_rdbKhong.insets = new Insets(0, 0, 0, 0);
		gbc_rdbKhong.gridx = 1;
		gbc_rdbKhong.gridy = 4;
		panelKyThuat.add(rdbKhong, gbc_rdbKhong);
		rdbKhong.setSelected(true);
		
		lblErrorRAM = new JLabel("");
		lblErrorRAM.setPreferredSize(new Dimension(0, 25));
		lblErrorRAM.setForeground(Color.RED);
		lblErrorRAM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorRAM = new GridBagConstraints();
		gbc_lblErrorRAM.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorRAM.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorRAM.insets = new Insets(0, 0, 0, 0);
		gbc_lblErrorRAM.gridx = 0;
		gbc_lblErrorRAM.gridy = 5;
		panelKyThuat.add(lblErrorRAM, gbc_lblErrorRAM);
		
		JRadioButton rdbUpgrade = new JRadioButton("");
		rdbUpgrade.setOpaque(false);
		rdbUpgrade.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_rdbUpgrade = new GridBagConstraints();
		gbc_rdbUpgrade.fill = GridBagConstraints.BOTH;
		gbc_rdbUpgrade.insets = new Insets(0, 0, 0, 2);
		gbc_rdbUpgrade.gridx = 1;
		gbc_rdbUpgrade.gridy = 5;
		panelKyThuat.add(rdbUpgrade, gbc_rdbUpgrade);
		
		txtNangCapRAM = new JTextField();
		txtNangCapRAM.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtNangCapRAM = new GridBagConstraints();
		gbc_txtNangCapRAM.fill = GridBagConstraints.BOTH;
		gbc_txtNangCapRAM.insets = new Insets(0, 0, 0, 0);
		gbc_txtNangCapRAM.gridx = 2;
		gbc_txtNangCapRAM.gridy = 5;
		panelKyThuat.add(txtNangCapRAM, gbc_txtNangCapRAM);
		txtNangCapRAM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNangCapRAM.setColumns(10);
		
		lblErrorNangCapRAM = new JLabel("");
		lblErrorNangCapRAM.setPreferredSize(new Dimension(0, 25));
		lblErrorNangCapRAM.setForeground(Color.RED);
		lblErrorNangCapRAM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorNangCapRAM = new GridBagConstraints();
		gbc_lblErrorNangCapRAM.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorNangCapRAM.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorNangCapRAM.gridwidth = 2;
		gbc_lblErrorNangCapRAM.insets = new Insets(0, 0, 0, 0);
		gbc_lblErrorNangCapRAM.gridx = 1;
		gbc_lblErrorNangCapRAM.gridy = 6;
		panelKyThuat.add(lblErrorNangCapRAM, gbc_lblErrorNangCapRAM);
		
		lblGPU = new JLabel("GPU:");
		GridBagConstraints gbc_lblGPU = new GridBagConstraints();
		gbc_lblGPU.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGPU.insets = new Insets(0, 0, 0, 0);
		gbc_lblGPU.gridx = 0;
		gbc_lblGPU.gridy = 7;
		panelKyThuat.add(lblGPU, gbc_lblGPU);
		lblGPU.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtGPU = new JTextField();
		txtGPU.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtGPU = new GridBagConstraints();
		gbc_txtGPU.fill = GridBagConstraints.BOTH;
		gbc_txtGPU.gridwidth = 3;
		gbc_txtGPU.insets = new Insets(0, 0, 0, 0);
		gbc_txtGPU.gridx = 0;
		gbc_txtGPU.gridy = 8;
		panelKyThuat.add(txtGPU, gbc_txtGPU);
		txtGPU.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtGPU.setColumns(10);
		
		lblErrorGPU = new JLabel("");
		lblErrorGPU.setPreferredSize(new Dimension(0, 25));
		lblErrorGPU.setForeground(Color.RED);
		lblErrorGPU.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorGPU = new GridBagConstraints();
		gbc_lblErrorGPU.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorGPU.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorGPU.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorGPU.gridx = 0;
		gbc_lblErrorGPU.gridy = 9;
		panelKyThuat.add(lblErrorGPU, gbc_lblErrorGPU);
		
		lblOCung = new JLabel("Ổ cứng:");
		GridBagConstraints gbc_lblOCung = new GridBagConstraints();
		gbc_lblOCung.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOCung.insets = new Insets(0, 0, 0, 0);
		gbc_lblOCung.gridx = 0;
		gbc_lblOCung.gridy = 10;
		panelKyThuat.add(lblOCung, gbc_lblOCung);
		lblOCung.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtDungLuong = new JTextField();
		txtDungLuong.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtDungLuong = new GridBagConstraints();
		gbc_txtDungLuong.fill = GridBagConstraints.BOTH;
		gbc_txtDungLuong.insets = new Insets(0, 0, 0, 10);
		gbc_txtDungLuong.gridx = 0;
		gbc_txtDungLuong.gridy = 11;
		panelKyThuat.add(txtDungLuong, gbc_txtDungLuong);
		txtDungLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDungLuong.setColumns(10);
		
		cbbLoaiOCung = new JComboBox<>();
		cbbLoaiOCung.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_cbbLoaiOCung = new GridBagConstraints();
		gbc_cbbLoaiOCung.insets = new Insets(0, 0, 0, 0);
		gbc_cbbLoaiOCung.gridwidth = 2;
		gbc_cbbLoaiOCung.fill = GridBagConstraints.BOTH;
		gbc_cbbLoaiOCung.gridx = 1;
		gbc_cbbLoaiOCung.gridy = 11;
		panelKyThuat.add(cbbLoaiOCung, gbc_cbbLoaiOCung);
		cbbLoaiOCung.setBackground(new Color(255, 255, 255));
		cbbLoaiOCung.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbLoaiOCung.setModel(loaiOCung);
		
		lblErrorOCung = new JLabel("");
		lblErrorOCung.setPreferredSize(new Dimension(0, 25));
		lblErrorOCung.setForeground(Color.RED);
		lblErrorOCung.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorOCung = new GridBagConstraints();
		gbc_lblErrorOCung.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorOCung.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorOCung.insets = new Insets(0, 0, 0, 0);
		gbc_lblErrorOCung.gridwidth = 3;
		gbc_lblErrorOCung.gridx = 0;
		gbc_lblErrorOCung.gridy = 12;
		panelKyThuat.add(lblErrorOCung, gbc_lblErrorOCung);
		
		panelManHinh = new JPanel();
		GridBagConstraints gbc_panelManHinh = new GridBagConstraints();
		gbc_panelManHinh.gridwidth = 2;
		gbc_panelManHinh.fill = GridBagConstraints.BOTH;
		gbc_panelManHinh.insets = new Insets(0, 0, 5, 0);
		gbc_panelManHinh.gridx = 0;
		gbc_panelManHinh.gridy = 12;
		productPanel.add(panelManHinh, gbc_panelManHinh);
		GridBagLayout gbl_panelManHinh = new GridBagLayout();
		gbl_panelManHinh.columnWidths = new int[]{0, 10, 0};
		gbl_panelManHinh.rowHeights = new int[]{25, 25, 25, 25, 25, 0, 25, 25, 0, 0};
		gbl_panelManHinh.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_panelManHinh.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelManHinh.setLayout(gbl_panelManHinh);
		
		lblManHinh = new JLabel("Kích thước (inch):");
		GridBagConstraints gbc_lblManHinh = new GridBagConstraints();
		gbc_lblManHinh.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblManHinh.anchor = GridBagConstraints.NORTH;
		gbc_lblManHinh.insets = new Insets(0, 0, 0, 5);
		gbc_lblManHinh.gridwidth = 2;
		gbc_lblManHinh.gridx = 0;
		gbc_lblManHinh.gridy = 0;
		panelManHinh.add(lblManHinh, gbc_lblManHinh);
		lblManHinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblTanSo = new JLabel("Tần số (Hz):");
		GridBagConstraints gbc_lblTanSo = new GridBagConstraints();
		gbc_lblTanSo.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTanSo.insets = new Insets(0, 5, 0, 0);
		gbc_lblTanSo.gridx = 2;
		gbc_lblTanSo.gridy = 0;
		panelManHinh.add(lblTanSo, gbc_lblTanSo);
		lblTanSo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtManHinh = new JTextField();
		txtManHinh.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtManHinh = new GridBagConstraints();
		gbc_txtManHinh.fill = GridBagConstraints.BOTH;
		gbc_txtManHinh.insets = new Insets(0, 0, 0, 5);
		gbc_txtManHinh.gridx = 0;
		gbc_txtManHinh.gridy = 1;
		panelManHinh.add(txtManHinh, gbc_txtManHinh);
		txtManHinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtManHinh.setColumns(10);
		
		txtTanSo = new JTextField();
		txtTanSo.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtTanSo = new GridBagConstraints();
		gbc_txtTanSo.fill = GridBagConstraints.BOTH;
		gbc_txtTanSo.insets = new Insets(0, 5, 0, 0);
		gbc_txtTanSo.gridx = 2;
		gbc_txtTanSo.gridy = 1;
		panelManHinh.add(txtTanSo, gbc_txtTanSo);
		txtTanSo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTanSo.setColumns(10);
		
		lblErrorManHinh = new JLabel("");
		lblErrorManHinh.setPreferredSize(new Dimension(0, 25));
		lblErrorManHinh.setForeground(Color.RED);
		lblErrorManHinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorManHinh = new GridBagConstraints();
		gbc_lblErrorManHinh.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorManHinh.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorManHinh.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrorManHinh.gridx = 0;
		gbc_lblErrorManHinh.gridy = 2;
		panelManHinh.add(lblErrorManHinh, gbc_lblErrorManHinh);
		
		lblErrorTanSo = new JLabel("");
		lblErrorTanSo.setPreferredSize(new Dimension(0, 25));
		lblErrorTanSo.setForeground(Color.RED);
		lblErrorTanSo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorTanSo = new GridBagConstraints();
		gbc_lblErrorTanSo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorTanSo.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorTanSo.insets = new Insets(0, 5, 5, 0);
		gbc_lblErrorTanSo.gridx = 2;
		gbc_lblErrorTanSo.gridy = 2;
		panelManHinh.add(lblErrorTanSo, gbc_lblErrorTanSo);
		
		lblDoPhanGiai = new JLabel("Độ phân giải:");
		GridBagConstraints gbc_lblDoPhanGiai = new GridBagConstraints();
		gbc_lblDoPhanGiai.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDoPhanGiai.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoPhanGiai.gridx = 0;
		gbc_lblDoPhanGiai.gridy = 3;
		panelManHinh.add(lblDoPhanGiai, gbc_lblDoPhanGiai);
		lblDoPhanGiai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtChieuDai = new JTextField();
		txtChieuDai.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtChieuDai = new GridBagConstraints();
		gbc_txtChieuDai.fill = GridBagConstraints.BOTH;
		gbc_txtChieuDai.insets = new Insets(0, 0, 0, 5);
		gbc_txtChieuDai.gridx = 0;
		gbc_txtChieuDai.gridy = 4;
		panelManHinh.add(txtChieuDai, gbc_txtChieuDai);
		txtChieuDai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtChieuDai.setColumns(10);
		
		JLabel lblX = new JLabel("x");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.anchor = GridBagConstraints.NORTH;
		gbc_lblX.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblX.gridx = 1;
		gbc_lblX.gridy = 4;
		panelManHinh.add(lblX, gbc_lblX);
		
		txtChieuCao = new JTextField();
		txtChieuCao.setPreferredSize(new Dimension(0, 25));
		txtChieuCao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtChieuCao.setColumns(10);
		GridBagConstraints gbc_txtChieuCao = new GridBagConstraints();
		gbc_txtChieuCao.fill = GridBagConstraints.BOTH;
		gbc_txtChieuCao.insets = new Insets(0, 5, 0, 0);
		gbc_txtChieuCao.gridx = 2;
		gbc_txtChieuCao.gridy = 4;
		panelManHinh.add(txtChieuCao, gbc_txtChieuCao);
		
		lblErrorDoPhanGiai = new JLabel("");
		lblErrorDoPhanGiai.setPreferredSize(new Dimension(0, 25));
		lblErrorDoPhanGiai.setForeground(Color.RED);
		lblErrorDoPhanGiai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorDoPhanGiai = new GridBagConstraints();
		gbc_lblErrorDoPhanGiai.gridwidth = 3;
		gbc_lblErrorDoPhanGiai.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorDoPhanGiai.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorDoPhanGiai.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrorDoPhanGiai.gridx = 0;
		gbc_lblErrorDoPhanGiai.gridy = 5;
		panelManHinh.add(lblErrorDoPhanGiai, gbc_lblErrorDoPhanGiai);
		
		lblCongNghe = new JLabel("Công nghệ:");
		GridBagConstraints gbc_lblCongNghe = new GridBagConstraints();
		gbc_lblCongNghe.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblCongNghe.insets = new Insets(0, 0, 0, 5);
		gbc_lblCongNghe.gridx = 0;
		gbc_lblCongNghe.gridy = 6;
		panelManHinh.add(lblCongNghe, gbc_lblCongNghe);
		lblCongNghe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblTamNen = new JLabel("Tấm nền:");
		GridBagConstraints gbc_lblTamNen = new GridBagConstraints();
		gbc_lblTamNen.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTamNen.insets = new Insets(0, 5, 0, 0);
		gbc_lblTamNen.gridx = 2;
		gbc_lblTamNen.gridy = 6;
		panelManHinh.add(lblTamNen, gbc_lblTamNen);
		lblTamNen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtCongNghe = new JTextField();
		txtCongNghe.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtCongNghe = new GridBagConstraints();
		gbc_txtCongNghe.fill = GridBagConstraints.BOTH;
		gbc_txtCongNghe.insets = new Insets(0, 0, 0, 5);
		gbc_txtCongNghe.gridx = 0;
		gbc_txtCongNghe.gridy = 7;
		panelManHinh.add(txtCongNghe, gbc_txtCongNghe);
		txtCongNghe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCongNghe.setColumns(10);
		
		txtTamNen = new JTextField();
		txtTamNen.setPreferredSize(new Dimension(0, 25));
		GridBagConstraints gbc_txtTamNen = new GridBagConstraints();
		gbc_txtTamNen.insets = new Insets(0, 5, 0, 0);
		gbc_txtTamNen.fill = GridBagConstraints.BOTH;
		gbc_txtTamNen.gridx = 2;
		gbc_txtTamNen.gridy = 7;
		panelManHinh.add(txtTamNen, gbc_txtTamNen);
		txtTamNen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTamNen.setColumns(10);
		
		lblErrorCongNghe = new JLabel("");
		lblErrorCongNghe.setPreferredSize(new Dimension(0, 25));
		lblErrorCongNghe.setForeground(Color.RED);
		lblErrorCongNghe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorCongNghe = new GridBagConstraints();
		gbc_lblErrorCongNghe.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorCongNghe.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorCongNghe.insets = new Insets(0, 0, 0, 5);
		gbc_lblErrorCongNghe.gridx = 0;
		gbc_lblErrorCongNghe.gridy = 8;
		panelManHinh.add(lblErrorCongNghe, gbc_lblErrorCongNghe);
		
		lblErrorTamNen = new JLabel("");
		lblErrorTamNen.setPreferredSize(new Dimension(0, 25));
		lblErrorTamNen.setForeground(Color.RED);
		lblErrorTamNen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblErrorTamNen = new GridBagConstraints();
		gbc_lblErrorTamNen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorTamNen.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorTamNen.insets = new Insets(0, 5, 0, 0);
		gbc_lblErrorTamNen.gridx = 2;
		gbc_lblErrorTamNen.gridy = 8;
		panelManHinh.add(lblErrorTamNen, gbc_lblErrorTamNen);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(new EmptyBorder(10,0,0,0));
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 2;
		infoPanel.add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		btnLuu = new JButton("LƯU");
		buttonPanel.add(btnLuu);
		btnLuu.setPreferredSize(new Dimension(120,30));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Laptop lap = new Laptop(txtMaSP.getText(), txtTenSP.getText(), "", txtDonGia.getText(), cbbTrangThai.getSelectedIndex(),
					txtTrongLuong.getText(), txtNamRaMat.getText(), txtMauSac.getText(), txtChatLieu.getText(), txtOS.getText(),
					cbbThuongHieu.getSelectedItem().toString(), txtBaoMat.getText(), txtBluetooth.getText(), txtPin.getText(), txtCPU.getText(),
					txtRAM.getText(), txtNangCapRAM.getText(), txtGPU.getText(), txtDungLuong.getText()+" "+cbbLoaiOCung.getSelectedItem().toString(),
					txtManHinh.getText(), txtTanSo.getText(), txtChieuDai.getText()+"x"+txtChieuCao.getText(), txtCongNghe.getText(), txtTamNen.getText()
				);
				String[] checking = LaptopBUS.kiemTra(lap, lblPic.getIcon());
				if (checking.length > 0) {
					for (int i=0; i<errorLabels.size(); i++) errorLabels.get(i).setText(checking[i]);
					return;					
				}
				
				if (txtMaSP.getText().isEmpty()) {
					if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm laptop này?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String anh = (imageChooser == null) ? "" : imageChooser.getSelectedFile().getAbsolutePath();
						if (LaptopBUS.insert(lap, lblPic.getIcon(), anh))
						{
							JOptionPane.showMessageDialog(null, "Thêm laptop thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							load(); clear();
						}
						else JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra, thêm laptop thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					if (JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật laptop này?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String anh = (imageChooser == null) ? "" : imageChooser.getSelectedFile().getAbsolutePath();
						if (LaptopBUS.update(lap, lblPic.getIcon(), anh))
						{
							JOptionPane.showMessageDialog(null, "Cập nhật laptop thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							load(); clear();
						}
						else JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra, cập nhật laptop thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnClear = new JButton("HỦY BỎ");
		btnClear.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnClear);
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
		this.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Laptop lap = search(model.getValueAt(table.getSelectedRow(),0).toString());
				txtMaSP.setText(lap.getMaSP());
				cbbTrangThai.setSelectedIndex(lap.getTrangThai());
				txtDonGia.setText(lap.getDonGia());
				txtTenSP.setText(lap.getTenSP());
				ImageIcon anh = new ImageIcon(new ImageIcon(lap.getAnhSP()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				lblPic.setIcon(anh);
				txtTrongLuong.setText(lap.getTrongLuong());
				txtNamRaMat.setText(lap.getNamRaMat());
				txtMauSac.setText(lap.getMauSac());
				txtOS.setText(lap.getHeDieuHanh());
				txtChatLieu.setText(lap.getChatLieu());
				cbbThuongHieu.setSelectedItem((Object)lap.getThuongHieu());
				cbbThuongHieu.setEnabled(false);
				txtBaoMat.setText(lap.getBaoMat());
				txtBluetooth.setText(lap.getBluetooth());
				txtPin.setText(lap.getPin());
				txtCPU.setText(lap.getCPU());
				txtRAM.setText(lap.getRAM());
				txtNangCapRAM.setText(lap.getNangCapRAM());
				txtGPU.setText(lap.getGPU());
				txtDungLuong.setText(lap.getDungLuongOCung());
				cbbLoaiOCung.setSelectedItem((Object)lap.getLoaiOCung());
				txtManHinh.setText(lap.getManHinh());
				txtTanSo.setText(lap.getTanSo());
				txtChieuDai.setText(lap.getDoPhanGiai().split("x")[0]);
				txtChieuCao.setText(lap.getDoPhanGiai().split("x")[1]);
				txtCongNghe.setText(lap.getCongNghe());
				txtTamNen.setText(lap.getTamNen());
				clearError();
			}
		});
		scrollPane.setViewportView(table);
		initTextfield();
		initLabel();
		initError();
		repainting();
	}
	
	private void load() {
		laptop = LaptopBUS.getDanhSachLaptop();
		model.setRowCount(0);
		Object[] data = new Object[model.getColumnCount()];
		
		if (laptop != null)
			for (Laptop lap: laptop) {
				data[0] = lap.getMaSP(); data[1] = lap.getTenSP(); data[2] = lap.getDonGia(); data[3] = lap.getSoLuong(); data[4] = lap.getTrangThai();
				for (int i=0; i<model.getColumnCount(); i++) if (data[i] == null) data[i] = "";
				model.addRow(data);
			}
	}
	
	private void clear() {
		for (int i=0; i<listTextfield.size(); i++) listTextfield.get(i).setText("");
		txtMaSP.setText("");
		cbbTrangThai.setSelectedIndex(2);
		cbbThuongHieu.setEnabled(true);
		cbbThuongHieu.setSelectedIndex(0);
		cbbLoaiOCung.setSelectedIndex(0);
		lblPic.setIcon(null);
		clearError();
	}
	
	private Laptop search(String maSP) {
		for (Laptop lap: laptop) if (lap.getMaSP() == maSP) return lap;
		return null;
	}
	
	private void initTextfield() {
		listTextfield.add(txtMaSP);
		listTextfield.add(txtTenSP);
		listTextfield.add(txtDonGia);
		listTextfield.add(txtTrongLuong);
		listTextfield.add(txtNamRaMat);
		listTextfield.add(txtMauSac);
		listTextfield.add(txtChatLieu);
		listTextfield.add(txtOS);
		listTextfield.add(txtBaoMat);
		listTextfield.add(txtBluetooth);
		listTextfield.add(txtPin);
		listTextfield.add(txtCPU);
		listTextfield.add(txtRAM);
		listTextfield.add(txtNangCapRAM);
		listTextfield.add(txtGPU);
		listTextfield.add(txtDungLuong);
		listTextfield.add(txtManHinh);
		listTextfield.add(txtTanSo);
		listTextfield.add(txtChieuDai);
		listTextfield.add(txtChieuCao);
		listTextfield.add(txtCongNghe);
		listTextfield.add(txtTamNen);
	}
	
	private void initLabel() {
		listLabel.add(lblTitle);
		listLabel.add(lblMaSP);
		listLabel.add(lblTrangThai);
		listLabel.add(lblDonGia);
		listLabel.add(lblTenSP);
		listLabel.add(lblNamRaMat);
		listLabel.add(lblTrongLuong);	
		listLabel.add(lblMauSac);
		listLabel.add(lblChatLieu);
		listLabel.add(lblCPU);
		listLabel.add(lblRAM);
		listLabel.add(lblNangCapRAM);
		listLabel.add(lblManHinh);
		listLabel.add(lblDoPhanGiai);
		listLabel.add(lblTanSo);
		listLabel.add(lblCongNghe);
		listLabel.add(lblTamNen);
		listLabel.add(lblGPU);
		listLabel.add(lblBaoMat);
		listLabel.add(lblBluetooth);
		listLabel.add(lblPin);
		listLabel.add(lblOS);
		listLabel.add(lblOCung);
		listLabel.add(lblThuongHieu);
	}
	
	private void clearError() {
		for (JLabel label: errorLabels) label.setText("");
	}
	
	private void initError() {
		errorLabels.add(lblErrorTenSP);
		errorLabels.add(lblErrorDonGia);
		errorLabels.add(lblErrorHinhAnh);
		errorLabels.add(lblErrorThuongHieu);
		errorLabels.add(lblErrorTrongLuong);
		errorLabels.add(lblErrorNamRaMat);
		errorLabels.add(lblErrorMauSac);
		errorLabels.add(lblErrorChatLieu);
		errorLabels.add(lblErrorOS);
		errorLabels.add(lblErrorBaoMat);
		errorLabels.add(lblErrorBluetooth);
		errorLabels.add(lblErrorPin);
		errorLabels.add(lblErrorCPU);
		errorLabels.add(lblErrorRAM);
		errorLabels.add(lblErrorNangCapRAM);
		errorLabels.add(lblErrorGPU);
		errorLabels.add(lblErrorOCung);
		errorLabels.add(lblErrorManHinh);
		errorLabels.add(lblErrorTanSo);
		errorLabels.add(lblErrorDoPhanGiai);
		errorLabels.add(lblErrorCongNghe);
		errorLabels.add(lblErrorTamNen);
	}
	
	void repainting() {
		setBackground(ThemeColor.LIGHT_1);
		productPanel.setBackground(ThemeColor.LIGHT_1);
		scrollNhapLieu.getVerticalScrollBar().setBackground(ThemeColor.LIGHT_1);
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(table);
		
		Redesign.GroupBox(panelChung, " Thông tin chung: ");
		Redesign.GroupBox(panelKyThuat, "Kỹ thuật");
		Redesign.GroupBox(panelManHinh, " Màn hình: ");
		
		Redesign.SubmitButton(btnHinhAnh);
		Redesign.SubmitButton(btnLuu);
		Redesign.SubmitButton(btnClear);
		
		Redesign.ComboBox(cbbThuongHieu);
		Redesign.ComboBox(cbbLoaiOCung);
		Redesign.ComboBox(cbbTrangThai);
		rdbKhong.setForeground(ThemeColor.TEXT);

		for (JLabel lbl: listLabel) lbl.setForeground(ThemeColor.TEXT);
		for (JTextField txt: listTextfield) Redesign.TextField(txt);
	}
}
