public class Instalacion {
    private String codigoInstalacion;
    private Cliente cliente;
    private ServicePlan plan;
    private Tecnico tecnico;
    private String fechaInstalacion;
    private String estado;

    public Instalacion(String codigoInstalacion, Cliente cliente, ServicePlan plan, Tecnico tecnico, String fechaInstalacion) {
        this.codigoInstalacion = codigoInstalacion;
        this.cliente = cliente;
        this.plan = plan;
        this.tecnico = tecnico;
        this.fechaInstalacion = fechaInstalacion;
        this.estado = "Pendiente";
    }

    public String getCodigoInstalacion() {
        return codigoInstalacion;
    }

    public void setCodigoInstalacion(String codigoInstalacion) {
        this.codigoInstalacion = codigoInstalacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ServicePlan getPlan() {
        return plan;
    }

    public void setPlan(ServicePlan plan) {
        this.plan = plan;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public String getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(String fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void mostrarResumen() {
        System.out.println("=== INSTALACION " + codigoInstalacion + " ===");
        System.out.println("Cliente: " + cliente.getNombreCompleto());
        System.out.println("Plan contratado: " + plan.getPlanName());
        System.out.println("Tecnico asignado: " + tecnico.getNombre());
        System.out.println("Fecha: " + fechaInstalacion);
        System.out.println("Estado: " + estado);
    }

    public double calcularCostoTotal() {
        double costoInstalacion = 50000;
        double costoPlan = plan.getMonthlyPrice();
        return costoInstalacion + costoPlan;
    }

    public void completarInstalacion() {
        this.estado = "Completada";
        System.out.println("Instalacion " + codigoInstalacion + " completada exitosamente");
    }
}