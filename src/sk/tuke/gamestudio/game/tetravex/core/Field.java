package sk.tuke.gamestudio.game.tetravex.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;

public class Field {
    private GameState state = GameState.PLAYING;

    private final int rowCount;
    private final int columnCount;
    private final Random rand;
    // create 9 piecies
    private final Tile tile1 = new Tile();
    private final Tile tile2 = new Tile();
    private final Tile tile3 = new Tile();
    private final Tile tile4 = new Tile();
    private final Tile tile5 = new Tile();
    private final Tile tile6 = new Tile();
    private final Tile tile7 = new Tile();
    private final Tile tile8 = new Tile();
    private final Tile tile9 = new Tile();

    public Field(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        rand = new Random();
        generate();
    }

    private void generate() {
        generateTilesEdges();
        addToList();
        shuffleList();
    }

    // Give every piece random edge value, neighbor edges must equal.
    private void generateTilesEdges() {
        tile1.setTop(rand.nextInt(9 - 1 + 1)+1);
        tile1.setLeft(rand.nextInt(9 - 1 + 1)+1);
        tile1.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile1.setBottom(rand.nextInt(9 - 1 + 1)+1);

        tile2.setTop(rand.nextInt(9 - 1 + 1)+1);
        tile2.setLeft(tile1.getRightt());
        tile2.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile2.setBottom(rand.nextInt(9 - 1 + 1)+1);

        tile3.setTop(rand.nextInt(9 - 1 + 1)+1);
        tile3.setLeft(tile2.getRightt());
        tile3.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile3.setBottom(rand.nextInt(9 - 1 + 1)+1);

        tile4.setTop(tile1.getBottom());
        tile4.setLeft(rand.nextInt(9 - 1 + 1)+1);
        tile4.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile4.setBottom(rand.nextInt(9 - 1 + 1)+1);

        tile5.setTop(tile2.getBottom());
        tile5.setLeft(tile4.getRightt());
        tile5.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile5.setBottom(rand.nextInt(9 - 1 + 1)+1);

        tile6.setTop(tile3.getBottom());
        tile6.setLeft(tile5.getRightt());
        tile6.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile6.setBottom(rand.nextInt(9 - 1 + 1)+1);

        tile7.setTop(tile4.getBottom());
        tile7.setLeft(rand.nextInt(9 - 1 + 1)+1);
        tile7.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile7.setBottom(rand.nextInt(9 - 1 + 1)+1);

        tile8.setTop(tile5.getBottom());
        tile8.setLeft(tile7.getRightt());
        tile8.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile8.setBottom(rand.nextInt(9 - 1 + 1)+1);

        tile9.setTop(tile6.getBottom());
        tile9.setLeft(tile8.getRightt());
        tile9.setRight(rand.nextInt(9 - 1 + 1)+1);
        tile9.setBottom(rand.nextInt(9 - 1 + 1)+1);
    }

    private void addToList() {
        pole.add(tile1);
        pole.add(tile2);
        pole.add(tile3);
        pole.add(tile4);
        pole.add(tile5);
        pole.add(tile6);
        pole.add(tile7);
        pole.add(tile8);
        pole.add(tile9);
        originalPole.addAll(pole);
    }

    private void shuffleList() {
        shuffleTiles(pole);
        newPole.addAll(pole);
    }

    public List<Tile> pole = new ArrayList<>();
    public List<Tile> originalPole = new ArrayList<>(pole);
    public List<Tile> newPole = new ArrayList<>();

    private void shuffleTiles(List<Tile> list){
        Collections.shuffle(list);
    }

    public GameState getState() {
        return state;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    //change pozition of pieces in new list
    public void addTile(int tileposition, int newPosition){
        newPole.set(newPosition, pole.get(tileposition));
        chceckIfBoardIsCorrect();
        if (newPole.get(newPosition).getState() == TileState.EMPTY) {
            newPole.get(newPosition).setState(TileState.FILLED);
        }
    }

    //remove piece from new list
    public void removeTile(List<Tile> list, int tileposition){
        if (list.get(tileposition).getState() == TileState.FILLED) {
            list.get(tileposition).setState(TileState.EMPTY);
        }
    }

    private boolean chceckIfBoardIsFilled(){
        for (int i = 0; i < newPole.size(); i++){
            if (newPole.get(i).getState() == TileState.EMPTY){
                return false;
            }
        }
        return true;
    }

    public void chceckIfBoardIsCorrect(){
        if (originalPole.equals(newPole) == true && chceckIfBoardIsFilled() == true){
            state = GameState.SOLVED;
        }
        else if(originalPole.equals(newPole) == false && chceckIfBoardIsFilled() == true){
            state = GameState.FAILED;
        }
    }

    public void timer(){

    }
}
