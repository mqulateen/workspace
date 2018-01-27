import com.mqul.mp.Actor;
import com.mqul.mp.repository.ActorRepo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.mqul.mp.PersonType.*;
import static org.junit.Assert.assertEquals;

@Ignore
public class ActorEntityTest {

    private static Logger log = LoggerFactory.getLogger(ActorEntityTest.class);

    private static ActorRepo actorRepo;

    private final static String REF = "999";

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        actorRepo = context.getBean(ActorRepo.class);
    }

    @Test
    public void actorEntityTest()
    {
        Actor a = new Actor();
        a.setImdbRef(REF);
        a.setFirstNames("test");
        a.setLastName("user");

        actorRepo.createActor(a);

        final Actor insertedActor = actorRepo.findActorByRef(REF);

        assertEquals(a.getID(), insertedActor.getID());
        assertEquals(a.getImdbRef(), insertedActor.getImdbRef());
        assertEquals(a.getFirstNames(), insertedActor.getFirstNames());
        assertEquals(a.getLastName(), insertedActor.getLastName());

        log.info("actor test as successful for actor {}", insertedActor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void attemptToRemoveActorTwiceTest()
    {
        final int actorId = actorRepo.findActorByRef(REF).getID();

        log.info("removing test actor with the imdb ref {} and id {}", REF, actorId);
        actorRepo.deleteActor(actorId);

        log.info("attempt second removal of actor with imdb ref {} and id {}, should fail", REF, actorId);
        actorRepo.deleteActor(actorId);
    }

    @AfterClass
    public static void removeTestData()
    {
        try
        {
            final int actorId = actorRepo.findActorByRef(REF).getID();
            actorRepo.deleteActor(actorId);
        }
        catch (IllegalArgumentException e)
        {
            log.warn("The actor with ref {} does not exist", REF);
        }
    }
}