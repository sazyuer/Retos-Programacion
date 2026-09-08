package org.example.consola;

import java.util.Scanner;

public class InteraccionConsola {
    private final Scanner sc = new Scanner(System.in);

    public String leerTexto(String mensaje) {
        String valor;
        do {
            System.out.println(mensaje);
            valor = sc.nextLine().trim();
        } while (valor.isBlank());
        return valor;
    }

    public int leerEntero(String mensaje, int min, int max) {
        int valor;
        do {
            System.out.println(mensaje);
            valor = sc.nextInt();
            sc.nextLine();
        } while (valor < min || valor > max);
        return valor;
    }

    public String leerRespuestaSiNo(String mensaje) {
        String respuesta;
        do {
            System.out.println(mensaje);
            respuesta = sc.nextLine().trim();
        } while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));
        return respuesta;
    }

    public void mostrar(String mensaje) {
        System.out.println(mensaje);
    }
}
