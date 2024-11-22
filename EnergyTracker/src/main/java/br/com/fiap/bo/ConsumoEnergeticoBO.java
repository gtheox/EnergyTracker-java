package br.com.fiap.bo;

import br.com.fiap.beans.ConsumoEnergetico;
import br.com.fiap.dao.ConsumoEnergeticoDAO;

import java.sql.SQLException;
import java.util.List;

public class ConsumoEnergeticoBO {

    private ConsumoEnergeticoDAO consumoEnergeticoDAO;

    public ConsumoEnergeticoBO() throws ClassNotFoundException, SQLException {
        consumoEnergeticoDAO = new ConsumoEnergeticoDAO();
    }

    // Listar todos os registros de consumo
    public List<ConsumoEnergetico> listarConsumos() throws SQLException {
        return consumoEnergeticoDAO.listar();
    }

    // Buscar consumo por ID
    public ConsumoEnergetico buscarConsumo(int idConsumo) throws SQLException {
        return consumoEnergeticoDAO.ler(idConsumo);
    }

    // Inserir novo registro de consumo
    public String inserirConsumo(ConsumoEnergetico consumo) throws SQLException {
        if (consumo.getHeatingLoad() < 0 || consumo.getCoolingLoad() < 0) {
            return "Os valores de HeatingLoad e CoolingLoad não podem ser negativos.";
        }
        return consumoEnergeticoDAO.inserir(consumo);
    }

    // Atualizar registro de consumo
    public String atualizarConsumo(ConsumoEnergetico consumo) throws SQLException {
        return consumoEnergeticoDAO.atualizar(consumo);
    }

    // Deletar consumo por ID
    public String deletarConsumo(int idConsumo) throws SQLException {
        return consumoEnergeticoDAO.deletar(idConsumo);
    }
}
