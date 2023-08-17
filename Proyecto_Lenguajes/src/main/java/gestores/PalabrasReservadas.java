package gestores;

public class PalabrasReservadas{
    
    
    String [] palabrasReservadas = {"and", "as", "assert", "break", "class", "continue", "def","del", "elif", "else", "except", "False", "finally", "for", "from", "global", "if", "import", "in", "is", "lambda", "None", "nonlocal", "not", "or", "pass", "raise", "return", "True", "try", "while", "with", "yield"};
    String [] palabrasBooleanas = {"True", "False"};
    String [] palabrasLogicas = {"AND", "OR", "NOT"};
    
    public void getTokenReservadas(String token){
        for (String valor : palabrasReservadas) {
            if (valor.equals(token)) {
                AnalizadorLexico.estadoActual = 10;
                return;
            } 
        }
    }
    public void getTokenBooleano(String token){
        for(String valor : palabrasBooleanas){
            if(valor.equals(token)){
                AnalizadorLexico.estadoActual = 12;
                return;
            }
        }
    }
    public void getTokenLogico(String token){
        for (String valor : palabrasLogicas){
            if(valor.equals(token)){
                AnalizadorLexico.estadoActual = 8;
            }
        }
    }
    
}
