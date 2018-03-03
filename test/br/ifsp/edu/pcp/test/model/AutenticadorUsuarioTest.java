package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.PerfilDAO;
import br.ifsp.edu.pcp.dao.UsuarioDAO;
import br.ifsp.edu.pcp.model.AutenticadorUsuario;
import br.ifsp.edu.pcp.model.Perfil;
import br.ifsp.edu.pcp.model.Usuario;

public class AutenticadorUsuarioTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tearDownAfterClass();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery("TRUNCATE TABLE usuario CASCADE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE perfil CASCADE").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();	
	}

	@Test
	public void autenticarUsuario() {
		PerfilDAO perfilDAO = new PerfilDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Perfil perfil = new Perfil("ENGENHARIA");
		perfilDAO.salvar(perfil);
		
		Usuario usuario = new Usuario("Usuario", "usuario", "12345", perfil);
		usuarioDAO.salvar(usuario);
		
		
		AutenticadorUsuario autenticador = new AutenticadorUsuario();
		assertNotNull(autenticador.autenticar(usuario.getLogin(),usuario.getSenha()));
	}

}
