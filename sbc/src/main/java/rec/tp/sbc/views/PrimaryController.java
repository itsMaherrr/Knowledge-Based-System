package rec.tp.sbc.views;

import java.io.IOException;
import javafx.fxml.FXML;
import rec.tp.sbc.main.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
