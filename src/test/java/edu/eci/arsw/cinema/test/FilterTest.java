import edu.eci.arsw.cinema.filter.CinemaFilterException;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import java.util.ArrayList;
import java.util.List;

import edu.eci.arsw.cinema.services.CinemaServices;
import org.junit.Test;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class FilterTest {
    private ApplicationContext ac;
    private CinemaServices cs;
    private Cinema c;
    @Before
    public void setUp() throws CinemaException, CinemaPersistenceException {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        cs = ac.getBean(CinemaServices.class);
        c = cs.getCinemaByName("cinemaX");

    }

    @Test
    public void shouldFilterByGender(){
        List<Movie> f = null;
        try {
            f = cs.filter("cinemaX","2018-12-18 15:30","Horror");
        } catch (CinemaException | CinemaFilterException | CinemaPersistenceException e) {
            fail();
        }
        String[] horrorMoviesNames = new String[]{"The Night"};
        for (int i = 0; i < f.size(); i++) {
            assertEquals(horrorMoviesNames[i],f.get(i).getName());
        }
    }
    @Test
    public void shouldFilterByAvailability()  {
        List<Movie> f = null;

        try {
            f = cs.filter("cinemaX","2018-12-18 15:30","80");
        } catch (CinemaException | CinemaPersistenceException | CinemaFilterException e) {
            fail();
        }

        String[] answers = new String[]{"SuperHeroes Movie","The Night"};
        for (int i = 0; i < f.size(); i++) {
            assertEquals(answers[i],f.get(i).getName());
        }
    }

}
