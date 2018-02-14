package br.ifsp.edu.pcp.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;

import br.ifsp.edu.pcp.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Serializable> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario pesquisarPorLoginESenha(String login, String senha) {
		try {
			return this.entityManager.createQuery(
					"SELECT u FROM Usuario u "
					+ "WHERE u.login = :login "
					+ "AND u.senha = :senha",Usuario.class).
					setParameter("login", login).
					setParameter("senha", senha).getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
}
