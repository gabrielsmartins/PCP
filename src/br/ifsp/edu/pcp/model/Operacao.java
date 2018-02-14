package br.ifsp.edu.pcp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="operacao")	
public class Operacao implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "oper_id")
	private Long id;
	@Column(name = "oper_desc")
	private String descricao;
	@Column(name = "oper_instr")
	private String instrucao;
	
	@ManyToOne
	@JoinColumn(name="oper_setr_id")
	private Setor setor;
	
	public Operacao(String descricao, String instrucao, Setor setor) {
		this.descricao = descricao;
		this.instrucao = instrucao;
		this.setor = setor;
	}

	public Operacao() {
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

	public String getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	
	
	
	
	

}
