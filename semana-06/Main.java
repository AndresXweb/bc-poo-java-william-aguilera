public class Main {
    public static void main(String[] args) {
        System.out.println("  CONNECTFAST - SEMANA 06");
        System.out.println("  Abstraccion e Interfaces");
        
        System.out.println("--- DEMOSTRACION 1: Clase Abstracta ---");
        System.out.println("ServicePlan es abstracta, no se puede instanciar directamente");
        System.out.println("Solo se pueden crear instancias de las subclases concretas\n");
        
        // Esto daria error de compilacion:

        System.out.println("--- DEMOSTRACION 2: Polimorfismo con Abstracta ---\n");
        
        ServicePlan[] planes = new ServicePlan[3];
        planes[0] = new PlanResidencial("PLAN-001", "Hogar Basico", 50, 45000, 80, true);
        planes[1] = new PlanEmpresarial("PLAN-002", "Pyme Plus", 200, 150000, true, 24, "Premium");
        planes[2] = new PlanGamer("PLAN-003", "Ultra Gamer", 500, 180000, 5, true);
        
        for (ServicePlan plan : planes) {
            System.out.println("=== " + plan.getPlanName() + " ===");
            System.out.println(plan.obtenerDescripcion());
            System.out.println("Costo instalacion: $" + plan.calcularCostoInstalacion());
            System.out.println();
        }
        
        // DEMOSTRACION 3: Interface Facturable
        System.out.println("\n--- DEMOSTRACION 3: Interface Facturable ---\n");
        
        Facturable planFacturable = new PlanResidencial("PLAN-004", "Hogar Premium", 200, 85000, 150, true);
        
        System.out.println(planFacturable.generarFactura());
        System.out.println("\nDetalle: " + planFacturable.obtenerDetalleFacturacion());
        
        // DEMOSTRACION 4: Interface Auditable
        System.out.println("\n--- DEMOSTRACION 4: Interface Auditable ---\n");
        
        ServicePlan planAuditable = new PlanEmpresarial("PLAN-005", "Corporativo", 500, 350000, true, 24, "Enterprise");
        
        // Realizar cambios
        planAuditable.setMonthlyPrice(320000);
        planAuditable.setSpeedMbps(600);
        
        Auditable auditable = (Auditable) planAuditable;
        System.out.println(auditable.obtenerHistorial());
        
        // DEMOSTRACION 5: Interface Promocionable
        System.out.println("\n--- DEMOSTRACION 5: Interface Promocionable ---\n");
        
        PlanResidencial planPromo = new PlanResidencial("PLAN-006", "Hogar Plus", 100, 65000, 120, true);
        
        System.out.println("Plan: " + planPromo.getPlanName());
        System.out.println("Precio normal: $" + planPromo.getMonthlyPrice());
        System.out.println(planPromo.obtenerPromocionesActivas());
        
        if (planPromo.esElegiblePromocion()) {
            double precioConPromo = planPromo.aplicarPromocion("HOGAR2025");
            System.out.println("Precio con promocion: $" + precioConPromo);
        }
        
        // DEMOSTRACION 6: Multiple implementacion
        System.out.println("\n--- DEMOSTRACION 6: Multiple Implementacion ---\n");
        
        PlanGamer planGamer = new PlanGamer("PLAN-007", "Pro Gamer", 500, 180000, 3, true);
        
        // Usar como ServicePlan
        ServicePlan comoServicePlan = planGamer;
        System.out.println("Como ServicePlan:");
        System.out.println("  " + comoServicePlan.obtenerDescripcion());
        
        // Usar como Facturable
        Facturable comoFacturable = planGamer;
        System.out.println("\nComo Facturable:");
        System.out.println("  IVA: $" + comoFacturable.calcularImpuestos());
        
        // Usar como Auditable
        Auditable comoAuditable = planGamer;
        System.out.println("\nComo Auditable:");
        System.out.println("  " + comoAuditable.obtenerUltimoCambio());
        
        // Usar como Promocionable
        Promocionable comoPromocionable = planGamer;
        System.out.println("\nComo Promocionable:");
        System.out.println("  " + comoPromocionable.obtenerPromocionesActivas());
        
        // DEMOSTRACION 7: Metodos abstractos en accion
        System.out.println("\n--- DEMOSTRACION 7: Metodos Abstractos ---\n");
        
        System.out.println("Llamando metodos abstractos (cada subclase implementa diferente):\n");
        
        for (ServicePlan plan : planes) {
            System.out.println("Plan: " + plan.getPlanName());
            System.out.println("Beneficios:");
            System.out.println(plan.obtenerBeneficios());
            System.out.println("\n---\n");
        }
        
        // DEMOSTRACION 8: Uso practico combinado
        System.out.println("\n--- DEMOSTRACION 8: Uso Practico ---\n");
        
        System.out.println("Procesando facturacion de todos los planes:\n");
        
        for (ServicePlan plan : planes) {
            if (plan instanceof Facturable) {
                Facturable f = (Facturable) plan;
                System.out.println(plan.getPlanName() + " - Total con IVA: $" + 
                                 (plan.getMonthlyPrice() + f.calcularImpuestos()));
            }
        }
        
        System.out.println("\n\nPlanes con promociones disponibles:\n");
        
        for (ServicePlan plan : planes) {
            if (plan instanceof Promocionable) {
                Promocionable p = (Promocionable) plan;
                if (p.esElegiblePromocion()) {
                    System.out.println(plan.getPlanName() + ": " + p.obtenerPromocionesActivas());
                }
            }
        }
        
        // RESUMEN FINAL
        System.out.println("RESUMEN DE ABSTRACCION E INTERFACES");
        System.out.println("1. ServicePlan es ABSTRACTA: No se puede instanciar");
        System.out.println("2. Metodos abstractos: Cada subclase los implementa");
        System.out.println("3. Facturable: Define contrato de facturacion");
        System.out.println("4. Auditable: Define contrato de auditoria");
        System.out.println("5. Promocionable: Define contrato de promociones");
        System.out.println("6. Multiple implementacion: Una clase puede implementar varias interfaces");
        System.out.println("7. Polimorfismo: Referencias de tipo abstracto/interface");
    }
}