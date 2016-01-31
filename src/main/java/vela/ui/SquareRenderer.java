package vela.ui;

import javafx.scene.Group;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import vela.game.Board;
import vela.game.Position;
import vela.game.TileItem;

public class SquareRenderer implements Renderer {

    @Override
    public Group draw(Board board) {
        Group mapGroup = new Group();

        double height = 80;
        double width = 80;

        board.getMap().getTiles().forEach(tile -> {
            StackPane tileStack = new StackPane();
            Polygon square = new Polygon();
            double x_offset = tile.getX() * width;
            double y_offset = tile.getY() * height;


            tileStack.setLayoutX(x_offset);
            tileStack.setLayoutY(y_offset);

            square.getPoints().addAll(x_offset, y_offset,
                    x_offset + width, y_offset,
                    x_offset + width, y_offset + height,
                    x_offset, y_offset + height);

            square.setFill(getPatternForCode(tile.getTerrain()));
            tileStack.setOnMouseEntered(event -> highlight(square));
            tileStack.setOnMouseExited(event -> unHighlight(square));

            tileStack.getChildren().add(square);
            Position pos = new Position(tile.getX(), tile.getY());
            if (board.tileAtPosition(pos).isOccupied()) {
                TileItem tileItem = tile.getItem();
                ImageView imageView;
                try {
                    imageView = new ImageView("assets/textures/" + tileItem.getImage());
                } catch (Exception e){
                    imageView = new ImageView("assets/images/placeholder.png");
                }
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                imageView.setX(x_offset);
                imageView.setY(y_offset);
                tileStack.getChildren().add(imageView);
                if (tileItem.equals(board.currentUnit())) {
                    tileStack.setEffect(new Lighting());
                }

            }

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
                return new ImagePattern(new Image("assets/textures/grass1.png"));
            case "b":
                return new ImagePattern(new Image("assets/textures/sand1.png"));
            default:
                return new ImagePattern(new Image("assets/textures/rock1.png"));
        }
    }
}
