package main;

public class Main {
	static GUIWindow GUI;
	
	public static void main(String [] args)
	{
		//creating connection to server and schema
		SqlTools sql = new SqlTools("CompanySchema");
		GUI = new GUIWindow(sql);
		HomePanel home = new HomePanel(GUI, sql);
		GUI.beginGUI(home.getPanel());
		GUI.setUser();
	}
	
}