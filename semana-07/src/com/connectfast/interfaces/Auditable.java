package com.connectfast.interfaces;

public interface Auditable {
    void registrarCambio(String cambio);
    String obtenerHistorial();
    String obtenerUltimoCambio();
}