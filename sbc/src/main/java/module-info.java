module rec.tp.sbc {
    requires javafx.controls;
    requires javafx.fxml;

    opens rec.tp.sbc to javafx.fxml;
    exports rec.tp.sbc;
}
