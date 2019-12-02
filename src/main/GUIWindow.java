package main;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class GUIWindow {
	//This is an example of the buttons and UI work I'd like to incorporate
	//This is fairly simple: 
	//	make buttons, make threads, add thread to buttons, add buttons
	//
	//Aside from that, we can go just about anywhere with this.
	
	//Right now the primary way this layout is made:
	
	//(panel) designed to be swapped
	//labels and buttons that are attached to the frame at all times
	
	List<Thread> threads = new LinkedList<Thread>();
	
	List<Component> frameObjects = new LinkedList<Component>();
	
	JButton homeButton;
	JPanel homePanel;
	
	JLabel currentUserLabel;
	JLabel currentSchemaLabel;
	public static final String user_prompt = "Logged in as: ";
	boolean backward = false;
	
	public static final String title = "International Student Info System";
	
	JPanel currentPanel;
	JFrame frame;
	public SqlTools sql;
	
	public GUIWindow(SqlTools sql)
	{
		this.sql = sql;
		sql.setGUIWindow(this);
	}
	
	public void setSchema()
	{
		String schema = "";
		try 
		{
			Object IPA = JOptionPane.showInputDialog(null, "Please enter the schema:", "Enter Schema", JOptionPane.PLAIN_MESSAGE);
			if(!((String)IPA).isEmpty()){
				schema = (String)IPA;
				System.out.println(schema);
			}
			updateSchemaLabel("Current Schema: " + schema);
			sql.setSchema(schema);
			sql.establishConnection();
		}
		catch(Exception e)
		{
			int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Canceled",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION){
					return;
				}
				else
				{
					setSchema();
				}
		}
	}
	
	public void setUser()
	{
		String user = "";
		String password = "";
		try 
		{
			//user
			Object IPA = JOptionPane.showInputDialog(null, "Please enter your username:", "Enter Username", JOptionPane.PLAIN_MESSAGE);
			if(!((String)IPA).isEmpty()){
				user = (String)IPA;
				System.out.println(user);
			}
			//pass
			JPasswordField passField = new JPasswordField();
			JOptionPane.showConfirmDialog(null, passField, "Please enter your password: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(passField.getPassword().length != 0){
				password = String.copyValueOf(passField.getPassword());
				System.out.println(password);
			}

			sql.setLoginInfo(user, password);
			sql.establishConnection();
		}
		catch(Exception e)
		{
			int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Canceled",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION){
					return;
				}
				else
				{
					setUser();
				}
		}
	}
	
	//only called once
	public void beginGUI(PanelBuilder builder)
	{
		beginGUI(builder.getPanel());
	}
	//only called once
	public void beginGUI(JPanel homePanel)
	{
		this.homePanel = homePanel;
		
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);
		layout.setHgap(500);
		
		frame = new JFrame(title);
		frame.setLocationRelativeTo(null);
		frame.setBounds(frame.getBounds().x - 256, frame.getBounds().y - 256, 512, 512);
		frame.setResizable(false);
		frame.setLayout(layout);
		sql.attachFrame(frame);
		
		//instancing of buttons
		homeButton = new JButton("Main Menu");
		
		JButton reconnect = new JButton("Connect to server.");
		JButton changeUser = new JButton("Change user/sign-in");
		JButton changeSchema = new JButton("Change Schema");
		JButton quit = new JButton("Quit");

		currentSchemaLabel = new JLabel("Current Schema: " + sql.schema);
		currentSchemaLabel.setVisible(true);
		
		currentUserLabel = new JLabel();
		if(sql.connected)
		{
			currentUserLabel.setText(user_prompt + sql.user);
		}
		else
		{
			currentUserLabel.setText("Not logged in");
		}
		
		//Action thread for home
		Thread home_action = new Thread()
		{
			public void run() {
				goHome();
			}
		};
		
		Thread changeUser_action = new Thread()
		{
			public void run()
			{
				sql.closeConnections();
				setUser();
				sql.establishConnection();
			}
		};
		
		Thread changeSchema_action = new Thread()
		{
			public void run()
			{
				
				sql.closeConnections();
				setSchema();
				sql.establishConnection();
			}
		};
		
		
		//action thread for reconnection
		Thread reconnect_action = new Thread()
		{
			public void run()
			{
				if(!sql.user.isEmpty())
					sql.establishConnection();
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
						beginClosing(frame, sql);
			            System.exit(0);
			        }
			}
		};
		
		//adding threads to list to check on them later
		threads.add(reconnect_action);
		threads.add(quit_action);
		threads.add(changeSchema_action);
		threads.add(home_action);
		
		//adding actions to the buttons
		homeButton.addActionListener(new ButtonAction(home_action));
		reconnect.addActionListener(new ButtonAction(reconnect_action));
		changeUser.addActionListener(new ButtonAction(changeUser_action));
		changeSchema.addActionListener(new ButtonAction(changeSchema_action));
		quit.addActionListener(new ButtonAction(quit_action));
		
		
		
		//setting buttons to visible
		homeButton.setVisible(true);
		reconnect.setVisible(true);
		changeUser.setVisible(true);
		changeSchema.setVisible(true);
		quit.setVisible(true);

		homeButton.setEnabled(false);

		updatePanel(homePanel);

		frameObjects.add(new JLabel("Settings:"));
		frameObjects.add(homeButton);
		frameObjects.add(quit);
		frameObjects.add(reconnect);
		frameObjects.add(changeUser);
		frameObjects.add(changeSchema);
		frameObjects.add(currentSchemaLabel);
		frameObjects.add(currentUserLabel);
		
		addAll();
		
		frame.setVisible(true);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent)
		    {
		    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        quit_action.run();
		    }
		    
		});
	}
	
	public void addAll()
	{
		for(Component c : frameObjects)
		{
			frame.add(c);
		}
	}
	
	public void removeAll()
	{
		for(Component c : frameObjects)
		{
			frame.remove(c);
		}
	}
	
	//update the label for the status at the bottom of the window
	public void updateLoginLabel(String status)
	{
		currentUserLabel.setText(status);
	}
	
	public void updateSchemaLabel(String status)
	{
		currentSchemaLabel.setText(status);
		if(frame != null)
		{
			frame.repaint();
		}
	}
	
	public void updatePanel(PanelBuilder panelBuilder)
	{
		updatePanel(panelBuilder.getPanel());
		if(frame != null)
		{
			frame.repaint();
		}
	}
	
	public void updatePanel(JPanel newPanel)
	{
		if(currentPanel == null)
		{
			currentPanel = newPanel;
			frame.add(currentPanel);
			addAll();
		}
		else
		{
			removeAll();
			frame.remove(currentPanel);
			currentPanel = newPanel;
			if(currentPanel != homePanel)
			{
				homeButton.setEnabled(true);
			}
			else
			{
				homeButton.setEnabled(false);
			}
			frame.add(currentPanel);
			addAll();
		}
		frame.setVisible(true);
		frame.repaint();
	}
	
	public void goHome()
	{
		updatePanel(homePanel);
	}
	
	public void beginClosing(JFrame frame, SqlTools sql)
	{
		sql.closeConnections();
		frame.dispose();
	}
}