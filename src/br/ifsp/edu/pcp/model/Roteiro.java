package br.ifsp.edu.pcp.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Roteiro {


	@Column(name = "rot_seq")
	private Long sequencia;

	@OneToOne
	private Operacao operacao;

	@Column(name = "rot_tmp_stp")
	private LocalTime tempoSetup;

	@Column(name = "rot_tmp_prd")
	private LocalTime tempoProducao;

	@Column(name = "rot_tmp_fnl")
	private LocalTime tempoFinalizacao;

	public Roteiro() {

	}

	

	public Roteiro(Long sequencia, Operacao operacao, LocalTime tempoSetup,
			LocalTime tempoProducao, LocalTime tempoFinalizacao) {
		this.sequencia = sequencia;
		this.operacao = operacao;
		this.tempoSetup = tempoSetup;
		this.tempoProducao = tempoProducao;
		this.tempoFinalizacao = tempoFinalizacao;
	}



	public Long getSequencia() {
		return sequencia;
	}

	public void setSequencia(Long sequencia) {
		this.sequencia = sequencia;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public LocalTime getTempoSetup() {
		return tempoSetup;
	}

	public void setTempoSetup(LocalTime tempoSetup) {
		this.tempoSetup = tempoSetup;
	}

	public LocalTime getTempoProducao() {
		return tempoProducao;
	}

	public void setTempoProducao(LocalTime tempoProducao) {
		this.tempoProducao = tempoProducao;
	}

	public LocalTime getTempoFinalizacao() {
		return tempoFinalizacao;
	}

	public void setTempoFinalizacao(LocalTime tempoFinalizacao) {
		this.tempoFinalizacao = tempoFinalizacao;
	}

	

}
