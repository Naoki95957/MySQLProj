package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends PanelBuilder {
	
	JPanel panel;
	
	PrintPanel currentStudents;
	PrintPanel gradStudents;
	PrintPanel rulesAndLaws;
	PanelBuilder updates;
	
	public HomePanel(GUIWindow gui, SqlTools sql)
	{
/**																			*
 * 																			*
 *																			*
 *------------------------REPLACE WITH CURR STUDENT QUERY-------------------*
 * 																			*
 * 																			*
 */
		currentStudents = new PrintPanel(gui, sql);
		//action thread current students
		Thread currStudentsAction = new Thread()
		{
			public void run() {
				ResultSet r = null;
				try {
					//TODO replace this query with the final one
					r = sql.query("select lname, fname from employee order by salary desc limit 5");
					int count = 0;
					while(r.next())
			        {
			        	String lname = r.getString(1);
			        	String fname = r.getString(2);
			        	currentStudents.output.append("Result " + count + ": " + lname + ", " + fname + "\n");
			        	System.out.println("Result from A: " + lname + ", " + fname);
			        	++count;
			        }
					currentStudents.output.append(count + " total students\n");
					System.out.println(count + " total students");
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
		currentStudents.action = currStudentsAction;
		currentStudents.rebuild();
/**																			*
 * 																			*
 *																			*
 *------------------------REPLACE WITH GRAD STUDENT QUERY-------------------*
 * 																			*
 * 																			*
 */
		gradStudents = new PrintPanel(gui, sql);
		//action thread current students
		Thread gradStudentsAction = new Thread()
		{
			public void run() {
				ResultSet r = null;
				try {
					//TODO replace this query with the final one
					r = sql.query("select lname, fname from employee order by salary desc limit 5");
					int count = 0;
					while(r.next())
			        {
			        	String lname = r.getString(1);
			        	String fname = r.getString(2);
			        	gradStudents.output.append("Result " + count + ": " + lname + ", " + fname + "\n");
			        	System.out.println("Result from A: " + lname + ", " + fname);
			        	++count;
			        }
					gradStudents.output.append(count + " total students\n");
					System.out.println(count + " total students");
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
		gradStudents.action = gradStudentsAction;
		gradStudents.rebuild();
		
/**																			*
 * 																			*
 *																			*
 *------------------------REPLACE WITH RULES & LAWS QUERY-------------------*
 * 																			*
 * 																			*
 */
		rulesAndLaws = new PrintPanel(gui, sql);
		//action thread current students
		Thread rulesAndLawsAction = new Thread()
		{
			public void run() {
				ResultSet r = null;
				try {
					//TODO replace this query with the final one
					r = sql.query("select lname, fname from employee order by salary desc limit 5");
					int count = 0;
					while(r.next())
			        {
			        	String lname = r.getString(1);
			        	String fname = r.getString(2);
			        	rulesAndLaws.output.append("Result " + count + ": " + lname + ", " + fname + "\n");
			        	System.out.println("Result from A: " + lname + ", " + fname);
			        	++count;
			        }
					rulesAndLaws.output.append(count + " total students\n");
					System.out.println(count + " total students");
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
		rulesAndLaws.action = rulesAndLawsAction;
		rulesAndLaws.rebuild();
		
		/**
		 * END OF SELECT QUERIES
		 */
		
		/**
		 * UPDATE PANEL:
		 */
		updates = new UpdatePanel(gui, sql);
		
		//doesn't need to exist, you could just add everything to the frame
		//however its good to put things on a panel so that we can update the UI 
		//or completely replace the panel easily by removing the panel child vs 
		//removing each child from the frame
		panel = new JPanel();
		GridLayout layout = new GridLayout(5, 0);
		layout.setVgap(5);
		panel.setLayout(layout);
		panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		panel.setBounds(0, 0, 502, 492);
		panel.setSize(502, 492);
		panel.setMaximumSize(new Dimension(502, 492));
		panel.setMinimumSize(new Dimension(502, 492));

		JButton currStudentsButton = new JButton("Current Students");
		JButton gradStudentsButton = new JButton("Graduated Students");
		JButton rulesLawsButton = new JButton("International Rules & Laws");
		JButton updatesButton = new JButton("Updates");
		
		Thread curr_action = new Thread()
		{
			public void run()
			{
				gui.updatePanel(currentStudents);
				currentStudents.action.run();
			}
		};
		
		Thread grad_action = new Thread()
		{
			public void run()
			{
				gui.updatePanel(gradStudents);
				gradStudents.action.run();
			}
		};
		
		Thread rulesLaws_action = new Thread()
		{
			public void run()
			{
				gui.updatePanel(rulesAndLaws);
				rulesAndLaws.action.run();
			}
		};
		
		Thread updates_action = new Thread()
		{
			public void run()
			{
				gui.updatePanel(updates);
			}
		};

		currStudentsButton.addActionListener(new ButtonAction(curr_action));
		gradStudentsButton.addActionListener(new ButtonAction(grad_action));
		rulesLawsButton.addActionListener(new ButtonAction(rulesLaws_action));
		updatesButton.addActionListener(new ButtonAction(updates_action));
		
		panel.add(new JLabel("Please select an option:"));
		panel.add(currStudentsButton);
		panel.add(gradStudentsButton);
		panel.add(rulesLawsButton);
		panel.add(updatesButton);

		panel.setVisible(true);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
}
