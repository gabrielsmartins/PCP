package br.ifsp.edu.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UnidadeMedidaView extends Stage {

	private Scene scene;
	private Text scenetitle;
	private GridPane root;
	private Button btnSalvar;
	private Button btnCancelar;
	private Label lblDescricao;
	private Label lblSigla;
	private TextField txtDescricao;
	private TextField txtSigla;
	private HBox hbBtn;
	
	public UnidadeMedidaView() {
		this.setTitle("Unidade Medida");
		this.initComps();
		this.setScene(scene);
	}
	
	private void initComps() {
		this.root = new GridPane();
		this.scenetitle = new Text("Unidade Medida");
		this.scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.root.setAlignment(Pos.CENTER);
		this.root.setHgap(10);
		this.root.setVgap(10);
		this.root.setPadding(new Insets(25, 25, 25, 25));
		this.root.add(scenetitle, 0, 0, 2, 1);
		this.lblDescricao = new Label("Descricao:");
		this.root.add(lblDescricao, 0, 1);
		this.txtDescricao = new TextField();
		this.root.add(txtDescricao, 1, 1);
		this.lblSigla = new Label("Sigla:");
		this.root.add(lblSigla, 0, 2);
		this.txtSigla = new TextField();
		this.root.add(txtSigla, 1, 2);
		this.hbBtn = new HBox(10);
		this.btnSalvar = new Button("Salvar");
		this.btnCancelar = new Button("Cancelar");
		this.hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		this.hbBtn.getChildren().addAll(btnSalvar,btnCancelar);
		this.root.add(hbBtn, 1, 4);
	    this.scene = new Scene(root, 300, 275);
	}
}
