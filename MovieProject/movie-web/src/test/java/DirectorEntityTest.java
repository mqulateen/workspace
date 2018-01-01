import com.mqul.mp.Director;
import com.mqul.mp.DirectorRepo;
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

    private static PersonRepo personRepo;
    private static DirectorRepo directorRepo;

    private final static String REF = "999";

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        personRepo = context.getBean(PersonRepo.class);
        directorRepo = context.getBean(DirectorRepo.class);
    }

    @Test
    public void directorEntityTest()
    {
        Director d = new Director();
        d.setImdbRef(REF);
        d.setFirstNames("test");
        d.setLastName("user");

        directorRepo.addDirector(d);

        final Director insertedDirector = directorRepo.findDirectorByRef(REF);

        assertEquals(d.getID(), insertedDirector.getID());
        assertEquals(d.getImdbRef(), insertedDirector.getImdbRef());
        assertEquals(d.getFirstNames(), insertedDirector.getFirstNames());
        assertEquals(d.getLastName(), insertedDirector.getLastName());

        log.info("director test as successful for director {}", insertedDirector);
    }

    @Test(expected = IllegalArgumentException.class)
    public void attemptToRemoveDirectorTwiceTest()
    {
        final int directorId = directorRepo.findDirectorByRef(REF).getID();

        log.info("removing test director with the imdb ref {} and id {}", REF, directorId);
        personRepo.removePerson(directorId, DIRECTOR);

        log.info("attempt second removal of actor with imdb ref {} and id {}, should fail", REF, directorId);
        personRepo.removePerson(directorId, DIRECTOR);
    }

    @AfterClass
    public static void removeTestData()
    {
        try
        {
            final int directorId = directorRepo.findDirectorByRef(REF).getID();
            personRepo.removePerson(directorId, DIRECTOR);
        }
        catch (IllegalArgumentException e)
        {
            log.warn("The director with ref {} does not exist", REF);
        }
    }
}