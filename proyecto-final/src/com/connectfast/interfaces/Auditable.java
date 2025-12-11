package com.connectfast.interfaces;

/**
 * Define un contrato para cualquier entidad dentro del sistema (como ServicePlan)
 * que deba registrar cambios y mantener un historial de auditoría.
 * * Este componente demuestra la aplicación del principio de ABSTRACCIÓN.
 * * @author William Andres Aguilera Lasprilla
 * @version 1.0
 */
public interface Auditable {
    
    /** * Registra un cambio con la fecha y hora actual en el historial interno del objeto.
     * @param cambio Descripción del cambio realizado (ej. "Precio actualizado").
     */
    void registrarCambio(String cambio);
    
    /** * Retorna el historial completo de todos los cambios registrados.
     * @return Una cadena de texto con el listado de cambios.
     */
    String obtenerHistorial();
    
    /** * Retorna el último cambio registrado en el historial.
     * @return La última entrada de auditoría.
     */
    String obtenerUltimoCambio();
}