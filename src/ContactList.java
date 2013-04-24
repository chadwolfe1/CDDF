import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class ContactList extends Module{

	final static List<Contact> contacts = new ArrayList<Contact>();		// List of contacts
	
	
	
	ContactList(){
		
		//From module method
		/*this.setColumNames("ID");
		this.setColumNames("Firstname");
		this.setColumNames("Last Name");
		this.setColumNames("Phone");*/
		
		//Load data file
		readFile();
		
	}

	
	static DefaultTableModel model = new DefaultTableModel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			//all cells false
			return false;
		}
	};
	
	
	/**
	 /* 
	 * @param s
	 * @return
	 */
	Contact parseLine(String sline){
		String[] line = sline.split(Constants.CSV_FIELD_SEPARATOR);
		return new Contact(line[0], line[1], line[2]);		
	}
	
	
	/**
	 * Adds contact to the list
	 * @param c
	 */
	void setContact(Contact c){
		contacts.add(c);
	}
	
	
	/**
	 * Display the contact list
	 */
	public void displayList(){
		
		
		//model.addColumn("ID");
		model.addColumn("Firstname");
		model.addColumn("Lastname");
		model.addColumn("Phone");
		
		// Load list
		for (int i = 0; i < contacts.size(); i++)
		{
			Object[] row = {/*i,*/ 
							contacts.get(i).getFirstname(),
							contacts.get(i).getLastname(),
							contacts.get(i).getPhone()
							};
			model.addRow(row);
		}
		
		JFrame frame = new JFrame(Constants.CONT_MOD_TITLE);
		JTable table = new JTable(model);
		
		frame.setPreferredSize(new Dimension(700,400));
		frame.add( new JScrollPane( table ));
		frame.pack();

		// on close window listeners
		//frame.addWindowListener(new WindowEventHandler());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible( true ); 
	}
	
	
	/**
	 * Reads the file
	 */
	void readFile()
	{
		
		//Populate list
		String line;
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(Constants.APP_CONTACT_FILE));
			try {
				
				while ((line = reader.readLine()) != null) {
					this.setContact(this.parseLine(line));
				}
				
			}catch (IOException e) {
				
				System.err.println("Error: " + e);
				
			}finally{
				reader.close();
			}

		}catch (FileNotFoundException e1) {
			
			 //Initialize data file if not exists
			 this.initModuleDataFile(Constants.APP_CONTACT_FILE);
			 
		}catch (IOException e3) {
			
			System.out.println(Constants.GEN_ERR_CANNOT_OPEN_FILE);
			
		}
	}
	
	void saveFile(){
		 try{
				FileOutputStream fos = new FileOutputStream(Constants.APP_CONTACT_FILE, false);
				PrintWriter dos = new PrintWriter(fos);
				for( int i = 0 ; i < contacts.size() ; i++ ) {
					dos.println((contacts.get(i).writeString()));
				}
				dos.close();
				fos.close();

			}
			catch (IOException e) {
				System.out.println("File Error");
			}
	}

}
