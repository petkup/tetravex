package sk.tuke.gamestudio.game.tetravex;

import sk.tuke.gamestudio.game.tetravex.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tetravex.core.Field;

public class Main {

    public static void main(String[] args) {
	    Field field = new Field(9,9);
        ConsoleUI ui = new ConsoleUI(field);
        ui.play();
    }
}
