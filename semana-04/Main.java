public class Main {
    public static void main(String[] args) {

        System.out.println("  CONNECTFAST - SEMANA 04");
        System.out.println("  Herencia y Polimorfismo");

        
        System.out.println("--- Creando Planes de Diferentes Tipos ---\n");
        
        // Crear array polimorfico de ServicePlan
        ServicePlan[] catalogo = new ServicePlan[5];
        
        // Agregar diferentes tipos de planes
        catalogo[0] = new PlanResidencial("PLAN-001", "Hogar Basico", 50, 45000, 80, true);
        catalogo[1] = new PlanResidencial("PLAN-002", "Hogar Premium", 200, 85000, 150, true);
        catalogo[2] = new PlanEmpresarial("PLAN-003", "Pyme Plus", 200, 150000, true, 24, "Premium");
        catalogo[3] = new PlanEmpresarial("PLAN-004", "Corporativo", 500, 350000, true, 24, "Enterprise");
        catalogo[4] = new PlanGamer("PLAN-005", "Ultra Gamer", 500, 180000, 5, true);
        
        // Mostrar todos los planes usando polimorfismo
        System.out.println("=== CATALOGO COMPLETO DE PLANES ===\n");
        
        for (ServicePlan plan : catalogo) {
            plan.mostrarInformacion();
            System.out.println();
        }
        

        System.out.println("  DEMOSTRACION DE POLIMORFISMO");

        

        System.out.println("--- Precio Contrato 12 Meses ---\n");
        
        for (int i = 0; i < catalogo.length; i++) {
            ServicePlan plan = catalogo[i];
            System.out.println("Plan: " + plan.getPlanName() + " (" + plan.getTipoPlan() + ")");
            System.out.println("Precio mensual: $" + plan.getMonthlyPrice());
            double precioAnual = plan.calcularPrecioConDescuento(12);
            System.out.println("Precio total 12 meses: $" + precioAnual);
            System.out.println("Ahorro mensual promedio: $" + ((plan.getMonthlyPrice() * 12) - precioAnual) / 12);

        }
        

        System.out.println("  COMPARACION POR TIPO");

        

        int residenciales = 0;
        int empresariales = 0;
        int gamers = 0;
        
        for (ServicePlan plan : catalogo) {
            if (plan instanceof PlanResidencial) {
                residenciales++;
            } else if (plan instanceof PlanEmpresarial) {
                empresariales++;
            } else if (plan instanceof PlanGamer) {
                gamers++;
            }
        }
        
        System.out.println("Planes Residenciales: " + residenciales);
        System.out.println("Planes Empresariales: " + empresariales);
        System.out.println("Planes Gamer: " + gamers);
        System.out.println("Total planes: " + catalogo.length);
        
        System.out.println("  BENEFICIOS ESPECIFICOS");

        
        // Mostrar beneficios usando casting
        for (ServicePlan plan : catalogo) {
            System.out.print(plan.getPlanName() + ": ");
            
            if (plan instanceof PlanResidencial) {
                PlanResidencial pr = (PlanResidencial) plan;
                System.out.println(pr.obtenerBeneficios());
            } else if (plan instanceof PlanEmpresarial) {
                PlanEmpresarial pe = (PlanEmpresarial) plan;
                System.out.println(pe.obtenerBeneficios());
            } else if (plan instanceof PlanGamer) {
                PlanGamer pg = (PlanGamer) plan;
                System.out.println(pg.obtenerBeneficios());
            }
        }
        

        System.out.println("  BUSQUEDA DE PLAN MAS RAPIDO");

        
        ServicePlan planMasRapido = catalogo[0];
        for (ServicePlan plan : catalogo) {
            if (plan.getSpeedMbps() > planMasRapido.getSpeedMbps()) {
                planMasRapido = plan;
            }
        }
        
        System.out.println("El plan mas rapido es:");
        planMasRapido.mostrarInformacion();
        

        System.out.println("RESUMEN");

        System.out.println("La herencia permite crear diferentes tipos de planes");
        System.out.println("Cada tipo tiene caracteristicas propias");
        System.out.println("El polimorfismo permite tratarlos a todos como ServicePlan");
        System.out.println("Pero cada uno mantiene su comportamiento especifico");

    }
}