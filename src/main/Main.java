package main;

import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;


public class Main {
	
	static //This is an example of the buttons and UI work I'd like to incorporate
	//This is fairly simple: 
	//	make buttons, make threads, add thread to buttons, add buttons
	//
	//Aside from that, we can go just about anywhere with this.
	
	List<Thread> threads = new LinkedList<Thread>();
	
	public static void main(String [] args)
	{
		
		JFrame frame = new JFrame("Query Options");
		frame.setSize(512, 300);
		
		//doesn't need to exist, you could just add everything to the frame
		//however its good to put things on a panel so that we can update the UI 
		//or completely replace the panel easily by removing the panel child vs 
		//removing each child from the frame
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setVisible(true);
		frame.add(panel);
		
		//text area output: using it basically like a terminal
		TextArea output = new TextArea();
		output.setSize(480, 256);
		output.setEditable(false);

		//creating connection to server and schema
		SqlTools sql = new SqlTools("CompanySchema", "student", "password", output);
		
		//instancing of buttons
		JButton button_a = new JButton("Find highest paid workers.");
		JButton button_b = new JButton("Find the most worked workers.");
		JButton clearText = new JButton("Clear output.");
		JButton reconnect = new JButton("Connect to server.");
		JButton quit = new JButton("Quit.");
		
		
		//action thread for button_a
		Thread button_a_action = new Thread()
		{
			public void run() {
				ResultSet r = null;
				try {
					r = sql.query("select lname, fname from employee order by salary desc limit 5");
					while(r.next())
			        {
			        	String lname = r.getString(1);
			        	String fname = r.getString(2);
			        	output.append("Result from A: " + lname + ", " + fname + "\n");
			        	System.out.println("Result from A: " + lname + ", " + fname);
			        }
				}
				catch (Exception e1)
				{
					System.out.println("Error with query");
					System.err.println(e1.toString());
				}
				finally
				{
					try {
						r.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		};
		
		//action thread for button_b
		Thread button_b_action = new Thread()
		{
			public void run()
			{
				ResultSet r = null;
				try
				{
					r = sql.query("select lname, hours from employee, works_on where hours >= 30 and ssn = essn");
					while(r.next())
			        {
			        	String lname = r.getString(1);
			        	double hours = r.getDouble(2);
			        	output.append("Result from B: " + lname + " " + hours + "\n");
			        }
				}
				catch (Exception e1)
				{
					System.out.println("Error with query");
					System.err.println(e1.toString());
				}
				finally
				{
					try {
						r.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		};
		
		//action thread for reconnection
		Thread reconnect_action = new Thread()
		{
			public void run()
			{
				sql.establishConnection();
			}
		};
		
		//action thread to clear the text output
		Thread clear_text_action = new Thread()
		{
			public void run()
			{
				output.setText("");
			}
		};
		
		//action thread to close the frame and 'begin termination'
		//technically since we're threading, if other threads are still running
		//this will only close the frame, not those threads
		Thread quit_action = new Thread()
		{
			public void run()
			{	
				beginClosing(frame, output);
			}
		};
		
		//adding threads to list to check on them later
		threads.add(button_a_action);
		threads.add(button_b_action);
		threads.add(reconnect_action);
		threads.add(clear_text_action);
		threads.add(quit_action);
		
		//adding actions to the buttons
		button_a.addActionListener(new ButtonAction(button_a_action));
		button_b.addActionListener(new ButtonAction(button_b_action));
		reconnect.addActionListener(new ButtonAction(reconnect_action));
		clearText.addActionListener(new ButtonAction(clear_text_action));
		quit.addActionListener(new ButtonAction(quit_action));
		
		//setting buttons to visible
		button_a.setVisible(true);
		button_b.setVisible(true);
		reconnect.setVisible(true);
		clearText.setVisible(true);
		quit.setVisible(true);

		//adding to display buttons
		panel.add(button_a);
		panel.add(button_b);
		panel.add(reconnect);
		panel.add(clearText);
		panel.add(quit);
		
		//adding text output to display
		panel.add(output);
		frame.setVisible(true);
	}
	
	
	public static void beginClosing(JFrame frame, TextArea output)
	{
		output.setText("Beginning to close\nwaiting for all threads to finish and terminate...");
		for(Thread t : threads)
		{
			if(t.isAlive())
			{
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		frame.dispose();
	}
}

/***
 * 	
 *  This listener is designed for our specific purpose
 *  However you can plug in any runnable and use have it 
 *  execute any thread
 *  
 *  @param button_action
 * 	This is a Runnable object and will takes that thread to execute it when triggered
 *  
 *	@author Naoki 
 */
class ButtonAction implements ActionListener
{
	Runnable button_action;
	
	ButtonAction(Runnable button_action)
	{
		this.button_action = button_action;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		button_action.run();
	}
}