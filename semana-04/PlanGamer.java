public class PlanGamer extends ServicePlan {
    private int latenciaMaxima;
    private boolean anchoDedicado;
    private String servidoresGaming;
    
    public PlanGamer(String planCode, String planName, int speedMbps, double monthlyPrice,
                    int latenciaMaxima, boolean anchoDedicado) {
        super(planCode, planName, speedMbps, monthlyPrice, "Gamer");
        setLatenciaMaxima(latenciaMaxima);
        this.anchoDedicado = anchoDedicado;
        this.servidoresGaming = "Optimizados";
    }
    
    public int getLatenciaMaxima() {
        return latenciaMaxima;
    }
    
    public void setLatenciaMaxima(int latenciaMaxima) {
        if (latenciaMaxima < 1 || latenciaMaxima > 50) {
            throw new IllegalArgumentException("Latencia debe estar entre 1 y 50 ms");
        }
        this.latenciaMaxima = latenciaMaxima;
    }
    
    public boolean isAnchoDedicado() {
        return anchoDedicado;
    }
    
    public void setAnchoDedicado(boolean anchoDedicado) {
        this.anchoDedicado = anchoDedicado;
    }
    
    public String getServidoresGaming() {
        return servidoresGaming;
    }
    
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Latencia maxima: " + latenciaMaxima + " ms");
        System.out.println("Ancho dedicado: " + (anchoDedicado ? "Si" : "No"));
        System.out.println("Servidores: " + servidoresGaming);
    }
    
    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);
        
        if (meses >= 12) {
            precioTotal = precioTotal * 0.95;
            System.out.println("Descuento aplicado: 5% por contrato anual (plan premium)");
        }
        
        return precioTotal;
    }
    
    public String obtenerBeneficios() {
        return "Plan gaming con latencia de " + latenciaMaxima + "ms y servidores optimizados";
    }
}