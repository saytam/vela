package vela.game;

public class DistanceCalculator {

    public static boolean canMove(Unit unit, Position from, Position to) {
        int dX = to.getX() - from.getX();
        int dY = to.getY() - from.getY();
        return unit.getSpeed() >= Math.max(Math.abs(dX), Math.abs(dY));
    }

    public static boolean canAttack(Unit unit, Position from, Position to) {
        int dX = from.getX() - to.getX();
        int dY = from.getY() - to.getY();
        return 1 == Math.max(Math.abs(dX), Math.abs(dY));
    }
}
