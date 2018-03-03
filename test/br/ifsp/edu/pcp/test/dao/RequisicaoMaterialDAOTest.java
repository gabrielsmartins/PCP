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
import br.ifsp.edu.pcp.dao.MaterialDAO;
import br.ifsp.edu.pcp.dao.PerfilDAO;
import br.ifsp.edu.pcp.dao.RequisicaoMaterialDAO;
import br.ifsp.edu.pcp.dao.UnidadeMedidaDAO;
import br.ifsp.edu.pcp.dao.UsuarioDAO;
import br.ifsp.edu.pcp.model.Material;
import br.ifsp.edu.pcp.model.Perfil;
import br.ifsp.edu.pcp.model.RequisicaoMaterial;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.StatusRequisicaoMaterial;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.pcp.model.Usuario;

public class RequisicaoMaterialDAOTest {

	private static RequisicaoMaterialDAO requisicaoMaterialDAO;
	private static MaterialDAO materialDAO;
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
		requisicaoMaterialDAO = new RequisicaoMaterialDAO();
		materialDAO = new MaterialDAO();
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
	public void salvarRequisicaoMaterial() {
		Material material = new Material("BARRA ACO INOX", SituacaoProduto.ATIVO, unidadeMedida, 500.50, 2, 10.00, 5.00);
		materialDAO.salvar(material);
		RequisicaoMaterial requisicaoMaterial = new RequisicaoMaterial(LocalDate.parse("30/03/2018", formatter), usuario);
		requisicaoMaterial.adicionarMaterial(material, 5.00);
		assertNotNull(requisicaoMaterialDAO.salvar(requisicaoMaterial));
	}
	
	
	@Test
	public void alterarRequisicaoMaterial() {
		Material material = new Material("TUBO DE ALUMINIO", SituacaoProduto.ATIVO, unidadeMedida, 500.50, 2, 10.00, 5.00);
		materialDAO.salvar(material);
		RequisicaoMaterial requisicaoMaterial = new RequisicaoMaterial(LocalDate.parse("02/04/2018", formatter), usuario);
		requisicaoMaterial.adicionarMaterial(material, 2.00);
		requisicaoMaterialDAO.salvar(requisicaoMaterial);
		
		
		RequisicaoMaterial requisicaoMaterialAlterada = new RequisicaoMaterial(LocalDate.parse("10/04/2018", formatter), usuario);
		requisicaoMaterialAlterada.setStatus(StatusRequisicaoMaterial.CANCELADA);
		requisicaoMaterialAlterada.setId(requisicaoMaterial.getId());
		
		requisicaoMaterialDAO.atualizar(requisicaoMaterialAlterada);
	}
	
	
	
	@Test
	public void pesquisarRequisicaoMaterial() {
		Material material = new Material("TUBO DE ACO INOX", SituacaoProduto.ATIVO, unidadeMedida, 300.50, 2, 10.00, 5.00);
		materialDAO.salvar(material);
		RequisicaoMaterial requisicaoMaterial = new RequisicaoMaterial(LocalDate.parse("02/05/2018", formatter), usuario);
		requisicaoMaterial.adicionarMaterial(material, 3.00);
		requisicaoMaterialDAO.salvar(requisicaoMaterial);
		RequisicaoMaterial encontrada = requisicaoMaterialDAO.pesquisar(requisicaoMaterial.getId());
       assertNotNull(encontrada);
	}
	
	
	@Test
	public void listarRequisicaoMaterial() {
		List<RequisicaoMaterial> requisicoes = requisicaoMaterialDAO.listar();
		assertNotNull(requisicoes);
	}
	
	
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
                entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
                entityManager.createNativeQuery("TRUNCATE TABLE requisicao_material_detalhe").executeUpdate();
                entityManager.createNativeQuery("TRUNCATE TABLE requisicao_material").executeUpdate();
                entityManager.createNativeQuery("TRUNCATE TABLE perfil").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE usuario").executeUpdate();
                entityManager.createNativeQuery("TRUNCATE TABLE produto").executeUpdate();
                entityManager.createNativeQuery("TRUNCATE TABLE unidade").executeUpdate();
                entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();
		
	}


}
