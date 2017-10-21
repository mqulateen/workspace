import com.mqul.mp.Actor;
import com.mqul.mp.PersonRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String... args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        PersonRepo repo = context.getBean(PersonRepo.class);

//        List<Actor> actors = repo.getActorsByFilmId(2);
//
//        System.out.println("************************************");
//        System.out.println("Actors list");
//        System.out.println("************************************");
//        for(Actor a : actors)
//        {
//            System.out.println(a.getActorID() + "" + a.getFirstNames()
//                              + "" + a.getLastName() + "" + a.getId());
//        }
//        System.out.println("************************************");

        Actor a = new Actor();
        a.setId(1);
        a.setActorID("1");
        a.setFirstNames("firstNames");
        a.setLastName("lastname");

        repo.addNewActor(a);

        Actor foundAcotr = repo.findActorById(1);

        System.out.println(foundAcotr.getFirstNames());
//        Test t = new Test();
//        t.saveActor("FIRSTACTOR");
    }

}