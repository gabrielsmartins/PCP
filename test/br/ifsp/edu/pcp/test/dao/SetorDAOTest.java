package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.SetorDAO;
import br.ifsp.edu.pcp.model.Setor;

public class SetorDAOTest {

	private static SetorDAO setorDAO;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setorDAO = new SetorDAO();
	}



	@Test
	public void salvarSetor() {
		Setor setor = new Setor("USINAGEM");
		setorDAO.salvar(setor);
		assertNotNull(setorDAO.salvar(setor));
	}
	
	@Test
	public void alterarSetor() {
		Setor setor = new Setor("CORTE");
		setorDAO.salvar(setor);
		Setor alterado = new Setor("CORTE");
		alterado.setId(setor.getId());
		setorDAO.atualizar(alterado);
	}
	
	
	@Test
	public void removerSetor() {
		Setor setor = new Setor("MONTAGEM");
		setorDAO.salvar(setor);
		setorDAO.remover(setor.getId());
	}
	
	@Test
	public void pesquisarSetor() {
	   Setor setor = new Setor("ESTAMPARIA");
	   setorDAO.salvar(setor);
	   Setor encontrado = setorDAO.pesquisar(setor.getId());
	   assertNotNull(encontrado);
	}
	
	@Test
	public void listarSetor() {
		List<Setor> setores = setorDAO.listar();
		assertNotNull(setores);
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery("TRUNCATE TABLE setor CASCADE").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();
		
	}

}
