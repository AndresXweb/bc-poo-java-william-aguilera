public class Main {
    public static void main(String[] args) {

        System.out.println("   CONNECTFAST - PLANES DE SERVICIO    ");
        System.out.println();
        
        ServicePlan plan1 = new ServicePlan("PLAN-001", "Basico Hogar", 50, 80, 45000);
        ServicePlan plan2 = new ServicePlan("PLAN-002", "Premium Hogar", 200, 150, 85000);
        ServicePlan plan3 = new ServicePlan("PLAN-003", "Empresarial Basico", 100, 100, 120000);
        ServicePlan plan4 = new ServicePlan("PLAN-004", "Empresarial Plus", 500, 150, 250000);
        ServicePlan plan5 = new ServicePlan("PLAN-005", "Gamer Ultra", 300, 120, 150000);
        
        plan1.showInfo();
        plan2.showInfo();
        plan3.showInfo();
        plan4.showInfo();
        plan5.showInfo();
        
        System.out.println("   CALCULO DE PRECIO CON IMPUESTO      ");
        System.out.println();
        
        System.out.println("Plan: " + plan1.planName);
        plan1.calcularPrecioConImpuesto();
        System.out.println();
        
        System.out.println("Plan: " + plan2.planName);
        plan2.calcularPrecioConImpuesto();
        System.out.println();
        
       
        System.out.println("        CLIENTES REGISTRADOS           ");
        System.out.println();
        
        Cliente cliente1 = new Cliente("CLI-001", "Juan Perez", "Calle 123 #45-67, Usaquen", "PLAN-001");
        Cliente cliente2 = new Cliente("CLI-002", "Maria Garcia", "Carrera 7 #89-12, Chapinero", "PLAN-002");
        Cliente cliente3 = new Cliente("CLI-003", "Carlos Rodriguez", "Avenida 68 #23-45, Suba", "PLAN-005");
        
        cliente1.mostrarInformacion();
        cliente2.mostrarInformacion();
        cliente3.mostrarInformacion();
        
  
        System.out.println("   RELACION CLIENTE - PLAN    ");
        System.out.println();
        
        System.out.println("El cliente " + cliente1.nombreCompleto + " tiene contratado el plan: " + cliente1.planContratado);
        System.out.println("El cliente " + cliente2.nombreCompleto + " tiene contratado el plan: " + cliente2.planContratado);
        System.out.println("El cliente " + cliente3.nombreCompleto + " tiene contratado el plan: " + cliente3.planContratado);
    }
}