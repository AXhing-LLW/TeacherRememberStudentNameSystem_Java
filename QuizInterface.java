//By LLW(LWL/AXhing-LLW)

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class QuizInterface extends JFrame 
{
	
	private QuizController quizController = new QuizController();
	private JButton[] selectButtons = new JButton[3];
	private JButton beforeButton = new JButton("before");
	private JButton afterButton = new JButton("after");
	private JButton submitButton = new JButton("submit");
	private JLabel studentPhoto;
	private JLabel nowTopicIdAndTotal = new JLabel("0 / 0");
	private JLabel show = new JLabel("");
	private JLabel complete = new JLabel("completed: " + 0);
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private int topicId = 0;
	private int size;
	private int cmplt;	
	private Window from = null;

	public QuizInterface(Window from, int size) 
	{
		this.size = size;
		this.from = from;
		this.setTitle("Quiz");
		this.setResizable(true);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		init();
		initEvent();
		initData();
		
	}

	private void init() 
	{
		
		nowTopicIdAndTotal.setBounds(10, 10, 150, 10);
		nowTopicIdAndTotal.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
	
		complete.setBounds(10, 40, 150, 10);
		complete.setFont(new Font("Black", Font.BOLD, 14));

		show.setBounds(10, 70, 150, 10);

		studentPhoto = new JLabel();
		studentPhoto.setBounds(275, 25, 250, 250);

		for (int i = 0; i < 3; i++) 
		{
			JButton jButton = new JButton("select" + (i + 1));
			selectButtons[i] = jButton;
			selectButtons[i].setBackground(Color.white);
		}
		
		submitButton.setBounds(10, 270, 100, 30);

		topPanel.setLayout(null);
		topPanel.setBounds(0, 0, 800, 300);
		topPanel.add(studentPhoto);
		topPanel.add(complete);
		topPanel.add(nowTopicIdAndTotal);
		topPanel.add(submitButton);
		topPanel.add(show);

		bottomPanel.setBounds(0, 350, 800, 200);
		bottomPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel selectJpl = new JPanel();
		selectJpl.setLayout(new GridLayout(1, 3, 10, 1));
		selectJpl.add(selectButtons[0]);
		selectJpl.add(selectButtons[1]);
		selectJpl.add(selectButtons[2]);

		JPanel qieTiPl = new JPanel();
		qieTiPl.add(beforeButton);
		qieTiPl.add(afterButton);
		bottomPanel.add(selectJpl);
		bottomPanel.add(qieTiPl);
		this.setLayout(null);
		this.add(topPanel);
		this.add(bottomPanel);
	}

	private void initEvent() 
	{
		for (int i = 0; i < selectButtons.length; i++) 
		{
			int p = i;
			JButton selectButton = selectButtons[i];
			selectButton.addActionListener(e -> 
			{
				int repeat = quizController.answer(topicId, p);
				setButtonSelect(p);
				this.complete.setText("complete: " + quizController.getComplete());
				if(repeat == 0) 
				{
					
					if (topicId >= 0 && topicId < size - 1) 
					{
						topicId++;
						Topic topic = quizController.getTopic(topicId);
						boundDateForJwt(topic);
						this.nowTopicIdAndTotal.setText((topicId + 1) + " / " + size);
					}
				}
			}
			);
		}

		this.afterButton.addActionListener(e -> 
		{
			if (topicId >= 0 && topicId < size - 1) 
			{
				topicId++;
				Topic topic = quizController.getTopic(topicId);
				boundDateForJwt(topic);
				this.nowTopicIdAndTotal.setText((topicId + 1) + " / " + size);
			}
		}
		);
		
		this.beforeButton.addActionListener(e -> 
		{
			if (topicId > 0 && topicId < size) 
			{
				topicId--;
				Topic topic = quizController.getTopic(topicId);
				boundDateForJwt(topic);
				this.nowTopicIdAndTotal.setText((topicId + 1) + " / " + size);
			}
		}
		);
		
		this.submitButton.addActionListener(e -> 
		{
			int score = quizController.save();
			System.out.println(score);
			quizController.exit();
			this.dispose();
			from.fresh();
		}
		);
	}

	private void initData() 
	{
		quizController.createQuiz(size);
		cmplt = quizController.getComplete();
		size = quizController.getSize();
		complete.setText("complete: " + cmplt);
		if (size > 0) 
		{
			topicId = 0;
			Topic topic = quizController.getTopic(topicId);
			boundDateForJwt(topic);
			nowTopicIdAndTotal.setText((topicId + 1) + " / " + size);
		}
	}

	private void boundDateForJwt(Topic topic) 
	{
		this.studentPhoto.setIcon(new ImageIcon(topic.getTopicContent().getPhoto()));
		List<String> options = topic.getOptions();
		for (int i = 0; i < options.size(); i++) 
		{
			this.selectButtons[i].setText(options.get(i));
		}
		setButtonSelect(topic.getSelect());
	}

	private void setButtonSelect(int select) 
	{
		for (int i = 0; i < this.selectButtons.length; i++) 
		{
			if (select == i) 
			{
				selectButtons[i].setBackground(Color.BLUE);
			} 
			else 
			{
				selectButtons[i].setBackground(Color.WHITE);
			}
		}
	}
}
