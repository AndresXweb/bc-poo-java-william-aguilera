package com.connectfast.modelo;

import com.connectfast.excepciones.PlanInvalidoException;

/**
 * Entidad concreta para planes Empresariales (B2B).
 * Incluye gestión de SLA (Acuerdos de Nivel de Servicio) y soporte prioritario.
 * * @author William Andres Aguilera Lasprilla
 * @version 2.0 (Proyecto Final)
 */
public class PlanEmpresarial extends ServicePlan {
    
    // Identificador de versión para la serialización
    private static final long serialVersionUID = 1L;
    
    private boolean ipFija;
    private int horasSoporte;
    private String nivelSLA; // Valores esperados: "Basico", "Premium", "Enterprise"

    /**
     * Constructor del Plan Empresarial.
     * @param planCode Código único
     * @param planName Nombre comercial
     * @param speedMbps Velocidad simétrica
     * @param monthlyPrice Precio mensual
     * @param ipFija Si incluye IP pública estática
     * @param horasSoporte Horas de soporte dedicadas
     * @param nivelSLA Nivel de acuerdo de servicio (SLA)
     * @throws PlanInvalidoException Si los datos son inválidos
     */
    public PlanEmpresarial(String planCode, String planName, int speedMbps, double monthlyPrice,
                           boolean ipFija, int horasSoporte, String nivelSLA) throws PlanInvalidoException {
        
        super(planCode, planName, speedMbps, monthlyPrice, "Empresarial");
        
        this.ipFija = ipFija;
        
        // Usamos setters para aplicar validaciones
        setHorasSoporte(horasSoporte);
        setNivelSLA(nivelSLA);
        
        registrarCambio("Plan empresarial configurado con SLA " + this.nivelSLA);
    }

    // --- MÉTODOS SOBRESCRITOS (Polimorfismo) ---

    @Override
    public String obtenerDescripcion() {
        return "Plan empresarial " + planName + " con internet simetrico de " + speedMbps +
                " Mbps, " + (ipFija ? "IP fija" : "IP dinamica") + ", soporte " +
                horasSoporte + "h y SLA " + nivelSLA;
    }

    @Override
    public String obtenerBeneficios() {
        StringBuilder beneficios = new StringBuilder();
        beneficios.append("Beneficios del Plan Empresarial:\n");
        beneficios.append("- Internet simetrico: ").append(speedMbps).append(" Mbps\n");
        if (ipFija) {
            beneficios.append("- Direccion IP fija dedicada\n");
        }
        beneficios.append("- Soporte prioritario ").append(horasSoporte).append(" horas\n");
        beneficios.append("- Acuerdo de nivel de servicio (SLA): ").append(nivelSLA).append("\n");
        beneficios.append("- Gerente de cuenta dedicado\n");
        beneficios.append("- Reportes mensuales de uso\n");
        beneficios.append("- Instalacion y configuracion especializada");
        return beneficios.toString();
    }

    @Override
    public double calcularCostoInstalacion() {
        double costoBase = 150000;

        // Costo adicional por IP Fija
        if (ipFija) {
            costoBase += 50000;
        }

        // Costo adicional según el nivel de SLA
        if (nivelSLA != null) {
            if (nivelSLA.equalsIgnoreCase("Enterprise")) {
                costoBase += 100000;
            } else if (nivelSLA.equalsIgnoreCase("Premium")) {
                costoBase += 50000;
            }
        }

        return costoBase;
    }

    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);

        // Descuentos corporativos por volumen de tiempo
        if (meses >= 12) {
            precioTotal = precioTotal * 0.85; // 15% desc anual
            registrarCambio("Descuento anual empresarial aplicado (15%)");
        } else if (meses >= 6) {
            precioTotal = precioTotal * 0.92; // 8% desc semestral
            registrarCambio("Descuento semestral aplicado (8%)");
        }

        return precioTotal;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("IP Fija: " + (ipFija ? "Si" : "No"));
        System.out.println("Soporte: " + horasSoporte + " horas");
        System.out.println("Nivel SLA: " + nivelSLA);
        System.out.println("Costo instalacion: $" + calcularCostoInstalacion());
    }

    // --- GETTERS Y SETTERS (Encapsulación) ---

    public boolean isIpFija() {
        return ipFija;
    }

    public void setIpFija(boolean ipFija) {
        this.ipFija = ipFija;
        registrarCambio("IP fija " + (ipFija ? "activada" : "desactivada"));
    }

    public int getHorasSoporte() {
        return horasSoporte;
    }

    public void setHorasSoporte(int horasSoporte) throws PlanInvalidoException {
        if (horasSoporte < 0 || horasSoporte > 24) {
            throw new PlanInvalidoException("Horas de soporte debe estar entre 0 y 24");
        }
        this.horasSoporte = horasSoporte;
    }

    public String getNivelSLA() {
        return nivelSLA;
    }

    public void setNivelSLA(String nivelSLA) {
        // Validación con valores por defecto para evitar inconsistencias
        if (nivelSLA == null || nivelSLA.trim().isEmpty()) {
            this.nivelSLA = "Basico";
        } else {
            // Normalizamos el texto (Primera mayúscula, resto minúscula) para consistencia
            String slaNormalizado = nivelSLA.substring(0, 1).toUpperCase() + nivelSLA.substring(1).toLowerCase();
            this.nivelSLA = slaNormalizado;
        }
    }
}