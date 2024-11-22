package br.com.fiap.dao;

import br.com.fiap.beans.Edificio;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EdificioDAO {
    private Connection minhaConexao;

    public EdificioDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(Edificio edificio) throws SQLException {
        String sql = "INSERT INTO GS_Edificios (Nome, Endereco, UsuarioID) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, edificio.getNome());
            stmt.setString(2, edificio.getEndereco());
            stmt.setInt(3, edificio.getIdUsuario());
            stmt.execute();
            return "Edifício cadastrado com sucesso!";
        }
    }

    // Ler (por ID)
    public Edificio ler(int idEdificio) throws SQLException {
        String sql = "SELECT * FROM GS_Edificios WHERE EdificioID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idEdificio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Edificio(
                            rs.getInt("EdificioID"),
                            rs.getString("Nome"),
                            rs.getString("Endereco"),
                            rs.getInt("UsuarioID")
                    );
                }
                return null;
            }
        }
    }

    // Ler (todos os edifícios)
    public List<Edificio> listar() throws SQLException {
        String sql = "SELECT * FROM GS_Edificios";
        List<Edificio> edificios = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                edificios.add(new Edificio(
                        rs.getInt("EdificioID"),
                        rs.getString("Nome"),
                        rs.getString("Endereco"),
                        rs.getInt("UsuarioID")
                ));
            }
        }
        return edificios;
    }

    // Atualizar
    public String atualizar(Edificio edificio) throws SQLException {
        String sql = "UPDATE GS_Edificios SET Nome = ?, Endereco = ?, UsuarioID = ? WHERE EdificioID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, edificio.getNome());
            stmt.setString(2, edificio.getEndereco());
            stmt.setInt(3, edificio.getIdUsuario());
            stmt.setInt(4, edificio.getIdEdificio());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Edifício atualizado com sucesso!" : "Edifício não encontrado.";
        }
    }

    // Deletar
    public String deletar(int idEdificio) throws SQLException {
        String sql = "DELETE FROM GS_Edificios WHERE EdificioID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idEdificio);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Edifício deletado com sucesso!" : "Edifício não encontrado.";
        }
    }
}
