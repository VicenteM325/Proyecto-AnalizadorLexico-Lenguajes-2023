package gestores;

import java.awt.Color;

public class Patron {
        private String[][] matrizPatrones = {
        {"Numero Entero", "[0-9]+"},
        {"Numero double", "[(0-9)+(.)][0-9]+"},
        {"Identificador Tipo Minuscula", "[(a-z)(_)][(a-z)(A-Z)(_)]*[0-9]+"},
        {"Identificador", "[(a-z)(A-Z)(_)][(a-z)(A-Z)(_)]*[0-9]+"},
        {"Aritmetico", "[(+)|(-)|(**)|(*)|(//)|(/)|(%)]"},
        {"Comparacion", "[\\==|\\!=|\\>|\\<|\\ >=|\\ <=]"},
        {"Logicos", "[(and)|(or)|(not)]"},
        {"Asignacion", "[=]"},
        {"Palabras Reservadas", "[Palabras Clave]"},
        {"Cadena", "[(\")|(').[(a-z)(A-Z)]+.(\")|(')]"},
        {"Booleana", "[True|False]"},
        {"Comentario" , "[(#).((a-z)(A-Z)+]"},
        {"Otros", "[( ( )|( ) )|({)|(})|([)|(])|(,)|(;)|(:)]"},
    };
    
    public String obtenerPatron(String token){
        for(String[] fila : matrizPatrones){
            if(fila[0].equals(token)){
            return fila[1];
            }
        }
        return "Patron Desconocido";
    }
    
    public java.awt.Color getColorPorCategoria(String token){
        if(token.equals("Numero Entero")){
            return java.awt.Color.BLUE;
        }
        if(token.equals("Numero double")){
            return java.awt.Color.ORANGE;
        }
        if(token.equals("Identificador Tipo Minuscula")){
            return java.awt.Color.WHITE;
        }
        return java.awt.Color.BLACK;
    }
    
}
