package dominio;

import java.util.Scanner;

public class Lector {
    private static Scanner s = new Scanner(System.in);

    private Lector() {}

    public static Scanner leer() {
        return s;
    }
}
