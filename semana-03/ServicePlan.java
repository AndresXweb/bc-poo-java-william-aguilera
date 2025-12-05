public class ServicePlan {
    private String planCode;
    private String planName;
    private int speedMbps;
    private int tvChannels;
    private double monthlyPrice;
    
    // Constructor completo
    public ServicePlan(String planCode, String planName, int speedMbps, int tvChannels, double monthlyPrice) {
        setPlanCode(planCode);
        setPlanName(planName);
        setSpeedMbps(speedMbps);
        setTvChannels(tvChannels);
        setMonthlyPrice(monthlyPrice);
    }
    
    // Constructor basico (sin canales y precio)
    public ServicePlan(String planCode, String planName, int speedMbps) {
        this(planCode, planName, speedMbps, 80, 50000);
    }
    
    // Constructor minimo (solo codigo y nombre)
    public ServicePlan(String planCode, String planName) {
        this(planCode, planName, 50);
    }
    
    public String getPlanCode() {
        return planCode;
    }
    
    public void setPlanCode(String planCode) {
        if (planCode == null || planCode.isEmpty()) {
            throw new IllegalArgumentException("Codigo no puede estar vacio");
        }
        if (!planCode.startsWith("PLAN-")) {
            throw new IllegalArgumentException("Codigo debe empezar con PLAN-");
        }
        this.planCode = planCode;
    }
    
    public String getPlanName() {
        return planName;
    }
    
    public void setPlanName(String planName) {
        if (planName == null || planName.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacio");
        }
        this.planName = planName;
    }
    
    public int getSpeedMbps() {
        return speedMbps;
    }
    
    public void setSpeedMbps(int speedMbps) {
        if (speedMbps < 10 || speedMbps > 1000) {
            throw new IllegalArgumentException("Velocidad debe estar entre 10 y 1000 Mbps");
        }
        this.speedMbps = speedMbps;
    }
    
    public int getTvChannels() {
        return tvChannels;
    }
    
    public void setTvChannels(int tvChannels) {
        if (tvChannels < 0) {
            throw new IllegalArgumentException("Canales no puede ser negativo");
        }
        this.tvChannels = tvChannels;
    }
    
    public double getMonthlyPrice() {
        return monthlyPrice;
    }
    
    public void setMonthlyPrice(double monthlyPrice) {
        if (monthlyPrice <= 0) {
            throw new IllegalArgumentException("Precio debe ser mayor a cero");
        }
        this.monthlyPrice = monthlyPrice;
    }
    
    public void mostrarInformacion() {
        System.out.println("Plan: " + planName);
        System.out.println("Codigo: " + planCode);
        System.out.println("Velocidad: " + speedMbps + " Mbps");
        System.out.println("Canales TV: " + tvChannels);
        System.out.println("Precio: $" + monthlyPrice);
    }
    
    public double calcularPrecioConImpuesto() {
        return monthlyPrice + (monthlyPrice * 0.19);
    }
}