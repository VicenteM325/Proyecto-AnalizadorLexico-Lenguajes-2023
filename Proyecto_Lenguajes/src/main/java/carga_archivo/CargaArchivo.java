package carga_archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class CargaArchivo {

    public ArrayList<String> leerFichero(File archivo, JTextArea text) throws FileNotFoundException, IOException {
        ArrayList<String> lineas = new ArrayList<>();
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            //System.out.println(linea);

            text.append("\n" + linea);
        }
        fr.close();
        return lineas;
    }

}
