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
	private Menu menuProduto;
	private Menu menuMaterial;
	private Menu menuUnidade;
	private MenuItem menuItemNovoProduto;
	private MenuItem menuItemEditarProduto;
	private MenuItem menuItemPesquisarProduto;
	private MenuItem menuItemExcluirProduto;
	private MenuItem menuItemNovoMaterial;
	private MenuItem menuItemEditarMaterial;
	private MenuItem menuItemPesquisarMaterial;
	private MenuItem menuItemExcluirMaterial;
	private MenuItem menuItemNovoUnidade;
	private MenuItem menuItemEditarUnidade;
	private MenuItem menuItemPesquisarUnidade;
	private MenuItem menuItemExcluirUnidade;

	
	public MenuView() {
		this.setTitle("Menu Principal");
		this.initComps();
		this.setScene(scene);
	}
	
	
	private void initComps() {
		this.root = new BorderPane();
		this.menuBar = new MenuBar();
		this.menuBar.getMenus().add(this.getMenuProduto());
		this.menuBar.getMenus().add(this.getMenuMaterial());
		this.menuBar.getMenus().add(this.getMenuUnidade());
		this.root.setTop(menuBar);
	    this.scene = new Scene(root,500,600); 
	    this.root.prefHeightProperty().bind(scene.heightProperty());
	    this.root.prefWidthProperty().bind(scene.widthProperty());
	}
	
	
	private Menu getMenuUnidade() {
		this.menuUnidade = new Menu("Unidades");
		this.menuItemNovoUnidade = new MenuItem("Novo");
		this.menuItemEditarUnidade = new MenuItem("Editar");
		this.menuItemExcluirUnidade = new MenuItem("Excluir");
		this.menuItemPesquisarUnidade = new MenuItem("Pesquisar");
		this.menuUnidade.getItems().addAll(menuItemNovoUnidade,menuItemEditarUnidade,menuItemExcluirUnidade,menuItemPesquisarUnidade);
		return this.menuUnidade;
	}
	
	private Menu getMenuMaterial() {
		this.menuMaterial = new Menu("Materiais");
		this.menuItemNovoMaterial = new MenuItem("Novo");
		this.menuItemEditarMaterial = new MenuItem("Editar");
		this.menuItemExcluirMaterial = new MenuItem("Excluir");
		this.menuItemPesquisarMaterial = new MenuItem("Pesquisar");
		this.menuMaterial.getItems().addAll(menuItemNovoMaterial,menuItemEditarMaterial,menuItemExcluirMaterial,menuItemPesquisarMaterial);
		return this.menuMaterial;
	}
	
	
	private Menu getMenuProduto() {
		this.menuProduto = new Menu("Produtos");
		this.menuItemNovoProduto = new MenuItem("Novo");
		this.menuItemEditarProduto = new MenuItem("Editar");
		this.menuItemExcluirProduto = new MenuItem("Excluir");
		this.menuItemPesquisarProduto = new MenuItem("Pesquisar");
		this.menuProduto.getItems().addAll(menuItemNovoProduto,menuItemEditarProduto,menuItemExcluirProduto,menuItemPesquisarProduto);
		return this.menuProduto;
	}


	public MenuItem getMenuItemNovoUnidade() {
		return menuItemNovoUnidade;
	}


	public MenuItem getMenuItemPesquisarUnidade() {
		return menuItemPesquisarUnidade;
	}
	
	
	
	
	
	
	
	
	
	
}
