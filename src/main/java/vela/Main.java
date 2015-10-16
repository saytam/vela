package vela;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vela.config.GameConfig;
import vela.config.Screens;
import vela.config.UiConfig;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {

        ApplicationContext context = new AnnotationConfigApplicationContext(UiConfig.class, GameConfig.class);
        Screens screens = context.getBean(Screens.class);
        screens.setPrimaryStage(theStage);
//        if (!screens.loginScreen().login()){
//            System.exit(0);
//        }

        try {
            screens.mainWindow().show();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
