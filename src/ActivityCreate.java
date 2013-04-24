import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ActivityCreate extends JFrame{ 
	JLabel labelActType, labelDesc, labelCase, labelAtt, labelSdate, labelEdate; 
	JTextField tfActType, tfDesc, tfCase, tfAtt, tfSdate, tfEdate;
	JButton buttonSubmit, buttonClear, buttonEnd; 
	List<Activity> list = new ArrayList<Activity>();
	static DefaultTableModel model = new DefaultTableModel();
	
	public ActivityCreate(List<Activity> list2, DefaultTableModel model2) { 
		// This is the Create new Activity frame
		// This is used 
		list = list2;
		model = model2;
		
		Container c = getContentPane(); //gets hold of the output screen
		c.setLayout(new GridLayout(7,2)); 
		c.setPreferredSize(new Dimension(600,200));
		
		labelActType = new JLabel ("Activity Type"); 
		tfActType = new JTextField();
		
		labelDesc = new JLabel("Description"); 
		tfDesc= new JTextField(); 
		
		labelCase = new JLabel("Case"); 
		tfCase = new JTextField(); 
		
		labelAtt = new JLabel("Attorney"); 
		tfAtt = new JTextField();
		
		labelSdate = new JLabel("Start Date"); 
		tfSdate = new JTextField(); 
		
		labelEdate = new JLabel("End Date"); 
		tfEdate = new JTextField();
		
		buttonSubmit = new JButton("Submit"); 
		buttonClear = new JButton("Clear"); 
		buttonEnd = new JButton("End"); 
		
		c.add(labelActType); 
		c.add(tfActType);
		
		c.add(labelDesc); 
		c.add(tfDesc);
		
		c.add (labelCase); 
		c.add(tfCase); 
		
		c.add (labelAtt); 
		c.add(tfAtt);
		
		c.add (labelSdate); 
		c.add(tfSdate);
		
		c.add (labelEdate); 
		c.add(tfEdate);
		
		c.add(buttonSubmit); 
		c.add(buttonClear); 
		//c.add(buttonEnd); 
		ButtonHandler handler = new ButtonHandler();
		buttonSubmit.addActionListener(handler); 
		buttonClear.addActionListener(handler); 
		//buttonEnd.addActionListener(handler); 
		
		pack(); 
		setVisible(true); 
	} 
	
	private class ButtonHandler implements ActionListener 
	{ 
		public void actionPerformed (ActionEvent e) 
		{ 
			if (e.getSource() == buttonSubmit) {
			//	When "Submit" button is pressed
			//	
				// Date not implemented
				Activity save = new Activity(
					tfActType.getText(), 
					tfDesc.getText(), 
					tfCase.getText(),
					tfAtt.getText(), 
					tfSdate.getText(),
					tfEdate.getText(),
					false);
				
				list.add(save);
				model.addRow(new Object[]{"*new*",save.getActType(), save.getActDesc(),save.getActCase(),
						save.getActAttorney(),save.getStartDate(),save.getEndDate(),save.getStatus()});
				//System.out.println("Added item: " + (list.get(list.size())).toString());
				// save string here
				
				//DEBUG Print
				//System.out.println(save.toString());
			} 
			
			if(e.getSource() == buttonClear) { 
				tfActType.setText(""); 
				tfDesc.setText(""); 
				tfCase.setText("");
				tfAtt.setText(""); 
				tfSdate.setText("");
				tfEdate.setText("");
			} 
			//if(e.getSource() == buttonEnd) {
			//	System.exit(0);} 
		} 
	} 
} 
