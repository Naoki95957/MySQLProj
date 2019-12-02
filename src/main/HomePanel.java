package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//technically doesn't need to exist since we have a menu panel
//but this would lead to such a giant file that I wanted to separate this
public class HomePanel extends PanelBuilder {
	
	JPanel panel;
	
	MenuPanel currentStudents;
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
		JButton academics = new JButton("Academic information");
		JButton contact = new JButton("Contact information");
		JButton visa_status = new JButton("Visa status");

		academics.setSize(100, 20);
		contact.setSize(100, 20);
		visa_status.setSize(100, 20);

		currentStudents = new MenuPanel("Current Student Options:", academics, contact, visa_status);	
		/**																			*
		 * 																			*
		 *																			*
		 *------------------------academics queries---------------------------------*
		 * 																			*
		 * 																			*
		 */

		PrintPanel academicsPanel = new PrintPanel(gui, sql);
		
		Thread a1 = new Thread() 
		{
			public void run()
			{
				String ssn = requestInput("Please provide a SSN of a student: ", "Enter SSN");
				if(ssn == null)
				{
					return;
				}
				ResultSet r = null;
				
				//part 1
				try {
					r = sql.query("SELECT U_name AS College, Degree_name AS Degree, Gpa FROM Diploma NATURAL JOIN University WHERE Ssn = '" + ssn + "'");
					int count = 0;
					//print what we're doing
					academicsPanel.output.append("Printing student details(" + ssn + "):\n");
					academicsPanel.output.append("Degrees earned:\n");
					academicsPanel.output.append("\t\tCollege, degree, and GPA:\n");
					
					while(r.next())
			        {
			        	String college = r.getString(1);
			        	String degree = r.getString(2);
			        	String GPA = r.getString(3);
			        	
			        	String out = "Result " + (count + 1) + ":\t" + college + ", " + degree + ", " + GPA + "\n"; 
			        	academicsPanel.output.append(out);
			        	System.out.print(out);
			        	++count;
			        }
					academicsPanel.output.append(count + " total degrees earned\n\n");
					System.out.println(count + " total degress earned");
				}
				catch (Exception e1)
				{
					System.out.println("Error with query");
					System.err.println(e1.toString());
				}
				finally
				{
					try {
						if(r != null)
						{
							r.close();
							
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
				//part 2
				try {
					r = sql.query("SELECT U_name AS College, AVG(Grade) AS Gpa FROM Transcript NATURAL JOIN University WHERE Ssn = '" + ssn + "' GROUP BY Ssn, U_id");
					int count = 0;
					//print what we're doing
					academicsPanel.output.append("Colleges attended:\n");
					academicsPanel.output.append("\t\tCollege, and Avg. GPA:\n");
					
					while(r.next())
			        {
			        	String college = r.getString(1);
			        	String GPA = r.getString(2);
			        	
			        	String out = "Result " + (count + 1) + ":\t" + college + ", " + GPA + "\n"; 
			        	academicsPanel.output.append(out);
			        	System.out.print(out);
			        	++count;
			        }
					academicsPanel.output.append(count + " total colleges attended\n\n");
					System.out.println(count + " total colleges attended");
				}
				catch (Exception e1)
				{
					System.out.println("Error with query");
					System.err.println(e1.toString());
				}
				finally
				{
					try {
						if(r != null)
						{
							r.close();
							
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		};
		
		
		Thread b1 = new Thread() 
		{
			public void run()
			{
				String college = requestInput("Please provide a college", "Enter a college");
				if(college == null)
				{
					return;
				}
				ResultSet r = null;
				try {
					r = sql.query("SELECT Ssn, Fname, Lname FROM Student NATURAL JOIN University WHERE S_type LIKE 'Undergrad' AND U_name LIKE '" + college + "'");
					int count = 0;
					//print what we're doing
					academicsPanel.output.append("Printing college (" + college + ") student details:\n");
					academicsPanel.output.append("\t\tSSN, first name, and last name:\n");
					
					while(r.next())
			        {
			        	String ssn = r.getString(1);
			        	String fname = r.getString(2);
			        	String lname = r.getString(3);
			        	
			        	String out = "Student " + (count + 1) + ":\t" + ssn + ", " + fname + ", " + lname + "\n"; 
			        	academicsPanel.output.append(out);
			        	System.out.print(out);
			        	++count;
			        }
					academicsPanel.output.append(count + " total students\n\n");
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
						if(r != null)
						{
							r.close();
							
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		};
		

		academicsPanel.rebuild();
		academicsPanel.removeReprint();
		
		JButton a1_button = new JButton("Educational History");
		a1_button.addActionListener(new ButtonAction(a1));

		JButton b1_button = new JButton("College Student Info");
		b1_button.addActionListener(new ButtonAction(b1));
		

		academicsPanel.addButton(a1_button);
		academicsPanel.addButton(b1_button);
		
		Thread academics_action = new Thread(){
			public void run()
			{
				gui.updatePanel(academicsPanel);
			}
		};
		
		academics.addActionListener(new ButtonAction(academics_action));
		
		/**																			*
		 * 																			*
		 *																			*
		 *------------------------contact queries-----------------------------------*
		 * 																			*
		 * 																			*
		 */
		
		PrintPanel contactPanel = new PrintPanel(gui, sql);
		//action thread current students
		Thread a2 = new Thread()
		{
			public void run() {
				ResultSet r = null;
				try {
					r = sql.query("SELECT DISTINCT Ssn, Fname, Lname, S_address AS Address, D_name AS Major_Dept, U_name AS College" + 
							" FROM Student NATURAL JOIN Majors NATURAL JOIN Department NATURAL JOIN University WHERE S_type LIKE 'Undergrad'");
					int count = 0;
					//print what we're doing
					contactPanel.output.append("Printing student contact details:\n");
					contactPanel.output.append("\t\tSSN, first name, last name, address, major, and college:\n");
					
					while(r.next())
			        {
			        	String ssn = r.getString(1);
			        	String fname = r.getString(2);
			        	String lname = r.getString(3);
			        	String address = r.getString(4);
			        	String major = r.getString(5);
			        	String college = r.getString(6);
			        	
			        	String out = "Student " + (count + 1) + ":\t" + ssn + ", " + fname + ", " + lname + ", " + address + ", " + major + ", " + college + "\n"; 
			        	contactPanel.output.append(out);
			        	System.out.print(out);
			        	++count;
			        }
					contactPanel.output.append(count + " total students\n\n");
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
						if(r != null)
						{
							r.close();
							
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		};
		
		Thread b2 = new Thread()
		{
			public void run() {
				
				
				ResultSet r = null;
				try {
					String country = requestInput("Please provide a country:", "Enter a country");
					if(country == null || country.isEmpty())
					{
						return;
					}
					r = sql.query("SELECT Ssn, Fname, Lname, S_address AS Address FROM Student NATURAL JOIN Citizenship WHERE S_type LIKE 'Undergrad' AND Country_name LIKE '"+ country +"'");
					int count = 0;
					//print what we're doing
					contactPanel.output.append("Printing student details by country (" + country + "):\n");
					contactPanel.output.append("\t\tSSN, first name, last name, and address:\n");
					
					while(r.next())
			        {
			        	String ssn = r.getString(1);
			        	String fname = r.getString(2);
			        	String lname = r.getString(3);
			        	String address = r.getString(4);
			        	
			        	String out = "Student " + (count + 1) + ":\t" + ssn + ", " + fname + ", " + lname + ", " + address + "\n"; 
			        	contactPanel.output.append(out);
			        	System.out.print(out);
			        	++count;
			        }
					contactPanel.output.append(count + " total students\n\n");
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
						if(r != null)
						{
							r.close();
							
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		
		Thread c2 = new Thread()
		{
			public void run() {
				ResultSet r = null;
				try {
					r = sql.query("SELECT Ssn, Fname, Lname, SUM(Hrs_per_week) AS Hours FROM Student NATURAL JOIN Job WHERE S_type LIKE 'Undergrad' GROUP BY Ssn");
					int count = 0;
					//print what we're doing
					contactPanel.output.append("Printing working-students details:\n");
					contactPanel.output.append("\t\tSSN, first name, last name, and hrs/wk: \n");
					
					while(r.next())
			        {
			        	String ssn = r.getString(1);
			        	String fname = r.getString(2);
			        	String lname = r.getString(3);
			        	String hours = r.getString(4);
			        	
			        	String out = "Student " + (count + 1) + ":\t" + ssn + ", " + fname + ", " + lname + ", " + hours + "\n";
			        	contactPanel.output.append(out);
			        	System.out.print(out);
			        	++count;
			        }
					contactPanel.output.append(count + " total students\n\n");
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
						if(r != null)
						{
							r.close();
							
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		};
		
		contactPanel.rebuild();
		contactPanel.removeReprint();
		
		JButton a2_button = new JButton("All student contacts");
		a2_button.addActionListener(new ButtonAction(a2));

		JButton b2_button = new JButton("All students from country");
		b2_button.addActionListener(new ButtonAction(b2));
		
		JButton c2_button = new JButton("All working students");
		c2_button.addActionListener(new ButtonAction(c2));

		contactPanel.addButton(a2_button);
		contactPanel.addButton(b2_button);
		contactPanel.addButton(c2_button);
		
		Thread contact_action = new Thread(){
			public void run()
			{
				gui.updatePanel(contactPanel);
			}
		};
		
		contact.addActionListener(new ButtonAction(contact_action));
		
		/**																			*
		 * 																			*
		 *																			*
		 *------------------------visa queries--------------------------------------*
		 * 																			*
		 * 																			*
		 */
		
		//visa_status
		
		PrintPanel visaPanel = new PrintPanel(gui, sql);
		
		//TODO add ssn to select statement???
		//seems like a useless request without knowing who holds what visa
		Thread a3 = new Thread() 
		{
			public void run()
			{
				ResultSet r = null;
				try {
					r = sql.query("SELECT V_type AS Visa, I20_start, I20_exp FROM Student GROUP BY Ssn, Fname, Lname;");
					int count = 0;
					//print what we're doing
					visaPanel.output.append("Printing student-visa details:\n");
					visaPanel.output.append("\t\tVisa-type, I-20 start date, and Experation Date:\n");
					
					while(r.next())
			        {
			        	String visa_type = r.getString(1);
			        	String start = r.getString(2);
			        	String experation = r.getString(3);
			        	
			        	String out = "Result " + (count + 1) + ":\t" + visa_type + ", " + start + ", " + experation + "\n"; 
			        	visaPanel.output.append(out);
			        	System.out.print(out);
			        	++count;
			        }
					visaPanel.output.append(count + " total visas \n\n");
					System.out.println(count + " total visas");
				}
				catch (Exception e1)
				{
					System.out.println("Error with query");
					System.err.println(e1.toString());
				}
				finally
				{
					try {
						if(r != null)
						{
							r.close();
							
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		};
		
		
		Thread b3 = new Thread() 
		{
			public void run()
			{
				ResultSet r = null;
				try {
					r = sql.query("SELECT Ssn, Fname, Lname, Country_name FROM Student NATURAL JOIN Citizenship WHERE V_type LIKE 'F1' AND S_type LIKE 'Undergrad'");
					int count = 0;
					//print what we're doing
					visaPanel.output.append("Printing F-1 holder details:\n");
					visaPanel.output.append("\t\tSSN, first name, last name, and country:\n");
					
					while(r.next())
			        {
			        	String ssn = r.getString(1);
			        	String fname = r.getString(2);
			        	String lname = r.getString(3);
			        	String country = r.getString(4);
			        	
			        	String out = "Student " + (count + 1) + ":\t" + ssn + ", " + fname + ", " + lname + ", " + country + "\n"; 
			        	visaPanel.output.append(out);
			        	System.out.print(out);
			        	++count;
			        }
					visaPanel.output.append(count + " total students\n\n");
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
						if(r != null)
						{
							r.close();
							
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		};
		

		visaPanel.rebuild();
		visaPanel.removeReprint();
		
		JButton a3_button = new JButton("Visa Status Report");
		a3_button.addActionListener(new ButtonAction(a3));

		JButton b3_button = new JButton("F-1 Visa");
		b3_button.addActionListener(new ButtonAction(b3));
		

		visaPanel.addButton(a3_button);
		visaPanel.addButton(b3_button);
		
		Thread visa_action = new Thread(){
			public void run()
			{
				gui.updatePanel(visaPanel);
			}
		};
		
		visa_status.addActionListener(new ButtonAction(visa_action));
		
		
/**																			*
 * 																			*
 *																			*
 *------------------------REPLACE WITH GRAD STUDENT QUERY-------------------*
 * 																			*
 * 																			*
 */
		
		//not assigned!
		
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
					r = sql.query("SELECT R_name AS Regulation, R_descr AS Description FROM Regulation");
					int count = 0;
					rulesAndLaws.output.append("\nPrinting Laws & Regulations:\n");
					while(r.next())
			        {
			        	String r_name = r.getString(1);
			        	String r_descr = r.getString(2);
			        	rulesAndLaws.output.append("Regulation " + (count + 1) + ": " + r_name + ":\n\t" + r_descr + "\n");
			        	System.out.println("Regulation "+ (count + 1) + ": " + r_name + ":\n\t" + r_descr);
			        	++count;
			        }
					rulesAndLaws.output.append("\n" + (count) + " total Laws/Regulations\n");
					System.out.println((count) + " total Laws/Regulations\n");
				}
				catch (Exception e1)
				{
					System.out.println("Error with query");
					System.err.println(e1.toString());
				}
				finally
				{
					try {
						if(r != null)
						{
							r.close();
							
						}
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
		//grad not assigned
		gradStudentsButton.setEnabled(false);
		JButton rulesLawsButton = new JButton("International Rules & Laws");
		JButton updatesButton = new JButton("Updates");
		
		Thread curr_action = new Thread()
		{
			public void run()
			{
				gui.updatePanel(currentStudents);
				//currentStudents.action.run();
			}
		};
		
		Thread grad_action = new Thread()
		{
			public void run()
			{
				gui.updatePanel(contactPanel);
				//gradStudents.action.run();
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
				if(gui.checkForAdmin())
				{
					gui.updatePanel(updates);
				}
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
	
	public String requestInput(String prompt, String title)
	{
		//user input
		String returnStr = "";
		try 
		{
			Object IPA = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.PLAIN_MESSAGE);
			if(!((String)IPA).isEmpty()){
				returnStr = (String)IPA;
				System.out.println(returnStr);
			}
		}
		catch(Exception e)
		{
			int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Canceled",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response == JOptionPane.YES_OPTION){
				return null;
			}
			else 
			{
				return requestInput(prompt, title);
			}
		}
		return returnStr;
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
}
