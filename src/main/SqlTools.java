package main;

import java.awt.TextArea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JFrame;

public class SqlTools {
	
	List<Thread> threads = new LinkedList<Thread>();
	
	boolean connected = false;
	Connection conn = null;

	String schema = "";
	String user = "";
	String pass = "";
	TextArea output;
	JFrame frame;
	
	GUIWindow parentRef = null;
	
	boolean loggingIn = false;
	
	boolean outputAttached = false;
	
	SqlTools(String schema)
	{
		this.schema = schema;
	}
	
	void setLoginInfo(String username, String password)
	{
		this.user = username;
		this.pass = password;
	}
	
	void setGUIWindow(GUIWindow parentRef)
	{
		this.parentRef = parentRef;
	}
	
	/***
	 * Attach text output stream to SQL tools
	 * @param output
	 */
	void attachOutput(TextArea output)
	{
		this.output = output;
		outputAttached = true;
	}
	
	/***
	 * Remove text output stream from SQL tools
	 */
	void removeOutput()
	{
		outputAttached = false;
	}

	/***
	 * Attach frame to SQL tools
	 * @param frame
	 */
	void attachFrame(JFrame frame)
	{
		this.frame = frame;
		if(loggingIn)
		{
			frame.setTitle(GUIWindow.title + " - logging in...");
		}
		else
		{
			frame.setTitle(GUIWindow.title + " - not logged in...");
		}
	}

	/***
	 * Remove frame from SQL tools
	 */
	void removeFrame()
	{
		this.frame = null;
	}
	
	public void establishConnection()
	{
		if(user.isEmpty())
		{
			if(frame != null)
			{
				frame.setTitle(GUIWindow.title + " - not logged in");
			}
			return;
		}
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
			if(frame != null)
			{
				frame.setTitle(GUIWindow.title + " - We're still trying to login...");
			}
		}
		else if(connected)
		{
			print("Already connected");
			if(frame != null)
			{
				frame.setTitle(GUIWindow.title + " - Logged in");
			}
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
		        if(frame != null)
				{
					frame.setTitle(GUIWindow.title + " - Logging in...");
			    	loginStatus("Logging in as: " + user + "...");
				}
		        conn = DriverManager.getConnection(url, user, pass);
		        connected = conn.isValid(0);
		        print(connected ? "Successfully logged in":"Failed to log in");
		        if(frame != null)
				{
		        	if(connected)
		        	{
						frame.setTitle(GUIWindow.title + " - Logged in");
				    	loginStatus(GUIWindow.user_prompt + user );
		        	}
		        	else
		        	{
		        		frame.setTitle(GUIWindow.title + " - Failed to log in");
				    	loginStatus("Failed to login as: " + user );
		        	}
				}
		        
		    } catch (ClassNotFoundException e) {
		    	print("Failed to load driver");
		    	loginStatus("Failed to load");
        		frame.setTitle(GUIWindow.title + " - Failed to load driver");
		    } catch (SQLException e) {/* ignored */} finally {
		    }
			loggingIn = false;
		}
	}
	
	void loginStatus(String status)
	{
		if(parentRef != null)
		{
			parentRef.currentUserLabel.setText(status);
			parentRef.currentUserLabel.setVisible(true);
		}
	}
	
	void closeConnections()
	{
		print("Terminating...");

		frame.setTitle(GUIWindow.title + " - closing...");
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
    			connected = false;
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
