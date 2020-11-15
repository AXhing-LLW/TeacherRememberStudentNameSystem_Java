//By LLW(LWL/AXhing-LLW)


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class QuizController 
{
	private StudentService studentService = new StudentService();
	private QuizScoreService quizScoreService = new QuizScoreService();
	private static final int DEAFULT_SIZE = 10;
	private static final int DEAFULT_OPTIONS_COUNT = 3;

	private List<Student> students = null;
	private Topic[] topics = null;
	private int[] completes = null;
	private int size = 0;
	private int topicId = 0;
	private int complete = 0;
	
	public void createQuiz() 
	{
		this.createQuiz(DEAFULT_SIZE);
	}

	public void createQuiz(int size) 
	{
		if (size <= 0) 
		{
			size = DEAFULT_SIZE;
		}
		
		students = studentService.getStudentsRandom(size);
		if(students == null) 
		{
			return;
		}
		this.size = students.size();
		this.topics = new Topic[this.size];
		
		int i = 0;
		while (i < this.size) 
		{
			Topic topic = createTopic(i);
			topics[i] = topic;
			i++;
		}
		this.complete = 0;
		this.completes = new int[this.size];
		this.topicId = 0;
	}

	private Topic createTopic(int topicId) 
	{
		Topic topic = null;
		Student student = this.students.get(topicId);
		int[] randomsTopicId = RandomUtils.getRandomsCoverNum(topicId, this.size, DEAFULT_OPTIONS_COUNT - 1);
		List<String> options = new ArrayList<String>();
		
		options.add(student.getLastname() + " " + student.getFirstname());
		for (int p : randomsTopicId) 
		{
			Student rdm = this.students.get(p);
			options.add(rdm.getLastname() + " " + rdm.getFirstname());
		}
		Collections.shuffle(options);
		topic = new Topic(student, options, options.indexOf(student.getLastname() + " " + student.getFirstname()));
		return topic;
	}

	public Topic getTopic(int topicId) 
	{
		if (topicId >= 0 && topicId < this.size) 
		{
			return topics[topicId];
		}
		return null;
	}

	public int answer(int topicId, int select) 
	{
		int repeat = 1;
		if (topicId >= 0 && topicId < this.size && select >= 0 && select < DEAFULT_OPTIONS_COUNT) 
		{
			Topic topic = topics[topicId];
			
			if (topic.getSelect() == -1) 
			{
				repeat = 0;
				this.complete++;
				this.completes[topicId] = 1;
			}
			topic.setSelect(select);
		}
		return repeat;
	}


	public int getScore() 
	{
		int res = 0;
		if (this.topics != null) 
		{
			for (Topic topic : this.topics) 
			{
				if (topic.getAnswer() == topic.getSelect()) 
				{
					res += 10;
				}
			}
		}
		return res;
	}


	public int getFuLLScore() 
	{
		return this.size * 10;
	}

	public int save() 
	{
		int score = getScore();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(d);
		QuizScore quizScore = new QuizScore(score, date);
		quizScoreService.insertQuizs(quizScore);
		return score;
	}

	public void exit() 
	{
		save();
		clear();
	}

	private void clear() 
	{
		this.students = null;
		this.topics = null;
		this.size = 0;
		this.topicId = 0;
		this.complete = 0;
		this.completes = null;
	}

	
	public Topic[] getTopics() 
	{
		return topics;
	}
	

	public int[] getCompletes() 
	{
		return completes;
	}
	

	public int getComplete() 
	{
		return complete;
	}
	
	public int getSize() 
	{
		return size;
	}
}
