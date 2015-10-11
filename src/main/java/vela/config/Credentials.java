package vela.config;

public class Credentials {

    public static Credentials USER_1 = new Credentials("user1", "user1");
    public static Credentials USER_2 = new Credentials("user2", "user2");
    public static Credentials USER_EMPTY = new Credentials("", "");

    private String name;
    private String password;

    public Credentials(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
