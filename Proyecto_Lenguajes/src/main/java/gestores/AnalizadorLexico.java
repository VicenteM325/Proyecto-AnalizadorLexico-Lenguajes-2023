package gestores;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import jframe.ColorCellRenderer;

public class AnalizadorLexico {

    private javax.swing.table.DefaultTableModel tableModel;
    private javax.swing.table.DefaultTableModel tableModelError;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea text;
    private javax.swing.JTextPane textEditor;
    private GeneradorAFD afd;
    private PalabrasReservadas palabrasReservadas;
    public Token nuevoToken;
    String palabra;
    int posicion = 0;
    private int contErrores;
    int filaActual = 1;
    int columnaActual = 0; 
    static int estadoActual = 0;
    Patron patrones = new Patron();


   public AnalizadorLexico(DefaultTableModel tableModel, DefaultTableModel tableModelError, JTextArea text, JTextPane textEditor, JTable jTable1) {
        this.tableModel = tableModel;
        this.tableModelError = tableModelError;
        this.jTable1 = jTable1;
        this.text = text;
        this.afd = new GeneradorAFD();
        this.palabrasReservadas = new PalabrasReservadas();
        this.contErrores = 0;
        this.textEditor = textEditor;

    }

    public void Verificar(String palabra) {
        this.palabra = palabra;
        while (posicion < palabra.length()) {
            getToken();

        }

    }

    public void getToken() {
     estadoActual = 0;
    boolean seguirLeyendo = true;
    char tmp;
    String token = "";

    while ((seguirLeyendo) && posicion < palabra.length()) {
        tmp = palabra.charAt(posicion);
        // Manejo de posicion de fila y columna
        if (tmp == '\n') {
            filaActual++;
            columnaActual = 1;
        } else if (tmp != ' ' && tmp != '\r' && tmp != '\t' && tmp != '\b' && tmp != '\f') {
            columnaActual++;
        }
        // Manejo de lectura de tokens
        if (tmp == ' ' || tmp == '\n' || tmp == '\r' || tmp == '\t' || tmp == '\b' || tmp == '\f') {
            if(tmp == ' ' && estadoActual == 13){
                seguirLeyendo = true;
                token += tmp;
                columnaActual++;
            } 
            else{
              seguirLeyendo = false;
            }
        } else {
            int estadoTemporal = afd.getSiguienteEstado(estadoActual, afd.getIntCaracter(tmp));
            this.text.append("Estado actual = " + estadoActual + " Fila = " + filaActual + " Columna = " + columnaActual + " Caracter = " + tmp + " Transicion a " + estadoTemporal + "\n");
            token += tmp;
            estadoActual = estadoTemporal;
        }
        posicion++;
    }
    
    if (!token.trim().isEmpty()) {
        String estadoAceptacion = afd.getEstadoAceptacion(estadoActual);
        Token nuevoToken = new Token(afd.getEstadoAceptacion(estadoActual), token, filaActual, columnaActual);
        agregarTokenATabla(nuevoToken); // Agregar el token a la tabla
        

        
        
    }
}

private void agregarTokenATabla(Token token) {
    PalabrasReservadas palabrasReservadas = new PalabrasReservadas();
    palabrasReservadas.getTokenReservadas(token.getToken());
    palabrasReservadas.getTokenBooleano(token.getToken());
    palabrasReservadas.getTokenLogico(token.getToken());
    

    String estadoAceptacion = afd.getEstadoAceptacion(estadoActual);
    String patron = patrones.obtenerPatron(estadoAceptacion);
    

    
    this.text.append(" El movimiento terminó en el estado: " + estadoAceptacion + " Patron: " + patron + " Token: " + token.getToken() + " Fila: " + token.getFila() + " Columna: " + token.getColumna() + "\n");
    
    if (estadoAceptacion.equals("Error")) {
        this.contErrores = this.contErrores + 1;
        Object[] rowData = {token.getToken(), token.getFila(), token.getColumna()};
        tableModelError.addRow(rowData);
        System.out.println("Cantidad de errores " + contErrores + "------" + token.getToken() + " Fila " + token.getFila() + " Columna " + token.getColumna());
    } else {
        
        Object[] rowData = {estadoAceptacion, patron, token.getToken(), token.getFila(), token.getColumna()};
        tableModel.addRow(rowData);
        
        Color textColor = patrones.getColorPorCategoria(estadoAceptacion);
        patrones.colorearEditorTexto(textEditor, token.getToken() , textColor);
        
        Patron patron1 = new Patron();
        jTable1.setDefaultRenderer(Object.class, new ColorCellRenderer(patron1));
        
        }
    }
}

