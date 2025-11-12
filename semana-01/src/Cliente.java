public class Cliente {
    String codigoCliente;
    String nombreCompleto;
    String direccion;
    String planContratado;
    
    public Cliente(String codigoCliente, String nombreCompleto, String direccion, String planContratado) {
        this.codigoCliente = codigoCliente;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.planContratado = planContratado;
    }
    
    public void mostrarInformacion() {
        System.out.println(" INFORMACION DEL CLIENTE ");
        System.out.println("Codigo: " + codigoCliente);
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Direccion: " + direccion);
        System.out.println("Plan contratado: " + planContratado);
        System.out.println();
    }
}