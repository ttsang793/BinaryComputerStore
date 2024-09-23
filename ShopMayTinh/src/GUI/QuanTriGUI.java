package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

import java.awt.*;
import javax.swing.ImageIcon;

public class QuanTriGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JDesktopPane desktopPanel;
	private int mau = 1;
	
	private JPanel logoPanel;
	private JPanel buttonPanel;
	private JPanel headerPanel;
	private JButton btnMau;
	private JLabel lblLoiChao;
	private JButton btnDangXuat;
	private JPanel workPanel;
	private JLabel picLogo;

	/**
	 * Create the frame.
	 */
	public QuanTriGUI(DangNhap dangNhap, String tenNV, int m) {
		this.mau = m;
		setTitle("Admin cửa hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1356, 749);
		setMinimumSize(new Dimension(1366, 679));
		setLocationRelativeTo(null);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0,0,0,0));

		setContentPane(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{150, 0};
		gbl_mainPanel.rowHeights = new int[]{40, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 1.0};
		mainPanel.setLayout(gbl_mainPanel);
		
		logoPanel = new JPanel();
		FlowLayout fl_logoPanel = (FlowLayout) logoPanel.getLayout();
		fl_logoPanel.setVgap(0);
		fl_logoPanel.setHgap(0);
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
		gbl_headerPanel.rowHeights = new int[] {40, 0};
		gbl_headerPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_headerPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		headerPanel.setLayout(gbl_headerPanel);
		
		btnMau = new JButton("ĐỔI MÀU");
		GridBagConstraints gbc_btnMau = new GridBagConstraints();
		gbc_btnMau.fill = GridBagConstraints.BOTH;
		gbc_btnMau.gridx = 1;
		gbc_btnMau.gridy = 0;
		headerPanel.add(btnMau, gbc_btnMau);
		Redesign.SubmitButton(btnMau);
		btnMau.setMaximumSize(new Dimension(150, 40));
		btnMau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mau = (mau + 1 > ThemeColor.NUM_THEME) ? 1 : mau + 1;
				ThemeColor.setColor(mau);
				repainting();
			}
		});
		
		lblLoiChao = new JLabel("Xin chào, " + tenNV);
		lblLoiChao.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiChao.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblLoiChao.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		GridBagConstraints gbc_lblLoiChao = new GridBagConstraints();
		gbc_lblLoiChao.fill = GridBagConstraints.BOTH;
		gbc_lblLoiChao.gridx = 0;
		gbc_lblLoiChao.gridy = 0;
		headerPanel.add(lblLoiChao, gbc_lblLoiChao);
		
		btnDangXuat = new JButton("Đăng xuất");
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
		
		workPanel = new JPanel();
		workPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		GridBagConstraints gbc_workPanel = new GridBagConstraints();
		gbc_workPanel.fill = GridBagConstraints.BOTH;
		gbc_workPanel.gridx = 1;
		gbc_workPanel.gridy = 1;
		mainPanel.add(workPanel, gbc_workPanel);
		workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.X_AXIS));
		
		desktopPanel = new JDesktopPane();
		workPanel.add(desktopPanel);
		desktopPanel.setLayout(new CardLayout(0, 0));
		
		buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 1;
		mainPanel.add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		JButton btnNhanVien = new JButton("NHÂN VIÊN");
		buttonPanel.add(btnNhanVien);
		btnNhanVien.setMaximumSize(new Dimension(150, 40));
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPanel.removeAll();
				desktopPanel.add(new PhanQuyenGUI(), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});
		btnNhanVien.doClick();
		
		JButton btnPhuKien = new JButton("PHỤ KIỆN");
		btnPhuKien.setMaximumSize(new Dimension(150, 40));
		buttonPanel.add(btnPhuKien);
		btnPhuKien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPanel.removeAll();
				desktopPanel.add(new PhuKienGUI(), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});
		
		JButton btnLaptop = new JButton("LAPTOP");
		buttonPanel.add(btnLaptop);
		btnLaptop.setMaximumSize(new Dimension(150, 40));
		btnLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPanel.removeAll();
				desktopPanel.add(new LaptopGUI(), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});
		
		JButton btnNCC = new JButton("NHÀ CUNG CẤP");
		buttonPanel.add(btnNCC);
		btnNCC.setMaximumSize(new Dimension(150, 40));
		btnNCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPanel.removeAll();
				desktopPanel.add(new NhaCungCapGUI(), "name_772147942077200");
				desktopPanel.repaint();
				desktopPanel.validate();
			}
		});
		
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
		lblLoiChao.setForeground(ThemeColor.TEXT);
		btnDangXuat.setBorder(new EmptyBorder(0,0,0,0));
		
		workPanel.setBackground(ThemeColor.LIGHT_1);
		
		try {
			if (desktopPanel.getComponent(0) instanceof PhanQuyenGUI)
				((PhanQuyenGUI) desktopPanel.getComponent(0)).repainting();
			else if (desktopPanel.getComponent(0) instanceof PhuKienGUI)
				((PhuKienGUI) desktopPanel.getComponent(0)).repainting();
			else if (desktopPanel.getComponent(0) instanceof LaptopGUI)
				((LaptopGUI) desktopPanel.getComponent(0)).repainting();
			else if (desktopPanel.getComponent(0) instanceof NhaCungCapGUI)
				((NhaCungCapGUI) desktopPanel.getComponent(0)).repainting();
		}
		catch (Exception e) {}
	}
}
