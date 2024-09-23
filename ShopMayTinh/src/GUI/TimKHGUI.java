package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.LinkedList;
import BUS.KhachHangBUS;
import DTO.KhachHang;
import DTO.TreEm;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TimKHGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private LinkedList<KhachHang> danhSachKH = KhachHangBUS.getDanhSachKH();
	private JPanel contentPane;
	private JComboBox<String> cbbTieuChi;
	private KhachHang khachHang = new KhachHang();
	private JTextField txtSearch;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel(
		new Object[][] {}, new String[] { "CMND", "Tên khách", "Ngày sinh", "Giới tính", "Số điện thoại", "" }
	);
	private JButton btnChon;

	/**
	 * Create the frame.
	 */
	public TimKHGUI(JPanel basePanel, char tinhChat) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Tìm khách hàng");
		setBounds(100, 100, 700, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setBackground(ThemeColor.LIGHT_1);

		setContentPane(contentPane);
		load(danhSachKH);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {80, 0, 0, 100};
		gbl_contentPane.rowHeights = new int[] {40, 35, 0, 35};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("TÌM KHÁCH HÀNG");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 4;
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblSearch = new JLabel("Tìm kiếm:");
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.fill = GridBagConstraints.BOTH;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 1;
		contentPane.add(lblSearch, gbc_lblSearch);
		
		cbbTieuChi = new JComboBox<>();
		cbbTieuChi.setBackground(new Color(255, 255, 255));
		cbbTieuChi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbTieuChi.setModel(new DefaultComboBoxModel<>(new String[] {"Tên KH", "CMND", "Số điện thoại"}));
		GridBagConstraints gbc_cbbTieuChi = new GridBagConstraints();
		gbc_cbbTieuChi.fill = GridBagConstraints.BOTH;
		gbc_cbbTieuChi.insets = new Insets(0, 0, 5, 5);
		gbc_cbbTieuChi.gridx = 1;
		gbc_cbbTieuChi.gridy = 1;
		contentPane.add(cbbTieuChi, gbc_cbbTieuChi);
		
		JButton btnTim = new JButton("Tìm");
		Redesign.SubmitButton(btnTim);
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSearch.getText().isEmpty()) load(danhSachKH);
				load(KhachHangBUS.search(cbbTieuChi.getSelectedItem().toString(), txtSearch.getText()));
			}
		});
		GridBagConstraints gbc_btnTim = new GridBagConstraints();
		gbc_btnTim.fill = GridBagConstraints.BOTH;
		gbc_btnTim.insets = new Insets(0, 0, 5, 0);
		gbc_btnTim.gridx = 3;
		gbc_btnTim.gridy = 1;
		contentPane.add(btnTim, gbc_btnTim);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btnTim.doClick();
			}
		});
		txtSearch.setToolTipText("Nhập thông tin");
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.fill = GridBagConstraints.BOTH;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 2;
		gbc_txtSearch.gridy = 1;
		contentPane.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(10, 0, 10, 0);
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					btnChon.doClick();
				}
			}
		});
		Redesign.Table(table);
		scrollPane.setViewportView(table);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridwidth = 4;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 3;
		contentPane.add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		btnChon = new JButton("CHỌN");
		Redesign.SubmitButton(btnChon);
		btnChon.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnChon);
		btnChon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					khachHang = danhSachKH.get(table.getSelectedRow());
					if (tinhChat == 'M' && khachHang instanceof TreEm) {
						JOptionPane.showMessageDialog(null, "Khách hàng mua không thể là trẻ em", "Lỗi", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else JOptionPane.showMessageDialog(null, "Chọn khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				dispose();
				if (basePanel instanceof LapDonHangGUI) {
					if (tinhChat == 'M') ((LapDonHangGUI) basePanel).setKHMua(khachHang);
					else ((LapDonHangGUI) basePanel).setKHNhan(khachHang);
				}
			}
		});
		
		JButton btnHuy = new JButton("HỦY BỎ");
		Redesign.SubmitButton(btnHuy);
		btnHuy.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnHuy);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	private void load(LinkedList<KhachHang> khachHang) {		
		if (khachHang != null)
			if (khachHang.size() > 0) {
				model.setRowCount(0);
				Object[] data = new Object[model.getColumnCount()];
				for (KhachHang kh: khachHang) {
					data[0] = kh.getCMND(); data[1] = kh.getTenKH(); data[2] = kh.getNgaySinh(); data[3] = kh.getGioiTinh();
					data[4] = kh.getSoDienThoai(); data[5] = (kh instanceof TreEm) ? "GH" : "";
					for (int i=0; i<model.getColumnCount(); i++) if (data[i] == null) data[i] = "";
					model.addRow(data);
				}
				return;
			}
		//JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
	}
}
