//By LLW(LWL/AXhing-LLW)

public class QuizScoreService 
{
	private QuizScoreDao quizScoreDao = new QuizScoreDao();
	
	public void insertQuizs(QuizScore quizScore) 
	{
		quizScoreDao.insertQuizs(quizScore);
	}
	
	public int getMaxScoreRecently() 
	{
		QuizScore quizScore = quizScoreDao.getMaxScoreRecently();
		return quizScore == null ? 0 : quizScore.getScore();
	}
	
	public int getRecentlyScore() 
	{
		QuizScore score = quizScoreDao.getScoreRecently();
		return score == null ? 0 : score.getScore();
	}
}
