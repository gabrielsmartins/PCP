package br.ifsp.edu.pcp.model;

public enum StatusRequisicaoMaterial {
	
	EMITIDA("Emitida"),
	INICIADA("Iniciada"),
	ENCERRADA("Encerrada"),
	CANCELADA("Cancelada");
	
	private String status;
	
	private StatusRequisicaoMaterial(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
