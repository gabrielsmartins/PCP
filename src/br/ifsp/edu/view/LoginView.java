package br.ifsp.edu.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView extends Stage{
	
	private Scene scene;
	private Text scenetitle;
	private GridPane root;
	private Button btnLogin;
	private Button btnSair;
	private Label lblUsuario;
	private Label lblSenha;
	private TextField txtUsuario;
	private PasswordField txtSenha;
	private HBox hbBtn;
	
	public LoginView() {
		this.setTitle("Login");
		this.initComps();
		this.setScene(scene);
	}
	
	private void initComps() {
		this.root = new GridPane();
		this.scenetitle = new Text("PCP");
		this.scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.root.setAlignment(Pos.CENTER);
		this.root.setHgap(10);
		this.root.setVgap(10);
		this.root.setPadding(new Insets(25, 25, 25, 25));
		this.root.add(scenetitle, 0, 0, 2, 1);
		this.lblUsuario = new Label("Usuario:");
		this.root.add(lblUsuario, 0, 1);
		this.txtUsuario = new TextField();
		this.root.add(txtUsuario, 1, 1);
		this.lblSenha = new Label("Senha:");
		this.root.add(lblSenha, 0, 2);
		this.txtSenha = new PasswordField();
		this.root.add(txtSenha, 1, 2);
		this.hbBtn = new HBox(10);
		this.btnLogin = new Button("Entrar");
		this.btnSair = new Button("Sair");
		this.hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		this.hbBtn.getChildren().addAll(btnLogin,btnSair);
		this.root.add(hbBtn, 1, 4);
	    this.scene = new Scene(root, 300, 275);
	}

}
