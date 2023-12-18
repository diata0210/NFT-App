package ui.searchByTime;


//import java.util.ResourceBundle;
import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.util.List;
public class Controller {
	@FXML
    private TextField  daysField, monthsField, yearsField;
	
	
	@FXML
	    private String[] handleSearchButton() {
	        String day = daysField.getText();
	        String month = monthsField.getText();
	        String year = yearsField.getText();
	        String[] dateArray = new String[3];
	        dateArray[0] = day; // element[0]
	        dateArray[1] = month; // element[1]
	        dateArray[2] = year;// element[2]
	        return dateArray;
	    }

}