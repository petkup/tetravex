package sk.tuke.gamestudio.game.tetravex.core;

public class Tile
{

    private TileState state;

    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    /* Edge colors */
    private int North;
    private int West;
    private int East;
    private int South;

    /* Solution location */
    private int pozitionX;
    private int pozitionY;

    public Tile (int x, int y)
    {
        this.pozitionX = x;
        this.pozitionY = y;
    }

    public int getX() {
        return this.pozitionX;
    }

    public int getY() {
        return this.pozitionY;
    }

    public int getNorth() {
        return North;
    }

    public void setNorth(int north) {
        this.North = north;
    }

    public int getWest() {
        return West;
    }

    public void setWest(int mWest) {
        this.West = mWest;
    }

    public int getEast() {
        return East;
    }

    public void setEast(int mEast) {
        this.East = mEast;
    }

    public int getSouth() {
        return South;
    }

    public void setSouth(int mSouth) {
        this.South = mSouth;
    }
}