package com.connectfast.interfaces;

/**
 * Define un contrato para los planes que son elegibles para promociones 
 * y descuentos especiales. Permite aplicar lógica polimórfica en los métodos de negocio.
 * * Este componente demuestra la aplicación del principio de ABSTRACCIÓN y POLIMORFISMO.
 * * @author William Andres Aguilera Lasprilla
 * @version 1.0
 */
public interface Promocionable {
    
    /** * Determina si el plan cumple con los requisitos mínimos de una promoción 
     * (ej. si el precio no es demasiado bajo para aplicar otro descuento).
     * @return true si es elegible, false en caso contrario.
     */
    boolean esElegiblePromocion();
    
    /** * Aplica un descuento específico basado en un código promocional.
     * @param codigoPromo El código de la promoción (ej. "HOGAR2025").
     * @return El precio mensual final después de aplicar el descuento.
     */
    double aplicarPromocion(String codigoPromo);
    
    /** * Retorna una lista de las promociones válidas que pueden aplicarse al plan.
     * @return Una cadena con la descripción de las promociones activas.
     */
    String obtenerPromocionesActivas();
}