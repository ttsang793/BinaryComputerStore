package GUI;

import java.io.Serializable;
import javax.swing.table.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class HeaderCellRenderer extends JButton implements TableCellRenderer, Serializable {
	private static final long serialVersionUID = 1L;
	
	public HeaderCellRenderer() {
        super();
        setOpaque(true);
    }
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	         int row, int column) {
		setBackground(ThemeColor.DARK_1);
		setForeground(ThemeColor.WHITE);
		setFont(new Font("Segoe UI", Font.BOLD + Font.ITALIC, 14));
		
		if (column < table.getColumnCount() - 1) super.setBorder(new MatteBorder(0, 0, 0, 1, ThemeColor.WHITE));
		else super.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		setText(value.toString());
		return this;
	}
}