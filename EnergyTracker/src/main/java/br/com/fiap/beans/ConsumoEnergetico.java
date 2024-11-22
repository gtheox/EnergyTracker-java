package br.com.fiap.beans;

import java.time.LocalDateTime;

public class ConsumoEnergetico {
    private int idConsumo;
    private int idEdificio;
    private LocalDateTime dataHora;
    private double heatingLoad; // Carga de aquecimento
    private double coolingLoad; // Carga de resfriamento

    public ConsumoEnergetico() {
        super();
    }

    public ConsumoEnergetico(int idConsumo, int idEdificio, LocalDateTime dataHora, double heatingLoad, double coolingLoad) {
        super();
        this.idConsumo = idConsumo;
        this.idEdificio = idEdificio;
        this.dataHora = dataHora;
        this.heatingLoad = heatingLoad;
        this.coolingLoad = coolingLoad;
    }

    // Getters e Setters
    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double getHeatingLoad() {
        return heatingLoad;
    }

    public void setHeatingLoad(double heatingLoad) {
        this.heatingLoad = heatingLoad;
    }

    public double getCoolingLoad() {
        return coolingLoad;
    }

    public void setCoolingLoad(double coolingLoad) {
        this.coolingLoad = coolingLoad;
    }

    // Lógica adicional
    public double calcularConsumoTotal() {
        return heatingLoad + coolingLoad;
    }

    public boolean verificarConsumoElevado() {
        return calcularConsumoTotal() > 50; // Exemplo: consumo total acima de 50 é considerado elevado
    }
}
