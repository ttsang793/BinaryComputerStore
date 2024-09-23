package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import DTO.PhanQuyen;
import java.awt.FlowLayout;

public class HomeGUI extends JFrame {
	private int mau = 1;
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;	

	private JPanel logoPanel;
	private JLabel picLogo;
	
	private JPanel headerPanel;
	private JLabel lblLoiChao;
	private JButton btnMau;
	private String loiChao;
	private JButton btnDangXuat;
	
	private JPanel workPanel;
	private JDesktopPane desktopPanel;
	
	private JButton btnNhapHang;
	private JButton btnDonHang;
	private JButton btnHoaDon;
	private JButton btnDSNhap;
	private JButton btnDSNV;
	private JButton btnThongKe;
	private Map<Integer, JButton[]> btnChucNang = new HashMap<>(); 
	
	private void addButton() {
		btnChucNang.put(0, new JButton[] {btnNhapHang});
		btnChucNang.put(1, new JButton[] {btnDonHang});
		btnChucNang.put(2, new JButton[] {btnHoaDon});
		btnChucNang.put(3, new JButton[] {btnDSNhap, btnDSNV, btnThongKe});
	}
	
	private JPanel buttonPanel;
	private void addToPanel(int quyen) {
		int mask = 1; int i = 0;
		while (mask <= quyen) {
			if ((quyen & mask) == mask)
				for (int j=0; j<btnChucNang.get(i).length; j++) buttonPanel.add(btnChucNang.get(i)[j]);
			mask <<= 1; i++;
		}
		((JButton) buttonPanel.getComponent(0)).doClick();
	}

	/**
	 * Create the frame.
	 */
	public HomeGUI(DangNhap dangNhap, PhanQuyen nv, int m) {
		this.mau = m;
		if (BUS.Time.isBirthday(nv.getNgaySinh()))
			loiChao = "Chúc mừng sinh nhật " + nv.getTenNV() + ". Hi vọng bạn có một ngày tuyệt vời!";
		else
			loiChao = "Xin chào, " + nv.getTenNV();
		
		setTitle("Phần mềm quản lý cửa hàng laptop Binary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 679);
		setMinimumSize(new Dimension(1366, 679));
		setLocationRelativeTo(null);
		mainPanel = new JPanel();

		setContentPane(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] {150, 0};
		gbl_mainPanel.rowHeights = new int[] {40, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 1.0};
		mainPanel.setLayout(gbl_mainPanel);
		
		logoPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) logoPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		GridBagConstraints gbc_logoPanel = new GridBagConstraints();
		gbc_logoPanel.fill = GridBagConstraints.BOTH;
		gbc_logoPanel.gridx = 0;
		gbc_logoPanel.gridy = 0;
		mainPanel.add(logoPanel, gbc_logoPanel);
		
		picLogo = new JLabel("");
		logoPanel.add(picLogo);
		
		headerPanel = new JPanel();
		GridBagConstraints gbc_headerPanel = new GridBagConstraints();
		gbc_headerPanel.fill = GridBagConstraints.BOTH;
		gbc_headerPanel.gridx = 1;
		gbc_headerPanel.gridy = 0;
		mainPanel.add(headerPanel, gbc_headerPanel);
		GridBagLayout gbl_headerPanel = new GridBagLayout();
		gbl_headerPanel.columnWidths = new int[] {0, 40, 100};
		gbl_headerPanel.rowHeights = new int[]{40, 0};
		gbl_headerPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_headerPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		headerPanel.setLayout(gbl_headerPanel);
		
		lblLoiChao = new JLabel(loiChao);
		lblLoiChao.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiChao.setMaximumSize(new Dimension(lblLoiChao.getWidth(), 50));
		lblLoiChao.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblLoiChao.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		GridBagConstraints gbc_lblLoiChao = new GridBagConstraints();
		gbc_lblLoiChao.fill = GridBagConstraints.BOTH;
		gbc_lblLoiChao.gridx = 0;
		gbc_lblLoiChao.gridy = 0;
		headerPanel.add(lblLoiChao, gbc_lblLoiChao);

		btnMau = new JButton("ĐỔI MÀU");
		GridBagConstraints gbc_btnMau = new GridBagConstraints();
		gbc_btnMau.fill = GridBagConstraints.BOTH;
		gbc_btnMau.gridx = 1;
		gbc_btnMau.gridy = 0;
		btnMau.setMaximumSize(new Dimension(150, 40));
		btnMau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mau = (mau + 1 > ThemeColor.NUM_THEME) ? 1 : mau + 1;
				ThemeColor.setColor(mau);
				repainting();
			}
		});
		headerPanel.add(btnMau, gbc_btnMau);
		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setMaximumSize(new Dimension(150, 40));
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MessageBox.cauHoi("Bạn có muốn đăng xuất?") == JOptionPane.YES_OPTION) {
					dangNhap.setMau(mau);
					dispose();
					dangNhap.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_btnDangXuat = new GridBagConstraints();
		gbc_btnDangXuat.fill = GridBagConstraints.BOTH;
		gbc_btnDangXuat.gridx = 2;
		gbc_btnDangXuat.gridy = 0;
		headerPanel.add(btnDangXuat, gbc_btnDangXuat);
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(ThemeColor.MID_1);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 1;
		mainPanel.add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		workPanel = new JPanel();
		workPanel.setBackground(ThemeColor.LIGHT_1);
		workPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		GridBagConstraints gbc_workPanel = new GridBagConstraints();
		gbc_workPanel.fill = GridBagConstraints.BOTH;
		gbc_workPanel.gridx = 1;
		gbc_workPanel.gridy = 1;
		mainPanel.add(workPanel, gbc_workPanel);
		workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.X_AXIS));
		
		desktopPanel = new JDesktopPane();
		desktopPanel.setBackground(new Color(255, 0, 128));
		workPanel.add(desktopPanel);
		desktopPanel.setLayout(new CardLayout(0, 0));
		
		btnNhapHang = new JButton("NHẬP HÀNG");
		btnNhapHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (desktopPanel != null) desktopPanel.removeAll();
				desktopPanel.add(new NhapHangGUI(nv.getMaNV()), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});
		Redesign.SubmitButton(btnNhapHang);
		btnNhapHang.setMaximumSize(new Dimension(150,40));
		
		btnDonHang = new JButton("LẬP ĐƠN HÀNG");
		btnDonHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (desktopPanel != null) desktopPanel.removeAll();
				desktopPanel.add(new LapDonHangGUI(nv.getMaNV()), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});
		btnDonHang.setMaximumSize(new Dimension(150, 40));
		
		btnHoaDon = new JButton("HÓA ĐƠN");
		btnHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (desktopPanel != null) desktopPanel.removeAll();
				desktopPanel.add(new HoaDonGUI(nv.getMaNV()), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});
		btnHoaDon.setMaximumSize(new Dimension(150, 40));
		
		btnDSNhap = new JButton("ĐƠN ĐÃ NHẬP");
		btnDSNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (desktopPanel != null) desktopPanel.removeAll();
				desktopPanel.add(new XemPhieuNhapGUI(nv.getMaQL()), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});
		btnDSNhap.setMaximumSize(new Dimension(150, 40));
		
		btnDSNV = new JButton("NHÂN VIÊN");
		btnDSNV.setMaximumSize(new Dimension(150, 40));
		btnDSNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (desktopPanel != null) desktopPanel.removeAll();
				desktopPanel.add(new NhanVienGUI(nv.getMaQL()), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});

		btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.setMaximumSize(new Dimension(150, 40));
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (desktopPanel != null) desktopPanel.removeAll();
				desktopPanel.add(new ThongKeGUI(), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});

		addButton();
		addToPanel(nv.getQuyen());
		
		repainting();
	}
	
	private void repainting() {
		picLogo.setIcon(new ImageIcon(ThemeColor.LOGO));
		
		for (Component c: buttonPanel.getComponents()) Redesign.SubmitButton((JButton)c);
		buttonPanel.setBackground(ThemeColor.MID_1);
		
		logoPanel.setBackground(ThemeColor.LIGHT_1);
		headerPanel.setBackground(ThemeColor.LIGHT_2);
		Redesign.HeaderButton(btnMau);
		Redesign.HeaderButton(btnDangXuat);
		btnDangXuat.setBorder(new EmptyBorder(0,0,0,0));
		lblLoiChao.setForeground(ThemeColor.TEXT);
		btnDangXuat.setForeground(ThemeColor.TEXT);
		
		workPanel.setBackground(ThemeColor.LIGHT_1);
		
		try {
			if (desktopPanel.getComponent(0) instanceof NhapHangGUI)
				((NhapHangGUI) desktopPanel.getComponent(0)).repainting();
			else if (desktopPanel.getComponent(0) instanceof LapDonHangGUI)
				((LapDonHangGUI) desktopPanel.getComponent(0)).repainting();
			else if (desktopPanel.getComponent(0) instanceof HoaDonGUI)
				((HoaDonGUI) desktopPanel.getComponent(0)).repainting();
			else if (desktopPanel.getComponent(0) instanceof XemPhieuNhapGUI)
				((XemPhieuNhapGUI) desktopPanel.getComponent(0)).repainting();
			else if (desktopPanel.getComponent(0) instanceof NhanVienGUI)
				((NhanVienGUI) desktopPanel.getComponent(0)).repainting();
			else if (desktopPanel.getComponent(0) instanceof ThongKeGUI)
				((ThongKeGUI) desktopPanel.getComponent(0)).repainting();
		}
		catch (Exception e) {}
	}
}
