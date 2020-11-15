//By LLW(LWL/AXhing-LLW)


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class StudentDao 
{

	public List<Student> getStudents() 
	{
		List<Student> res = new ArrayList<Student>();
		Connection conn = DbConnectUtils.getConnection();
		String sql = "SELECT * FROM STUDENT;";
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			res = DbPojoUtils.DbToStudents(rs);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (rs != null) 
				{
					rs.close();
				}
				if (stmt != null) 
				{
					stmt.close();
				}
				if (conn != null) 
				{
					conn.close();
				}

			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
		}
		return res;
	}


	public Student getStudentById(Integer sid) 
	{
		Student student = null;
		Connection conn = DbConnectUtils.getConnection();
		String sql = "SELECT * FROM STUDENT WHERE sid = " + sid + ";";
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Student> res = DbPojoUtils.DbToStudents(rs);
			if (res != null && res.size() > 0) 
			{
				student = res.get(0);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (rs != null) 
				{
					rs.close();
				}
				if (stmt != null) 
				{
					stmt.close();
				}
				if (conn != null) 
				{
					conn.close();
				}

			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return student;
	}

	public List<Student> getStudentNew(int count) 
	{
		String sql = "SELECT * FROM STUDENT where ebbinghaus_level = 0 limit " + count  + ";";
		return queryBase(sql);
	}
	
	public List<Student> getStudentReview() 
	{
		String sql = "SELECT * FROM STUDENT where ebbinghaus_level > 0 AND ebbinghaus_level < 9 ORDER BY time ASC;";
		return queryBase(sql);
	}
	

	public Student getStudentNextRev() 
	{
		String sql = "SELECT * FROM STUDENT where ebbinghaus_level > 0 AND ebbinghaus_level < 9 ORDER BY time ASC limit 1;";
		List<Student> res = queryBase(sql);
		return (res != null && res.size() > 0) ? res.get(0) : null; 
	}

	public List<Student> getCompleteFinalToday(Date date) 
	{
		String YMD = DateUtils.dateToStringYMD(date);
		String sql = "SELECT * FROM STUDENT WHERE ebbinghaus_level = 9 AND time like '" + YMD + "%';";
		return queryBase(sql);
	}
	

	public List<Student> getCompleteFinally() 
	{
		String sql = "SELECT * FROM STUDENT WHERE ebbinghaus_level = 9;";
		return queryBase(sql);
	}
	

	public List<Student> getIncomplete() 
	{
		String sql = "SELECT * FROM STUDENT WHERE ebbinghaus_level != 9;";
		return queryBase(sql);
	}
	
	public int deleteStudentById(Integer sid) 
	{
		Connection conn = DbConnectUtils.getConnection();
		String sql = "DELETE FROM STUDENT WHERE sid = " + sid + ";";
		Statement stmt = null;
		int executeRes = 0;
		try 
		{
			stmt = conn.createStatement();
			executeRes = stmt.executeUpdate(sql);
		} 
		catch (SQLException e) 
		{
			System.out.println("Delete fail");
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (stmt != null) 
				{
					stmt.close();
				}
				if (conn != null) 
				{
					conn.close();
				}

			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return executeRes;
	}

	public int insertStudent(Student student) 
	{
		String sql = "INSERT INTO STUDENT(sid, lastname, firstname, nickname, photo, ebbinghaus_level, time) " + "values(?,?,?,?,?,?,?)";
		Connection conn = DbConnectUtils.getConnection();
		Statement stat = null; 
		PreparedStatement ps=null; 
		int executeRes = 0;
		try
		{ 
			ps=conn.prepareStatement(sql); 
			ps.setInt(1, student.getSid());
			ps.setString(2, student.getLastname());
			ps.setString(3, student.getFirstname());
			ps.setString(4, student.getNickname());
			ps.setString(5, student.getPhoto());
			ps.setInt(6, student.getEbbinghaus_level());
			ps.setString(7, student.getTime());
			executeRes = ps.executeUpdate(); 
			System.out.println("Sqlite Successfully Add!");
		}
		catch (SQLException b)
		{ 
			executeRes = 0;
			b.printStackTrace(); 
		}
		finally
		{ 
			try 
			{
				if (ps != null) 
				{
					ps.close();
				}
				if (stat != null) 
				{
					stat.close();
				}
				if (conn != null) 
				{
					conn.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return executeRes;
	}

	public int updateStudent(Student student) 
	{
		String sql = "UPDATE STUDENT set lastname = ?, firstname = ?, nickname = ?, photo = ?, ebbinghaus_level = ?, time = ? where sid = ?;";
		Connection conn = DbConnectUtils.getConnection();
		Statement stat = null; 
		PreparedStatement ps=null;
		int executeRes = 0;
		try
		{ 
			ps=conn.prepareStatement(sql); 
			ps.setString(1, student.getLastname());
			ps.setString(2, student.getFirstname());
			ps.setString(3, student.getNickname());
			ps.setString(4, student.getPhoto());
			ps.setInt(5, student.getEbbinghaus_level());
			ps.setString(6, student.getTime());
			ps.setInt(7, student.getSid());
			executeRes = ps.executeUpdate();
		}
		catch (SQLException b)
		{ 
			b.printStackTrace(); 
		}
		finally
		{ 
			try 
			{
				if (ps != null) 
				{
					ps.close();
				}
				if (stat != null) 
				{
					stat.close();
				}
				if (conn != null) 
				{
					conn.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return executeRes;
	}

	private List<Student> queryBase(String sql) 
	{
		List<Student> res = new ArrayList<Student>();
		Connection conn = DbConnectUtils.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			res = DbPojoUtils.DbToStudents(rs);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (rs != null) 
				{
					rs.close();
				}
				if (stmt != null) 
				{
					stmt.close();
				}
				if (conn != null) 
				{
					conn.close();
				}

			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return res;
	}

}
