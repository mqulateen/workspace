//import com.mqul.mp.models.*;
//import org.junit.Assert;
//import org.junit.Test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class EntityTest {
//
//    Logger logger = Logger.getLogger(EntityTest.class.getName());
//
//    EntityManagerFactory factory =
//            Persistence.createEntityManagerFactory("movie");
//
//    EntityManager entityManager = factory.createEntityManager();
//
//    //@Mock
//
//
//    @Test
//    public void testActorEntity()
//    {
//        final Actor actor = new Actor("123", "first", "last");
//        entityManager.persist(actor);
//
//        logger.log(Level.INFO, String.format("actor: id {%s}, name {%s %s}", actor.getActorID(), actor.getFirstNames(), actor.getLastName()));
//
//        Assert.assertEquals("123", actor.getActorID());
//        Assert.assertEquals("first", actor.getFirstNames());
//
//        actor.setFirstNames("fir");
//
//        logger.log(Level.INFO, String.format("[UPDATE] actor: id {%s}, name {%s %s}", actor.getActorID(), actor.getFirstNames(), actor.getLastName()));
//
//        //todo: assert using repo instead of directly from the entity
//        Assert.assertEquals("fir", actor.getFirstNames());
//    }
//
//    @Test
//    public void testDirectorEntity()
//    {
//        final Director director = new Director("123", "first", "last");
//
//        Assert.assertEquals("123", director);
//        Assert.assertEquals("first", director.getFirstNames());
//
//        director.setFirstNames("fir");
//
//        //todo: assert using repo instead of directly from the entity
//        Assert.assertEquals("fir", director.getFirstNames());
//    }
//
//    @Test
//    public void testFilmEntity()
//    {
//        //todo
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
//}