package vela.maps;

import vela.game.Tile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MapReader {

    public VelaMap read(Path filePath) throws IOException, URISyntaxException {

        List<Tile> tiles = new ArrayList<>();
        final int[] x = {0};
        final int[] y = {0};
        Files.lines(filePath, StandardCharsets.UTF_8).forEach(line -> {
            x[0] = 0;
            Arrays.asList(line.split(" ")).forEach(token -> {
                tiles.add(new Tile(token, x[0], y[0]));
                x[0]++;
            });
            y[0]++;
        });
        VelaMap map = new VelaMap();
        map.setTiles(tiles);
        map.setHeight(y[0]);
        map.setWidth(x[0]);
        return map;
    }




}
