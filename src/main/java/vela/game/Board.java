package vela.game;

import vela.maps.VelaMap;

import java.util.ArrayList;
import java.util.List;

public class Board {

    VelaMap map;

    public enum StartPosition {
        LEFT,
        RIGHT
    }

    List<Unit> units = new ArrayList<>();

    public Board(VelaMap map){
        this.map = map;
    }

    public Tile tileAtPosition(Position pos) {
        int position = (pos.getY() * (map.getWidth() - 1)) + pos.getX() + pos.getY();
        return map.getTiles().get(position);
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
        this.units.addAll(units);
        int x = position == StartPosition.LEFT ? 0 : map.getWidth() - 1;
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

    public Unit currentUnit(){
        if (units.size() == 0){
            return Unit.NOT_FOUND;
        }
        return units.get(0);
    }

    public VelaMap getMap() {
        return map;
    }

}
