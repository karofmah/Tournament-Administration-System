module no.ntnu.idatt1002.k204.tasystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens no.ntnu.idatt1002.k204.tasystem to javafx.fxml;
    exports no.ntnu.idatt1002.k204.tasystem;
    exports no.ntnu.idatt1002.k204.tasystem.controller;
    opens no.ntnu.idatt1002.k204.tasystem.controller to javafx.fxml;
}