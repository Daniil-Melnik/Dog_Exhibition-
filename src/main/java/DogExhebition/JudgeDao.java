package DogExhebition;

import java.util.ArrayList;
import jakarta.persistence.*;
import org.hibernate.HibernateException;

public class JudgeDao {

    public static Judge deleteJudge(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Judge reJudge = em.find(Judge.class, id);
        em.remove(reJudge);
        em.getTransaction().commit();
        return reJudge;
    }

    public static Judge editJudge(String name, int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Judge edJudge = em.find(Judge.class, id);
        
        edJudge.setName(name);
        
        em.getTransaction().commit();
        return edJudge;
    }

    public static Judge findJudge(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Judge judge = null;
        judge = em.find(Judge.class, id);

        return judge;
    }

    public static int addJudge(String name){
        int res = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Judge newJudge = new Judge();
        
        newJudge.setName(name);
        
        em.persist(newJudge);
        em.getTransaction().commit();
        res = newJudge.getId();
        return res;
    }

    public static ArrayList < Judge > getJudges() {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        	EntityManager em = emf.createEntityManager();
            return new ArrayList<Judge>(em.createQuery("from Judge", Judge.class).getResultList());
        } catch (HibernateException ex) {
        	ex.printStackTrace();
        	return null; 
        }
    }
}
