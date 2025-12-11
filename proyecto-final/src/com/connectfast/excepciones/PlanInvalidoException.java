package com.connectfast.excepciones;

/**
 * Excepción lanzada cuando los datos de un plan (código, nombre, precio, velocidad)
 * no cumplen con las reglas de negocio básicas definidas en ServicePlan o sus hijos.
 * Es una Checked Exception, forzando al desarrollador a manejarla con try-catch.
 * * @author William Andres Aguilera Lasprilla
 * @version 1.0
 */
public class PlanInvalidoException extends Exception {
    
    // Identificador de versión para la serialización
    private static final long serialVersionUID = 1L;

    /**
     * Constructor que acepta un mensaje detallando la causa de la invalidez.
     * @param mensaje Descripción del error de validación.
     */
    public PlanInvalidoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa original (para encadenamiento de excepciones).
     * @param mensaje Descripción del error.
     * @param causa Excepción original que provocó este error.
     */
    public PlanInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}