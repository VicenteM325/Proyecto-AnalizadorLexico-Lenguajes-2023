package gestores;

import java.awt.Color;


public class Token {
    
        private String tipo;
    private String valor;
    private int fila;
    private int columna;
    private String categoria;

    public Token(String tipo, String valor, int fila, int columna) {
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
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
           
    }


    
    
}
