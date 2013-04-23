import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class ActivityTable {
	
	
	static JButton createAct;	
	static JButton deleteAct;
	final static List<Activity> list = new ArrayList<Activity>();		// Create activity list that all JFrames can read from
	static File file = new File("act.txt");								// holds file name that activity will read off
	static BufferedReader reader = null;								// used to read and open file
	static String inputLine;											// used to grab lines from text file



	static DefaultTableModel model = new DefaultTableModel() {
		// Needed to make sure JTable cannot be edited in display
		// regular Jtable allows text in cells to be edited, this turns that option off

		@Override
		public boolean isCellEditable(int row, int column) {
			//all cells false
			return false;
		}
	};
	
	static class WindowEventHandler extends WindowAdapter {
		  public void windowClosing(WindowEvent evt) {
			// This is attached to the Jframe for displaying activities, it runs this function when it closes,
			// upon activity jframe closing this should happen:
		    // Erase old text file
			// Creates new file using modified listarray
			  
			  try{
					FileOutputStream fos = new FileOutputStream(file);
					PrintWriter dos = new PrintWriter(fos);

					for( int i = 0 ; i < list.size() ; i++ ) {
						// Goes through list of activities and prints information as a tab delimited list
						// in text file.
						dos.print((list.get(i).writeString()));
						dos.println(); 
					}
					dos.close();	// close fos stream
					fos.close();	// close dos stream

				}
				catch (IOException e) {
					System.out.println("File Error");
				}
		  }
		}
	
	public static Activity parseIt(String s){
		// Parses a line read from text into an Activity object
		// using the tab character as a delimiter
		// returns activity object created using line text
		
		String[] line = s.split("\\t");

		return new Activity(line[0],line[1],line[2],line[3],line[4],line[5],Boolean.parseBoolean(line[6]));
	}
	
	public void DisplayTable(){

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
			// if error opening file
			System.err.println("Error: " + e);
		}


	}
	catch (FileNotFoundException e1) {
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

	catch (IOException e3) {
		System.out.println("File Error");
	}



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

	// convert it to array
	// list reformat
	for (int i=0; i < list.size(); i++){
		model.addRow(new Object[]{i+1,list.get(i).getActType(), list.get(i).getActDesc(),list.get(i).getActCase(),
				list.get(i).getActAttorney(),list.get(i).getStartDate(),list.get(i).getEndDate(),list.get(i).getStatus()});
	}
	// end list format
	

	JFrame frame = new JFrame();
	JPanel subPanel = new JPanel();

	// Create New Activity button
	// attaches listener, for upon click
	// runs Activity Frame to create a new Activity
	createAct = new JButton("New Activity");
	deleteAct = new JButton("Delete Activity");
	//frame.add(createAct, BorderLayout.SOUTH);
	subPanel.add(createAct);
	//subPanel.add(deleteAct);

	frame.add(subPanel, BorderLayout.SOUTH);
	
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("New Activity");
			ActivityCreate framex = new ActivityCreate(list, model);

		}
	};
	
	ActionListener actionListener2 = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			int option = JOptionPane.showConfirmDialog (null, "Yes or No?", "DeleteStuff", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		}
	};

	createAct.addActionListener(actionListener); 
	//deleteAct.addActionListener(actionListener2); 

	// End create activity button


	table.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			// Listens for mouse clicks on Jtable cells
			JTable target = (JTable)e.getSource();
			int row = target.getSelectedRow();
			int column = target.getSelectedColumn();
			
			// if double click
			if (e.getClickCount() == 1) {
				

				if (column == 7){
					// Change Status on double click on status row
					list.get(row).setStatus(!(list.get(row).getStatus()));
					model.setValueAt(list.get(row).getStatus(), row, column);
				}

				//Debug
				System.out.println("Selected Row: " + row + " column: "+ column);

				// insert code for pop up here
				
				
				}
			if (e.getClickCount() == 2) {
				int option = JOptionPane.showConfirmDialog (null, "Do you want to delete this activity?", "Delete Activity?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(option == 0){
					System.out.println("Deleting activity at row " + row);
					model.removeRow(row);
					list.remove(row);
					
				}
				

				// Two buttons delete or completed
			}
		}
	});

	frame.setPreferredSize(new Dimension(700,400));
	frame.add( new JScrollPane( table ));
	frame.pack();

	// on close window listeners
	frame.addWindowListener(new WindowEventHandler());
	frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


	frame.setVisible( true ); 
	
	}


}	


