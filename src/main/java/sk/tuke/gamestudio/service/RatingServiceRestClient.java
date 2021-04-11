package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;


import java.util.Arrays;
import java.util.List;

public class RatingServiceRestClient implements RatingService {
    @Value("${remote.server.api}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void setRating(Rating rating) {
        restTemplate.postForEntity(url + "/rating", rating, Rating.class);
    }

    @Override
    public int getAverageRating(String game) {
        return restTemplate.getForEntity(url + "/rating" + game, Integer.class).getBody();
    }

    @Override
    public int getRating(String game, String player) {
        return restTemplate.getForEntity(url + "/rating/" + game, Integer.class).getBody();
    }

    @Override
    public void reset() {

    }
}