public class ServicePlan {
    String planCode;
    String planName;
    int speedMbps;
    int tvChannels;
    double monthlyPrice;
    
    public ServicePlan(String planCode, String planName, int speedMbps, int tvChannels, double monthlyPrice) {
        this.planCode = planCode;
        this.planName = planName;
        this.speedMbps = speedMbps;
        this.tvChannels = tvChannels;
        this.monthlyPrice = monthlyPrice;
    }
    
    public void showInfo() {
        System.out.println(" PLAN DE SERVICIO ");
        System.out.println("Codigo: " + planCode);
        System.out.println("Nombre: " + planName);
        System.out.println("Velocidad: " + speedMbps + " Mbps");
        System.out.println("Canales TV: " + tvChannels);
        System.out.println("Precio mensual: $" + monthlyPrice);
        System.out.println();
    }
    
    public double calcularPrecioConImpuesto() {
        double iva = 0.19;
        double precioConIva = monthlyPrice + (monthlyPrice * iva);
        System.out.println("Precio con IVA (19%): $" + precioConIva);
        return precioConIva;
    }
}