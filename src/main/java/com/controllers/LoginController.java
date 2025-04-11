package com.controllers;

import com.gui.enums.ScenePath;
import com.gui.utils.Loader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    void onLoginButton(ActionEvent event){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Loader.loadScene(stage, ScenePath.MENU);
    }
}
