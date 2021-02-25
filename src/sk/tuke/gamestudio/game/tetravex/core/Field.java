package sk.tuke.gamestudio.game.tetravex.core;

public class Field {
    private GameState state = GameState.PLAYING;

    private final int rowCount;

    private final int columnCount;

    private final Tile[][] tiles;

    public Field(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        tiles = new Tile[rowCount][columnCount];
        generate();
    }

    private void generate() {
    }


}
