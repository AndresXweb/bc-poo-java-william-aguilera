public class Instalacion {
    private String codigoInstalacion;
    private Cliente cliente;
    private ServicePlan plan;
    private Tecnico tecnico;
    private String fechaInstalacion;
    private String estado;
    
    // Constructor completo
    public Instalacion(String codigoInstalacion, Cliente cliente, ServicePlan plan, Tecnico tecnico, String fechaInstalacion) {
        setCodigoInstalacion(codigoInstalacion);
        setCliente(cliente);
        setPlan(plan);
        setTecnico(tecnico);
        setFechaInstalacion(fechaInstalacion);
        this.estado = "Pendiente";
    }
    
    // Constructor sin fecha (usa fecha por defecto)
    public Instalacion(String codigoInstalacion, Cliente cliente, ServicePlan plan, Tecnico tecnico) {
        this(codigoInstalacion, cliente, plan, tecnico, "Por definir");
    }
    
    public String getCodigoInstalacion() {
        return codigoInstalacion;
    }
    
    public void setCodigoInstalacion(String codigoInstalacion) {
        if (codigoInstalacion == null || codigoInstalacion.isEmpty()) {
            throw new IllegalArgumentException("Codigo no puede estar vacio");
        }
        if (!codigoInstalacion.startsWith("INST-")) {
            throw new IllegalArgumentException("Codigo debe empezar con INST-");
        }
        this.codigoInstalacion = codigoInstalacion;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no puede ser nulo");
        }
        this.cliente = cliente;
    }
    
    public ServicePlan getPlan() {
        return plan;
    }
    
    public void setPlan(ServicePlan plan) {
        if (plan == null) {
            throw new IllegalArgumentException("Plan no puede ser nulo");
        }
        this.plan = plan;
    }
    
    public Tecnico getTecnico() {
        return tecnico;
    }
    
    public void setTecnico(Tecnico tecnico) {
        if (tecnico == null) {
            throw new IllegalArgumentException("Tecnico no puede ser nulo");
        }
        this.tecnico = tecnico;
    }
    
    public String getFechaInstalacion() {
        return fechaInstalacion;
    }
    
    public void setFechaInstalacion(String fechaInstalacion) {
        if (fechaInstalacion == null || fechaInstalacion.isEmpty()) {
            this.fechaInstalacion = "Por definir";
        } else {
            this.fechaInstalacion = fechaInstalacion;
        }
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        if (estado == null || estado.isEmpty()) {
            this.estado = "Pendiente";
        } else {
            this.estado = estado;
        }
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