public class ServicePlan {
    private String planCode;
    private String planName;
    private int speedMbps;
    private int tvChannels;
    private double monthlyPrice;

    public ServicePlan(String planCode, String planName, int speedMbps, int tvChannels, double monthlyPrice) {
        this.planCode = planCode;
        this.planName = planName;
        this.speedMbps = speedMbps;
        this.tvChannels = tvChannels;
        this.monthlyPrice = monthlyPrice;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getSpeedMbps() {
        return speedMbps;
    }

    public void setSpeedMbps(int speedMbps) {
        this.speedMbps = speedMbps;
    }

    public int getTvChannels() {
        return tvChannels;
    }

    public void setTvChannels(int tvChannels) {
        this.tvChannels = tvChannels;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
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