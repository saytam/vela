package vela.windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindow {

    private Stage stage;

    public MainWindow(Stage stage){
        this.stage = stage;

    }

    public void show(){
        Group root = new Group();
        root.getChildren().add(new Button("hello"));
        Scene otherScene = new Scene(root, 1024, 768 );
        this.stage.setScene(otherScene);
        this.stage.show();
    }
}
