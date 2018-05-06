package br.ifsp.edu.view;

import br.ifsp.edu.pcp.dao.ComponenteDAO;
import br.ifsp.edu.pcp.dao.OperacaoDAO;
import br.ifsp.edu.pcp.dao.ProdutoDAO;
import br.ifsp.edu.pcp.dao.UnidadeMedidaDAO;
import br.ifsp.edu.pcp.model.Componente;
import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.Produto;
import br.ifsp.edu.pcp.model.Roteiro;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;
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

public class ProdutoView extends Stage {

	private Scene scene;
	private Text scenetitle;
	private BorderPane root;
	private GridPane grid;
	private GridPane gridPesquisa;
	private TabPane tabPane;
	private Tab abaDadosBasicos;
	private Tab abaDadosTecnicos;
	private Tab abaDadosEstoque;
	private Tab abaDadosEstrutura;
	private Tab abaDadosRoteiro;
	private Button btnNovo;
	private Button btnSalvar;
	private Button btnEditar;
	private Button btnCancelar;
	private Button btnAddOperacao;
	private Button btnRemoveOperacao;
	private Button btnAddComponente;
	private Button btnRemoveComponente;
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
	private Label lblPesquisarComponente;
	private Label lblPesquisarOperacao;
	private TextField txtCodigoInterno;
	private TextField txtDescricao;
	private ToggleGroup groupSituacao;
	private RadioButton radioSituacaoAtivo;
	private RadioButton radioSituacaoInativo;
	private RadioButton radioSituacaoForaDeLinha;
	private ComboBox<String> cmbCriterio;
	private ComboBox<String> cmbCriterioComponente;
	private ComboBox<String> cmbCriterioOperacao;
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
	private TextField txtPesquisaComponente;
	private TextField txtPesquisaOperacao;
	private HBox hbBtn;
	private VBox vbTbl;
	private TableView<Produto> table;
	private TableColumn<Produto, Long> columnID;
	private TableColumn<Produto, String> columnCodigoInterno;
	private TableColumn<Produto, String> columnDescricao;
	private TableColumn<Produto, String> columnUnidadeMedida;
	private TableColumn<Produto, String> columnSituacao;
	private TableColumn<Produto, Double> columnPeso;
	private TableColumn<Produto, Double> columnComprimento;
	private TableColumn<Produto, Double> columnLargura;
	private TableColumn<Produto, Double> columnAltura;
	private TableColumn<Produto, Double> columnQuantidadeEstoque;
	private TableColumn<Produto, Double> columnQuantidadeMinima;
	private TableColumn<Produto, Integer> columnLeadTime;
	private TableView<Componente> tableEstrutura;
	private TableView<Roteiro> tableRoteiro;
	private TableView<Operacao> tableOperacao;
	private TableColumn<Operacao, Long> columnOperacaoID;
	private TableColumn<Operacao, String> columnOperacaoDescricao;
	private TableColumn<Operacao, String> columnOperacaoSetor;
	private TableView<Componente> tableComponente;
	private TableColumn<Componente, Long> columnComponenteID;
	private TableColumn<Componente, String> columnComponenteCodigoInterno;
	private TableColumn<Componente, String> columnComponenteDescricao;
	private TableColumn<Componente, String> columnComponenteSituacao;
	private ProdutoDAO produtoDAO;
	private UnidadeMedidaDAO unidadeMedidaDAO;
	private ComponenteDAO componenteDAO;
	private OperacaoDAO operacaoDAO;

	public ProdutoView() {
		this.setTitle("Produto");
		this.produtoDAO = new ProdutoDAO();
		this.unidadeMedidaDAO = new UnidadeMedidaDAO();
		this.operacaoDAO = new OperacaoDAO();
		this.componenteDAO = new ComponenteDAO();
		this.carregaComboCriterios();
		this.carregaComboCriteriosComponente();
		this.carregaComboCriteriosOperacao();
		this.carregaComboUnidade();
		this.carregaTabela();
		this.carregaTabelaEstrutura();
		this.carregaTabelaRoteiro();
		this.carregaTabelaOperacao();
		this.carregaTabelaComponente();
		this.initComps();
		this.setScene(scene);
	}

	private void initComps() {
		this.root = new BorderPane();
		this.grid = new GridPane();
		this.gridPesquisa = new GridPane();
		this.scenetitle = new Text("Produto");
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
		this.lblPesquisarComponente = new Label("Pesquisar:");
		this.lblPesquisarOperacao = new Label("Pesquisar:");
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
		this.txtPesquisaComponente = new TextField();
		this.txtPesquisaOperacao = new TextField();
		this.txtLeadTime = new TextField();
		this.txtQuantidadeEstoque = new TextField();
		this.txtQuantidadeMinima = new TextField();
		this.btnNovo = new Button("Novo");
		this.btnSalvar = new Button("Salvar");
		this.btnEditar = new Button("Editar");
		this.btnCancelar = new Button("Cancelar");
		this.btnAddOperacao = new Button("+");
		this.btnRemoveOperacao = new Button("-");
		this.btnAddComponente = new Button("+");
		this.btnRemoveComponente = new Button("-");
		this.tabPane = new TabPane();
		this.abaDadosBasicos = new Tab("Dados Basicos");
		this.abaDadosTecnicos = new Tab("Dados Tecnicos");
		this.abaDadosEstoque = new Tab("Dados Estoque");
		this.abaDadosEstrutura = new Tab("Dados Estrutura");
		this.abaDadosRoteiro = new Tab("Dados Roteiro");
		this.abaDadosBasicos.setClosable(false);
		this.abaDadosTecnicos.setClosable(false);
		this.abaDadosEstoque.setClosable(false);
		this.abaDadosEstrutura.setClosable(false);
		this.abaDadosRoteiro.setClosable(false);
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
		this.carregaAbaDadosEstrutura();
		this.carregaAbaDadosRoteiro();
		this.tabPane.getTabs().addAll(abaDadosBasicos,abaDadosTecnicos,abaDadosEstoque,abaDadosEstrutura,abaDadosRoteiro);
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
		this.vbTbl.getChildren().addAll(gridPesquisa, table);
		this.gridPesquisa.setHgrow(txtPesquisa, Priority.ALWAYS);
		this.vbTbl.setVgrow(table, Priority.ALWAYS);
		this.grid.setHgrow(tabPane, Priority.ALWAYS);
		this.root.setLeft(vbTbl);
		this.root.setCenter(grid);
		this.scene = new Scene(root);
	}

	private void carregaTabela() {
		this.table = new TableView<>();
		this.table.setPrefWidth(700);
		this.table.setItems(FXCollections.observableArrayList(this.produtoDAO.listar()));
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
	
	private void carregaTabelaEstrutura() {
		this.tableEstrutura = new TableView<>();
		this.tableEstrutura.setPrefWidth(700);
	}
	
	
	private void carregaTabelaRoteiro() {
		this.tableRoteiro = new TableView<>();
		this.tableRoteiro.setPrefWidth(700);
		
	}
	
	
	private void carregaTabelaOperacao() {
		this.tableOperacao = new TableView<>();
		this.tableOperacao.setPrefWidth(500);
		this.tableOperacao.setItems(FXCollections.observableArrayList(this.operacaoDAO.listar()));
		this.columnOperacaoID = new TableColumn<>("ID");
		this.columnOperacaoDescricao = new TableColumn<>("Descricao");
		this.columnOperacaoSetor = new TableColumn<>("Setor");
		this.columnOperacaoID.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.columnOperacaoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		this.columnOperacaoSetor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSetor().getDescricao()));
		this.tableOperacao.getColumns().addAll(columnOperacaoID,columnOperacaoDescricao,columnOperacaoSetor);
		
	}
	
	
	private void carregaTabelaComponente() {
		this.tableComponente = new TableView<>();
		this.tableComponente.setPrefWidth(500);
		this.tableComponente.setItems(FXCollections.observableArrayList(this.componenteDAO.listar()));
		this.columnComponenteID = new TableColumn<>("ID");
		this.columnComponenteCodigoInterno = new TableColumn<>("Cod. Interno");
		this.columnComponenteDescricao = new TableColumn<>("Descricao");
		this.columnComponenteSituacao = new TableColumn<>("Situacao");
		this.columnComponenteID.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.columnComponenteCodigoInterno.setCellValueFactory(new PropertyValueFactory<>("codigoInterno"));
		this.columnComponenteDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		this.columnComponenteSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		this.tableComponente.getColumns().addAll(columnComponenteID,columnComponenteCodigoInterno,columnComponenteDescricao,columnComponenteSituacao);
		
	}

	private void carregaComboCriterios() {
		ObservableList<String> options = FXCollections.observableArrayList("ID", "Descrição", "Sigla");
		this.cmbCriterio = new ComboBox<>(options);
		this.cmbCriterio.getSelectionModel().select(1);
	}
	
	private void carregaComboCriteriosComponente() {
		ObservableList<String> options = FXCollections.observableArrayList("ID", "Descrição", "Sigla");
		this.cmbCriterioComponente = new ComboBox<>(options);
		this.cmbCriterioComponente.getSelectionModel().select(1);
	}
	
	private void carregaComboCriteriosOperacao() {
		ObservableList<String> options = FXCollections.observableArrayList("ID", "Descrição", "Setor");
		this.cmbCriterioOperacao = new ComboBox<>(options);
		this.cmbCriterioOperacao.getSelectionModel().select(1);
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
	
	private void carregaAbaDadosEstrutura() {
		GridPane gridPane = new GridPane();
		gridPane.add(lblPesquisarComponente, 0, 1);
		gridPane.add(cmbCriterioComponente, 1, 1);
		gridPane.add(txtPesquisaComponente, 2, 1);
		gridPane.add(btnAddComponente,3,1);
		gridPane.add(tableEstrutura, 0, 2,3,1);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		gridPane.setAlignment(Pos.BASELINE_LEFT);
		this.abaDadosEstrutura.setContent(gridPane);
	}
	
	
	private void carregaAbaDadosRoteiro() {
		GridPane gridPane = new GridPane();
		gridPane.add(lblPesquisarOperacao, 0, 1);
		gridPane.add(cmbCriterioOperacao, 1, 1);
		gridPane.add(txtPesquisaOperacao, 2, 1);
		gridPane.add(btnAddOperacao,3,1);
		gridPane.add(tableOperacao, 0, 2,3,1);
		//gridPane.add(btnRemoveOperacao, 0, 3);
		//gridPane.add(tableRoteiro, 0, 3);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		gridPane.setAlignment(Pos.BASELINE_LEFT);
		this.abaDadosRoteiro.setContent(gridPane);
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

	public TableView<Produto> getTable() {
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
