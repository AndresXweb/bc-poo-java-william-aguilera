package com.connectfast.excepciones;

/**
 * Excepción lanzada cuando se intenta buscar o acceder a un plan que no existe en el sistema.
 */
public class PlanNoEncontradoException extends Exception {

    public PlanNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public PlanNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}