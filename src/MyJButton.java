import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

/**
* Class MuJButton
* 
* Legal Case Management System
* Florida State University
* @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos
* @version 1
* @since 2013-03-01
*/
public class MyJButton extends JButton{
	static final long serialVersionUID = 0;//To prevent warning
	
	Container c;
	MyJFrame f;
	
	
	MyJButton(MyJFrame f){
		this.f = f;
		c = f.getContentPane();
		
	}
	
	
	void addScreen(){

		
		JPanel panel = new JPanel();
		
		//Read the file into a collection
		//Set sise of the grid base on the size of the collection
		//Display the grid
		panel.setLayout(new GridLayout(5,4,10,10));
		
		panel.add(new JLabel("Sel"));
		panel.add(new JLabel("First Name"));
		panel.add(new JLabel("Last Name"));
		panel.add(new JLabel("Phone"));
		
		//Read all rows an loop for each contact
		panel.add(new JCheckBox("1"));
		panel.add(new JLabel("Peter"));
		panel.add(new JLabel("Wilson"));
		panel.add(new JLabel("230-012-0122"));

		panel.add(new JCheckBox("1"));
		panel.add(new JLabel("Peter"));
		panel.add(new JLabel("Wilson"));
		panel.add(new JLabel("230-012-0122"));

		panel.add(new JCheckBox("1"));
		panel.add(new JLabel("Peter"));
		panel.add(new JLabel("Wilson"));
		panel.add(new JLabel("230-012-0122"));
		
		//panel.add(new JTextField(5));
		
		panel.add(new JButton("Modify"));
		panel.add(new JButton("Delete"));
		panel.add(new JLabel(""));
		panel.add(new JButton("Close"));
		
		this.f.add(panel);
		
	}
	
	/**
	 * Adds button to screen
	 */
	void add_buttons(){
		
		//Container set as grid
		Container content = f.getContentPane(); 
		
		GridBagLayout gbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		content.setLayout(gbag);
		
	
		//Contacts
		JButton b = new JButton("Create Contact");
		b.addMouseListener(new ButtonAction(1));
		c.add(b);
		
		JButton ContactSearch = new JButton("Search Contact");
		ContactSearch.addMouseListener(new ButtonAction(2));
		c.add(ContactSearch);
		
		//Another
		b = new JButton("Create Case");
		b.addMouseListener(new ButtonAction(2));
		c.add(b);
		
		//Etc
		b = new JButton("Create Activity");
		b.addMouseListener(new ButtonAction(3));
		c.add(b);
		
		b = new JButton("Create Activity");
		//b.addMouseListener(new ButtonAction(3));
		c.add(b);
		
		
		///addScreen()
	}
	
}//end class
