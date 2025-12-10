package com.connectfast.excepciones;

/**
 * Excepción específica para reglas de negocio relacionadas con el ancho de banda.
 * Se lanza cuando un plan especializado no cumple con la velocidad mínima requerida.
 */
public class VelocidadInsuficienteException extends Exception {

    public VelocidadInsuficienteException(String mensaje) {
        super(mensaje);
    }

    public VelocidadInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}