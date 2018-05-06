package br.ifsp.edu.controller;

import java.util.List;

import br.ifsp.edu.pcp.dao.OperacaoDAO;
import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.Recurso;
import br.ifsp.edu.pcp.model.Setor;
import br.ifsp.edu.view.OperacaoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OperacaoController {

	private OperacaoView operacaoView;
	private OperacaoDAO operacaoDAO;

	public OperacaoController() {
		this.operacaoView = new OperacaoView();
		this.operacaoDAO = new OperacaoDAO();
		this.handleButtonNovo();
		this.handleButtonSalvar();
		this.handleButtonEditar();
		this.handleButtonCancelar();
		this.handleTable();
		this.handleTextPesquisa();
	}

	public OperacaoView getRecursoView() {
		return this.operacaoView;
	}

	private void handleButtonNovo() {
		this.getRecursoView().getBtnNovo().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpaCampos();
				habilitaCampos();
				operacaoView.getBtnNovo().setDisable(true);
				operacaoView.getBtnSalvar().setDisable(false);
				operacaoView.getBtnEditar().setDisable(true);
				operacaoView.getBtnCancelar().setDisable(false);
			}
		});
	}
	
	private void handleButtonEditar() {
		this.getRecursoView().getBtnEditar().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				habilitaCampos();
				operacaoView.getBtnNovo().setDisable(true);
				operacaoView.getBtnSalvar().setDisable(false);
				operacaoView.getBtnEditar().setDisable(true);
				operacaoView.getBtnCancelar().setDisable(true);
			}
		});
	}

	private void handleButtonSalvar() {
		this.getRecursoView().getBtnSalvar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String id = operacaoView.getLblIDValue().getText();
				String descricao = operacaoView.getTxtDescricao().getText().toUpperCase();
				String instrucao = operacaoView.getTxtInstrucao().getText().toUpperCase();
				Setor setor = operacaoView.getCmbSetor().getSelectionModel().getSelectedItem();
				

				if (descricao.isEmpty() || descricao == null) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Atencao");
					alert.setContentText("Por favor preencha todos os campos");
					alert.show();
				} else {
					Operacao operacao = new  Operacao(descricao, instrucao, setor);
					
					if(id != "-") {
						setor.setId(Long.parseLong(id));
						operacaoDAO.atualizar(operacao);
					}else {
						operacaoDAO.salvar(operacao);
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Atencao");
					alert.setContentText("Operacao Salva com Sucesso !!!");
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
				operacaoView.getBtnNovo().setDisable(false);
				operacaoView.getBtnSalvar().setDisable(true);
				operacaoView.getBtnEditar().setDisable(true);
				operacaoView.getBtnCancelar().setDisable(true);
				
			}
		});
	}

	private void handleTable() {
		this.getRecursoView().getTable().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (operacaoView.getTable().getSelectionModel().getSelectedItem() != null) {
					Operacao operacao = getRecursoView().getTable().getSelectionModel().getSelectedItem();
					operacaoView.getLblIDValue().setText(operacao.getId().toString());
					operacaoView.getTxtDescricao().setText(operacao.getDescricao());
					operacaoView.getTxtInstrucao().setText(operacao.getInstrucao());
					operacaoView.getCmbSetor().getSelectionModel().select(operacao.getSetor());
					desabilitaCampos();
					operacaoView.getBtnNovo().setDisable(false);
					operacaoView.getBtnSalvar().setDisable(true);
					operacaoView.getBtnEditar().setDisable(false);
					operacaoView.getBtnCancelar().setDisable(true);
				}

			}
		});

	}
	
	private void handleTextPesquisa() {
	   

		
		operacaoView.getTxtPesquisa().setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 String item = operacaoView.getCmbCriterio().getSelectionModel().getSelectedItem();
					String valor = operacaoView.getTxtPesquisa().getText();
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
					
					List<Operacao> operacoes = operacaoDAO.pesquisarPorCriterio(criterio, valor);
				
		
			   
					
					operacaoView.getTable().getItems().clear();
					operacaoView.getTable().setItems(FXCollections.observableArrayList(operacoes));
					
				
			}
		});
	}

	private void limpaCampos() {
		operacaoView.getLblIDValue().setText("-");
		operacaoView.getTxtDescricao().setText(null);
		operacaoView.getTxtInstrucao().setText(null);

	}

	private void habilitaCampos() {
		operacaoView.getTxtDescricao().setDisable(false);
		operacaoView.getTxtInstrucao().setDisable(false);
		operacaoView.getCmbSetor().setDisable(false);
	}

	private void desabilitaCampos() {
		operacaoView.getTxtDescricao().setDisable(true);
		operacaoView.getTxtInstrucao().setDisable(true);
		operacaoView.getCmbSetor().setDisable(true);
	}
	

	private void atualizarTabela() {
		operacaoView.getTable().getItems().clear();
		ObservableList<Operacao> operacoes = FXCollections.observableArrayList(operacaoDAO.listar());
		operacaoView.getTable().getItems().addAll(operacoes);
	
	}
}
