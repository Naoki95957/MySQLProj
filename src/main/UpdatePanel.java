package main;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UpdatePanel extends PanelBuilder{
	public JPanel mainPanel;
	public GUIWindow gui;
	public SqlTools sql;

	public UpdatePanel(GUIWindow gui, SqlTools sql)
	{
		
		this.gui = gui;
		this.sql = sql;
		
		mainPanel = new JPanel();
		GridLayout mainLayout = new GridLayout(0, 3);
		mainLayout.setHgap(5);
		mainLayout.setVgap(4);
		mainPanel.setLayout(mainLayout);
		mainPanel.setBounds(0, 0, 502 * gui.screenSize.width / gui.hScale, 492 * gui.screenSize.height / gui.vScale);
		mainPanel.setMaximumSize(mainPanel.getSize());
		mainPanel.setMinimumSize(mainPanel.getSize());
		mainPanel.setVisible(true);
		
		JPanel optionA = new JPanel();
		JPanel optionB = new JPanel();
		JPanel optionC = new JPanel();

		GridLayout subLayout = new GridLayout(5, 1);
		subLayout.setVgap(2);
		
		optionA.setLayout(subLayout);
		optionB.setLayout(subLayout);
		optionC.setLayout(subLayout);

		JLabel a_label = new JLabel("Add/insert info");
		JButton a1 = new JButton("Add new student"),
				a2= new JButton("Add new course"),
				a3 = new JButton("Add new department"),
				a4 = new JButton("Register a student");

		JLabel b_label = new JLabel("Remove/delete info");
		JButton b1 = new JButton("Delete a student"),
				b2= new JButton("Delete a course"),
				b3 = new JButton("Delete a department"),
				b4 = new JButton("Withdraw a student");

		JLabel c_label = new JLabel("Update info");
		JButton c1 = new JButton("Update a student"),
				c2= new JButton("Update a course"),
				c3 = new JButton("Update a department"),
				c4 = new JButton("Update a registration");

		optionA.add(a_label);
		optionA.add(a1);
		optionA.add(a2);
		optionA.add(a3);
		optionA.add(a4);

		optionB.add(b_label);
		optionB.add(b1);
		optionB.add(b2);
		optionB.add(b3);
		optionB.add(b4);

		optionC.add(c_label);
		optionC.add(c1);
		optionC.add(c2);
		optionC.add(c3);
		optionC.add(c4);

		a1.setEnabled(true);
		a2.setEnabled(true);
		a3.setEnabled(true);
		a4.setEnabled(true);
		
		//TODO enable as they are completed or simply delete/comment these statements
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		
		c1.setEnabled(false);
		c2.setEnabled(false);
		c3.setEnabled(false);
		c4.setEnabled(false);
		
		//TODO add threads and actions to all 12 buttons along with input streams...
		
		mainPanel.add(optionA);	
		mainPanel.add(optionB);	
		mainPanel.add(optionC);
		
		a1.addActionListener(new ButtonAction(new Thread(){
			public void run()
			{
				if(!sql.connected)
				{
					signInMessage();
					return;
				}
				//instructions per input
				String types [] = 
				{
					"SSN", 
					"First Name", 
					"Middle Initial",
					"Last Name", 
					"Brith Date (YYYY-MM-DD)", 
					"Sex (M or F)",
					"Address", 
					"graduate status (undergrad or graduate)", 
					"Visa type",
					"Visa start data (YYYY-MM-DD)", 
					"Visa Experation date (YYYY-MM-DD)", 
					"Enrollment date (YYYY-MM-DD)", 
					"Class (YYYY)"
				};
				//used as a way to identify numbers vs strings
				boolean stringsAt [] =
				{
					false,
					true,
					true,
					true,
					true,
					true,
					true,
					true,
					true,
					true,
					true,
					true,
					true
				};

				insertData(types, stringsAt, "Student", "Students");
			}
		}));
		
		a2.addActionListener(new ButtonAction(new Thread(){
			public void run()
			{
				if(!sql.connected)
				{
					signInMessage();
					return;
				}
				//instructions per input
				String types [] = {
						"Course ID", 
						"Department ID", 
						"College/University ID (char 8)",
						"Course name", 
						"Credits"
						};
				//used as a way to identify numbers vs strings
				boolean stringsAt [] =
				{
					true,
					true,
					true,
					true,
					false
				};
				insertData(types, stringsAt, "Course", "Course");
			}
		}));
		
		a3.addActionListener(new ButtonAction(new Thread(){
			public void run()
			{
				if(!sql.connected)
				{
					signInMessage();
					return;
				}
				//instructions per input
				String types [] = {
						"Department ID", 
						"College/University ID (char 8)", 
						"Department name",
						"Department address", 
						"Department head name",
						"Department head address"
						};
				//used as a way to identify numbers vs strings
				boolean stringsAt [] =
				{
					true,
					true,
					true,
					true,
					true,
					true,
				};
				
				insertData(types, stringsAt, "Department", "Department");
			}
		}));
		
		a4.addActionListener(new ButtonAction(new Thread(){
			public void run()
			{
				if(!sql.connected)
				{
					signInMessage();
					return;
				}
				//instructions per input
				String types [] = {
						"Student SSN", 
						"Course ID",
						"Department ID",
						"College/University ID (char 8)"
						};
				//used as a way to identify numbers vs strings
				boolean stringsAt [] =
				{
					true,
					true,
					true,
					true,
				};
				insertData(types, stringsAt, "Registered", "Student Registry");
			}
		}));
		

		//... TODO all other update buttons:
	}
	
	public void insertData(String [] types, boolean [] stringsAt, String tableName, String message_tableName)
	{
		String query = "insert into " + tableName + " values (";
		try {
			for(int i = 0; i < types.length; ++i)
			{
				boolean confirmed = false;
				while(!confirmed)
				{
					Object out = JOptionPane.showInputDialog(null, "Please enter a " + types[i] + ":", "Entry for " + message_tableName, JOptionPane.PLAIN_MESSAGE);
					if(out instanceof String)
					{
						if(!((String)out).isEmpty()){
							String str = (String)out;
							System.out.println(str);
							if(stringsAt[i])
							{
								str = "'" + str + "'";
							}
							query += str + ", ";
							confirmed = true;
						}
					}
					else
					{
						int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Canceled",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(response == JOptionPane.YES_OPTION){
							return;
						}
					}
				}
			}
			query = query.substring(0, query.length() - ", ".length());
			query += ")";
			System.out.println(query);
			if(!sql.conn.prepareStatement(query).execute()) 
			{
				throw new SQLException("The first result is an updatecount or there is no result.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			if(e1 instanceof java.sql.SQLSyntaxErrorException)
			{
				JOptionPane.showMessageDialog(null, "Error with SQL syntax:  " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(e1 instanceof java.sql.SQLIntegrityConstraintViolationException)
			{
				JOptionPane.showMessageDialog(null, "Error with SQL constraints:  " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Error with SQL:  " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}

	public static void signInMessage()
	{
		JOptionPane.showMessageDialog(null, "You must sign in first!", "Error", JOptionPane.ERROR_MESSAGE);;
	}
	
	public static void failed(String err)
	{
		JOptionPane.showMessageDialog(null, "There was a problem:\n" + err, "Error", JOptionPane.ERROR_MESSAGE);;
	}
	
	public static void success(String info)
	{
		JOptionPane.showMessageDialog(null, "Successfully updated with info:\n" + info, "Success", JOptionPane.OK_OPTION);;
	}
	
}
