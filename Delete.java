//By LLW(LWL/AXhing-LLW)

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Delete extends JFrame 
{
	private static final long serialVersionUID = -1928970409928880648L;

	private StudentDao studentDao = new StudentDao();
	JLabel jlnumber = new JLabel("Student ID: ");

	JTextField jtnumber = new JTextField("", 20);

	JButton buttondelete = new JButton("Delete");
	JButton buttonreturn = new JButton("Back");
	JLabel showMsg = new JLabel("     ", SwingConstants.CENTER);
	Window from = null;

	public Delete(Window f) 
	{
		from = f;
		JPanel jpnumber = new JPanel();
		JPanel jpforbutton = new JPanel(new GridLayout(1, 1));

		jpnumber.add(jlnumber);
		jpnumber.add(jtnumber);

		jpforbutton.add(buttondelete);
		jpforbutton.add(buttonreturn);

		buttondelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String number = jtnumber.getText();
				Integer sid = null;
				try 
				{
					sid = Integer.valueOf(jtnumber.getText());
				} 
				catch (NumberFormatException e1) 
				{
					System.err.println("Student id is Illegal: [" + number + "]");
					showMsg.setText("Student id is Illegal: [" + number + "]");
					return;
				}
				int res = studentDao.deleteStudentById(sid);
				if (res == 0) 
				{
					showMsg.setText("Deletete fail");
				} 
				else 
				{
					showMsg.setText("Deletete success");
					PhotoUtils.deletePhoto(PhotoUtils.SAVE_DRICT + sid + ".png");
				}
			}
		}
		);

		buttonreturn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				from.fresh();
			}
		}
		);

		this.setTitle("Delete Student Information");
		this.setLayout(new GridLayout(9, 1));
		this.add(jpnumber);
		this.add(jpforbutton);
		this.add(showMsg);
		this.setLocation(700, 300);
		this.setSize(700, 300);
		this.setVisible(true);
	}
}
