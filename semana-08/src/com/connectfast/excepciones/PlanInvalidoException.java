package com.connectfast.excepciones;

/**
 * Excepción lanzada cuando los datos de un plan no cumplen con las reglas de negocio básicas.
 * Ejemplo: Precio negativo, nombre vacío, velocidad cero.
 */
public class PlanInvalidoException extends Exception {

    // Constructor básico con mensaje
    public PlanInvalidoException(String mensaje) {
        super(mensaje);
    }

    // Constructor con mensaje y causa (para encadenar errores)
    public PlanInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}