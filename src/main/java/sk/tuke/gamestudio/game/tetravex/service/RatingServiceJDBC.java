package sk.tuke.gamestudio.game.tetravex.service;

import sk.tuke.gamestudio.game.tetravex.entity.Rating;

import java.sql.*;
import java.util.List;

public class RatingServiceJDBC implements RatingService {

    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String SELECT = "SELECT rating FROM rating WHERE game = ? AND player = ?";
    public static final String DELETE = "DELETE FROM comment";
    private static final String SELECT_AVG_RATING = "SELECT avg(rating) FROM rating WHERE game = ?";
    public static final String INSERT = "INSERT INTO rating (game, player, rating, ratedon) VALUES (?, ?, ?, ?) ON CONFLICT (player, game) DO UPDATE SET rating=?, ratedon=?";


    @Override
    public void setRating(Rating rating) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
                ps.setString(1, rating.getGame());
                ps.setString(2, rating.getPlayer());
                ps.setInt(3, rating.getRating());
                ps.setDate(4, new Date(rating.getRatedon().getTime()));
                ps.setInt(5, rating.getRating());
                ps.setDate(6, new Date(rating.getRatedon().getTime()));

                ps.executeUpdate();
                ps.closeOnCompletion();
            } catch (SQLException e) {
                throw new GamestudioException("Problem inserting score", e);
            }
        } catch (SQLException e) {
            throw new GamestudioException("Problem inserting score", e);
        }

    }

    @Override
    public int getAverageRating(String game) {
        int avgRating = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_AVG_RATING)) {
                ps.setString(1, game);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        avgRating = rs.getInt(1);

                    }
                }
            } catch (SQLException e) {
                throw new GamestudioException("Problem inserting score", e);
            }
        } catch (SQLException e) {
            throw new GamestudioException("Problem inserting score", e);
        }

        return avgRating;
    }

    @Override
    public int getRating(String game, String player) {
        int rating = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT)) {
                ps.setString(1, game);
                ps.setString(2, player);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        rating = rs.getInt(1);

                    }
                }
            } catch (SQLException e) {
                throw new GamestudioException("Problem inserting score", e);
            }
        } catch (SQLException e) {
            throw new GamestudioException("Problem inserting score", e);
        }

        return rating;
    }

    @Override
    public void reset() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new GamestudioException("Problem deleting score", e);
        }
    }
}
