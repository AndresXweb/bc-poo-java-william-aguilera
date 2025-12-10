package com.connectfast.modelo;

import com.connectfast.interfaces.Promocionable;

import com.connectfast.excepciones.PlanInvalidoException;

import com.connectfast.excepciones.VelocidadInsuficienteException;

public class PlanGamer extends ServicePlan implements Promocionable {
    private int latenciaMaxima;
    private boolean anchoDedicado;
    private String servidoresGaming;

    public PlanGamer(String planCode, String planName, int speedMbps, double monthlyPrice,
                     int latenciaMaxima, boolean anchoDedicado) 
                     throws PlanInvalidoException, VelocidadInsuficienteException {
        
        super(planCode, planName, speedMbps, monthlyPrice, "Gamer");

        if (speedMbps < 200) {
            throw new VelocidadInsuficienteException(
                "La experiencia Gamer requiere minimo 200 Mbps para garantizar estabilidad."
            );
        }

        setLatenciaMaxima(latenciaMaxima);
        this.anchoDedicado = anchoDedicado;
        this.servidoresGaming = "Optimizados";
        
        registrarCambio("Plan gamer configurado con latencia " + latenciaMaxima + "ms");
    }

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
            beneficios.append("- Ancho de banda 100% dedicado\n");
        }
        beneficios.append("- Servidores gaming ").append(servidoresGaming).append("\n");
        beneficios.append("- Sin throttling en gaming\n");
        beneficios.append("- Prioridad en trafico de juegos\n");
        beneficios.append("- Router gaming incluido");
        return beneficios.toString();
    }

    @Override
    public double calcularCostoInstalacion() {
        double costoBase = 80000;

        if (anchoDedicado) {
            costoBase += 70000;
        }

        return costoBase;
    }

    @Override
    public double calcularPrecioConDescuento(int meses) {
        double precioTotal = super.calcularPrecioConDescuento(meses);

        if (meses >= 12) {
            precioTotal = precioTotal * 0.95;
            registrarCambio("Descuento anual gamer aplicado (5%)");
        }

        return precioTotal;
    }

    @Override
    public boolean esElegiblePromocion() {
        return true; 
    }

    @Override
    public double aplicarPromocion(String codigoPromo) {
        double precioFinal = monthlyPrice;

        switch (codigoPromo.toUpperCase()) {
            case "GAMER2025":
                precioFinal = monthlyPrice * 0.88;
                registrarCambio("Promocion GAMER2025 aplicada (12% descuento)");
                break;
            case "ESPORTS":
                precioFinal = monthlyPrice * 0.92;
                registrarCambio("Promocion ESPORTS aplicada (8% descuento)");
                break;
            default:
                System.out.println("Codigo de promocion invalido");
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

    public int getLatenciaMaxima() {
        return latenciaMaxima;
    }

    public void setLatenciaMaxima(int latenciaMaxima) throws PlanInvalidoException {
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