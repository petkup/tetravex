package sk.tuke.gamestudio.game.tetravex.core;

public class Tile
{

    private TileState state = TileState.FILLED;

    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    // 4 strany kachlicky
    private int top;
    private int left;
    private int right;
    private int bottom;

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getRightt() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
}