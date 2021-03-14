package sk.tuke.gamestudio.game.tetravex.service;

import sk.tuke.gamestudio.game.tetravex.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score);

    List<Score> getTopScore(String game);

    void  reset();
}
