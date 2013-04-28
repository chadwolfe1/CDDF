/**
 * Activity Class
 * @author Dean Burgos
 *
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Activity extends Module {

	// Class parameters

	private String actType, 	// Activity Type such as Phone Call, Meeting, Delivery
	actDesc, 					// Activity Description, short text describing activity like a small note in an agenda
	actCase,					// Title of case associated with Activity can be none
	actAttorney,				// Attorney assigned to Activity
	startDate, 
	endDate;					// Beginning and ending dates associated with activity
	private Boolean actStatus = false;	// Toggle activity status true if Activity has been completed, false if incomplete
	String modulecode = "ACT";

	
	Activity( String t, String d, String startD, String endD, String c, String a, Boolean aS ){
		setActType(t);
		setActDesc(d);
		setStartDate(startD);
		setEndDate(endD);
		setActCase(c);
		setActAttorney(a);
		setStatus(aS);   
	}

	// Default Constructor
	Activity(){
		this(
				"",
				"",
				"",
				"",
				"",
				"",
				false);   
	}

	Activity(String[] row){
		if (row.length == 0) {
			return;
		}

		this.setActType(row[0]);
		this.setActDesc(row[1]);
		this.setStartDate(row[2]);
		this.setEndDate(row[3]);
		this.setActCase(row[4]);
		this.setActAttorney(row[5]);
		this.setStatus(Boolean.parseBoolean(row[6]));

	}

	// Create New Activity / Edit Activity form
	void formEdit(ActivityList al, CaseList caselist, ContactList contactlist, boolean createRow) {

		Vector<String> attorneyNames = new Vector<String>();	// creates array of Attorney and Paralegal names for JCombo Box
		Vector<String> caseNames = new Vector<String>();		// creates array of Case names for JCombo Box
		
		
		// This will search through the entire list of contacts but will only add the names of contact types: Attorney and Paralegal
		// NOTE: only these two contact types will be performing activities that is why only these two are selected.
		for (int i =0; i < contactlist.rowCount(); i++)
		{
			Contact contact = contactlist.getRowContact(i);
			if (contact.getContactType().equals("Attorney"))
				attorneyNames.add(contact.getLastname());	
			else if (contact.getContactType().equals("Paralegal"))
				attorneyNames.add(contact.getLastname());
		}

		// This will search through the entire list of case and grabs the name of each case
		for (int i =0; i < caselist.caseList.getRowCount(); i++)
		{
			Case cases = caselist.getRowCase(i);
			caseNames.add(cases.getCaseName());	
		}

		attorneyNames.add("No Attorney");	// Place holder just in case no Attorney is assigned to an activity
		caseNames.add("No Case");			// Place holder just in case no Case is assigned to an activity

		// Build a panel
		JPanel panel1 = new JPanel(new GridLayout(0, 2));

		// Window Size here
		panel1.setPreferredSize(new Dimension(400, 200));
		// Adding Fields to the form

		panel1.add(new JLabel("Activity Form"));
		panel1.add(new JLabel(""));

		JTextField tfType = new JTextField(this.getActType());
		panel1.add(new JLabel("Activity Type: "));
		panel1.add(tfType);

		JTextField tf2Desc = new JTextField(this.getActDesc());
		panel1.add(new JLabel("Description: "));
		panel1.add(tf2Desc);

		JTextField fl5 = new JTextField(this.getStartDate());
		panel1.add(new JLabel("Start Date: "));
		panel1.add(fl5);

		JTextField fl6 = new JTextField(this.getEndDate());
		panel1.add(new JLabel("End Date: "));
		panel1.add(fl6);

		JComboBox fl3 = new JComboBox(caseNames);
		fl3.setSelectedItem("No Case");
		panel1.add(new JLabel("Case: "));
		panel1.add(fl3);

		JComboBox fl4 = new JComboBox(attorneyNames);
		fl4.setSelectedItem(this.getActAttorney());
		panel1.add(new JLabel("Attorney: "));
		panel1.add(fl4);

		String[] items3 = {"Inactive", "Active"};
		JComboBox cf6 = new JComboBox(items3);
		panel1.add(new JLabel("Activity Status: "));
		panel1.add(cf6);

		panel1.add(new JLabel(""));
		panel1.add(new JLabel(""));

		int result = JOptionPane.showConfirmDialog(null, panel1,
				"Activity Module", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// Submits Activity for creation
			// Firsts validates activity fields have required text fields occupied *NOTE: not all text fields are required for Activities

			//Load
			this.setActType(tfType.getText());
			this.setActDesc(tf2Desc.getText());
			this.setStartDate(fl5.getText());
			this.setEndDate(fl6.getText());
			this.setActCase(fl3.getSelectedItem().toString());
			this.setActAttorney(fl4.getSelectedItem().toString());
			if (cf6.getSelectedIndex()==0)
				this.setStatus(false);
			else
				this.setStatus(true);

			if (this.validateRecord()) {
				this.setLoaded(true);// setting flag.

				if (createRow) {

					this.saveRecord(al);
				}

			} else {

				this.setLoaded(false);// setting flag.
				this.formEdit(al, caselist, contactlist, createRow);

			}

		} else {

			// Set defaults and close
			this.initRecord();
			this.setLoaded(false);

		}

	}

	/**
	 * @see Module::validateRecord()
	 */
	boolean validateRecord() {

		if (this.getActType().equals("")) {
			OutputBox.alert("Activity Module", "Invalid Act Type");
			return false;
		}

		if (this.getActDesc().equals("")) {
			OutputBox.alert("Activity Module", "Invalid Act Description");
			return false;
		}		
		return true;
	}

	void saveRecord(ActivityList al) {

		al.addActivityList(this);
		al.saveFile();

	}
	// end

	// Setters
	void setActType(String type){
		actType = type;
	}
	void setActDesc(String desc){
		actDesc = desc;
	}
	void setActCase(String c){
		actCase = c;
	}
	void setActAttorney(String actAtt){
		actAttorney = actAtt;
	}
	void setStartDate(String actStart){
		startDate = actStart;
	}
	void setEndDate(String actEnd){
		endDate = actEnd;
	}
	void setStatus(Boolean status){
		actStatus = status;
	}

	// Getters
	String getActType(){
		return actType;
	}
	String getActDesc(){
		return actDesc;

	}
	String getActCase(){
		return actCase;
	}
	String getActAttorney(){
		return actAttorney;
	}
	String getStartDate(){
		return startDate;
	}
	String getEndDate(){
		return endDate;
	}
	Boolean getStatus(){
		return actStatus;
	}

	public String displayString(){
		return ("Act Type: "	+ actType 		+ "\n" +
				"Description: " + actDesc 		+ "\n" +
				"Case: " 		+ actCase 		+ "\n" +
				"Attorney: " 	+ actAttorney 	+ "\n" +
				"Start Date: " 	+ startDate		+ "\n" +
				"End Date: " 	+ endDate		+ "\n" +
				"Status: " 		+ actStatus		+ "\n");
	}

	public String writeString(){
		return (this.getActType() 		+ Constants.CSV_FIELD_SEPARATOR +
				this.getActDesc() 		+ Constants.CSV_FIELD_SEPARATOR +
				this.getStartDate() 	+ Constants.CSV_FIELD_SEPARATOR +
				this.getEndDate() 		+ Constants.CSV_FIELD_SEPARATOR +
				this.getActCase()	+ Constants.CSV_FIELD_SEPARATOR +
				this.getActAttorney()		+ Constants.CSV_FIELD_SEPARATOR +
				Boolean.toString(this.getStatus())		+ Constants.CSV_FIELD_SEPARATOR );
	}

}
// End Activity.java