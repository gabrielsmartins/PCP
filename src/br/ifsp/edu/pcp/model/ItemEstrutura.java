package br.ifsp.edu.pcp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ItemEstrutura {
	
	@OneToOne
	@JoinColumn(name = "prod_sub_id")
	private Componente componente;
	
	@Column(name = "prod_sub_qntd")
	private Double quantidade;
	
	public ItemEstrutura() {
		
	}
	
	
	public ItemEstrutura(Componente componente, Double quantidade) {
		this.componente = componente;
		this.quantidade = quantidade;
	}
	
	
	public Componente getComponente() {
		return componente;
	}
	public void setComponente(Componente componente) {
		this.componente = componente;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	

}
