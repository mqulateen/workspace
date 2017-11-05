import com.mqul.mp.Actor;
import com.mqul.mp.PersonRepo;
import com.mqul.mp.PersonType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.mqul.mp.PersonType.*;
import static org.junit.Assert.assertEquals;

public class ActorEntityTest {

    private static Logger log = LoggerFactory.getLogger(ActorEntityTest.class);

    private static PersonRepo repo;

    private final static int PRIMARY_KEY = 999;

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        repo = context.getBean(PersonRepo.class);
    }

    @Test
    public void actorEntityTest()
    {
        Actor a = new Actor();
        a.setId(PRIMARY_KEY);
        a.setActorID("999");
        a.setFirstNames("test");
        a.setLastName("user");

        repo.addPerson(a);

        final Actor insertedActor = repo.findActorById(PRIMARY_KEY);

        assertEquals(a.getActorID(), insertedActor.getActorID());
        assertEquals(a.getFirstNames(), insertedActor.getFirstNames());
        assertEquals(a.getLastName(), insertedActor.getLastName());

        log.info("actor test as successful for actor {}", insertedActor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void attemptToRemoveActorTwiceTest()
    {
        log.info("removing test actor with the id {}", PRIMARY_KEY);
        repo.removePerson(PRIMARY_KEY, ACTOR);

        log.info("attempt second removal of actor with id {}, should fail", PRIMARY_KEY);
        repo.removePerson(PRIMARY_KEY, ACTOR);
    }

    @AfterClass
    public static void removeTestData()
    {
        try
        {
            repo.removePerson(PRIMARY_KEY, ACTOR);
        }
        catch (IllegalArgumentException e)
        {
            log.warn("The actor with id {} does not exist", PRIMARY_KEY);
        }
    }
}