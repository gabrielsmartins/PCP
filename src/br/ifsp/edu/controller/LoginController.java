package br.ifsp.edu.controller;

import br.ifsp.edu.pcp.dao.UsuarioDAO;
import br.ifsp.edu.pcp.model.Usuario;
import br.ifsp.edu.view.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

	private UsuarioDAO usuarioDAO;
	private LoginView loginView;

	
	
	public LoginController() {
		this.usuarioDAO = new UsuarioDAO();
		this.loginView = new LoginView();
		this.carregaAcaoLogin();
		this.carregaAcaoSair();
	}
	

	public LoginView getLoginView() {
		return loginView;
	}
	
	private void carregaAcaoLogin() {
		this.loginView.getBtnLogin().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				String login = loginView.getTxtUsuario().getText().trim();
				String senha = loginView.getTxtSenha().getText().trim();
				Usuario usuario = usuarioDAO.pesquisarPorLoginESenha(login, senha);
				if(usuario != null) {
					new MenuController().getMenuView().show();
					loginView.close();
				}else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setContentText("Usuario ou Senha Invalidos" + login + senha);
					alert.show();
				}
			}
		});
	}
	
	private void carregaAcaoSair() {
		this.loginView.getBtnSair().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			loginView.close();
				
			}
		});
	}
	
	
	
	
}
