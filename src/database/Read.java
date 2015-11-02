package database;

import java.sql.*;

import application.DataInformation;

// Most useful for reading material from a database
// Make sure that sqlite-jdbc.3.8.11.2.jar is in the Java Build Path
public class Read {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:testdb");
		Statement stat = con.createStatement();

		boolean done = false;
		while (!done) {
			String cmd = DataInformation.getRead();
			if (cmd.equals("quit")) {
				done = true;
			} else {
				int columns = 4;
				if (stat.execute(cmd)){
					ResultSet results = stat.getResultSet();
					while (results.next()) {
						for (int c = 1; c <= columns; ++c) {
							System.out.print(results.getString(c) + "\t");
						}
						System.out.println();
					} 
				}
			}
		}
	}
}

//More of Ferrer's code. I don't know what it means yet.
