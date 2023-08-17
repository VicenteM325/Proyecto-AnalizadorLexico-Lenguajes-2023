package gestores;

import static gestores.AnalizadorLexico.estadoActual;

public class GeneradorAFD {
        private int matriz[][] = new int [17][16];
    int estadosFinalizacion[] = new int[15];
    String descripcionFinalizacion[] = new String[15];
    
    
    public GeneradorAFD(){
        matriz[0][0] = 1;  matriz[0][1] = -1;    matriz[0][2] = 4;      matriz[0][3] = 4;      matriz[0][4] = 6;      matriz[0][5] = 7;        matriz[0][6] = 8;      matriz[0][7] = 9;      matriz[0][8] = 10;     matriz[0][9] = 11;     matriz[0][10] = 12;    matriz[0][11] = 13;      matriz[0][12] = 14;      matriz[0][13] = 15;        matriz[0][14] = 16;

        matriz[1][0] = 1;  matriz[1][1] = 2;     matriz[1][2] = -1;     matriz[1][3] = -1;     matriz[1][4] = -1;     matriz[1][5] = -1;       matriz[1][6] = -1;     matriz[1][7] = -1;     matriz[1][8] = -1;     matriz[1][9] = -1;     matriz[1][10] = -1;    matriz[1][11] = -1;       matriz[1][12] = -1;      matriz[1][13] = -1;       matriz[1][14] = 0;

        matriz[2][0] = 3;  matriz[2][1] = -1;    matriz[2][2] = -1;     matriz[2][3] = -1;     matriz[2][4] = -1;     matriz[2][5] = -1;       matriz[2][6] = -1;     matriz[2][7] = -1;     matriz[2][8] = -1;     matriz[2][9] = -1;     matriz[2][10] = -1;    matriz[2][11] = -1;       matriz[2][12] = -1;      matriz[2][13] = -1;        matriz[2][14] = 0;

        matriz[3][0] = 3;  matriz[3][1] = -1;    matriz[3][2] = -1;     matriz[3][3] = -1;     matriz[3][4] = -1;     matriz[3][5] = -1;       matriz[3][6] = -1;     matriz[3][7] = -1;     matriz[3][8] = -1;     matriz[3][9] = -1;     matriz[3][10] = -1;    matriz[3][11] = -1;       matriz[3][12] = -1;      matriz[3][13] = -1;        matriz[3][14] = 0;

        matriz[4][0] = 5;  matriz[4][1] = -1;    matriz[4][2] = 4;      matriz[4][3] = 4;      matriz[4][4] = -1;     matriz[4][5] = -1;       matriz[4][6] = -1;     matriz[4][7] = -1;     matriz[4][8] = -1;     matriz[4][9] = -1;     matriz[4][10] = -1;    matriz[4][11] = -1;       matriz[4][12] = -1;      matriz[4][13] = 15;        matriz[4][14] = 0;

        matriz[5][0] = 5;  matriz[5][1] = -1;    matriz[5][2] = 4;      matriz[5][3] = 4;      matriz[5][4] = -1;     matriz[5][5] = -1;       matriz[5][6] = -1;     matriz[5][7] = -1;     matriz[5][8] = -1;     matriz[5][9] = -1;     matriz[5][10] = -1;    matriz[5][11] = -1;       matriz[5][12] = -1;      matriz[5][13] = 15;        matriz[5][14] = 0;

        matriz[6][0] = -1; matriz[6][1] = -1;    matriz[6][2] = -1;     matriz[6][3] = -1;     matriz[6][4] = -1;     matriz[6][5] = -1;       matriz[6][6] = -1;     matriz[6][7] = 9;     matriz[6][8] = -1;     matriz[6][9] = -1;     matriz[6][10] = -1;    matriz[6][11] = -1;       matriz[6][12] = -1;      matriz[6][13] = -1;         matriz[6][14] = 0;

        matriz[7][0] = -1; matriz[7][1] = -1;     matriz[7][2] = -1;     matriz[7][3] = -1;    matriz[7][4] = -1;     matriz[7][5] = -1;       matriz[7][6] = -1;     matriz[7][7] = 7;     matriz[7][8] = -1;     matriz[7][9] = -1;     matriz[7][10] = -1;    matriz[7][11] = -1;       matriz[7][12] = -1;      matriz[7][13] = -1;         matriz[7][14] = 0;

        matriz[8][0] = -1; matriz[8][1] = -1;     matriz[8][2] = -1;     matriz[8][3] = -1;    matriz[8][4] = -1;     matriz[8][5] = -1;       matriz[8][6] = -1;     matriz[8][7] = -1;     matriz[8][8] = -1;     matriz[8][9] = -1;     matriz[8][10] = -1;    matriz[8][11] = -1;       matriz[8][12] = -1;      matriz[8][13] = -1;        matriz[8][14] = 0;

        matriz[9][0] = -1; matriz[9][1] = -1;     matriz[9][2] = -1;     matriz[9][3] = -1;    matriz[9][4] = -1;     matriz[9][5] = -1;       matriz[9][6] = -1;     matriz[9][7] = 9;     matriz[9][8] = -1;     matriz[9][9] = -1;     matriz[9][10] = -1;    matriz[9][11] = -1;       matriz[9][12] = -1;      matriz[9][13] = -1;         matriz[9][14] = 0;

        matriz[10][0] = -1; matriz[10][1] = -1;   matriz[10][2] = -1;    matriz[10][3] = -1;   matriz[10][4] = -1;    matriz[10][5] = -1;      matriz[10][6] = -1;    matriz[10][7] = -1;    matriz[10][8] = -1;    matriz[10][9] = -1;    matriz[10][10] = -1;    matriz[10][11] = -1;      matriz[10][12] = -1;    matriz[10][13] = -1;       matriz[10][14] = 0;

        matriz[11][0] = 11; matriz[11][1] = -1;   matriz[11][2] = 11;    matriz[11][3] = 11;   matriz[11][4] = -1;    matriz[11][5] = -1;      matriz[11][6] = -1;    matriz[11][7] = -1;    matriz[11][8] = -1;     matriz[11][9] = 11;   matriz[11][10] = -1;    matriz[11][11] = -1;      matriz[11][12] = -1;    matriz[11][13] = -1;       matriz[11][14] = 0;

        matriz[12][0] = -1; matriz[12][1] = -1;   matriz[12][2] = -1;    matriz[12][3] = -1;   matriz[12][4] = -1;    matriz[12][5] = -1;      matriz[12][6] = -1;    matriz[12][7] = -1;    matriz[12][8] = -1;     matriz[12][9] = -1;   matriz[12][10] = -1;    matriz[12][11] = -1;      matriz[12][12] = -1;    matriz[12][13] = -1;       matriz[12][14] = 0;

        matriz[13][0] =  13; matriz[13][1] = -1;   matriz[13][2] =  13;    matriz[13][3] =  13;   matriz[13][4] = -1;    matriz[13][5] = -1;      matriz[13][6] = -1;    matriz[13][7] = -1;    matriz[13][8] = -1;     matriz[13][9] = -1;   matriz[13][10] = -1;    matriz[13][11] = -1;      matriz[13][12] = -1;    matriz[13][13] = 13;    matriz[13][14] = 13;

        matriz[14][0] = -1; matriz[14][1] = -1;   matriz[14][2] = -1;    matriz[14][3] = -1;   matriz[14][4] = -1;    matriz[14][5] = -1;      matriz[14][6] = -1;    matriz[14][7] = -1;    matriz[14][8] = -1;     matriz[14][9] = -1;   matriz[14][10] = -1;    matriz[14][11] = -1;      matriz[14][12] = -1;    matriz[14][13] = -1;       matriz[14][14] = 0;

        matriz[15][0] = 5; matriz[15][1] = -1;   matriz[15][2] = 15;    matriz[15][3] = 15;   matriz[15][4] = -1;    matriz[15][5] = -1;      matriz[15][6] = -1;    matriz[15][7] = -1;    matriz[15][8] = -1;     matriz[15][9] = -1;   matriz[15][10] = -1;    matriz[15][11] = -1;      matriz[15][12] = -1;    matriz[15][13] = 15;       matriz[15][14] = 0;

        matriz[16][0] = -1; matriz[16][1] = -1;   matriz[16][2] = -1;    matriz[16][3] = -1;   matriz[16][4] = -1;    matriz[16][5] = -1;      matriz[16][6] = -1;    matriz[16][7] = -1;    matriz[16][8] = -1;     matriz[16][9] = -1;   matriz[16][10] = -1;    matriz[16][11] = 13;      matriz[16][12] = -1;    matriz[16][13] = -1;       matriz[16][14] = -1;


        //AFD Numero Entero
        estadosFinalizacion[0]=1;
        descripcionFinalizacion[0] = "Numero Entero";
        //AFD Numero Flotante
        estadosFinalizacion[1]=3;
        descripcionFinalizacion[1] = "Numero double";
        //AFD Identificador
        estadosFinalizacion[2]=4;
        descripcionFinalizacion[2] = "Identificador Tipo Minuscula";
        //AFD Identificador
        estadosFinalizacion[3]=5;
        descripcionFinalizacion[3] = "Identificador";
        //AFD Aritmetico
        estadosFinalizacion[4]=6;
        descripcionFinalizacion[4] = "Aritmetico";
        //AFD Comparacion
        estadosFinalizacion[5]=7;
        descripcionFinalizacion[5] = "Comparacion";
        // AFD Logicos
        estadosFinalizacion[6]=8;
        descripcionFinalizacion[6] = "Logicos";
        //AFD Asignacion
        estadosFinalizacion[7]=9;
        descripcionFinalizacion[7] = "Asignacion";
        //AFD PalabrasClave
        estadosFinalizacion[8]=10;
        descripcionFinalizacion[8] = "Palabras Reservadas";
        //AFD Cadena
        estadosFinalizacion[9] = 11;
        descripcionFinalizacion[9] = "Cadena";
        //AFD Booleana
        estadosFinalizacion[10] = 12;
        descripcionFinalizacion[10] = "Booleana";
        //AFD Comentario
        estadosFinalizacion[11] = 13;
        descripcionFinalizacion[11] = "Comentario";
        //AFD Otros
        estadosFinalizacion[12] = 14;
        descripcionFinalizacion[12] = "Otros";
        //AFD LETRA
        estadosFinalizacion[13] = 15;
        descripcionFinalizacion[13] = "Identificador Tipo Mayuscula";
        //AFD Espacios
        estadosFinalizacion[14] = 16;
        descripcionFinalizacion[14] = "Espacios";

    }
    
    public void getToken(Token token){
        String tipo = token.getTipo();
        String valor = token.getToken();
        int fila = token.getFila();
        int columna = token.getColumna();
    
    }
    public int getSiguienteEstado(int posicionActual, int caracter) {
        int resultado = -1;
        
        try {
            if(estadoActual == 13 && caracter == 14){
                resultado = 13;
            }
            

          else  if (caracter >= 0 && caracter <= 14) {
                resultado = matriz[estadoActual][caracter];
            }
            
        } catch (Exception e) {
            //El resultado toma el valor de 0 (resultado = matriz[][] = 0)
        }
        
        return resultado;
    }
    
    
    public int getIntCaracter(char caracter) {
        int resultado = -1;
        if (Character.isDigit(caracter)) {
            resultado = 0;
        } else if (caracter == '.') {
            resultado = 1;
        } else {
            if (Character.isLetter(caracter)) {
                if (Character.isUpperCase(caracter)) {
                    resultado = 13;
                }
                if (Character.isLowerCase(caracter)) {
                    resultado = 3;
                }

            } else if (caracter == '_') {
                resultado = 2;
                //Devuelve el token Aritmetico
            } else if (caracter == '+' | caracter == '-' | caracter == '*' /*| caracter == '**' */ | caracter == '/' /*| caracter == '//' */ | caracter == '%') {
                resultado = 4;
                //Devuelve el token Comparacion
            } else if (caracter == '!' | caracter == '>' | caracter == '<') {
                resultado = 5;
                //Devuelve el token Asignacion
            } else if (caracter == '=') {
                resultado = 7;
            } // Devuelve el token Cadena
            else if (caracter == '"') {
                resultado = 9;
            } else if (caracter == '#') {
                resultado = 11;
            } else if (caracter == '(' | caracter == ')' | caracter == '{' | caracter == '}' | caracter == '[' | caracter == ']' | caracter == ',' | caracter == ';' | caracter == ':') {
                resultado = 12;
            } else if (caracter == ' ' | caracter == '\n' | caracter == '\r' | caracter == '\t' | caracter == '\b' | caracter == '\f') {
                resultado = 14;
            }

        }
        return resultado;
    }
        public String getEstadoAceptacion(int i) {
        String res = "Error";
        int indice = 0;
        for (int estadoAceptacion : estadosFinalizacion) {
            if (estadoAceptacion == i) {
                res = descripcionFinalizacion[indice];
                break;
            }
            indice++;
        }
        return res;
    }
}
