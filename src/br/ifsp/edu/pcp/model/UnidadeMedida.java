package br.ifsp.edu.pcp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unidade")
public class UnidadeMedida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unid_id")
	private Long id;
	@Column(name = "unid_desc")
	private String descricao;
	@Column(name = "unid_sig")
	private String sigla;

	public UnidadeMedida(String descricao, String sigla) {
		this.descricao = descricao;
		this.sigla = sigla;
	}
	
	public UnidadeMedida() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getDescricao() {
		return this.descricao;
	}
	
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	public String getSigla() {
		return this.sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	
	
	

}
