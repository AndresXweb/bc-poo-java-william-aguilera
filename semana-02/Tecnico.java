public class Tecnico {
    private String codigoTecnico;
    private String nombre;
    private String especialidad;
    private String zonaAsignada;

    public Tecnico(String codigoTecnico, String nombre, String especialidad, String zonaAsignada) {
        this.codigoTecnico = codigoTecnico;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.zonaAsignada = zonaAsignada;
    }

    public String getCodigoTecnico() {
        return codigoTecnico;
    }

    public void setCodigoTecnico(String codigoTecnico) {
        this.codigoTecnico = codigoTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(String zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

    public void mostrarInformacion() {
        System.out.println("Tecnico: " + nombre);
        System.out.println("Codigo: " + codigoTecnico);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Zona: " + zonaAsignada);
    }

    public void asignarInstalacion(String codigoInstalacion) {
        System.out.println("Tecnico " + nombre + " asignado a instalacion " + codigoInstalacion);
    }
}