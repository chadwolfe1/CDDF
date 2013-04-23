// TeamActivity.java
// Dean Burgos
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class TeamActivity {
	
	static JButton createAct;
	static File file = new File("act.txt");
	static BufferedReader reader = null;
	static String inputLine;
	
	static DefaultTableModel model = new DefaultTableModel() {
		// Needed to make sure JTable cannot be edited in display

		@Override
		public boolean isCellEditable(int row, int column) {
			//all cells false
			return false;
		}
	};
	
	final static List<Activity> list = new ArrayList<Activity>();
	
	public static Activity parseIt(String s){
		// Parses a line read from text into an Activity object
		// using the tab character as a delimiter
		String[] line = s.split("\\t");

		return new Activity(line[0],line[1],line[2],line[3],new Date(),new Date(),Boolean.parseBoolean(line[6]));
	}
	
	static class WindowEventHandler extends WindowAdapter {
		  public void windowClosing(WindowEvent evt) {
		    //Resave File Here
			// Erases first file
			// Creates new file using modified listarray
			  
			  try{
					FileOutputStream fos = new FileOutputStream(file);
					PrintWriter dos = new PrintWriter(fos);

					
					for( int i = 0 ; i < list.size() ; i++ ) {
						dos.print((list.get(i).writeString()));
						dos.println(); 
					}

					//dos.print("");
					dos.close();
					fos.close();
					
					System.exit(0);

				}
				catch (IOException e) {
					System.out.println("File Error");
				}
		  }
		}

	
	public static class ActivityFrame extends JFrame{ 
		JLabel labelActType, labelDesc, labelCase, labelAtt, labelSdate, labelEdate; 
		JTextField tfActType, tfDesc, tfCase, tfAtt, tfSdate, tfEdate;
		JButton buttonSubmit, buttonClear, buttonEnd; 
		//List<Activity> list2 = new ArrayList<Activity>();
		
		public ActivityFrame() { 
			//
			//list2 = list;
			
			Container c = getContentPane(); //gets hold of the output screen
			c.setLayout(new GridLayout(7,2)); 
			c.setPreferredSize(new Dimension(500,500));
			
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
				//	
					// Date not implemented
					Activity save = new Activity(
						tfActType.getText(), 
						tfDesc.getText(), 
						tfCase.getText(),
						tfAtt.getText(), 
						new Date(),
						new Date(),
						false);
					
					//list2.add(save);
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
	
	///////////////////////////////////////////////////////
	
	
	
	
	public static void main( String [] args ){
///////////////////////////////////////////////
		
		
		//Test
		
		//End test
		

		

		

		// try to open act.txt
		// if none create act.txt display "No Activities"
		try{

			reader = new BufferedReader(new FileReader(file));
			try {
				// Reads each line of file, stores into a string to end of file
				while ((inputLine = reader.readLine()) != null) {
					//System.out.println(parseIt(inputLine).writeString());

					// Sends each string to parseIt function which returns an activity object
					list.add(parseIt(inputLine));
				}
				
				reader.close();
			}
			catch (IOException e) {
				System.err.println("Error: " + e);
			}


		}
		catch (FileNotFoundException e) {
			// File is not found so new file "act.txt" must  be created.
			try{
				FileOutputStream fos = new FileOutputStream(file);
				PrintWriter dos = new PrintWriter(fos);

				// TEST LIST CODE REMOVE
				//for( int i = 0 ; i < 15 ; i++ ) {
				//	list.add( new Activity( "Phone"+i , "Call Tamy", "Case #", "Att John", new Date(), new Date(), false ));
				//	dos.print((list.get(i).writeString()));
				//	dos.println(); 
				//}
				// End TEST list code

				//dos.print("");
				dos.close();
				fos.close();	

				System.out.println("New act.txt file created");
			}
			catch (IOException e2) {
				System.out.println("File Error");
			}

		}

		catch (IOException e) {
			System.out.println("File Error");
		}

		// convert it to array
		Activity [] actGroup = list.toArray( new Activity[ list.size() ] );

		//DefaultTableModel model = new DefaultTableModel(); 

		//TEST
		


		//END TEST
		JTable table = new JTable(model); 


		// Create columns
		model.addColumn("ID");
		model.addColumn("Act Type");
		model.addColumn("Description");
		model.addColumn("Case");
		model.addColumn("Attorney");
		model.addColumn("Start Date");
		model.addColumn("End Date");
		model.addColumn("Status");
		//end columns


		// Append a row 
		//model.addRow(new Object[]{"v1", "v2"});

		// Iterate through Array adding a row for each activity
		for (int i=0; i < actGroup.length; i++){
			model.addRow(new Object[]{i+1,actGroup[i].getActType(), actGroup[i].getActDesc(),actGroup[i].getActCase(),
					actGroup[i].getActAttorney(),actGroup[i].getStartDate(),actGroup[i].getEndDate(),actGroup[i].getStatus()});
		}

		//table.setEnabled(false);
		JFrame frame = new JFrame();

		// Create New Activity button
		createAct = new JButton("New Activity");
		frame.add(createAct, BorderLayout.SOUTH);
		
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        System.out.println("New Activity");
		        ActivityFrame framex = new ActivityFrame();
		        
		      }
		    };
		
		createAct.addActionListener(actionListener); 
		
		
		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  
				// if double click
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      
			      if (column == 7){
			    	  // Change Status on double click
			    	  list.get(row).setStatus(!(list.get(row).getStatus()));
			    	  model.setValueAt(list.get(row).getStatus(), row, column);
			    	  System.out.println("Status Change" + list.get(row).toString());
			      }
			      
			      //Debug
			      System.out.println("Selected Row: " + row + " column: "+ column);
			      
			      // insert code for pop up here
			      
			      // Two buttons delete or completed
			    }
			  }
			});


		frame.add( new JScrollPane( table ));
		frame.pack();
		
		// on close window listeners
		frame.addWindowListener(new WindowEventHandler());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


		frame.setVisible( true ); 


	}	
}