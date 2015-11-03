package application.database;

import java.sql.*;
import java.util.ArrayList;

public class Database {

	private Statement statement;
    private Connection connection;
    
    public void initiateDB() throws ClassNotFoundException, SQLException {
    	
    	try {
	    	Class.forName ("org.sqlite.JDBC");
	        connection = DriverManager.getConnection("jdbc:sqlite:testDB.db");
	        statement = connection.createStatement();
	        String command1 = "CREATE TABLE IF NOT EXISTS Physical (Date TEXT, Meals INTEGER, Exercise INTEGER,  Sleep INTEGER)";
	        this.statement.execute(command1);
	        String command2 = "CREATE TABLE IF NOT EXISTS Social (Date TEXT, Chat INTEGER, SocialMedia INTEGER,  Fun INTEGER)";
	        this.statement.execute(command2);
	        String command3 = "CREATE TABLE IF NOT EXISTS Mental (Date TEXT, tasksCompleted INTEGER, personalProjects INTEGER,  helpTime INTEGER)";
	        this.statement.execute(command3);
	        String command4 = "CREATE TABLE IF NOT EXISTS Evaluation (Date TEXT, Productivity INTEGER, Happiness INTEGER,  Stress INTEGER)";
	        this.statement.execute(command4);
    	} catch (SQLException e) {
    		System.err.println(e.getMessage());
    	} 
    	
	        
    }       
   
   public void updateTable(String command) throws SQLException {
	   statement.executeUpdate(command);
   }
   
   public void emptyTable() throws SQLException {
	   try {
		   statement.execute("DELETE FROM Physical");
		   statement.execute("DELETE FROM Social");
		   statement.execute("DELETE FROM Mental");
		   statement.execute("DELETE FROM Evaluation");
		   
	   } catch (Exception e) {
		   System.err.println(e.getClass().getName() + ": " + e.getMessage());
		   System.exit(0);
	   }
   }
   
   public ArrayList<String> getDates() {
	   ArrayList<String> dates = new ArrayList<String>();
	   try {
	   ResultSet result = statement.executeQuery("SELECT Date FROM Physical");
	   while (result.next()) {
		   dates.add(result.getString(0));
	   }
	   }
	   catch (Exception e) {
		   System.err.println(e.getClass().getName() + ": " + e.getMessage());
		   System.exit(0);
	   }
	   return dates;
   }
   
   public ArrayList<Integer> getData(String tableName, String column) {
	   ArrayList<Integer> dataList = new ArrayList<Integer>();
	   try {
		   String cmd = "SELECT " + column + " FROM " + tableName;
		   ResultSet result = statement.executeQuery(cmd);
		   while (result.next()) {
			   dataList.add(result.getInt(column));
	   }
	   }
	   catch (Exception e) {
		   System.err.println(e.getClass().getName() + ": " + e.getMessage());
		   System.exit(0);
	   }
	   return dataList;
   }
   
   
   
   
   

}
