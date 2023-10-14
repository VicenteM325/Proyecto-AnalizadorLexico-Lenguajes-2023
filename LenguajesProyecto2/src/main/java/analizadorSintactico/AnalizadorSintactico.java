package analizadorSintactico;

import analizadorLexico.TipoToken;
import analizadorLexico.Token;
import jFrame.Principal;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import recursos.ManejadorTabla;

/**
 *
 * @author vicente
 */
public class AnalizadorSintactico {

    private Token token;
    private TipoToken tipoToken;
    private ArrayList<String> listaErrores = new ArrayList<String>();
    Principal principal = new Principal();
    ArrayList<Token> listaToken = principal.getTokens();

    int estado = 1;
    int indice;

    public void analizar(ArrayList<Token> listaToken) throws IOException {
        for (int i = 0; i < listaToken.size(); i++) {

            // Si no se encuentra un comentario, continúa con el análisis sintáctico normal
            switch (estado) {
                case 0:
                    if (listaToken.get(i).getTipo() == tipoToken.IDENTIFICADOR) {
                        System.out.println("Entre identificador ahora voy a estado 2");
                        estado = 1;
                    } else {
                        System.out.println("Inicie con IDENTIFICADOR | CONDICIONAL | OPERADOR TERNARIO | CICLOS en la fila "
                                + listaToken.get(i).getLinea()
                                + " y columna " + listaToken.get(i).getColumna());
                        estado = 0;
                    }
                    break;

                case 1:
                    if (listaToken.get(i).getTipo() == tipoToken.ASIGNACION) {
                        estado = 2;
                    }
                    break;

                case 2:
                    if (listaToken.get(i).getTipo() == tipoToken.ENTERO || listaToken.get(i).getTipo() == tipoToken.CADENA || listaToken.get(i).getTipo() == tipoToken.DECIMAL || listaToken.get(i).getTipo() == tipoToken.BOOLEANO) {
                        listaToken.get(i).setTipo(tipoToken.DECLARACION_ASIGNACION_VARIABLES);
                        estado = 3;
                    } else if(listaToken.get(i).getTipo() == tipoToken.COMENTARIO){
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                    }
                    else {
                        System.out.println("se espera una CADENA | , | ENTERO | DECIMAL en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera una CADENA | , | ENTERO | DECIMAL en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }
                    break;

                case 3:
                    if (listaToken.get(i).getTipo() == tipoToken.ARITMETICO && listaToken.get(i-1).getTipo() == tipoToken.DECLARACION_ASIGNACION_VARIABLES || listaToken.get(i).getTipo() == tipoToken.LOGICO && listaToken.get(i-1).getTipo() == tipoToken.DECLARACION_ASIGNACION_VARIABLES) {
                        estado = 4;
                    } else if(listaToken.get(i).getTipo() == tipoToken.COMPARACION && listaToken.get(i-1).getTipo() == tipoToken.DECLARACION_ASIGNACION_VARIABLES){
                        estado = 4;
                    } 
                    
                    else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                    } else {
                        System.out.println("se espera una CADENA | , | ENTERO | DECIMAL en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera una CADENA | , | ENTERO | DECIMAL en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }
                    
                    break;
                    
                case 4:
                    if (listaToken.get(i).getTipo() == tipoToken.ENTERO && listaToken.get(i-1).getTipo() == tipoToken.ARITMETICO || listaToken.get(i).getTipo() == tipoToken.ENTERO && listaToken.get(i-1).getTipo() == tipoToken.COMPARACION) {
                        listaToken.get(i).setTipo(tipoToken.DECLARACION_ASIGNACION_VARIABLES);
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                    } else {
                        System.out.println("se espera una CADENA | , | ENTERO | DECIMAL en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera una CADENA | , | ENTERO | DECIMAL en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;

            }
        }
    }

    public void VerificaErrores(ArrayList<Token> tokens, JTable tbSintactico, JTable tbErrores) {
        ManejadorTabla maneja = new ManejadorTabla();
        maneja.ReporteSintactico(tokens, tbSintactico);
        maneja.ReporteErroresSintacticos(listaErrores, tbErrores);
    }

}
