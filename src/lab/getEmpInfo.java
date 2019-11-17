package lab;

import java.sql.*;
import java.io.*;


public class getEmpInfo {
    public static void main(String args[]) {
        Connection conn = null;
        try {
            //Step 1: Load the JDBC driver
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connect to the database
            String schema = "CompanySchema";
            String url = "jdbc:mysql://localhost:3306/" + schema + "?serverTimezone=UTC&useSSL=TRUE";
            String user, pass;
            user = readEntry("UserId: ");
            pass = readEntry("Password: ");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Successfully logged in");
            
            //"Example 1": Get Employ Info- retrieve first name and salary giving exact ssn for the employee
            
            //Perform query using PreparedStatement object
            //by providing SSN at run time
            String query = "select lname, salary from employee where ssn = ?";
            PreparedStatement p = conn.prepareStatement(query);
            String ssn = readEntry("Eneter a SSN: ");
            System.out.println("Fetching " + ssn + "...");
            p.clearParameters();
            p.setString(1, ssn);

            //Process the ResultSet
            ResultSet r = p.executeQuery();
            while(r.next())
            {
            	String lname = r.getString(1);
            	double salary = r.getDouble(2);
            	System.out.println(lname + " " + salary);
            }
            
            //Close objects
            System.out.println("Terminating");
            p.close();
            conn.close();
            System.out.println("Closed");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver");
        } catch (SQLException e) { /* ignored */} finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }

    static String readEntry(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            int c = System.in.read();
            while (c != '\n' && c != -1) {
                buffer.append((char) c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }
}
