module sbc.rec.tp.app {
    requires javafx.controls;
    requires javafx.fxml;

    opens sbc.rec.tp.controllers to javafx.fxml, javafx.graphics;
    opens sbc.rec.tp.views to javafx.fxml, javafx.graphics;
    exports sbc.rec.tp.controllers;
}
