import com.mqul.mp.models.Actor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

public class Test {

    private EntityManager entityManager = entityM();

    public EntityManager entityM()
    {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.user", "mqul");
        properties.put("javax.persistence.jdbc.password", "");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "movie", properties);

        return emf.createEntityManager();
    }

    public Actor saveActor(String actorFirstNames) {
        Actor actor = new Actor();
        try {
            entityManager.getTransaction().begin();
            actor.setFirstNames(actorFirstNames);

            actor = entityManager.merge(actor);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return actor;
    }

    public static void main(String... args)
    {
        Test t = new Test();
        t.saveActor("FIRSTACTOR");
    }

}
