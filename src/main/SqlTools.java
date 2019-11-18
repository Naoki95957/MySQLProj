package main;

import java.awt.TextArea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlTools {
	
	List<Thread> threads = new LinkedList<Thread>();
	
	boolean connected = false;
	Connection conn = null;

	String schema = "";
	String user = "";
	String pass = "";
	TextArea output;
	
	boolean loggingIn = false;
	
	boolean outputAttached = false;
	
	SqlTools(String schema, String user, String pass)
	{
		this.pass = pass;
		this.user = user;
		this.schema = schema;
		
		establishConnection();
	}
	
	/***
	 * Attach text output stream from SQL
	 * @param output
	 */
	void attachOutput(TextArea output)
	{
		this.output = output;
		outputAttached = true;
	}
	
	/***
	 * Remove text output stream from SQL
	 */
	void removeOutput()
	{
		outputAttached = false;
	}
	
	public void establishConnection()
	{
		///start new thread to begin login process
		Thread t = new Thread(){
			public void run()
			{
				establishConnectionToSQL();
			}
			
		};
		threads.add(t);
		t.start();
	}
	
	private void establishConnectionToSQL() 
	{
		if(loggingIn)
		{
			print("Already attempting to login");
		}
		else
		{
			loggingIn = true;
			try {
		        //Step 1: Load the JDBC driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		
		        //Connect to the database
		        String url = "jdbc:mysql://localhost:3306/" + schema + "?serverTimezone=UTC&useSSL=TRUE";
		        print("Attempting to login...");
		        conn = DriverManager.getConnection(url, user, pass);
		        connected = conn.isValid(0);
		        print(connected ? "Successfully logged in":"Failed to log in");
		        
		    } catch (ClassNotFoundException e) {
		    	print("Failed to load driver");
		    	print("Failed to log in");
		    } catch (SQLException e) { /* ignored */} finally {
		    }
			loggingIn = false;
		}
	}
	
	void closeConnections()
	{
		print("Terminating...");
		for(Thread t : threads)
		{
			if(t.isAlive())
			{
				try {
					t.join();
				} catch (InterruptedException e) {
					//these threads better not be interrupted...
					e.printStackTrace();
				}
			}
			
		}
		
        //Close objects
        try {
        	if(connected)
        	{
    			conn.close();
        	}
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
		if(outputAttached)
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
