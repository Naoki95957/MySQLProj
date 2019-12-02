package main;

import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PrintPanel extends PanelBuilder {
	public JPanel panel;
	public Thread action;
	public TextArea output;
	public GUIWindow gui;
	public SqlTools sql;
	
	JButton reprint;
	JButton clearText;
	
	//doesn't need to exist, you could just add everything to the frame
	//however its good to put things on a panel so that we can update the UI 
	//or completely replace the panel easily by removing the panel child vs 
	//removing each child from the frame
	
	public PrintPanel(GUIWindow gui, SqlTools sql)
	{
		this.gui = gui;
		this.sql = sql;
		rebuild();
	}
	
	//this exists so we can externalize the thread that can be set
	//for the reprint button. 
	public void rebuild()
	{
		panel = new JPanel();
		panel.setLayout(new WrapLayout());
		panel.setBounds(0, 0, 502 * gui.hScale / gui.screenSize.width, 492 * gui.vScale / gui.screenSize.height);
		panel.setMaximumSize(panel.getSize());
		panel.setMinimumSize(panel.getSize());
		panel.setVisible(true);
		
		//text area output: using it basically like a terminal
		output = new TextArea();
		output.setEditable(false);
		sql.attachOutput(output);
		
		reprint = new JButton("Re-print");
		clearText = new JButton("Clear output.");
		
		//action thread to clear the text output
		Thread clear_text_action = new Thread()
		{
			public void run()
			{
				output.setText("");
			}
		};
		
		gui.threads.add(action);
		gui.threads.add(clear_text_action);

		reprint.addActionListener(new ButtonAction(action));
		clearText.addActionListener(new ButtonAction(clear_text_action));
		
		reprint.setVisible(true);
		clearText.setVisible(true);
		
		//adding to display buttons
		panel.add(reprint);
		panel.add(clearText);

		//adding text output to display
		panel.add(output);
	}
	
	public void removeReprint()
	{
		panel.remove(reprint);
	}
	
	public void addButton(JButton button)
	{
		//so that the output remains on the bottom
		panel.remove(output);
		
		panel.add(button);
		panel.add(output);
	}

	public JPanel getPanel() {
		return panel;
	}
	
	
}
