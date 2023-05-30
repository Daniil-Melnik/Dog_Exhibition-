package DogExhebition;

import java.util.List;
import jakarta.persistence.*;
import org.hibernate.HibernateException;


public class BreedDao {

    public static Breed deleteBreed(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Breed reBreed = em.find(Breed.class, id);
        em.remove(reBreed);
        em.getTransaction().commit();
        return reBreed;
    }

    public static Breed findBreed(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Breed breed = null;
        breed = em.find(Breed.class, id);

        return breed;
    }

    public static int addBreed(String title){
        int res = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Breed newBreed = new Breed();
        
        newBreed.setTitle(title);
        
        em.persist(newBreed);
        em.getTransaction().commit();
        res = newBreed.getId();
        return res;
    }

    public static List < Breed > getBreeds() {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        	EntityManager em = emf.createEntityManager();
            return em.createQuery("from Breed", Breed.class).getResultList();
        } catch (HibernateException ex) {
        	ex.printStackTrace();
            System.out.println("ОШИБКА ВЫПОЛНЕНИЯ ПРОГРАММЫ");
        	return null; 
        }
    }
}
