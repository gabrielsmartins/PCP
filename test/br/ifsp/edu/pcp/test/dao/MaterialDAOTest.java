package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.MaterialDAO;
import br.ifsp.edu.pcp.dao.UnidadeMedidaDAO;
import br.ifsp.edu.pcp.model.Material;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;

public class MaterialDAOTest {

	private static UnidadeMedidaDAO unidadeMedidaDAO;
	private static MaterialDAO materialDAO;
	private static UnidadeMedida unidadeMedida;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		materialDAO = new MaterialDAO();
		unidadeMedidaDAO = new UnidadeMedidaDAO();
		unidadeMedida = new UnidadeMedida("Unidade", "UN");
		unidadeMedidaDAO.salvar(unidadeMedida);
	}

	@Test
	public void salvarMaterial() {

		Material material = new Material("CHAPA ACO INOX #20", SituacaoProduto.ATIVO,
				unidadeMedida, 75.00, 5, 100.00, 85.00);

		material.setCodigoInterno("CHI-50");
		material.setPeso(0.2);
		material.setAltura(0.2);
		material.setComprimento(200.00);
		material.setLargura(200.00);
		material.setSituacao(SituacaoProduto.ATIVO);
		assertNotNull(materialDAO.salvar(material));
	}

	@Test
	public void alterarMaterial() {
		Material material = new Material("CHAPA ACO INOX #18", SituacaoProduto.ATIVO,
				unidadeMedida, 65.00, 7, 75.00, 55.00);
		material.setPeso(0.35);
		material.setAltura(0.2);
		material.setComprimento(300.00);
		material.setLargura(250.00);
		material.setSituacao(SituacaoProduto.ATIVO);
		materialDAO.salvar(material);
		
		Material materialAlterado = new Material("CHAPA ACO INOX #10", SituacaoProduto.ATIVO,
				unidadeMedida, 95.00, 3, 15.00, 5.00);
		materialAlterado.setPeso(0.3);
		materialAlterado.setAltura(0.2);
		materialAlterado.setComprimento(350.00);
		materialAlterado.setLargura(150.00);
		materialAlterado.setSituacao(SituacaoProduto.ATIVO);

		materialAlterado.setId(material.getId());
		materialDAO.atualizar(materialAlterado);
	}

	@Test
	public void removerMaterial() {
		Material material = new Material("PARAFUSO 3/8 \"\" x 100mm", SituacaoProduto.ATIVO,unidadeMedida, 95.00, 3, 15.00, 5.00);
		materialDAO.salvar(material);
		materialDAO.remover(material.getId());
	}

	@Test
	public void pesquisarMaterial() {
		Material material = new Material("ARRUELA 1/2 \"\" x 100mm", SituacaoProduto.ATIVO,
				unidadeMedida, 95.00, 3, 15.00, 5.00);
		materialDAO.salvar(material);
		Material materialEncontrado = materialDAO.pesquisar(material.getId());
		assertNotNull(materialEncontrado);
	}

	@Test
	public void listarMateriais() {
		List<Material> materiais = materialDAO.listar();
		assertNotNull(materiais);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery("TRUNCATE TABLE produto CASCADE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE unidade CASCADE").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();

	}

}
