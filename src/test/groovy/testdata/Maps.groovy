package testdata

import vela.game.Tile
import vela.maps.VelaMap


class Maps {

    public static VelaMap aMap(int width, int height){
        VelaMap map = new VelaMap()
        map.height = height
        map.width = width
        map.tiles = createTiles(width, height)
        return map
    }

    private static List<Tile> createTiles(int width, int height) {
        List<Tile> tileList = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String terrain = ((x + y)%3 ) == 0 ? "grass" : "sand"
                tileList.add(new Tile(terrain, x, y));
            }
        }
        return tileList;

    }
}
