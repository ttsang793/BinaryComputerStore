package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.NhaCungCapBUS;

import java.util.LinkedList;
import DTO.NhaCungCap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;

class NhaCungCapGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JLabel lblMaNCC;
	private JLabel lblTenNCC;
	private JLabel lblDiaChi;
	private JLabel lblSoDienThoai;
	private JLabel lblEmail;
	
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtDiaChi;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private JCheckBox chkMoKhoa;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Mã NCC", "Tên NCC", "Địa chỉ", "Điện thoại", "Email", ""});
	private LinkedList<NhaCungCap> nhaCungCap = new LinkedList<>();
	
	private JLabel lblErrorTenNCC;	
	private JLabel lblErrorDiaChi;	
	private JLabel lblErrorSoDT;
	private JLabel lblErrorEmail;
	private LinkedList<JLabel> errorLabels = new LinkedList<>();
	private JPanel buttonPanel;
	
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public NhaCungCapGUI() {
		setBorder(new EmptyBorder(20,0,20,0));
		setBounds(0, 0, 1200, 600);
		load();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{480, 0};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_infoPanel.insets = new Insets(0, 0, 0, 30);
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 0;
		add(infoPanel, gbc_infoPanel);
		infoPanel.setLayout(null);
		
		lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		lblTitle.setBounds(0, 0, 450, 30);
		infoPanel.add(lblTitle);
		
		lblMaNCC = new JLabel("Mã NCC:");
		lblMaNCC.setBounds(0, 60, 80, 25);
		infoPanel.add(lblMaNCC);
		lblMaNCC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtMaNCC = new JTextField();
		txtMaNCC.setEditable(false);
		txtMaNCC.setBounds(80, 60, 255, 25);
		infoPanel.add(txtMaNCC);
		txtMaNCC.setColumns(10);
		
		lblTenNCC = new JLabel("Tên NCC:");
		lblTenNCC.setBounds(0, 120, 80, 25);
		infoPanel.add(lblTenNCC);
		lblTenNCC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtTenNCC = new JTextField();
		txtTenNCC.setBounds(80, 120, 350, 25);
		infoPanel.add(txtTenNCC);
		txtTenNCC.setColumns(10);
		
		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(0, 180, 80, 25);
		lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		infoPanel.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(80, 180, 350, 25);
		infoPanel.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		lblSoDienThoai = new JLabel("Số ĐT:");
		lblSoDienThoai.setBounds(0, 240, 80, 25);
		infoPanel.add(lblSoDienThoai);
		lblSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(80, 240, 350, 25);
		infoPanel.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);		
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(0, 300, 80, 25);
		infoPanel.add(lblEmail);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(80, 300, 350, 25);
		infoPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(0, 370, 450, 30);
		infoPanel.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		JButton btnLuu = new JButton("LƯU");
		btnLuu.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnLuu);
		Redesign.SubmitButton(btnLuu);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhaCungCap ncc = new NhaCungCap(txtMaNCC.getText(), txtTenNCC.getText(), txtDiaChi.getText(), txtSoDienThoai.getText(), txtEmail.getText(), chkMoKhoa.isSelected());
				String[] checking = NhaCungCapBUS.kiemTra(ncc);
				if (checking.length > 0) {
					for (int i=0; i<errorLabels.size(); i++) errorLabels.get(i).setText(checking[i]);
					return;
				}
				if (txtMaNCC.getText().isEmpty()) {
					if (MessageBox.cauHoi("Bạn có muốn thêm nhà cung cấp này?") == JOptionPane.YES_OPTION) {
						if (NhaCungCapBUS.insert(ncc)) {
							MessageBox.thanhCong("Thêm nhà cung cấp thành công");
							NhaCungCapBUS.load();
							load(); clear();					
						}
						else MessageBox.loi("Đã có lỗi xảy ra, thêm nhà cung cấp thất bại");
					}
				}
				else {
					if (MessageBox.cauHoi("Bạn có muốn cập nhật nhà cung cấp này?") == JOptionPane.YES_OPTION) {
						if (NhaCungCapBUS.update(ncc)) {
							MessageBox.thanhCong("Cập nhật nhà cung cấp thành công");
							NhaCungCapBUS.load();
							load(); clear();
						}
						else MessageBox.loi("Đã có lỗi xảy ra, cập nhật nhà cung cấp thất bại");
					}
				}
			}
		});
		
		JButton btnClear = new JButton("HỦY BỎ");
		btnClear.setPreferredSize(new Dimension(120,30));
		buttonPanel.add(btnClear);
		Redesign.SubmitButton(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		lblErrorTenNCC = new JLabel("");
		lblErrorTenNCC.setForeground(new Color(255, 0, 0));
		lblErrorTenNCC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblErrorTenNCC.setBounds(100, 145, 330, 20);
		infoPanel.add(lblErrorTenNCC);
		
		lblErrorDiaChi = new JLabel("");
		lblErrorDiaChi.setForeground(Color.RED);
		lblErrorDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblErrorDiaChi.setBounds(100, 205, 330, 20);
		infoPanel.add(lblErrorDiaChi);
		
		lblErrorSoDT = new JLabel("");
		lblErrorSoDT.setForeground(Color.RED);
		lblErrorSoDT.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblErrorSoDT.setBounds(100, 265, 330, 20);
		infoPanel.add(lblErrorSoDT);
		
		lblErrorEmail = new JLabel("");
		lblErrorEmail.setForeground(Color.RED);
		lblErrorEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblErrorEmail.setBounds(100, 325, 330, 20);
		infoPanel.add(lblErrorEmail);
		
		chkMoKhoa = new JCheckBox("Mở khóa");
		chkMoKhoa.setOpaque(false);
		chkMoKhoa.setEnabled(false);
		chkMoKhoa.setSelected(true);
		chkMoKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		chkMoKhoa.setBounds(345, 60, 85, 25);
		infoPanel.add(chkMoKhoa);
		
		scrollPane = new JScrollPane();
		
		table = new JTable(model);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.gridheight = GridBagConstraints.REMAINDER;
		add(scrollPane, gbc_scrollPane);		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chkMoKhoa.setEnabled(true);
				NhaCungCap ncc = search(model.getValueAt(table.getSelectedRow(),0).toString());
				chkMoKhoa.setSelected(ncc.getTrangThai());
				txtMaNCC.setText(ncc.getMaNCC());
				txtTenNCC.setText(ncc.getTenNCC());
				txtDiaChi.setText(ncc.getDiaChiNCC());
				txtSoDienThoai.setText(ncc.getSoDTNCC());
				txtEmail.setText(ncc.getEmailNCC());
				clearError();
			}
		});
		scrollPane.setViewportView(table);
		
		initError();
		repainting();
	}
	
	private void load() {
		nhaCungCap = NhaCungCapBUS.getDanhSachNCC();
		model.setRowCount(0);
		Object[] data = new Object[model.getColumnCount()];
		
		if (nhaCungCap != null)
			for (NhaCungCap ncc: nhaCungCap) {
				data[0] = ncc.getMaNCC(); data[1] = ncc.getTenNCC(); data[2] = ncc.getDiaChiNCC();
				data[3] = ncc.getSoDTNCC(); data[4] = ncc.getEmailNCC(); data[5] = ncc.getTrangThai() ? 1 : 0;
				for (int i=0; i<model.getColumnCount(); i++) if (data[i] == null) data[i] = "";
				model.addRow(data);
			}
	}
	
	private void clear() {
		chkMoKhoa.setSelected(true);
		chkMoKhoa.setEnabled(false);
		txtMaNCC.setText("");
		txtTenNCC.setText("");
		txtDiaChi.setText("");
		txtSoDienThoai.setText("");
		txtEmail.setText("");
	}
	
	private void clearError() {
		for (JLabel label: errorLabels) label.setText("");
	}
	
	private NhaCungCap search(String maNCC) {
		for (NhaCungCap ncc: nhaCungCap) if (ncc.getMaNCC() == maNCC) return ncc;
		return null;
	}
	
	private void initError() {
		errorLabels.add(lblErrorTenNCC);	
		errorLabels.add(lblErrorDiaChi);	
		errorLabels.add(lblErrorSoDT);
		errorLabels.add(lblErrorEmail);
	}
	
	void repainting() {
		setBackground(ThemeColor.LIGHT_1);
		scrollPane.getViewport().setBackground(ThemeColor.LIGHT_1);
		Redesign.Table(table);
		
		lblTitle.setForeground(ThemeColor.TEXT);
		lblMaNCC.setForeground(ThemeColor.TEXT);
		lblTenNCC.setForeground(ThemeColor.TEXT);
		lblDiaChi.setForeground(ThemeColor.TEXT);
		lblSoDienThoai.setForeground(ThemeColor.TEXT);
		lblEmail.setForeground(ThemeColor.TEXT);
		chkMoKhoa.setForeground(ThemeColor.TEXT);
		
		Redesign.TextField(txtMaNCC);
		Redesign.TextField(txtTenNCC);
		Redesign.TextField(txtDiaChi);
		Redesign.TextField(txtSoDienThoai);
		Redesign.TextField(txtEmail);
		
		for (Component c: buttonPanel.getComponents()) Redesign.SubmitButton((JButton)c);
	}
}
