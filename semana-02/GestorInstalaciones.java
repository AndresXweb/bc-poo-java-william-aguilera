import java.util.ArrayList;

public class GestorInstalaciones {
    private String nombreEmpresa;
    private ArrayList<Instalacion> instalaciones;

    public GestorInstalaciones(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.instalaciones = new ArrayList<>();
    }

    public void agregarInstalacion(Instalacion instalacion) {
        instalaciones.add(instalacion);
        System.out.println("Instalacion agregada: " + instalacion.getCodigoInstalacion());
    }

    public void mostrarTodasInstalaciones() {
        System.out.println("\n========================================");
        System.out.println("  INSTALACIONES DE " + nombreEmpresa.toUpperCase());
        System.out.println("========================================\n");

        if (instalaciones.isEmpty()) {
            System.out.println("No hay instalaciones registradas");
        } else {
            for (Instalacion instalacion : instalaciones) {
                instalacion.mostrarResumen();
                System.out.println();
            }
        }
    }

    public int contarInstalaciones() {
        return instalaciones.size();
    }

    public void mostrarInstalacionesPendientes() {
        System.out.println("\n--- Instalaciones Pendientes ---");
        int contador = 0;
        for (Instalacion inst : instalaciones) {
            if (inst.getEstado().equals("Pendiente")) {
                System.out.println("- " + inst.getCodigoInstalacion() +
                        " | Cliente: " + inst.getCliente().getNombreCompleto());
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay instalaciones pendientes");
        }
    }

    public Instalacion buscarPorCodigo(String codigo) {
        for (Instalacion inst : instalaciones) {
            if (inst.getCodigoInstalacion().equals(codigo)) {
                return inst;
            }
        }
        return null;
    }

    public double calcularIngresoTotal() {
        double total = 0;
        for (Instalacion inst : instalaciones) {
            total += inst.calcularCostoTotal();
        }
        return total;
    }
}