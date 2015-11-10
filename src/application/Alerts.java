package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Alerts {

	public int getInfo(String info){
	  		if(info == "") {
	  			badNews();
	  		}
	  		return Integer.parseInt(info);
	  }
	
	 void badNews() {
		Alert badNum = new Alert(AlertType.ERROR);
		badNum.setContentText("A value must be entered into " +
			"all the text boxes before you can click on 'Next'.");
		badNum.show();
	}
}
