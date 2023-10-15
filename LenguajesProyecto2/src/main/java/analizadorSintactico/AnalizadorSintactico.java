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

    int estado = 0;
    int indice;

    public void analizar(ArrayList<Token> listaToken) throws IOException {
        for (int i = 0; i < listaToken.size(); i++) {

            // Si no se encuentra un comentario, continúa con el análisis sintáctico normal
            switch (estado) {
                case 0:
                    if (listaToken.get(i).getTipo() == tipoToken.IDENTIFICADOR) {
                        System.out.println("Entre identificador ahora voy a estado 2");
                        estado = 1;
                    }
                    else if (listaToken.get(i).getTipo() == tipoToken.RESERVADA &&listaToken.get(i).getLexema().equals("if")){
                        System.out.println("por lo menos aqui si");
                        estado = 8;
                    }
                    else {
                        System.out.println("Inicie con IDENTIFICADOR | CONDICIONAL | OPERADOR TERNARIO | CICLOS en la fila "
                                + listaToken.get(i).getLinea()
                                + " y columna " + listaToken.get(i).getColumna());

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
                    } else if(listaToken.get(i).getLexema().equals("not")){
                        estado = 4;
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
                    if (listaToken.get(i).getTipo() == tipoToken.ARITMETICO && listaToken.get(i-1).getTipo() == tipoToken.DECLARACION_ASIGNACION_VARIABLES) {
                        estado = 4;
                    } else if(listaToken.get(i).getTipo() == tipoToken.COMPARACION && listaToken.get(i-1).getTipo() == tipoToken.DECLARACION_ASIGNACION_VARIABLES){
                        estado = 4;
                        System.out.println("declaracion a estado 4");
                    } else if(listaToken.get(i).getLexema().equals("is")){
                        estado = 7;
                        
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
                    //Valida la secuencia de ID ASIGNACION ENTERO (COMPARACION  | ARITMETICO) ENTERO
                case 4:
                    if (listaToken.get(i).getTipo() == tipoToken.ENTERO && listaToken.get(i-1).getTipo() == tipoToken.ARITMETICO 
                            || listaToken.get(i).getTipo() == tipoToken.ENTERO && listaToken.get(i-1).getTipo() == tipoToken.COMPARACION && !listaToken.get(i-1).getLexema().equals("==")){
                        listaToken.get(i).setTipo(tipoToken.DECLARACION_ASIGNACION_VARIABLES);
                    } else if(listaToken.get(i).getTipo() == tipoToken.ENTERO && listaToken.get(i-1).getTipo() == tipoToken.COMPARACION && listaToken.get(i-1).getLexema().equals("==")){
                        estado = 5;
                    } else if(listaToken.get(i).getTipo() == tipoToken.ENTERO && listaToken.get(i-1).getLexema().equals("not")){
                        estado = 5;
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
                    
                case 5:
                    if(listaToken.get(i).getLexema().equals("and") || listaToken.get(i).getLexema().equals("or")){
                        estado = 6;
                        System.out.println("fue asignado");
                    } else if(listaToken.get(i).getLexema().equals("==")){
                        estado = 7;
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
                   
                 case 6:
                    if(listaToken.get(i).getTipo() == tipoToken.ENTERO){
                        listaToken.get(i).setTipo(tipoToken.DECLARACION_ASIGNACION_VARIABLES);
                        estado = 3;
                        System.out.println("fue asignado");
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;

                case 7:
                    if (listaToken.get(i).getTipo() == tipoToken.ENTERO) {
                        listaToken.get(i).setTipo(tipoToken.DECLARACION_ASIGNACION_VARIABLES);
                        System.out.println("fue asignado");
                    } else if(listaToken.get(i).getLexema().equals("not")){
                        estado = 7 ;
                    }else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;
                    
                case 8:
                    if (listaToken.get(i).getTipo() == tipoToken.ENTERO) {
                        estado = 9;
                        System.out.println("enterooooo");
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;
                    

                case 9:
                    if (listaToken.get(i).getLexema().equals("==")) {
                        estado = 10;
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;    
 
                case 10:
                    if (listaToken.get(i).getTipo() == tipoToken.ENTERO) {
                        estado = 11;
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;
                    
                case 11:
                    if (listaToken.get(i).getLexema().equals(":")) {
                        estado = 12;
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 12;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;
                
                case 12:
                    
                    if (listaToken.get(i).getLexema().equals("print")) {
                        estado = 13;
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 13;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;

                case 13:
                    if (listaToken.get(i).getLexema().equals("(")) {
                        estado = 14;
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        listaToken.get(i).setTipo(listaToken.get(i-1).getTipo());
                        listaToken.get(i).setLexema(listaToken.get(i-1).getLexema());
                        listaToken.get(i).setLinea(listaToken.get(i-1).getLinea());
                        listaToken.get(i).setColumna(listaToken.get(i-1).getColumna());
                        estado = 14;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;

                case 14:
                    if (listaToken.get(i).getTipo() == tipoToken.CADENA) {
                        estado = 15;
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 15;
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                        estado = 0;
                    }

                    break;
                    
                case 15:
                    if (listaToken.get(i).getLexema().equals(")")) {
                        System.out.println("llegue aqui");
                        listaToken.get(i).setTipo(tipoToken.CONDICIONALES);
                    } else if (listaToken.get(i).getTipo() == tipoToken.COMENTARIO) {
                        listaToken.get(i).setLinea(i);
                        estado = 0;
                        listaToken.get(i).setTipo(listaToken.get(i-1).getTipo());
                        listaToken.get(i).setLexema(listaToken.get(i-1).getLexema());
                        listaToken.get(i).setLinea(listaToken.get(i-1).getLinea());
                        listaToken.get(i).setColumna(listaToken.get(i-1).getColumna());
                    } else {
                        System.out.println("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un ENTERO en la fila " + listaToken.get(i).getLinea() + " y columna "
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
