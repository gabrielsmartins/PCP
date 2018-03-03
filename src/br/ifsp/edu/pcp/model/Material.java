package br.ifsp.edu.pcp.model;

import java.io.Serializable;


import javax.persistence.Entity;


@Entity
public class Material extends Componente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Material(String descricao, SituacaoProduto situacaoProduto,UnidadeMedida unidadeMedida, Double valorUnitario, Integer leadTime,
			Double quantidadeEstoque, Double quantidadeMinima) {
		super(descricao, situacaoProduto,unidadeMedida, valorUnitario, leadTime, quantidadeEstoque, quantidadeMinima);
	}

	
	public Material() {
		
	}

}
