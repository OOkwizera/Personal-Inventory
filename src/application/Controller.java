package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import application.database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {
	
	private Database data = new Database();
  
	@FXML
	TabPane tabs;
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
	void initialize()  {
		ratings.addAll(ints);
		productivityRate.setItems(ratings);
		happinessRate.setItems(ratings);
		stressRate.setItems(ratings);
		
		try {
			data.initiateDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}	
	
	@FXML 
	void savetoPTable() throws SQLException {
		String date = datePT.getValue().toString();
		int numMeals = Integer.parseInt(meals.getText());
		int exercTime = Integer.parseInt(exercise.getText());
		int sleepTime = Integer.parseInt(sleep.getText());
		String cmd = getCommand(date, numMeals, exercTime, sleepTime);
		data.updateTable("INSERT INTO Physical VALUES" + cmd);
		setDates();
		getNextTab();
		clearTexts(meals, exercise, sleep);
	}
	
	@FXML 
	void savetoSTable() throws SQLException {
		String date = datePT.getValue().toString();
		int chatTime = Integer.parseInt(chat.getText());
		int socialMediaTime = Integer.parseInt(socialMedia.getText());
		int funTime = Integer.parseInt(fun.getText());
		String cmd = getCommand(date, chatTime, socialMediaTime, funTime);
		data.updateTable("INSERT INTO Social VALUES" + cmd);
		clearTexts(chat, socialMedia, fun);
		getNextTab();
	}
	
	@FXML
	void savetoMTable() throws SQLException{
		String date = datePT.getValue().toString();
		int numTasks = Integer.parseInt(tasks.getText());
		int personalTime = Integer.parseInt(personalProjects.getText());
		int help = Integer.parseInt(helpTime.getText());
		String cmd = getCommand(date, numTasks, personalTime, help);
		data.updateTable("INSERT INTO Mental VALUES" + cmd);
		getNextTab();
		clearTexts(tasks, personalProjects, helpTime);
		
	}
	
	@FXML 
	void savetoEvalTable() throws SQLException{
		String date = datePT.getValue().toString();
		int pScore = productivityRate.getValue();
		int hScore = happinessRate.getValue();
		int sScore = stressRate.getValue();
		String cmd = getCommand(date, pScore, hScore, sScore);
		data.updateTable("INSERT INTO Evaluation VALUES" + cmd);
		
	}
	
	public String getCommand(String column1, int column2, int column3, int column4) {
		String cmd = " ( " + column1 + ", " + column2 + ", " + column3 + ", " + column4 + " )";
		return cmd;
	}
	
	@FXML
	public void setDates() {
		LocalDate d =datePT.getValue();
		if (!d.toString().equals("")) {
			dateST.setValue(d);
			dateMT.setValue(d);
			dateET.setValue(d);
		}
	}
	
	
	void clearTexts(TextField t1, TextField t2, TextField t3) {
		t1.clear();
		t2.clear();
		t3.clear();
	}
	
	@FXML
	void getNextTab() {
		tabs.getSelectionModel().selectNext();
	}
	
	
	
	
	
}
