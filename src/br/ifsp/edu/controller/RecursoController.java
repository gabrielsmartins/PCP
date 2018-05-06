package br.ifsp.edu.controller;

import java.util.List;

import br.ifsp.edu.pcp.dao.RecursoDAO;
import br.ifsp.edu.pcp.model.Recurso;
import br.ifsp.edu.pcp.model.Setor;
import br.ifsp.edu.view.RecursoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RecursoController {

	private RecursoView recursoView;
	private RecursoDAO recursoDAO;

	public RecursoController() {
		this.recursoView = new RecursoView();
		this.recursoDAO = new RecursoDAO();
		this.handleButtonNovo();
		this.handleButtonSalvar();
		this.handleButtonEditar();
		this.handleButtonCancelar();
		this.handleTable();
		this.handleTextPesquisa();
	}

	public RecursoView getRecursoView() {
		return this.recursoView;
	}

	private void handleButtonNovo() {
		this.getRecursoView().getBtnNovo().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				habilitaCampos();
				recursoView.getBtnNovo().setDisable(true);
				recursoView.getBtnSalvar().setDisable(false);
				recursoView.getBtnEditar().setDisable(true);
				recursoView.getBtnCancelar().setDisable(false);
			}
		});
	}
	
	private void handleButtonEditar() {
		this.getRecursoView().getBtnEditar().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				habilitaCampos();
				recursoView.getBtnNovo().setDisable(true);
				recursoView.getBtnSalvar().setDisable(false);
				recursoView.getBtnEditar().setDisable(true);
				recursoView.getBtnCancelar().setDisable(true);
			}
		});
	}

	private void handleButtonSalvar() {
		this.getRecursoView().getBtnSalvar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String id = recursoView.getLblIDValue().getText();
				String descricao = recursoView.getTxtDescricao().getText().toUpperCase();
				Setor setor = recursoView.getCmbSetor().getSelectionModel().getSelectedItem();
				

				if (descricao.isEmpty() || descricao == null) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Atencao");
					alert.setContentText("Por favor preencha todos os campos");
					alert.show();
				} else {
					Recurso recurso = new Recurso(descricao, setor);
					
					if(id != "-") {
						setor.setId(Long.parseLong(id));
						recursoDAO.atualizar(recurso);
					}else {
						recursoDAO.salvar(recurso);
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Atencao");
					alert.setContentText("Recurso Salvo com Sucesso !!!");
					alert.showAndWait();
					limpaCampos();
					desabilitaCampos();
					atualizarTabela();
				}

			}
		});
	}

	private void handleButtonCancelar() {
		this.getRecursoView().getBtnCancelar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				recursoView.getBtnNovo().setDisable(false);
				recursoView.getBtnSalvar().setDisable(true);
				recursoView.getBtnEditar().setDisable(true);
				recursoView.getBtnCancelar().setDisable(true);
				
			}
		});
	}

	private void handleTable() {
		this.getRecursoView().getTable().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (recursoView.getTable().getSelectionModel().getSelectedItem() != null) {
					Recurso recurso = getRecursoView().getTable().getSelectionModel().getSelectedItem();
					recursoView.getLblIDValue().setText(recurso.getId().toString());
					recursoView.getTxtDescricao().setText(recurso.getDescricao());
					recursoView.getCmbSetor().getSelectionModel().select(recurso.getSetor());
					desabilitaCampos();
					recursoView.getBtnNovo().setDisable(false);
					recursoView.getBtnSalvar().setDisable(true);
					recursoView.getBtnEditar().setDisable(false);
					recursoView.getBtnCancelar().setDisable(true);
				}

			}
		});

	}
	
	private void handleTextPesquisa() {
	   

		
		recursoView.getTxtPesquisa().setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 String item = recursoView.getCmbCriterio().getSelectionModel().getSelectedItem();
					String valor = recursoView.getTxtPesquisa().getText();
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
					
					List<Recurso> recursos = recursoDAO.pesquisarPorCriterio(criterio, valor);
				
		
			   
					
					recursoView.getTable().getItems().clear();
					recursoView.getTable().setItems(FXCollections.observableArrayList(recursos));
					
				
			}
		});
	}

	private void limpaCampos() {
		recursoView.getLblIDValue().setText("-");
		recursoView.getTxtDescricao().setText(null);

	}

	private void habilitaCampos() {
		recursoView.getTxtDescricao().setDisable(false);
		recursoView.getCmbSetor().setDisable(false);
	}

	private void desabilitaCampos() {
		recursoView.getTxtDescricao().setDisable(true);
		recursoView.getCmbSetor().setDisable(true);
	}
	
	private void atualizarTabela() {
		recursoView.getTable().getItems().clear();
		ObservableList<Recurso> recursos = FXCollections.observableArrayList(recursoDAO.listar());
		recursoView.getTable().getItems().addAll(recursos);
	
	}

}
