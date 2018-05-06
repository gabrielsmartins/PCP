package br.ifsp.edu.controller;

import java.util.List;

import br.ifsp.edu.pcp.dao.ProdutoDAO;
import br.ifsp.edu.pcp.model.Produto;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.view.ProdutoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
		this.handleTextPesquisa();
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
