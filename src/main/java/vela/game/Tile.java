package vela.game;

public class Tile {

    private int x;
    private int y;
    private boolean isOccupied = false;
    TileItem item = null;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void placeItem(TileItem item){
        this.item = item;
        this.isOccupied = true;
        item.setTile(this);
    }

    public void clear(){
        this.item.setTile(null);
        this.item = null;
        this.isOccupied = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public TileItem getItem() {
        return item;
    }

    public Unit getUnit() {
        if (item instanceof Unit)
            return (Unit)item;
        return Unit.NOT_FOUND;
    }
}
