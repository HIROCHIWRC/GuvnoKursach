package com;

import com.gui.enums.ScenePath;
import com.gui.utils.AlertUtil;
import com.gui.utils.Loader;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        try {
            Loader.loadSceneWithThrowException(primaryStage, ScenePath.LOGIN);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
