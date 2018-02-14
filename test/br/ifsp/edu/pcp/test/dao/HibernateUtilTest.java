package br.ifsp.edu.pcp.test.dao;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.ifsp.edu.pcp.dao.HibernateUtil;



public class HibernateUtilTest {

	@Test
	public void obterEntityManager() {
		EntityManager entityManager = HibernateUtil.getInstance();
		assertNotNull(entityManager);
	}

}
