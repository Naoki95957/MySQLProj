package main;

import java.awt.TextArea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlTools {
	
	boolean connected = false;
	Connection conn = null;

	String schema = "";
	String user = "";
	String pass = "";
	TextArea output;
	
	SqlTools(String schema, String user, String pass, TextArea output)
	{
		this.pass = pass;
		this.user = user;
		this.schema = schema;
		this.output = output;
		establishConnection();
	}

	void establishConnection() 
	{
	    try {
	        //Step 1: Load the JDBC driver
	        
	        Class.forName("com.mysql.cj.jdbc.Driver");
	
	        //Connect to the database
	        String url = "jdbc:mysql://localhost:3306/" + schema + "?serverTimezone=UTC&useSSL=TRUE";
	        conn = DriverManager.getConnection(url, user, pass);
	        System.out.println("Successfully logged in");
	        output.append("Successfully logged in\n");
	        connected = true;
	        
	    } catch (ClassNotFoundException e) {
	        System.out.println("Could not load the driver");
	        output.append("Could not load the driver\n");
	        System.out.println("Failed to logged in");
	        output.append("Failed to logged in\n");
	    } catch (SQLException e) { /* ignored */} finally {
	    }
	}
	
	void closeConnections()
	{
        //Close objects
        System.out.println("Terminating");
        output.append("Terminating\n");
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Closed");
        output.append("Closed\n");
	}
	
	ResultSet query(String query_str)
	{
        if(!connected)
        {
        	System.err.println("Not connected to MySQL");
            output.append("Not connected to MySQL\n");
        	return null;
        }
        PreparedStatement p = null;
		try {
			p = conn.prepareStatement(query_str);
	        //Process the ResultSet
	        return p.executeQuery();
		} catch (SQLException e) {
        	System.err.println("Error with query");
            output.append("Error with query\n");
        	System.err.println(e.toString());
        	return null;
		}
	}

	static String readEntry(String prompt) {
	    try {
	        StringBuffer buffer = new StringBuffer();
	        System.out.print(prompt);
	        System.out.flush();
	        int c = System.in.read();
	        while (c != '\n' && c != -1) {
	            buffer.append((char) c);
	            c = System.in.read();
	        }
	        return buffer.toString().trim();
	    } catch (IOException e) {
	        return "";
	    }
	}
	
}
