package sk.tuke.gamestudio.game.tetravex.consoleui;

import sk.tuke.gamestudio.game.tetravex.core.Field;
import sk.tuke.gamestudio.game.tetravex.core.GameState;
import sk.tuke.gamestudio.game.tetravex.core.TileState;

import java.util.Scanner;

public class ConsoleUI {
    private final Field field;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(Field field) {
        this.field = field;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // pole na vykrelslenie
     private int[][] board = {};

    // nove prazdne pole, do ktoreho budem presuvat
    private int newBoard[][]=new int[9][9];


    public void play(){
        do {
            System.out.println(field.pole.get(0).getState());
            System.out.println(field.newPole.get(0).getState());
            printField();
            System.out.println();
            printNewField();
            processInput();
            field.chceckIfBoardIsCorrect();
        } while (field.getState() == GameState.PLAYING);
        printField();

        if (field.getState() == GameState.FAILED) {
            System.out.println("Game failed!");
        } else {
            System.out.println("Game solved!");
        }
    }

    private void printField() {
        int[][] gameBoard = {{0,field.pole.get(0).getTop(),0,0,field.pole.get(1).getTop(),0,0,field.pole.get(2).getTop(),0},
                {field.pole.get(0).getLeft(),0,field.pole.get(0).getRightt(),field.pole.get(1).getLeft(),0,field.pole.get(1).getRightt(),field.pole.get(2).getLeft(),0,field.pole.get(2).getRightt()},
                {0,field.pole.get(0).getBottom(),0,0,field.pole.get(1).getBottom(),0,0,field.pole.get(2).getBottom(),0},
                {0,field.pole.get(3).getTop(),0,0,field.pole.get(4).getTop(),0,0,field.pole.get(5).getTop(),0},
                {field.pole.get(3).getLeft(),0,field.pole.get(3).getRightt(),field.pole.get(4).getLeft(),0,field.pole.get(4).getRightt(),field.pole.get(5).getLeft(),0,field.pole.get(5).getRightt()},
                {0,field.pole.get(3).getBottom(),0,0,field.pole.get(4).getBottom(),0,0,field.pole.get(5).getBottom(),0},
                {0,field.pole.get(6).getTop(),0,0,field.pole.get(7).getTop(),0,0,field.pole.get(8).getTop(),0},
                {field.pole.get(6).getLeft(),0,field.pole.get(6).getRightt(),field.pole.get(7).getLeft(),0,field.pole.get(7).getRightt(),field.pole.get(8).getLeft(),0,field.pole.get(8).getRightt()},
                {0,field.pole.get(6).getBottom(),0,0,field.pole.get(7).getBottom(),0,0,field.pole.get(8).getBottom(),0}
        };

        board = gameBoard;

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
                if (board[i][j] == 0){
                    System.out.format("%-2s", ' ');
            }   else{
                    System.out.format("%-2s", board[i][j]);
                }

                if (j == 2 || j == 5 || j == 8)
                    System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    private void printNewField() {
        newBoard[1][1]=1;
        newBoard[1][4]=2;
        newBoard[1][7]=3;
        newBoard[4][1]=4;
        newBoard[4][4]=5;
        newBoard[4][7]=6;
        newBoard[7][1]=7;
        newBoard[7][4]=8;
        newBoard[7][7]=9;
        for (int i = 0; i < field.getRowCount(); i++)
        {
            if (i == 3 || i == 6)
                System.out.println("--------------------------");
            for (int j = 0; j < field.getColumnCount(); j++)
            {
                if (newBoard[i][j] == 0){
                    System.out.format("%-2s", ' ');
                }   else{
                    System.out.format("%-2s", newBoard[i][j]);
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
            System.out.print("Enter command (X - exit, P11 - put, R1 - remove): ");
            String line = scanner.nextLine().toUpperCase();
            if ("X".equals(line))
                System.exit(0);

            int row = line.charAt(1) - '0';
            int column = line.charAt(2) - '0';

            if (line.startsWith("P")) {
                if (field.newPole.get(row-1).getState() == TileState.EMPTY) {
                    field.addTile(row - 1, column - 1);
                    break;
                }
                else{
                    System.out.println("This position is already filled!");
                    continue;
                    }
                   /* newBoard[row - 1][column] = field.pole.get(row-1).getTop();
                    newBoard[row][column - 1] = field.pole.get(row-1).getLeft();
                    newBoard[row + 1][column] = field.pole.get(row-1).getBottom();
                    newBoard[row][column + 1] = field.pole.get(row-1).getRightt();*/
            }

            if (line.startsWith("R")) {
                if (field.newPole.get(row-1).getState() == TileState.FILLED) {
                    field.removeTile(field.newPole, row - 1);
                    break;
                }
                else{
                    System.out.println("This position is empty!");
                    continue;
                }
            }
        }
    }
}