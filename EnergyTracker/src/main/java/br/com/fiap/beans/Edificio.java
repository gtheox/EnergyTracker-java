package br.com.fiap.beans;

public class Edificio {
    private int idEdificio;
    private String nome;
    private String endereco;
    private int idUsuario;

    public Edificio() {
        super();
    }

    public Edificio(int idEdificio, String nome, String endereco, int idUsuario) {
        super();
        this.idEdificio = idEdificio;
        this.nome = nome;
        this.endereco = endereco;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Lógica adicional
    public boolean validarEndereco() {
        return endereco != null && !endereco.isEmpty();
    }

    public String gerarDescricao() {
        return "Edifício: " + nome + ", localizado em: " + endereco;
    }
}
