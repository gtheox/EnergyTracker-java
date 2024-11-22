package br.com.fiap.dao;

import br.com.fiap.beans.CaracteristicasEdificio;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaracteristicasEdificioDAO {
    private Connection minhaConexao;

    public CaracteristicasEdificioDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(CaracteristicasEdificio caracteristica) throws SQLException {
        String sql = "INSERT INTO GS_CaracteristicasEdificio (EdificioID, X1, X2, X3, X4, X5, X6, X7, X8) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, caracteristica.getIdEdificio());
            stmt.setDouble(2, caracteristica.getX1());
            stmt.setDouble(3, caracteristica.getX2());
            stmt.setDouble(4, caracteristica.getX3());
            stmt.setDouble(5, caracteristica.getX4());
            stmt.setDouble(6, caracteristica.getX5());
            stmt.setInt(7, caracteristica.getX6());
            stmt.setDouble(8, caracteristica.getX7());
            stmt.setInt(9, caracteristica.getX8());
            stmt.execute();
            return "Características do edifício cadastradas com sucesso!";
        }
    }

    // Ler (por ID)
    public CaracteristicasEdificio ler(int idCaracteristica) throws SQLException {
        String sql = "SELECT * FROM GS_CaracteristicasEdificio WHERE CaracteristicaID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idCaracteristica);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CaracteristicasEdificio(
                            rs.getInt("CaracteristicaID"),
                            rs.getInt("EdificioID"),
                            rs.getDouble("X1"),
                            rs.getDouble("X2"),
                            rs.getDouble("X3"),
                            rs.getDouble("X4"),
                            rs.getDouble("X5"),
                            rs.getInt("X6"),
                            rs.getDouble("X7"),
                            rs.getInt("X8")
                    );
                }
                return null;
            }
        }
    }

    // Ler (todas as características)
    public List<CaracteristicasEdificio> listar() throws SQLException {
        String sql = "SELECT * FROM GS_CaracteristicasEdificio";
        List<CaracteristicasEdificio> caracteristicas = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                caracteristicas.add(new CaracteristicasEdificio(
                        rs.getInt("CaracteristicaID"),
                        rs.getInt("EdificioID"),
                        rs.getDouble("X1"),
                        rs.getDouble("X2"),
                        rs.getDouble("X3"),
                        rs.getDouble("X4"),
                        rs.getDouble("X5"),
                        rs.getInt("X6"),
                        rs.getDouble("X7"),
                        rs.getInt("X8")
                ));
            }
        }
        return caracteristicas;
    }

    // Atualizar
    public String atualizar(CaracteristicasEdificio caracteristica) throws SQLException {
        String sql = "UPDATE GS_CaracteristicasEdificio SET X1 = ?, X2 = ?, X3 = ?, X4 = ?, X5 = ?, X6 = ?, X7 = ?, X8 = ? WHERE CaracteristicaID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setDouble(1, caracteristica.getX1());
            stmt.setDouble(2, caracteristica.getX2());
            stmt.setDouble(3, caracteristica.getX3());
            stmt.setDouble(4, caracteristica.getX4());
            stmt.setDouble(5, caracteristica.getX5());
            stmt.setInt(6, caracteristica.getX6());
            stmt.setDouble(7, caracteristica.getX7());
            stmt.setInt(8, caracteristica.getX8());
            stmt.setInt(9, caracteristica.getIdCaracteristica());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Características do edifício atualizadas com sucesso!" : "Característica não encontrada.";
        }
    }

    // Deletar
    public String deletar(int idCaracteristica) throws SQLException {
        String sql = "DELETE FROM GS_CaracteristicasEdificio WHERE CaracteristicaID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idCaracteristica);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Características do edifício excluídas com sucesso!" : "Característica não encontrada.";
        }
    }
}
