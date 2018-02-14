package br.ifsp.edu.pcp.dao;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static EntityManagerFactory _entityManagerFactory ;
    
    
    public static EntityManager getInstance() {
        try {
            if(_entityManagerFactory == null)
            	_entityManagerFactory  = Persistence.createEntityManagerFactory("pcpPU");
            return _entityManagerFactory.createEntityManager();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
    }
}
