package br.ifsp.edu.controller;

import java.util.List;

import br.ifsp.edu.pcp.dao.SetorDAO;
import br.ifsp.edu.pcp.model.Setor;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.view.SetorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SetorController {

	private SetorView setorView;
	private SetorDAO setorDAO;

	public SetorController() {
		this.setorView = new SetorView();
		this.setorDAO = new SetorDAO();
		this.handleButtonNovo();
		this.handleButtonSalvar();
		this.handleButtonEditar();
		this.handleButtonCancelar();
		this.handleTable();
		this.handleTextPesquisa();
	}

	public SetorView getSetorView() {
		return this.setorView;
	}

	private void handleButtonNovo() {
		this.getSetorView().getBtnNovo().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				habilitaCampos();
				setorView.getBtnNovo().setDisable(true);
				setorView.getBtnSalvar().setDisable(false);
				setorView.getBtnEditar().setDisable(true);
				setorView.getBtnCancelar().setDisable(false);
			}
		});
	}
	
	private void handleButtonEditar() {
		this.getSetorView().getBtnEditar().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				habilitaCampos();
				setorView.getBtnNovo().setDisable(true);
				setorView.getBtnSalvar().setDisable(false);
				setorView.getBtnEditar().setDisable(true);
				setorView.getBtnCancelar().setDisable(true);
			}
		});
	}

	private void handleButtonSalvar() {
		this.getSetorView().getBtnSalvar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String id = setorView.getLblIDValue().getText();
				String descricao = setorView.getTxtDescricao().getText().toUpperCase();
				

				if (descricao.isEmpty() || descricao == null) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Atencao");
					alert.setContentText("Por favor preencha todos os campos");
					alert.show();
				} else {
					Setor setor = new Setor(descricao);
					if(id != "-") {
						setor.setId(Long.parseLong(id));
						setorDAO.atualizar(setor);
					}else {
						setorDAO.salvar(setor);
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Atencao");
					alert.setContentText("Setor Salvo com Sucesso !!!");
					alert.showAndWait();
					limpaCampos();
					desabilitaCampos();
					atualizarTabela();
				}

			}
		});
	}

	private void handleButtonCancelar() {
		this.getSetorView().getBtnCancelar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				setorView.getBtnNovo().setDisable(false);
				setorView.getBtnSalvar().setDisable(true);
				setorView.getBtnEditar().setDisable(true);
				setorView.getBtnCancelar().setDisable(true);
				
			}
		});
	}

	private void handleTable() {
		this.getSetorView().getTable().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (setorView.getTable().getSelectionModel().getSelectedItem() != null) {
					Setor setor = getSetorView().getTable().getSelectionModel().getSelectedItem();
					setorView.getLblIDValue().setText(setor.getId().toString());
					setorView.getTxtDescricao().setText(setor.getDescricao());
					desabilitaCampos();
					setorView.getBtnNovo().setDisable(false);
					setorView.getBtnSalvar().setDisable(true);
					setorView.getBtnEditar().setDisable(false);
					setorView.getBtnCancelar().setDisable(true);
				}

			}
		});

	}
	
	private void handleTextPesquisa() {
	   

		
		setorView.getTxtPesquisa().setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 String item = setorView.getCmbCriterio().getSelectionModel().getSelectedItem();
					String valor = setorView.getTxtPesquisa().getText();
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
					
					List<Setor> setores = setorDAO.pesquisarPorCriterio(criterio, valor);
				
		
			   
					
					setorView.getTable().getItems().clear();
					setorView.getTable().setItems(FXCollections.observableArrayList(setores));
					
				
			}
		});
	}

	private void limpaCampos() {
		setorView.getLblIDValue().setText("-");
		setorView.getTxtDescricao().setText(null);

	}

	private void habilitaCampos() {
		setorView.getTxtDescricao().setDisable(false);
	}

	private void desabilitaCampos() {
		setorView.getTxtDescricao().setDisable(true);
	}
	
	
	private void atualizarTabela() {
		setorView.getTable().getItems().clear();
		ObservableList<Setor> setores = FXCollections.observableArrayList(setorDAO.listar());
		setorView.getTable().getItems().addAll(setores);
	
	}

}
