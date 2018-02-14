package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.MaterialDAO;
import br.ifsp.edu.pcp.dao.OperacaoDAO;
import br.ifsp.edu.pcp.dao.ProdutoDAO;
import br.ifsp.edu.pcp.dao.SetorDAO;
import br.ifsp.edu.pcp.dao.UnidadeMedidaDAO;
import br.ifsp.edu.pcp.model.Material;
import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.Produto;
import br.ifsp.edu.pcp.model.Roteiro;
import br.ifsp.edu.pcp.model.Setor;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;

public class ProdutoDAOTest {

	private static UnidadeMedidaDAO unidadeMedidaDAO;
	private static MaterialDAO materialDAO;
	private static ProdutoDAO produtoDAO;
	private static UnidadeMedida unidadeMedida;
	private static SetorDAO setorDAO;
	private static OperacaoDAO operacaoDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		unidadeMedidaDAO = new UnidadeMedidaDAO();
		materialDAO = new MaterialDAO();
		produtoDAO = new ProdutoDAO();
		setorDAO = new SetorDAO();
		operacaoDAO = new OperacaoDAO();
		unidadeMedida = new UnidadeMedida("Unidade", "UN");
		unidadeMedidaDAO.salvar(unidadeMedida);
	}

	@Test
	public void salvarProduto() {

		Material material = new Material("CHAPA ACO INOX #20", SituacaoProduto.ATIVO,
				unidadeMedida, 75.00, 5, 100.00, 85.00);

		material.setPeso(0.35);
		material.setAltura(0.2);
		material.setComprimento(300.00);
		material.setLargura(250.00);

		materialDAO.salvar(material);

		Produto produto = new Produto("Refrigerador", SituacaoProduto.ATIVO, unidadeMedida,
				1750.00, 20, 5.00, 2.00);

		produto.setCodigoInterno("RFR-50");
		produto.setPeso(10.00);
		produto.setAltura(1750.00);
		produto.setComprimento(500.00);
		produto.setLargura(450.00);

		produto.adicionarMaterial(material, 2.00);
		
		Setor setor1 = new Setor("CORTE A LASER");
		Setor setor2 = new Setor("DOBRA");
		
		
		setorDAO.salvar(setor1);
		setorDAO.salvar(setor2);
		
		Operacao operacao1 = new Operacao("CORTE A LASER", "CORTAR CONFORME DESENHO", setor1);
		Operacao operacao2 = new Operacao("DOBRA", "DOBRAR 90º", setor2);
		
		operacaoDAO.salvar(operacao1);
		operacaoDAO.salvar(operacao2);
		


		
	   Roteiro roteiro1 = new Roteiro(1L,operacao1, LocalTime.of(02, 05, 00), LocalTime.of(05, 50, 10),  LocalTime.of(02, 10, 05));
	   Roteiro roteiro2 = new Roteiro(2L,operacao2, LocalTime.of(01, 10, 05), LocalTime.of(06, 35, 05),  LocalTime.of(00, 50, 30));
	
	    produto.adicionarRoteiro(roteiro1);
	    produto.adicionarRoteiro(roteiro2);
		
		assertNotNull(produtoDAO.salvar(produto));
	}
	
	

	@Test
	public void alteraProduto() {
		Material material = new Material("CHAPA ACO INOX #18", SituacaoProduto.ATIVO,
				unidadeMedida, 65.00, 7, 75.00, 55.00);

		material.setPeso(0.35);
		material.setAltura(0.2);
		material.setComprimento(300.00);
		material.setLargura(250.00);
		materialDAO.salvar(material);

		Produto produto = new Produto("Refrigerador", SituacaoProduto.ATIVO, unidadeMedida,
				1550.00, 20, 5.00, 2.00);

		produto.setPeso(10.00);
		produto.setAltura(1750.00);
		produto.setComprimento(500.00);
		produto.setLargura(450.00);

		produto.adicionarMaterial(material, 5.00);

		produtoDAO.salvar(produto);

		Produto produtoAlterado = new Produto("Fogao", SituacaoProduto.ATIVO, unidadeMedida,1600.00, 20, 5.00, 2.00);
		produtoAlterado.setCodigoInterno("FG-500");
		produtoAlterado.setAltura(1750.00);
		produtoAlterado.setComprimento(500.00);
		produtoAlterado.setLargura(450.00);
		produtoAlterado.adicionarMaterial(material, 5.00);
		produtoAlterado.setId(produto.getId());
		produtoDAO.atualizar(produtoAlterado);
	}

	@Test
	public void removerProduto() {
		Material material = new Material("PARAFUSO 3/8 \"\" x 100mm", SituacaoProduto.ATIVO,
				unidadeMedida, 95.00, 3, 15.00, 5.00);

		materialDAO.salvar(material);
		Produto produto = new Produto("Prateleira Grande", SituacaoProduto.ATIVO,
				unidadeMedida, 350.00, 20, 5.00, 2.00);

		produto.adicionarMaterial(material, 2.00);

		produtoDAO.salvar(produto);
		produtoDAO.remover(produto.getId());
	}

	@Test
	public void pesquisarProduto() {
		Material material = new Material("ARRUELA 1/2 \"\" x 100mm", SituacaoProduto.ATIVO,
				unidadeMedida, 95.00, 3, 15.00, 5.00);

		materialDAO.salvar(material);

		Produto produto = new Produto("Estante Pequena", SituacaoProduto.ATIVO,
				unidadeMedida, 150.00, 20, 5.00, 2.00);
		produtoDAO.salvar(produto);

		Produto produtoFabricadoEncontrado = produtoDAO.pesquisar(produto.getId());
		assertNotNull(produtoFabricadoEncontrado);
	}

	@Test
	public void listarProdutos() {
		List<Produto> produtos = produtoDAO.listar();
		assertNotNull(produtos);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery("TRUNCATE TABLE estrutura_produto CASCADE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE produto CASCADE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE unidade CASCADE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE setor CASCADE").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();
	}

}
