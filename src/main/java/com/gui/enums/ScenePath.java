package com.gui.enums;

import lombok.Getter;

@Getter
public enum ScenePath {
    LOGIN("/views/login.fxml"),
    MENU("/views/menu.fxml");

    private final String pathToFxml;

    ScenePath(String pathToFxml) {
        this.pathToFxml = pathToFxml;
    }

    public String getPathToFxml() {
        return pathToFxml;
    }
}
