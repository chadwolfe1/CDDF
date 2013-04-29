//Software Engineering II
//Spring 2013

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class login
{

	private static void createAndShowUI()		//create user interface to enter information
	{
		LoginPanel login = new LoginPanel();
		int response = JOptionPane.showConfirmDialog(null, login, "Please Enter User Name and Password",
		JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (response == JOptionPane.OK_OPTION) 
		{
			String name = login.getName();
			String pWord = login.getPassword();

			if (name.equals("Admin") && pWord.equals("Password"))   //If username and password correct, create next module
			{
				Window frame = new Window();
				frame.setVisible(true);
			}
		
			else	//if username and password is incorrect
			{
				String msg = "Incorrect name and password. \n(try Admin and Password)";
				JOptionPane.showMessageDialog(null, msg);
			}
		}
	}

	public static void main(String[] args) 	//main
	{
		java.awt.EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				createAndShowUI();
			}
		});
	}
}


class LoginPanel extends JPanel //code for login panel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField = new JTextField(10);
	private JPasswordField passwordField = new JPasswordField(10);

	public LoginPanel() 
	{
		setLayout(new GridLayout(2, 2, 5, 5)); 
		add(new JLabel("Enter User Name:"));
		add(nameField);
		add(new JLabel("Enter Password:"));
		add(passwordField);
	}

	public String getName() 	//method for user name
	{
		return nameField.getText();
	}

	public String getPassword() //method for password
	{
		return new String(passwordField.getPassword()); 
	}

}
