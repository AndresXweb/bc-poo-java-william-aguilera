package com.connectfast.util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase utilitaria para manejar la entrada y salida de datos en la consola.
 * Centraliza el uso de Scanner para prevenir errores de tipo InputMismatchException
 * y facilitar la lectura de datos primitivos y cadenas.
 * * @author William Andres Aguilera Lasprilla
 * @version 2.0 (Proyecto Final)
 */
public class Consola {
    
    // Scanner estático para ser usado en toda la aplicación
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Muestra un mensaje en la consola con un salto de línea.
     * @param mensaje El texto a mostrar.
     */
    public static void mostrar(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Pausa la ejecución hasta que el usuario presione ENTER.
     * Útil para mejorar la experiencia de usuario en menús.
     */
    public static void esperarEnter() {
        System.out.print("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * Lee una cadena de texto ingresada por el usuario.
     * @param prompt Mensaje guía para el usuario.
     * @return La cadena ingresada.
     */
    public static String leerCadena(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Lee un número entero de manera segura.
     * Si el usuario ingresa texto, muestra error y vuelve a pedir el dato.
     * @param prompt Mensaje guía para el usuario.
     * @return El número entero válido.
     */
    public static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                // Leemos el entero
                int valor = scanner.nextInt();
                // Consumimos el salto de línea residual
                scanner.nextLine(); 
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                // Limpiamos el buffer del scanner
                scanner.nextLine(); 
            }
        }
    }

    /**
     * Lee un número decimal (double) de manera segura.
     * Soporta tanto punto (.) como coma (,) dependiendo de la configuración regional.
     * @param prompt Mensaje guía para el usuario.
     * @return El número decimal válido.
     */
    public static double leerDecimal(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número decimal (ej. 10,5 o 10.5).");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Cierra el flujo de entrada. Se debe llamar al finalizar la aplicación.
     */
    public static void cerrar() {
        scanner.close();
    }
}