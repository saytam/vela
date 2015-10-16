package vela.windows;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
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
        Group root = new Group();
//        root.getChildren().add(new Button("hello"));



        int size = 20;
        double height = size * 2;
        double factor = 0.86;
        double width = Math.floor(height*factor);
        double origin_x = width/2;
        double origin_y = height/2;
        ImagePattern grassPattern = new ImagePattern(new Image("assets/images/grass1.png"));
        ImagePattern sandPattern = new ImagePattern(new Image("assets/images/sand1.png"));


        URL resourceUrl = getClass().getResource("/maps/level1.map");

        Path resourcePath = Paths.get(resourceUrl.toURI());


        MapReader mapReader = new MapReader();
        VelaMap map = mapReader.read(resourcePath);

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
            ImagePattern pattern = (tile.getTerrain().equals("a"))? grassPattern : sandPattern;
            hex.setFill(pattern);
            hex.setOnMouseEntered(event -> highlight(hex));
            hex.setOnMouseExited(event -> unHighlight(hex));
            root.getChildren().add(hex);
        });


        for (int x = 0; x < map.getWidth(); x++){
            for (int y = 0; y < map.getHeight(); y++) {


            }
        }



        Scene scene = new Scene(root, 1024, 768, Color.BLACK);

        this.stage.setScene(scene);
        this.stage.show();
    }

    private void highlight(Shape source) {
        source.setEffect(new Lighting());
    }
    private void unHighlight(Shape source){
        source.setEffect(null);
    }
}
