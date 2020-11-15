//By LLW(LWL/AXhing-LLW)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateMySQLite 
{

    private static final String STUDENT_TABLE = "CREATE TABLE STUDENT ("
    		+ "sid	INTEGER PRIMARY KEY NOT NULL, "
    		+ "lastname TEXT NOT NULL, "
    		+ "firstname TEXT NOT NULL, "
    		+ "nickname TEXT NOT NULL,"
    		+ "photo TEST NOT NULL,"
    		+ "ebbinghaus_level INT,"
    		+ "time TEXT) ";


	private static final String QUIZSOCRE_TABLE = "CREATE TABLE QUIZS"
			+ "(id INTEGER PRIMARY KEY autoincrement NOT NULL, "
			+ "time TEXT NOT NULL,"
			+ "score int NOT NULL)";
	
	
	public static void createDb() 
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Data/MYSTUDENT.db");
			stmt = c.createStatement();
			stmt.executeUpdate(STUDENT_TABLE);
			stmt.executeUpdate(QUIZSOCRE_TABLE);
			System.out.println("Create Table  successfully");
		} 
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		finally
		{
			try 
			{
				if(null != stmt)
				{
					stmt.close();

				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				if(null != c)
				{
					c.close();
				}

			} 
			catch (SQLException e) 
			{
				e.printStackTrace();

			}

		}
	}
}
