//By LLW(LWL/AXhing-LLW)

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentService 
{
	private StudentDao studentDao = new StudentDao();
	
	public List<Student> getStudentAll() 
	{
		return studentDao.getStudents();
	}

	public Student getStudentBySid(Integer sid) 
	{
		return studentDao.getStudentById(sid);
	}

	public List<Student> getStudentsRandom(int count) 
	{
		List<Student> students = studentDao.getStudents();
		if (students == null || students.size() == 0) 
		{
			return null;
		}
		List<Student> res = new ArrayList<Student>();
		if (count < 0) 
		{
			count = 10;
		}
		if (count >= students.size())
		{
			return students;
		}
		int[] randoms = RandomUtils.getRandoms(count, students.size());
		for (int i = 0; i < randoms.length; i++) 
		{
			Student student = students.get(randoms[i]);
			res.add(student);
		}
		return res;
	}

	public List<Student> getStudentsNew(int count) 
	{
		if(count <= 0) 
		{
			count = 10;
		}
		return studentDao.getStudentNew(count);
	}
	
	public int getCompleteFinally() 
	{
		List<Student> r = studentDao.getCompleteFinally();
		return r == null ? 0 : r.size();
	}
	
	public List<Student> getStudentReviewDay() 
	{
		Date date = new Date();
		String today = DateUtils.dateToStringYMD(date);
		List<Student> todayRevList = new ArrayList<Student>();
		studentDao.getStudentReview().stream().forEach(stu -> 
		{
			Date d = DateUtils.stringToDate(stu.getTime());
			if(d.compareTo(date) < 0 || stu.getTime().startsWith(today)) 
			{
				todayRevList.add(stu);
			}
		}
		);
		return todayRevList;
	}
	
	public Date getRevNextDate() 
	{
		Student stu = studentDao.getStudentNextRev();
		return stu == null ? null : DateUtils.stringToDate(stu.getTime());
	}
	
	public int getCompletFinalToday() 
	{
		List<Student> list = studentDao.getCompleteFinalToday(new Date());
		return list == null ? 0 : list.size();
	}
	
	public int getIncomplete() 
	{
		List<Student> list = studentDao.getIncomplete();
		return list == null ? 0 : list.size();
	}
	
	public void insertStudent(Student student) 
	{
		if (null != student) 
		{
			if (student.getSid() != null && student.getFirstname() != null && student.getLastname() != null
					&& student.getNickname() != null) 
			{
				if(null != studentDao.getStudentById(student.getSid())) {
					System.err.println("The student of this student number already exists: [" + student.getSid() + "]");
					return;
				}
				if(student.getPhoto() == null || "".equals(student.getPhoto())) 
				{
					student.setPhoto(" ");
				}
				if(student.getEbbinghaus_level() == null || student.getEbbinghaus_level() < 0) 
				{
					student.setEbbinghaus_level(0);
				}
				if(student.getTime() == null) 
				{
					student.setTime("");
				}
				if(studentDao.insertStudent(student) == 1) 
				{
					System.out.println("Insert student: [" +student.getSid() + "] success");
					
				} 
				else 
				{
					
					System.out.println("Insert student: [" +student.getSid() + "] failure");
				}
			}
			
			System.err.println("Student information is illegal: " + student);
		}
		
		System.err.println("Student information is illegal: " + student);
	}

	public int updataStudent(Student student) 
	{
		int executeRes = studentDao.updateStudent(student);
		return executeRes;
	}


	public void delectStudentById(Integer sid) 
	{
		if(null == sid) 
		{
			
			return;
		}
		int executeRes = studentDao.deleteStudentById(sid);
		if(executeRes == 0) 
		{
			
		} else 
		{
			
		}
	}
}
