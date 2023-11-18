
package Modelo;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;


public class GestionEncabezado implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent encabeza = null;
        encabeza = new JLabel((String)value);
        ((JLabel)encabeza).setHorizontalAlignment(SwingConstants.CENTER);
        Object cellValue = value;
        encabeza.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 0, 204)));
        encabeza.setForeground(new java.awt.Color(204, 0, 204));
        encabeza.setFont(new java.awt.Font("Yu Gothic UI", 1, 16));
        
        return encabeza;
        }
    
}
    

