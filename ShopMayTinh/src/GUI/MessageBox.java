package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;

public abstract class MessageBox {
	private static JOptionPane getOptionPane(JComponent source) {
		return (JOptionPane)source.getParent().getParent();
	}
	
	private static void actionInfo(String thongBao, String tieuDe, int loai) {
		JLabel labelTB = new JLabel(thongBao);
		labelTB.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JButton buttonOK = new JButton("OK");
		buttonOK.setFocusPainted(false);
		buttonOK.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonOK.setBackground(new Color(255, 255, 255));
		buttonOK.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				getOptionPane((JComponent)e.getSource()).setValue(buttonOK);
		    }
		});
		JOptionPane.showOptionDialog(null, labelTB, tieuDe, JOptionPane.OK_OPTION, loai, null, new Object[] {buttonOK}, buttonOK);
	}
	
	static void thongBao(String thongBao) {
		actionInfo(thongBao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	static void thanhCong(String thongBao) {
		actionInfo(thongBao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void loi(String thongBao) {
		actionInfo(thongBao, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	static int cauHoi(String thongBao) {
		JLabel labelTB = new JLabel(thongBao);
		labelTB.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JButton buttonYes = new JButton("ĐỒNG Ý");
		buttonYes.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonYes.setFocusPainted(false);
		buttonYes.setBackground(new Color(255, 255, 255));
		buttonYes.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				getOptionPane((JComponent)e.getSource()).setValue(buttonYes);
		    }
		});
		
		JButton buttonNo = new JButton("HỦY BỎ");
		buttonNo.setFocusPainted(false);
		buttonNo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonNo.setBackground(new Color(255, 255, 255));
		buttonNo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				getOptionPane((JComponent)e.getSource()).setValue(buttonNo);
		    }
		});
		
		Object[] options = {buttonYes, buttonNo};
		return JOptionPane.showOptionDialog(null, labelTB, "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, buttonYes);
	}
}