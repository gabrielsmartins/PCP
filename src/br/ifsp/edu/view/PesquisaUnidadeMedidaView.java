package br.ifsp.edu.view;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PesquisaUnidadeMedidaView extends Stage {

	private Scene scene;
	private BorderPane root;
	private Text lblPesquisar;
	private ComboBox<String> cmbCampo;
	private HBox hboxPesquisa;
	
	public PesquisaUnidadeMedidaView() {
		this.initComps();
	}
	
	private void initComps() {
		this.root = new BorderPane();
		this.hboxPesquisa = new HBox();
        this.lblPesquisar = new Text("Pesquisar:");
        this.cmbCampo = new ComboBox<>();
        this.hboxPesquisa.getChildren().addAll(lblPesquisar,cmbCampo);
		this.root.setTop(this.hboxPesquisa);
	    this.scene = new Scene(root,500,600); 
	    this.root.prefHeightProperty().bind(scene.heightProperty());
	    this.root.prefWidthProperty().bind(scene.widthProperty());
	}
}
