package br.ifsp.edu.pcp.model;

import br.ifsp.edu.pcp.dao.UsuarioDAO;

public class AutenticadorUsuario {

	public Usuario autenticar(String login, String senha) {
		Usuario usuario = new UsuarioDAO().pesquisarPorLoginESenha(login,senha);
		if(usuario != null)
			return usuario;
		return null;
	}

}
