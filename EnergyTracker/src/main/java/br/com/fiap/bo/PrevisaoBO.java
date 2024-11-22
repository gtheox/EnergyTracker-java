package br.com.fiap.bo;

import br.com.fiap.beans.Previsao;
import br.com.fiap.dao.PrevisaoDAO;

import java.sql.SQLException;
import java.util.List;

public class PrevisaoBO {

    private PrevisaoDAO previsaoDAO;

    public PrevisaoBO() throws ClassNotFoundException, SQLException {
        previsaoDAO = new PrevisaoDAO();
    }

    // Listar todas as previsões
    public List<Previsao> listarPrevisoes() throws SQLException {
        return previsaoDAO.listar();
    }

    // Buscar previsão por ID
    public Previsao buscarPrevisao(int idPrevisao) throws SQLException {
        return previsaoDAO.ler(idPrevisao);
    }

    // Inserir nova previsão
    public String inserirPrevisao(Previsao previsao) throws SQLException {
        if (!previsao.validarTipoPrevisao()) {
            return "O tipo de previsão informado não é válido.";
        }
        return previsaoDAO.inserir(previsao);
    }

    // Atualizar previsão
    public String atualizarPrevisao(Previsao previsao) throws SQLException {
        return previsaoDAO.atualizar(previsao);
    }

    // Deletar previsão por ID
    public String deletarPrevisao(int idPrevisao) throws SQLException {
        return previsaoDAO.deletar(idPrevisao);
    }
}
