package br.ifsp.edu.pcp.dao;

import java.io.Serializable;

import br.ifsp.edu.pcp.model.Componente;

public class ComponenteDAO extends GenericDAO<Componente, Serializable> {

	public ComponenteDAO() {
		super(Componente.class);
	}
}
