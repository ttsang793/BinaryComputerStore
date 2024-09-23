package GUI;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import BUS.TraGopBUS;
import DTO.DonHang;
import DTO.ThanhToan;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class HoaDonGUI extends JPanel {
	private static final long serialVersionUID = 1L;	
	
	private JPanel traGopPanel;
	private JLabel lblTitleTraGop;
	private JLabel lblDonHang;
	private JLabel lblTienGop;
	private JLabel lblSoThang;
	private JLabel lblSoThangMuon;
	private JLabel lblPhiTraMuon;
	private JLabel lblGiamGia;
	private JLabel lblTong;
	
	private JComboBox<String> cbbDonHang;
	private JTextField txtTienGop;
	private JSpinner spnSoThang;
	private JTextField txtSoThangMuon;
	private JTextField txtPhiMuon;
	private JTextField txtGiamGia;
	private JTextField txtTong;
	
	private JScrollPane scrollPane;
	
	private JButton btnThanhToan;
	private JButton btnHuy;
	
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Mã HĐ", "Ngày lập", "Tổng tiền", "Trả góp"});
	private DefaultComboBoxModel<String> dhModel = new DefaultComboBoxModel<>();
	private JTable table;
	private LinkedList<DonHang> donHang;

	/**
	 * Create the panel.
	 */
	public HoaDonGUI(String maNV) {
		setBounds(0,0,1200,600);
		setBorder(new EmptyBorder(20,0,20,0));
		setLayout(new GridLayout(0, 2, 30, 0));
		
		traGopPanel = new JPanel();
		traGopPanel.setOpaque(false);
		add(traGopPanel);
		GridBagLayout gbl_traGopPanel = new GridBagLayout();
		gbl_traGopPanel.columnWidths = new int[] {140, 0};
		gbl_traGopPanel.rowHeights = new int[] {40, 25, 25, 25, 25, 25, 25, 25, 30};
		gbl_traGopPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_traGopPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		traGopPanel.setLayout(gbl_traGopPanel);
		
		lblTitleTraGop = new JLabel("THANH TOÁN TRẢ GÓP");
		lblTitleTraGop.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleTraGop.setFont(new Font("Montserrat", Font.BOLD, 24));
		GridBagConstraints gbc_lblTitleTraGop = new GridBagConstraints();
		gbc_lblTitleTraGop.fill = GridBagConstraints.BOTH;
		gbc_lblTitleTraGop.insets = new Insets(0, 0, 25, 0);
		gbc_lblTitleTraGop.gridwidth = 2;
		gbc_lblTitleTraGop.gridx = 0;
		gbc_lblTitleTraGop.gridy = 0;
		traGopPanel.add(lblTitleTraGop, gbc_lblTitleTraGop);
		
		lblDonHang = new JLabel("Chọn đơn hàng:");
		lblDonHang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDonHang = new GridBagConstraints();
		gbc_lblDonHang.fill = GridBagConstraints.BOTH;
		gbc_lblDonHang.insets = new Insets(0, 0, 25, 0);
		gbc_lblDonHang.gridx = 0;
		gbc_lblDonHang.gridy = 1;
		traGopPanel.add(lblDonHang, gbc_lblDonHang);
		
		cbbDonHang = new JComboBox<>(dhModel);
		cbbDonHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonHang dh = search(cbbDonHang.getItemAt(cbbDonHang.getSelectedIndex()));
				spnSoThang.setModel(new SpinnerNumberModel(1, 1, Integer.parseInt(dh.getThangTraGop()), 1));
				setValue();
			}
		});
		cbbDonHang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_cbbDonHang = new GridBagConstraints();
		gbc_cbbDonHang.fill = GridBagConstraints.BOTH;
		gbc_cbbDonHang.insets = new Insets(0, 0, 25, 0);
		gbc_cbbDonHang.gridx = 1;
		gbc_cbbDonHang.gridy = 1;
		traGopPanel.add(cbbDonHang, gbc_cbbDonHang);
		
		lblTienGop = new JLabel("Số tiền phải trả:");
		lblTienGop.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTienGop = new GridBagConstraints();
		gbc_lblTienGop.anchor = GridBagConstraints.NORTH;
		gbc_lblTienGop.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTienGop.insets = new Insets(0, 0, 25, 0);
		gbc_lblTienGop.gridx = 0;
		gbc_lblTienGop.gridy = 2;
		traGopPanel.add(lblTienGop, gbc_lblTienGop);
		
		txtTienGop = new JTextField();
		GridBagConstraints gbc_txtTienGop = new GridBagConstraints();
		gbc_txtTienGop.fill = GridBagConstraints.BOTH;
		gbc_txtTienGop.insets = new Insets(0, 0, 25, 0);
		gbc_txtTienGop.gridx = 1;
		gbc_txtTienGop.gridy = 2;
		traGopPanel.add(txtTienGop, gbc_txtTienGop);
		txtTienGop.setColumns(10);
		
		lblSoThang = new JLabel("Số tháng trả:");
		lblSoThang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSoThang = new GridBagConstraints();
		gbc_lblSoThang.anchor = GridBagConstraints.NORTH;
		gbc_lblSoThang.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSoThang.insets = new Insets(0, 0, 25, 0);
		gbc_lblSoThang.gridx = 0;
		gbc_lblSoThang.gridy = 3;
		traGopPanel.add(lblSoThang, gbc_lblSoThang);
		
		spnSoThang = new JSpinner();
		spnSoThang.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setValue();
			}
		});
		spnSoThang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_spnSoThang = new GridBagConstraints();
		gbc_spnSoThang.fill = GridBagConstraints.BOTH;
		gbc_spnSoThang.insets = new Insets(0, 0, 25, 0);
		gbc_spnSoThang.gridx = 1;
		gbc_spnSoThang.gridy = 3;
		traGopPanel.add(spnSoThang, gbc_spnSoThang);
		
		lblSoThangMuon = new JLabel("Số tháng bị trả muộn:");
		lblSoThangMuon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSoThangMuon = new GridBagConstraints();
		gbc_lblSoThangMuon.anchor = GridBagConstraints.NORTH;
		gbc_lblSoThangMuon.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSoThangMuon.insets = new Insets(0, 0, 25, 0);
		gbc_lblSoThangMuon.gridx = 0;
		gbc_lblSoThangMuon.gridy = 4;
		traGopPanel.add(lblSoThangMuon, gbc_lblSoThangMuon);
		
		txtSoThangMuon = new JTextField();
		txtSoThangMuon.setColumns(10);
		GridBagConstraints gbc_txtSoThangMuon = new GridBagConstraints();
		gbc_txtSoThangMuon.fill = GridBagConstraints.BOTH;
		gbc_txtSoThangMuon.insets = new Insets(0, 0, 25, 0);
		gbc_txtSoThangMuon.gridx = 1;
		gbc_txtSoThangMuon.gridy = 4;
		traGopPanel.add(txtSoThangMuon, gbc_txtSoThangMuon);
		
		lblPhiTraMuon = new JLabel("Phí trả muộn:");
		lblPhiTraMuon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPhiTraMuon = new GridBagConstraints();
		gbc_lblPhiTraMuon.anchor = GridBagConstraints.NORTH;
		gbc_lblPhiTraMuon.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPhiTraMuon.insets = new Insets(0, 0, 25, 0);
		gbc_lblPhiTraMuon.gridx = 0;
		gbc_lblPhiTraMuon.gridy = 5;
		traGopPanel.add(lblPhiTraMuon, gbc_lblPhiTraMuon);
		
		txtPhiMuon = new JTextField();
		txtPhiMuon.setColumns(10);
		GridBagConstraints gbc_txtPhiMuon = new GridBagConstraints();
		gbc_txtPhiMuon.fill = GridBagConstraints.BOTH;
		gbc_txtPhiMuon.insets = new Insets(0, 0, 25, 0);
		gbc_txtPhiMuon.gridx = 1;
		gbc_txtPhiMuon.gridy = 5;
		traGopPanel.add(txtPhiMuon, gbc_txtPhiMuon);
		
		lblGiamGia = new JLabel("Giảm giá:");
		lblGiamGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblGiamGia = new GridBagConstraints();
		gbc_lblGiamGia.anchor = GridBagConstraints.NORTH;
		gbc_lblGiamGia.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGiamGia.insets = new Insets(0, 0, 25, 0);
		gbc_lblGiamGia.gridx = 0;
		gbc_lblGiamGia.gridy = 6;
		traGopPanel.add(lblGiamGia, gbc_lblGiamGia);
		
		txtGiamGia = new JTextField();
		txtGiamGia.setColumns(10);
		GridBagConstraints gbc_txtGiamGia = new GridBagConstraints();
		gbc_txtGiamGia.fill = GridBagConstraints.BOTH;
		gbc_txtGiamGia.insets = new Insets(0, 0, 25, 0);
		gbc_txtGiamGia.gridx = 1;
		gbc_txtGiamGia.gridy = 6;
		traGopPanel.add(txtGiamGia, gbc_txtGiamGia);
		
		lblTong = new JLabel("Tổng:");
		lblTong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTong = new GridBagConstraints();
		gbc_lblTong.anchor = GridBagConstraints.NORTH;
		gbc_lblTong.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTong.insets = new Insets(0, 0, 25, 0);
		gbc_lblTong.gridx = 0;
		gbc_lblTong.gridy = 7;
		traGopPanel.add(lblTong, gbc_lblTong);
		
		txtTong = new JTextField();
		txtTong.setColumns(10);
		GridBagConstraints gbc_txtTong = new GridBagConstraints();
		gbc_txtTong.fill = GridBagConstraints.BOTH;
		gbc_txtTong.insets = new Insets(0, 0, 25, 0);
		gbc_txtTong.gridx = 1;
		gbc_txtTong.gridy = 7;
		traGopPanel.add(txtTong, gbc_txtTong);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 25, 0);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridwidth = 2;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 8;
		traGopPanel.add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		btnThanhToan = new JButton("THANH TOÁN");
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThanhToan tg = new ThanhToan(cbbDonHang.getItemAt(cbbDonHang.getSelectedIndex()), BUS.Time.toString(new Date()), maNV, spnSoThang.getValue().toString(), txtTong.getText());
				if (TraGopBUS.insert(tg)) {
					MessageBox.thanhCong("Thao tác trả góp thành công");
					TraGopBUS.load(); clear();
				}
				else MessageBox.loi("Thao tác trả góp thất bại");
			}
		});
		btnThanhToan.setPreferredSize(new Dimension(150, 30));
		buttonPanel.add(btnThanhToan);
		
		btnHuy = new JButton("HỦY BỎ");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnHuy.setPreferredSize(new Dimension(150, 30));
		buttonPanel.add(btnHuy);
		
		scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String maDH = model.getValueAt(table.getSelectedRow(), 0).toString();
				String tongTien = model.getValueAt(table.getSelectedRow(), 2).toString();
				
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					new ChiTietDonHangGUI(maDH, tongTien).setVisible(true);
				}
				
				try {
					cbbDonHang.setSelectedItem(maDH);
				}
				catch (Exception ex) { cbbDonHang.setSelectedIndex(0); }
			}
		});
		Redesign.Table(table);
		scrollPane.setViewportView(table);
		
		loadDH();
		repainting();
	}
	
	private void setValue() {
		DonHang dh = search(cbbDonHang.getItemAt(cbbDonHang.getSelectedIndex()));
		txtTienGop.setText(dh.getGopHangThang());
		TinhTienGop t = new TinhTienGop(txtTienGop.getText(), dh.getNgayDH(), spnSoThang.getValue().toString());
		txtSoThangMuon.setText(t.getThangTraMuon());
		txtPhiMuon.setText(t.getPhiTraMuon());
		txtGiamGia.setText(t.getGiamGia());
		txtTong.setText(t.getTienGop());

		Redesign.Spinner(spnSoThang);
	}
	
	private void loadDH() {
		donHang = TraGopBUS.getDanhSachDH();
		model.setRowCount(0);
		Object[] data = new Object[model.getColumnCount()];
		
		if (donHang != null)
			for (DonHang dh: donHang) {
				data[0] = dh.getMaDH(); data[1] = dh.getNgayDH(); data[2] = dh.getTongTien();
				if (dh.getThangTraGop().equals("0")) data[3] = "Không có";
				else {
					data[3] =  "Còn " + dh.getThangTraGop() + " tháng";
					dhModel.addElement(dh.getMaDH());
				}
				for (int i=0; i<model.getColumnCount(); i++) if (data[i] == null) data[i] = "";
				model.addRow(data);
			}
	}
	
	private DonHang search(String maDH) {
		for (DonHang dh: TraGopBUS.getDanhSachDH())
			if (dh.getMaDH().equals(maDH)) return dh;
		return null;
	}
	
	private void clear() {
		cbbDonHang.setSelectedIndex(0);
		txtTienGop.setText("");
		txtSoThangMuon.setText("1");
		txtPhiMuon.setText("");
		txtGiamGia.setText("");
		txtTong.setText("");
	}
	
	void repainting() {
		setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(table);
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);

		Redesign.GroupBox(traGopPanel, " Trả góp: ");
		Redesign.ComboBox(cbbDonHang);
		Redesign.TextField(txtTienGop);
		Redesign.TextField(txtSoThangMuon);
		Redesign.TextField(txtPhiMuon);
		Redesign.TextField(txtGiamGia);
		Redesign.TextField(txtTong);
		Redesign.Spinner(spnSoThang);
		
		lblTitleTraGop.setForeground(ThemeColor.TEXT);
		lblDonHang.setForeground(ThemeColor.TEXT);
		lblTienGop.setForeground(ThemeColor.TEXT);
		lblSoThang.setForeground(ThemeColor.TEXT);
		lblSoThangMuon.setForeground(ThemeColor.TEXT);
		lblPhiTraMuon.setForeground(ThemeColor.TEXT);
		lblGiamGia.setForeground(ThemeColor.TEXT);
		lblTong.setForeground(ThemeColor.TEXT);
		
		Redesign.SubmitButton(btnThanhToan);
		Redesign.SubmitButton(btnHuy);
	}
}
