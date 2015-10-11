package vela.config;

import javafx.stage.Stage;
import vela.windows.LoginWindow;
import vela.windows.MainWindow;

public class Screens {

    private Stage stage;

    public void setPrimaryStage(Stage stage){
        this.stage = stage;
    }

    public LoginWindow loginScreen(){
        return new LoginWindow();
    }

    public MainWindow mainWindow(){
        return new MainWindow(this.stage);
    }


}
