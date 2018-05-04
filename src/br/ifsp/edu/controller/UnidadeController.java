package br.ifsp.edu.controller;

import br.ifsp.edu.view.PesquisaUnidadeMedidaView;
import br.ifsp.edu.view.UnidadeMedidaView;

public class UnidadeController {
	
	private UnidadeMedidaView unidadeMedidaView;
	private PesquisaUnidadeMedidaView pesquisaUnidadeMedidaView;
	
	 public UnidadeController() {
		 this.unidadeMedidaView = new UnidadeMedidaView();
		 this.pesquisaUnidadeMedidaView = new PesquisaUnidadeMedidaView();
	 }

	public UnidadeMedidaView getUnidadeMedidaView() {
		return unidadeMedidaView;
	}
	
	
	
	public PesquisaUnidadeMedidaView getPesquisaUnidadeMedidaView() {
		return pesquisaUnidadeMedidaView;
	}

	private void carregaAcaoPesquisar() {
		
	}
	 
	 
	 

}
