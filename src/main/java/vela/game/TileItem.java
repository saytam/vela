package vela.game;

public abstract class TileItem {
    private Tile tile = null;
    protected String image = "";

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public String getImage() {
        return image;
    }
}
