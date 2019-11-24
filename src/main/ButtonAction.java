package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * 	
 *  This listener is designed for our specific purpose
 *  However you can plug in any runnable and use have it 
 *  execute any thread.
 *  
 *  This isn't really multi-threading as much as it is managing threads.
 *  We're overwriting 'run()' and executing our definition.
 *  
 *  In order to multi-thread, it is as simple as thread.start() vs thread.run()
 *  
 *  HOWEVER, when a thread dies, it cannot start again. It's a javadoc rule! 
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