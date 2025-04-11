package com.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    void onAddAccident(ActionEvent event) {
        System.out.println("Добавить ДТП нажата");
        // Тут загрузи сцену добавления
    }

    @FXML
    void onViewAccidents(ActionEvent event) {
        System.out.println("Просмотр ДТП нажата");
        // Тут загрузи сцену просмотра
    }

    @FXML
    void onStatistics(ActionEvent event) {
        System.out.println("Статистика нажата");
        // Тут загрузи сцену со статистикой
    }
}
