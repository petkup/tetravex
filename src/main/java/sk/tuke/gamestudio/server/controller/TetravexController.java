package sk.tuke.gamestudio.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.tuke.gamestudio.game.tetravex.core.Field;
import sk.tuke.gamestudio.game.tetravex.core.TileState;

@Controller
@RequestMapping("/tetravex")
public class TetravexController {
    private Field field = new Field(9,9);
    private String color = "white";
    private String topColor;
    private String bottomColor;
    private String rightColor;
    private String leftColor;

    @RequestMapping
    public String tetravex(){
        return "tetravex";
    }

    public String getHtmlField(){
        StringBuilder sb = new StringBuilder();

        //Pole 1
        sb.append("<div class = \"chessboard\">\n");
        for (int row = 0; row < field.getRowCount(); row++) {

            topColor = chceckEdgeColor(field.pole.get(row).getTop(), topColor);
            bottomColor = chceckEdgeColor(field.pole.get(row).getBottom(), bottomColor);
            rightColor = chceckEdgeColor(field.pole.get(row).getRightt(), rightColor);
            leftColor = chceckEdgeColor(field.pole.get(row).getLeft(), leftColor);

            if(field.pole.get(row).getState() == TileState.EMPTY){
                sb.append("<div class=\"emptyPiece\"></div>");
            }
            else {
                sb.append("<div class=\"tetravexpiece\"></div>");
            }
        }
        sb.append("</div>\n");

        //Pole 2
        sb.append("<div class = \"chessboard\">\n");
        for (int row = 0; row < field.getRowCount(); row++) {
            if(field.newPole.get(row).getState() == TileState.EMPTY){
                sb.append("<div class=\"emptyPiece\"></div>");
            }
            else {
                sb.append("<div class=\"tetravexpiece\"></div>");
            }
        }
        sb.append("</div>\n");

        return sb.toString();
    }

    public String getCss(){
        StringBuilder sb = new StringBuilder();

        //topColor = chceckEdgeColor(field.pole.get(0).getTop(), topColor);

        sb.append("<style>\n");
        sb.append (String.format(".tetravexpiece{\n" +
                "    float: left;\n" +
                "    width: 0px;\n" +
                "    height: 0px;\n" +
                "    background-color: #d20005;\n" +
                "    border-top: 40px solid %s", topColor + ";" + "\n"));
        sb.append (String.format("    border-bottom: 40px solid %s", bottomColor + ";" + "\n"));
        sb.append (String.format("    border-right: 40px solid %s", rightColor + ";" + "\n"));
        sb.append (String.format("    border-left: 40px solid %s", leftColor + ";" + "\n"));
        sb.append("    display: table-cell;\n" +
                "    vertical-align:middle;\n" +
                "}\n");
        sb.append("</style>\n");
        return sb.toString();
    }

    @RequestMapping("/new")
    public String newGame() {
        field = new Field(9, 9);
        return "tetravex";
    }

    public String chceckEdgeColor(int edgeValue, String color){
        if (edgeValue == 1){
            color = "brown";
        }
        else if (edgeValue == 2){
            color = "red";
        }
        else if (edgeValue == 3){
            color = "orange";
        }
        else if (edgeValue == 4){
            color = "yellow";
        }
        else if (edgeValue == 5){
            color = "green";
        }
        else if (edgeValue == 6){
            color = "blue";
        }
        else if (edgeValue == 7){
            color = "purple";
        }
        else if (edgeValue == 8){
            color = "black";
        }
        else if (edgeValue == 9){
            color = "white";
        }
        return color;
    }

}
