package vela.auth;

import vela.config.Credentials;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Authenticator {

    private static List<Credentials> validCredentials = new ArrayList<>(Arrays.asList(Credentials.USER_1, Credentials.USER_2, Credentials.USER_EMPTY));

    public static boolean isValid(String userName, String password){
        return validCredentials.stream()
                .filter(c -> c.getName().equals(userName) && c.getPassword().equals(password))
                .findFirst().isPresent();
    }
}
