package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.PerfilDAO;
import br.ifsp.edu.pcp.model.Perfil;

public class PerfilDAOTest {

	private static PerfilDAO perfilDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		perfilDAO = new PerfilDAO();
	}


	@Test
	public void salvarPerfil() {
		Perfil perfil = new Perfil("PCP");
		assertNotNull(perfilDAO.salvar(perfil));
	}
	
	@Test
	public void alterarPerfil() {
		Perfil perfil = new Perfil("PROGRAMADOR PCP");
		perfilDAO.salvar(perfil);
		Perfil alterado = new Perfil("PRODUCAO");
		alterado.setId(perfil.getId());
		perfilDAO.atualizar(alterado);
	}
	
	@Test
	public void removerPerfil() {
		Perfil perfil = new Perfil("GERENTE PCP");
		perfilDAO.salvar(perfil);
        perfilDAO.remover(perfil.getId());
	}
	
	@Test
	public void pesquisarPerfil() {
		Perfil perfil = new Perfil("PROGRAMADOR PCP");
		perfilDAO.salvar(perfil);
       Perfil encontrado = perfilDAO.pesquisar(perfil.getId());
       assertNotNull(encontrado);
	}
	
	@Test
	public void listarPerfil() {
		List<Perfil> perfis = perfilDAO.listar();
		assertNotNull(perfis);
	}
	
	
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery("TRUNCATE TABLE perfil CASCADE").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();
		
	}
	
	

}
