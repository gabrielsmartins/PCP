package br.ifsp.edu.pcp.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.ifsp.edu.pcp.model.Operacao;

public class OperacaoDAO extends GenericDAO<Operacao, Serializable> {

	public OperacaoDAO() {
		super(Operacao.class);
	}

	public List<Operacao> buscaSimilar(String descricao) {
		TypedQuery<Operacao> typedQuery = entityManager.createQuery(
				"SELECT o FROM Operacao o WHERE upper(o.descricao) LIKE upper(:descricao)", Operacao.class);
		typedQuery.setParameter("descricao", "%" + descricao + "%");
		return typedQuery.getResultList();
	}

	
}
