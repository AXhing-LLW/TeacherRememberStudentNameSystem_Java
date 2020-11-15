//By LLW(LWL/AXhing-LLW)

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


public class Modify extends JFrame 
{
	private static final long serialVersionUID = -1928970409928880648L;

	private StudentDao studentDao = new StudentDao();

	JLabel jlnumber = new JLabel("Student ID: ");
	JLabel jllname = new JLabel("Last Name: ");
	JLabel jlfname = new JLabel("First Name: ");
	JLabel jlnname = new JLabel("Nick Nmae: ");
	JLabel jlphoto = new JLabel("Student Photo: ");

	JLabel showMsg = new JLabel("     ", SwingConstants.CENTER);
	JLabel photoMsg = new JLabel("     ", SwingConstants.CENTER);

	JTextField jtnumber = new JTextField("", 20);
	JTextField jtlname = new JTextField("", 20);
	JTextField jtfname = new JTextField("", 20);
	JTextField jtnname = new JTextField("", 20);
	JTextField jtphoto = new JTextField("", 50);

	JButton buttonchange = new JButton("Modify");
	JButton buttonreturn = new JButton("Back");
	Window from = null;

	public Modify(Window f) 
	{
		this.from = f;
		JPanel jpnumber = new JPanel();
		JPanel jplname = new JPanel();
		JPanel jpfname = new JPanel();
		JPanel jpnname = new JPanel();
		JPanel jpphoto = new JPanel();
		JPanel jpforbutton = new JPanel(new GridLayout(1, 1));

		jpnumber.add(jlnumber);
		jpnumber.add(jtnumber);

		jplname.add(jllname);
		jplname.add(jtlname);

		jpfname.add(jlfname);
		jpfname.add(jtfname);

		jpnname.add(jlnname);
		jpnname.add(jtnname);

		jpphoto.add(jlphoto);
		jpphoto.add(jtphoto);

		jpforbutton.add(buttonchange);
		jpforbutton.add(buttonreturn);

		buttonchange.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Integer sid;
				try 
				{
					sid = Integer.valueOf(jtnumber.getText());
				} 
				catch (NumberFormatException e1) 
				{
					System.err.println("student id is Illegal: [" + jtnumber.getText() + "]");
					showMsg.setText("student id is Illegal: [" + jtnumber.getText() + "]");
					return;
				}
				Student stu = studentDao.getStudentById(sid);
				if(stu == null) 
				{
					System.err.println("student id is not exist: + [" + sid +"]");
					showMsg.setText("student id is not exist: + [" + sid +"]");
					return;
				}
				String lname = jtlname.getText();
				String fname = jtfname.getText();
				String jtn = jtnname.getText();
				String photo = jtphoto.getText();
				if (!StringUtils.isBlank(lname)) 
				{
					stu.setLastname(lname);
				}
				if (!StringUtils.isBlank(fname)) 
				{
					stu.setFirstname(fname);
				}
				if (!StringUtils.isBlank(jtn)) 
				{
					stu.setNickname(jtn);
				}
				if (!StringUtils.isBlank(photo)) 
				{
					
					if(PhotoUtils.isIllPhoto(photo)) 
					{
						String serverPath = PhotoUtils.getServerPath(stu);
						try 
						{
							PhotoUtils.uploadPhoto(photo, serverPath);
							stu.setPhoto(serverPath);
							photoMsg.setText("photo load sucess");
						} 
						catch (IOException e1) 
						{
							photoMsg.setText("photo load fail");
						}
					} 
					else 
					{
						photoMsg.setText("photo load fail");
					}
				}
				int res = studentDao.updateStudent(stu);
				if (res == 0) 
				{
					showMsg.setText("update fail");
				} 
				else 
				{
					showMsg.setText("update success");
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

		this.setTitle("Modify Student Information");
		this.setLayout(new GridLayout(9, 1));
		this.add(jpnumber);
		this.add(jplname);
		this.add(jpfname);
		this.add(jpnname);
		this.add(jpphoto);
		this.add(jpforbutton);
		this.add(showMsg);
		this.add(photoMsg);
		this.setLocation(700, 300);
		this.setSize(700, 300);
		this.setVisible(true);
	}
}
