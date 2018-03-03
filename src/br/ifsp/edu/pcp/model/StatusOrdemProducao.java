package br.ifsp.edu.pcp.model;

public enum StatusOrdemProducao {
	
	EMITIDA("Emitida"),
	INICIADA("Iniciada"),
	ENCERRADA("Encerrada"),
	CANCELADA("Cancelada");
	
	private String status;
	
	private StatusOrdemProducao(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
