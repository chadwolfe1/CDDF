/**
 * ActivityList Class
 * @author Dean Burgos
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ActivityList extends Module {

	DefaultTableModel activityList = null;
	JTable activityTable = null;
	JComboBox fl4 = null;
	JComboBox fl3 =null;
	JComboBox cf6 = null;
	/**
	 * Constructor
	 */
	ActivityList() {

		/**
		 * Activity list
		 */
		this.activityList = new DefaultTableModel() {
			// Disables user ability to edit cells directly on JTable
			// Disabled to reduce data misrepresentation

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		activityList.addColumn("Act Type");
		activityList.addColumn("Description");
		activityList.addColumn("Start Date");
		activityList.addColumn("End Date");
		activityList.addColumn("Case");
		activityList.addColumn("Attorney/Paralegal");
		activityList.addColumn("Status");

		activityTable = new JTable(activityList);
		activityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// From module method
		readFile();

	}

	/*
	 * @param s
	 * 
	 * @return
	 */
	Activity parseLine(String sline) {

		String[] line = sline.split(Constants.CSV_FIELD_SEPARATOR);

		return new Activity(getArrayIndex(line, 0), getArrayIndex(line, 1), getArrayIndex(line, 2), 
				getArrayIndex(line, 3), getArrayIndex(line, 4), getArrayIndex(line, 5), Boolean.parseBoolean(getArrayIndex(line, 6) ));
	}

	void addActivityList(Activity a) {

		// DEBUG
		// System.out.println(a.displayString());

		String[] row = { a.getActType(), a.getActDesc(), a.getStartDate(), a.getEndDate(), a.getActCase(), a.getActAttorney(), Boolean.toString(a.getStatus()) };
		activityList.addRow(row);
	}

	Activity parseEntry(String[] line)
	{
		return new Activity(getArrayIndex(line, 0), getArrayIndex(line, 1), getArrayIndex(line, 2), 
				getArrayIndex(line, 3), getArrayIndex(line, 4), getArrayIndex(line, 5), Boolean.parseBoolean(getArrayIndex(line, 6) ));
	}

	Integer rowCount()
	{
		return activityList.getRowCount();
	}



	/**
	 * Update activity list row
	 * 
	 * @param a
	 * @param row
	 */
	void updateActivityListRow(Activity a, int row) {
		activityList.setValueAt(a.getActType(), row, 0);
		activityList.setValueAt(a.getActDesc(), row, 1);
		activityList.setValueAt(a.getStartDate(), row, 2);
		activityList.setValueAt(a.getEndDate(), row, 3);
		activityList.setValueAt(a.getActCase().toString(), row, 4);
		activityList.setValueAt(a.getActAttorney().toString(), row, 5);
		activityList.setValueAt(a.getStatus().toString(), row, 6);

	}

	/**
	 * Display the activity list
	 */
	void getDisplayList(JPanel panel, final ContactList contactList, final CaseList caseList) {

		panel.removeAll();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		activityTable.setPreferredScrollableViewportSize(panel
				.getPreferredSize());
		activityTable.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(activityTable);
		panel.add(scrollPane);

		JPanel panelb = new JPanel();

		/**
		 * Update Button
		 * Update serves same function between Contact, Case, and Activity Module
		 * Update will take the selected row and populate the respective modules edit form with that row's data.
		 * User can then change information of selected object and save it
		 * */
		JButton updateBtn = new JButton("Update");
		panelb.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Update

				int selected = activityTable.getSelectedRow();
				if(selected==-1){
					OutputBox.display(0, Constants.MAIN_WIN_TITLE, "No Item selected. \nPlease click in the row that requires modification.");
					return;
				}
				Activity at = getRowActivity(selected);
				at.formEdit(new ActivityList(), caseList, contactList, false);
				updateActivityListRow(at, selected);
				saveFile();
			}
		});

		/**
		 *  Delete Button
		 * 	Delete serves same function between Contact, Case, and Activity Module
		 *	Delete will take the selected row and ask user to confirm deletion
		 * 	if confirmed then delete will remove row from respective object/module list
		 */

		JButton deleteBtn = new JButton("Delete");
		panelb.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int selected = activityTable.getSelectedRow();
				if(selected==-1){
					OutputBox.display(0, Constants.MAIN_WIN_TITLE, "No Item selected. \nPlease click in the row that requires modification.");
					return;
				}

				int option = JOptionPane.showConfirmDialog(null, "Yes or No?",
						"Delete Activity", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					// Delete and update
					activityList.removeRow(selected);
					saveFile();
				}
			}
		});

		panel.add(panelb);

		JPanel panelc = new JPanel();

		// Search Buttons and Listeners
		// Each jcombo box has a respective button to search for selected item.
		// At the moment the search function can only search one selection at a time.
		// Upon user clicking on the search button the item from the Jcombo box will be selected and sent to the SearchTable function
		// a new Jtable will apear displaying results.
		// *NOTE: the popup JTable has no functions to add/update/or delete data
		// these operations have not been implemented for search results to prevent errors from new JTable rows not matching
		// Find Button
		JButton findAttBtn = new JButton("Search by Attorney");
		JButton findCaseBtn = new JButton("Search by Case");


		// JCombo Boxes
		ContactList contactlist = new ContactList();		// list of all contacts
		CaseList caselist = new CaseList();					// list of all cases
		Vector attorneyNames = new Vector();				// store Attorney Paralegal contact results
		Vector caseNames = new Vector();					// store Case Names

		// Attorney and Paralegal Search
		// Same search and store as Activity.java
		for (int i =0; i < contactlist.rowCount(); i++)
		{
			Contact contact = contactlist.getRowContact(i);
			if (contact.getContactType().equals("Attorney"))
				attorneyNames.add(contact.getLastname());	
			else if (contact.getContactType().equals("Paralegal"))
				attorneyNames.add(contact.getLastname());
		}
		attorneyNames.add("No Attorney");
		
		fl4 = new JComboBox(attorneyNames);
		fl4.setSelectedItem("No Attorney");
		panelc.add(new JLabel("  "));
		panelc.add(fl4);
		panelc.add(findAttBtn);
		
		// Case Search
		// Same search and store as Activity.java
		for (int i =0; i < caselist.caseList.getRowCount(); i++)
		{
			Case cases = caselist.getRowCase(i);
			caseNames.add(cases.getCaseName());	
		}
		caseNames.add("No Case");

		fl3 = new JComboBox(caseNames);
		fl3.setSelectedItem("No Case");
		panelc.add(new JLabel("  "));
		panelc.add(fl3);
		panelc.add(findCaseBtn);


		// Status Search
		String[] items3 = {"Inactive", "Active"};
		cf6 = new JComboBox(items3);
		panelc.add(cf6);
		JButton findStatusBtn = new JButton("Search by Status");

		panelc.add(findStatusBtn);

		findAttBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SearchTable st = new SearchTable();
				st.SearchForAtt(new ActivityList(), fl4.getSelectedItem().toString());

			}
		});
		findCaseBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SearchTable st = new SearchTable();
				st.SearchForCase(new ActivityList(), fl3.getSelectedItem().toString());

			}
		});
		findStatusBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SearchTable st = new SearchTable();
				Boolean stat = false;
				if (cf6.getSelectedIndex()==0)
					stat = false;
				else
					stat = true;
				st.SearchForStatus(new ActivityList(), stat);

			}
		});
		// End JCombo and Search code

		panel.add(panelc);

	}// end method

	/**
	 * Reads the file
	 */
	void readFile() {

		// Populate list
		String line = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"act.txt"));
			try {

				while ((line = reader.readLine()) != null) {

					Activity a = this.parseLine(line);
					addActivityList(a);
				}

			} catch (IOException e) {

				System.err.println("Error: " + e);

			} finally {
				reader.close();
			}

		} catch (FileNotFoundException e1) {

			// Initialize data file if not exists
			this.initModuleDataFile("act.txt");

		} catch (IOException e3) {

			System.out.println(Constants.GEN_ERR_CANNOT_OPEN_FILE);

		}
	}

	/**
	 * Saves Activity List to File
	 */
	void saveFile() {
		try {
			FileOutputStream fos = new FileOutputStream(
					"act.txt", false);
			PrintWriter dos = new PrintWriter(fos);

			for (int i = 0; i < activityList.getRowCount(); i++) {
				Activity act = getRowActivity(i);
				dos.println((act.writeString()));
			}

			dos.close();
			fos.close();

		} catch (IOException e) {
			System.out.println("File Error");
		}
	}

	/**
	 * Returns activity from List
	 * 
	 * @param rowIndex
	 * @return
	 */
	public String[] getRowData(int rowIndex) {
		// test the index
		if (activityList.getRowCount() == 0 || rowIndex < 0) {
			return null;
		}

		ArrayList<String> data = new ArrayList<String>();
		for (int col = 0; col < activityList.getColumnCount(); col++) {
			data.add((String) activityList.getValueAt(rowIndex, col));
		}

		String[] retVal = new String[data.size()];
		for (int i = 0; i < retVal.length; i++) {
			retVal[i] = data.get(i);
		}
		return retVal;
	}

	/**
	 * Builds activity object from row
	 * 
	 * @param rowIndex
	 * @return
	 */
	public Activity getRowActivity(int rowIndex) {

		return new Activity(getRowData(rowIndex));

	}

}

