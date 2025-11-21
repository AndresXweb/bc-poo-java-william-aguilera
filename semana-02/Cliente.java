public class Cliente {
    private String codigoCliente;
    private String nombreCompleto;
    private String direccion;
    private String telefono;

    public Cliente(String codigoCliente, String nombreCompleto, String direccion, String telefono) {
        this.codigoCliente = codigoCliente;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombreCompleto);
        System.out.println("Codigo: " + codigoCliente);
        System.out.println("Direccion: " + direccion);
        System.out.println("Telefono: " + telefono);
    }
}