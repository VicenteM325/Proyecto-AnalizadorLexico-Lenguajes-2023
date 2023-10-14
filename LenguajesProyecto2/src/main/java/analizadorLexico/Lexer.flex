package analizadorLexico;
import static analizadorLexico.TipoToken.*;
import analizadorLexico.Token;


%%
%public
%class Lexer
%type Token
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
    private int lineaActual = 1;
    private int columnaActual = 1;
%}

%%

// Regla para salto de línea
"\n" {
    // Incrementa el número de línea y reinicia la columna
    lineaActual++;
    columnaActual = 1;
}



// Reglas para palabras reservadas
as|assert|break|class|continue|def|del|elif|else|except|finally|for|from|global|if|import|in|is|lambda|
None|nonlocal|pass|raise|return|try|with|yield|
while { 
    // Calcula la posición de columna actual
    int tokenColumna = columnaActual;

    // Incrementa la columna por la longitud del lexema
    columnaActual += yytext().length();
    
    return new Token(RESERVADA, yytext(), lineaActual, tokenColumna); }


// Reglas para constantes Booleanas
True|False { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(BOOLEANO, yytext(), lineaActual, tokenColumna); }
 

// Regla para operadores lógicos (and, or, not)
and|or|not {
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(LOGICO, yytext(), lineaActual, tokenColumna); } 

// Regla para identificadores
[a-zA-Z_][a-zA-Z0-9_]* {
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(IDENTIFICADOR, yytext(), lineaActual, tokenColumna); }

// Reglas numeros enteros 
[0-9]+ { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(ENTERO, yytext(), lineaActual, tokenColumna); }

// Regla para numero decimal
[0-9]+"."[0-9]+ { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(DECIMAL, yytext(), lineaActual, tokenColumna); }

// Reglas para cadenas con comillas dobles 
\"[^\"]*\" { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(CADENA, yytext(), lineaActual, tokenColumna); }

// Regla para cadena con comillas simples
\'[^\']*\' { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(CADENA, yytext(), lineaActual, tokenColumna); }

//Regla para delimitadores
"(" | ")" | "{" | "}" | "[" | "]" | "," | ";" | ":" { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(DELIMITADOR, yytext(), lineaActual, tokenColumna); }
 
// Regla para operadores aritméticos (+, -, *, /, %)
"+" | "-" | "*" | "*" | "**" | "/" | "//" | "%"  { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(ARITMETICO, yytext(), lineaActual, tokenColumna); }

// Regla para operadores de comparación (==, !=, >, <, >=, <=)
"==" | "!=" | ">" | "<" | ">=" | "<="  { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(COMPARACION, yytext(), lineaActual, tokenColumna); }

// Regla para operadores de asignación (=, +=, -=, *=, /=)
""="" | "+=" | "-=" | "*=" | "/="  { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(ASIGNACION, yytext(), lineaActual, tokenColumna); }

//Regla para comentarios en una linea
\#.* { 
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(COMENTARIO, yytext(), lineaActual, tokenColumna); }

// Reglas para espacios en blanco 
//RECORDARTE QUE TAMBIEN DEBES DE SUMAR LOS ESPACIOS EN BLANCO EN LAS COLUMNAS
[ \t\r\n]  { columnaActual += yytext().length(); }


/*Regla para los errores*/
 . {
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(ERROR, yytext(), lineaActual, tokenColumna); }
[0-9]+[a-zA-Z_0-9]* {
    int tokenColumna = columnaActual;
    columnaActual += yytext().length();
    return new Token(ERROR, yytext(), lineaActual, tokenColumna); }