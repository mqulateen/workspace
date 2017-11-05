import com.mqul.mp.Actor;
import com.mqul.mp.Director;
import com.mqul.mp.PersonRepo;
import com.mqul.mp.PersonType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AsbtractEntityTest {

    private static Logger log = LoggerFactory.getLogger(ActorEntityTest.class);

    private static PersonRepo repo;

    private final static List<Integer> PRIMARY_KEYS = Arrays.asList(997, 998, 999);
    private final static String firstName = "first", lastName = "last";

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        repo = context.getBean(PersonRepo.class);
    }

    @Test
    public void retrieveAllActors()
    {
        //insert some test actors
        for(int key : PRIMARY_KEYS)
        {
            final Actor actor = new Actor(key, "a"+key, firstName+key, lastName+key);
            repo.addPerson(actor);
        }

        //todo: this test and method (getAll()) will become in-efficient as the table grows, adjust as and when needed
        List<Actor> actors = repo.getAll(PersonType.ACTOR);

        for(Actor actor : actors)
        {
            if(PRIMARY_KEYS.contains(actor.getId()))
            {
                assertEquals(actor.getFirstNames(), firstName+actor.getId());
                assertEquals(actor.getLastName(), lastName+actor.getId());
            }
        }
    }
    
    @Test
    public void retrieveAllDirectors()
    {
        //insert some test directors
        for(int key : PRIMARY_KEYS)
        {
            final Director director = new Director(key, "d"+key, firstName+key, lastName+key);
            repo.addPerson(director);
        }

        //todo: this test and method (getAll()) will become in-efficient as the table grows, adjust as and when needed
        List<Director> directors = repo.getAll(PersonType.DIRECTOR);

        for(Director director : directors)
        {
            if(PRIMARY_KEYS.contains(director.getId()))
            {
                assertEquals(director.getFirstNames(), firstName+director.getId());
                assertEquals(director.getLastName(), lastName+director.getId());
            }
        }
    }
    
    @AfterClass
    public static void dbCleanUp()
    {
        for(int id : PRIMARY_KEYS)
        {
            try
            {
                repo.removePerson(id, PersonType.ACTOR);

                repo.removePerson(id, PersonType.DIRECTOR);
            }
            catch (IllegalArgumentException ex)
            {
                log.warn("person with id {} was not found. Error: {}", id, ex);
            }
        }
    }
}

