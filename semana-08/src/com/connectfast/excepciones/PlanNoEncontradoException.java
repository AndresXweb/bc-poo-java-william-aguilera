package com.connectfast.excepciones;

public class PlanNoEncontradoException extends Exception {

    public PlanNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public PlanNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}