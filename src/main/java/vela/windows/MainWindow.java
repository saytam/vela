package vela.windows;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import vela.game.Board;
import vela.maps.MapReader;
import vela.maps.VelaMap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainWindow {

    private Stage stage;

    public MainWindow(Stage stage){
        this.stage = stage;

    }

    public void show() throws URISyntaxException, IOException {
        String mapName = "level1";
        VelaMap map = createMap(mapName);
        Group drawnMap = drawHexMap(map);
        Scene scene = new Scene(drawnMap, 1024, 768, Color.BLACK);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private VelaMap createMap(String mapName) throws URISyntaxException, IOException {
        URL resourceUrl = getClass().getResource("/maps/"+mapName+".map");
        Path resourcePath = Paths.get(resourceUrl.toURI());
        MapReader mapReader = new MapReader();
        return mapReader.read(resourcePath);
    }

    private Group drawHexMap(VelaMap map) {
        Group mapGroup = new Group();
        int size = 20;
        double height = size * 2;
        double factor = 0.86;
        double width = Math.floor(height*factor);


        map.getTiles().forEach(tile -> {
            Polygon hex = new Polygon();
            double x_offset = tile.getX()*width;
            double y_offset = 0.75*tile.getY()*height;
            if (tile.getY()%2 != 0){
                x_offset += width/2;
            }
            hex.getPoints().addAll( x_offset, y_offset+height/4.0,
                    x_offset+width/2.0,  y_offset,
                    x_offset+width,  y_offset+height/4.0,
                    x_offset+width,  y_offset+3*height/4,
                    x_offset+width/2.0,  y_offset+height,
                    x_offset,  y_offset+3*height/4);

            hex.setFill(getPatternForCode(tile.getTerrain()));
            hex.setOnMouseEntered(event -> highlight(hex));
            hex.setOnMouseExited(event -> unHighlight(hex));

            Label text = new Label(tile.getX()+","+tile.getY());
            text.setFont(new Font(8));

            text.setWrapText(true);

            text.setLayoutX(x_offset+10);
            text.setLayoutY(y_offset+10);
            mapGroup.getChildren().add(hex);
            mapGroup.getChildren().add(text);
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
