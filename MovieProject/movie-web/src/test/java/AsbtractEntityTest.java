import com.mqul.mp.*;
import com.mqul.mp.repository.ActorRepo;
import com.mqul.mp.repository.DirectorRepo;
import com.mqul.mp.repository.PersonRepo;
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

    private static PersonRepo personRepo;
    private static ActorRepo actorRepo;
    private static DirectorRepo directorRepo;

    private final static List<String> REFS = Arrays.asList("997", "998", "999");
    private final static String firstName = "first", lastName = "last";

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        personRepo = context.getBean(PersonRepo.class);
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
        List<Actor> actors = personRepo.getAll(PersonType.ACTOR);

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
        List<Director> directors = personRepo.getAll(PersonType.DIRECTOR);

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
                personRepo.removePerson(actorRepo.findActorByRef(ref).getID(), PersonType.ACTOR);

                personRepo.removePerson(directorRepo.findDirectorByRef(ref).getID(), PersonType.DIRECTOR);
            }
            catch (IllegalArgumentException ex)
            {
                log.warn("person with imdb ref {} was not found. Error: {}", ref, ex);
            }
        }
    }
}

