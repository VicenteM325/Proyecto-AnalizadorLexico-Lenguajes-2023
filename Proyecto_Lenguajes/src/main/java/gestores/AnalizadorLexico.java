package gestores;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import jframe.ColorCellRenderer;

public class AnalizadorLexico {

    private javax.swing.table.DefaultTableModel tableModel;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea text;
    private GeneradorAFD afd;
    private PalabrasReservadas palabrasReservadas;
    private Token nuevoToken;
    String palabra;
    int posicion = 0;
    private int contErrores;
    int filaActual = 1;
    int columnaActual = 0; 
    static int estadoActual = 0;

   public AnalizadorLexico(DefaultTableModel tableModel, JTextArea text, JTable jTable1) {
        this.tableModel = tableModel;
        this.jTable1 = jTable1;
        this.text = text;
        this.afd = new GeneradorAFD();
        this.palabrasReservadas = new PalabrasReservadas();
        this.contErrores = 0;
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

    if (!token.isEmpty()) {
        Token nuevoToken = new Token(afd.getEstadoAceptacion(estadoActual), token, filaActual, columnaActual);
        agregarTokenATabla(nuevoToken); // Agregar el token a la tabla
    }
}

private void agregarTokenATabla(Token token) {
    PalabrasReservadas palabrasReservadas = new PalabrasReservadas();
    Patron patrones = new Patron();
    palabrasReservadas.getTokenReservadas(token.getToken());
    palabrasReservadas.getTokenBooleano(token.getToken());
    palabrasReservadas.getTokenLogico(token.getToken());
    

    String estadoAceptacion = afd.getEstadoAceptacion(estadoActual);
    String patron = patrones.obtenerPatron(estadoAceptacion);

    
    this.text.append(" El movimiento terminó en el estado: " + estadoAceptacion + " Patron: " + patron + " Token: " + token.getToken() + " Fila: " + token.getFila() + " Columna: " + token.getColumna() + "\n");
    
    if (estadoAceptacion.equals("Error")) {
        this.contErrores = this.contErrores + 1;
        System.out.println("Cantidad de errores " + contErrores + "------" + token.getToken());
    } else {
        Object[] rowData = {estadoAceptacion, patron, token.getToken(), token.getFila(), token.getColumna()};
        tableModel.addRow(rowData);
        Patron patron1 = new Patron();
        jTable1.setDefaultRenderer(Object.class, new ColorCellRenderer(patron1));
        
        }
    }
}

