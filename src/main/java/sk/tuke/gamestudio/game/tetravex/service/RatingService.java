package sk.tuke.gamestudio.game.tetravex.service;

import sk.tuke.gamestudio.game.tetravex.entity.Rating;

import java.util.List;

public interface RatingService {
    void setRating(Rating rating);
    int getAverageRating(String game);
    int getRating(String game, String player);
    void reset();
}
