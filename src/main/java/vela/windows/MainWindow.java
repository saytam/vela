package vela.windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import vela.game.Board;
import vela.game.Position;
import vela.game.Units;
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

        Board board = new Board(map);
        board.placeUnit(Units.dragon(), new Position(2, 3));

        Group drawnBoard = board.draw();

        Scene scene = new Scene(drawnBoard, 1024, 768, Color.BLACK);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private VelaMap createMap(String mapName) throws URISyntaxException, IOException {
        URL resourceUrl = getClass().getResource("/maps/"+mapName+".map");
        Path resourcePath = Paths.get(resourceUrl.toURI());
        MapReader mapReader = new MapReader();
        return mapReader.read(resourcePath);
    }


}
