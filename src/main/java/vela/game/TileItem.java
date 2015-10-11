package vela.game;

public abstract class TileItem {
    private Tile tile = null;

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
}
