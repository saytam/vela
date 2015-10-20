package vela.game;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import vela.maps.VelaMap;

import java.util.ArrayList;
import java.util.List;

public class Board {

    VelaMap map;

    public enum StartPosition {
        LEFT,
        RIGHT
    }

    public Board(VelaMap map){
        this.map = map;
    }

    public Tile tileAtPosition(Position pos) {
        int position = (pos.getY() * (map.getWidth() - 1)) + pos.getX() + pos.getY();
        return map.getTiles().get(position);
    }

    public boolean move(Position from, Position to){
        Tile startTile = tileAtPosition(from);
        Unit unit = startTile.getUnit();

        if(unit == Unit.NOT_FOUND || !placeUnit(unit, to) || !DistanceCalculator.canMove(unit, from, to)){
            return false;
        }
        startTile.clear();
        return true;
    }

    public boolean attack(Position from, Position target){
        Tile startTile = tileAtPosition(from);
        Unit unit = startTile.getUnit();
        if (unit == Unit.NOT_FOUND || !DistanceCalculator.canAttack(unit, from, target)){
            return false;
        }
        Tile targetTile = tileAtPosition(target);
        targetTile.clear();
        return true;
    }

    public void placeUnits(List<Unit> units, StartPosition position){
        int x = position == StartPosition.LEFT ? 0 : map.getWidth() - 1;
        for (int i = 0; i < units.size(); i++) {
            placeUnit(units.get(i), new Position(x, i));
        }
    }

    public boolean placeUnit(Unit unit, Position pos){
        Tile tile = tileAtPosition(pos);
        if (tile.isOccupied()){
            return false;
        }
        tile.placeItem(unit);
        return true;
    }

    public Group draw() {
        Group mapGroup = new Group();
        int size = 20;
        double height = size * 2;
        double factor = 0.86;
        double width = Math.floor(height*factor);


        map.getTiles().forEach(tile -> {
            StackPane tileStack = new StackPane();
            Polygon hex = new Polygon();
            double x_offset = tile.getX()*width;
            double y_offset = 0.75*tile.getY()*height;


            if (tile.getY()%2 != 0){
                x_offset += width/2;
            }
            tileStack.setLayoutX(x_offset);
            tileStack.setLayoutY(y_offset);

            hex.getPoints().addAll( x_offset, y_offset+height/4.0,
                    x_offset+width/2.0,  y_offset,
                    x_offset+width,  y_offset+height/4.0,
                    x_offset+width,  y_offset+3*height/4,
                    x_offset+width/2.0,  y_offset+height,
                    x_offset,  y_offset+3*height/4);

            hex.setFill(getPatternForCode(tile.getTerrain()));
            tileStack.setOnMouseEntered(event -> highlight(hex));
            tileStack.setOnMouseExited(event -> unHighlight(hex));

            tileStack.getChildren().add(hex);
            Position pos = new Position(tile.getX(), tile.getY());



            if (tileAtPosition(pos).isOccupied()){
                TileItem tileItem = tile.getItem();
                ImageView imageView = new ImageView("assets/images/dragon.png");
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                imageView.setX(x_offset);
                imageView.setY(y_offset);
                tileStack.getChildren().add(imageView);

            }

//            Label cartCoords = new Label(pos.getX()+","+pos.getY());
//            cartCoords.setFont(new Font(8));
//            cartCoords.setWrapText(true);
//            cartCoords.setLayoutX(x_offset+10);
//            cartCoords.setLayoutY(y_offset+10);
//
//            Label cubeCoords = new Label(pos.getCubeX()+","+pos.getCubeY()+","+pos.getCubeZ());
//            cubeCoords.setFont(new Font(8));
//            cubeCoords.setWrapText(true);
//            cubeCoords.setLayoutX(x_offset+10);
//            cubeCoords.setLayoutY(y_offset+20);
            mapGroup.getChildren().add(tileStack);
        });
        return mapGroup;
    }

    private void highlight(Shape source) {
        source.setEffect(new Lighting());
    }
    private void unHighlight(Shape source){
        source.setEffect(null);
    }

    private ImagePattern getPatternForCode(String patterCode){
        switch (patterCode){
            case "a":
                return new ImagePattern(new Image("assets/images/grass1.png"));
            case "b":
                return new ImagePattern(new Image("assets/images/sand1.png"));
            default:
                return new ImagePattern(new Image("assets/images/forest1.png"));
        }
    }
}
