package vela.game;

public class Units {

    public static Unit dragon(){
        return new Unit("Dragon", 7, 7, 7, 1, "dragon.png");
    }

    public static Unit knight(){
        return new Unit("Knight", 4, 7, 7, 1, "knight.png");
    }

    public static Unit zombie(){
        return new Unit("Zombie", 2, 7, 7, 1, "zombie.png");
    }
}
