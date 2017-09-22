//import com.mqul.mp.Actor;
//import com.mqul.mp.PersonRepo;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Test {
//
//    private EntityManager entityManager = entityM();
//
//    public EntityManager entityM()
//    {
//        Map<String, String> properties = new HashMap<String, String>();
//        properties.put("javax.persistence.jdbc.user", "postgres");
//        properties.put("javax.persistence.jdbc.password", "");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
//                "movie", properties);
//
//        return emf.createEntityManager();
//    }
//
//    public Actor saveActor(String actorFirstNames) {
//        Actor actor = new Actor();
//        try {
//            entityManager.getTransaction().begin();
//            actor.setFirstNames(actorFirstNames);
//
//            actor = entityManager.merge(actor);
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//        }
//        return actor;
//    }
//
//    public static void main(String... args)
//    {
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml");
//
//        PersonRepo repo = context.getBean(PersonRepo.class);
//
//        List<Actor> actors = repo.getActorsByFilmId(2);
//
//        System.out.println("************************************");
//        System.out.println("Actors list");
//        System.out.println("************************************");
//        for(Actor a : actors)
//        {
//            System.out.println(a.getActorID() + "" + a.getFirstNames()
//                              + "" + a.getLastName() + "" + a.getId());
//        }
//        System.out.println("************************************");
//
//        Actor a = new Actor();
//        a.setId(1);
//        a.setActorID("1");
//        a.setFirstNames("firstNames");
//        a.setLastName("lastname");
//
//        repo.addNewActor(a);
//
//        Actor foundAcotr = repo.findActorById(1);
//
//        System.out.println(foundAcotr.getFirstNames());
////        Test t = new Test();
////        t.saveActor("FIRSTACTOR");
//    }
//
//}
