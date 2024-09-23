package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.KhachHang;
import DTO.TreEm;
import BUS.KhachHangBUS;
import BUS.Time;
import BUS.TreEmBUS;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.LinkedList;
import java.awt.Font;
import java.awt.Color;

import org.jdesktop.swingx.JXDatePicker;

class ThemKHGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCMND;
	private JTextField txtHoTen;
	private JXDatePicker dtpNgaySinh;
	private JTextField txtSoDT;
	private KhachHang khachHang;
	private JRadioButton rdbNam;
	private JRadioButton rdbNu;
	private JRadioButton rdbChinhChu;
	private JRadioButton rdbGiamHo;
	private JLabel lblErrorCMND;
	private JLabel lblErrorHoTen;
	private JLabel lblErrorNgaySinh;
	private JLabel lblErrorSoDT;
	private LinkedList<JLabel> errorLabels = new LinkedList<>();

	/**
	 * Create the frame.
	 */
	public ThemKHGUI(JPanel basePanel, char tinhChat) {
		this.setResizable(false);
		setTitle("Thêm khách hàng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 525);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(ThemeColor.LIGHT_1);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("THÊM KHÁCH HÀNG");
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 20, 434, 40);
		contentPane.add(lblTitle);
		
		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCMND.setBounds(20, 80, 90, 25);
		contentPane.add(lblCMND);
		
		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCMND.setBounds(110, 80, 310, 25);
		contentPane.add(txtCMND);
		txtCMND.setColumns(10);
		
		JLabel lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHoTen.setBounds(20, 165, 91, 25);
		contentPane.add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtHoTen.setBounds(110, 165, 310, 25);
		contentPane.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNgaySinh.setBounds(20, 225, 90, 25);
		contentPane.add(lblNgaySinh);
		
		dtpNgaySinh = new JXDatePicker();
		Redesign.DatePicker(dtpNgaySinh);
		dtpNgaySinh.setDate(new Date());
		dtpNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		dtpNgaySinh.setBounds(110, 225, 310, 25);
		contentPane.add(dtpNgaySinh);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGioiTinh.setBounds(20, 285, 90, 25);
		contentPane.add(lblGioiTinh);
		
		rdbNam = new JRadioButton("Nam");
		rdbNam.setOpaque(false);
		rdbNam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbNam.setSelected(true);
		rdbNam.setBounds(110, 285, 90, 25);
		contentPane.add(rdbNam);
		
		rdbNu = new JRadioButton("Nữ");
		rdbNu.setOpaque(false);
		rdbNu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbNu.setBounds(220, 285, 90, 25);
		contentPane.add(rdbNu);

		rdbNam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbNu.setSelected(false);
				rdbNam.setSelected(true);
			}
		});
		rdbNu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbNam.setSelected(false);
				rdbNu.setSelected(true);
			}
		});
		
		JLabel lblSoDT = new JLabel("Số điện thoại:");
		lblSoDT.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSoDT.setBounds(20, 345, 90, 25);
		contentPane.add(lblSoDT);
		
		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSoDT.setBounds(110, 345, 310, 25);
		contentPane.add(txtSoDT);
		txtSoDT.setColumns(10);
		
		JButton btnLuu = new JButton("LƯU");
		Redesign.SubmitButton(btnLuu);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gioiTinh = (rdbNam.isSelected()) ? "Nam" : "Nữ";
				String[] checking;
				if (rdbChinhChu.isSelected()) {
					khachHang = new KhachHang(0, txtHoTen.getText(), Time.toString(dtpNgaySinh.getDate()), gioiTinh, txtSoDT.getText(), txtCMND.getText());
					checking = KhachHangBUS.kiemTra(khachHang);
				}
				else {
					khachHang = new TreEm(0, txtHoTen.getText(), Time.toString(dtpNgaySinh.getDate()), gioiTinh, txtSoDT.getText(), txtCMND.getText(), 0);
					checking = TreEmBUS.kiemTra((TreEm)khachHang);
				}
				if (checking.length > 0) {
					for (int i=0; i<errorLabels.size(); i++) errorLabels.get(i).setText(checking[i]);
					khachHang = new KhachHang();
					return;
				}
				JOptionPane.showMessageDialog(null, "Lưu khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				clear(); dispose();
				if (basePanel instanceof LapDonHangGUI) {
					if (tinhChat == 'M') ((LapDonHangGUI) basePanel).setKHMua(khachHang);
					else ((LapDonHangGUI) basePanel).setKHNhan(khachHang);
				}
			}
		});
		btnLuu.setBounds(78, 420, 100, 30);
		contentPane.add(btnLuu);
		
		JButton btnHuy = new JButton("HỦY");
		Redesign.SubmitButton(btnHuy);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear(); dispose();
			}
		});
		btnHuy.setBounds(256, 420, 100, 30);
		contentPane.add(btnHuy);
		
		rdbChinhChu = new JRadioButton("Chính chủ");
		rdbChinhChu.setOpaque(false);
		rdbChinhChu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbChinhChu.setSelected(true);
		rdbChinhChu.setBounds(110, 110, 100, 25);
		contentPane.add(rdbChinhChu);
		
		rdbGiamHo = new JRadioButton("Giám hộ");
		rdbGiamHo.setOpaque(false);
		rdbGiamHo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbGiamHo.setBounds(220, 110, 100, 25);
		contentPane.add(rdbGiamHo);
		
		lblErrorCMND = new JLabel("");
		lblErrorCMND.setForeground(new Color(255, 0, 0));
		lblErrorCMND.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblErrorCMND.setBounds(110, 135, 310, 25);
		contentPane.add(lblErrorCMND);
		
		lblErrorHoTen = new JLabel("");
		lblErrorHoTen.setForeground(Color.RED);
		lblErrorHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblErrorHoTen.setBounds(110, 195, 310, 25);
		contentPane.add(lblErrorHoTen);
		
		lblErrorNgaySinh = new JLabel("");
		lblErrorNgaySinh.setForeground(Color.RED);
		lblErrorNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblErrorNgaySinh.setBounds(110, 255, 310, 25);
		contentPane.add(lblErrorNgaySinh);
		
		lblErrorSoDT = new JLabel("");
		lblErrorSoDT.setForeground(Color.RED);
		lblErrorSoDT.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblErrorSoDT.setBounds(110, 375, 310, 25);
		contentPane.add(lblErrorSoDT);
		
		if (tinhChat == 'M') {
			rdbChinhChu.setEnabled(false);
			rdbGiamHo.setEnabled(false);
		}
		
		rdbChinhChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbGiamHo.setSelected(false);
				rdbChinhChu.setSelected(true);
			}
		});
		rdbGiamHo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbChinhChu.setSelected(false);
				rdbGiamHo.setSelected(true);
			}
		});
		
		initError();
	}
	
	private void clear() {
		txtCMND.setText("");
		txtHoTen.setText("");
		dtpNgaySinh.setDate(new Date());
		txtSoDT.setText("");
		rdbNam.setSelected(true); rdbNu.setSelected(false);
		rdbChinhChu.setSelected(true); rdbGiamHo.setSelected(false);
		clearError();
	}
	
	private void clearError() {
		for (JLabel label: errorLabels) label.setText("");
	}
	
	private void initError() {
		errorLabels.add(lblErrorCMND);
		errorLabels.add(lblErrorHoTen);
		errorLabels.add(lblErrorNgaySinh);
		errorLabels.add(lblErrorSoDT);
	}
	
	KhachHang getKhachHang() {
		return khachHang;
	}
}
