package br.ifsp.edu.view;



import br.ifsp.edu.pcp.dao.RecursoDAO;
import br.ifsp.edu.pcp.dao.SetorDAO;
import br.ifsp.edu.pcp.model.Recurso;
import br.ifsp.edu.pcp.model.Setor;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class RecursoView extends Stage {

	private Scene scene;
	private Text scenetitle;
	private BorderPane root;
	private GridPane gridCadastro;
	private GridPane gridPesquisa;
	private Button btnNovo;
	private Button btnSalvar;
	private Button btnEditar;
	private Button btnCancelar;
	private Label lblID;
	private Label lblIDValue;
	private Label lblDescricao;
	private Label lblSetor;
	private Label lblPesquisar;
	private ComboBox<String> cmbCriterio;
	private ComboBox<Setor> cmbSetor;
	private TextField txtDescricao;
	private TextField txtPesquisa;
	private HBox hbBtn;
	private VBox vbTbl;
	private TableView<Recurso> table;
	private TableColumn<Recurso, Long> columnID;
	private TableColumn<Recurso, String> columnDescricao;
	private TableColumn<Recurso, String> columnSetor;
	private RecursoDAO recursoDAO;
	private SetorDAO setorDAO;
	
	
	public RecursoView() {
		this.setTitle("Recurso");
		this.recursoDAO = new RecursoDAO();
		 this.setorDAO = new SetorDAO();
		this.initComps();
		this.setScene(scene);
	}
	
	private void initComps() {
		this.root = new BorderPane();
		this.gridCadastro = new GridPane();
		this.gridPesquisa = new GridPane();
		this.scenetitle = new Text("Recurso");
		this.lblDescricao = new Label("Descricao:");
		this.lblID = new Label("ID:");
		this.lblSetor = new Label("Setor:");
		this.lblIDValue = new Label("-");
		this.lblPesquisar = new Label("Pesquisar:");
		this.txtDescricao = new TextField();
		this.txtPesquisa = new TextField();
		this.btnNovo = new Button("Novo");
		this.btnSalvar = new Button("Salvar");
		this.btnEditar = new Button("Editar");
		this.btnCancelar = new Button("Cancelar");
		this.carregaComboCriterios();
		this.carregaTabela();
		this.carregaComboSetor();
		this.txtDescricao.setDisable(true);
		this.cmbSetor.setDisable(true);
		this.btnSalvar.setDisable(true);
		this.btnEditar.setDisable(true);
		this.btnCancelar.setDisable(true);
		this.hbBtn = new HBox(10);
		this.vbTbl = new VBox(10);
		this.vbTbl.setPadding(new Insets(25, 25, 25, 25));
		this.scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.gridCadastro.setAlignment(Pos.BASELINE_LEFT);
		this.gridCadastro.setHgap(10);
		this.gridCadastro.setVgap(10);
		this.gridCadastro.setPadding(new Insets(25, 25, 25, 25));
		this.gridCadastro.add(scenetitle, 0, 0, 2, 1);
		this.gridCadastro.add(lblID, 0, 1);
		this.gridCadastro.add(lblIDValue, 1, 1);
		this.gridCadastro.add(lblDescricao, 0, 2);
		this.gridCadastro.add(txtDescricao, 1, 2);
		this.gridCadastro.add(lblSetor, 0, 3);
		this.gridCadastro.add(cmbSetor, 1, 3);
		this.hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		this.hbBtn.getChildren().addAll(btnNovo,btnSalvar,btnEditar,btnCancelar);
		this.gridCadastro.add(hbBtn, 1, 4);
		this.gridPesquisa.setHgap(10);
		this.gridPesquisa.setVgap(10);
		this.gridPesquisa.setPadding(new Insets(25, 25, 25, 0));
		this.gridPesquisa.add(lblPesquisar, 0, 0);
		this.gridPesquisa.add(cmbCriterio, 1, 0);
		this.gridPesquisa.add(txtPesquisa, 2, 0);
		this.vbTbl.getChildren().addAll(gridPesquisa,table);
		this.root.setLeft(vbTbl);
		this.root.setCenter(gridCadastro);
	    this.scene = new Scene(root);
	}
	
	
	private void carregaComboSetor() {
	 ObservableList<Setor> setores = FXCollections.observableArrayList(this.setorDAO.listar());
	 this.cmbSetor = new ComboBox<>(setores);
	 
	 this.cmbSetor.setConverter(new StringConverter<Setor>() {
		
		@Override
		public String toString(Setor setor) {
			return setor.getId() + "-" + setor.getDescricao();
		}
		
		@Override
		public Setor fromString(String string) {
			return null;
		}
	});
	 
	}
	
	private void carregaTabela() {
		this.table = new TableView<>();
		this.table.setPrefWidth(500);
		this.table.setItems(FXCollections.observableArrayList(this.recursoDAO.listar()));
		this.columnID = new TableColumn<>("ID");
		this.columnDescricao = new TableColumn<>("Descricao");
		this.columnSetor = new TableColumn<>("Setor");
		this.columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		this.columnSetor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSetor().getDescricao()));
		this.table.getColumns().addAll(columnID,columnDescricao,columnSetor);
		
	}
	
	
	private void carregaComboCriterios() {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "ID",
			        "Descrição",
			        "Setor"
			    );
		this.cmbCriterio = new ComboBox<>(options);
		this.cmbCriterio.getSelectionModel().select(1);
	}

	
	public Button getBtnNovo() {
		return btnNovo;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}
	
	public Button getBtnEditar() {
		return btnEditar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public ComboBox<String> getCmbCriterio() {
		return cmbCriterio;
	}

	public TextField getTxtDescricao() {
		return txtDescricao;
	}


	public TableView<Recurso> getTable() {
		return table;
	}

	public Label getLblIDValue() {
		return lblIDValue;
	}

	public TextField getTxtPesquisa() {
		return txtPesquisa;
	}

	public ComboBox<Setor> getCmbSetor() {
		return cmbSetor;
	}
	
	
	
	
	
}
