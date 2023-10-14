/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorSintactico;

import analizadorLexico.TipoToken;

/**
 *
 * @author vicente
 */
public class TokenSintactico {
     private TipoToken tipo;
     private String lexema;
     private int linea;
     private int columna;


    public TokenSintactico() {

    }


    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Token{" + "tipo=" + tipo + ", lexema=" + lexema + ", linea=" + linea + ", columna=" + columna + '}';
    }
}
    
