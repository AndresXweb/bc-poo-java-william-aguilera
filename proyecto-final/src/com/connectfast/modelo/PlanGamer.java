package com.connectfast.modelo;

import com.connectfast.interfaces.Promocionable;
import com.connectfast.excepciones.PlanInvalidoException;
import com.connectfast.excepciones.VelocidadInsuficienteException;

/**
 * Entidad concreta para planes de alto rendimiento (Gamer).
 * Incluye reglas de negocio estrictas sobre latencia y velocidad mínima.
 * Implementa Promocionable para campañas específicas de eSports.
 * * @author William Andres Aguilera Lasprilla
 * @version 2.0 (Proyecto Final)
 */
public class PlanGamer extends ServicePlan implements Promocionable {
    
    // Identificador de versión para la serialización
    private static final long serialVersionUID = 1L;
    
    private int latenciaMaxima;
    private boolean anchoDedicado;
    private String servidoresGaming;

    /**
     * Constructor del Plan Gamer.
     * Aplica una regla de negocio estricta: La velocidad debe ser >= 200 Mbps.
     * @param planCode Código único
     * @param planName Nombre comercial
     * @param speedMbps Velocidad (Mínimo 200)
     * @param monthlyPrice Precio mensual
     * @param latenciaMaxima Latencia garantizada en ms (1-50)
     * @param anchoDedicado Si tiene canal exclusivo de fibra
     * @throws PlanInvalidoException Si los datos generales son incorrectos
     * @throws VelocidadInsuficienteException Si la velocidad es menor a 200 Mbps
     */
    public PlanGamer(String planCode, String planName, int speedMbps, double monthlyPrice,
                     int latenciaMaxima, boolean anchoDedicado) 
                     throws PlanInvalidoException, VelocidadInsuficienteException {
        
        super(planCode, planName, speedMbps, monthlyPrice, "Gamer");

        // Regla de Negocio Crítica: Validación específica de este dominio
        if (speedMbps < 200) {
            throw new VelocidadInsuficienteException(
                "La experiencia Gamer requiere mínimo 200 Mbps para garantizar estabilidad (Valor actual: " + speedMbps + ")"
            );
        }

        setLatenciaMaxima(latenciaMaxima);
        this.anchoDedicado = anchoDedicado;
        this.servidoresGaming = "Optimizados (Miami/Sao Paulo)";
        
        registrarCambio("Plan gamer configurado con latencia " + latenciaMaxima + "ms");
    }

    // --- MÉTODOS SOBRESCRITOS (Polimorfismo) ---

    @Override
    public String obtenerDescripcion() {
        return "Plan gaming " + planName + " con internet ultra rapido de " + speedMbps +
                " Mbps, latencia garantizada de " + latenciaMaxima + "ms y " +
                (anchoDedicado ? "ancho de banda dedicado" : "ancho compartido");
    }

    @Override
    public String obtenerBeneficios() {
        StringBuilder beneficios = new StringBuilder();
        beneficios.append("Beneficios del Plan Gamer:\n");
        beneficios.append("- Internet ultra rapido: ").append(speedMbps).append(" Mbps\n");
        beneficios.append("- Latencia ultra baja: ").append(latenciaMaxima).append(" ms\n");
        if (anchoDedicado) {
            beneficios.append("- Ancho de banda 100% dedicado (Sin reuso)\n");
        }
        beneficios.append("- Enrutamiento a ").append(servidoresGaming).append("\n");
        beneficios.append("- Sin throttling (estrangulamiento) de datos\n");
        beneficios.append("- Prioridad QoS en tráfico UDP\n");
        beneficios.append("- Router Gaming RGB incluido");
        return beneficios.toString();
    }

    @Override
    public double calcularCostoInstalacion() {
        double costoBase = 80000;
        // La instalación de fibra dedicada es más costosa
        if (anchoDedicado) {
            costoBase += 70000;
        }
        return costoBase;
    }

    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);

        if (meses >= 12) {
            precioTotal = precioTotal * 0.95; // 5% descuento anual
            registrarCambio("Descuento anual gamer aplicado (5%)");
        }

        return precioTotal;
    }

    // --- IMPLEMENTACIÓN INTERFAZ PROMOCIONABLE ---

    @Override
    public boolean esElegiblePromocion() {
        return true; // Los planes Gamer siempre son elegibles para campañas
    }

    @Override
    public double aplicarPromocion(String codigoPromo) {
        double precioFinal = monthlyPrice;

        switch (codigoPromo.toUpperCase()) {
            case "GAMER2025":
                precioFinal = monthlyPrice * 0.88; // 12% desc
                registrarCambio("Promocion GAMER2025 aplicada (12% descuento)");
                break;
            case "ESPORTS":
                precioFinal = monthlyPrice * 0.92; // 8% desc
                registrarCambio("Promocion ESPORTS aplicada (8% descuento)");
                break;
            default:
                System.out.println("Codigo de promocion invalido para Gamer");
        }

        return precioFinal;
    }

    @Override
    public String obtenerPromocionesActivas() {
        return "Promociones activas: GAMER2025 (12% desc), ESPORTS (8% desc)";
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Latencia maxima: " + latenciaMaxima + " ms");
        System.out.println("Ancho dedicado: " + (anchoDedicado ? "Si" : "No"));
        System.out.println("Servidores: " + servidoresGaming);
        System.out.println("Costo instalacion: $" + calcularCostoInstalacion());
    }

    // --- GETTERS Y SETTERS (Encapsulación) ---

    public int getLatenciaMaxima() {
        return latenciaMaxima;
    }

    public void setLatenciaMaxima(int latenciaMaxima) throws PlanInvalidoException {
        // Validación estricta para garantizar calidad
        if (latenciaMaxima < 1 || latenciaMaxima > 50) {
            throw new PlanInvalidoException("La latencia debe estar entre 1 y 50 ms para ser considerada Gamer");
        }
        this.latenciaMaxima = latenciaMaxima;
    }

    public boolean isAnchoDedicado() {
        return anchoDedicado;
    }

    public void setAnchoDedicado(boolean anchoDedicado) {
        this.anchoDedicado = anchoDedicado;
        registrarCambio("Ancho dedicado " + (anchoDedicado ? "activado" : "desactivado"));
    }

    public String getServidoresGaming() {
        return servidoresGaming;
    }
}