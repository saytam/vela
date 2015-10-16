package vela.maps;

import vela.game.Tile;

import java.util.List;

public class VelaMap {

    private int width = 0;
    private int height = 0;
    List<Tile> tiles;

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
