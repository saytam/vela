package vela.windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import vela.game.Board;
import vela.game.Position;
import vela.game.Unit;
import vela.game.Units;
import vela.maps.MapReader;
import vela.maps.VelaMap;
import vela.ui.Renderer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Remote;
import java.util.List;

import static java.util.Arrays.asList;

public class MainWindow {

    private Stage stage;

    public MainWindow(Stage stage){
        this.stage = stage;
    }

    public void show() throws URISyntaxException, IOException {
        String mapName = "level1";
        VelaMap map = createMap(mapName);

        Board board = new Board(map);

        board.placeUnits(asList(Units.dragon(), Units.knight()), Board.StartPosition.LEFT);
        board.placeUnits(asList(Units.knight(), Units.dragon()), Board.StartPosition.RIGHT);

        Renderer renderer = new Renderer();
        Group drawnBoard = renderer.draw(board);

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
