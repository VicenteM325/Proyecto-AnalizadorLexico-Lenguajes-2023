package gestores;

import static gestores.AnalizadorLexico.estadoActual;
import java.awt.Color;
import static java.awt.SystemColor.text;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class Patron {
    
     public static final Color COLOR_IDENTIFICADOR_MAYUSCULA = Color.BLUE;
     private javax.swing.JTextArea textArea3;
     GeneradorAFD afd = new GeneradorAFD();
    
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
            return java.awt.Color.RED;
        }
        if(token.equals("Numero double")){
            return java.awt.Color.RED;
        }
        if(token.equals("Identificador Tipo Minuscula")){
            return java.awt.Color.BLACK;
        }
        if(token.equals("Identificador")){
            return java.awt.Color.BLACK;
        }
        if(token.equals("Aritmetico")){
            return java.awt.Color.blue;       
        }
        if(token.equals("Comparacion")){
            return java.awt.Color.blue;
        }
        if(token.equals("Logicos")){
            return java.awt.Color.blue;
        }
        if(token.equals("Asignacion")){
            return java.awt.Color.blue;       
        }
        if(token.equals("Palabras Reservadas")){
            return java.awt.Color.MAGENTA;
        }
        if(token.equals("Cadena")){
            return java.awt.Color.RED;
        }
        if(token.equals("Booleana")){
            return java.awt.Color.RED;
        }
        if(token.equals("Comentario")){
            return java.awt.Color.GRAY;       
        }
        if(token.equals("Otros")){
            return java.awt.Color.GREEN;
        }
        if(token.equals("Identificador Tipo Mayuscula")){
            return java.awt.Color.BLACK;
        }         
        
        return java.awt.Color.yellow;
    }
       
        
        public void colorearEditorTexto(JTextPane textPane, String textEditor, Color color){
        
            StyledDocument doc = textPane.getStyledDocument();
            String contenido = null;
            try {
                //Obtiene el texto completo del JTextPane
                contenido = doc.getText(0, doc.getLength()); 
            } catch (BadLocationException e) {
                e.printStackTrace();
                return;
            }
                //Encuentra el indice del token 
            int startIndex = contenido.indexOf(textEditor); 
            while (startIndex >= 0) {
                //Calcula el índice final
                int endIndex = startIndex + textEditor.length(); 

                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

                doc.setCharacterAttributes(startIndex, endIndex - startIndex, aset, true);

                // Buscar la próxima ocurrencia del token
                startIndex = contenido.indexOf(textEditor, endIndex);
            }
    }

        
    
    
    
    
}
