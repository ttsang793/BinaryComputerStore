package GUI;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BUS.*;
import DTO.PhanQuyen;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.io.File;
import javax.swing.SwingConstants;
//import javax.swing.UIManager;

//import com.formdev.flatlaf.*;
import javax.swing.ImageIcon;

public class DangNhap extends JFrame {
	private static final long serialVersionUID = 1L;
	private int mau = 1;
	private JLabel lblTitle;
	private JLabel picLogo;
	private JButton btnSee;
	private JButton btnDangNhap;
	private JButton btnThoat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
					
					/* UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels(); 
				        for (UIManager.LookAndFeelInfo look : looks) { 
				            System.out.println(look.getClassName()); 
				        } */
					
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./lib/Montserrat-Regular.ttf")));
					ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./lib/Montserrat-Bold.ttf")));
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;
	private JLabel lblMaNV;
	private JLabel lblPassword;
	
	private JTextField txtMaNV;
	private JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		setResizable(false);		
		PhanQuyenBUS.load();
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 399);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(ThemeColor.LIGHT_1);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("CHÀO MỪNG BẠN!");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Montserrat", Font.BOLD, 24));
		lblTitle.setForeground(ThemeColor.DARK_2);
		lblTitle.setBounds(10, 145, 430, 30);
		contentPane.add(lblTitle);
		
		lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMaNV.setBounds(10, 195, 80, 24);
		contentPane.add(lblMaNV);
		
		txtMaNV = new JTextField();
		Redesign.TextField(txtMaNV);
		txtMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaNV.setBounds(90, 195, 335, 26);
		contentPane.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPassword.setBounds(10, 240, 80, 24);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		Redesign.TextField((JTextField)txtPassword);
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPassword.setBounds(90, 240, 310, 26);
		contentPane.add(txtPassword);
		
		btnDangNhap = new JButton("ĐĂNG NHẬP");
		Redesign.SubmitButton(btnDangNhap);
		
		btnDangNhap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtMaNV.getText().isEmpty()) {
					MessageBox.loi("Vui lòng nhập mã nhân viên");
					return;
				}
				if (txtPassword.getPassword().length == 0){
					MessageBox.loi("Vui lòng nhập mật khẩu");
					return;
				}
				PhanQuyen nv = PhanQuyenBUS.dangNhap(txtMaNV.getText(), new String(txtPassword.getPassword()));
				if (nv != null) {
					MessageBox.thongBao("Đăng nhập thành công");
					txtPassword.setText("");
					setVisible(false);
					load(nv);
				}
				else MessageBox.loi("Đăng nhập thất bại");
			}
		});
		btnDangNhap.setBounds(60, 300, 120, 40);
		contentPane.add(btnDangNhap);
		
		btnThoat = new JButton("THOÁT");
		Redesign.SubmitButton(btnThoat);
		btnThoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnThoat.setBounds(270, 300, 120, 40);
		contentPane.add(btnThoat);
		
		picLogo = new JLabel("");
		picLogo.setIcon(new ImageIcon(ThemeColor.BIG_LOGO));
		picLogo.setBounds(25, 20, 405, 108);
		contentPane.add(picLogo);
		
		btnSee = new JButton("");
		btnSee.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtPassword.setEchoChar('\0');
			}			

			public void mouseReleased(MouseEvent e) {
				txtPassword.setEchoChar('•');
			}
		});
		btnSee.setBackground(ThemeColor.TEXT_CON);
		btnSee.setBorder(new LineBorder(ThemeColor.BORDER, 1));
		btnSee.setBounds(399, 240, 26, 26);
		contentPane.add(btnSee);
		
//		txtMaNV.setText("AD1");
//		txtPassword.setText("aA@!1");
		
		txtMaNV.setText("NV0007");
		txtPassword.setText("123456");
	}
	
	private void load(PhanQuyen nv) {
		NhaCungCapBUS.load();
		PhuKienBUS.load();
		LaptopBUS.load();
		PhieuNhapBUS.load();
		KhachHangBUS.load();
		TreEmBUS.load();
		DonHangBUS.load();
		TraGopBUS.load();
		if (nv.getChucVu().equals("Admin")) new QuanTriGUI(this, nv.getTenNV(), mau).setVisible(true);
		else {
			if (nv.getChucVu().equals("Quản lý")) NhanVienBUS.load(nv.getMaNV());
			new HomeGUI(this, nv, mau).setVisible(true);
		}
	}
	
	void setMau(int mau) {
		this.mau = mau;
		contentPane.setBackground(ThemeColor.LIGHT_1);
		lblTitle.setForeground(ThemeColor.DARK_2);
		picLogo.setIcon(new ImageIcon(ThemeColor.BIG_LOGO));
		lblMaNV.setForeground(ThemeColor.TEXT);
		lblPassword.setForeground(ThemeColor.TEXT);
		Redesign.TextField(txtMaNV);
		Redesign.TextField((JTextField)txtPassword);
		Redesign.Button(btnSee);
		Redesign.SubmitButton(btnDangNhap);
		Redesign.SubmitButton(btnThoat);
	}
}