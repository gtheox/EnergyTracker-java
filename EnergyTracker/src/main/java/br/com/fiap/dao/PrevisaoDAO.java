package br.com.fiap.dao;

import br.com.fiap.beans.Previsao;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrevisaoDAO {
    private Connection minhaConexao;

    public PrevisaoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(Previsao previsao) throws SQLException {
        String sql = "INSERT INTO GS_Previsoes (EdificioID, TipoPrevisao, ValorPrevisto) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, previsao.getIdEdificio());
            stmt.setString(2, previsao.getTipoPrevisao());
            stmt.setDouble(3, previsao.getValorPrevisto());
            stmt.execute();
            return "Previsão cadastrada com sucesso!";
        }
    }

    // Ler (por ID)
    public Previsao ler(int idPrevisao) throws SQLException {
        String sql = "SELECT * FROM GS_Previsoes WHERE PrevisaoID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idPrevisao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Previsao(
                            rs.getInt("PrevisaoID"),
                            rs.getInt("EdificioID"),
                            rs.getString("TipoPrevisao"),
                            rs.getDouble("ValorPrevisto")
                    );
                }
                return null;
            }
        }
    }

    // Ler (todas as previsões)
    public List<Previsao> listar() throws SQLException {
        String sql = "SELECT * FROM GS_Previsoes";
        List<Previsao> previsoes = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                previsoes.add(new Previsao(
                        rs.getInt("PrevisaoID"),
                        rs.getInt("EdificioID"),
                        rs.getString("TipoPrevisao"),
                        rs.getDouble("ValorPrevisto")
                ));
            }
        }
        return previsoes;
    }

    // Atualizar
    public String atualizar(Previsao previsao) throws SQLException {
        String sql = "UPDATE GS_Previsoes SET TipoPrevisao = ?, ValorPrevisto = ? WHERE PrevisaoID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, previsao.getTipoPrevisao());
            stmt.setDouble(2, previsao.getValorPrevisto());
            stmt.setInt(3, previsao.getIdPrevisao());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Previsão atualizada com sucesso!" : "Previsão não encontrada.";
        }
    }

    // Deletar
    public String deletar(int idPrevisao) throws SQLException {
        String sql = "DELETE FROM GS_Previsoes WHERE PrevisaoID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idPrevisao);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Previsão excluída com sucesso!" : "Previsão não encontrada.";
        }
    }
}
