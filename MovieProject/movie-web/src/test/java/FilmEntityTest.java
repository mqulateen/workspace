import com.mqul.mp.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilmEntityTest {

    private static Logger log = LoggerFactory.getLogger(FilmEntityTest.class);

    private static FilmRepo repo;

    private final static int PRIMARY_KEY = 999;

    @BeforeClass
    public static void init()
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        repo = context.getBean(FilmRepo.class);
    }

    @Test
    public void getAllFilms()
    {
        repo.addNewFilm(new Film("f"+PRIMARY_KEY, "Film: The Test", 5.6, 2007));

        List<Film> films = repo.getAllFilms();

        int finalIndex = films.size()-1;

        assertEquals(films.get(finalIndex).getImdbRef(), "f999");
        assertEquals(films.get(finalIndex).getFilmName(), "Film: The Test");
    }

    @Test
    public void actorByFilmIdTest()
    {
        final int filmId = 2;

        List<Actor> actors = repo.getActorsByFilmId(filmId);

        assertEquals(actors.size(), 2);
    }

    @Test
    public void directorByFilmIdTest()
    {
        final int filmId = 2;

        List<Director> directors = repo.getDirectorsByFilmId(filmId);

        assertEquals(directors.size(), 1);
    }


    @AfterClass
    public static void removeTestData()
    {
        try
        {
            repo.removeFilm(PRIMARY_KEY);
        }
        catch (IllegalArgumentException e)
        {
            log.warn("The director with id {} does not exist", PRIMARY_KEY);
        }
    }
}