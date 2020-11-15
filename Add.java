//By LLW(LWL/AXhing-LLW)

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;


public class Add extends JFrame 
{

	private static final long serialVersionUID = -1928970409928880648L;
	
	private StudentDao studentDao = new StudentDao();

	JLabel jlnumber = new JLabel("Student ID: ");
	JLabel jllname = new JLabel("Last Name: ");
	JLabel jlfname = new JLabel("First Name: ");
	JLabel jlnname = new JLabel("Nick Name: ");
	JLabel jlphoto = new JLabel("Student Photo: ");

	JTextField jtnumber = new JTextField("", 20);
	JTextField jtlname = new JTextField("", 20);
	JTextField jtfname = new JTextField("", 20);
	JTextField jtnname = new JTextField("", 20);
	JTextField jtphoto = new JTextField("", 50);
	
	JLabel showMsg = new JLabel("     ",SwingConstants.CENTER);
	JLabel photoMsg = new JLabel("     ", SwingConstants.CENTER);

	JButton buttonadd = new JButton("Add");
	JButton buttonreturn = new JButton("Back");
	Window from = null;

	public Add(Window f) 
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

		jpforbutton.add(buttonadd);
		jpforbutton.add(buttonreturn);

		//Init button add
		buttonadd.addActionListener(new ActionListener() 
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
					System.err.println("Student id is Illegal: [" + jtnumber.getText() + "]");
					showMsg.setText("Student id is Illegal: [" + jtnumber.getText() + "]");
					return;
				}
				String lname = jtlname.getText();
				String fname = jtfname.getText();
				String jtn = jtnname.getText();
				String photo = jtphoto.getText();
				String[] checks = new String[] {lname, fname, jtn, photo};
				for(int i = 0; i < checks.length; i++) 
				{
					if(StringUtils.isBlank(checks[i])) 
					{
						System.err.println("Student msg is Illegal.");
						showMsg.setText("Student msg is Illegal.");
						return;
					}
				}
				Student student = new Student(sid, lname, fname, jtn, photo);
				String serverPath = PhotoUtils.getServerPath(student);
				student.setPhoto(serverPath);
				int res = studentDao.insertStudent(student);
				if(res == 0) 
				{
					showMsg.setText("Insert fail");
					photoMsg.setText("Photo upload fail");
				} 
				else 
				{
					if(PhotoUtils.isIllPhoto(photo)) 
					{
						try 
						{
							PhotoUtils.uploadPhoto(photo, serverPath);
							photoMsg.setText("Photo upload success");
						} 
						catch (IOException e1) 
						{
							photoMsg.setText("Photo upload fail");
						}
					} 
					else 
					{
						photoMsg.setText("Photo upload fail");
					}
					showMsg.setText("Insert success");
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

		this.setTitle("Add Student Information");
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
