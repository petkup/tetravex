package sk.tuke.gamestudio.game.tetravex.consoleui;

import sk.tuke.gamestudio.game.tetravex.core.Field;
import sk.tuke.gamestudio.game.tetravex.core.GameState;
import sk.tuke.gamestudio.game.tetravex.core.Tile;
import sk.tuke.gamestudio.game.tetravex.core.TileState;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    public static final String GAME_NAME = "tetravex";
    private final Field field;

    private final Scanner scanner = new Scanner(System.in);

    private ScoreService scoreService = new ScoreServiceJDBC();

    private CommentService commentService = new CommentServiceJDBC();

    private RatingService ratingService = new RatingServiceJDBC();

    public ConsoleUI(Field field) {
        this.field = field;
    }

    private void displayGameMenu() {
        System.out.println("(1) Start new game");
        System.out.println("(2) Show rules");
        System.out.println("(3) Show top Score");
        System.out.println("(4) Reset Score");
        System.out.println("(5) Show comments");
        System.out.println("(6) Exit");
        System.out.print("Choose an option: ");
    }

    private void selectGameOption(int optionSelected) {
        switch (optionSelected) {
            case 1:
                this.play();
                break;
            case 2:
                System.out.print("Tetravex is an edge-matching puzzle. " + '\n' +
                        "You have a 3x3 grid and 9 square tiles, each with a different number on each side." + '\n' +
                        "The objective of the game is to place the tiles in the grid in the proper position as fast as possible. " + '\n' +
                        "Neighbor tiles edge number must equal."+ '\n');
                System.out.println();
                run();
                break;
            case 6:
                System.out.println("Exiting game");
                break;
            case 3:
                printTopScores();
                run();
                break;
            case 4:
                resetScore();
                run();
                break;
            case 5:
                printComments();
                System.out.println();
                run();break;
            default:
                System.err.println("Wrong input ");
                run();
                break;
        }
    }

    public void run(){
        displayGameMenu();
        String line = scanner.nextLine().toUpperCase();
        int position1 = line.charAt(0) - '0';
        selectGameOption(position1);
    }

    public void play(){
        printField(field.originalPole);
        do {
            printField(field.pole);
            System.out.println();
            printField(field.newPole);
            processInput();
            field.chceckIfBoardIsCorrect();
        } while (field.getState() == GameState.PLAYING);
        printField(field.newPole);
        System.out.println("Time: " + field.getScore());
        if (field.getState() == GameState.FAILED) {
            System.out.println("Wrong solution!");
            rate();
            comment();
        } else {
            System.out.println("Game solved!");
            scoreService.addScore(new Score("tetravex", System.getProperty("user.name"), field.getScore(), new Date()));
            rate();
            comment();
        }
    }

    private void printField(List<Tile> list) {
        int[][] gameBoard = {{0,list.get(0).getTop(),0,0,list.get(1).getTop(),0,0,list.get(2).getTop(),0},
                {list.get(0).getLeft(),0,list.get(0).getRightt(),list.get(1).getLeft(),0,list.get(1).getRightt(),list.get(2).getLeft(),0,list.get(2).getRightt()},
                {0,list.get(0).getBottom(),0,0,list.get(1).getBottom(),0,0,list.get(2).getBottom(),0},
                {0,list.get(3).getTop(),0,0,list.get(4).getTop(),0,0,list.get(5).getTop(),0},
                {list.get(3).getLeft(),0,list.get(3).getRightt(),list.get(4).getLeft(),0,list.get(4).getRightt(),list.get(5).getLeft(),0,list.get(5).getRightt()},
                {0,list.get(3).getBottom(),0,0,list.get(4).getBottom(),0,0,list.get(5).getBottom(),0},
                {0,list.get(6).getTop(),0,0,list.get(7).getTop(),0,0,list.get(8).getTop(),0},
                {list.get(6).getLeft(),0,list.get(6).getRightt(),list.get(7).getLeft(),0,list.get(7).getRightt(),list.get(8).getLeft(),0,list.get(8).getRightt()},
                {0,list.get(6).getBottom(),0,0,list.get(7).getBottom(),0,0,list.get(8).getBottom(),0}
        };
        gameBoard[1][1]=1;
        gameBoard[1][4]=2;
        gameBoard[1][7]=3;
        gameBoard[4][1]=4;
        gameBoard[4][4]=5;
        gameBoard[4][7]=6;
        gameBoard[7][1]=7;
        gameBoard[7][4]=8;
        gameBoard[7][7]=9;

        for (int i = 0; i < field.getRowCount(); i++)
        {
            if (i == 3 || i == 6)
                System.out.println("--------------------------");
            for (int j = 0; j < field.getColumnCount(); j++)
            {
                if (gameBoard[i][j] == 0){
                    System.out.format("%-2s", ' ');
                }
                    else {
                        System.out.format("%-2s", gameBoard[i][j]);
                }
                if (j == 2 || j == 5 || j == 8)
                    System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    private void processInput() {
        while (true) {
            System.out.print("Enter command (X - exit, P11 - put, R11 - remove): ");
            String line = scanner.nextLine().toUpperCase();
            if ("X".equals(line))
                System.exit(0);
            if (line.length() != 3){
                System.err.println("Wrong input " + line);
                System.out.println();
                continue;
            }

            int position1 = line.charAt(1) - '0';
            int position2 = line.charAt(2) - '0';

            if (position1 == 0 || position2 == 0){
                System.err.println("Wrong input " + line);
                System.out.println();
                continue;
            }

            if (line.startsWith("P")) {
                if (field.newPole.get(position2-1).getState() == TileState.EMPTY & field.pole.get(position1-1).getState() == TileState.FILLED) {
                    field.addTile(position1 - 1, position2 - 1);
                    break;
                }
                else{
                    System.out.println("You can't do this!");
                    continue;
                    }
            }

            else if (line.startsWith("R")) {
                if (field.newPole.get(position1-1).getState() == TileState.FILLED & field.pole.get(position2-1).getState() == TileState.EMPTY) {
                    field.removeTile(position1-1, position2 - 1);
                    break;
                }
                else{
                    System.out.println("You can't do this!");
                    continue;
                }
            } else{
                System.err.println("Wrong input " + line);
                System.out.println();
                continue;
            }
        }
    }

    private void printTopScores() {
        List<Score> scores = scoreService.getTopScore(GAME_NAME);
        for (Score score : scores) {
            System.out.printf("%s %d\n", score.getPlayer(), score.getPoints());
        }
    }

    private void printComments() {
        List<Comment> comments = commentService.getComments(GAME_NAME);
        for (Comment comment : comments) {
            System.out.printf("%s %s\n", comment.getPlayer(), comment.getComment());
        }
    }

    private void resetScore(){
        scoreService.reset();
    }

    private void comment(){
        System.out.println("Leave a comment: ");
        String line = scanner.nextLine().toUpperCase();
        commentService.addComment(new Comment(System.getProperty("user.name"), "tetravex", line, new Date()));
    }

    private void rate(){
        System.out.println("Rate game from 1-5: ");
        int rating = new Scanner(System.in).nextInt();
        ratingService.setRating(new Rating("tetravex",System.getProperty("user.name"), rating, new Date()));
        System.out.println();
        System.out.println("Average rating: " + ratingService.getAverageRating("tetravex"));
    }

}