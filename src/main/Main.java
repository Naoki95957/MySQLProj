package main;

public class Main {
	static GUIWindow GUI;
	
	public static void main(String [] args)
	{
		//creating connection to server and schema
		SqlTools sql = new SqlTools("CompanySchema", "student", "password");
		GUI = new GUIWindow(sql);
	}
	
	public void print(String str)
	{
		if(GUI != null)
		{
			GUI.output.append(str + "\n");
		}
		System.out.println(str);
	}
}