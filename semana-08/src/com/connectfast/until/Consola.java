package com.connectfast.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Consola {

    private static final Scanner scanner = new Scanner(System.in);

    // NUEVO MÉTODO 1: Para imprimir mensajes
    public static void mostrar(String mensaje) {
        System.out.println(mensaje);
    }

    // NUEVO MÉTODO 2: Para hacer una pausa (similar a system("pause"))
    public static void esperarEnter() {
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    public static String leerCadena(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Entrada inválida. Por favor, ingrese un número entero.");
                scanner.nextLine();
            }
        }
    }

    // Método renombrado en Main.java (leerDecimal es el correcto)
    public static double leerDecimal(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Entrada inválida. Por favor, ingrese un número decimal.");
                scanner.nextLine();
            }
        }
    }

    public static void cerrar() {
        scanner.close();
    }
}