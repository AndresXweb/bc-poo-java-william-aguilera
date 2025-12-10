public class Main {
    public static void main(String[] args) {

        System.out.println("  CONNECTFAST - SEMANA 03             ");
        System.out.println("  Constructores y Validaciones        ");

        
        // Demostrar constructores sobrecargados de ServicePlan
        System.out.println("--- Demostracion Constructores ServicePlan ---");
        
        ServicePlan plan1 = new ServicePlan("PLAN-001", "Basico Hogar", 50, 80, 45000);
        System.out.println("Plan 1 (constructor completo): " + plan1.getPlanName());
        
        ServicePlan plan2 = new ServicePlan("PLAN-002", "Premium Hogar", 200);
        System.out.println("Plan 2 (constructor basico): " + plan2.getPlanName() + 
                         " - Canales: " + plan2.getTvChannels() + " - Precio: $" + plan2.getMonthlyPrice());
        
        ServicePlan plan3 = new ServicePlan("PLAN-003", "Empresarial");
        System.out.println("Plan 3 (constructor minimo): " + plan3.getPlanName() + 
                         " - Velocidad: " + plan3.getSpeedMbps() + " Mbps");
        
        System.out.println();
        
        // Demostrar constructores sobrecargados de Cliente
        System.out.println("--- Demostracion Constructores Cliente ---");
        
        Cliente cliente1 = new Cliente("CLI-001", "Juan Perez", "Calle 123", "555-1001");
        System.out.println("Cliente 1 (constructor completo): " + cliente1.getNombreCompleto());
        
        Cliente cliente2 = new Cliente("CLI-002", "Maria Garcia", "Carrera 7");
        System.out.println("Cliente 2 (sin telefono): " + cliente2.getNombreCompleto() + 
                         " - Tel: " + cliente2.getTelefono());
        
        Cliente cliente3 = new Cliente("CLI-003", "Carlos Rodriguez");
        System.out.println("Cliente 3 (basico): " + cliente3.getNombreCompleto() + 
                         " - Dir: " + cliente3.getDireccion());
        
        System.out.println();
        
        // Demostrar constructores sobrecargados de Tecnico
        System.out.println("--- Demostracion Constructores Tecnico ---");
        
        Tecnico tecnico1 = new Tecnico("TEC-001", "Carlos Lopez", "Fibra Optica", "Usaquen");
        System.out.println("Tecnico 1 (completo): " + tecnico1.getNombre() + 
                         " - " + tecnico1.getEspecialidad());
        
        Tecnico tecnico2 = new Tecnico("TEC-002", "Ana Martinez", "Instalacion TV");
        System.out.println("Tecnico 2 (sin zona): " + tecnico2.getNombre() + 
                         " - Zona: " + tecnico2.getZonaAsignada());
        
        Tecnico tecnico3 = new Tecnico("TEC-003", "Pedro Gomez");
        System.out.println("Tecnico 3 (basico): " + tecnico3.getNombre() + 
                         " - Esp: " + tecnico3.getEspecialidad());
        
        System.out.println();
        
        // Demostrar validaciones
        System.out.println("--- Demostracion Validaciones ---");
        
        try {
            ServicePlan planInvalido = new ServicePlan("001", "Test", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
        
        try {
            ServicePlan planInvalido2 = new ServicePlan("PLAN-004", "Test", 5000);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
        
        try {
            Cliente clienteInvalido = new Cliente("CLI-004", "Ab");
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
        
        System.out.println();
        
        // Crear sistema completo
        System.out.println("--- Sistema Completo ---\n");
        
        GestorInstalaciones gestor = new GestorInstalaciones("ConnectFast");
        
        Instalacion inst1 = new Instalacion("INST-001", cliente1, plan1, tecnico1, "2025-11-15");
        Instalacion inst2 = new Instalacion("INST-002", cliente2, plan2, tecnico2);
        
        gestor.agregarInstalacion(inst1);
        gestor.agregarInstalacion(inst2);
        
        System.out.println();
        gestor.mostrarTodasInstalaciones();
        
        System.out.println("--- Intentar agregar instalacion duplicada ---");
        try {
            Instalacion inst3 = new Instalacion("INST-001", cliente3, plan3, tecnico3);
            gestor.agregarInstalacion(inst3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
        

        System.out.println("RESUMEN");

        System.out.println("Total instalaciones: " + gestor.contarInstalaciones());
        System.out.println("Las validaciones funcionan correctamente");
        System.out.println("Los constructores sobrecargados facilitan la creacion de objetos");
  
    }
}