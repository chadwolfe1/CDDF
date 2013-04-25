import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Contact List Class
 * @author Francisco santana
 *
 */
public class ContactList extends Module{

	
	DefaultTableModel contactList = null;
	
	/**
	 * Constructor
	 */
	ContactList(){
		
		/**
		 * Contact list
		 */
		this.contactList = new DefaultTableModel(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			} 
		};
		
		//contactList.addColumn("ID");
		contactList.addColumn("Firstname");
		contactList.addColumn("Lastname");
		contactList.addColumn("Phone");

		
		//From module method
		readFile();
	
	}
		
	
	 /* 
	 * @param s
	 * @return
	 */
	Contact parseLine(String sline){
		String[] line = sline.split(Constants.CSV_FIELD_SEPARATOR);
		return new Contact(line[0], line[1], line[2]);		
	}
	
	void addContactList(Contact c){
		String[] row = { c.getFirstname(), c.getLastname(),	c.getPhone()};
		contactList.addRow(row);
	}
	
	/**
	 * Display the contact list
	 */
	void getDisplayList(JPanel panel){
		
		JTable table = new JTable(contactList);
		
		table.setPreferredScrollableViewportSize(panel.getPreferredSize());
		table.setFillsViewportHeight(true);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Add the scroll pane to this panel.
		panel.removeAll();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		
		
	}//end method
	
	
	/**
	 * Reads the file
	 */
	void readFile()
	{
		
		//Populate list
		String line = "";
				
		try{
			BufferedReader reader = new BufferedReader(new FileReader(Constants.APP_CONTACT_FILE));
			try {
				
				while ((line = reader.readLine()) != null) {
					
					Contact c = this.parseLine(line);
					addContactList(c);
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
	
	
	/**
	 * Saves Contact List to File
	 */
	void saveFile(){
		 try{
				FileOutputStream fos = new FileOutputStream(Constants.APP_CONTACT_FILE, false);
				PrintWriter dos = new PrintWriter(fos);
				
				for(int i=0; i<contactList.getRowCount(); i++)
			    {
					Contact cont = getRowContact(i);
					dos.println((cont.writeString()));					
			    }				
				
				dos.close();
				fos.close();

			}
			catch (IOException e) {
				System.out.println("File Error");
			}
	}
	
	
	/**
	 * Returns contact from List
	 * @param rowIndex
	 * @return
	 */
	public String[] getRowData(int rowIndex)
    {
        //test the index
        if (contactList.getRowCount()==0 || rowIndex < 0)
        {
            return null;
        }
        
        ArrayList < String >  data = new ArrayList < String > ();
        for (int col = 0; col  <  contactList.getColumnCount(); col++)
        {
            data.add((String) contactList.getValueAt(rowIndex, col));
        }
        
        String[] retVal = new String[data.size()];
        for (int i = 0; i  <  retVal.length; i++)
        {
            retVal[i] = data.get(i);
        }
        return retVal;
    }

	/**
	 * Builds contact object from row
	 * @param rowIndex
	 * @return
	 */
	public Contact getRowContact(int rowIndex){
		
		return new Contact(getRowData(rowIndex));
		
	}
	

	
	
	
}
