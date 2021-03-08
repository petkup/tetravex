package sk.tuke.gamestudio;

import sk.tuke.gamestudio.game.tetravex.core.Tile;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

    /*
    private void randomField(){
        field.generateField(tile1,tile2,tile3,tile4,tile5,tile6,tile7,tile8,tile9);
        System.out.println(tile1.getBottom());
        System.out.println(tile4.getTop());
        System.out.println(tile4.getRightt());
    }*/

         Tile tile1 = new Tile();
         Tile tile2 = new Tile();
         Tile tile3 = new Tile();
         Tile tile4 = new Tile();
         Tile tile5 = new Tile();
         Tile tile6 = new Tile();
         Tile tile7 = new Tile();
         Tile tile8 = new Tile();
         Tile tile9 = new Tile();

        ArrayList<Tile> listOfBanks = new ArrayList<>();
        listOfBanks.add(tile1);
        listOfBanks.add(tile2);
        listOfBanks.add(tile3);
        listOfBanks.add(tile4);


        Tile[] finalArray = {tile1,tile2,tile3, tile4,tile5,tile6, tile7,tile8,tile9};

        Tile[] randomArray = {tile1,tile2,tile3, tile4,tile5,tile6, tile7,tile8,tile9};

        Tile[][] newArray = {

        };

        boolean isEqual = finalArray.equals(randomArray);
        System.out.println(isEqual);


        for(int i = 0 ; i<listOfBanks.size();i++){
            System.out.println(tile1.getLeft());
        }

        /* newBoard[row - 1][column] = field.pole.get(row-1).getTop();
                    newBoard[row][column - 1] = field.pole.get(row-1).getLeft();
                    newBoard[row + 1][column] = field.pole.get(row-1).getBottom();
                    newBoard[row][column + 1] = field.pole.get(row-1).getRightt();*/

    }
}
