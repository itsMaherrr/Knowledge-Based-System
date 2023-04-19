package rec.tp.sbc.views;

import java.io.IOException;
import javafx.fxml.FXML;
import rec.tp.sbc.main.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}