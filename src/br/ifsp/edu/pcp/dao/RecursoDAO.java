package br.ifsp.edu.pcp.dao;

import java.io.Serializable;

import br.ifsp.edu.pcp.model.Recurso;

public class RecursoDAO extends GenericDAO<Recurso, Serializable> {
	
	public RecursoDAO() {
		super(Recurso.class);
	}

}
