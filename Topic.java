//By LLW(LWL/AXhing-LLW)

import java.util.List;

public class Topic 
{
	private Student topicContent;
	
	private List<String> options;
	
	private int answer;
	
	private int select = -1;
	
	public Topic(Student topicContent, List<String> options, int answer) 
	{
		this.topicContent = topicContent;
		this.options = options;
		this.answer = answer;
	}

	public Student getTopicContent() 
	{
		return topicContent;
	}

	public void setTopicContent(Student topicContent) 
	{
		this.topicContent = topicContent;
	}

	public List<String> getOptions() 
	{
		return options;
	}

	public void setOptions(List<String> options) 
	{
		this.options = options;
	}

	public int getAnswer() 
	{
		return answer;
	}

	public void setAnswer(int answer) 
	{
		this.answer = answer;
	}

	public int getSelect() 
	{
		return select;
	}

	public void setSelect(int select) 
	{
		this.select = select;
	}

	public String toString() 
	{
		return "TopicStudentId: " + topicContent.getSid() +" Topic [options=" + options + ", answer=" + answer + ", select=" + select + "]";
	}	
}
