package com.connectfast.servicio;

import java.io.*;
import java.util.*;
import com.connectfast.modelo.ServicePlan;
import com.connectfast.excepciones.PlanNoEncontradoException;
import com.connectfast.excepciones.PlanInvalidoException;

/**
 * Clase de servicio que gestiona la lógica de negocio de los planes.
 * Implementa persistencia de datos mediante serialización binaria.
 * Maneja colecciones eficientes (HashMap y ArrayList) para operaciones CRUD.
 * * @author William Andres Aguilera Lasprilla
 * @version 2.0 (Proyecto Final)
 */
public class GestorPlanes {
    
    // Archivo donde se guardarán los datos
    private static final String ARCHIVO_DATOS = "planes.dat";
    
    // Colecciones principales
    private Map<String, ServicePlan> planesPorCodigo; 
    private List<ServicePlan> historialPlanes; 

    /**
     * Constructor del Gestor.
     * Inicializa las colecciones e intenta cargar datos previos del archivo.
     */
    public GestorPlanes() {
        this.planesPorCodigo = new HashMap<>();
        this.historialPlanes = new ArrayList<>();
        
        // Intentamos cargar los datos guardados al iniciar
        cargarDatos();
    }

    // --- CRUD: CREAR ---

    /**
     * Registra un nuevo plan en el sistema.
     * Guarda automáticamente los cambios en el archivo.
     * * @param plan El objeto ServicePlan a agregar
     * @throws PlanInvalidoException Si el plan es nulo o el código ya existe
     */
    public void agregarPlan(ServicePlan plan) throws PlanInvalidoException {
        if (plan == null) {
            throw new PlanInvalidoException("El plan a agregar no puede ser nulo.");
        }
        
        String codigo = plan.getPlanCode();
        
        if (planesPorCodigo.containsKey(codigo)) {
            throw new PlanInvalidoException("Ya existe un plan registrado con el código: " + codigo);
        }
        
        planesPorCodigo.put(codigo, plan);
        historialPlanes.add(plan);
        guardarDatos(); // Persistencia automática
        System.out.println("✅ PLAN REGISTRADO: " + plan.getPlanName() + " (Código: " + codigo + ")");
    }

    // --- CRUD: LEER (Búsqueda O(1)) ---

    /**
     * Busca un plan por su código único.
     * * @param codigo Código del plan (ej. PLAN-100)
     * @return El objeto ServicePlan encontrado
     * @throws PlanNoEncontradoException Si no existe un plan con ese código
     */
    public ServicePlan buscarPlan(String codigo) throws PlanNoEncontradoException {
        ServicePlan plan = planesPorCodigo.get(codigo);
        
        if (plan == null) {
            throw new PlanNoEncontradoException("No se encontró ningún plan con el código: " + codigo);
        }
        return plan;
    }

    // --- CRUD: ACTUALIZAR (Simulado) ---
    
    /**
     * Guarda el estado actual de los datos. Útil después de modificar un plan existente.
     */
    public void guardarCambios() {
        guardarDatos();
        System.out.println("💾 Cambios guardados correctamente.");
    }

    // --- CRUD: ELIMINAR ---

    /**
     * Elimina un plan del sistema por su código.
     * * @param codigo Código del plan a eliminar
     * @return El plan eliminado
     * @throws PlanNoEncontradoException Si el código no existe
     */
    public ServicePlan eliminarPlan(String codigo) throws PlanNoEncontradoException {
        ServicePlan planEliminado = planesPorCodigo.remove(codigo); 
        
        if (planEliminado == null) {
             throw new PlanNoEncontradoException("No se puede eliminar: el plan con código " + codigo + " no existe.");
        }
        
        historialPlanes.remove(planEliminado);
        guardarDatos(); // Persistencia automática
        
        return planEliminado;
    }

    // --- OPERACIONES AVANZADAS (Filtros y Ordenamiento) ---

    public List<ServicePlan> filtrarPorTipo(String tipo) {
        List<ServicePlan> resultado = new ArrayList<>();
        String tipoBuscado = tipo.toLowerCase();
        
        for (ServicePlan plan : historialPlanes) { 
            if (plan.getTipoPlan().toLowerCase().contains(tipoBuscado)) {
                resultado.add(plan);
            }
        }
        return resultado;
    }
    
    /**
     * Retorna la lista de planes ordenada por precio (de menor a mayor).
     * Utiliza la interfaz Comparable implementada en ServicePlan.
     */
    public List<ServicePlan> obtenerPlanesOrdenadosPorPrecio() {
        List<ServicePlan> listaOrdenada = new ArrayList<>(historialPlanes); // Copia para no alterar el original
        Collections.sort(listaOrdenada); // Usa compareTo de ServicePlan
        return listaOrdenada;
    }

    // --- ESTADÍSTICAS ---

    public double calcularIngresoMensualTotal() {
        double total = 0;
        for (ServicePlan plan : historialPlanes) {
            total += plan.getMonthlyPrice();
        }
        return total;
    }
    
    public double calcularPromedioMensual() {
        if (historialPlanes.isEmpty()) return 0;
        return calcularIngresoMensualTotal() / historialPlanes.size();
    }

    public ServicePlan obtenerPlanMasRapido() {
        if (historialPlanes.isEmpty()) return null;

        ServicePlan masRapido = historialPlanes.get(0);
        for (ServicePlan plan : historialPlanes) {
            if (plan.getSpeedMbps() > masRapido.getSpeedMbps()) {
                masRapido = plan;
            }
        }
        return masRapido;
    }
    
    public Map<String, Integer> contarPlanesPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        for (ServicePlan plan : historialPlanes) {
            String tipo = plan.getTipoPlan();
            conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
        }
        return conteo;
    }
    
    // --- PERSISTENCIA DE DATOS (Serialización) ---
    
    /**
     * Guarda la lista completa de planes en un archivo binario.
     */
    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(historialPlanes);
        } catch (IOException e) {
            System.err.println("⚠️ Error al guardar los datos: " + e.getMessage());
        }
    }
    
    /**
     * Carga los planes desde el archivo binario al iniciar el programa.
     * Reconstruye tanto el ArrayList como el HashMap.
     */
    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);
        if (!archivo.exists()) return; // Si no existe, iniciamos vacíos

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
            // Leemos el ArrayList del archivo
            historialPlanes = (List<ServicePlan>) ois.readObject();
            
            // Reconstruimos el HashMap para mantener la búsqueda O(1)
            planesPorCodigo.clear();
            for (ServicePlan plan : historialPlanes) {
                planesPorCodigo.put(plan.getPlanCode(), plan);
            }
            System.out.println("📂 Datos cargados exitosamente: " + historialPlanes.size() + " planes.");
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("⚠️ Error al cargar datos (iniciando catálogo vacío): " + e.getMessage());
            this.historialPlanes = new ArrayList<>();
            this.planesPorCodigo = new HashMap<>();
        }
    }
    
    // --- UTILIDADES ---
    
    public List<ServicePlan> obtenerTodosLosPlanes() {
        return historialPlanes;
    }
    
    public int getCantidadPlanes() {
        return historialPlanes.size();
    }
    
    public void mostrarCatalogo() {
        System.out.println("\n--- CATALOGO DISPONIBLE (" + getCantidadPlanes() + " planes) ---");
        for (ServicePlan plan : historialPlanes) {
            System.out.println(plan.toString());
        }
        System.out.println("-------------------------------------------");
    }
}