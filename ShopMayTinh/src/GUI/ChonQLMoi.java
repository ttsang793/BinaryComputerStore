package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.PhanQuyenBUS;
import DTO.PhanQuyen;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class ChonQLMoi extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<PhanQuyen> cbbNhanVien;
	private DefaultComboBoxModel<PhanQuyen> nvModel = new DefaultComboBoxModel<>();

	/**
	 * Create the dialog.
	 */
	public ChonQLMoi(String maNV, NhanVienGUI basePanel) {
		setTitle("Chọn quản lý mới");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(15, 5, 15, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JLabel lblNewLabel = new JLabel("Chọn quản lý mới:");
			lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			cbbNhanVien = new JComboBox<>(nvModel);
			cbbNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			contentPanel.add(cbbNhanVien);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CHỌN");
				okButton.setBackground(new Color(255, 255, 255));
				okButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						basePanel.dieuPhoi(maNV, cbbNhanVien.getItemAt(cbbNhanVien.getSelectedIndex()).getMaQL());
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("HỦY BỎ");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
				buttonPane.add(cancelButton);
			}
		}
		loadNV();
	}
	
	private void loadNV() {
		for (PhanQuyen nv: PhanQuyenBUS.getDanhSachNV())
			if (nv.getChucVu().equals("Quản lý")) nvModel.addElement(nv);
	}
}
