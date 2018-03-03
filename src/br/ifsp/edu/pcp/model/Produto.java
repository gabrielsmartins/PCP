package br.ifsp.edu.pcp.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;



@Entity
public class Produto extends Componente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	 @ElementCollection
	  @CollectionTable(
	        name="roteiro",
	        joinColumns=@JoinColumn(name="rot_prod_id")
	  )
	private List<Roteiro> roteiros = new LinkedList<>();
	 
	 
	 @ElementCollection
	  @CollectionTable(
	        name="estrutura_produto",
	        joinColumns=@JoinColumn(name="prod_id")
	  )
	private List<ItemEstrutura> estrutura = new LinkedList<>();
	 
	 

	public Produto(String descricao, SituacaoProduto situacaoProduto, UnidadeMedida unidadeMedida, Double valorUnitario,
			Integer leadTime, Double quantidadeEstoque, Double quantidadeMinima) {
		super(descricao, situacaoProduto, unidadeMedida, valorUnitario, leadTime, quantidadeEstoque, quantidadeMinima);
	}

	public Produto() {

	}

	public void adicionarComponente(ItemEstrutura itemEstrutura) {
		this.estrutura.add(itemEstrutura);
	}

	
	public void adicionarRoteiro(Roteiro roteiro) {
		this.roteiros.add(roteiro);
	}
	
	
	

	public List<ItemEstrutura> getEstrutura() {
		return estrutura;
	}

	public void setEstrutura(List<ItemEstrutura> estrutura) {
		this.estrutura = estrutura;
	}

	public List<Roteiro> getRoteiros() {
		return roteiros;
	}

	

	public void setRoteiros(List<Roteiro> roteiros) {
		this.roteiros = roteiros;
	}

	public void removerComponente(int index) {
		this.estrutura.remove(index);
		
	}
	
	
	
	
	
	
}
