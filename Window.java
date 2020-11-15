//By LLW(LWL/AXhing-LLW)

import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;

public class Window extends JFrame 
{
	private StudentService studentService = new StudentService();
	private QuizScoreService quizScoreService = new QuizScoreService();
	private JPanel top = new JPanel();
	private JPanel bottom = new JPanel();
	private JLabel todayRev = new JLabel("Today need review: " + 0);
	private JLabel revTime = new JLabel("Next Review Time: " + 0);
	private JLabel maxScoreInHistory = new JLabel("Max score in history: " + 0);
	private JLabel recentlyScore = new JLabel("Recently score: " + 0);
	private JLabel completeFinally = new JLabel("completeFinally: " + 0);
	private JLabel incompleteTotal = new JLabel("incompleteTotal: " + 0);
	private JLabel practiceNum = new JLabel("Practice Num: ");
	private JLabel quizNum = new JLabel("Quiz Num: ");
	private JTextField practiceNumText = new JTextField("");
	private JTextField quizNumText = new JTextField("");
	private int prctcNum;
	private int qzNum;

	JButton add = new JButton("Add");
	JButton modify = new JButton("Modify");
	JButton delete = new JButton("Delete");
	JButton query = new JButton("Query");
	JButton quiz = new JButton("Quiz");
	JButton practice = new JButton("Practice");

	public Window() 
	{
		initView();
		initDate();
		initEvent();
	}

	private void initView() 
	{

		this.setLayout(null);
		this.setTitle("Name Quiz Hub");
		this.setResizable(true);
		this.setSize(615, 415);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		top.setBounds(0, 100, 600, 200);
		top.setLayout(null);
		todayRev.setBounds(200, 10, 400, 20);
		revTime.setBounds(200, 40, 400, 20);
		maxScoreInHistory.setBounds(200, 70, 400, 20);
		recentlyScore.setBounds(200, 100, 400, 20);
		completeFinally.setBounds(200, 130, 400, 20);
		incompleteTotal.setBounds(200, 160, 400, 20);
		practiceNum.setBounds(10, 150, 100, 20);
		practiceNumText.setBounds(110, 150, 50, 20);
		quizNum.setBounds(10, 180, 100, 20);
		quizNumText.setBounds(110, 180, 50, 20);
		top.add(todayRev);
		top.add(revTime);
		top.add(maxScoreInHistory);
		top.add(recentlyScore);
		top.add(completeFinally);
		top.add(incompleteTotal);
		top.add(practiceNum);
		top.add(quizNum);
		top.add(quizNumText);
		top.add(practiceNumText);

		bottom.setBounds(0, 300, 600, 40);
		bottom.setLayout(new GridLayout(1, 6, 5, 5));
		bottom.add(add);
		bottom.add(modify);
		bottom.add(delete);
		bottom.add(query);
		bottom.add(quiz);
		bottom.add(practice);
		this.add(bottom);
		this.add(top);

	}

	private void initDate() 
	{
		loadDate();
	}

	private void initEvent() 
	{
		add.addActionListener(e -> 
		{
			new Add(this);
		}
		);

		modify.addActionListener(e -> 
		{
			new Modify(this);
		}
		);

		delete.addActionListener(e -> 
		{
			new Delete(this);
		}
		);

		query.addActionListener(e -> 
		{
			new Look(this);
		}
		);

		quiz.addActionListener(e -> 
		{
			try
			{
				qzNum = Integer.valueOf(quizNumText.getText());
			} 
			catch (NumberFormatException e1) 
			{
				qzNum = 0;
			}
			new QuizInterface(this, qzNum);
		}
		);

		practice.addActionListener(e -> 
		{
			try 
			{
				prctcNum = Integer.valueOf(practiceNumText.getText());
			} catch (NumberFormatException e1) 
			{
				prctcNum = 0;
			}
			new PracticeInterface(this, prctcNum);
		}
		);
	}

	private void loadDate() 
	{
		int cmpltfly = studentService.getCompleteFinally();
		List<Student> stu = studentService.getStudentReviewDay();
		int revToday = stu == null ? 0 : stu.size();
		int incomplete = studentService.getIncomplete();
		String timeStr = DateUtils.dateToString(studentService.getRevNextDate());
		maxScoreInHistory.setText("Max score in history: " + quizScoreService.getMaxScoreRecently());
		recentlyScore.setText("Recently score: " + quizScoreService.getRecentlyScore());
		todayRev.setText("Today need review: " + revToday);
		revTime.setText("Next Review Time: " + timeStr);
		completeFinally.setText("completeFinally: " + cmpltfly);
		incompleteTotal.setText("incompleteTotal: " + incomplete);
	}

	public void fresh() 
	{
		loadDate();
	}
}
