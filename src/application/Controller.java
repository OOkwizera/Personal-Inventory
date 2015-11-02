package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {
  
	@FXML
	Tab Physical; 
	@FXML
	Pane physicalTable;
	@FXML
	Tab Social; 
	@FXML
	Pane socialTable;
	@FXML
	Tab Mental; 
	@FXML
	Pane mentalTable;
	@FXML
	Tab Evaluation;
	@FXML
	Pane evaluationTable;
	
	@FXML
	DatePicker datePT;
	@FXML
	TextField meals;
	@FXML
	TextField exercise;
	@FXML
	TextField sleep;
	@FXML
	Button analyzePhysical;
	@FXML
	Button savePhysical;
	@FXML
	DatePicker dateST;
	@FXML
	TextField chat;
	@FXML
	TextField socialMedia;
	@FXML
	TextField fun;
	@FXML
	Button analyzeSocial;
	@FXML
	Button saveSocial;
	@FXML
	DatePicker dateMT;
	@FXML
	TextField tasks;
	@FXML
	TextField personalProjects;
	@FXML
	TextField helpTime;
	@FXML
	Button analyzeMental;
	@FXML
	Button saveMental;
	@FXML
	DatePicker dateET;
	@FXML
	ChoiceBox<Integer> productivityRate = new ChoiceBox<Integer>();
	@FXML
	ChoiceBox<Integer> happinessRate = new ChoiceBox<Integer>();
	@FXML
	ChoiceBox<Integer> stressRate = new ChoiceBox<Integer>();
	@FXML
	Button saveEval;
	
	final ArrayList<Integer> ints = new ArrayList<Integer>(Arrays.asList(0, 1,2,3,4,5,6,7,8,9,10));
	ObservableList<Integer> ratings = FXCollections.observableArrayList();
	
	@FXML
	void initialize() {
		ratings.addAll(ints);
		productivityRate.setItems(ratings);
		happinessRate.setItems(ratings);
		stressRate.setItems(ratings);
	
	}	
	
	
	
}
