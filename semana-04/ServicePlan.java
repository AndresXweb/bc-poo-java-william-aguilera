public class ServicePlan {
    protected String planCode;
    protected String planName;
    protected int speedMbps;
    protected double monthlyPrice;
    protected String tipoPlan;
    
    public ServicePlan(String planCode, String planName, int speedMbps, double monthlyPrice, String tipoPlan) {
        setPlanCode(planCode);
        setPlanName(planName);
        setSpeedMbps(speedMbps);
        setMonthlyPrice(monthlyPrice);
        this.tipoPlan = tipoPlan;
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
    
    public double getMonthlyPrice() {
        return monthlyPrice;
    }
    
    public void setMonthlyPrice(double monthlyPrice) {
        if (monthlyPrice <= 0) {
            throw new IllegalArgumentException("Precio debe ser mayor a cero");
        }
        this.monthlyPrice = monthlyPrice;
    }
    
    public String getTipoPlan() {
        return tipoPlan;
    }
    
    public void mostrarInformacion() {
        System.out.println("Plan: " + planName);
        System.out.println("Codigo: " + planCode);
        System.out.println("Tipo: " + tipoPlan);
        System.out.println("Velocidad: " + speedMbps + " Mbps");
        System.out.println("Precio mensual: $" + monthlyPrice);
    }
    
    public double calcularPrecioConDescuento(int meses) {
        return monthlyPrice * meses;
    }
    
    public double calcularPrecioConImpuesto() {
        return monthlyPrice + (monthlyPrice * 0.19);
    }
}