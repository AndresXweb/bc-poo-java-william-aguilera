package com.connectfast.excepciones;

public class PlanInvalidoException extends Exception {

    public PlanInvalidoException(String mensaje) {
        super(mensaje);
    }

    public PlanInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}