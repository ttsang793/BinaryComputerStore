package GUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import org.jdesktop.swingx.*;

interface Redesign {
	static void Table(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setDefaultEditor(Object.class, null);
		JTableHeader header = table.getTableHeader();
		
		header.setReorderingAllowed(false);
		
		HeaderCellRenderer headerRender = new HeaderCellRenderer();		
		headerRender.setPreferredSize(new Dimension(0,32));
		header.setDefaultRenderer(headerRender);
		
		table.setBackground(ThemeColor.TEXT_CON);
		table.setForeground(ThemeColor.TEXT);
		table.setSelectionBackground(ThemeColor.LIGHT_2);
		table.setSelectionForeground(ThemeColor.TEXT);
		table.setFocusable(false);
		table.setColumnSelectionAllowed(true);
		if (table.getRowHeight() == 16) table.setRowHeight(table.getRowHeight() + 12); //16 là chiều cao mặc định của dòng
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		
		TableColumnModel colModel = table.getColumnModel();
		TableModel model = table.getModel();
		int totalCol = colModel.getColumnCount();
		
		for (int i=0; i<totalCol; i++) {
			int cellSize = 0;
			int totalRow = table.getRowCount();
			for (int j=0; j<totalRow; j++)
				if (model.getValueAt(j, i) != null) {
					int newCellSize = model.getValueAt(j, i).toString().length() * 5;
					if (newCellSize > cellSize) cellSize = newCellSize;
				}
			colModel.getColumn(i).setPreferredWidth(cellSize);
		}
	}
	
	static void DatePicker(JXDatePicker datePicker) {
		JXMonthView month = datePicker.getMonthView();
		datePicker.getEditor().setBackground(ThemeColor.TEXT_CON);
		datePicker.getEditor().setForeground(ThemeColor.TEXT);
		datePicker.setMinimumSize(new Dimension(datePicker.getWidth(), 25));
		datePicker.getEditor().setEditable(false);
		datePicker.getEditor().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		datePicker.getEditor().setBorder(new LineBorder(ThemeColor.BORDER, 1));
		datePicker.setFormats("yyyy-MM-dd");
		
		//Chỉnh nền của tháng trong trình chọn tháng
		month.setMonthStringBackground(ThemeColor.LIGHT_2);
		month.setMonthStringForeground(ThemeColor.TEXT);
		
		//Chỉnh font chữ của trình chọn ngày
		month.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		//Lấy nút để hiển thị trình chọn ngày
		JButton button = (JButton)datePicker.getComponent(1);
		button.setBackground(ThemeColor.TEXT_CON);
		button.setBorder(new CompoundBorder(
			new MatteBorder(1, 0, 1, 1, ThemeColor.BORDER),
			new EmptyBorder(0, 1, 0, 1)
		));
	}
	
	@SuppressWarnings("rawtypes") 
	static void ComboBox(JComboBox comboBox) {
		comboBox.setBackground(ThemeColor.TEXT_CON);
		comboBox.setForeground(ThemeColor.TEXT);
		comboBox.getEditor().getEditorComponent().setBackground(ThemeColor.TEXT_CON);
		comboBox.getEditor().getEditorComponent().setForeground(ThemeColor.TEXT);
		
		comboBox.setMinimumSize(new Dimension(comboBox.getWidth(), 25));
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBox.setBorder(new LineBorder(ThemeColor.BORDER, 1));
		JButton button = (JButton)comboBox.getComponent(0);
		button.setBorder(new CompoundBorder(
			new MatteBorder(0, 1, 0, 0, ThemeColor.BORDER),
			new EmptyBorder(0, 5, 0, 4)
		));
	}
	
	static void GroupBox(JPanel panel, String title) {
		panel.setBackground(ThemeColor.LIGHT_1);
		panel.setBorder(new CompoundBorder(
			new TitledBorder(new LineBorder(ThemeColor.BORDER, 2), title, TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.ITALIC, 14), ThemeColor.TEXT), //Viền ngoài
			new EmptyBorder(5, 10, 5, 10) //Viền trong
		));
	}
	
	static void TextField(JTextField textField) {
		textField.setCaretColor(ThemeColor.TEXT);
		textField.setBorder(new LineBorder(ThemeColor.BORDER, 1));
		textField.setBackground(ThemeColor.TEXT_CON);
		textField.setForeground(ThemeColor.TEXT);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setMinimumSize(new Dimension(textField.getWidth(), 25));
		textField.setDisabledTextColor(ThemeColor.TEXT);
	}
	
	//Dùng cho các nút mang ý nghĩa quyết định
	static void SubmitButton(JButton button) {
		button.setBackground(ThemeColor.MID_1);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setFont(new Font("Segoe UI", Font.BOLD, 16));
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setFocusPainted(false);
		button.setForeground(ThemeColor.WHITE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(ThemeColor.MID_2);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(ThemeColor.MID_1);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				button.setContentAreaFilled(false);
				button.setOpaque(true);
				button.setBackground(ThemeColor.DARK_1);
				button.repaint();
			}
		});
	}
	
	//Dùng cho các nút chỉ mang ý nghĩa nhập liệu
	static void Button(JButton button) {		
		button.setBackground(ThemeColor.TEXT_CON);
		button.setForeground(ThemeColor.TEXT);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));		

		button.setBorder(new CompoundBorder(
			new LineBorder(ThemeColor.BORDER, 1),
			new EmptyBorder(5, 10, 5, 10)
		));
		
		button.setFocusPainted(false);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (button.isEnabled()) button.setBackground(ThemeColor.TEXT_CON_HOVER);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (button.isEnabled()) button.setBackground(ThemeColor.TEXT_CON);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (button.isEnabled()) {
					button.setContentAreaFilled(false);
					button.setOpaque(true);
					button.setBackground(ThemeColor.TEXT_CON_ACTIVE);
					button.repaint();
				}
			}
		});
	}
	
	//Dùng cho các nút ở header
		static void HeaderButton(JButton button) {
			button.setBackground(ThemeColor.LIGHT_2);
			button.setForeground(ThemeColor.TEXT);
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			button.setBorder(new EmptyBorder(0, 0, 0, 0));
			button.setFocusPainted(false);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					button.setBackground(ThemeColor.LIGHT_3);
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					button.setBackground(ThemeColor.LIGHT_2);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					button.setContentAreaFilled(false);
					button.setOpaque(true);
					button.setBackground(ThemeColor.MID_1);
					button.repaint();
				}
			});
		}
	
	static void Spinner(JSpinner spn) {
		spn.setBorder(new LineBorder(ThemeColor.BORDER, 1));
		spn.getComponent(0).setBackground(ThemeColor.TEXT_CON);
		spn.getComponent(1).setBackground(ThemeColor.TEXT_CON);		
		
		JTextField inputField = ((JSpinner.DefaultEditor)spn.getEditor()).getTextField();
		inputField.setHorizontalAlignment(SwingConstants.LEFT);
		TextField(inputField);
		inputField.setBorder(new EmptyBorder(0,0,0,0));
	}
}