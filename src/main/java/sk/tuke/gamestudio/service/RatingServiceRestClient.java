package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;


import java.util.Arrays;
import java.util.List;

public class RatingServiceRestClient implements RatingService {
    @Value("${remote.server.api}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void setRating(Rating rating) {

    }

    @Override
    public int getAverageRating(String game) {
        return 0;
    }

    @Override
    public int getRating(String game, String player) {
        return 0;
    }

    @Override
    public void reset() {

    }
}