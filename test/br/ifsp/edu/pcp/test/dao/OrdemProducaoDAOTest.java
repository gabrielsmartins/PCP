package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.OrdemProducaoDAO;
import br.ifsp.edu.pcp.dao.PerfilDAO;
import br.ifsp.edu.pcp.dao.ProdutoDAO;
import br.ifsp.edu.pcp.dao.UnidadeMedidaDAO;
import br.ifsp.edu.pcp.dao.UsuarioDAO;
import br.ifsp.edu.pcp.model.OrdemProducao;
import br.ifsp.edu.pcp.model.Perfil;
import br.ifsp.edu.pcp.model.Produto;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.StatusOrdemProducao;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.pcp.model.Usuario;

public class OrdemProducaoDAOTest {
	
	private static OrdemProducaoDAO ordemProducaoDAO;
	private static ProdutoDAO produtoDAO;
	private static UnidadeMedidaDAO unidadeMedidaDAO;
	private static PerfilDAO perfilDAO;
	private static UsuarioDAO usuarioDAO;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	private static Perfil perfil;
	private static Usuario usuario;
	private static UnidadeMedida unidadeMedida;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
                tearDownAfterClass();
		ordemProducaoDAO = new OrdemProducaoDAO();
		produtoDAO = new ProdutoDAO();
		unidadeMedidaDAO = new UnidadeMedidaDAO();
		perfilDAO = new PerfilDAO();
		usuarioDAO = new UsuarioDAO();
		
		 perfil = new Perfil("PCP");
		 usuario = new Usuario("Admin", "admin", "12345", perfil);
		 unidadeMedida = new UnidadeMedida("UNIDADE", "UN");
		 
		 perfilDAO.salvar(perfil);
		 usuarioDAO.salvar(usuario);
		 unidadeMedidaDAO.salvar(unidadeMedida);
		 
	}


	@Test
	public void salvarOrdemProducao() {
		Produto produto = new Produto("CADEIRA", SituacaoProduto.ATIVO, unidadeMedida, 50.50, 2, 10.00, 5.00);
		produtoDAO.salvar(produto);
		OrdemProducao ordemProducao = new OrdemProducao(produto , LocalDate.parse("25/03/2018",formatter), usuario);
		assertNotNull(ordemProducaoDAO.salvar(ordemProducao));
	}
	
	
	@Test
	public void alterarOrdemProducao() {
		Produto produto = new Produto("MESA", SituacaoProduto.ATIVO, unidadeMedida, 50.50, 2, 10.00, 5.00);
		produtoDAO.salvar(produto);
		OrdemProducao ordemProducao = new OrdemProducao(produto , LocalDate.parse("25/03/2018",formatter), usuario);
		ordemProducaoDAO.salvar(ordemProducao);
		
		
		OrdemProducao ordemProducaoAlterada = new OrdemProducao(produto , LocalDate.parse("30/03/2018",formatter), usuario);
		ordemProducao.setStatus(StatusOrdemProducao.CANCELADA);
		ordemProducaoAlterada.setId(ordemProducao.getId());
		
		ordemProducaoDAO.atualizar(ordemProducaoAlterada);
	}
	
	
	
	@Test
	public void pesquisarOrdemProducao() {
		Produto produto = new Produto("BICICLETA", SituacaoProduto.ATIVO, unidadeMedida, 50.50, 2, 10.00, 5.00);
		produtoDAO.salvar(produto);
		OrdemProducao ordemProducao = new OrdemProducao(produto , LocalDate.parse("25/03/2018",formatter), usuario);
		ordemProducaoDAO.salvar(ordemProducao);
		OrdemProducao encontrada = ordemProducaoDAO.pesquisar(ordemProducao.getId());
       assertNotNull(encontrada);
	}
	
	@Test
	public void listarOrdemProducao() {
		List<OrdemProducao> ordens = ordemProducaoDAO.listar();
		assertNotNull(ordens);
	}
	
	
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
                entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE ordem_producao").executeUpdate();
                entityManager.createNativeQuery("TRUNCATE TABLE unidade").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE perfil").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE usuario").executeUpdate();
                entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();
		
	}

}
