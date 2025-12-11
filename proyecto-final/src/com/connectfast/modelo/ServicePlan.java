package com.connectfast.modelo;

import java.util.ArrayList;
import java.io.Serializable; // IMPORTANTE: Para persistencia
import com.connectfast.interfaces.Facturable;
import com.connectfast.interfaces.Auditable;
import com.connectfast.excepciones.PlanInvalidoException;

/**
 * Clase abstracta que representa la base de cualquier plan de servicio en ConnectFast.
 * Implementa Serializable para permitir persistencia de datos y Comparable para ordenamiento.
 * * @author William Andres Aguilera Lasprilla
 * @version 2.0 (Proyecto Final)
 */
public abstract class ServicePlan implements Facturable, Auditable, Serializable, Comparable<ServicePlan> {
    
    // Identificador único de versión para la serialización
    private static final long serialVersionUID = 1L;

    protected String planCode;
    protected String planName;
    protected int speedMbps;
    protected double monthlyPrice;
    protected String tipoPlan;
    protected ArrayList<String> historialCambios;

    /**
     * Constructor principal que inicializa y valida un plan de servicio.
     * @param planCode Código único del plan (debe iniciar con PLAN-)
     * @param planName Nombre comercial del plan
     * @param speedMbps Velocidad en Megas
     * @param monthlyPrice Precio mensual
     * @param tipoPlan Categoría del plan (Residencial, Gamer, etc.)
     * @throws PlanInvalidoException Si algún dato no cumple las validaciones.
     */
    public ServicePlan(String planCode, String planName, int speedMbps, double monthlyPrice, String tipoPlan) 
            throws PlanInvalidoException {
        // Usamos los setters para aprovechar las validaciones desde el inicio
        setPlanCode(planCode);
        setPlanName(planName);
        setSpeedMbps(speedMbps);
        setMonthlyPrice(monthlyPrice);
        this.tipoPlan = tipoPlan;
        this.historialCambios = new ArrayList<>();
        registrarCambio("Plan creado: " + planName);
    }

    // --- MÉTODOS ABSTRACTOS ---
    public abstract String obtenerDescripcion();
    public abstract String obtenerBeneficios();
    public abstract double calcularCostoInstalacion();

    /**
     * Muestra la información básica del plan en consola.
     */
    public void mostrarInformacion() {
        System.out.println("------------------------------------------------");
        System.out.println("Plan: " + planName);
        System.out.println("Codigo: " + planCode);
        System.out.println("Tipo: " + tipoPlan);
        System.out.println("Velocidad: " + speedMbps + " Mbps");
        System.out.println("Precio mensual: $" + monthlyPrice);
        System.out.println("------------------------------------------------");
    }

    public double calcularPrecioConDescuento(int meses) {
        return monthlyPrice * meses;
    }

    // --- IMPLEMENTACIÓN INTERFAZ FACTURABLE ---
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

    // --- IMPLEMENTACIÓN INTERFAZ AUDITABLE ---
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
    
    // --- IMPLEMENTACIÓN INTERFAZ COMPARABLE ---
    /**
     * Define el orden natural de los planes basado en el precio mensual.
     * Permite usar Collections.sort(listaPlanes).
     */
    @Override
    public int compareTo(ServicePlan otro) {
        // Orden ascendente por precio
        return Double.compare(this.monthlyPrice, otro.monthlyPrice);
    }

    // --- GETTERS Y SETTERS CON VALIDACIONES ---
    
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
        // Validación extra para Encapsulación robusta
        if (planName.length() < 3) {
            throw new PlanInvalidoException("El nombre del plan es muy corto (mínimo 3 caracteres)");
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
        // Ampliamos un poco el rango para el proyecto final (hasta 10,000 Mbps / 10 Gbps)
        if (speedMbps < 10 || speedMbps > 10000) {
            throw new PlanInvalidoException("La velocidad base debe estar entre 10 y 10000 Mbps");
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
    
    // Sobrescribimos toString para facilitar la depuración y listados simples
    @Override
    public String toString() {
        return String.format("[%s] %s - $%.2f", planCode, planName, monthlyPrice);
    }
}