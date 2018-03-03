package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.RecursoDAO;
import br.ifsp.edu.pcp.dao.SetorDAO;
import br.ifsp.edu.pcp.model.Recurso;
import br.ifsp.edu.pcp.model.Setor;


public class RecursoDAOTest {

	private static SetorDAO setorDAO;
	private static RecursoDAO recursoDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
                tearDownAfterClass();
		setorDAO = new SetorDAO();
		recursoDAO = new RecursoDAO();
	}



	@Test
	public void salvarRecurso() {
		Setor setor = new Setor("ESTAMPARIA");
		setorDAO.salvar(setor);
		Recurso recurso = new Recurso("PRENSA",setor);
		assertNotNull(recursoDAO.salvar(recurso));
	}
	
	@Test
	public void alterarRecurso() {
		Setor setor = new Setor("PINTURA");
		setorDAO.salvar(setor);
		Recurso recurso = new Recurso("OPERADOR 1",setor);
		recursoDAO.salvar(recurso);
		Recurso alterado = new Recurso("OPERADOR 1",setor);
		alterado.setId(recurso.getId());
		recursoDAO.atualizar(alterado);
	}
	
	
	@Test
	public void removerRecurso() {
		Setor setor = new Setor("AJUSTAGEM");
		setorDAO.salvar(setor);
		Recurso recurso = new Recurso("FURADEIRA VERTICAL",setor);
		recursoDAO.salvar(recurso);
		recursoDAO.remover(recurso.getId());
	}
	
	@Test
	public void pesquisarRecurso() {
		Setor setor = new Setor("DOBRA");
	   setorDAO.salvar(setor);
	   Recurso recurso = new Recurso("DOBRADEIRA",setor);
	   recursoDAO.salvar(recurso);
	   Recurso encontrado = recursoDAO.pesquisar(recurso.getId());
       assertNotNull(encontrado);
	}
	
	@Test
	public void listarRecurso() {
		List<Recurso> recursos = recursoDAO.listar();
		assertNotNull(recursos);
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
                entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE recurso").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE setor").executeUpdate();
                entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();
		
	}

}
