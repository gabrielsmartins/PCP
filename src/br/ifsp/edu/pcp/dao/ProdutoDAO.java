package br.ifsp.edu.pcp.dao;

import java.io.Serializable;

import br.ifsp.edu.pcp.model.Produto;

public class ProdutoDAO extends GenericDAO<Produto, Serializable> {

	public ProdutoDAO() {
		super(Produto.class);
	}
}
