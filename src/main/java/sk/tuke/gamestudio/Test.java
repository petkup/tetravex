package sk.tuke.gamestudio;

import sk.tuke.gamestudio.game.tetravex.core.Field;
import sk.tuke.gamestudio.game.tetravex.core.Tile;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.sql.DriverManager;

public class Test {

     public static void main(String[] args) throws Exception {
          try (var connection = DriverManager.getConnection("jdbc:postgresql://localhost/gamestudio", "postgres", "postgres")){
               System.out.println("----------");
          }
     }

     //jdbc:postgresql://host/database

     private Random rand;
     private Field field;
     private int rowCount;
     private int columnCount;

     public Test(){
          rowCount = 9;
          columnCount = 9;
          field = new Field(rowCount, columnCount);
     }

     //chceck if tile state change to fill after putting tile

     //chceck if tile state change to empty after removing tile

     //chceck new field tiles state

     //chceck previous field tile state

     //chceck if fields are equl


}
