package br.com.fiap.dao;

import br.com.fiap.beans.ConsumoEnergetico;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumoEnergeticoDAO {
    private Connection minhaConexao;

    public ConsumoEnergeticoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(ConsumoEnergetico consumo) throws SQLException {
        String sql = "INSERT INTO GS_ConsumoEnergetico (EdificioID, DataHora, HeatingLoad, CoolingLoad) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, consumo.getIdEdificio());
            stmt.setTimestamp(2, Timestamp.valueOf(consumo.getDataHora()));
            stmt.setDouble(3, consumo.getHeatingLoad());
            stmt.setDouble(4, consumo.getCoolingLoad());
            stmt.execute();
            return "Consumo energético cadastrado com sucesso!";
        }
    }

    // Ler (por ID)
    public ConsumoEnergetico ler(int idConsumo) throws SQLException {
        String sql = "SELECT * FROM GS_ConsumoEnergetico WHERE ConsumoID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idConsumo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ConsumoEnergetico(
                            rs.getInt("ConsumoID"),
                            rs.getInt("EdificioID"),
                            rs.getTimestamp("DataHora").toLocalDateTime(),
                            rs.getDouble("HeatingLoad"),
                            rs.getDouble("CoolingLoad")
                    );
                }
                return null;
            }
        }
    }

    // Ler (todos os consumos)
    public List<ConsumoEnergetico> listar() throws SQLException {
        String sql = "SELECT * FROM GS_ConsumoEnergetico";
        List<ConsumoEnergetico> consumos = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                consumos.add(new ConsumoEnergetico(
                        rs.getInt("ConsumoID"),
                        rs.getInt("EdificioID"),
                        rs.getTimestamp("DataHora").toLocalDateTime(),
                        rs.getDouble("HeatingLoad"),
                        rs.getDouble("CoolingLoad")
                ));
            }
        }
        return consumos;
    }

    // Atualizar
    public String atualizar(ConsumoEnergetico consumo) throws SQLException {
        String sql = "UPDATE GS_ConsumoEnergetico SET DataHora = ?, HeatingLoad = ?, CoolingLoad = ? WHERE ConsumoID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(consumo.getDataHora()));
            stmt.setDouble(2, consumo.getHeatingLoad());
            stmt.setDouble(3, consumo.getCoolingLoad());
            stmt.setInt(4, consumo.getIdConsumo());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Consumo energético atualizado com sucesso!" : "Consumo não encontrado.";
        }
    }

    // Deletar
    public String deletar(int idConsumo) throws SQLException {
        String sql = "DELETE FROM GS_ConsumoEnergetico WHERE ConsumoID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idConsumo);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Consumo energético excluído com sucesso!" : "Consumo não encontrado.";
        }
    }
}
