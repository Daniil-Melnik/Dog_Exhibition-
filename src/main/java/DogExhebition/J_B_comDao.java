package DogExhebition;

import java.util.ArrayList;
import jakarta.persistence.*;
import org.hibernate.HibernateException;

public class J_B_comDao {

    public static J_B_com deleteCom(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        J_B_com reCom = em.find(J_B_com.class, id);
        em.remove(reCom);
        em.getTransaction().commit();
        return reCom;
    }

    public static J_B_com editJudge(Judge judge, Breed breed, int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        J_B_com edCom = em.find(J_B_com.class, id);
        
        edCom.setJudge(judge);
        edCom.setBreed(breed);
        
        em.getTransaction().commit();
        return edCom;
    }

    public static J_B_com findCom(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        J_B_com Com = null;
        Com = em.find(J_B_com.class, id);

        return Com;
    }

    public static int addCom(Judge judge, Breed breed){
        int res = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        J_B_com newCom = new J_B_com();
        
        newCom.setJudge(judge);
        newCom.setBreed(breed);
        
        em.persist(newCom);
        em.getTransaction().commit();
        res = newCom.getId();
        return res;
    }

    public static ArrayList < J_B_com > getComs() {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        	EntityManager em = emf.createEntityManager();
            return new ArrayList<J_B_com>(em.createQuery("from J_B_com", J_B_com.class).getResultList());
        } catch (HibernateException ex) {
        	ex.printStackTrace();
        	return null; 
        }
    }
}