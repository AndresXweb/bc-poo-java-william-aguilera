public class Main {
    public static void main(String[] args) {

        System.out.println("     CONNECTFAST - SISTEMA DE GESTION  ");


        // Crear planes de servicio
        ServicePlan plan1 = new ServicePlan("PLAN-001", "Basico Hogar", 50, 80, 45000);
        ServicePlan plan2 = new ServicePlan("PLAN-002", "Premium Hogar", 200, 150, 85000);
        ServicePlan plan3 = new ServicePlan("PLAN-003", "Empresarial Plus", 500, 150, 250000);

        System.out.println(" Planes Disponibles ");
        plan1.mostrarInformacion();
        System.out.println();
        plan2.mostrarInformacion();
        System.out.println();

        // Crear clientes
        Cliente cliente1 = new Cliente("CLI-001", "Juan Perez", "Calle 123 #45-67, Usaquen", "555-1001");
        Cliente cliente2 = new Cliente("CLI-002", "Maria Garcia", "Carrera 7 #89-12, Chapinero", "555-1002");
        Cliente cliente3 = new Cliente("CLI-003", "Carlos Rodriguez", "Avenida 68 #23-45, Suba", "555-1003");

        System.out.println("\n Clientes Registrados ");
        cliente1.mostrarInformacion();
        System.out.println();

        // Crear tecnicos
        Tecnico tecnico1 = new Tecnico("TEC-001", "Carlos Lopez", "Fibra Optica", "Usaquen");
        Tecnico tecnico2 = new Tecnico("TEC-002", "Ana Martinez", "Instalacion TV", "Chapinero");

        System.out.println("\n Tecnicos Disponibles ");
        tecnico1.mostrarInformacion();
        System.out.println();
        tecnico2.mostrarInformacion();
        System.out.println();

        // Crear gestor de instalaciones
        GestorInstalaciones gestor = new GestorInstalaciones("ConnectFast");

        // Crear instalaciones (relacionando objetos)
        Instalacion inst1 = new Instalacion("INST-001", cliente1, plan1, tecnico1, "2025-11-15");
        Instalacion inst2 = new Instalacion("INST-002", cliente2, plan2, tecnico2, "2025-11-16");
        Instalacion inst3 = new Instalacion("INST-003", cliente3, plan3, tecnico1, "2025-11-17");

        // Agregar instalaciones al gestor
        gestor.agregarInstalacion(inst1);
        gestor.agregarInstalacion(inst2);
        gestor.agregarInstalacion(inst3);

        // Mostrar todas las instalaciones
        gestor.mostrarTodasInstalaciones();

        // Completar una instalacion
        System.out.println("\n Completando Instalacion ");
        inst1.completarInstalacion();
        System.out.println();

        // Mostrar instalaciones pendientes
        gestor.mostrarInstalacionesPendientes();

        // Calcular costos
        System.out.println("\n Calculos ");
        System.out.println("Costo total instalacion INST-001: $" + inst1.calcularCostoTotal());
        System.out.println("Ingreso total de todas las instalaciones: $" + gestor.calcularIngresoTotal());

        // Buscar instalacion
        System.out.println("\n Busqueda ");
        Instalacion buscada = gestor.buscarPorCodigo("INST-002");
        if (buscada != null) {
            System.out.println("Instalacion encontrada:");
            buscada.mostrarResumen();
        }

        // Resumen final

        System.out.println("RESUMEN FINAL");
        System.out.println("Total de instalaciones registradas: " + gestor.contarInstalaciones());
        System.out.println("Sistema funcionando correctamente");

    }
}