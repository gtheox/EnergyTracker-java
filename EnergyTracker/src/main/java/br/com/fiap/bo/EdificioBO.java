package br.com.fiap.bo;

import br.com.fiap.beans.Edificio;
import br.com.fiap.dao.EdificioDAO;

import java.sql.SQLException;
import java.util.List;

public class EdificioBO {

    private EdificioDAO edificioDAO;

    public EdificioBO() throws ClassNotFoundException, SQLException {
        edificioDAO = new EdificioDAO();
    }

    // Listar todos os edifícios
    public List<Edificio> listarEdificios() throws SQLException {
        return edificioDAO.listar();
    }

    // Buscar edifício por ID
    public Edificio buscarEdificio(int idEdificio) throws SQLException {
        return edificioDAO.ler(idEdificio);
    }

    // Inserir novo edifício
    public String inserirEdificio(Edificio edificio) throws SQLException {
        // Regras de negócio
        if (!edificio.validarEndereco()) {
            return "O endereço do edifício não pode estar vazio.";
        }
        return edificioDAO.inserir(edificio);
    }

    // Atualizar um edifício existente
    public String atualizarEdificio(Edificio edificio) throws SQLException {
        return edificioDAO.atualizar(edificio);
    }

    // Deletar edifício por ID
    public String deletarEdificio(int idEdificio) throws SQLException {
        return edificioDAO.deletar(idEdificio);
    }
}
