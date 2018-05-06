package br.ifsp.edu.controller;

import br.ifsp.edu.view.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuController {
	
	private MenuView menuView;

	public MenuController() {
		this.menuView = new MenuView();
		this.handleMenuUnidadeMedida();
		this.handleMenuSetor();
		this.handleMenuRecurso();
		this.handleMenuOperacao();
		this.handleMenuMaterial();
		this.handleMenuProduto();
	}

	public MenuView getMenuView() {
		return menuView;
	}
	
	
	private void handleMenuUnidadeMedida() {
		this.menuView.getMenuUnidade().getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new UnidadeController().getUnidadeMedidaView().show();
				
			}
		});
		
		
		this.menuView.getMenuUnidade().getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new UnidadeController().getUnidadeMedidaView().show();
				
			}
		});
		
	
	}
	
	private void handleMenuSetor() {
		this.menuView.getMenuSetor().getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new SetorController().getSetorView().show();
				
			}
		});
		
		
		this.menuView.getMenuSetor().getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new SetorController().getSetorView().show();
				
			}
		});

	}
	
	
	private void handleMenuRecurso() {
		this.menuView.getMenuRecurso().getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new RecursoController().getRecursoView().show();
				
			}
		});
		
		
		this.menuView.getMenuRecurso().getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new RecursoController().getRecursoView().show();
				
			}
		});
		
	}
	
	
	private void handleMenuOperacao() {
		this.menuView.getMenuOperacao().getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new OperacaoController().getRecursoView().show();
				
			}
		});
		
		
		this.menuView.getMenuOperacao().getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new OperacaoController().getRecursoView().show();
				
			}
		});
		
	}
	
	
	private void handleMenuMaterial() {
		this.menuView.getMenuMaterial().getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new MaterialController().getMaterialView().show();
				
			}
		});
		
		
		this.menuView.getMenuMaterial().getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new MaterialController().getMaterialView().show();
				
			}
		});
		
	}
	
	
	private void handleMenuProduto() {
		this.menuView.getMenuProduto().getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new ProdutoController().getProdutoView().show();
				
			}
		});
		
		
		this.menuView.getMenuProduto().getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new ProdutoController().getProdutoView().show();
				
			}
		});
		
	}
	
	
	

}
