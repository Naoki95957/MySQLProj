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
	
	Thread t = new Thread();
	
	SqlTools(String schema, String user, String pass, TextArea output)
	{
		this.pass = pass;
		this.user = user;
		this.schema = schema;
		this.output = output;
		t = new Thread(){
			public void run()
			{
				establishConnection();
			}
		};
		t.start();
	}

	void establishConnection() 
	{
	    try {
	        //Step 1: Load the JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	
	        //Connect to the database
	        String url = "jdbc:mysql://localhost:3306/" + schema + "?serverTimezone=UTC&useSSL=TRUE";
	        System.out.println("");
	        print("Attempting to login...");
	        conn = DriverManager.getConnection(url, user, pass);
	        print("Successfully logged in");
	        connected = true;
	        
	    } catch (ClassNotFoundException e) {
	    	print("Failed to load driver");
	    	print("Failed to log in");
	    } catch (SQLException e) { /* ignored */} finally {
	    }
	}
	
	void closeConnections()
	{
		if(t.isAlive())
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        //Close objects
		print("Terminating...");
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        print("Closed");
	}
	
	ResultSet query(String query_str)
	{
        if(!connected)
        {
        	print("Not connected to MySQL server");
        	return null;
        }
        PreparedStatement p = null;
		try {
			p = conn.prepareStatement(query_str);
	        //Process the ResultSet
	        return p.executeQuery();
		} catch (SQLException e) {
			print("Error with query");
        	System.err.println(e.toString());
        	return null;
		}
	}
	
	void print()
	{
		print("");
	}
	void print(String text)
	{
		System.out.println(text);
		output.append(text + "\n");
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
