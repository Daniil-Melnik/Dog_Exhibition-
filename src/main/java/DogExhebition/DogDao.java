package DogExhebition;

import java.util.List;
import jakarta.persistence.*;
import org.hibernate.HibernateException;

public class DogDao {

    public static Dog deleteDog(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dog reDog = em.find(Dog.class, id);
        em.remove(reDog);
        em.getTransaction().commit();
        return reDog;
    }

    public static Dog editDog(String name, Breed breed, Award award, int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dog edDog = em.find(Dog.class, id);
        
        edDog.setName(name);
        edDog.setAward(award);
        edDog.setBreed(breed);
        
        em.getTransaction().commit();
        return edDog;
    }

    public static Dog findDog(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dog dog = null;
        dog = em.find(Dog.class, id);

        return dog;
    }

    public static int addDog(String name, Breed breed, Award award, Owner owner){
        int res = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dog newDog = new Dog();
        
        newDog.setName(name);
        newDog.setAward(award);
        newDog.setBreed(breed);
        newDog.setOwner(owner);
        
        em.persist(newDog);
        em.getTransaction().commit();
        res = newDog.getId();
        return res;
    }

    

    public static List < Dog > getDog() {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        	EntityManager em = emf.createEntityManager();
            return em.createQuery("from Dog", Dog.class).getResultList();
        } catch (HibernateException ex) {
        	ex.printStackTrace();
        	return null;
        }
    }
    
}
