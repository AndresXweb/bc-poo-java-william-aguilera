package com.connectfast;

import java.util.List;
import java.util.Map;
import com.connectfast.modelo.PlanResidencial;
import com.connectfast.modelo.PlanEmpresarial;
import com.connectfast.modelo.PlanGamer;

import com.connectfast.excepciones.PlanInvalidoException;
import com.connectfast.excepciones.PlanNoEncontradoException;
import com.connectfast.excepciones.VelocidadInsuficienteException;
import com.connectfast.modelo.ServicePlan;
import com.connectfast.servicio.GestorPlanes;
import com.connectfast.util.Consola;

public class Main {

    private static GestorPlanes gestor = new GestorPlanes();

    public static void main(String[] args) {

        cargarDatosIniciales();

        int opcion = 0;

        do {
            mostrarMenu();
            opcion = Consola.leerEntero("Ingrese una opción: ");

            try {
                switch (opcion) {
                    case 1: agregarPlan(); break;
                    case 2: buscarPlan(); break;
                    case 3: eliminarPlan(); break;
                    case 4: gestor.mostrarCatalogo(); break;
                    case 5: filtrarPlanes(); break;
                    case 6: mostrarEstadisticas(); break;
                    case 0: Consola.mostrar("\nGracias por usar ConnectFast."); break;
                    default: Consola.mostrar("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                Consola.mostrar("Ocurrió un error inesperado en la operación: " + e.getMessage());
            }

            Consola.esperarEnter();

        } while (opcion != 0);
    }

    private static void cargarDatosIniciales() {
        Consola.mostrar("Iniciando Gestor de Planes ConnectFast...");
        try {
            PlanResidencial p1 = new PlanResidencial("PLAN-100", "Básico Hogar", 100, 29900, 80, true);
            gestor.agregarPlan(p1);

            PlanGamer p2 = new PlanGamer("PLAN-G500", "Gamer Pro", 500, 59900, 5, true);
            gestor.agregarPlan(p2);

            PlanEmpresarial p3 = new PlanEmpresarial("PLAN-E200", "Emprendedor", 200, 49900, true, 24, "Premium");
            gestor.agregarPlan(p3);

            PlanResidencial p4 = new PlanResidencial("PLAN-300", "Premium Plus", 300, 39900, 150, true);
            gestor.agregarPlan(p4);

        } catch (PlanInvalidoException | VelocidadInsuficienteException e) {
            Consola.mostrar("Error al cargar datos iniciales: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        Consola.mostrar("\n========= GESTOR DE PLANES CONNECTFAST (S08) =========");
        Consola.mostrar("1. Agregar Plan");
        Consola.mostrar("2. Buscar Plan por Código (Búsqueda O(1))");
        Consola.mostrar("3. Eliminar Plan");
        Consola.mostrar("4. Mostrar Catálogo Completo");
        Consola.mostrar("5. FILTRAR Planes por Tipo (Residencial, Empresarial, Gamer)");
        Consola.mostrar("6. MOSTRAR Estadísticas Avanzadas");
        Consola.mostrar("0. Salir");
        Consola.mostrar("=====================================================");
    }

    private static void agregarPlan() {
        Consola.mostrar("\n--- AGREGAR NUEVO PLAN ---");
        try {
            String codigo = Consola.leerCadena("Código (ej. PLAN-50): ");
            String nombre = Consola.leerCadena("Nombre del Plan: ");

            double precio = Consola.leerDecimal("Precio Mensual ($): ");
            int velocidad = Consola.leerEntero("Velocidad (Mbps): ");

            PlanResidencial nuevoPlan = new PlanResidencial(codigo, nombre, velocidad, precio, 50, false);
            gestor.agregarPlan(nuevoPlan);

        } catch (PlanInvalidoException e) {
            Consola.mostrar("ERROR AL AGREGAR: " + e.getMessage());
        }
    }

    private static void buscarPlan() {
        Consola.mostrar("\n--- BUSCAR PLAN POR CÓDIGO ---");
        String codigo = Consola.leerCadena("Ingrese el código del plan a buscar (ej. PLAN-G500): ");
        try {
            ServicePlan planEncontrado = gestor.buscarPlan(codigo);
            Consola.mostrar("\nPLAN ENCONTRADO:");
            planEncontrado.mostrarInformacion();
        } catch (PlanNoEncontradoException e) {
            Consola.mostrar("ERROR DE BÚSQUEDA: " + e.getMessage());
        }
    }

    private static void eliminarPlan() {
        Consola.mostrar("\n--- ELIMINAR PLAN POR CÓDIGO ---");
        String codigo = Consola.leerCadena("Ingrese el código del plan a eliminar: ");
        try {
            ServicePlan planEliminado = gestor.eliminarPlan(codigo);
            Consola.mostrar("PLAN ELIMINADO: " + planEliminado.getPlanName() + " (" + planEliminado.getPlanCode() + ")");
        } catch (PlanNoEncontradoException e) {
            Consola.mostrar("ERROR AL ELIMINAR: " + e.getMessage());
        }
    }

    private static void filtrarPlanes() {
        Consola.mostrar("\n--- FILTRAR PLANES ---");
        String tipo = Consola.leerCadena("Ingrese el tipo de plan a filtrar (ej. Gamer): ");

        List<ServicePlan> planesFiltrados = gestor.filtrarPorTipo(tipo);

        Consola.mostrar("\nResultados de filtro por '" + tipo + "' (" + planesFiltrados.size() + " planes):");
        if (planesFiltrados.isEmpty()) {
            Consola.mostrar("No se encontraron planes de ese tipo.");
        } else {
            for (ServicePlan plan : planesFiltrados) {
                Consola.mostrar("- [" + plan.getPlanCode() + "] " + plan.getPlanName() + " ($" + plan.getMonthlyPrice() + ")");
            }
        }
    }

    private static void mostrarEstadisticas() {
        Consola.mostrar("\n--- ESTADÍSTICAS DEL CATÁLOGO ---");

        double ingresoTotal = gestor.calcularIngresoMensualTotal();
        double promedio = gestor.calcularPromedioMensual();
        Consola.mostrar("Ingreso Mensual Total: $" + String.format("%.2f", ingresoTotal));
        Consola.mostrar("Precio Promedio Mensual: $" + String.format("%.2f", promedio));

        ServicePlan planRapido = gestor.obtenerPlanMasRapido();
        if (planRapido != null) {
            Consola.mostrar("Plan Más Rápido: " + planRapido.getPlanName() + " (" + planRapido.getSpeedMbps() + " Mbps)");
        } else {
            Consola.mostrar("No hay planes para calcular el más rápido.");
        }

        Map<String, Integer> conteo = gestor.contarPlanesPorTipo();
        Consola.mostrar("\nConteo de Planes por Tipo:");
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            Consola.mostrar("- " + entry.getKey() + ": " + entry.getValue() + " planes");
        }
    }
}