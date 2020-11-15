//By LLW(LWL/AXhing-LLW)

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PracticeController 
{
	private StudentService studentService = new StudentService();

	
	private PriorityQueue<Student> topics = null;

	private List<Student> completeFinal = null;

	private static final int DEAFULT_NEW_PRACTICE_SIZE = 20;
	
	public void createPractice() 
	{
		this.createPractice(DEAFULT_NEW_PRACTICE_SIZE);
	}

	public void createPractice(int newPracticeSize) 
	{
		completeFinal = new ArrayList<Student>();
		if (newPracticeSize <= 0) 
		{
			newPracticeSize = DEAFULT_NEW_PRACTICE_SIZE;
		}
		topics = new PriorityQueue<Student>(new Comparator<Student>() 
		{
			@Override
            public int compare(Student o1, Student o2) 
			{
                Date d1 = DateUtils.stringToDate(o1.getTime());
                Date d2 = DateUtils.stringToDate(o2.getTime());
                return d1.compareTo(d2);
            }
		}
		);
		
		studentService.getStudentReviewDay().stream().forEach(stu -> this.topics.add(stu));

		
		Date date = new Date();
		
		studentService.getStudentsNew(newPracticeSize).forEach(stu -> 
		{
			stu.setTime(DateUtils.dateToString(date));
			this.topics.add(stu);
		}
		);
	}


	public Student getTopic() 
	{
		if(topics.isEmpty()) 
		{
			return null;
		}
		Date dateNow = new Date();
		Student student = topics.peek();
		String dateStr = student.getTime();
		
		if (student.getEbbinghaus_level() == 0) 
		{
			return student;
		} 
		else 
		{
			
			if (DateUtils.stringToDate(dateStr).compareTo(dateNow) < 0) 
			{
				return student;
			}
		}
		return null;
	}
	

	public Date getNextTime() 
	{
		if(this.topics == null || this.topics.isEmpty()) 
		{
			return null;
		}
		String timeStr = topics.peek().getTime();
		return DateUtils.stringToDate(timeStr);
	}


	public int completeTopic(int raiseLevel, int seconds) 
	{
		if(topics == null || topics.isEmpty()) 
		{
			return 0;
		}
		Student student = topics.poll();
		if(student == null) 
		{
			return 0;
		}
		
		Date date = new Date();
		int level = student.getEbbinghaus_level();
		
		if(level < 0 || level > 9) 
		{
			level = 9;
		}
		
		if(level == 9) 
		{
			student.setEbbinghaus_level(level);
			student.setTime(DateUtils.dateToString(date));
			completeFinal.add(student);
		} 
		else 
		{
			
			if(raiseLevel == 0) 
			{
				Date d = new Date(date.getTime() + seconds * 1000);
				student.setTime(DateUtils.dateToString(d));
				this.topics.add(student);
			} 
			else 
			{
				
				student.setEbbinghaus_level(++level);
				if(level == 9) 
				{
					student.setTime(DateUtils.dateToString(date));
					completeFinal.add(student);
				} 
				else 
				{
					Date next = DateUtils.getNoticeTimeByEbbinghaus(level, date);
					student.setTime(DateUtils.dateToString(next));
					this.topics.add(student);
				}
			}
		}
		
		show();
		this.studentService.updataStudent(student);
		return 1;
	}


	public int getNowTotal() 
	{
		int res = 0;
		Date date = new Date();
		if(this.topics != null) 
		{
			for(Student stu : topics) 
			{
				if(DateUtils.stringToDate(stu.getTime()).compareTo(date) <= 0) 
				{
					res++;
				}
			}
		}
		return res;
	}
	

	public void show() 
	{
		System.out.println("--------------------------");
		System.out.println("topics ALL");
		List<Student> res = new ArrayList<Student>();
		while(!topics.isEmpty())
		{
			res.add(topics.poll());
		}
		for(Student stu : res) 
		{
			System.out.println(stu);
			topics.add(stu);
		}
		System.out.println("--------------------------");
		System.out.println("complete ALL: ");
		completeFinal.stream().forEach(stu -> System.out.println(stu));
		System.out.println("--------------------------");
	}

	public void exit() 
	{
		save();
		this.completeFinal = null;
		this.studentService = null;
		this.topics = null;
	}

	public void save() 
	{
		
		if(this.topics != null) 
		{
			topics.stream().forEach(stu -> 
			{
			    studentService.updataStudent(stu);
			}
			);
		}
		if(this.completeFinal != null) 
		{
			completeFinal.stream().forEach(stu -> 
			{
			    studentService.updataStudent(stu);
			}
			);
		}
	}
}
