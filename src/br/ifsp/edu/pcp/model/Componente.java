package br.ifsp.edu.pcp.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produto")
@DiscriminatorColumn(name="prod_tipo")
public abstract class Componente {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column(name="prod_id")
	protected Long id;
	@Column(name="prod_cod_intr")
	protected String codigoInterno;
	@Column(name="prod_desc")
	protected String descricao;
	@Enumerated(EnumType.STRING)
	@Column(name="prod_sit")
	protected SituacaoProduto situacao;
	@ManyToOne
	@JoinColumn(name="prod_unid_id")
	protected UnidadeMedida unidadeMedida;
	@Column(name="prod_vlr_unit")
	protected Double valorUnitario;
	@Column(name="prod_lead_time")
	protected Integer leadTime;
	@Column(name="prod_qntd_estq")
	protected Double quantidadeEstoque;
	@Column(name="prod_qntd_min")
	protected Double quantidadeMinima;
	@Column(name="prod_peso_kg")
	protected Double peso;
	@Column(name="prod_comp_mm")
	protected Double comprimento;
	@Column(name="prod_larg_mm")
	protected Double largura;
	@Column(name="prod_alt_mm")
	protected Double altura;
	
	
	public Componente(String descricao, SituacaoProduto situacaoProduto,UnidadeMedida unidadeMedida, Double valorUnitario, Integer leadTime,
			Double quantidadeEstoque, Double quantidadeMinima) {
		this.descricao = descricao;
		this.situacao = situacaoProduto;
		this.unidadeMedida = unidadeMedida;
		this.valorUnitario = valorUnitario;
		this.leadTime = leadTime;
		this.quantidadeEstoque = quantidadeEstoque;
		this.quantidadeMinima = quantidadeMinima;
	}
	
	
	
	public Componente() {
		
	}

	


	public Long getId() {
		return id;
	}
	


	public void setId(Long id) {
		this.id = id;
	}

	

	public String getCodigoInterno() {
		return codigoInterno;
	}

	
	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}


	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}


	public Double getValorUnitario() {
		return valorUnitario;
	}


	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	public Integer getLeadTime() {
		return leadTime;
	}


	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}


	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}


	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}


	public Double getQuantidadeMinima() {
		return quantidadeMinima;
	}


	public void setQuantidadeMinima(Double quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}



	public Double getPeso() {
		return peso;
	}



	public void setPeso(Double peso) {
		this.peso = peso;
	}



	public Double getComprimento() {
		return comprimento;
	}



	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}



	public Double getLargura() {
		return largura;
	}



	public void setLargura(Double largura) {
		this.largura = largura;
	}



	public Double getAltura() {
		return altura;
	}



	public void setAltura(Double altura) {
		this.altura = altura;
	}



	public SituacaoProduto getSituacao() {
		return situacao;
	}



	public void setSituacao(SituacaoProduto situacao) {
		this.situacao = situacao;
	}


	
	
	
}
