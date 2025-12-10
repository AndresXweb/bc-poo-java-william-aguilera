import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("  CONNECTFAST - SEMANA 05");
        System.out.println("  Polimorfismo Avanzado");
        
        // Crear gestor
        GestorPlanes gestor = new GestorPlanes("ConnectFast");
        
        // Crear planes de diferentes tipos
        ServicePlan plan1 = new PlanResidencial("PLAN-001", "Hogar Basico", 50, 45000, 80, true);
        ServicePlan plan2 = new PlanResidencial("PLAN-002", "Hogar Premium", 200, 85000, 150, true);
        ServicePlan plan3 = new PlanEmpresarial("PLAN-003", "Pyme Plus", 200, 150000, true, 24, "Premium");
        ServicePlan plan4 = new PlanEmpresarial("PLAN-004", "Corporativo", 500, 350000, true, 24, "Enterprise");
        ServicePlan plan5 = new PlanGamer("PLAN-005", "Ultra Gamer", 500, 180000, 5, true);
        
        // Agregar planes al gestor (metodo polimorfico)
        System.out.println("--- Agregando Planes al Sistema ---");
        gestor.agregarPlan(plan1);
        gestor.agregarPlan(plan2);
        gestor.agregarPlan(plan3);
        gestor.agregarPlan(plan4);
        gestor.agregarPlan(plan5);
        
        // DEMOSTRACION 1: Sobrecarga de metodos
        System.out.println("  DEMOSTRACION 1: SOBRECARGA");
        
        System.out.println("\n1. Buscar por codigo (String):");
        ServicePlan encontrado = gestor.buscarPlan("PLAN-003");
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.getPlanName());
        }
        
        System.out.println("\n2. Buscar por rango de precio (double, double):");
        ArrayList<ServicePlan> enRango = gestor.buscarPlan(40000, 100000);
        System.out.println("Planes entre $40,000 y $100,000:");
        for (ServicePlan p : enRango) {
            System.out.println("  - " + p.getPlanName() + ": $" + p.getMonthlyPrice());
        }
        
        System.out.println("\n3. Buscar por velocidad minima (int):");
        ArrayList<ServicePlan> rapidos = gestor.buscarPlan(200);
        System.out.println("Planes con velocidad >= 200 Mbps:");
        for (ServicePlan p : rapidos) {
            System.out.println("  - " + p.getPlanName() + ": " + p.getSpeedMbps() + " Mbps");
        }
        
        System.out.println("  DEMOSTRACION 2: METODOS POLIMORFICOS");
        
        System.out.println("\nProcesando planes individuales:");
        gestor.procesarPlan(plan1);
        gestor.procesarPlan(plan3);
        gestor.procesarPlan(plan5);
        
        // DEMOSTRACION 3: ArrayList polimorfico
        System.out.println("  DEMOSTRACION 3: ARRAYLIST POLIMORFICO");
        
        ArrayList<ServicePlan> catalogo = new ArrayList<>();
        catalogo.add(plan1);
        catalogo.add(plan3);
        catalogo.add(plan5);
        
        System.out.println("Recorriendo ArrayList polimorfico:");
        for (ServicePlan p : catalogo) {
            System.out.println("\nPlan: " + p.getPlanName());
            System.out.println("Tipo: " + p.getTipoPlan());
            System.out.println("Tipo real: " + p.getClass().getSimpleName());
            double precioAnual = p.calcularPrecioConDescuento(12);
            System.out.println("Precio anual: $" + precioAnual);
        }
        
        // DEMOSTRACION 4: Dynamic binding (enlace dinamico)
        System.out.println("  DEMOSTRACION 4: DYNAMIC BINDING");
        
        System.out.println("Mismo metodo, comportamiento diferente segun el tipo:");
        
        ServicePlan[] array = {plan1, plan3, plan5};
        for (ServicePlan plan : array) {
            System.out.println("\n--- " + plan.getPlanName() + " ---");
            plan.mostrarInformacion();
        }
        
        // DEMOSTRACION 5: Calculos polimorficos
        System.out.println("  DEMOSTRACION 5: CALCULOS POLIMORFICOS");
        
        System.out.println("\n Calculando precios anuales:");
        gestor.calcularPrecioAnual(plan1);
        gestor.calcularPrecioAnual(plan3);
        gestor.calcularPrecioAnual(plan5);
        
        // DEMOSTRACION 6: Comparacion polimorf ica

        System.out.println("  DEMOSTRACION 6: COMPARACION");
        
        gestor.compararPlanes(plan1, plan2);
        gestor.compararPlanes(plan3, plan4);
        
        // DEMOSTRACION 7: Reporte general
        gestor.generarReporte();
        
        // DEMOSTRACION 8: Plan mas rapido

        System.out.println("  PLAN MAS RAPIDO");
        
        ServicePlan masRapido = gestor.obtenerPlanMasRapido();
        if (masRapido != null) {
            System.out.println("El plan mas rapido es:");
            masRapido.mostrarInformacion();
        }
        
        // DEMOSTRACION 9: instanceof y casting
        System.out.println("  DEMOSTRACION 9: INSTANCEOF Y CASTING");
        
        for (ServicePlan plan : gestor.getPlanes()) {
            System.out.println("Plan: " + plan.getPlanName());
            
            if (plan instanceof PlanResidencial) {
                PlanResidencial pr = (PlanResidencial) plan;
                System.out.println("  Tipo: Residencial");
                System.out.println("  " + pr.obtenerBeneficios());
            } else if (plan instanceof PlanEmpresarial) {
                PlanEmpresarial pe = (PlanEmpresarial) plan;
                System.out.println("  Tipo: Empresarial");
                System.out.println("  " + pe.obtenerBeneficios());
            } else if (plan instanceof PlanGamer) {
                PlanGamer pg = (PlanGamer) plan;
                System.out.println("  Tipo: Gamer");
                System.out.println("  " + pg.obtenerBeneficios());
            }
            System.out.println();
        }
        
        // RESUMEN FINAL
        System.out.println("RESUMEN DE LOS NUEVOS POLIMORFISMOS CREADOS");
        System.out.println("1. Sobrecarga: Mismo metodo, diferentes parametros");
        System.out.println("2. Sobrescritura: Subclases modifican comportamiento");
        System.out.println("3. Metodos polimorficos: Aceptan clase padre");
        System.out.println("4. Dynamic binding: Enlace en tiempo de ejecucion");
        System.out.println("5. ArrayList polimorfico: Almacena diferentes tipos");
    }
}