package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.game.tetravex.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tetravex.core.Field;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;


@SpringBootApplication
@Configuration
public class SpringClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringClient.class);

    }
}
