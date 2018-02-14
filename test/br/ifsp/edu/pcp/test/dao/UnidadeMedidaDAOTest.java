package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.UnidadeMedidaDAO;
import br.ifsp.edu.pcp.model.UnidadeMedida;

public class UnidadeMedidaDAOTest {
	
	private static UnidadeMedidaDAO unidadeMedidaDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		unidadeMedidaDAO = new UnidadeMedidaDAO();
	}

	@Test
	public void salvarUnidadeMedida() {
		UnidadeMedida unidadeMedida = new UnidadeMedida("Unidade", "UN");
		assertNotNull(unidadeMedidaDAO.salvar(unidadeMedida));
	}
	
	@Test
	public void alterarUnidadeMedida() {
		UnidadeMedida unidadeMedida = new UnidadeMedida("Caixa", "CX");
		unidadeMedidaDAO.salvar(unidadeMedida);
		UnidadeMedida unidadeMedidaAlterada = new UnidadeMedida("Caixa", "CX");
		unidadeMedidaAlterada.setId(unidadeMedida.getId());
		unidadeMedidaDAO.atualizar(unidadeMedidaAlterada);
	}
	
	@Test
	public void removerUnidadeMedidal() {
		UnidadeMedida unidadeMedida = new UnidadeMedida("Litro", "L");
		unidadeMedidaDAO.salvar(unidadeMedida);
		unidadeMedidaDAO.remover(unidadeMedida.getId());
	}
	
	@Test
	public void pesquisarUnidadeMedida() {
		UnidadeMedida unidadeMedida = new UnidadeMedida("Quilograma", "KG");
		unidadeMedidaDAO.salvar(unidadeMedida);
		UnidadeMedida unidadeMedidaEncontrada = unidadeMedidaDAO.pesquisar(unidadeMedida.getId());
       assertNotNull(unidadeMedidaEncontrada);
	}
	
	@Test
	public void listarUnidadeMedida() {
		List<UnidadeMedida> unidades = unidadeMedidaDAO.listar();
		assertNotNull(unidades);
	}
	
	
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery("TRUNCATE TABLE unidade CASCADE").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();
		
	}

}
