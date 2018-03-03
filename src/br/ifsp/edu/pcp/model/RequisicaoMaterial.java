package br.ifsp.edu.pcp.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

@Entity
@AttributeOverrides({
@AttributeOverride( name="dataEmissao", column = @Column(name="rm_dt_emi")),
@AttributeOverride( name="prazo", column = @Column(name="rm_prazo") ),
@AttributeOverride( name="dataConclusao", column = @Column(name="rm_dt_concl") )
})


@AssociationOverrides({
	   @AssociationOverride(name = "responsavel",
	      joinColumns = @JoinColumn(name = "rm_usr_id"))
	})
@Table(name="requisicao_material")
public class RequisicaoMaterial extends Documento {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rm_id")
	private Long id;
        
        
	@Enumerated(EnumType.STRING)
        @Column(name="rm_status")
	private StatusRequisicaoMaterial status = StatusRequisicaoMaterial.EMITIDA;
	
	
	@ElementCollection
	@JoinTable(name = "requisicao_material_detalhe", joinColumns = @JoinColumn(name = "rm_id"))
	@Column(name = "rm_prod_qntd")
	@MapKeyJoinColumn(name = "rm_prod_id", updatable = true)
	private Map<Material,Double> itens = new LinkedHashMap<>();
	
	public RequisicaoMaterial() {
		
	}

	public RequisicaoMaterial (LocalDate prazo, Usuario responsavel) {
		this.prazo = prazo;
		this.responsavel = responsavel;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public StatusRequisicaoMaterial getStatus() {
		return status;
	}


	public void setStatus(StatusRequisicaoMaterial status) {
		this.status = status;
	}


	public Map<Material, Double> getItens() {
		return itens;
	}


	public void setItens(Map<Material, Double> itens) {
		this.itens = itens;
	}
	
	public void adicionarMaterial(Material material, Double quantidade) {
		this.itens.put(material,quantidade);
	}
	
	
	
	

}
