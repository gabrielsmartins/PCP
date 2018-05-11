package br.ifsp.edu.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import br.ifsp.edu.pcp.dao.UnidadeMedidaDAO;
import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.view.UnidadeMedidaView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class UnidadeController {

	private UnidadeMedidaView unidadeMedidaView;
	private UnidadeMedidaDAO unidadeMedidaDAO;

	public UnidadeController() {
		this.unidadeMedidaView = new UnidadeMedidaView();
		this.unidadeMedidaDAO = new UnidadeMedidaDAO();
		this.handleButtonNovo();
		this.handleButtonSalvar();
		this.handleButtonEditar();
		this.handleButtonExcluir();
		this.handleButtonCancelar();
		this.handleTable();
		this.handleTextPesquisa();
	}

	public UnidadeMedidaView getUnidadeMedidaView() {
		return unidadeMedidaView;
	}

	private void handleButtonNovo() {
		this.getUnidadeMedidaView().getBtnNovo().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				habilitaCampos();
				unidadeMedidaView.getBtnNovo().setDisable(true);
				unidadeMedidaView.getBtnSalvar().setDisable(false);
				unidadeMedidaView.getBtnEditar().setDisable(true);
				unidadeMedidaView.getBtnCancelar().setDisable(false);
			}
		});
	}
	
	private void handleButtonEditar() {
		this.getUnidadeMedidaView().getBtnEditar().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				habilitaCampos();
				unidadeMedidaView.getBtnNovo().setDisable(true);
				unidadeMedidaView.getBtnSalvar().setDisable(false);
				unidadeMedidaView.getBtnExcluir().setDisable(false);
				unidadeMedidaView.getBtnEditar().setDisable(true);
				unidadeMedidaView.getBtnCancelar().setDisable(true);
			}
		});
	}

	private void handleButtonSalvar() {
		this.getUnidadeMedidaView().getBtnSalvar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String id = unidadeMedidaView.getLblIDValue().getText();
				String descricao = unidadeMedidaView.getTxtDescricao().getText().toUpperCase();
				String sigla = unidadeMedidaView.getTxtSigla().getText().toUpperCase();
				

				if (descricao.isEmpty() || descricao == null || sigla.isEmpty() || sigla == null) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Atencao");
					alert.setContentText("Por favor preencha todos os campos");
					alert.show();
				} else {
					UnidadeMedida unidadeMedida = new UnidadeMedida(descricao, sigla);
					if(id != "-") {
						unidadeMedida.setId(Long.parseLong(id));
						unidadeMedidaDAO.atualizar(unidadeMedida);
					}else {
						unidadeMedidaDAO.salvar(unidadeMedida);
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Atencao");
					alert.setContentText("Unidade Salva com Sucesso !!!");
					alert.showAndWait();
					limpaCampos();
					desabilitaCampos();
					atualizarTabela();
				}

			}
		});
	}
	
	private void handleButtonExcluir() {
		this.getUnidadeMedidaView().getBtnExcluir().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(unidadeMedidaView.getLblIDValue().getText() != "-") {
					Long id = Long.parseLong(unidadeMedidaView.getLblIDValue().getText());
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					ButtonType btnSim = new  ButtonType("Sim");
					ButtonType btnNao = new  ButtonType("Não");
					alert.getButtonTypes().setAll(btnSim,btnNao);
					alert.setHeaderText("Atenção");
					alert.setContentText("Deseja realmente excluir?");
					Optional<ButtonType> result = alert.showAndWait();
					if(result.get() == btnSim) {
						try {
							unidadeMedidaDAO.remover(id);
							Alert alertConfirm = new  Alert(AlertType.INFORMATION);
							alertConfirm.setHeaderText("Sucesso");
							alertConfirm.setContentText("Unidade Excluída com Sucesso");
							alertConfirm.show();
						}catch (Exception e) {
							Alert alertError = new Alert(AlertType.ERROR);
							alertError.setHeaderText("Erro");
							alertError.setContentText("Erro ao excluir, unidade já está sendo utilizada por produto ou material");
						}finally {
							atualizarTabela();
						}
					}
					
					
					
				}
				
				
			}
		});
	}

	private void handleButtonCancelar() {
		this.getUnidadeMedidaView().getBtnCancelar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				unidadeMedidaView.getBtnNovo().setDisable(false);
				unidadeMedidaView.getBtnSalvar().setDisable(true);
				unidadeMedidaView.getBtnExcluir().setDisable(true);
				unidadeMedidaView.getBtnEditar().setDisable(true);
				unidadeMedidaView.getBtnCancelar().setDisable(true);
				
			}
		});
	}

	private void handleTable() {
		this.getUnidadeMedidaView().getTable().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (unidadeMedidaView.getTable().getSelectionModel().getSelectedItem() != null) {
					UnidadeMedida unidade = getUnidadeMedidaView().getTable().getSelectionModel().getSelectedItem();
					unidadeMedidaView.getLblIDValue().setText(unidade.getId().toString());
					unidadeMedidaView.getTxtDescricao().setText(unidade.getDescricao());
					unidadeMedidaView.getTxtSigla().setText(unidade.getSigla());
					desabilitaCampos();
					unidadeMedidaView.getBtnNovo().setDisable(false);
					unidadeMedidaView.getBtnSalvar().setDisable(true);
					unidadeMedidaView.getBtnEditar().setDisable(false);
					unidadeMedidaView.getBtnExcluir().setDisable(false);
					unidadeMedidaView.getBtnCancelar().setDisable(true);
				}

			}
		});

	}
	
	private void handleTextPesquisa() {
	   

		
		unidadeMedidaView.getTxtPesquisa().setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 String item = unidadeMedidaView.getCmbCriterio().getSelectionModel().getSelectedItem();
					String valor = unidadeMedidaView.getTxtPesquisa().getText();
					String criterio = "";
					
				switch (item) {
				case "ID":
					criterio = "id";
					break;
					
                 case "Descrição":
                	 criterio = "descricao";
					break;
					
                 case "Sigla":
                	 criterio = "sigla";
 					break;

				default:
					break;
				}
					
					List<UnidadeMedida> unidades = unidadeMedidaDAO.pesquisarPorCriterio(criterio, valor);
				
		
			   
					
					unidadeMedidaView.getTable().getItems().clear();
					unidadeMedidaView.getTable().setItems(FXCollections.observableArrayList(unidades));
					
				
			}
		});
	}

	private void limpaCampos() {
		unidadeMedidaView.getLblIDValue().setText("-");
		unidadeMedidaView.getTxtDescricao().setText(null);
		unidadeMedidaView.getTxtSigla().setText(null);

	}

	private void habilitaCampos() {
		unidadeMedidaView.getTxtDescricao().setDisable(false);
		unidadeMedidaView.getTxtSigla().setDisable(false);
	}

	private void desabilitaCampos() {
		unidadeMedidaView.getTxtDescricao().setDisable(true);
		unidadeMedidaView.getTxtSigla().setDisable(true);
	}
	
	
	private void atualizarTabela() {
		unidadeMedidaView.getTable().getItems().clear();
		ObservableList<UnidadeMedida> unidades = FXCollections.observableArrayList(unidadeMedidaDAO.listar());
		unidadeMedidaView.getTable().getItems().addAll(unidades);
	
	}

}
