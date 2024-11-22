package br.com.fiap.beans;

public class Previsao {
    private int idPrevisao;
    private int idEdificio;
    private String tipoPrevisao; // Ex.: "Redução de Emissões" ou "Consumo Energético"
    private double valorPrevisto;

    public Previsao() {
        super();
    }

    public Previsao(int idPrevisao, int idEdificio, String tipoPrevisao, double valorPrevisto) {
        super();
        this.idPrevisao = idPrevisao;
        this.idEdificio = idEdificio;
        this.tipoPrevisao = tipoPrevisao;
        this.valorPrevisto = valorPrevisto;
    }

    // Getters e Setters
    public int getIdPrevisao() {
        return idPrevisao;
    }

    public void setIdPrevisao(int idPrevisao) {
        this.idPrevisao = idPrevisao;
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getTipoPrevisao() {
        return tipoPrevisao;
    }

    public void setTipoPrevisao(String tipoPrevisao) {
        this.tipoPrevisao = tipoPrevisao;
    }

    public double getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(double valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    // Lógica adicional
    public boolean validarTipoPrevisao() {
        return tipoPrevisao != null && (tipoPrevisao.equalsIgnoreCase("Redução de Emissões") ||
                                        tipoPrevisao.equalsIgnoreCase("Consumo Energético"));
    }

    public String gerarResumoPrevisao() {
        return "Previsão do tipo: " + tipoPrevisao + ", Valor Previsto: " + valorPrevisto + " kWh.";
    }
}
