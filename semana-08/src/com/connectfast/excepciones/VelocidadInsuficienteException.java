package com.connectfast.excepciones;

public class VelocidadInsuficienteException extends Exception {

    public VelocidadInsuficienteException(String mensaje) {
        super(mensaje);
    }

    public VelocidadInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}