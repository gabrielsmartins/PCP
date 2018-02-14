package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;
import br.ifsp.edu.pcp.dao.OperacaoDAO;
import br.ifsp.edu.pcp.dao.SetorDAO;
import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.Setor;

public class OperacaoDAOTest {

	private static SetorDAO setorDAO;
	private static OperacaoDAO operacaoDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setorDAO = new SetorDAO();
		operacaoDAO = new OperacaoDAO();
	}



	@Test
	public void salvarOperacao() {
		Setor setor = new Setor("USINAGEM");
		setorDAO.salvar(setor);
		Operacao operacao = new Operacao("TORNEAR", "TORNEAR NO COMPRIMENTO", setor);
		assertNotNull(operacaoDAO.salvar(operacao));
	}
	
	@Test
	public void alterarOperacao() {
		Setor setor = new Setor("CORTE");
		setorDAO.salvar(setor);
		Operacao operacao = new Operacao("CORTAR", "CORTAR NA MEDIDA", setor);
		operacaoDAO.salvar(operacao);
		Operacao alterado = new Operacao("CORTE PERSONALIZADO", "CORTAR LATERAIS", setor);
		alterado.setId(setor.getId());
		operacaoDAO.atualizar(alterado);
	}
	
	
	@Test
	public void removerOperacao() {
		Setor setor = new Setor("MONTAGEM");
		setorDAO.salvar(setor);
		Operacao operacao = new Operacao("MONTAR", "MONTAR CONFORME DESENHO", setor);
		operacaoDAO.salvar(operacao);
		operacaoDAO.remover(operacao.getId());
	}
	
	@Test
	public void pesquisarOperacao() {
	   Setor setor = new Setor("DOBRA");
	   setorDAO.salvar(setor);
	   Operacao operacao = new Operacao("DOBRAR", "DOBRAR 90�", setor);
	   operacaoDAO.salvar(operacao);
	   Operacao encontrada = operacaoDAO.pesquisar(operacao.getId());
       assertNotNull(encontrada);
	}
	
	@Test
	public void listarOperacao() {
		List<Operacao> operacoes = operacaoDAO.listar();
		assertNotNull(operacoes);
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManager entityManager = HibernateUtil.getInstance();
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery("TRUNCATE TABLE operacao CASCADE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE setor CASCADE").executeUpdate();
		entityManager.flush();
		entityManager.getTransaction().commit();
		
	}

}
