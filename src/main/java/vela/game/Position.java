package vela.game;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCubeX(){
        int offset = y/2;
        return x-offset;
    }

    public int getCubeY(){
        return y;
    }

    public int getCubeZ(){
        return -getCubeX() - getCubeY();
    }

}
