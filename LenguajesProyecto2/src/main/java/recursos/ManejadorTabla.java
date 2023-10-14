/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import analizadorLexico.Token;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vicente
 */
public class ManejadorTabla {

    public void ReporteSintactico(ArrayList<Token> list, JTable table) {
//         DefaultTableModel modelo = new DefaultTableModel();
//          table.setModel(modelo);
//          modelo.addColumn("Estructura");
//          modelo.addColumn("Bloque");
//          modelo.addColumn("Fila");
//          modelo.addColumn("columna");
//          for(int i=0; i<list.size();i++){
//              modelo.addRow(new Object[]{list.get(i).getTipo(), list.get(i).getLexema(),list.get(i).getLinea(),list.get(i).getColumna()});
//          }
//           
//    }

        DefaultTableModel modelo = new DefaultTableModel();
        table.setModel(modelo);
        modelo.addColumn("Estructura");
        modelo.addColumn("Bloque");
        modelo.addColumn("Fila");
        modelo.addColumn("Columna");

        StringBuilder lexemas = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);

            lexemas.append(token.getLexema()).append(" ");

            if (i == list.size() - 1 || token.getLinea() != list.get(i + 1).getLinea()) {
                modelo.addRow(new Object[]{token.getTipo(), lexemas.toString().trim(), list.get(i).getLinea(), list.get(i).getColumna()});
                lexemas.setLength(0);
            }
        }
    }
    
    public void ReporteErroresSintacticos(ArrayList<String> lista, JTable tabla){
         DefaultTableModel modelo = new DefaultTableModel();
          tabla.setModel(modelo);
          modelo.addColumn("Error");
          for(int i=0; i<lista.size();i++){
              modelo.addRow(new Object[]{lista.get(i)});
          }
    }
}
