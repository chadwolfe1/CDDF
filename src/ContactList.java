import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * Contact List Class
 * Legal Case Management System
 * Florida State University
 * 
 * @author Francisco Santana
 * @version 1
 * @since 2013-03-01
 */
public class ContactList extends Module {

	DefaultTableModel contactList = null;
	JTable contactTable = null;

	/**
	 * Constructor
	 */
	ContactList() {

		/**
		 * Contact list
		 */
		this.contactList = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		// contactList.addColumn("ID");
		contactList.addColumn("Firstname");
		contactList.addColumn("Lastname");
		contactList.addColumn("Phone");
		contactList.addColumn("Fax");
		contactList.addColumn("Contact Type");
		contactList.addColumn("Contact Status");
		
		
		contactTable = new JTable(contactList);
		contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// From module method
		readFile();

	}

	/*
	 * @param s
	 * 
	 * @return
	 */
	Contact parseLine(String sline) {
		
		String[] line = sline.split(Constants.CSV_FIELD_SEPARATOR);
		
		return new Contact(getArrayIndex(line, 0), getArrayIndex(line, 1), getArrayIndex(line, 2), 
				getArrayIndex(line, 3), getArrayIndex(line, 4), getArrayIndex(line, 5));
	}

	void addContactList(Contact c) {

		String[] row = { c.getFirstname(), c.getLastname(), c.getPhone(), c.getFax(), c.getContactType().toString(), c.getCustomerStatus().toString() };
		contactList.addRow(row);
	}
	
	Contact parseEntry(String[] line)
	{
		return new Contact(getArrayIndex(line, 0), getArrayIndex(line, 1), getArrayIndex(line, 2), 
				getArrayIndex(line, 3), getArrayIndex(line, 4), getArrayIndex(line, 5));
	}
	
	Integer rowCount()
	{
		return contactList.getRowCount();
	}
	


	/**
	 * Update contact list row
	 * 
	 * @param c
	 * @param row
	 */
	void updateContactListRow(Contact c, int row) {
		contactList.setValueAt(c.getFirstname(), row, 0);
		contactList.setValueAt(c.getLastname(), row, 1);
		contactList.setValueAt(c.getPhone(), row, 2);
		contactList.setValueAt(c.getFax(), row, 3);
		contactList.setValueAt(c.getContactType().toString(), row, 4);
		contactList.setValueAt(c.getCustomerStatus().toString(), row, 5);
		
	}

	/**
	 * Display the contact list
	 */
	void getDisplayList(JPanel panel) {

		// Add the scroll pane to this panel.
		panel.removeAll();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		contactTable.setPreferredScrollableViewportSize(panel
				.getPreferredSize());
		contactTable.setFillsViewportHeight(true);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(contactTable);
		panel.add(scrollPane);

		JPanel panelb = new JPanel();
		// Update Button
		JButton updateBtn = new JButton("Update");
		// updateBtn.setPreferredSize(new Dimension(200,30));
		panelb.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				// Update
				
				int selected = contactTable.getSelectedRow();
				if(selected==-1){
					OutputBox.display(0, Constants.MAIN_WIN_TITLE, "No Item selected. \nPlease click in the row that requires modification.");
					return;
				}
				Contact x = getRowContact(selected);
				x.formEdit(new ContactList(), false);
				updateContactListRow(x, selected);
				saveFile();
			}
		});

		// Delete Button
		JButton deleteBtn = new JButton("Delete");
		// deleteBtn.setPreferredSize(new Dimension(30,30));
		panelb.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				int selected = contactTable.getSelectedRow();
				if(selected==-1){
					OutputBox.display(0, Constants.MAIN_WIN_TITLE, "No Item selected. \nPlease click in the row that requires modification.");
					return;
				}
				
				int option = JOptionPane.showConfirmDialog(null, "Yes or No?",
						"Delete Contact", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					// Delete and update
					
					contactList.removeRow(selected);
					saveFile();
				}
			}
		});

		panel.add(panelb);

	}// end method

	/**
	 * Reads the file
	 */
	void readFile() {

		// Populate list
		String line = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					Constants.APP_CONTACT_FILE));
			try {

				while ((line = reader.readLine()) != null) {

					Contact c = this.parseLine(line);
					addContactList(c);
				}

			} catch (IOException e) {

				System.err.println("Error: " + e);

			} finally {
				reader.close();
			}

		} catch (FileNotFoundException e1) {

			// Initialize data file if not exists
			this.initModuleDataFile(Constants.APP_CONTACT_FILE);

		} catch (IOException e3) {

			System.out.println(Constants.GEN_ERR_CANNOT_OPEN_FILE);

		}
	}

	/**
	 * Saves Contact List to File
	 */
	void saveFile() {
		try {
			FileOutputStream fos = new FileOutputStream(
					Constants.APP_CONTACT_FILE, false);
			PrintWriter dos = new PrintWriter(fos);

			for (int i = 0; i < contactList.getRowCount(); i++) {
				Contact cont = getRowContact(i);
				dos.println((cont.writeString()));
			}

			dos.close();
			fos.close();

		} catch (IOException e) {
			System.out.println("File Error");
		}
	}

	/**
	 * Returns contact from List
	 * 
	 * @param rowIndex
	 * @return
	 */
	public String[] getRowData(int rowIndex) {
		// test the index
		if (contactList.getRowCount() == 0 || rowIndex < 0) {
			return null;
		}

		ArrayList<String> data = new ArrayList<String>();
		for (int col = 0; col < contactList.getColumnCount(); col++) {
			data.add((String) contactList.getValueAt(rowIndex, col));
		}

		String[] retVal = new String[data.size()];
		for (int i = 0; i < retVal.length; i++) {
			retVal[i] = data.get(i);
		}
		return retVal;
	}

	/**
	 * Builds contact object from row
	 * 
	 * @param rowIndex
	 * @return
	 */
	public Contact getRowContact(int rowIndex) {

		return new Contact(getRowData(rowIndex));

	}

}
