package com.connectfast.interfaces;

/**
 * Define un contrato para cualquier plan o servicio (como ServicePlan)
 * que genere costos y deba ser incluido en una factura, permitiendo el cálculo
 * de precios e impuestos.
 * * Este componente demuestra la aplicación del principio de ABSTRACCIÓN.
 * * @author William Andres Aguilera Lasprilla
 * @version 1.0
 */
public interface Facturable {
    
    /** * Genera una representación detallada de la factura del plan.
     * @return Una cadena de texto formateada con los componentes de la factura.
     */
    String generarFactura();
    
    /** * Calcula los impuestos aplicables al precio mensual (ej. IVA del 19%).
     * @return El monto del impuesto en double.
     */
    double calcularImpuestos();
    
    /** * Retorna un resumen de los componentes del precio (Base + Impuestos).
     * @return Cadena con el detalle de facturación.
     */
    String obtenerDetalleFacturacion();
}