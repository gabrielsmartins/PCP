package br.ifsp.edu.pcp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="setor")
public class Setor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setr_id")
	private Long id;
	
	@Column(name="setr_desc")
	private String descricao;
	
	@OneToMany(mappedBy="setor")
	private List<Operacao> operacoes;
	
	
	@OneToMany(mappedBy="setor")
	private List<Recurso> recursos;
	
	public Setor(String descricao) {
		this.descricao = descricao;
	}
	

	public Setor() {
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


	public List<Operacao> getOperacoes() {
		return operacoes;
	}

	
	public void adicionarOperacao(Operacao operacao) {
		this.operacoes.add(operacao);
	}

	public void adicionarRecurso(Recurso recurso) {
		this.recursos.add(recurso);
	}
}
