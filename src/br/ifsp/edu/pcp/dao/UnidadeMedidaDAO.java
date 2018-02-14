package br.ifsp.edu.pcp.dao;

import java.io.Serializable;

import br.ifsp.edu.pcp.model.UnidadeMedida;

public class UnidadeMedidaDAO extends GenericDAO<UnidadeMedida, Serializable> {
	
	public UnidadeMedidaDAO() {
		super(UnidadeMedida.class);
	}

}
