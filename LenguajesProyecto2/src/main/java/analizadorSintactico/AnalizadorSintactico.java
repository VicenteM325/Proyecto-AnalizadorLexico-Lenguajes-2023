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
    private ArrayList<String> listaErrores = new ArrayList<String> ();
    Principal principal = new Principal();
    ArrayList<Token> listaToken = principal.getTokens();

   
    int estado=0;
    int indice;
    public void analizar(ArrayList<Token> listaToken) throws IOException{
        for(int i=0; i<listaToken.size(); i++){
             
            switch (estado) {
                case 0:
                    if(listaToken.get(i).getTipo() == tipoToken.COMENTARIO){
                        listaToken.get(i).setTipo(tipoToken.COMENTARIO);
                        estado = 4;
                    }
                    //Bloque asignacion IDENTIFICADOR
                case 1:
                    if(listaToken.get(i).getTipo() == tipoToken.IDENTIFICADOR){
                        estado = 1;
                     }

                    else {
                        if (listaToken.get(i).getTipo() != tipoToken.COMENTARIO) {
                             System.out.println( "Inicie con IDENTIFICADOR | CONDICIONAL | OPERADOR TERNARIO | CICLOS en la fila " 
                                    + listaToken.get(i).getLinea()
                                    + " y columna " + listaToken.get(i).getColumna());
                            estado = 0;
                        }
                    }
                break;
                 //Bloque asignacion , y mas IDENTIFICADORES
                case 2: 
                    if(listaToken.get(i).getTipo() == tipoToken.DELIMITADOR && listaToken.get(i-1).getTipo() == tipoToken.IDENTIFICADOR)  {
                        System.out.println("estoy en mas identificadors");
                        estado = 3;         
                    }
                    else if(listaToken.get(i).getTipo() == tipoToken.ASIGNACION){
                        System.out.println("case 2 asignacion");
                        estado = 3;
                    }
                    
                    else {

                         System.out.println( "se espera un signo = en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un signo = en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
            
                    }
                    break;
                 
                case 3:
                     if(listaToken.get(i).getTipo() == tipoToken.IDENTIFICADOR && listaToken.get(i-1).getTipo() == tipoToken.DELIMITADOR){
                         System.out.println("estoy en mas , e ident");
                         estado = 0;
                     }  
                    else if (listaToken.get(i).getTipo() == tipoToken.CADENA && listaToken.get(i - 1).getTipo() == tipoToken.ASIGNACION) {
                        System.out.println("entre a la asignacion CADENA");
                        listaToken.get(i).setTipo(tipoToken.EXPRESION);
                        estado = 0;
                        
                        
                    } else if (listaToken.get(i).getTipo() == tipoToken.ENTERO && listaToken.get(i - 1).getTipo() == tipoToken.ASIGNACION) {
                        System.out.println("entre a la asignacion ENTERO");
                        listaToken.get(i).setTipo(tipoToken.EXPRESION);
                        estado = 0;
                        
                        
                    } else if (listaToken.get(i).getTipo() == tipoToken.DECIMAL && listaToken.get(i - 1).getTipo() == tipoToken.ASIGNACION) {
                        listaToken.get(i).setTipo(tipoToken.EXPRESION);
                        System.out.println("entre a la asignacion DECIMAL");
                        
                    } else if (listaToken.get(i).getTipo() == tipoToken.BOOLEANO & listaToken.get(i - 1).getTipo() == tipoToken.ASIGNACION ){
                        listaToken.get(i).setTipo(tipoToken.EXPRESION);
                        estado = 0;
                        
                    }
                    else {
                        System.out.println( "se espera una CADENA | , | ENTERO | DECIMAL en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                        listaErrores.add("se espera un IDENTIFICADOR | CADENA | ENTERO | DECIMAL en la fila " + listaToken.get(i).getLinea() + " y columna "
                                + listaToken.get(i).getColumna() + " despues de " + listaToken.get(i - 1).getLexema());
                    }
                    break;

                case 4:
                    listaToken.get(i).setTipo(listaToken.get(i-2).getTipo());
                    if (listaToken.get(i-1).getTipo() == tipoToken.CADENA) {
                        listaToken.get(i).setTipo(tipoToken.EXPRESION);
                        System.out.println(listaToken.get(i).getTipo() + " " + listaToken.get(i).getLinea() + " " + listaToken.get(i).getColumna());
                    } else if (listaToken.get(i - 1).getTipo() == tipoToken.BOOLEANO) {
                        listaToken.get(i).setTipo(tipoToken.EXPRESION);
                    } else if (listaToken.get(i - 1).getTipo() == tipoToken.ENTERO) {
                        listaToken.get(i).setTipo(tipoToken.EXPRESION);

                    } else if (listaToken.get(i - 1).getTipo() == tipoToken.DECIMAL) {
                        listaToken.get(i).setTipo(tipoToken.EXPRESION);
                    } else{
                        listaToken.get(i).setTipo(tipoToken.ERROR_SINTACTICO);
                    }
                    break;


            }

            }
        }
        public void VerificaErrores(ArrayList<Token> tokens, JTable tbSintactico, JTable tbErrores){
                ManejadorTabla maneja = new ManejadorTabla();
                maneja.ReporteSintactico(tokens, tbSintactico);
                maneja.ReporteErroresSintacticos(listaErrores, tbErrores);
        }
        
    }
    
    
