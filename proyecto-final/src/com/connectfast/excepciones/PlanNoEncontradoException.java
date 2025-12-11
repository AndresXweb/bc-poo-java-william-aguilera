package com.connectfast.excepciones;

/**
 * Excepción lanzada cuando se intenta buscar, modificar o eliminar un plan 
 * cuyo código no existe dentro del catálogo gestionado por GestorPlanes.
 * Es una Checked Exception, obligando a manejar el error de forma controlada.
 * * @author William Andres Aguilera Lasprilla
 * @version 1.0
 */
public class PlanNoEncontradoException extends Exception {
    
    // Identificador de versión para la serialización
    private static final long serialVersionUID = 1L;

    /**
     * Constructor que acepta un mensaje detallando el código no encontrado.
     * @param mensaje Descripción del plan que falló al ser localizado.
     */
    public PlanNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa original (para encadenamiento de excepciones).
     * @param mensaje Descripción del error.
     * @param causa Excepción original que provocó este error.
     */
    public PlanNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}