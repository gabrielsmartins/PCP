package br.ifsp.edu.pcp.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
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
@AttributeOverrides({
@AttributeOverride( name="dataEmissao", column = @Column(name="ord_dt_emi")),
@AttributeOverride( name="prazo", column = @Column(name="ord_prazo") ),
@AttributeOverride( name="dataConclusao", column = @Column(name="ord_dt_concl") )
})


@AssociationOverrides({
	   @AssociationOverride(name = "responsavel",
	      joinColumns = @JoinColumn(name = "ord_usr_id"))
	})
@Table(name="ordem_producao")
public class OrdemProducao extends Documento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ord_status")
	private StatusOrdemProducao status = StatusOrdemProducao.EMITIDA;
	
	@ManyToOne
	@JoinColumn(name="ord_prod_id")
	private Produto produto;
	

	
	public OrdemProducao() {
		
	}

	public OrdemProducao(Produto produto, LocalDate prazo, Usuario responsavel) {
		this.produto = produto;
		this.prazo = prazo;
		this.responsavel = responsavel;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public StatusOrdemProducao getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemProducao status) {
		this.status = status;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	
	
	

}
