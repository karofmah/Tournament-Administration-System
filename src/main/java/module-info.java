module no.ntnu.idatt1002.k204.tasystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens no.ntnu.idatt1002.k204.tasystem to javafx.fxml;
    exports no.ntnu.idatt1002.k204.tasystem;
    exports no.ntnu.idatt1002.k204.tasystem.controller;
    opens no.ntnu.idatt1002.k204.tasystem.controller to javafx.fxml;
    exports no.ntnu.idatt1002.k204.tasystem.model;
    opens no.ntnu.idatt1002.k204.tasystem.model to javafx.fxml;
    exports no.ntnu.idatt1002.k204.tasystem.dao;
    opens no.ntnu.idatt1002.k204.tasystem.dao to javafx.fxml;
}