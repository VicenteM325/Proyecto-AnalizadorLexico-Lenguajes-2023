package jframe;


import gestores.Patron;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorCellRenderer extends DefaultTableCellRenderer {
    private Patron patrones; // Supongo que tienes una clase Patrones

    public ColorCellRenderer(Patron patrones) {
        this.patrones = patrones;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Tipo en columna 0
        String token = table.getValueAt(row, 0).toString(); 
        //Se obtiene el color por categoria
        Color color = patrones.getColorPorCategoria(token); 
        
        cell.setForeground(color);
        //cell.setForeground(table.getForeground());

        return cell;
    }
}