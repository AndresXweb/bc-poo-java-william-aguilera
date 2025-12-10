package com.connectfast.modelo;

import com.connectfast.excepciones.PlanInvalidoException;

public class PlanEmpresarial extends ServicePlan {
    private boolean ipFija;
    private int horasSoporte;
    private String nivelSLA;

    // CONSTRUCTOR: Ahora declara que puede lanzar una excepción (throws PlanInvalidoException)
    public PlanEmpresarial(String planCode, String planName, int speedMbps, double monthlyPrice,
                           boolean ipFija, int horasSoporte, String nivelSLA) throws PlanInvalidoException {
        // La llamada a super() valida los datos básicos (precio, nombre, etc.)
        super(planCode, planName, speedMbps, monthlyPrice, "Empresarial");
        
        this.ipFija = ipFija;
        
        // Estos setters validan datos específicos de este plan
        setHorasSoporte(horasSoporte);
        setNivelSLA(nivelSLA);
        
        registrarCambio("Plan empresarial configurado con SLA " + this.nivelSLA);
    }

    // IMPLEMENTACION DE METODOS ABSTRACTOS
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

        if (ipFija) {
            costoBase += 50000;
        }

        if (nivelSLA != null) {
            if (nivelSLA.equals("Enterprise")) {
                costoBase += 100000;
            } else if (nivelSLA.equals("Premium")) {
                costoBase += 50000;
            }
        }

        return costoBase;
    }

    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);

        if (meses >= 12) {
            precioTotal = precioTotal * 0.85;
            registrarCambio("Descuento anual empresarial aplicado (15%)");
        } else if (meses >= 6) {
            precioTotal = precioTotal * 0.92;
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

    // GETTERS Y SETTERS PROPIOS

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

    // Validamos con PlanInvalidoException en lugar de IllegalArgumentException
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
        // Aquí mantenemos la lógica de seguridad: si es nulo, asignamos Básico.
        // No lanzamos excepción porque lo corregimos automáticamente.
        if (nivelSLA == null || nivelSLA.isEmpty()) {
            this.nivelSLA = "Basico";
        } else {
            this.nivelSLA = nivelSLA;
        }
    }
}