package br.ifsp.edu.controller;

import br.ifsp.edu.view.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuController {
	
	private MenuView menuView;

	public MenuController() {
		this.menuView = new MenuView();
		this.carregaAcaoMenuUnidade();
	}

	public MenuView getMenuView() {
		return menuView;
	}
	
	
	private void carregaAcaoMenuUnidade() {
		this.menuView.getMenuItemNovoUnidade().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new UnidadeController().getUnidadeMedidaView().show();
				
			}
		});
		
		
		this.menuView.getMenuItemPesquisarUnidade().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new UnidadeController().getPesquisaUnidadeMedidaView().show();
				
			}
		});
	}
	

}
