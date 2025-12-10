public class PlanEmpresarial extends ServicePlan {
    private boolean ipFija;
    private int horasSoporte;
    private String nivelSLA;
    
    public PlanEmpresarial(String planCode, String planName, int speedMbps, double monthlyPrice,
                          boolean ipFija, int horasSoporte, String nivelSLA) {
        super(planCode, planName, speedMbps, monthlyPrice, "Empresarial");
        this.ipFija = ipFija;
        setHorasSoporte(horasSoporte);
        setNivelSLA(nivelSLA);
    }
    
    public boolean isIpFija() {
        return ipFija;
    }
    
    public void setIpFija(boolean ipFija) {
        this.ipFija = ipFija;
    }
    
    public int getHorasSoporte() {
        return horasSoporte;
    }
    
    public void setHorasSoporte(int horasSoporte) {
        if (horasSoporte < 0 || horasSoporte > 24) {
            throw new IllegalArgumentException("Horas de soporte debe estar entre 0 y 24");
        }
        this.horasSoporte = horasSoporte;
    }
    
    public String getNivelSLA() {
        return nivelSLA;
    }
    
    public void setNivelSLA(String nivelSLA) {
        if (nivelSLA == null || nivelSLA.isEmpty()) {
            this.nivelSLA = "Basico";
        } else {
            this.nivelSLA = nivelSLA;
        }
    }
    
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("IP Fija: " + (ipFija ? "Si" : "No"));
        System.out.println("Soporte: " + horasSoporte + " horas");
        System.out.println("Nivel SLA: " + nivelSLA);
    }
    
    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);
        
        if (meses >= 12) {
            precioTotal = precioTotal * 0.85;
        } else if (meses >= 6) {
            precioTotal = precioTotal * 0.92;
        }
        
        return precioTotal;
    }
    
    public String obtenerBeneficios() {
        return "Plan empresarial con SLA " + nivelSLA + " y soporte de " + horasSoporte + "h";
    }
}