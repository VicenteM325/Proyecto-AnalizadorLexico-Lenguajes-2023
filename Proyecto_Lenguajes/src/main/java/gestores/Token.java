package gestores;


public class Token {
    
    private String tipo;
    private String valor;
    private String palabraReservada;
    private int fila;
    private int columna;

    public Token(String tipo,  String valor, int fila, int columna) {
        this.tipo = tipo;
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return valor;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    
    public String getpalabraReservada(){
        return palabraReservada;
    }


    
    
}
