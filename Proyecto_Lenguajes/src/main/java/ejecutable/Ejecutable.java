package ejecutable;
import gestores.Identificador;
import java.util.Scanner;

public class Ejecutable {
    public static void main(String[] args) {
        String palabra;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese una palabra: " );
        palabra = entrada.nextLine();
        Identificador ident = new Identificador();
        ident.Verificar(palabra);
    }
}
