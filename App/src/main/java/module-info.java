module sbc.rec.tp.app {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens sbc.rec.tp.ui.controllers to javafx.fxml, javafx.graphics;
    opens sbc.rec.tp.ui.views to javafx.fxml, javafx.graphics;
    exports sbc.rec.tp.ui.controllers;
}
