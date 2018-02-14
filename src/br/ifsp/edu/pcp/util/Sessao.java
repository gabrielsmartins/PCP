package br.ifsp.edu.pcp.util;

import br.ifsp.edu.pcp.model.Usuario;

public class Sessao {
	private static Sessao instance = null;
	private Usuario usuario;

	private Sessao() {
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public static Sessao getInstance() {
		if (instance == null) {
			instance = new Sessao();
		}
		return instance;
	}

	public void encerrarSessao() {
		instance = null;

	}
}