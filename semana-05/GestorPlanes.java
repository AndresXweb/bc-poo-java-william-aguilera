import java.util.ArrayList;

public class GestorPlanes {
    private ArrayList<ServicePlan> planes;
    private String nombreEmpresa;
    
    public GestorPlanes(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.planes = new ArrayList<>();
    }
    
    public void agregarPlan(ServicePlan plan) {
        if (plan == null) {
            throw new IllegalArgumentException("Plan no puede ser nulo");
        }
        planes.add(plan);
        System.out.println("Plan agregado: " + plan.getPlanName());
    }
    
    public ServicePlan buscarPlan(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            return null;
        }
        
        for (ServicePlan plan : planes) {
            if (plan.getPlanCode().equals(codigo)) {
                return plan;
            }
        }
        return null;
    }
    
    public ArrayList<ServicePlan> buscarPlan(double precioMin, double precioMax) {
        ArrayList<ServicePlan> resultados = new ArrayList<>();
        
        for (ServicePlan plan : planes) {
            double precio = plan.getMonthlyPrice();
            if (precio >= precioMin && precio <= precioMax) {
                resultados.add(plan);
            }
        }
        
        return resultados;
    }
    
    public ArrayList<ServicePlan> buscarPlan(int velocidadMinima) {
        ArrayList<ServicePlan> resultados = new ArrayList<>();
        
        for (ServicePlan plan : planes) {
            if (plan.getSpeedMbps() >= velocidadMinima) {
                resultados.add(plan);
            }
        }
        
        return resultados;
    }

    public void procesarPlan(ServicePlan plan) {
        System.out.println("\n--- Procesando Plan ---");
        plan.mostrarInformacion();
        System.out.println("Precio con IVA: $" + plan.calcularPrecioConImpuesto());
        System.out.println("Tipo real del objeto: " + plan.getClass().getSimpleName());
    }
    
    public void procesarTodos() {
        System.out.println("  PROCESANDO TODOS LOS PLANES");
        
        for (ServicePlan plan : planes) {
            procesarPlan(plan);
        }
    }
    
    public void calcularPrecioAnual(ServicePlan plan) {
        System.out.println("\nPlan: " + plan.getPlanName());
        System.out.println("Precio mensual: $" + plan.getMonthlyPrice());
        double precioAnual = plan.calcularPrecioConDescuento(12);
        System.out.println("Precio anual con descuento: $" + precioAnual);
        double ahorroMensual = ((plan.getMonthlyPrice() * 12) - precioAnual) / 12;
        System.out.println("Ahorro mensual: $" + ahorroMensual);
    }
    
    public void generarReporte() {

        System.out.println("  REPORTE DE PLANES - " + nombreEmpresa);
        
        int residenciales = 0;
        int empresariales = 0;
        int gamers = 0;
        double sumaPrecios = 0;
        
        for (ServicePlan plan : planes) {
            sumaPrecios += plan.getMonthlyPrice();
            
            if (plan instanceof PlanResidencial) {
                residenciales++;
            } else if (plan instanceof PlanEmpresarial) {
                empresariales++;
            } else if (plan instanceof PlanGamer) {
                gamers++;
            }
        }
        
        System.out.println("Total de planes: " + planes.size());
        System.out.println("Planes Residenciales: " + residenciales);
        System.out.println("Planes Empresariales: " + empresariales);
        System.out.println("Planes Gamer: " + gamers);
        System.out.println("Precio promedio: $" + (sumaPrecios / planes.size()));
    }
    
    public ServicePlan obtenerPlanMasRapido() {
        if (planes.isEmpty()) {
            return null;
        }
        
        ServicePlan masRapido = planes.get(0);
        for (ServicePlan plan : planes) {
            if (plan.getSpeedMbps() > masRapido.getSpeedMbps()) {
                masRapido = plan;
            }
        }
        
        return masRapido;
    }
    
    public void compararPlanes(ServicePlan plan1, ServicePlan plan2) {
        System.out.println("\n========================================");
        System.out.println("  COMPARACION DE PLANES");
        System.out.println("========================================\n");
        
        System.out.println("Plan 1: " + plan1.getPlanName() + " (" + plan1.getTipoPlan() + ")");
        System.out.println("Plan 2: " + plan2.getPlanName() + " (" + plan2.getTipoPlan() + ")");
        System.out.println();
        
        System.out.println("Velocidad:");
        System.out.println("  " + plan1.getPlanName() + ": " + plan1.getSpeedMbps() + " Mbps");
        System.out.println("  " + plan2.getPlanName() + ": " + plan2.getSpeedMbps() + " Mbps");
        
        System.out.println("\nPrecio mensual:");
        System.out.println("  " + plan1.getPlanName() + ": $" + plan1.getMonthlyPrice());
        System.out.println("  " + plan2.getPlanName() + ": $" + plan2.getMonthlyPrice());
        
        System.out.println("\nPrecio anual con descuento:");
        System.out.println("  " + plan1.getPlanName() + ": $" + plan1.calcularPrecioConDescuento(12));
        System.out.println("  " + plan2.getPlanName() + ": $" + plan2.calcularPrecioConDescuento(12));
    }
    
    public ArrayList<ServicePlan> getPlanes() {
        return planes;
    }
}