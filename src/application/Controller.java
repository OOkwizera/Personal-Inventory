package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import application.database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Controller {
	
	private Database data = new Database();
  
	@FXML
	TabPane tabs;
	
	@FXML
	DatePicker datePT;
	@FXML
	TextField meals;
	@FXML
	TextField exercise;
	@FXML
	TextField sleep;
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
	@FXML
	ChoiceBox<String> xAxis;
	@FXML
	ChoiceBox<String> yAxis;
	@FXML
	LineChart<Number, Number> graph;
	@FXML
	Button analyze;
	
	
	final ArrayList<Integer> ints = new ArrayList<Integer>(Arrays.asList(0, 1,2,3,4,5,6,7,8,9,10));
	ObservableList<Integer> ratings = FXCollections.observableArrayList();
	
	final ArrayList<String> Xaxis = new ArrayList<String>(Arrays.asList("Meals", "Exercise", 
			"Sleep", "Chat", "Social Media", "Fun", "Tasks Completed", 
			"Personal Projects", "Help Time"));
	final ArrayList<String> Yaxis = new ArrayList<String>(Arrays.asList("Productivity", 
			"Happiness", "Stress"));
	ObservableList<String> xcategories = FXCollections.observableArrayList();
	ObservableList<String> ycategories = FXCollections.observableArrayList();
	
	@FXML
	void initialize()  {
		ratings.addAll(ints);
		productivityRate.setItems(ratings);
		happinessRate.setItems(ratings);
		stressRate.setItems(ratings);
		
		xcategories.addAll(Xaxis);
		xAxis.setItems(xcategories);
		
		ycategories.addAll(Yaxis);
		yAxis.setItems(ycategories);
		
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
		try {
			String date = datePT.getValue().toString();
			int numMeals = Integer.parseInt(meals.getText());
			int exercTime = Integer.parseInt(exercise.getText());
			int sleepTime = Integer.parseInt(sleep.getText());
			String cmd = getCommand(date, numMeals, exercTime, sleepTime);
			data.updateTable("INSERT INTO Physical VALUES" + cmd);
			setDates();
			getNextTab();
			clearTexts(meals, exercise, sleep);
		} catch (Exception e) {
			missingInfo();
		}
		
	}
	
	@FXML 
	void savetoSTable() throws SQLException {
		try {
			String date = dateST.getValue().toString();
			int chatTime = Integer.parseInt(chat.getText());
			int socialMediaTime = Integer.parseInt(socialMedia.getText());
			int funTime = Integer.parseInt(fun.getText());
			String cmd = getCommand(date, chatTime, socialMediaTime, funTime);
			data.updateTable("INSERT INTO Social VALUES" + cmd);
			clearTexts(chat, socialMedia, fun);
			getNextTab();
		} catch (Exception e) {
			missingInfo();
		}
		
	}
	
	@FXML
	void savetoMTable() throws SQLException{
		try {
			String date = dateMT.getValue().toString();
			int numTasks = Integer.parseInt(tasks.getText());
			int personalTime = Integer.parseInt(personalProjects.getText());
			int help = Integer.parseInt(helpTime.getText());
			String cmd = getCommand(date, numTasks, personalTime, help);
			data.updateTable("INSERT INTO Mental VALUES" + cmd);
			getNextTab();
			clearTexts(tasks, personalProjects, helpTime);
		} catch(Exception e) {
			missingInfo();
		}
		
		
	}
	
	@FXML 
	void savetoEvalTable() throws SQLException{
		try {
			String date = dateET.getValue().toString();
			int pScore = productivityRate.getValue();
			int hScore = happinessRate.getValue();
			int sScore = stressRate.getValue();
			String cmd = getCommand(date, pScore, hScore, sScore);
			data.updateTable("INSERT INTO Evaluation VALUES" + cmd);
			clearChoiceBoxes(productivityRate, happinessRate, stressRate);
			getNextTab();
		} catch(Exception e) {
			missingInfo();
		}
				
		
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
	
	void clearChoiceBoxes(ChoiceBox<Integer> b1, ChoiceBox<Integer> b2, ChoiceBox<Integer> b3) {
		b1.getSelectionModel().clearSelection();
		b2.getSelectionModel().clearSelection();
		b3.getSelectionModel().clearSelection();
	}
	
	
	void missingInfo() {
		Alert a  =  new Alert(AlertType.ERROR);
		a.setContentText("Please, Enter all Required Information in the right formats");
		a.showAndWait();
		
	}
	
	@FXML
	void getNextTab() {
		tabs.getSelectionModel().selectNext();
	}
	
	@FXML
	public XYChart.Series<Number, Number> getSeries() {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		if (validAxes()) {
			String columnX = xAxis.getValue();
			String columnY = yAxis.getValue();
			series.setName(columnX + " vs " + columnY);
			ArrayList<Integer> xData = data.getData(getTable(columnX), columnX);
			ArrayList<Integer> yData = data.getData(getTable(columnY), columnY);
			for (int i = 0; i< Math.min(10, Math.min(xData.size(), yData.size())); i++) {
				series.getData().add(new Data<Number, Number> (xData.get(i), yData.get(i)));
					
			}	
			
		} return series;
		
	}
	
	public boolean validAxes() {
		return (xAxis.getValue() != null && yAxis.getValue() != null);
	}
	
	public String getTable(String column) {
		String tableName = "";
		if (column.equals("Meals") || column.equals("Exercise") || column.equals("Sleep")) {
			tableName += "Physical";
			return tableName;
		} else if ((column.equals("Chat") || column.equals("SocialMedia") || column.equals("Fun"))) {
			tableName += "Social";
			return tableName;
		} else if ((column.equals("tasksCompleted") || column.equals("personalProjects") || column.equals("helpTime"))) {
			tableName += "Mental";
			return tableName;
		} else {
			tableName += "Evaluation";
			return tableName;
		}
		
	} 
	
	@FXML
	void analyze() {
		graph.getData().clear();
		graph.getXAxis().setLabel(xAxis.getValue());
		graph.getYAxis().setLabel(yAxis.getValue());
		graph.getData().add(getSeries());
		graph.setCreateSymbols(false);
			
	}
	
	
}	