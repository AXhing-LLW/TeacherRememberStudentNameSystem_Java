//By LLW(LWL/AXhing-LLW)

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.Vector;
import java.util.List;

public class Look extends JFrame 
{
	private static final long serialVersionUID = -1928970409928880648L;

	private StudentService studentService = new StudentService();

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet res = null;

	JTable jtable;
	JScrollPane jscrollpane = new JScrollPane();

	Vector columnNames = null;
	Vector rowData = null;

	JButton buttonreturn = new JButton("Back");
	Window from = null;

	public Look(Window f) 
	{
		from = f;
		columnNames = new Vector();
		columnNames.add("Student ID");
		columnNames.add("Last Name");
		columnNames.add("First Name");
		columnNames.add("Nick Name");
		columnNames.add("Stuednt Photo");
		rowData = new Vector();
		studentService.getStudentAll().forEach(stu -> 
		{
			Vector hang = new Vector();
			hang.add(stu.getSid());
			hang.add(stu.getLastname());
			hang.add(stu.getFirstname());
			hang.add(stu.getNickname());
			hang.add(stu.getPhoto());
			rowData.add(hang);
		}
		);
		jtable = new JTable(rowData, columnNames);
		jscrollpane = new JScrollPane(jtable);
		buttonreturn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				from.fresh();
			}
		}
		);
		
		jscrollpane.setBounds(0, 0, 680, 200);
		buttonreturn.setBounds(300, 220, 100, 30);
		this.add(jscrollpane);
		this.setTitle("View Student Information");
		this.setLayout(null);
		this.add(buttonreturn);
		this.setLocation(700, 300);
		this.setSize(700, 300);
		this.setVisible(true);
		this.setResizable(false);
	}
}
