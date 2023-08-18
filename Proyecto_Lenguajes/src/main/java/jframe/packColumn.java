/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author vicente
 */
public class packColumn {
    public static void packColumn(JTable table, int columnIndex, int margin) {
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        int width = 0;

        // Obtener el ancho preferido del encabezado de columna
        TableCellRenderer headerRenderer = column.getHeaderRenderer();
        if (headerRenderer == null) {
            headerRenderer = table.getTableHeader().getDefaultRenderer();
        }
        Component headerComp = headerRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, columnIndex);
        width = headerComp.getPreferredSize().width;

        // Obtener el ancho preferido de cada celda y ajustar el ancho de la columna según sea necesario
        for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
            TableCellRenderer cellRenderer = table.getCellRenderer(rowIndex, columnIndex);
            Component cellComp = cellRenderer.getTableCellRendererComponent(table, table.getValueAt(rowIndex, columnIndex), false, false, rowIndex, columnIndex);
            width = Math.max(width, cellComp.getPreferredSize().width);
        }

        // Agregar un margen adicional al ancho calculado
        width += 2 * margin;

        // Establecer el ancho calculado para la columna
        column.setPreferredWidth(width);
    }
}
