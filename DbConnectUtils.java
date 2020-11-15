//By LLW(LWL/AXhing-LLW)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnectUtils 
{

	static 
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() 
	{
		Connection conn = null;
		try 
		{
			 conn = DriverManager.getConnection("jdbc:sqlite:Data/MYSTUDENT.db");
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return conn;
	}
}
