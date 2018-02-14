package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.PerfilDAO;
import br.ifsp.edu.pcp.dao.UsuarioDAO;
import br.ifsp.edu.pcp.model.Perfil;
import br.ifsp.edu.pcp.model.Usuario;

public class UsuarioDAOTest {

	private static PerfilDAO perfilDAO;
	private static UsuarioDAO usuarioDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		perfilDAO = new PerfilDAO();
		usuarioDAO = new UsuarioDAO();
	}



	@Test
	public void salvarUsuario() {
		Perfil perfil = new Perfil("PCP");
		perfilDAO.salvar(perfil);
		Usuario usuario = new Usuario("Usuario 1", "usuario1", "12345",perfil);
		assertNotNull(usuarioDAO.salvar(usuario));
	}
	
	@Test
	public void alterarUsuario() {
		Perfil perfil = new Perfil("PROGRAMADOR PCP");
		perfilDAO.salvar(perfil);
		Usuario usuario = new Usuario("Usuario 2", "usuario2", "12345",perfil);
		usuarioDAO.salvar(usuario);
		Usuario alterado = new Usuario("Usuario 3","usuario3","12345",perfil);
		alterado.setId(usuario.getId());
		usuarioDAO.atualizar(alterado);
	}
	
	
	@Test
	public void removerUsuario() {
		Perfil perfil = new Perfil("GERENTE PCP");
		perfilDAO.salvar(perfil);
		Usuario usuario = new Usuario("Usuario 4", "usuario4", "12345",perfil);
		usuarioDAO.salvar(usuario);
		usuarioDAO.remover(usuario.getId());
	}
	
	@Test
	public void pesquisarUsuario() {
	   Perfil perfil = new Perfil("PRODUCAO");
	   perfilDAO.salvar(perfil);
	   Usuario usuario = new Usuario("Usuario 5", "usuario5", "12345", perfil);
	   usuarioDAO.salvar(usuario);
	   Usuario encontrado = usuarioDAO.pesquisar(usuario.getId());
       assertNotNull(encontrado);
	}
	
	@Test
	public void listarUsuario() {
		List<Usuario> usuarios = usuarioDAO.listar();
		assertNotNull(usuarios);
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
		

}
