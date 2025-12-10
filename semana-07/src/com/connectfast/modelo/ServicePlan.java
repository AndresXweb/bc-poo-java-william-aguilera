package com.connectfast.modelo;

import java.util.ArrayList;
import com.connectfast.interfaces.Facturable;
import com.connectfast.interfaces.Auditable;
import com.connectfast.excepciones.PlanInvalidoException;

public abstract class ServicePlan implements Facturable, Auditable {
    protected String planCode;
    protected String planName;
    protected int speedMbps;
    protected double monthlyPrice;
    protected String tipoPlan;
    protected ArrayList<String> historialCambios;

    public ServicePlan(String planCode, String planName, int speedMbps, double monthlyPrice, String tipoPlan) 
            throws PlanInvalidoException {
        setPlanCode(planCode);
        setPlanName(planName);
        setSpeedMbps(speedMbps);
        setMonthlyPrice(monthlyPrice);
        this.tipoPlan = tipoPlan;
        this.historialCambios = new ArrayList<>();
        registrarCambio("Plan creado: " + planName);
    }

    public abstract String obtenerDescripcion();
    public abstract String obtenerBeneficios();
    public abstract double calcularCostoInstalacion();

    public void mostrarInformacion() {
        System.out.println("Plan: " + planName);
        System.out.println("Codigo: " + planCode);
        System.out.println("Tipo: " + tipoPlan);
        System.out.println("Velocidad: " + speedMbps + " Mbps");
        System.out.println("Precio mensual: $" + monthlyPrice);
    }

    public double calcularPrecioConDescuento(int meses) {
        return monthlyPrice * meses;
    }

    // --- IMPLEMENTACION DE FACTURABLE ---
    @Override
    public String generarFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append("========== FACTURA ==========\n");
        factura.append("Plan: ").append(planName).append("\n");
        factura.append("Codigo: ").append(planCode).append("\n");
        factura.append("Tipo: ").append(tipoPlan).append("\n");
        factura.append("Precio base: $").append(monthlyPrice).append("\n");
        factura.append("IVA (19%): $").append(calcularImpuestos()).append("\n");
        factura.append("Total: $").append(monthlyPrice + calcularImpuestos()).append("\n");
        factura.append("============================");
        return factura.toString();
    }

    @Override
    public double calcularImpuestos() {
        return monthlyPrice * 0.19;
    }

    @Override
    public String obtenerDetalleFacturacion() {
        return "Plan: " + planName + " | Precio: $" + monthlyPrice + " | IVA: $" + calcularImpuestos();
    }

    // --- IMPLEMENTACION DE AUDITABLE ---
    @Override
    public void registrarCambio(String cambio) {
        String registro = java.time.LocalDateTime.now() + " - " + cambio;
        historialCambios.add(registro);
    }

    @Override
    public String obtenerHistorial() {
        if (historialCambios.isEmpty()) {
            return "Sin historial de cambios";
        }

        StringBuilder historial = new StringBuilder();
        historial.append("=== HISTORIAL DE CAMBIOS ===\n");
        for (String cambio : historialCambios) {
            historial.append("- ").append(cambio).append("\n");
        }
        return historial.toString();
    }

    @Override
    public String obtenerUltimoCambio() {
        if (historialCambios.isEmpty()) {
            return "Sin cambios registrados";
        }
        return historialCambios.get(historialCambios.size() - 1);
    }

    // --- GETTERS Y SETTERS CON VALIDACIONES (Throws) ---
    
    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) throws PlanInvalidoException {
        if (planCode == null || planCode.isEmpty()) {
            throw new PlanInvalidoException("El codigo del plan no puede estar vacio");
        }
        if (!planCode.startsWith("PLAN-")) {
            throw new PlanInvalidoException("El codigo debe empezar con el prefijo 'PLAN-' (Ej: PLAN-100)");
        }
        this.planCode = planCode;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) throws PlanInvalidoException {
        if (planName == null || planName.isEmpty()) {
            throw new PlanInvalidoException("El nombre del plan no puede estar vacio");
        }
        String oldName = this.planName;
        this.planName = planName;
        if (oldName != null) {
            registrarCambio("Nombre cambiado de " + oldName + " a " + planName);
        }
    }

    public int getSpeedMbps() {
        return speedMbps;
    }

    public void setSpeedMbps(int speedMbps) throws PlanInvalidoException {
        // Validación general básica (Rango físico posible)
        if (speedMbps < 10 || speedMbps > 1000) {
            throw new PlanInvalidoException("La velocidad base debe estar entre 10 y 1000 Mbps");
        }
        int oldSpeed = this.speedMbps;
        this.speedMbps = speedMbps;
        if (oldSpeed != 0) {
            registrarCambio("Velocidad cambiada de " + oldSpeed + " a " + speedMbps + " Mbps");
        }
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) throws PlanInvalidoException {
        if (monthlyPrice <= 0) {
            throw new PlanInvalidoException("El precio mensual debe ser mayor a cero");
        }
        double oldPrice = this.monthlyPrice;
        this.monthlyPrice = monthlyPrice;
        if (oldPrice != 0) {
            registrarCambio("Precio cambiado de $" + oldPrice + " a $" + monthlyPrice);
        }
    }

    public String getTipoPlan() {
        return tipoPlan;
    }
}