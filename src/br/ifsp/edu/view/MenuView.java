package br.ifsp.edu.view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuView extends Stage {

	private Scene scene;
	private BorderPane root;
	private MenuBar menuBar;
	private Menu menuUnidade;
	private Menu menuSetor;
	private Menu menuRecurso;
    private Menu menuOperacao;
    private Menu menuMaterial;
    private Menu menuProduto;

	
	public MenuView() {
		this.setTitle("Menu Principal");
		this.initComps();
		this.setMaximized(true);
		this.setScene(scene);
		
	}
	
	
	private void initComps() {
		this.root = new BorderPane();
		this.menuBar = new MenuBar();
		this.carregaMenuUnidade();
		this.carregaMenuSetor();
		this.carregaMenuRecurso();
		this.carregaMenuOperacao();
		this.carregaMenuMaterial();
		this.carregaMenuProduto();
		this.menuBar.getMenus().addAll(menuUnidade,menuSetor,menuRecurso,menuOperacao,menuMaterial,menuProduto);
		this.root.setTop(menuBar);
	    this.scene = new Scene(root,500,600); 
	    this.root.prefHeightProperty().bind(scene.heightProperty());
	    this.root.prefWidthProperty().bind(scene.widthProperty());
	}
	
	private void carregaMenuUnidade() {
		this.menuUnidade = new Menu("Unidades");
		MenuItem menuItemNovo = new MenuItem("Novo");
		MenuItem menuItemEditar = new MenuItem("Editar");
		MenuItem menuItemExcluir = new MenuItem("Excluir");
		MenuItem menuItemPesquisar = new MenuItem("Pesquisar");
		this.menuUnidade.getItems().addAll(menuItemNovo,menuItemEditar,menuItemExcluir,menuItemPesquisar);
	}
	
	
	private void carregaMenuSetor() {
		this.menuSetor = new Menu("Setores");
		MenuItem menuItemNovo = new MenuItem("Novo");
		MenuItem menuItemEditar = new MenuItem("Editar");
		MenuItem menuItemExcluir = new MenuItem("Excluir");
		MenuItem menuItemPesquisar = new MenuItem("Pesquisar");
		this.menuSetor.getItems().addAll(menuItemNovo,menuItemEditar,menuItemExcluir,menuItemPesquisar);
	}
	
	private void carregaMenuRecurso() {
		this.menuRecurso = new Menu("Recursos");
		MenuItem menuItemNovo = new MenuItem("Novo");
		MenuItem menuItemEditar = new MenuItem("Editar");
		MenuItem menuItemExcluir = new MenuItem("Excluir");
		MenuItem menuItemPesquisar = new MenuItem("Pesquisar");
		this.menuRecurso.getItems().addAll(menuItemNovo,menuItemEditar,menuItemExcluir,menuItemPesquisar);
	}
	
	
	private void carregaMenuOperacao() {
		this.menuOperacao = new Menu("Operacoes");
		MenuItem menuItemNovo = new MenuItem("Novo");
		MenuItem menuItemEditar = new MenuItem("Editar");
		MenuItem menuItemExcluir = new MenuItem("Excluir");
		MenuItem menuItemPesquisar = new MenuItem("Pesquisar");
		this.menuOperacao.getItems().addAll(menuItemNovo,menuItemEditar,menuItemExcluir,menuItemPesquisar);
	}
	
	private void carregaMenuMaterial() {
		this.menuMaterial = new Menu("Material");
		MenuItem menuItemNovo = new MenuItem("Novo");
		MenuItem menuItemEditar = new MenuItem("Editar");
		MenuItem menuItemExcluir = new MenuItem("Excluir");
		MenuItem menuItemPesquisar = new MenuItem("Pesquisar");
		this.menuMaterial.getItems().addAll(menuItemNovo,menuItemEditar,menuItemExcluir,menuItemPesquisar);
	}
	
	
	private void carregaMenuProduto() {
		this.menuProduto = new Menu("Produto");
		MenuItem menuItemNovo = new MenuItem("Novo");
		MenuItem menuItemEditar = new MenuItem("Editar");
		MenuItem menuItemExcluir = new MenuItem("Excluir");
		MenuItem menuItemPesquisar = new MenuItem("Pesquisar");
		this.menuProduto.getItems().addAll(menuItemNovo,menuItemEditar,menuItemExcluir,menuItemPesquisar);
	}
	
	public Menu getMenuUnidade() {
		return this.menuUnidade;
	}
	
	
	
	public Menu getMenuSetor() {
		return this.menuSetor;
	}


	public Menu getMenuRecurso() {
		return menuRecurso;
	}


	public Menu getMenuOperacao() {
		return menuOperacao;
	}


	public Menu getMenuMaterial() {
		return menuMaterial;
	}


	public Menu getMenuProduto() {
		return menuProduto;
	}
	
	

	
	
	
	
}
