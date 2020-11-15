//By LLW(LWL/AXhing-LLW)


public class QuizScore 
{
	private Integer id;
	
	private Integer score;
	
	private String time;
	
	public QuizScore(Integer score, String time) 
	{
		this.score = score;
		this.time = time;
	}

	public Integer getScore() 
	{
		return score;
	}

	public void setScore(Integer score) 
	{
		this.score = score;
	}

	public String getTime() 
	{
		return time;
	}

	public void setTime(String time) 
	{
		this.time = time;
	}

	public int getId() 
	{
		return this.id;
	}
	
	public void setId(Integer id) 
	{
		this.id = id;
	}

	
	public String toString() 
	{
		return "QuizScore [id=" + id + ", score=" + score + ", time=" + time + "]";
	}
}
