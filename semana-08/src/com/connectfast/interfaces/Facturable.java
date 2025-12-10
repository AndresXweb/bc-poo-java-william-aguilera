package com.connectfast.interfaces;

public interface Facturable {
    String generarFactura();
    double calcularImpuestos();
    String obtenerDetalleFacturacion();
}