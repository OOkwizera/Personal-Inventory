package database;

import java.sql.*;

import application.DataInformation;

// Most useful for creating/adding material to a database
// Make sure sqlite-jdbc-3.8.11.2.jar is in the Java build path
public class Build {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:testdb");
        Statement stat = con.createStatement();

        boolean done = false;
        while (!done) {
            String cmd = DataInformation.getCommand();
            if (cmd.equals("quit")) {
                done = true;
            } else {
                stat.execute(cmd);
            }
        }
    }
}