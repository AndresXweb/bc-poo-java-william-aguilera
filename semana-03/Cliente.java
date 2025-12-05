public class Cliente {
    private String codigoCliente;
    private String nombreCompleto;
    private String direccion;
    private String telefono;
    
    // Constructor completo
    public Cliente(String codigoCliente, String nombreCompleto, String direccion, String telefono) {
        setCodigoCliente(codigoCliente);
        setNombreCompleto(nombreCompleto);
        setDireccion(direccion);
        setTelefono(telefono);
    }
    
    // Constructor sin telefono
    public Cliente(String codigoCliente, String nombreCompleto, String direccion) {
        this(codigoCliente, nombreCompleto, direccion, "Sin telefono");
    }
    
    // Constructor basico
    public Cliente(String codigoCliente, String nombreCompleto) {
        this(codigoCliente, nombreCompleto, "Sin direccion");
    }
    
    public String getCodigoCliente() {
        return codigoCliente;
    }
    
    public void setCodigoCliente(String codigoCliente) {
        if (codigoCliente == null || codigoCliente.isEmpty()) {
            throw new IllegalArgumentException("Codigo no puede estar vacio");
        }
        if (!codigoCliente.startsWith("CLI-")) {
            throw new IllegalArgumentException("Codigo debe empezar con CLI-");
        }
        this.codigoCliente = codigoCliente;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacio");
        }
        if (nombreCompleto.length() < 3) {
            throw new IllegalArgumentException("Nombre debe tener minimo 3 caracteres");
        }
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isEmpty()) {
            this.direccion = "Sin direccion";
        } else {
            this.direccion = direccion;
        }
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) {
            this.telefono = "Sin telefono";
        } else if (telefono.length() < 7) {
            throw new IllegalArgumentException("Telefono debe tener minimo 7 caracteres");
        } else {
            this.telefono = telefono;
        }
    }
    
    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombreCompleto);
        System.out.println("Codigo: " + codigoCliente);
        System.out.println("Direccion: " + direccion);
        System.out.println("Telefono: " + telefono);
    }
}