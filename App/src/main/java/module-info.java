module sbc.rec.tp.app {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires com.jfoenix;

    opens sbc.rec.tp.ui.controllers to javafx.fxml, javafx.graphics;
    opens sbc.rec.tp.ui.views to javafx.fxml, javafx.graphics;
    opens sbc.rec.tp.datasets to javafx.base;
    exports sbc.rec.tp.ui.controllers;
}
