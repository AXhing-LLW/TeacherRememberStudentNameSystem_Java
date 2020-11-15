//By LLW(LWL/AXhing-LLW)

import java.awt.GridLayout;

import javax.swing.*;

public class PracticeInterface extends JFrame 
{
	private PracticeController practiceController = new PracticeController();
	private JPanel top = new JPanel();
	private JPanel bottom = new JPanel();
	private JLabel practiceTotalNumLb = new JLabel("Toal: " + 0);
	private JButton submitButton = new JButton("submit");
	private JLabel studentPhoto = new JLabel();
	private JLabel studentName = new JLabel("student name", SwingConstants.CENTER);
	private JLabel showMsg = new JLabel("Reviewing", SwingConstants.CENTER);
	private JButton familarButton = new JButton("familar");
	private JButton vagueButton = new JButton("vague");
	private JButton forgetButton = new JButton("forget");
	private JButton freshButton = new JButton("fresh");
	private Student stu = null;
	private Window from = null;	
	private int size;

	public PracticeInterface(Window from, int size) 
	{
		this.size = size;
		this.from = from;
		initView();
		initDate();
		initEvent();
	}

	private void initView() 
	{
		this.setLayout(null);
		this.setTitle("Practice");
		this.setResizable(true);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		top.setLayout(null);
		top.setBounds(0, 0, 800, 450);
		practiceTotalNumLb.setBounds(0, 20, 100, 20);
		top.add(practiceTotalNumLb);
		submitButton.setBounds(0, 300, 100, 20);
		top.add(submitButton);
		freshButton.setBounds(0, 350, 100, 20);
		top.add(freshButton);
		studentPhoto.setBounds(275, 50, 250, 250);
		top.add(studentPhoto);
		studentName.setBounds(350, 350, 100, 20);
		top.add(studentName);
		showMsg.setBounds(200, 10, 400, 20);
		top.add(showMsg);
		bottom.setLayout(new GridLayout(1, 3, 10, 1));
		bottom.setBounds(250, 450, 300, 40);
		bottom.add(familarButton);
		bottom.add(vagueButton);
		bottom.add(forgetButton);
		this.add(top);
		this.add(bottom);
	}

	private void initDate() 
	{
		practiceController.createPractice(size);
		loadNextTopic();
	}

	private void initEvent() 
	{
		this.familarButton.addActionListener(e -> 
		{
			if (stu != null) 
			{
				completeTopic(1, 0);
				loadNextTopic();
			}
		}
		);

		this.vagueButton.addActionListener(e -> 
		{
			if (stu != null) 
			{
				completeTopic(0, 3);
				loadNextTopic();
			}
		}
		);

		this.forgetButton.addActionListener(e -> 
		{
			if (stu != null) 
			{
				completeTopic(0, 1);
				loadNextTopic();
			}
		}
		);

		this.submitButton.addActionListener(e -> 
		{
			practiceController.exit();
			this.dispose();
			from.fresh();
		}
		);

		this.freshButton.addActionListener(e -> 
		{
			loadNextTopic();
		}
		);
	}

	private void loadNextTopic() 
	{
		this.showMsg.setText("Reviewing");
		this.practiceTotalNumLb.setText("Total: " + practiceController.getNowTotal());
		stu = practiceController.getTopic();
		if (stu != null) 
		{
			this.studentPhoto.setIcon(new ImageIcon(stu.getPhoto()));
			this.studentName.setText(stu.getLastname() + stu.getFirstname() + stu.getNickname());
		} 
		else 
		{
			String naextDateStr = DateUtils.dateToString(this.practiceController.getNextTime());
			this.showMsg.setText("Next review time: " + naextDateStr);
			this.studentName.setText("No need to review for now");
		}
	}

	private int completeTopic(int raiseLevel, int tensOfSeconds) 
	{
		return practiceController.completeTopic(raiseLevel, tensOfSeconds);
	}
}
