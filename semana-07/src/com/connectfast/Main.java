package com.connectfast.servicio;

import java.util.ArrayList;
import java.util.List;
import com.connectfast.modelo.ServicePlan;
import com.connectfast.excepciones.PlanNoEncontradoException;

public class GestorPlanes {
    
    private List<ServicePlan> planes;

    public GestorPlanes() {
        this.planes = new ArrayList<>();
    }

    public void agregarPlan(ServicePlan plan) {
        if (plan != null) {
            planes.add(plan);
            System.out.println("✅ PLAN REGISTRADO: " + plan.getPlanName());
        }
    }

    public ServicePlan buscarPlan(String codigo) throws PlanNoEncontradoException {
        
        for (ServicePlan plan : planes) {
            if (plan.getPlanCode().equalsIgnoreCase(codigo)) {
                return plan;
            }
        }
        
        throw new PlanNoEncontradoException("No se encontro ningun plan con el codigo: " + codigo);
    }

    public void mostrarCatalogo() {
        System.out.println("\n--- CATALOGO DISPONIBLE (" + planes.size() + " planes) ---");
        for (ServicePlan plan : planes) {
            System.out.println("- [" + plan.getPlanCode() + "] " + plan.getPlanName() + 
                             " ($" + plan.getMonthlyPrice() + ")");
        }
        System.out.println("-------------------------------------------");
    }
}