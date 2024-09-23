package testGUI;

import java.awt.EventQueue;
import java.awt.Font;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.formdev.flatlaf.*;
import java.awt.Color;

public class Hello extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatLightLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hello frame = new Hello();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Hello() {
		setTitle("Java");
		setBounds(100, 100, 637, 472);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("hello");
		button.setBounds(65, 45, 124, 102);
		button.setFont(new Font("Segoe UI", Font.BOLD, 30));
		getContentPane().add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Java", "C#", "Python"}));
		comboBox.setBounds(65, 196, 131, 32);
		getContentPane().add(comboBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Bóng đá");
		try {
		chckbxNewCheckBox.setSelectedIcon(new ImageIcon(ImageIO.read(new File("yes.png"))));
		chckbxNewCheckBox.setIcon(new ImageIcon(ImageIO.read(new File("none.png"))));
		}
		catch(Exception e) {
			System.exit(ABORT);
		}
		chckbxNewCheckBox.setForeground(new Color(255, 0, 128));
		chckbxNewCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		chckbxNewCheckBox.setBounds(229, 70, 303, 174);
		getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxBngR = new JCheckBox("Bóng rổ");
		chckbxBngR.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		chckbxBngR.setBounds(65, 299, 179, 32);
		getContentPane().add(chckbxBngR);
		
		JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("Tennis");
		chckbxNewCheckBox_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		chckbxNewCheckBox_1_1.setBounds(65, 350, 179, 32);
		getContentPane().add(chckbxNewCheckBox_1_1);
	}
}
