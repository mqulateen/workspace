import com.mqul.mp.Director;
import com.mqul.mp.repository.DirectorRepo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.mqul.mp.PersonType.DIRECTOR;
import static org.junit.Assert.assertEquals;

@Ignore
public class DirectorEntityTest {

    private static Logger log = LoggerFactory.getLogger(DirectorEntityTest.class);

    private static DirectorRepo directorRepo;

    private final static String REF = "999";

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        directorRepo = context.getBean(DirectorRepo.class);
    }

    @Test
    public void directorEntityTest()
    {
        Director d = new Director();
        d.setImdbRef(REF);
        d.setFirstNames("test");
        d.setLastName("user");

        directorRepo.createDirector(d);

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
        directorRepo.deleteDirector(directorId);

        log.info("attempt second removal of actor with imdb ref {} and id {}, should fail", REF, directorId);
        directorRepo.deleteDirector(directorId);
    }

    @AfterClass
    public static void removeTestData()
    {
        try
        {
            final int directorId = directorRepo.findDirectorByRef(REF).getID();
            directorRepo.deleteDirector(directorId);
        }
        catch (IllegalArgumentException e)
        {
            log.warn("The director with ref {} does not exist", REF);
        }
    }
}