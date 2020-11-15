//By LLW(LWL/AXhing-LLW)

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DbPojoUtils 
{

	public static List<Student> DbToStudents(ResultSet rs) throws SQLException 
	{
		List<Student> res = new ArrayList<Student>();
		while (rs.next()) 
		{
			Integer sid = rs.getInt("sid");
			String lastname = rs.getString("lastname");
			String firstname = rs.getString("firstname");
			String nickname = rs.getString("nickname");
			String photo = rs.getString("photo");
			Integer ebbinghaus_level = rs.getInt("ebbinghaus_level");
			String time = rs.getString("time");
			Student stu = new Student(sid, lastname, firstname, nickname, photo);
			stu.setEbbinghaus_level(ebbinghaus_level);
			stu.setTime(time);
			res.add(stu);
		}
		return res;
	}
	
	public static List<QuizScore> DbToQuizScore(ResultSet rs) throws SQLException 
	{
		List<QuizScore> res = new ArrayList<QuizScore>();
		while (rs.next()) 
		{
			String time = rs.getString("time");
			Integer score = rs.getInt("score");
			Integer id = rs.getInt("id");
			QuizScore quizScore = new QuizScore(score, time);
			quizScore.setId(id);
			res.add(quizScore);
		}
		return res;
	}
}
