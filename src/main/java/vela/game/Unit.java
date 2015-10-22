package vela.game;

public class Unit extends TileItem {
    public final static Unit NOT_FOUND = new Unit();

    private String name = "";
    private int speed = 0;
    private int attackPower = 0;
    private int defense = 0;
    private int health = 0;


    public Unit (String name, int speed, int attackPower, int defense, int health, String image){
        this.name = name;
        this.speed = speed;
        this.attackPower = attackPower;
        this.defense = defense;
        this.health = health;
        this.image = image;
    }

    public Unit(){}

    public String getName(){
        return name;
    }

    public int getSpeed(){
        return speed;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }


}
