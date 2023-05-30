package DogExhebition;

import java.util.List;
import jakarta.persistence.*;
import org.hibernate.HibernateException;

public class AwardDao {
    public static List < Award > getAwards() {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("rms_persistence");
        	EntityManager em = emf.createEntityManager();
            return em.createQuery("from Award", Award.class).getResultList();
        } catch (HibernateException ex) {
        	ex.printStackTrace();
            System.out.println("ОШИБКА ВЫПОЛНЕНИЯ ПРОГРАММЫ");
        	return null;
        }
    }
}
