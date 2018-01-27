import com.mqul.mp.*;
import com.mqul.mp.repository.ActorRepo;
import com.mqul.mp.repository.DirectorRepo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Ignore
public class AsbtractEntityTest {

    private static Logger log = LoggerFactory.getLogger(ActorEntityTest.class);

    private static ActorRepo actorRepo;
    private static DirectorRepo directorRepo;

    private final static List<String> REFS = Arrays.asList("997", "998", "999");
    private final static String firstName = "first", lastName = "last";

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        actorRepo = context.getBean(ActorRepo.class);
        directorRepo = context.getBean(DirectorRepo.class);
    }

    @Test
    public void retrieveAllActors()
    {
        //insert some test actors
        for(String key : REFS)
        {
            final Actor actor = new Actor("a"+key, firstName+key, lastName+key);
            actorRepo.createActor(actor);
        }

        //todo: this test and method (getAll()) will become in-efficient as the table grows, adjust as and when needed
        List<Actor> actors = actorRepo.getAllActors();

        for(Actor actor : actors)
        {
            if(REFS.contains(actor.getImdbRef()))
            {
                assertEquals(actor.getFirstNames(), firstName+actor.getImdbRef());
                assertEquals(actor.getLastName(), lastName+actor.getImdbRef());
            }
        }
    }

    @Test
    public void retrieveAllDirectors()
    {
        //insert some test directors
        for(String key : REFS)
        {
            final Director director = new Director("d"+key, firstName+key, lastName+key);
            directorRepo.createDirector(director);
        }

        //todo: this test and method (getAll()) will become in-efficient as the table grows, adjust as and when needed
        List<Director> directors = directorRepo.getAllDirectors();

        for(Director director : directors)
        {
            if(REFS.contains(director.getImdbRef()))
            {
                assertEquals(director.getFirstNames(), firstName+director.getImdbRef());
                assertEquals(director.getLastName(), lastName+director.getImdbRef());
            }
        }
    }

    @AfterClass
    public static void dbCleanUp()
    {
        for(String ref : REFS)
        {
            try
            {
                actorRepo.deleteActor(actorRepo.findActorByRef(ref).getID());

                directorRepo.deleteDirector(directorRepo.findDirectorByRef(ref).getID());
            }
            catch (IllegalArgumentException ex)
            {
                log.warn("person with imdb ref {} was not found. Error: {}", ref, ex);
            }
        }
    }
}

