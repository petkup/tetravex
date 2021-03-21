package sk.tuke.gamestudio.game.tetravex.core;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FieldTest {
    Field field = new Field(9,9);

    @Test
    public void testAddingTile() {
        field.addTile(1,2);
        assertEquals(field.pole.get(1).getState(), TileState.EMPTY);
    }

    @Test
    public void removingTile() {
        field.removeTile(2,1);
        assertEquals(field.pole.get(1).getState(), TileState.FILLED);
    }

    @Test
    public void testTileState() {
        field.addTile(1,2);
        assertEquals(TileState.FILLED, field.newPole.get(2).getState());
        assertEquals(TileState.EMPTY, field.pole.get(1).getState());
    }
}
