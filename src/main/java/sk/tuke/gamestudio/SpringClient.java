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
import sk.tuke.gamestudio.service.ScoreServiceJPA;


@SpringBootApplication
@Configuration
public class SpringClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringClient.class);

    }

    @Bean
    public CommandLineRunner runner(ConsoleUI ui) {
        return args -> ui.run();
    }

    @Bean
    public ConsoleUI consoleUI(Field field) {
        return new ConsoleUI(field);
    }

    @Bean
    public Field field() {
        return new Field(9, 9);
    }

    @Bean
    public ScoreService scoreService() {
        //return new ScoreServiceJDBC();
        return new ScoreServiceJPA();
    }

}
