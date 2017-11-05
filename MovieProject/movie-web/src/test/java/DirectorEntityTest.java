import com.mqul.mp.Director;
import com.mqul.mp.PersonRepo;
import com.mqul.mp.PersonType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.mqul.mp.PersonType.DIRECTOR;
import static org.junit.Assert.assertEquals;

public class DirectorEntityTest {

    private static Logger log = LoggerFactory.getLogger(DirectorEntityTest.class);

    private static PersonRepo repo;

    private final static int PRIMARY_KEY = 999;

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        repo = context.getBean(PersonRepo.class);
    }

    @Test
    public void directorEntityTest()
    {
        Director d = new Director();
        d.setId(PRIMARY_KEY);
        d.setDirectorID("999");
        d.setFirstNames("test");
        d.setLastName("user");

        repo.addPerson(d);

        final Director insertedDirector = repo.findDirectorById(PRIMARY_KEY);

        assertEquals(d.getDirectorID(), insertedDirector.getDirectorID());
        assertEquals(d.getFirstNames(), insertedDirector.getFirstNames());
        assertEquals(d.getLastName(), insertedDirector.getLastName());

        log.info("director test as successful for director {}", insertedDirector);
    }

    @Test(expected = IllegalArgumentException.class)
    public void attemptToRemoveDirectorTwiceTest()
    {
        log.info("removing test director with the id {}", PRIMARY_KEY);
        repo.removePerson(PRIMARY_KEY, DIRECTOR);

        log.info("attempt second removal of director with id {}, should fail", PRIMARY_KEY);
        repo.removePerson(PRIMARY_KEY, DIRECTOR);
    }

    @AfterClass
    public static void removeTestData()
    {
        try
        {
            repo.removePerson(PRIMARY_KEY, DIRECTOR);
        }
        catch (IllegalArgumentException e)
        {
            log.warn("The director with id {} does not exist", PRIMARY_KEY);
        }
    }
}