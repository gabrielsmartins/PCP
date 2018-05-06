package br.ifsp.edu.pcp.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;

public abstract class GenericDAO<T, I extends Serializable> {


   protected EntityManager entityManager = HibernateUtil.getInstance();

   private Class<T> persistedClass;

   protected GenericDAO() {
	   
   }

   protected GenericDAO(Class<T> persistedClass) {
       this();
       this.persistedClass = persistedClass;
   }

   public T salvar(T entity) {
       EntityTransaction t = entityManager.getTransaction();
       t.begin();
       entityManager.persist(entity);
       entityManager.flush();
       t.commit();
       return entity;
   }

   public T atualizar(T entity) {
       EntityTransaction t = entityManager.getTransaction();
       t.begin();
       entityManager.merge(entity);
       entityManager.flush();
       t.commit();
       return entity;
   }

   public void remover(I id) {
       T entity = pesquisar(id);
       EntityTransaction t = entityManager.getTransaction();
       t.begin();
       T mergedEntity = entityManager.merge(entity);
       entityManager.remove(mergedEntity);
       entityManager.flush();
       t.commit();
   }
   
   public List<T> pesquisarPorCriterio(String criterio,String valor){
	   TypedQuery<T> query = entityManager.createQuery("SELECT o FROM " + this.persistedClass.getSimpleName() 
		+ " o WHERE o." + criterio +" IS NOT NULL AND o." + criterio +" LIKE UPPER(:valor)",this.persistedClass);
	   query.setParameter("valor", valor+"%");
	   
	   try {
		   List<T> dados = query.getResultList();
		   return dados;
	   }catch(NoResultException ex) {
		   System.out.println(ex.getStackTrace());
	   }
	  return null;
   }

   public List<T> listar() {
       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
       CriteriaQuery<T> query = builder.createQuery(persistedClass);
       query.from(persistedClass);
       return entityManager.createQuery(query).getResultList();
   }

   public T pesquisar(I id) {
       return entityManager.find(persistedClass, id);
   }
}