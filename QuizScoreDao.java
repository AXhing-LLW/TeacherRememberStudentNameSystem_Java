//By LLW(LWL/AXhing-LLW)


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class QuizScoreDao 
{

	public List<QuizScore> getQuizs() 
	{
		String sql = "SELECT * FROM QUIZS;";
		return baseQuery(sql);
	}


	public int insertQuizs(QuizScore quizScore) 
	{
		String sql = "INSERT INTO QUIZS(time, score) " + "values(?,?)";
		Connection conn = DbConnectUtils.getConnection();
		Statement stat = null;
		PreparedStatement ps = null;
		int executeRes = 0;
		try 
		{
			ps = conn.prepareStatement(sql);
			ps.setString(1, quizScore.getTime());
			ps.setInt(2, quizScore.getScore());
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


	public QuizScore getMaxScoreRecently() 
	{
		String sql = "select * from QUIZS order by score DESC, time DESC limit 1;";
		List<QuizScore> list = baseQuery(sql);
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}


	public List<QuizScore> getMaxScoreAll() 
	{
		QuizScore max = getMaxScoreRecently();
		List<QuizScore> res = null;
		if (max != null) 
		{
			res = getScoreAll(max.getScore());
		}
		return res;
	}


	public List<QuizScore> getScoreAll(int score) 
	{
		List<QuizScore> res = null;
		String sql = "select * from QUIZS where score = " + score;
		res = baseQuery(sql);
		return res;
	}

	public QuizScore getScoreRecently() 
	{
		List<QuizScore> res = null;
		String sql = "SELECT * FROM QUIZS order by time DESC limit 1";
		res = baseQuery(sql);
		return (res != null && res.size() > 0) ? res.get(0) : null;
	}


	private List<QuizScore> baseQuery(String sql)
	{
		List<QuizScore> res = new ArrayList<QuizScore>();
		Connection conn = DbConnectUtils.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			res = DbPojoUtils.DbToQuizScore(rs);
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
}
