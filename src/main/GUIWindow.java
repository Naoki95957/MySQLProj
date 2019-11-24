package main;

import java.awt.FlowLayout;
import java.awt.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIWindow {
	
	//TODO move buttons out so that we have better OOP

	//This is an example of the buttons and UI work I'd like to incorporate
	//This is fairly simple: 
	//	make buttons, make threads, add thread to buttons, add buttons
	//
	//Aside from that, we can go just about anywhere with this.
	
	List<Thread> threads = new LinkedList<Thread>();
	
	//list of previous panels (to go back)
	//last item is always the current panel
	List<JPanel> prevPanels = new LinkedList<JPanel>();
	
	JButton backButton;
	boolean backward = false;
	
	JPanel panel;
	JFrame frame;
	TextArea output;
	SqlTools sql;
	
	public GUIWindow(SqlTools sql)
	{
		this.sql = sql;
		beginGUI();
	}
	
	public void beginGUI()
	{
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		
		frame = new JFrame("International Student Info System");
		frame.setBounds(0, 0, 512, 512);
		frame.setResizable(false);
		frame.setLayout(layout);
		
		//doesn't need to exist, you could just add everything to the frame
		//however its good to put things on a panel so that we can update the UI 
		//or completely replace the panel easily by removing the panel child vs 
		//removing each child from the frame
		panel = new JPanel();
		panel.setLayout(new WrapLayout());
		panel.setBounds(0, 0, frame.getWidth() - 10, frame.getHeight() - 20);
		panel.setMaximumSize(panel.getSize());
		panel.setMinimumSize(panel.getSize());
		panel.setVisible(true);
		
		
		//text area output: using it basically like a terminal
		output = new TextArea();
		output.setEditable(false);
		sql.attachOutput(output);
		
		//instancing of buttons
		backButton = new JButton("Back");
		
		JButton button_a = new JButton("Find highest paid workers.");
		JButton button_b = new JButton("Find the most worked workers.");
		JButton clearText = new JButton("Clear output.");
		JButton reconnect = new JButton("Connect to server.");
		
		JButton quit = new JButton("Quit");
		
		//Action thread for back
		Thread back_action = new Thread(){
			public void run() {goBack();};
		};
		
		//action thread for button_a
		Thread button_a_action = new Thread()
		{
			public void run() {
				ResultSet r = null;
				try {
					r = sql.query("select lname, fname from employee order by salary desc limit 5");
					int count = 0;
					while(r.next())
			        {
			        	String lname = r.getString(1);
			        	String fname = r.getString(2);
			        	output.append("Result from A: " + lname + ", " + fname + "\n");
			        	System.out.println("Result from A: " + lname + ", " + fname);
			        	++count;
			        }
					output.append(count + " total results from A");
					System.out.println(count + " total results from A\n");
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
					r = sql.query("select lname, hours from employee, works_on where hours >= 40 and ssn = essn");
					int count = 0;
					while(r.next())
			        {
			        	String lname = r.getString(1);
			        	double hours = r.getDouble(2);
			        	output.append("Result from B: " + lname + " " + hours + "\n");
			        	System.out.println("Result from B: " + lname + " " + hours);
			        	++count;
			        }
					output.append(count + " total results from B\n");
					System.out.println(count + " total results from B");
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
					if (JOptionPane.showConfirmDialog(
							frame, 
							"Are you sure you want to quit?", 
							"Quit?", 
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					{
						beginClosing(frame, output, sql);
			            System.exit(0);
			        }
			}
		};
		
		//adding threads to list to check on them later
		threads.add(button_a_action);
		threads.add(button_b_action);
		threads.add(reconnect_action);
		threads.add(clear_text_action);
		threads.add(quit_action);
		threads.add(back_action);
		
		//adding actions to the buttons
		backButton.addActionListener(new ButtonAction(back_action));
		button_a.addActionListener(new ButtonAction(button_a_action));
		button_b.addActionListener(new ButtonAction(button_b_action));
		reconnect.addActionListener(new ButtonAction(reconnect_action));
		clearText.addActionListener(new ButtonAction(clear_text_action));
		quit.addActionListener(new ButtonAction(quit_action));
		
		
		
		//setting buttons to visible
		backButton.setVisible(true);
		button_a.setVisible(true);
		button_b.setVisible(true);
		reconnect.setVisible(true);
		clearText.setVisible(true);
		quit.setVisible(true);

		backButton.setEnabled(false);

		//adding to display buttons
		panel.add(button_a);
		panel.add(button_b);
		panel.add(clearText);
		
		//adding text output to display
		panel.add(output);
		frame.setVisible(true);
		
		updatePanel(panel);

		frame.add(backButton);
		frame.add(quit);
		frame.add(reconnect);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent)
		    {
		    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        quit_action.run();
		    }
		    
		});
	}
	
	public void updatePanel(JPanel newPanel)
	{
		prevPanels.add(newPanel);
		newPanel.setVisible(true);
		frame.add(newPanel);
		if(prevPanels.size() > 1)
		{
			frame.remove(prevPanels.get(prevPanels.size() - 1));
			backward = true;
		}
		backButton.setEnabled(!backward);
	}
	
	public void goBack()
	{
		if(prevPanels.size() <= 1)
		{
			return;
		}
		updatePanel(prevPanels.get(prevPanels.size() - 2));
		prevPanels.remove(prevPanels.size() - 1);
		if(prevPanels.size() == 1)
		{
			backward = false;
		}
	}
	
	public void beginClosing(JFrame frame, TextArea output, SqlTools sql)
	{
		sql.closeConnections();
		frame.dispose();
	}
}