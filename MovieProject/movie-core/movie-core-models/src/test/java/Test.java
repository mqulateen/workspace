import com.mqul.mp.Actor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Test {

    @PersistenceContext(unitName = "movie")
    public EntityManager entityManager;

    public static void main(String... args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        //Test bean = context.getBean();

        Actor a = new Actor();
        a.setId(1);
        a.setActorID("1");
        a.setFirstNames("aName");
        a.setLastName("aLastName");
        //entityManager.persist(a);

        //Actor stored = entityManager.find(Actor.class, 1);

        System.out.println("************************************");
        System.out.println("Actors list");
        System.out.println("************************************");

        System.out.println(a.getActorID() + "" + a.getFirstNames()
                              + "" + a.getLastName() + "" + a.getId());

        System.out.println("************************************");
    }
}
