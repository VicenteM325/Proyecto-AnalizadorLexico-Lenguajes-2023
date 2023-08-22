package jframe;

import java.io.IOException;

public class GraphvizUtils {

    public static void generarImagenDeDot(String dotFilePath, String outputImagePath) {
        Process process;
        try {
            // Comando para generar la imagen desde el archivo DOT
            String command = "dot -Tpng " + dotFilePath + " -o " + outputImagePath;
            // Ejecutar el comando en la línea de comandos
          if(System.getProperty("os.name").startsWith("Windows")){
              ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", command);
             process = processBuilder.start();
          } else {
            ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", command);
             process = processBuilder.start();
          }

            // Esperar a que termine la ejecución del proceso
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Imagen generada exitosamente: " + outputImagePath);
            } else {
                System.err.println("Error al generar la imagen.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}