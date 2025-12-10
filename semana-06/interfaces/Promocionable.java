public interface Promocionable {
    boolean esElegiblePromocion();
    double aplicarPromocion(String codigoPromo);
    String obtenerPromocionesActivas();
}