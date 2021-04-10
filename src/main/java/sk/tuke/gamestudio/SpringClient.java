package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import sk.tuke.gamestudio.game.tetravex.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tetravex.core.Field;
import sk.tuke.gamestudio.service.*;


@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))

public class SpringClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);

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
        //return new ScoreServiceRestClient();
    }
    @Bean
    public CommentService commentService() {
        //return new ScoreServiceJDBC();
        return new CommentServiceJPA();
        //return new ScoreServiceRestClient();
    }
    @Bean
    public RatingService ratingService() {
        //return new ScoreServiceJDBC();
        return new RatingServiceJPA();
        //return new ScoreServiceRestClient();
    }

}
