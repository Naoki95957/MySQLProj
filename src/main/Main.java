package main;

public class Main {
	static GUIWindow GUI;
	
	public static void main(String [] args)
	{
		//creating connection to server and schema
		SqlTools sql = new SqlTools("ProjectPartB");
		GUI = new GUIWindow(sql);
		HomePanel home = new HomePanel(GUI, sql);
		GUI.beginGUI(home.getPanel());
		sql.establishConnection();
	}
	
}