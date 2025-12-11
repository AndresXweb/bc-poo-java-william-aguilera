package com.connectfast.modelo;

import com.connectfast.interfaces.Promocionable;
import com.connectfast.excepciones.PlanInvalidoException;

/**
 * Entidad concreta que representa un Plan de Servicio Residencial (Hogar).
 * Incluye lógica específica para TV y Telefonía.
 * Implementa la interfaz Promocionable para aplicar descuentos.
 * * @author William Andres Aguilera Lasprilla
 * @version 2.0 (Proyecto Final)
 */
public class PlanResidencial extends ServicePlan implements Promocionable {
    
    // Identificador de versión para la serialización
    private static final long serialVersionUID = 1L;
    
    private int canalesTV;
    private boolean incluyeTelefonia;
    private String tipoContenido;

    /**
     * Constructor del Plan Residencial.
     * @param planCode Código único (PLAN-XXX)
     * @param planName Nombre del plan
     * @param speedMbps Velocidad en Megas
     * @param monthlyPrice Precio mensual
     * @param canalesTV Número de canales de televisión
     * @param incluyeTelefonia Si incluye línea telefónica fija
     * @throws PlanInvalidoException Si los datos no cumplen las reglas de negocio
     */
    public PlanResidencial(String planCode, String planName, int speedMbps, double monthlyPrice,
                           int canalesTV, boolean incluyeTelefonia) throws PlanInvalidoException {
        // Llamada al constructor del padre (ServicePlan)
        super(planCode, planName, speedMbps, monthlyPrice, "Residencial");
        
        // Usamos el setter para validar
        setCanalesTV(canalesTV); 
        this.incluyeTelefonia = incluyeTelefonia;
        this.tipoContenido = "Familiar";
        
        registrarCambio("Plan residencial configurado con " + canalesTV + " canales");
    }

    // --- MÉTODOS SOBRESCRITOS (Polimorfismo) ---

    @Override
    public String obtenerDescripcion() {
        return "Plan residencial " + planName + " con internet de " + speedMbps +
                " Mbps, " + canalesTV + " canales de TV y " +
                (incluyeTelefonia ? "telefonia incluida" : "sin telefonia");
    }

    @Override
    public String obtenerBeneficios() {
        StringBuilder beneficios = new StringBuilder();
        beneficios.append("Beneficios del Plan Residencial:\n");
        beneficios.append("- Internet de alta velocidad: ").append(speedMbps).append(" Mbps\n");
        beneficios.append("- Television con ").append(canalesTV).append(" canales\n");
        beneficios.append("- Contenido ").append(tipoContenido).append("\n");
        if (incluyeTelefonia) {
            beneficios.append("- Telefonia ilimitada a fijos locales\n");
        }
        beneficios.append("- Soporte tecnico horario oficina\n");
        beneficios.append("- Instalacion gratuita");
        return beneficios.toString();
    }

    @Override
    public double calcularCostoInstalacion() {
        return 0; // Estrategia comercial: Instalación gratis en residencial
    }

    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);

        if (meses >= 12) {
            precioTotal = precioTotal * 0.90; // 10% descuento por año adelantado
            registrarCambio("Descuento anual aplicado (10%)");
        }

        return precioTotal;
    }

    // --- IMPLEMENTACIÓN INTERFAZ PROMOCIONABLE ---

    @Override
    public boolean esElegiblePromocion() {
        // Solo aplica promos si el precio es menor a 100k
        return monthlyPrice < 100000;
    }

    @Override
    public double aplicarPromocion(String codigoPromo) {
        double precioFinal = monthlyPrice;

        switch (codigoPromo.toUpperCase()) {
            case "HOGAR2025":
                precioFinal = monthlyPrice * 0.85; // 15% desc
                registrarCambio("Promocion HOGAR2025 aplicada (15% descuento)");
                break;
            case "FAMILIA":
                precioFinal = monthlyPrice * 0.90; // 10% desc
                registrarCambio("Promocion FAMILIA aplicada (10% descuento)");
                break;
            default:
                System.out.println("Codigo de promocion invalido o expirado");
        }

        return precioFinal;
    }

    @Override
    public String obtenerPromocionesActivas() {
        return "Promociones activas: HOGAR2025 (15% desc), FAMILIA (10% desc)";
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Canales TV: " + canalesTV);
        System.out.println("Telefonia incluida: " + (incluyeTelefonia ? "Si" : "No"));
        System.out.println("Contenido: " + tipoContenido);
    }

    // --- GETTERS Y SETTERS (Encapsulación) ---

    public int getCanalesTV() {
        return canalesTV;
    }

    public void setCanalesTV(int canalesTV) throws PlanInvalidoException {
        // Validación mejorada: Rango realista
        if (canalesTV < 0 || canalesTV > 500) {
            throw new PlanInvalidoException("El numero de canales debe estar entre 0 y 500.");
        }
        this.canalesTV = canalesTV;
    }

    public boolean isIncluyeTelefonia() {
        return incluyeTelefonia;
    }

    public void setIncluyeTelefonia(boolean incluyeTelefonia) {
        this.incluyeTelefonia = incluyeTelefonia;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }
}