package com.connectfast;

import java.util.List;
import java.util.Map;

import com.connectfast.modelo.PlanResidencial;
import com.connectfast.modelo.PlanEmpresarial;
import com.connectfast.modelo.PlanGamer;
import com.connectfast.modelo.ServicePlan;
import com.connectfast.servicio.GestorPlanes;
import com.connectfast.excepciones.PlanInvalidoException;
import com.connectfast.excepciones.PlanNoEncontradoException;
import com.connectfast.excepciones.VelocidadInsuficienteException;
import com.connectfast.util.Consola;

/**
 * Clase principal del sistema ConnectFast.
 * Implementa un menú interactivo para gestionar el ciclo de vida de los planes.
 * Integra todas las funcionalidades: CRUD, Persistencia, Ordenamiento y Estadísticas.
 * * @author William Andres Aguilera Lasprilla
 * @version 2.0 (Proyecto Final)
 */
public class Main {

    private static GestorPlanes gestor = new GestorPlanes();

    public static void main(String[] args) {
        
        // Carga datos de prueba SOLO si el sistema está vacío (para no duplicar al persistir)
        if (gestor.getCantidadPlanes() == 0) {
            cargarDatosIniciales();
        } else {
            Consola.mostrar("Sistema iniciado con datos existentes (" + gestor.getCantidadPlanes() + " planes).");
        }

        int opcion = 0;

        do {
            mostrarMenu();
            opcion = Consola.leerEntero("Ingrese una opción: ");

            try {
                switch (opcion) {
                    case 1: agregarPlan(); break;
                    case 2: buscarPlan(); break;
                    case 3: actualizarPlan(); break; // NUEVA FUNCIONALIDAD
                    case 4: eliminarPlan(); break;
                    case 5: gestor.mostrarCatalogo(); break;
                    case 6: listarOrdenadoPorPrecio(); break; // NUEVA FUNCIONALIDAD
                    case 7: filtrarPlanes(); break;
                    case 8: mostrarEstadisticas(); break;
                    case 0: 
                        Consola.mostrar("\nGuardando datos y saliendo...");
                        // El gestor guarda automáticamente en cada cambio, pero esto confirma la salida.
                        Consola.mostrar("👋 Gracias por usar ConnectFast. ¡Hasta pronto!"); 
                        break;
                    default: Consola.mostrar("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                Consola.mostrar("Ocurrió un error inesperado: " + e.getMessage());
            }

            if (opcion != 0) Consola.esperarEnter();

        } while (opcion != 0);
    }

    /**
     * Carga datos semilla solo para la primera ejecución.
     */
    private static void cargarDatosIniciales() {
        Consola.mostrar("Iniciando configuración inicial del sistema...");
        try {
            gestor.agregarPlan(new PlanResidencial("PLAN-100", "Básico Hogar", 100, 29900, 80, true));
            gestor.agregarPlan(new PlanGamer("PLAN-G500", "Gamer Pro", 500, 59900, 5, true));
            gestor.agregarPlan(new PlanEmpresarial("PLAN-E200", "Emprendedor", 200, 49900, true, 24, "Premium"));
            gestor.agregarPlan(new PlanResidencial("PLAN-300", "Premium Plus", 300, 39900, 150, true));
        } catch (Exception e) {
            Consola.mostrar("Error al cargar datos iniciales: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        Consola.mostrar("\n========= SISTEMA DE GESTIÓN CONNECTFAST (V2.0) =========");
        Consola.mostrar("1. Agregar Nuevo Plan (Create)");
        Consola.mostrar("2. Buscar Plan por Código (Read - O(1))");
        Consola.mostrar("3. Actualizar Precio/Velocidad (Update)");
        Consola.mostrar("4. Eliminar Plan (Delete)");
        Consola.mostrar("5. Listar Todos los Planes");
        Consola.mostrar("6. Listar Ordenados por Precio (Sort)");
        Consola.mostrar("7. Filtrar por Tipo");
        Consola.mostrar("8. Ver Estadísticas y Reportes");
        Consola.mostrar("0. Salir");
        Consola.mostrar("========================================================");
    }

    /**
     * Opción 1: Crea un plan solicitando el tipo específico.
     * Demuestra Polimorfismo y manejo de Excepciones Específicas.
     */
    private static void agregarPlan() {
        Consola.mostrar("\n--- AGREGAR NUEVO PLAN ---");
        Consola.mostrar("Seleccione el tipo de plan:");
        Consola.mostrar("1. Residencial");
        Consola.mostrar("2. Gamer");
        Consola.mostrar("3. Empresarial");
        
        int tipo = Consola.leerEntero("Opción: ");
        
        try {
            // Datos comunes
            String codigo = Consola.leerCadena("Código (ej. PLAN-50): ");
            String nombre = Consola.leerCadena("Nombre del Plan: ");
            int velocidad = Consola.leerEntero("Velocidad (Mbps): ");
            double precio = Consola.leerDecimal("Precio Mensual ($): ");

            ServicePlan nuevoPlan = null;

            switch (tipo) {
                case 1: // Residencial
                    int canales = Consola.leerEntero("Canales de TV: ");
                    boolean telefonia = Consola.leerEntero("¿Incluye Telefonía? (1=Sí, 0=No): ") == 1;
                    nuevoPlan = new PlanResidencial(codigo, nombre, velocidad, precio, canales, telefonia);
                    break;
                case 2: // Gamer
                    int latencia = Consola.leerEntero("Latencia Máxima (ms): ");
                    boolean dedicado = Consola.leerEntero("¿Ancho Dedicado? (1=Sí, 0=No): ") == 1;
                    nuevoPlan = new PlanGamer(codigo, nombre, velocidad, precio, latencia, dedicado);
                    break;
                case 3: // Empresarial
                    boolean ipFija = Consola.leerEntero("¿IP Fija? (1=Sí, 0=No): ") == 1;
                    int soporte = Consola.leerEntero("Horas de Soporte: ");
                    String sla = Consola.leerCadena("Nivel SLA (Basico/Premium/Enterprise): ");
                    nuevoPlan = new PlanEmpresarial(codigo, nombre, velocidad, precio, ipFija, soporte, sla);
                    break;
                default:
                    Consola.mostrar("Tipo de plan no válido.");
                    return;
            }

            if (nuevoPlan != null) {
                gestor.agregarPlan(nuevoPlan);
            }

        } catch (PlanInvalidoException | VelocidadInsuficienteException e) {
            Consola.mostrar("ERROR DE VALIDACIÓN: " + e.getMessage());
        } catch (Exception e) {
            Consola.mostrar("ERROR INESPERADO: " + e.getMessage());
        }
    }

    private static void buscarPlan() {
        Consola.mostrar("\n--- BUSCAR PLAN POR CÓDIGO ---");
        String codigo = Consola.leerCadena("Ingrese el código a buscar: ");
        try {
            ServicePlan plan = gestor.buscarPlan(codigo);
            Consola.mostrar("\nDETALLES DEL PLAN:");
            plan.mostrarInformacion();
            Consola.mostrar("Beneficios: " + plan.obtenerBeneficios());
        } catch (PlanNoEncontradoException e) {
            Consola.mostrar("Error" + e.getMessage());
        }
    }

    /**
     * Opción 3: Actualiza datos de un plan existente.
     * Cumple con el requisito de CRUD (Update).
     */
    private static void actualizarPlan() {
        Consola.mostrar("\n--- ACTUALIZAR PLAN ---");
        String codigo = Consola.leerCadena("Ingrese el código del plan a modificar: ");
        
        try {
            ServicePlan plan = gestor.buscarPlan(codigo);
            Consola.mostrar("Plan actual: " + plan.getPlanName() + " - $" + plan.getMonthlyPrice());
            
            Consola.mostrar("¿Qué desea modificar?");
            Consola.mostrar("1. Precio Mensual");
            Consola.mostrar("2. Velocidad");
            Consola.mostrar("0. Cancelar");
            int op = Consola.leerEntero("Opción: ");
            
            if (op == 1) {
                double nuevoPrecio = Consola.leerDecimal("Nuevo Precio: ");
                plan.setMonthlyPrice(nuevoPrecio);
                gestor.guardarCambios(); // Importante para persistencia
            } else if (op == 2) {
                int nuevaVel = Consola.leerEntero("Nueva Velocidad: ");
                plan.setSpeedMbps(nuevaVel);
                gestor.guardarCambios();
            } else {
                Consola.mostrar("Operación cancelada.");
            }
            
        } catch (PlanNoEncontradoException | PlanInvalidoException e) {
            Consola.mostrar("ERROR: " + e.getMessage());
        }
    }

    private static void eliminarPlan() {
        Consola.mostrar("\n--- ELIMINAR PLAN ---");
        String codigo = Consola.leerCadena("Ingrese el código del plan a eliminar: ");
        try {
            // Confirmación simple
            int confirm = Consola.leerEntero("¿Seguro que desea eliminar " + codigo + "? (1=Sí, 0=No): ");
            if (confirm == 1) {
                ServicePlan eliminado = gestor.eliminarPlan(codigo);
                Consola.mostrar("Plan eliminado: " + eliminado.getPlanName());
            } else {
                Consola.mostrar("Operación cancelada.");
            }
        } catch (PlanNoEncontradoException e) {
            Consola.mostrar("ERROR: " + e.getMessage());
        }
    }

    /**
     * Opción 6: Lista los planes usando el método compareTo (Comparable).
     */
    private static void listarOrdenadoPorPrecio() {
        Consola.mostrar("\n--- CATÁLOGO ORDENADO POR PRECIO (Menor a Mayor) ---");
        List<ServicePlan> ordenados = gestor.obtenerPlanesOrdenadosPorPrecio();
        
        if (ordenados.isEmpty()) {
            Consola.mostrar("El catálogo está vacío.");
        } else {
            for (ServicePlan p : ordenados) {
                System.out.println(String.format("[$%.2f] %s (%s)", p.getMonthlyPrice(), p.getPlanName(), p.getPlanCode()));
            }
        }
    }

    private static void filtrarPlanes() {
        Consola.mostrar("\n--- FILTRAR POR TIPO ---");
        String tipo = Consola.leerCadena("Ingrese tipo (Residencial/Gamer/Empresarial): ");
        List<ServicePlan> filtrados = gestor.filtrarPorTipo(tipo);
        
        if (filtrados.isEmpty()) {
            Consola.mostrar("No se encontraron planes.");
        } else {
            Consola.mostrar("Resultados encontrados: " + filtrados.size());
            for (ServicePlan p : filtrados) {
                System.out.println(" - " + p.toString());
            }
        }
    }

    private static void mostrarEstadisticas() {
        Consola.mostrar("\n--- ESTADÍSTICAS Y REPORTES ---");
        
        double total = gestor.calcularIngresoMensualTotal();
        double promedio = gestor.calcularPromedioMensual();
        
        Consola.mostrar(String.format("Ingresos Mensuales Proyectados: $%.2f", total));
        Consola.mostrar(String.format("Precio Promedio del Catálogo:   $%.2f", promedio));
        
        ServicePlan masRapido = gestor.obtenerPlanMasRapido();
        if (masRapido != null) {
            Consola.mostrar("Plan Más Rápido: " + masRapido.getPlanName() + " (" + masRapido.getSpeedMbps() + " Mbps)");
        }
        
        Consola.mostrar("\nDistribución del Catálogo:");
        Map<String, Integer> conteo = gestor.contarPlanesPorTipo();
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            System.out.println("   * " + entry.getKey() + ": " + entry.getValue() + " planes");
        }
    }
}