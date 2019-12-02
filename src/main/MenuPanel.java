package main;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Vertical menu builder
 * @author Naoki
 *
 */
public class MenuPanel extends PanelBuilder{
	
	private JPanel panel;
	private JLabel title;
	
	public MenuPanel(String title, Component ... components)
	{
		int size = components.length + 1;
		GridLayout layout = new GridLayout(size, 1);
		layout.setVgap(3);
		panel = new JPanel();
		panel.setLayout(layout);
		panel.setBounds(0, 0, 502, 492);
		panel.setMaximumSize(panel.getSize());
		panel.setMinimumSize(panel.getSize());
		panel.setVisible(true);
		
		this.title = new JLabel(title);
		
		panel.add(this.title);
		for(Component c : components)
		{
			panel.add(c);
		}
	}

	public JPanel getPanel() {
		return panel;
	}
}
