package br.com.fiap.bo;

import br.com.fiap.beans.CaracteristicasEdificio;
import br.com.fiap.dao.CaracteristicasEdificioDAO;

import java.sql.SQLException;
import java.util.List;

public class CaracteristicasEdificioBO {

    private CaracteristicasEdificioDAO caracteristicasEdificioDAO;

    public CaracteristicasEdificioBO() throws ClassNotFoundException, SQLException {
        caracteristicasEdificioDAO = new CaracteristicasEdificioDAO();
    }

    // Listar todas as características
    public List<CaracteristicasEdificio> listarCaracteristicas() throws SQLException {
        return caracteristicasEdificioDAO.listar();
    }

    // Buscar características por ID
    public CaracteristicasEdificio buscarCaracteristica(int idCaracteristica) throws SQLException {
        return caracteristicasEdificioDAO.ler(idCaracteristica);
    }

    // Inserir novas características
    public String inserirCaracteristica(CaracteristicasEdificio caracteristica) throws SQLException {
        if (!caracteristica.validarDistribuicaoVidros()) {
            return "A distribuição da área envidraçada (X8) está fora do intervalo permitido.";
        }
        return caracteristicasEdificioDAO.inserir(caracteristica);
    }

    // Atualizar características existentes
    public String atualizarCaracteristica(CaracteristicasEdificio caracteristica) throws SQLException {
        return caracteristicasEdificioDAO.atualizar(caracteristica);
    }

    // Deletar características por ID
    public String deletarCaracteristica(int idCaracteristica) throws SQLException {
        return caracteristicasEdificioDAO.deletar(idCaracteristica);
    }
}
