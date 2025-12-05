public class Tecnico {
    private String codigoTecnico;
    private String nombre;
    private String especialidad;
    private String zonaAsignada;
    
    // Constructor completo
    public Tecnico(String codigoTecnico, String nombre, String especialidad, String zonaAsignada) {
        setCodigoTecnico(codigoTecnico);
        setNombre(nombre);
        setEspecialidad(especialidad);
        setZonaAsignada(zonaAsignada);
    }
    
    // Constructor sin zona
    public Tecnico(String codigoTecnico, String nombre, String especialidad) {
        this(codigoTecnico, nombre, especialidad, "Sin asignar");
    }
    
    // Constructor basico
    public Tecnico(String codigoTecnico, String nombre) {
        this(codigoTecnico, nombre, "General");
    }
    
    public String getCodigoTecnico() {
        return codigoTecnico;
    }
    
    public void setCodigoTecnico(String codigoTecnico) {
        if (codigoTecnico == null || codigoTecnico.isEmpty()) {
            throw new IllegalArgumentException("Codigo no puede estar vacio");
        }
        if (!codigoTecnico.startsWith("TEC-")) {
            throw new IllegalArgumentException("Codigo debe empezar con TEC-");
        }
        this.codigoTecnico = codigoTecnico;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacio");
        }
        if (nombre.length() < 3) {
            throw new IllegalArgumentException("Nombre debe tener minimo 3 caracteres");
        }
        this.nombre = nombre;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }
    
    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.isEmpty()) {
            this.especialidad = "General";
        } else {
            this.especialidad = especialidad;
        }
    }
    
    public String getZonaAsignada() {
        return zonaAsignada;
    }
    
    public void setZonaAsignada(String zonaAsignada) {
        if (zonaAsignada == null || zonaAsignada.isEmpty()) {
            this.zonaAsignada = "Sin asignar";
        } else {
            this.zonaAsignada = zonaAsignada;
        }
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