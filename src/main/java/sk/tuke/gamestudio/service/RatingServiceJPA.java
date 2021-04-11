package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void setRating(Rating rating){
        entityManager.persist(rating);
    }

    @Override
    public int getAverageRating(String game){
        double avgR = (double) entityManager.createNamedQuery("Rating.getAverageRating")
                .setParameter("game", game)
                .getSingleResult();
        return (int) avgR;
    }

    @Override
    public int getRating(String game, String player) {
        return (int) entityManager.createNamedQuery("Rating.getRating")
                .setParameter("game", game)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("delete from rating").executeUpdate();
    }

}