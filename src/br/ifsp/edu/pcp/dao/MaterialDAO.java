package br.ifsp.edu.pcp.dao;

import java.io.Serializable;

import br.ifsp.edu.pcp.model.Material;

public class MaterialDAO extends GenericDAO<Material, Serializable> {

	public MaterialDAO() {
		super(Material.class);
	}

	
}
