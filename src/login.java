//Daniel Weston
//Software Engineering II
//Spring 2013
//Assignment 6
//My assigned task (i.e., module) is to program a screen to enter a username and password and, if that information is correct, then a screen is displayed containing the additional modules to be programmed by the other members of the group.

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Login
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
				JFrame frame = new JFrame("Main Application");
				JLabel label = new JLabel("Main Application", SwingConstants.CENTER);
				label.setPreferredSize(new Dimension(400, 300));
				frame.getContentPane().add(label);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
				MyJFrame f = new MyJFrame(Constants.TITLE);
				f.setBackground(Color.WHITE);
		
				Toolkit tk = f.getToolkit();
				Dimension size = tk.getScreenSize();		
				
				//Adding buttons
				MyJButton b = new MyJButton(f);
				b.add_buttons();
		
				Rectangle r = new Rectangle(Constants.X_POS, Constants.Y_POS, size.width/Constants.X_FACTOR, 
				size.height/Constants.Y_FACTOR);
				f.setBounds(r);
		
				if(!f.isVisible()) f.setVisible(true);
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
