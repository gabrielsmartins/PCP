package br.ifsp.edu.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.ifsp.edu.pcp.dao.ComponenteDAO;
import br.ifsp.edu.pcp.dao.OperacaoDAO;
import br.ifsp.edu.pcp.dao.ProdutoDAO;
import br.ifsp.edu.pcp.model.Componente;
import br.ifsp.edu.pcp.model.ItemEstrutura;
import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.Produto;
import br.ifsp.edu.pcp.model.Roteiro;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.view.ProdutoView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProdutoController {

	private ProdutoView produtoView;
	private ProdutoDAO produtoDAO;

	public ProdutoController() {
		this.produtoView = new ProdutoView();
		this.produtoDAO = new ProdutoDAO();
		this.handleButtonNovo();
		this.handleButtonSalvar();
		this.handleButtonEditar();
		this.handleButtonCancelar();
		this.handleTable();
		this.handleTableRoteiro();
		this.handleTableEstrutura();
		this.handleTextPesquisa();
		this.handleButtonPesquisarOperacao();
		this.handleButtonAddRoteiro();
		this.handleButtonAddEstrutura();
		this.handleButtonPesquisarComponente();
	}

	public ProdutoView getProdutoView() {
		return this.produtoView;
	}

	private void handleButtonNovo() {
		this.getProdutoView().getBtnNovo().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				habilitaCampos();
				produtoView.getBtnNovo().setDisable(true);
				produtoView.getBtnSalvar().setDisable(false);
				produtoView.getBtnEditar().setDisable(true);
				produtoView.getBtnCancelar().setDisable(false);
			}
		});
	}
	
	private void handleButtonEditar() {
		this.getProdutoView().getBtnEditar().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				habilitaCampos();
				produtoView.getBtnNovo().setDisable(true);
				produtoView.getBtnSalvar().setDisable(false);
				produtoView.getBtnEditar().setDisable(true);
				produtoView.getBtnCancelar().setDisable(true);
			}
		});
	}

	private void handleButtonSalvar() {
		this.getProdutoView().getBtnSalvar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String id = produtoView.getLblIDValue().getText();
				String codigoInterno = produtoView.getTxtCodigoInterno().getText().toUpperCase();
				String descricao = produtoView.getTxtDescricao().getText().toUpperCase();
				UnidadeMedida unidadeMedida = produtoView.getCmbUnidadeMedida().getSelectionModel().getSelectedItem();
				Double valorUnitario = Double.parseDouble(produtoView.getTxtValorUnitario().getText());
				Integer leadTime = Integer.parseInt(produtoView.getTxtLeadTime().getText());
				Double quantidadeEstoque = Double.parseDouble(produtoView.getTxtQuantidadeEstoque().getText());
				Double quantidadeMinima = Double.parseDouble(produtoView.getTxtQuantidadeMinima().getText());
				SituacaoProduto situacaoProduto = (SituacaoProduto) produtoView.getGroupSituacao().getSelectedToggle().getUserData();
				Double peso = Double.parseDouble(produtoView.getTxtPeso().getText());
				Double comprimento = Double.parseDouble(produtoView.getTxtComprimento().getText());
				Double largura = Double.parseDouble(produtoView.getTxtLargura().getText());
				Double altura = Double.parseDouble(produtoView.getTxtAltura().getText());
		       
				

				if (descricao.isEmpty() || descricao == null) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Atencao");
					alert.setContentText("Por favor preencha todos os campos");
					alert.show();
				} else {
					Produto produto = new Produto(descricao, situacaoProduto, unidadeMedida, valorUnitario, leadTime, quantidadeEstoque, quantidadeMinima);
					produto.setCodigoInterno(codigoInterno);
					produto.setPeso(peso);
				    produto.setComprimento(comprimento);
				    produto.setLargura(largura);
					produto.setAltura(altura);
					if(id != "-") {
						produto.setId(Long.parseLong(id));
						produtoDAO.atualizar(produto);
					}else {
						produtoDAO.salvar(produto);
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Atencao");
					alert.setContentText("Produto Salvo com Sucesso !!!");
					alert.showAndWait();
					limpaCampos();
					desabilitaCampos();
					atualizarTabela();
				}

			}
		});
	}

	private void handleButtonCancelar() {
		this.getProdutoView().getBtnCancelar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				produtoView.getBtnNovo().setDisable(false);
				produtoView.getBtnSalvar().setDisable(true);
				produtoView.getBtnEditar().setDisable(true);
				produtoView.getBtnCancelar().setDisable(true);
				
			}
		});
	}

	
	private void handleTable() {
		this.getProdutoView().getTable().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (produtoView.getTable().getSelectionModel().getSelectedItem() != null) {
					Produto produto = getProdutoView().getTable().getSelectionModel().getSelectedItem();
					produtoView.getLblIDValue().setText(produto.getId().toString());
					produtoView.getTxtCodigoInterno().setText(produto.getCodigoInterno());
					produtoView.getTxtDescricao().setText(produto.getDescricao());
					produtoView.getTxtValorUnitario().setText(produto.getValorUnitario().toString());
					produtoView.getTxtPeso().setText(produto.getPeso().toString());
					produtoView.getTxtComprimento().setText(produto.getComprimento().toString());
					produtoView.getTxtLargura().setText(produto.getLargura().toString());
					produtoView.getTxtAltura().setText(produto.getAltura().toString());
					produtoView.getTxtAltura().setText(produto.getAltura().toString());
					produtoView.getTxtQuantidadeEstoque().setText(produto.getQuantidadeEstoque().toString());
					produtoView.getTxtQuantidadeMinima().setText(produto.getQuantidadeMinima().toString());
					produtoView.getTxtLeadTime().setText(produto.getLeadTime().toString());
					produtoView.getCmbUnidadeMedida().getSelectionModel().select(produto.getUnidadeMedida());
					
					SituacaoProduto situacao = produto.getSituacao();
					
					if(situacao == SituacaoProduto.ATIVO) {
						produtoView.getRadioSituacaoAtivo().setSelected(true);
					}else if(situacao == SituacaoProduto.INATIVO) {
						produtoView.getRadioSituacaoInativo().setSelected(true);
					}else {
						produtoView.getRadioSituacaoForaDeLinha().setSelected(true);
					}
					
					desabilitaCampos();
					produtoView.getBtnNovo().setDisable(false);
					produtoView.getBtnSalvar().setDisable(true);
					produtoView.getBtnEditar().setDisable(false);
					produtoView.getBtnCancelar().setDisable(true);
				}

			}
		});

	}
	
	
	
	
	
	
	

	private void handleTableRoteiro() {
		this.getProdutoView().getTableRoteiro().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (produtoView.getTableRoteiro().getSelectionModel().getSelectedItem() != null) {
					Roteiro roteiro = getProdutoView().getTableRoteiro().getSelectionModel().getSelectedItem();
					
					produtoView.getLblIDOperacaoValue().setText(roteiro.getOperacao().getId().toString());
					produtoView.getLblDescricaoOperacaoValue().setText(roteiro.getOperacao().getDescricao());
					produtoView.getLblSetorOperacaoValue().setText(roteiro.getOperacao().getSetor().getDescricao());
					produtoView.getTxtTempoSetup().setText(roteiro.getTempoSetup().toString());
					produtoView.getTxtTempoProducao().setText(roteiro.getTempoProducao().toString());
					produtoView.getTxtTempoFinalizacao().setText(roteiro.getTempoFinalizacao().toString());
			        produtoView.getTxtTempoSetup().setDisable(true);
			        produtoView.getTxtTempoProducao().setDisable(true);
			        produtoView.getTxtTempoFinalizacao().setDisable(true);
				}

			}
		});

	}
	
	
	private void handleTableEstrutura() {
		this.getProdutoView().getTableEstrutura().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (produtoView.getTableEstrutura().getSelectionModel().getSelectedItem() != null) {
					ItemEstrutura item = getProdutoView().getTableEstrutura().getSelectionModel().getSelectedItem();
					
					produtoView.getLblIDComponenteValue().setText(item.getComponente().getId().toString());
					produtoView.getLblCodigoInternoComponenteValue().setText(item.getComponente().getCodigoInterno());
					produtoView.getLblDescricaoComponenteValue().setText(item.getComponente().getDescricao());
					produtoView.getLblUnidadeComponenteValue().setText(item.getComponente().getUnidadeMedida().getSigla());
					produtoView.getLblSituacaoComponenteValue().setText(item.getComponente().getSituacao().toString());
					produtoView.getTxtQuantidadeComponente().setText(item.getQuantidade().toString());
					
					 produtoView.getTxtQuantidadeComponente().setDisable(true);
				}

			}
		});

	}
	
	
	
	
	private void handleTextPesquisa() {
		produtoView.getTxtPesquisa().setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 String item = produtoView.getCmbCriterio().getSelectionModel().getSelectedItem();
					String valor = produtoView.getTxtPesquisa().getText();
					String criterio = "";
					
				switch (item) {
				case "ID":
					criterio = "id";
					break;
					
                 case "Descrição":
                	 criterio = "descricao";
					break;
					
                 case "Setor":
                	 criterio = "setor";
 					break;

				default:
					break;
				}
					
					List<Produto> produtos = produtoDAO.pesquisarPorCriterio(criterio, valor);
					produtoView.getTable().getItems().clear();
					produtoView.getTable().setItems(FXCollections.observableArrayList(produtos));
					
				
			}
		});
	}



private void handleButtonPesquisarOperacao() {

	produtoView.getBtnPesquisarOperacao().setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Stage stage = new Stage(StageStyle.DECORATED);
			TableView<Operacao> tableOperacao = new TableView<>();
			TableColumn<Operacao, Long> columnOperacaoID = new TableColumn<>("ID");
			TableColumn<Operacao, String> columnOperacaoDescricao = new TableColumn<>("Descricao");
			TableColumn<Operacao, String> columnOperacaoSetor = new TableColumn<>("Setor");
			tableOperacao.setItems(FXCollections.observableArrayList(new OperacaoDAO().listar()));
			columnOperacaoID.setCellValueFactory(new PropertyValueFactory<>("id"));
			columnOperacaoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			columnOperacaoSetor.setCellValueFactory(
					cell -> new SimpleStringProperty(cell.getValue().getSetor().getDescricao()));
			tableOperacao.getColumns().addAll(columnOperacaoID, columnOperacaoDescricao, columnOperacaoSetor);

			tableOperacao.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
						Operacao operacao = tableOperacao.getSelectionModel().getSelectedItem();
						produtoView.getLblIDOperacaoValue().setText(operacao.getId().toString());
						produtoView.getLblDescricaoOperacaoValue().setText(operacao.getDescricao());
						produtoView.getLblSetorOperacaoValue().setText(operacao.getSetor().getDescricao());
						produtoView.getTxtTempoSetup().setDisable(false);
						produtoView.getTxtTempoProducao().setDisable(false);
						produtoView.getTxtTempoFinalizacao().setDisable(false);
						stage.close();
					}

				}
			});

			Label lblPesquisar = new Label("Pesquisar:");
			ComboBox<String> cmbCriterioOperacao = new ComboBox<>(
					FXCollections.observableArrayList("ID", "Descrição", "Sigla"));
			TextField txtPesquisaOperacao = new TextField();
			Button btnEscolher = new Button("Escolher");
			GridPane gridPane = new GridPane();
			gridPane.add(lblPesquisar, 0, 1);
			gridPane.add(cmbCriterioOperacao, 1, 1);
			gridPane.add(txtPesquisaOperacao, 2, 1);
			gridPane.add(btnEscolher, 3, 1);
			gridPane.add(tableOperacao, 0, 2, 3, 1);
			gridPane.setVgap(10);
			gridPane.setHgap(10);
			gridPane.setPadding(new Insets(5, 5, 5, 5));
			gridPane.setHgrow(txtPesquisaOperacao, Priority.ALWAYS);

			BorderPane pane = new BorderPane();
			pane.setPadding(new Insets(0, 5, 10, 5));
			pane.setTop(gridPane);
			pane.setCenter(tableOperacao);

			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();

			btnEscolher.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Operacao operacao = tableOperacao.getSelectionModel().getSelectedItem();
					produtoView.getLblIDOperacaoValue().setText(operacao.getId().toString());
					produtoView.getLblDescricaoOperacaoValue().setText(operacao.getDescricao());
					produtoView.getLblSetorOperacaoValue().setText(operacao.getSetor().getDescricao());
					produtoView.getTxtTempoSetup().setDisable(false);
					produtoView.getTxtTempoProducao().setDisable(false);
					produtoView.getTxtTempoFinalizacao().setDisable(false);
					stage.close();
				}
			});

		}
	});

}






private void handleButtonPesquisarComponente() {

	produtoView.getBtnPesquisarComponente().setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setWidth(700);
			TableView<Componente> tableEstrutura = new TableView<>();
			TableColumn<Componente, Long> columnComponenteID = new TableColumn<>("ID");
			TableColumn<Componente, String> columnComponenteCodigoInterno = new TableColumn<>("Cod. Interno");
			TableColumn<Componente, String> columnComponenteDescricao = new TableColumn<>("Descricao");
			TableColumn<Componente, String> columnComponenteUnidadeMedida = new TableColumn<>("U.M");
			TableColumn<Componente, String> columnComponenteSituacao = new TableColumn<>("Situacao");
			TableColumn<Componente, Double> columnComponenteQuantidade = new TableColumn<>("Qntd.");
			tableEstrutura.setItems(FXCollections.observableArrayList(new ComponenteDAO().listar()));
			columnComponenteID.setCellValueFactory(new PropertyValueFactory<>("id"));
			columnComponenteCodigoInterno.setCellValueFactory(new PropertyValueFactory<>("codigoInterno"));
			columnComponenteDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			columnComponenteUnidadeMedida.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUnidadeMedida().getSigla()));
			columnComponenteSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
			columnComponenteQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
			tableEstrutura.getColumns().addAll(columnComponenteID, columnComponenteCodigoInterno,columnComponenteDescricao, columnComponenteUnidadeMedida,columnComponenteSituacao,columnComponenteQuantidade);

			tableEstrutura.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
						Componente componente = tableEstrutura.getSelectionModel().getSelectedItem();
						produtoView.getLblIDComponenteValue().setText(componente.getId().toString());
						produtoView.getLblCodigoInternoComponenteValue().setText(componente.getCodigoInterno());
						produtoView.getLblDescricaoComponenteValue().setText(componente.getDescricao());
						produtoView.getLblUnidadeComponenteValue().setText(componente.getUnidadeMedida().getSigla());
						produtoView.getLblSituacaoComponenteValue().setText(componente.getSituacao().toString());
						produtoView.getTxtQuantidadeComponente().setDisable(false);
						stage.close();
					}

				}
			});

			Label lblPesquisar = new Label("Pesquisar:");
			ComboBox<String> cmbCriterioOperacao = new ComboBox<>(
					FXCollections.observableArrayList("ID", "Codigo Interno","Descrição","Situacao"));
			TextField txtPesquisaOperacao = new TextField();
			Button btnEscolher = new Button("Escolher");
			GridPane gridPane = new GridPane();
			gridPane.add(lblPesquisar, 0, 1);
			gridPane.add(cmbCriterioOperacao, 1, 1);
			gridPane.add(txtPesquisaOperacao, 2, 1);
			gridPane.add(btnEscolher, 3, 1);
			gridPane.add(tableEstrutura, 0, 2, 3, 1);
			gridPane.setVgap(10);
			gridPane.setHgap(10);
			gridPane.setPadding(new Insets(5, 5, 5, 5));
			gridPane.setHgrow(txtPesquisaOperacao, Priority.ALWAYS);

			BorderPane pane = new BorderPane();
			pane.setPadding(new Insets(0, 5, 10, 5));
			pane.setTop(gridPane);
			pane.setCenter(tableEstrutura);

			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();

			btnEscolher.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Componente componente = tableEstrutura.getSelectionModel().getSelectedItem();
					produtoView.getLblIDComponenteValue().setText(componente.getId().toString());
					produtoView.getLblCodigoInternoComponenteValue().setText(componente.getCodigoInterno());
					produtoView.getLblDescricaoComponenteValue().setText(componente.getDescricao());
					produtoView.getLblUnidadeComponenteValue().setText(componente.getUnidadeMedida().getSigla());
					produtoView.getLblSituacaoComponenteValue().setText(componente.getSituacao().toString());
					produtoView.getTxtQuantidadeComponente().setDisable(false);
					stage.close();
				}
			});

		}
	});

}
	
	
private void handleButtonAddRoteiro() {
	produtoView.getBtnAddRoteiro().setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			if (produtoView.getLblIDOperacaoValue().getText() != "-") {
				Operacao operacao = new OperacaoDAO().pesquisar(Long.parseLong(produtoView.getLblIDOperacaoValue().getText()));
				Long sequencia = (long) (produtoView.getTableRoteiro().getItems().size() + 1);
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
				LocalTime tempoSetup = LocalTime.parse(produtoView.getTxtTempoSetup().getText(),dateTimeFormatter);
				LocalTime tempoProducao = LocalTime.parse(produtoView.getTxtTempoProducao().getText(),dateTimeFormatter);
				LocalTime tempoFinalizacao = LocalTime.parse(produtoView.getTxtTempoFinalizacao().getText(),dateTimeFormatter);
				
				Roteiro roteiro = new Roteiro(sequencia, operacao, tempoSetup, tempoProducao, tempoFinalizacao);
				
				produtoView.getTableRoteiro().getItems().add(roteiro);
			}
			
		}
	});
}



private void handleButtonAddEstrutura() {
produtoView.getBtnAddEstrutura().setOnAction(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent event) {
		if (produtoView.getLblIDComponenteValue().getText() != "-") {
			Componente componente = new ComponenteDAO().pesquisar(Long.parseLong(produtoView.getLblIDComponenteValue().getText()));
			Double quantidade = Double.parseDouble(produtoView.getTxtQuantidadeComponente().getText());
			
			ItemEstrutura item = new ItemEstrutura(componente, quantidade);
			produtoView.getTableEstrutura().getItems().add(item);

		}
		
	}
});
}

	private void limpaCampos() {
		produtoView.getLblIDValue().setText("-");
		produtoView.getTxtCodigoInterno().setText(null);
		produtoView.getTxtValorUnitario().setText(null);
		produtoView.getTxtComprimento().setText(null);
		produtoView.getTxtLargura().setText(null);
		produtoView.getTxtAltura().setText(null);
		produtoView.getTxtPeso().setText(null);
		produtoView.getTxtDescricao().setText(null);
		produtoView.getTxtQuantidadeEstoque().setText(null);
		produtoView.getTxtQuantidadeMinima().setText(null);
		produtoView.getTxtLeadTime().setText(null);
		produtoView.getRadioSituacaoAtivo().setSelected(true);
		produtoView.getCmbUnidadeMedida().valueProperty().set(null);

	}

	private void habilitaCampos() {
		produtoView.getTxtCodigoInterno().setDisable(false);
		produtoView.getTxtValorUnitario().setDisable(false);
		produtoView.getTxtComprimento().setDisable(false);
		produtoView.getTxtLargura().setDisable(false);
		produtoView.getTxtAltura().setDisable(false);
		produtoView.getTxtPeso().setDisable(false);
		produtoView.getTxtDescricao().setDisable(false);
		produtoView.getTxtQuantidadeEstoque().setDisable(false);
		produtoView.getTxtQuantidadeMinima().setDisable(false);
		produtoView.getTxtLeadTime().setDisable(false);
		produtoView.getRadioSituacaoAtivo().setDisable(false);
		produtoView.getRadioSituacaoInativo().setDisable(false);
		produtoView.getRadioSituacaoForaDeLinha().setDisable(false);
		produtoView.getCmbUnidadeMedida().setDisable(false);
	}

	private void desabilitaCampos() {
		produtoView.getTxtCodigoInterno().setDisable(true);
		produtoView.getTxtValorUnitario().setDisable(true);
		produtoView.getTxtComprimento().setDisable(true);
		produtoView.getTxtLargura().setDisable(true);
		produtoView.getTxtAltura().setDisable(true);
		produtoView.getTxtPeso().setDisable(true);
		produtoView.getTxtDescricao().setDisable(true);
		produtoView.getTxtQuantidadeEstoque().setDisable(true);
		produtoView.getTxtQuantidadeMinima().setDisable(true);
		produtoView.getTxtLeadTime().setDisable(true);
		produtoView.getRadioSituacaoAtivo().setDisable(true);
		produtoView.getRadioSituacaoInativo().setDisable(true);
		produtoView.getRadioSituacaoForaDeLinha().setDisable(true);
		produtoView.getCmbUnidadeMedida().setDisable(true);
	}
	

	private void atualizarTabela() {
		produtoView.getTable().getItems().clear();
		ObservableList<Produto> produtos = FXCollections.observableArrayList(produtoDAO.listar());
		produtoView.getTable().getItems().addAll(produtos);
	
	}
}
