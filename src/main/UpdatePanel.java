package main;

import java.awt.GridLayout;

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
		mainPanel.setBounds(0, 0, 502, 492);
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
		
		//TODO add threads and actions to all 12 buttons along with input streams...
		
		mainPanel.add(optionA);	
		mainPanel.add(optionB);	
		mainPanel.add(optionC);
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}
	
	public void failed(String err)
	{
		JOptionPane.showMessageDialog(null, "There was a problem:\n" + err, "Error", JOptionPane.ERROR_MESSAGE);;
	}
	
	public void success(String info)
	{
		JOptionPane.showMessageDialog(null, "Successfully updated with info:\n" + info, "Success", JOptionPane.OK_OPTION);;
	}
	
}
