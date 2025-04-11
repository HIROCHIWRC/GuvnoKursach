module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires static lombok;

    opens com.controllers to javafx.fxml;
    exports com;
}