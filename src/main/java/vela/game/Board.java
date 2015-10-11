package vela.game;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int TILES_X = 10;
    public static final int TILES_Y = 10;

    private List<Tile> tiles = new ArrayList<>();

    public enum StartPosition {
        LEFT,
        RIGHT
    }

    public Board(){
        init();
    }

    public void init(){
        for (int y = 0; y < TILES_Y; y++) {
            for (int x = 0; x < TILES_X; x++) {
                tiles.add(new Tile(x, y));
            }
        }
    }

    public Tile tileAtPosition(Position pos) {
        int position = (pos.getY() * (TILES_X - 1)) + pos.getX() + pos.getY();
        return tiles.get(position);
    }

    public boolean move(Position from, Position to){
        Tile startTile = tileAtPosition(from);
        Unit unit = startTile.getUnit();

        if(unit == Unit.NOT_FOUND || !placeUnit(unit, to) || !DistanceCalculator.canMove(unit, from, to)){
            return false;
        }
        startTile.clear();
        return true;
    }

    public boolean attack(Position from, Position target){
        Tile startTile = tileAtPosition(from);
        Unit unit = startTile.getUnit();
        if (unit == Unit.NOT_FOUND || !DistanceCalculator.canAttack(unit, from, target)){
            return false;
        }
        Tile targetTile = tileAtPosition(target);
        targetTile.clear();
        return true;
    }

    public void placeUnits(List<Unit> units, StartPosition position){
        int x = position == StartPosition.LEFT ? 0 : TILES_X - 1;
        for (int i = 0; i < units.size(); i++) {
            placeUnit(units.get(i), new Position(x, i));
        }
    }

    public boolean placeUnit(Unit unit, Position pos){
        Tile tile = tileAtPosition(pos);
        if (tile.isOccupied()){
            return false;
        }
        tile.placeItem(unit);
        return true;
    }
}
