package com.connectfast.excepciones;

/**
 * Excepción específica lanzada cuando un plan especializado (como PlanGamer) 
 * no cumple con la velocidad mínima requerida por las reglas de negocio de ConnectFast 
 * para garantizar la calidad del servicio.
 * Es una Checked Exception, obligando a manejar el error.
 * * @author William Andres Aguilera Lasprilla
 * @version 1.0
 */
public class VelocidadInsuficienteException extends Exception {
    
    // Identificador de versión para la serialización
    private static final long serialVersionUID = 1L;

    /**
     * Constructor que acepta un mensaje detallando la velocidad mínima requerida.
     * @param mensaje Descripción del error de velocidad.
     */
    public VelocidadInsuficienteException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa original (para encadenamiento de excepciones).
     * @param mensaje Descripción del error.
     * @param causa Excepción original que provocó este error.
     */
    public VelocidadInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}