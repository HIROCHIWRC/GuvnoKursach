package com.gui.utils;

import com.gui.enums.ScenePath;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Loader {
    public static void loadSceneWithThrowException(Stage stage, ScenePath ScenePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Loader.class.getResource(ScenePath.getPathToFxml()));
        Parent root = loader.load();

        stage.setResizable(false);

        stage.setScene(new Scene(root));

    }

    public static void loadScene(Stage stage, ScenePath ScenePath) {
        try {
            loadSceneWithThrowException(stage, ScenePath);
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.error("Navigation Error", "Could not navigate.");
        }
    }
}
