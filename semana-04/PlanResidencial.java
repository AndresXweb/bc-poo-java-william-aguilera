public class PlanResidencial extends ServicePlan {
    private int canalesTV;
    private boolean incluyeTelefonia;
    private String tipoContenido;
    
    public PlanResidencial(String planCode, String planName, int speedMbps, double monthlyPrice, 
                          int canalesTV, boolean incluyeTelefonia) {
        super(planCode, planName, speedMbps, monthlyPrice, "Residencial");
        setCanalesTV(canalesTV);
        this.incluyeTelefonia = incluyeTelefonia;
        this.tipoContenido = "Familiar";
    }
    
    public int getCanalesTV() {
        return canalesTV;
    }
    
    public void setCanalesTV(int canalesTV) {
        if (canalesTV < 0) {
            throw new IllegalArgumentException("Canales no puede ser negativo");
        }
        this.canalesTV = canalesTV;
    }
    
    public boolean isIncluyeTelefonia() {
        return incluyeTelefonia;
    }
    
    public void setIncluyeTelefonia(boolean incluyeTelefonia) {
        this.incluyeTelefonia = incluyeTelefonia;
    }
    
    public String getTipoContenido() {
        return tipoContenido;
    }
    
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Canales TV: " + canalesTV);
        System.out.println("Telefonia incluida: " + (incluyeTelefonia ? "Si" : "No"));
        System.out.println("Contenido: " + tipoContenido);
    }
    
    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);
        
        if (meses >= 12) {
            precioTotal = precioTotal * 0.90;
            System.out.println("Descuento aplicado: 10% por contrato anual");
        }
        
        return precioTotal;
    }
    
    public String obtenerBeneficios() {
        return "Plan residencial con " + canalesTV + " canales, ideal para familias";
    }
}