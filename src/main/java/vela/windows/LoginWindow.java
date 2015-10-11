package vela.windows;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import vela.auth.Authenticator;

public class LoginWindow {
    Stage stage = new Stage();
    StringProperty name = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    boolean success = false;

    public LoginWindow(){
    }

    public boolean login(){

        Label nameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");


        TextField nameField = new TextField();
        nameField.textProperty().bindBidirectional(name);
        nameField.setText("");

        PasswordField passwordField = new PasswordField();
        passwordField.textProperty().bindBidirectional(password);
        passwordField.setText("");

        GridPane root = new GridPane();

        root.addRow(0, nameLabel, nameField);
        root.addRow(1, passwordLabel, passwordField);
        Button loginButton = new Button("Log in");
        loginButton.setOnMouseClicked(event -> attemptLogin());
        root.addRow(2, loginButton);


        Scene loginScene = new Scene(root);
        loginScene.getStylesheets().add("assets/css/main.css");
        stage.setScene(loginScene);
        stage.showAndWait();

        return success;
    }

    private void attemptLogin(){
        System.out.println("Login attempted");
        if (Authenticator.isValid(name.getValue(), password.getValue())){
            System.out.println("Login successful");
            success = true;
            stage.close();
        } else {
            System.out.println("Login failed");
            success = false;
        }
    }
}
