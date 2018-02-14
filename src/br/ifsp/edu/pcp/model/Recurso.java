package br.ifsp.edu.pcp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="recurso")
public class Recurso {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recr_id")
	private Long id;
	@Column(name="recr_desc")
	private String descricao;
	@ManyToOne
	@JoinColumn(name="recr_setr_id")
	private Setor setor;

	public Recurso(String descricao, Setor setor) {
		super();
		this.descricao = descricao;
		this.setor = setor;
	}
	
	public Recurso(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	
	
	
	

}
