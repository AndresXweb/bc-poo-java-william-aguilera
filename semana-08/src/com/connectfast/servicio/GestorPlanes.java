package com.connectfast.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.connectfast.modelo.ServicePlan;
import com.connectfast.excepciones.PlanNoEncontradoException;
import com.connectfast.excepciones.PlanInvalidoException;


public class GestorPlanes {
    
    private Map<String, ServicePlan> planesPorCodigo; 

    private List<ServicePlan> historialPlanes; 

    public GestorPlanes() {
        this.planesPorCodigo = new HashMap<>();
        this.historialPlanes = new ArrayList<>();
    }

    public void agregarPlan(ServicePlan plan) throws PlanInvalidoException {
        if (plan == null) {
            throw new PlanInvalidoException("El plan a agregar no puede ser nulo.");
        }
        
        String codigo = plan.getPlanCode();
        
        if (planesPorCodigo.containsKey(codigo)) {
            throw new PlanInvalidoException("Ya existe un plan registrado con el código: " + codigo);
        }
        
        planesPorCodigo.put(codigo, plan);
        historialPlanes.add(plan);
        System.out.println("✅ PLAN REGISTRADO: " + plan.getPlanName() + " (Código: " + codigo + ")");
    }

    public ServicePlan buscarPlan(String codigo) throws PlanNoEncontradoException {
        ServicePlan plan = planesPorCodigo.get(codigo);
        
        if (plan == null) {
            throw new PlanNoEncontradoException("No se encontró ningún plan con el código: " + codigo);
        }
        return plan;
    }

    public ServicePlan eliminarPlan(String codigo) throws PlanNoEncontradoException {
        ServicePlan planEliminado = planesPorCodigo.remove(codigo); 
        
        if (planEliminado == null) {
             throw new PlanNoEncontradoException("No se puede eliminar: el plan con código " + codigo + " no existe.");
        }
        
        historialPlanes.remove(planEliminado);
        
        return planEliminado;
    }

    public List<ServicePlan> filtrarPorTipo(String tipo) {
        List<ServicePlan> resultado = new ArrayList<>();
        String tipoBuscado = tipo.toLowerCase();
        
        for (ServicePlan plan : historialPlanes) { 
            if (plan.getTipoPlan().toLowerCase().contains(tipoBuscado)) {
                resultado.add(plan);
            }
        }
        return resultado;
    }

    public double calcularIngresoMensualTotal() {
        double total = 0;
        for (ServicePlan plan : historialPlanes) {
            total += plan.getMonthlyPrice();
        }
        return total;
    }
    
    public double calcularPromedioMensual() {
        if (historialPlanes.isEmpty()) return 0;
        return calcularIngresoMensualTotal() / historialPlanes.size();
    }

    public ServicePlan obtenerPlanMasRapido() {
        if (historialPlanes.isEmpty()) {
            return null;
        }

        ServicePlan masRapido = historialPlanes.get(0);
        for (ServicePlan plan : historialPlanes) {
            if (plan.getSpeedMbps() > masRapido.getSpeedMbps()) {
                masRapido = plan;
            }
        }
        return masRapido;
    }
    
    public Map<String, Integer> contarPlanesPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        for (ServicePlan plan : historialPlanes) {
            String tipo = plan.getTipoPlan();
            conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
        }
        return conteo;
    }
    
    public List<ServicePlan> obtenerTodosLosPlanes() {
        return historialPlanes;
    }
    
    public int getCantidadPlanes() {
        return historialPlanes.size();
    }
    
    public void mostrarCatalogo() {
        System.out.println("\n--- CATALOGO DISPONIBLE (" + getCantidadPlanes() + " planes) ---");
        for (ServicePlan plan : historialPlanes) {
            System.out.println("- [" + plan.getPlanCode() + "] " + plan.getPlanName() + 
                              " (" + plan.getTipoPlan() + " - $" + plan.getMonthlyPrice() + ")");
        }
        System.out.println("-------------------------------------------");
    }
}