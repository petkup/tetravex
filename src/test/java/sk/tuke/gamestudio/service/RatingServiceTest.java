package sk.tuke.gamestudio.service;

import org.junit.Test;
import sk.tuke.gamestudio.entity.Rating;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class RatingServiceTest {
    private RatingService createService() {
        return new RatingServiceJDBC();
    }

    @Test
    public void testReset() {
        RatingService service = createService();
        service.reset();
        assertEquals(0, service.getAverageRating("tetravex"));
    }

    @Test
    public void testsetRating() {
        RatingService service = createService();
        service.reset();
        Date date = new Date();
        service.setRating(new Rating("tetravex", "Jaro", 4, date));

        int ratings = service.getAverageRating("tetravex");
        assertEquals(4,ratings);
    }


    @Test
    public void testPersistance() {
        RatingService service = createService();
        service.reset();
        service.setRating(new Rating("mines", "Jaro", 4, new Date()));
        service.setRating(new Rating("mines", "Jaro", 2, new Date()));

        service = createService();
        assertEquals(0, service.getAverageRating("tetravex"));
    }
}
