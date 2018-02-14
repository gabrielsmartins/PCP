package br.ifsp.edu.pcp.dao;

import java.io.Serializable;

import br.ifsp.edu.pcp.model.Perfil;

public class PerfilDAO extends GenericDAO<Perfil, Serializable> {
	
	public PerfilDAO() {
	       super(Perfil.class);
	    }   

}
