public interface Auditable {
    void registrarCambio(String cambio);
    String obtenerHistorial();
    String obtenerUltimoCambio();
}
