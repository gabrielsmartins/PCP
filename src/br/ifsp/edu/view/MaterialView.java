package br.ifsp.edu.view;

import br.ifsp.edu.pcp.dao.MaterialDAO;
import br.ifsp.edu.pcp.dao.SetorDAO;
import br.ifsp.edu.pcp.dao.UnidadeMedidaDAO;
import br.ifsp.edu.pcp.model.Material;
import br.ifsp.edu.pcp.model.Setor;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class MaterialView extends Stage {

	private Scene scene;
	private Text scenetitle;
	private BorderPane root;
	private GridPane grid;
	private GridPane gridPesquisa;
	private TabPane tabPane;
	private Tab abaDadosBasicos;
	private Tab abaDadosTecnicos;
	private Tab abaDadosEstoque;
	private Button btnNovo;
	private Button btnSalvar;
	private Button btnEditar;
	private Button btnCancelar;
	private Label lblID;
	private Label lblIDValue;
	private Label lblCodigoInterno;
	private Label lblDescricao;
	private Label lblSituacao;
	private Label lblUnidadeMedida;
	private Label lblValorUnitario;
	private Label lblLeadTime;
	private Label lblPeso;
	private Label lblComprimento;
	private Label lblLargura;
	private Label lblAltura;
	private Label lblQuantidadeEstoque;
	private Label lblQuantidadeMinima;
	private Label lblPesquisar;
	private ComboBox<String> cmbCriterio;
	private TextField txtCodigoInterno;
	private TextField txtDescricao;
	private ToggleGroup groupSituacao;
	private RadioButton radioSituacaoAtivo;
	private RadioButton radioSituacaoInativo;
	private RadioButton radioSituacaoForaDeLinha;
	private ComboBox<UnidadeMedida> cmbUnidadeMedida;
	private TextField txtPeso;
	private TextField txtComprimento;
	private TextField txtLargura;
	private TextField txtAltura;
	private TextField txtValorUnitario;
	private TextField txtLeadTime;
	private TextField txtQuantidadeEstoque;
	private TextField txtQuantidadeMinima;
	private TextField txtPesquisa;
	private HBox hbBtn;
	private VBox vbTbl;
	private TableView<Material> table;
	private TableColumn<Material, Long> columnID;
	private TableColumn<Material, String> columnCodigoInterno;
	private TableColumn<Material, String> columnDescricao;
	private TableColumn<Material, String> columnUnidadeMedida;
	private TableColumn<Material, String> columnSituacao;
	private TableColumn<Material, Double> columnPeso;
	private TableColumn<Material, Double> columnComprimento;
	private TableColumn<Material, Double> columnLargura;
	private TableColumn<Material, Double> columnAltura;
	private TableColumn<Material, Double> columnQuantidadeEstoque;
	private TableColumn<Material, Double> columnQuantidadeMinima;
	private TableColumn<Material, Integer> columnLeadTime;
	private MaterialDAO materialDAO;
	private UnidadeMedidaDAO unidadeMedidaDAO;

	public MaterialView() {
		this.setTitle("Material");
		this.materialDAO = new MaterialDAO();
		this.unidadeMedidaDAO = new UnidadeMedidaDAO();
		this.carregaComboCriterios();
		this.carregaComboUnidade();
		this.carregaTabela();
		this.initComps();
		this.setScene(scene);
	}

	private void initComps() {
		this.root = new BorderPane();
		this.grid = new GridPane();
		this.gridPesquisa = new GridPane();
		this.scenetitle = new Text("Material");
		this.lblDescricao = new Label("Descricao:");
		this.lblID = new Label("ID:");
		this.lblIDValue = new Label("-");
		this.lblCodigoInterno = new Label("Cod. Interno:");
		this.lblSituacao = new Label("Situacao:");
		this.lblUnidadeMedida = new Label("U.M:");
		this.lblPeso = new Label("Peso:");
		this.lblComprimento = new Label("Comprimento (mm):");
		this.lblLargura = new Label("Largura (mm):");
		this.lblAltura = new Label("Altura (mm):");
		this.lblValorUnitario = new Label("Valor Unit.:");
		this.lblLeadTime = new Label("Lead Time:");
		this.lblQuantidadeEstoque = new Label("Qntd. Estoque:");
		this.lblQuantidadeMinima = new Label("Qntd. Minima:");
		this.lblPesquisar = new Label("Pesquisar:");
		this.groupSituacao = new ToggleGroup();
		this.radioSituacaoAtivo = new RadioButton("Ativo");
		this.radioSituacaoInativo = new RadioButton("Inativo");
		this.radioSituacaoForaDeLinha = new RadioButton("Fora de Linha");
		this.txtCodigoInterno = new TextField();
		this.txtDescricao = new TextField();
		this.txtCodigoInterno = new TextField();
		this.txtValorUnitario = new TextField();
		this.txtPeso = new TextField();
		this.txtComprimento = new TextField();
		this.txtAltura = new TextField();
		this.txtLargura = new TextField();
		this.txtPesquisa = new TextField();
		this.txtLeadTime = new TextField();
		this.txtQuantidadeEstoque = new TextField();
		this.txtQuantidadeMinima = new TextField();
		this.btnNovo = new Button("Novo");
		this.btnSalvar = new Button("Salvar");
		this.btnEditar = new Button("Editar");
		this.btnCancelar = new Button("Cancelar");
		this.tabPane = new TabPane();
		this.abaDadosBasicos = new Tab("Dados Basicos");
		this.abaDadosTecnicos = new Tab("Dados Tecnicos");
		this.abaDadosEstoque = new Tab("Dados Estoque");
		this.abaDadosBasicos.setClosable(false);
		this.abaDadosTecnicos.setClosable(false);
		this.abaDadosEstoque.setClosable(false);
		this.txtCodigoInterno.setDisable(true);
		this.txtValorUnitario.setDisable(true);
		this.txtComprimento.setDisable(true);
		this.txtLargura.setDisable(true);
		this.txtAltura.setDisable(true);
		this.txtPeso.setDisable(true);
		this.txtDescricao.setDisable(true);
		this.txtQuantidadeEstoque.setDisable(true);
		this.txtQuantidadeMinima.setDisable(true);
		this.txtLeadTime.setDisable(true);
		this.radioSituacaoAtivo.setDisable(true);
		this.radioSituacaoInativo.setDisable(true);
		this.radioSituacaoForaDeLinha.setDisable(true);
		this.cmbUnidadeMedida.setDisable(true);
		this.btnSalvar.setDisable(true);
		this.btnEditar.setDisable(true);
		this.btnCancelar.setDisable(true);
		this.hbBtn = new HBox(10);
		this.vbTbl = new VBox(10);
		this.vbTbl.setPadding(new Insets(25, 25, 25, 25));
		this.scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.grid.setAlignment(Pos.BASELINE_LEFT);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
		this.grid.setPadding(new Insets(25, 25, 25, 25));
		this.carregaAbaDadosBasicos();
		this.carregaAbaDadosTecnicos();
		this.carregaAbaDadosEstoque();
		this.tabPane.getTabs().addAll(abaDadosBasicos,abaDadosTecnicos,abaDadosEstoque);
		this.hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		this.hbBtn.getChildren().addAll(btnNovo, btnSalvar, btnEditar, btnCancelar);
		this.grid.add(scenetitle, 0, 0, 2, 1);
		this.grid.add(tabPane, 0, 1);
		this.grid.add(hbBtn, 0, 2);
		this.gridPesquisa.setHgap(10);
		this.gridPesquisa.setVgap(10);
		this.gridPesquisa.setPadding(new Insets(25, 25, 25, 0));
		this.gridPesquisa.add(lblPesquisar, 0, 0);
		this.gridPesquisa.add(cmbCriterio, 1, 0);
		this.gridPesquisa.add(txtPesquisa, 2, 0);
		this.gridPesquisa.setHgrow(txtPesquisa, Priority.ALWAYS);
		this.vbTbl.getChildren().addAll(gridPesquisa, table);
		this.vbTbl.setVgrow(table, Priority.ALWAYS);
		this.grid.setHgrow(tabPane, Priority.ALWAYS);
		this.root.setLeft(vbTbl);
		this.root.setCenter(grid);
		this.scene = new Scene(root);
	}

	private void carregaTabela() {
		this.table = new TableView<>();
		this.table.setPrefWidth(700);
		this.table.setItems(FXCollections.observableArrayList(this.materialDAO.listar()));
		this.columnID = new TableColumn<>("ID");
		this.columnCodigoInterno = new TableColumn<>("Cod. Interno");
		this.columnDescricao = new TableColumn<>("Descricao");
		this.columnUnidadeMedida = new TableColumn<>("U.M");
		this.columnSituacao = new TableColumn<>("Situacao");
		this.columnQuantidadeEstoque = new TableColumn<>("Qntd Estq.");
		this.columnQuantidadeMinima = new TableColumn<>("Qntd Min.");
		this.columnLeadTime = new TableColumn<>("Lead Time");
		this.columnPeso = new TableColumn<>("Peso");
		this.columnComprimento = new TableColumn<>("Comp (mm)");
		this.columnLargura = new TableColumn<>("Larg (mm)");
		this.columnAltura = new TableColumn<>("Alt (mm)");
		this.columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.columnCodigoInterno.setCellValueFactory(new PropertyValueFactory<>("codigoInterno"));
		this.columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		this.columnUnidadeMedida.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUnidadeMedida().getDescricao()));
		this.columnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		this.columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
		this.columnComprimento.setCellValueFactory(new PropertyValueFactory<>("comprimento"));
		this.columnLargura.setCellValueFactory(new PropertyValueFactory<>("largura"));
		this.columnAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		this.columnQuantidadeEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));
		this.columnQuantidadeMinima.setCellValueFactory(new PropertyValueFactory<>("quantidadeMinima"));
		this.columnQuantidadeMinima.setCellValueFactory(new PropertyValueFactory<>("leadTime"));
		this.columnLeadTime.setCellValueFactory(new PropertyValueFactory<>("leadTime"));
		this.table.getColumns().addAll(columnID, columnCodigoInterno,columnDescricao,columnUnidadeMedida,columnSituacao,columnQuantidadeEstoque,columnQuantidadeMinima,columnLeadTime,columnPeso,columnComprimento,columnLargura,columnAltura);

	}

	private void carregaComboCriterios() {
		ObservableList<String> options = FXCollections.observableArrayList("ID", "Descrição", "Sigla");
		this.cmbCriterio = new ComboBox<>(options);
		this.cmbCriterio.getSelectionModel().select(1);
	}
	
	private void carregaComboUnidade() {
		 ObservableList<UnidadeMedida> unidades = FXCollections.observableArrayList(this.unidadeMedidaDAO.listar());
		 this.cmbUnidadeMedida = new ComboBox<>(unidades);
		 
		 this.cmbUnidadeMedida.setConverter(new StringConverter<UnidadeMedida>() {
			
			@Override
			public String toString(UnidadeMedida unidade) {
				return unidade.getId() + "-" + unidade.getDescricao();
			}
			
			@Override
			public UnidadeMedida fromString(String string) {
				return null;
			}
		});
		 
		}
	
	

	private void carregaAbaDadosBasicos() {
		GridPane gridPane = new GridPane();
		gridPane.add(lblID, 0, 1);
		gridPane.add(lblIDValue, 1, 1);
		gridPane.add(lblCodigoInterno, 0, 2);
		gridPane.add(txtCodigoInterno, 1, 2);
		gridPane.add(lblDescricao, 0, 3);
		gridPane.add(txtDescricao, 1, 3,3,1);
		gridPane.add(lblValorUnitario, 0, 4);
		gridPane.add(txtValorUnitario, 1, 4);
		this.radioSituacaoAtivo.setToggleGroup(this.groupSituacao);
		this.radioSituacaoAtivo.setUserData(SituacaoProduto.ATIVO);
		this.radioSituacaoInativo.setToggleGroup(this.groupSituacao);
		this.radioSituacaoInativo.setUserData(SituacaoProduto.INATIVO);
		this.radioSituacaoForaDeLinha.setToggleGroup(this.groupSituacao);
		this.radioSituacaoForaDeLinha.setUserData(SituacaoProduto.FORA_DE_LINHA);
		this.radioSituacaoAtivo.setSelected(true);
		gridPane.add(lblSituacao, 0, 5);
		HBox hboxSituacao = new HBox();
		hboxSituacao.getChildren().addAll(radioSituacaoAtivo,radioSituacaoInativo,radioSituacaoForaDeLinha);
		gridPane.add(hboxSituacao, 1, 5);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		gridPane.setAlignment(Pos.BASELINE_LEFT);
		this.abaDadosBasicos.setContent(gridPane);

	}

	private void carregaAbaDadosTecnicos() {
		GridPane gridPane = new GridPane();
		gridPane.add(lblUnidadeMedida, 0, 1);
		gridPane.add(cmbUnidadeMedida,1,1,3,1);
		gridPane.add(lblComprimento, 0, 2);
		gridPane.add(txtComprimento, 1, 2);
		gridPane.add(lblLargura, 0, 3);
		gridPane.add(txtLargura, 1, 3);
		gridPane.add(lblAltura, 0, 4);
		gridPane.add(txtAltura, 1, 4);
		gridPane.add(lblPeso, 0, 5);
		gridPane.add(txtPeso, 1, 5);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		gridPane.setAlignment(Pos.BASELINE_LEFT);
		this.abaDadosTecnicos.setContent(gridPane);
	}

	private void carregaAbaDadosEstoque() {
		GridPane gridPane = new GridPane();
		gridPane.add(lblQuantidadeEstoque, 0, 1);
		gridPane.add(txtQuantidadeEstoque, 1, 1);
		gridPane.add(lblQuantidadeMinima, 0, 2);
		gridPane.add(txtQuantidadeMinima, 1, 2);
		gridPane.add(lblLeadTime, 0, 3);
		gridPane.add(txtLeadTime, 1, 3);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		gridPane.setAlignment(Pos.BASELINE_LEFT);
		this.abaDadosEstoque.setContent(gridPane);
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

	public TableView<Material> getTable() {
		return table;
	}

	public Label getLblIDValue() {
		return lblIDValue;
	}

	public TextField getTxtPesquisa() {
		return txtPesquisa;
	}

	public TextField getTxtCodigoInterno() {
		return txtCodigoInterno;
	}

	public TextField getTxtDescricao() {
		return txtDescricao;
	}

	public ComboBox<UnidadeMedida> getCmbUnidadeMedida() {
		return cmbUnidadeMedida;
	}

	public TextField getTxtPeso() {
		return txtPeso;
	}

	public TextField getTxtComprimento() {
		return txtComprimento;
	}

	public TextField getTxtLargura() {
		return txtLargura;
	}

	public TextField getTxtAltura() {
		return txtAltura;
	}

	public TextField getTxtValorUnitario() {
		return txtValorUnitario;
	}

	public TextField getTxtLeadTime() {
		return txtLeadTime;
	}

	public TextField getTxtQuantidadeEstoque() {
		return txtQuantidadeEstoque;
	}

	public TextField getTxtQuantidadeMinima() {
		return txtQuantidadeMinima;
	}

	public RadioButton getRadioSituacaoAtivo() {
		return radioSituacaoAtivo;
	}

	public RadioButton getRadioSituacaoInativo() {
		return radioSituacaoInativo;
	}

	public RadioButton getRadioSituacaoForaDeLinha() {
		return radioSituacaoForaDeLinha;
	}

	public ToggleGroup getGroupSituacao() {
		return groupSituacao;
	}
	
	
	
	
	
	
	
	

}
