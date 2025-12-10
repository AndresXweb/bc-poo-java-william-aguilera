package com.connectfast.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Imports de la Semana 07
import com.connectfast.modelo.ServicePlan;
import com.connectfast.excepciones.PlanNoEncontradoException;
import com.connectfast.excepciones.PlanInvalidoException;

/**
 * Clase que gestiona la colección de ServicePlan. 
 * * S08 Refactorización:
 * - Uso de HashMap<String, ServicePlan> para búsqueda O(1).
 * - Uso de ArrayList<ServicePlan> para historial y operaciones estadísticas.
 */
public class GestorPlanes {
    
    // 1. HashMap para búsqueda rápida (O(1)). Key: planCode. Value: ServicePlan (Ejercicio 2)
    private Map<String, ServicePlan> planesPorCodigo; 

    // 2. ArrayList para mantener orden de inserción y facilitar estadísticas/filtrado (Ejercicio 1)
    private List<ServicePlan> historialPlanes; 

    public GestorPlanes() {
        // Uso de Generics en la inicialización
        this.planesPorCodigo = new HashMap<>();
        this.historialPlanes = new ArrayList<>();
    }

    // -----------------------------------------------------------
    // CRUD: Agregar con Validación de Duplicados (Ejercicio 2)
    // -----------------------------------------------------------
    
    /**
     * Agrega un plan al gestor, validando que el código no exista.
     * Sincroniza las colecciones HashMap y ArrayList.
     */
    public void agregarPlan(ServicePlan plan) throws PlanInvalidoException {
        if (plan == null) {
            throw new PlanInvalidoException("El plan a agregar no puede ser nulo.");
        }
        
        String codigo = plan.getPlanCode();
        
        // Validación 2: Chequear duplicados usando containsKey (Eficiencia O(1))
        if (planesPorCodigo.containsKey(codigo)) {
            throw new PlanInvalidoException("Ya existe un plan registrado con el código: " + codigo);
        }
        
        // Agregar a ambas colecciones (sincronización)
        planesPorCodigo.put(codigo, plan);
        historialPlanes.add(plan);
        System.out.println("✅ PLAN REGISTRADO: " + plan.getPlanName() + " (Código: " + codigo + ")");
    }

    // -----------------------------------------------------------
    // CRUD: Búsqueda O(1) (Ejercicio 2)
    // -----------------------------------------------------------
    
    /**
     * Busca un plan por código de forma rápida usando HashMap (O(1)).
     */
    public ServicePlan buscarPlan(String codigo) throws PlanNoEncontradoException {
        // Uso directo del HashMap para búsqueda O(1)
        ServicePlan plan = planesPorCodigo.get(codigo);
        
        if (plan == null) {
            throw new PlanNoEncontradoException("No se encontró ningún plan con el código: " + codigo);
        }
        return plan;
    }
    
    // -----------------------------------------------------------
    // CRUD: Eliminar y Sincronizar
    // -----------------------------------------------------------
    
    /**
     * Elimina un plan por código, sincronizando HashMap y ArrayList.
     */
    public ServicePlan eliminarPlan(String codigo) throws PlanNoEncontradoException {
        ServicePlan planEliminado = planesPorCodigo.remove(codigo); // Eliminar del HashMap
        
        if (planEliminado == null) {
             throw new PlanNoEncontradoException("No se puede eliminar: el plan con código " + codigo + " no existe.");
        }
        
        // Eliminar del ArrayList para mantener sincronización
        historialPlanes.remove(planEliminado);
        
        return planEliminado;
    }

    // -----------------------------------------------------------
    // OPERACIONES DE FILTRADO Y ESTADÍSTICAS (Ejercicio 3)
    // -----------------------------------------------------------

    /**
     * 1. FILTRADO: Filtra los planes por su tipo (Residencial, Empresarial, Gamer).
     */
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

    /**
     * 2. ESTADÍSTICA: Calcula el ingreso mensual total de todos los planes activos.
     */
    public double calcularIngresoMensualTotal() {
        double total = 0;
        for (ServicePlan plan : historialPlanes) {
            total += plan.getMonthlyPrice();
        }
        return total;
    }
    
    /**
     * 3. ESTADÍSTICA: Calcula el precio mensual promedio de todos los planes activos.
     */
    public double calcularPromedioMensual() {
        if (historialPlanes.isEmpty()) return 0;
        return calcularIngresoMensualTotal() / historialPlanes.size();
    }

    /**
     * 4. ESTADÍSTICA: Encuentra el plan con la mayor velocidad (speedMbps).
     */
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
    
    /**
     * 5. ESTADÍSTICA: Conteo por Tipo de Plan (Uso de HashMap para acumular)
     */
    public Map<String, Integer> contarPlanesPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        for (ServicePlan plan : historialPlanes) {
            String tipo = plan.getTipoPlan();
            conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
        }
        return conteo;
    }
    
    // --- MÉTODOS AUXILIARES ---
    
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