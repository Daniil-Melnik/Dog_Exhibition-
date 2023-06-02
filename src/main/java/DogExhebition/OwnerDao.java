package DogExhebition;

import java.util.List;
import jakarta.persistence.*;
import org.hibernate.HibernateException;

public class OwnerDao {

    public static Owner deleteOwner(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Owner reOwner = em.find(Owner.class, id);
        em.remove(reOwner);
        em.getTransaction().commit();
        return reOwner;
    }

    public static Owner editOwner(String name, Dog dog, int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Owner edOwner = em.find(Owner.class, id);
        
        edOwner.setName(name);
        //edOwner.setDog(dog);
        
        em.getTransaction().commit();
        return edOwner;
    }

    public static Owner findOwner(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Owner owner = null;
        owner = em.find(Owner.class, id);

        return owner;
    }

    public static int addOwner(String name, Dog dog){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Owner newOwner = new Owner();
        
        newOwner.setName(name);
        //newOwner.setDog(dog);
        
        em.persist(newOwner);
        em.getTransaction().commit();

        return newOwner.getId();
    }
    public static List < Owner > getOwners() {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        	EntityManager em = emf.createEntityManager();
            return em.createQuery("from Owner", Owner.class).getResultList();
        } catch (HibernateException ex) {
        	ex.printStackTrace();
        	return null;
        }
    }
}
