package jframe;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GeneradorDiagramas {

    private List<Nodo> nodos;
    private List<Transicion> transiciones;
    
    public GeneradorDiagramas() {
        nodos = new ArrayList<>();
        transiciones = new ArrayList<>();
    }

    public void agregarNodo(int estado, String descripcion) {
        nodos.add(new Nodo(estado, descripcion));
    }

    public void agregarTransicion(int estadoOrigen, int estadoDestino, String simbolo) {
        transiciones.add(new Transicion(estadoOrigen, estadoDestino, simbolo));
    }

    public void generarDotFile(String filePath) {
        // Generar el contenido del archivo DOT basado en los nodos y transiciones
        StringBuilder dotContent = new StringBuilder();
        dotContent.append("digraph G {\n");
        dotContent.append("   rankdir=LR;\n");

        for (Nodo nodo : nodos) {
            if (nodo.getEstado() == 1 | nodo.getEstado() == 3 | nodo.getEstado() == 4 | nodo.getEstado() == 5 | nodo.getEstado() == 6 | nodo.getEstado() == 7 | nodo.getEstado() == 8 | nodo.getEstado() == 9 | nodo.getEstado() == 10 | nodo.getEstado() == 11 | nodo.getEstado() == 12 | nodo.getEstado() == 13 | nodo.getEstado() == 14) {
                dotContent.append(String.format("  %d [label=\"%s\", shape=doublecircle];\n", nodo.getEstado(), nodo.getDescripcion()));
            } else {
                dotContent.append(String.format("  %d [label=\"%s\"];\n", nodo.getEstado(), nodo.getDescripcion()));
            }
        }

        for (Transicion transicion : transiciones) {
            dotContent.append(String.format("  %d -> %d [label=\"%s\"];\n", transicion.getEstadoOrigen(), transicion.getEstadoDestino(), transicion.getSimbolo()));
        }

        dotContent.append("}");

        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.write(dotContent.toString());
            System.out.println("Archivo DOT generado exitosamente: " + filePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error al generar el archivo DOT: " + e.getMessage());
        }

        // Guardar el contenido en un archivo DOT
        // Aquí tendrías el código para escribir el contenido en un archivo en el filePath especificado.
    }

    public void visualizarArchivoDOT() {
        String rutaCarpetaImagenes = getClass().getClassLoader().getResource("Imagenes/").getPath();
        // Ruta del archivo DOT que generaste previamente
        String rutaArchivoDOT = rutaCarpetaImagenes + "archivo.dot";
        // Ruta donde se guardará las imagenes generada por Graphviz
        String rutaImagen = rutaCarpetaImagenes + "imagen.png";

        // Llamar a Graphviz para generar la imagen
        GraphvizUtils.generarImagenDeDot(rutaArchivoDOT, rutaImagen);

        // Cargar la imagen en un JLabel u otro componente gráfico
        ImageIcon imageIcon = new ImageIcon(rutaImagen);
        int anchoImagen = 800;
        int altoImagen = 600;
        Image image = imageIcon.getImage().getScaledInstance(anchoImagen, altoImagen, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(imageIcon);

        // Crear un JFrame para mostrar la imagen
        JFrame frame = new JFrame("Diagrama de Estados");
        frame.getContentPane().add(label);
        frame.pack();
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        

        // Ajustar el tamaño del JFrame para que coincida con el tamaño de la imagen
        frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        // Establecer el cierre al hacer clic en la "X"
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }
    public void eliminarImagenGenerada(String rutaImagen, String rutaArchivoDOT) {
    File dot = new File(rutaArchivoDOT);
    File imagen = new File(rutaImagen);
    if (imagen.exists() && dot.exists() ) {
        imagen.delete();
        dot.delete();
        System.out.println("Imagen eliminada: " + rutaImagen);
        System.out.println("Archivo dot eliminado = " + rutaArchivoDOT);
    } else {
        System.out.println("La imagen no existe: " + rutaImagen);
        System.out.println("El archivo dot no existe: " + rutaArchivoDOT);
    }
    }
        public void limpiar() {
        String rutaCarpetaImagenes = getClass().getClassLoader().getResource("Imagenes/").getPath();
        // Ruta del archivo DOT que generaste previamente
        String rutaArchivoDOT = rutaCarpetaImagenes + "archivo.dot";
        // Ruta donde se guardará las imagenes generada por Graphviz
        String rutaImagen = rutaCarpetaImagenes + "imagen.png";
        eliminarImagenGenerada(rutaImagen, rutaArchivoDOT);
    }



    class Nodo {

        private int estado;
        private String descripcion;

        public Nodo(int estado, String descripcion) {
            this.estado = estado;
            this.descripcion = descripcion;
        }

        public int getEstado() {
            return estado;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    class Transicion {

        private int estadoOrigen;
        private int estadoDestino;
        private String simbolo;

        public Transicion(int estadoOrigen, int estadoDestino, String simbolo) {
            this.estadoOrigen = estadoOrigen;
            this.estadoDestino = estadoDestino;
            this.simbolo = simbolo;
        }

        public int getEstadoOrigen() {
            return estadoOrigen;
        }

        public int getEstadoDestino() {
            return estadoDestino;
        }

        public String getSimbolo() {
            return simbolo;
        }
    }
}
