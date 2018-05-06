package br.ifsp.edu.controller;

import java.util.List;

import br.ifsp.edu.pcp.dao.MaterialDAO;
import br.ifsp.edu.pcp.model.Material;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.view.MaterialView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MaterialController {

	private MaterialView materialView;
	private MaterialDAO materialDAO;

	public MaterialController() {
		this.materialView = new MaterialView();
		this.materialDAO = new MaterialDAO();
		this.handleButtonNovo();
		this.handleButtonSalvar();
		this.handleButtonEditar();
		this.handleButtonCancelar();
		this.handleTable();
		this.handleTextPesquisa();
	}

	public MaterialView getMaterialView() {
		return this.materialView;
	}

	private void handleButtonNovo() {
		this.getMaterialView().getBtnNovo().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				habilitaCampos();
				materialView.getBtnNovo().setDisable(true);
				materialView.getBtnSalvar().setDisable(false);
				materialView.getBtnEditar().setDisable(true);
				materialView.getBtnCancelar().setDisable(false);
			}
		});
	}
	
	private void handleButtonEditar() {
		this.getMaterialView().getBtnEditar().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				habilitaCampos();
				materialView.getBtnNovo().setDisable(true);
				materialView.getBtnSalvar().setDisable(false);
				materialView.getBtnEditar().setDisable(true);
				materialView.getBtnCancelar().setDisable(true);
			}
		});
	}

	private void handleButtonSalvar() {
		this.getMaterialView().getBtnSalvar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String id = materialView.getLblIDValue().getText();
				String codigoInterno = materialView.getTxtCodigoInterno().getText().toUpperCase();
				String descricao = materialView.getTxtDescricao().getText().toUpperCase();
				UnidadeMedida unidadeMedida = materialView.getCmbUnidadeMedida().getSelectionModel().getSelectedItem();
				Double valorUnitario = Double.parseDouble(materialView.getTxtValorUnitario().getText());
				Integer leadTime = Integer.parseInt(materialView.getTxtLeadTime().getText());
				Double quantidadeEstoque = Double.parseDouble(materialView.getTxtQuantidadeEstoque().getText());
				Double quantidadeMinima = Double.parseDouble(materialView.getTxtQuantidadeMinima().getText());
				SituacaoProduto situacaoProduto = (SituacaoProduto) materialView.getGroupSituacao().getSelectedToggle().getUserData();
				Double peso = Double.parseDouble(materialView.getTxtPeso().getText());
				Double comprimento = Double.parseDouble(materialView.getTxtComprimento().getText());
				Double largura = Double.parseDouble(materialView.getTxtLargura().getText());
				Double altura = Double.parseDouble(materialView.getTxtAltura().getText());
		       
				

				if (descricao.isEmpty() || descricao == null) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Atencao");
					alert.setContentText("Por favor preencha todos os campos");
					alert.show();
				} else {
					Material material = new Material(descricao, situacaoProduto, unidadeMedida, valorUnitario, leadTime, quantidadeEstoque, quantidadeMinima);
					material.setCodigoInterno(codigoInterno);
					material.setPeso(peso);
				    material.setComprimento(comprimento);
				    material.setLargura(largura);
					material.setAltura(altura);
					if(id != "-") {
						material.setId(Long.parseLong(id));
						materialDAO.atualizar(material);
					}else {
						materialDAO.salvar(material);
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Atencao");
					alert.setContentText("Material Salvo com Sucesso !!!");
					alert.showAndWait();
					limpaCampos();
					desabilitaCampos();
					atualizarTabela();
				}

			}
		});
	}

	private void handleButtonCancelar() {
		this.getMaterialView().getBtnCancelar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				materialView.getBtnNovo().setDisable(false);
				materialView.getBtnSalvar().setDisable(true);
				materialView.getBtnEditar().setDisable(true);
				materialView.getBtnCancelar().setDisable(true);
				
			}
		});
	}

	private void handleTable() {
		this.getMaterialView().getTable().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (materialView.getTable().getSelectionModel().getSelectedItem() != null) {
					Material material = getMaterialView().getTable().getSelectionModel().getSelectedItem();
					materialView.getLblIDValue().setText(material.getId().toString());
					materialView.getTxtCodigoInterno().setText(material.getCodigoInterno());
					materialView.getTxtDescricao().setText(material.getDescricao());
					materialView.getTxtValorUnitario().setText(material.getValorUnitario().toString());
					materialView.getTxtPeso().setText(material.getPeso().toString());
					materialView.getTxtComprimento().setText(material.getComprimento().toString());
					materialView.getTxtLargura().setText(material.getLargura().toString());
					materialView.getTxtAltura().setText(material.getAltura().toString());
					materialView.getTxtAltura().setText(material.getAltura().toString());
					materialView.getTxtQuantidadeEstoque().setText(material.getQuantidadeEstoque().toString());
					materialView.getTxtQuantidadeMinima().setText(material.getQuantidadeMinima().toString());
					materialView.getTxtLeadTime().setText(material.getLeadTime().toString());
					materialView.getCmbUnidadeMedida().getSelectionModel().select(material.getUnidadeMedida());
					
					SituacaoProduto situacao = material.getSituacao();
					
					if(situacao == SituacaoProduto.ATIVO) {
						materialView.getRadioSituacaoAtivo().setSelected(true);
					}else if(situacao == SituacaoProduto.INATIVO) {
						materialView.getRadioSituacaoInativo().setSelected(true);
					}else {
						materialView.getRadioSituacaoForaDeLinha().setSelected(true);
					}
					
					desabilitaCampos();
					materialView.getBtnNovo().setDisable(false);
					materialView.getBtnSalvar().setDisable(true);
					materialView.getBtnEditar().setDisable(false);
					materialView.getBtnCancelar().setDisable(true);
				}

			}
		});

	}
	
	private void handleTextPesquisa() {
	   

		
		materialView.getTxtPesquisa().setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 String item = materialView.getCmbCriterio().getSelectionModel().getSelectedItem();
					String valor = materialView.getTxtPesquisa().getText();
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
					
					List<Material> materiais = materialDAO.pesquisarPorCriterio(criterio, valor);
					materialView.getTable().getItems().clear();
					materialView.getTable().setItems(FXCollections.observableArrayList(materiais));
					
				
			}
		});
	}

	private void limpaCampos() {
		materialView.getLblIDValue().setText("-");
		materialView.getTxtCodigoInterno().setText(null);
		materialView.getTxtValorUnitario().setText(null);
		materialView.getTxtComprimento().setText(null);
		materialView.getTxtLargura().setText(null);
		materialView.getTxtAltura().setText(null);
		materialView.getTxtPeso().setText(null);
		materialView.getTxtDescricao().setText(null);
		materialView.getTxtQuantidadeEstoque().setText(null);
		materialView.getTxtQuantidadeMinima().setText(null);
		materialView.getTxtLeadTime().setText(null);
		materialView.getRadioSituacaoAtivo().setSelected(true);
		materialView.getCmbUnidadeMedida().valueProperty().set(null);

	}

	private void habilitaCampos() {
		materialView.getTxtCodigoInterno().setDisable(false);
		materialView.getTxtValorUnitario().setDisable(false);
		materialView.getTxtComprimento().setDisable(false);
		materialView.getTxtLargura().setDisable(false);
		materialView.getTxtAltura().setDisable(false);
		materialView.getTxtPeso().setDisable(false);
		materialView.getTxtDescricao().setDisable(false);
		materialView.getTxtQuantidadeEstoque().setDisable(false);
		materialView.getTxtQuantidadeMinima().setDisable(false);
		materialView.getTxtLeadTime().setDisable(false);
		materialView.getRadioSituacaoAtivo().setDisable(false);
		materialView.getRadioSituacaoInativo().setDisable(false);
		materialView.getRadioSituacaoForaDeLinha().setDisable(false);
		materialView.getCmbUnidadeMedida().setDisable(false);
	}

	private void desabilitaCampos() {
		materialView.getTxtCodigoInterno().setDisable(true);
		materialView.getTxtValorUnitario().setDisable(true);
		materialView.getTxtComprimento().setDisable(true);
		materialView.getTxtLargura().setDisable(true);
		materialView.getTxtAltura().setDisable(true);
		materialView.getTxtPeso().setDisable(true);
		materialView.getTxtDescricao().setDisable(true);
		materialView.getTxtQuantidadeEstoque().setDisable(true);
		materialView.getTxtQuantidadeMinima().setDisable(true);
		materialView.getTxtLeadTime().setDisable(true);
		materialView.getRadioSituacaoAtivo().setDisable(true);
		materialView.getRadioSituacaoInativo().setDisable(true);
		materialView.getRadioSituacaoForaDeLinha().setDisable(true);
		materialView.getCmbUnidadeMedida().setDisable(true);
	}
	

	private void atualizarTabela() {
		materialView.getTable().getItems().clear();
		ObservableList<Material> materiais = FXCollections.observableArrayList(materialDAO.listar());
		materialView.getTable().getItems().addAll(materiais);
	
	}
}
