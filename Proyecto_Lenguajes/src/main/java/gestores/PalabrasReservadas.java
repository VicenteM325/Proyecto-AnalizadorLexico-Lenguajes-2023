package gestores;

public class PalabrasReservadas{
    
    public static String palabraReservada;
    String [] palabrasReservadas = {"and", "as", "assert", "break", "class", "continue", "def","del", "elif", "else", "except", "False", "finally", "for", "from", "global", "if", "import", "in", "is", "lambda", "None", "nonlocal", "not", "or", "pass", "raise", "return", "True", "try", "while", "with", "yield"};
    String [] palabrasBooleanas = {"True", "False"};
    String [] palabrasLogicas = {"and", "or", "not"};
    String [] aritmeticos = {"+", "-", "**","*", "//","/","%"};
    String [] otros = {"(",")","{","}","[","]",",",";",":"};
    
    public void getTokenReservadas(String token){
        for (String valor : palabrasReservadas) {
            if (valor.equals(token)) {
                AnalizadorLexico.estadoActual = 10;
                palabraReservada = token;
                return;
            } 
        }
    }
    public void getTokenBooleano(String token){
        for(String valor : palabrasBooleanas){
            if(valor.equals(token)){
                AnalizadorLexico.estadoActual = 12;
                palabraReservada = token;
                return;
            }
        }
    }
    public void getTokenLogico(String token){
        for (String valor : palabrasLogicas){
            if(valor.equals(token)){
                AnalizadorLexico.estadoActual = 8;
                palabraReservada = token;
                return;
            }
        }
    }
    public void getTokenAritmetico(String token) {
        for (String valor : aritmeticos) {
            if (valor.equals(token)) {
                palabraReservada = token;
                return;
            }
        }
    }
    public void getTokenOtros(String token) {
        for (String valor : otros) {
            if (valor.equals(token)) {
                palabraReservada = token;
                return;
            }
        }
    }    

}
