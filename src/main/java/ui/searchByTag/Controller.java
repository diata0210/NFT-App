package ui.searchByTag;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
	
	@FXML
	private TextField tagField;
	
	@FXML
    private String handleSearchButton() {
        String tag = tagField.getText();
        return tag;
    }
}
