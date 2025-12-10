package com.connectfast.modelo;

import com.connectfast.interfaces.Promocionable;
import com.connectfast.excepciones.PlanInvalidoException;

public class PlanResidencial extends ServicePlan implements Promocionable {
    private int canalesTV;
    private boolean incluyeTelefonia;
    private String tipoContenido;

    public PlanResidencial(String planCode, String planName, int speedMbps, double monthlyPrice,
                           int canalesTV, boolean incluyeTelefonia) throws PlanInvalidoException {
        super(planCode, planName, speedMbps, monthlyPrice, "Residencial");
        
        setCanalesTV(canalesTV); 
        this.incluyeTelefonia = incluyeTelefonia;
        this.tipoContenido = "Familiar";
        
        registrarCambio("Plan residencial configurado con " + canalesTV + " canales");
    }

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
            beneficios.append("- Telefonia ilimitada\n");
        }
        beneficios.append("- Soporte tecnico basico 24/7\n");
        beneficios.append("- Instalacion gratuita");
        return beneficios.toString();
    }

    @Override
    public double calcularCostoInstalacion() {
        return 0;
    }

    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);

        if (meses >= 12) {
            precioTotal = precioTotal * 0.90;
            registrarCambio("Descuento anual aplicado (10%)");
        }

        return precioTotal;
    }

    @Override
    public boolean esElegiblePromocion() {
        return monthlyPrice < 100000;
    }

    @Override
    public double aplicarPromocion(String codigoPromo) {
        double precioFinal = monthlyPrice;

        switch (codigoPromo.toUpperCase()) {
            case "HOGAR2025":
                precioFinal = monthlyPrice * 0.85;
                registrarCambio("Promocion HOGAR2025 aplicada (15% descuento)");
                break;
            case "FAMILIA":
                precioFinal = monthlyPrice * 0.90;
                registrarCambio("Promocion FAMILIA aplicada (10% descuento)");
                break;
            default:
                System.out.println("Codigo de promocion invalido");
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

    public int getCanalesTV() {
        return canalesTV;
    }

    public void setCanalesTV(int canalesTV) throws PlanInvalidoException {
        if (canalesTV < 0) {
            throw new PlanInvalidoException("El numero de canales no puede ser negativo");
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