package br.com.fiap.beans;

public class CaracteristicasEdificio {
    private int idCaracteristica;
    private int idEdificio;
    private double x1;
    private double x2;
    private double x3;
    private double x4;
    private double x5;
    private int x6;
    private double x7;
    private int x8;

    public CaracteristicasEdificio() {
        super();
    }

    public CaracteristicasEdificio(int idCaracteristica, int idEdificio, double x1, double x2, double x3, double x4, double x5, int x6, double x7, int x8) {
        super();
        this.idCaracteristica = idCaracteristica;
        this.idEdificio = idEdificio;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.x6 = x6;
        this.x7 = x7;
        this.x8 = x8;
    }

    // Getters e Setters
    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getX4() {
        return x4;
    }

    public void setX4(double x4) {
        this.x4 = x4;
    }

    public double getX5() {
        return x5;
    }

    public void setX5(double x5) {
        this.x5 = x5;
    }

    public int getX6() {
        return x6;
    }

    public void setX6(int x6) {
        this.x6 = x6;
    }

    public double getX7() {
        return x7;
    }

    public void setX7(double x7) {
        this.x7 = x7;
    }

    public int getX8() {
        return x8;
    }

    public void setX8(int x8) {
        this.x8 = x8;
    }

    // Lógica adicional
    public double calcularMediaArea() {
        return (x1 + x2 + x3 + x4 + x5) / 5;
    }

    public boolean validarDistribuicaoVidros() {
        return x8 >= 0 && x8 <= 4; // Exemplo de regra para distribuição
    }
}
