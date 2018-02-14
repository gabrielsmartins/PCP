package br.ifsp.edu.pcp.model;

public enum SituacaoProduto {
	
	ATIVO("Ativo"),
	INATIVO("Inativo"),
	FORA_DE_LINHA("Fora de Linha");
	
	private String situacao;
	
	private SituacaoProduto(String situacao) {
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}
	
	

}
