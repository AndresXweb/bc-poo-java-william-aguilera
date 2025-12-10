package com.connectfast.interfaces;

public interface Promocionable {
    boolean esElegiblePromocion();
    double aplicarPromocion(String codigoPromo);
    String obtenerPromocionesActivas();
}