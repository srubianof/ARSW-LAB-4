import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;

import java.util.List;

import edu.eci.arsw.cinema.services.CinemaServices;
import org.junit.Test;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class PersistenceTest {
    private ApplicationContext ac;
    private CinemaServices cs;
    private Cinema c;
    private List<CinemaFunction> cfs;
    @Before
    public void setUp() throws CinemaException, CinemaPersistenceException {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        cs = ac.getBean(CinemaServices.class);
        c = cs.getCinemaByName("cinemaX");
        cfs = cs.getFunctionsByCinemaAndDate("cinemaX", "2018-12-18 15:30");
    }
    @Test
    public void shouldReturnCinemaName(){
        String name = c.getName();
        assertEquals("cinemaX",name);
    }

    @Test
    public void shouldReturnByCinemaAndDate(){
        String[] answers = new String[]{"SuperHeroes Movie","The Night"};
        for (int i = 0; i < cfs.size(); i++) {
            assertEquals(answers[i],cfs.get(i).getMovie().getName());
        }
    }
    @Test
    public void shouldBuyTickets(){
        int before = cfs.get(0).movieAvailability(); //SuperHeroes Movie
        try{
            cs.buyTicket(1,1,"cinemaX","2018-12-18 15:30","SuperHeroes Movie");
        }
        catch (CinemaException e){
            fail();
        }
        int after = cfs.get(0).movieAvailability();
        assertTrue(before > after);

    }

}
