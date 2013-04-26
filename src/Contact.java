import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Contact Class
 * Legal Case Management System
 * Florida State University
 * 
 * @author Francisco Santana
 * @version 1
 * @since 2013-03-01
 */
public class Contact extends Module {

	String modulecode = "CONT";
	
	/*Contact Properties*/
	private String firstname = "";
	private String lastname = "";
	private String contactType = "";
	private String phone = "";
	private String fax = "";
	private String customerStatus = "";


	/**
	 * Constructor
	 */
	Contact() {

	}

	Contact(String firstname, String lastname, String phone, String fax, String contactType, String customerStatus) {
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setPhone(phone);
		this.setFax(fax);
		this.setContactType(contactType);
		this.setCustomerStatus(customerStatus);
	}

	Contact(String[] row) {

		if (row.length == 0) {
			return;
		}
		
		this.setFirstname(row[0]);
		this.setLastname(row[1]);
		this.setPhone(row[2]);
		this.setFax(row[3]);
		this.setContactType(row[4]);
		this.setCustomerStatus(row[5]);
	}

	/**
	 * @see Module::formEdit()
	 */
	// void formEdit(JPanel panel)
	void formEdit(ContactList cl, boolean createRow) {

		// Build a panel
		JPanel panel1 = new JPanel(new GridLayout(0, 2));

		panel1.setPreferredSize(new Dimension(400, 200));
		// Adding Fields to the form

		panel1.add(new JLabel("Contact Form"));
		panel1.add(new JLabel(""));

		JTextField fl1 = new JTextField(this.getFirstname());
		panel1.add(new JLabel("Firstname:"));
		panel1.add(fl1);

		JTextField fl2 = new JTextField(this.getLastname());
		panel1.add(new JLabel("Lastname:"));
		panel1.add(fl2);

		JTextField fl5 = new JTextField(this.getPhone());
		panel1.add(new JLabel("Phone:"));
		panel1.add(fl5);

		JTextField fl6 = new JTextField(this.getFax());
		panel1.add(new JLabel("Fax:"));
		panel1.add(fl6);

		JComboBox fl3 = new JComboBox(Constants.CONT_CUSTOMER_TYPES);
		fl3.setSelectedItem(this.getContactType());
		panel1.add(new JLabel("Contact Type:"));
		panel1.add(fl3);

		JComboBox fl4 = new JComboBox(Constants.CONT_STATUS);
		fl4.setSelectedItem(this.getCustomerStatus());
		panel1.add(new JLabel("Contact Status:"));
		panel1.add(fl4);

		panel1.add(new JLabel(""));
		panel1.add(new JLabel(""));

		int result = JOptionPane.showConfirmDialog(null, panel1,
				Constants.CONT_MOD_TITLE, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {

			//Load
			this.setFirstname(fl1.getText());
			this.setLastname(fl2.getText());
			this.setPhone(fl5.getText());
			this.setFax(fl6.getText());
			this.setContactType(fl3.getSelectedItem().toString());
			this.setCustomerStatus(fl4.getSelectedItem().toString());
			
			if (this.validateRecord()) {
				this.setLoaded(true);// setting flag.

				if (createRow) {
					this.saveRecord(cl);
				}

			} else {

				this.setLoaded(false);// setting flag.
				this.formEdit(cl, createRow);

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

		// Validating first name
		if (this.getFirstname().equals("")) {
			// Just a little note
			OutputBox.alert(Constants.CONT_MOD_TITLE, "Invalid First name");
			return false;
		}

		if (this.getLastname().equals("")) {
			// Just a little note
			OutputBox.alert(Constants.CONT_MOD_TITLE, "Invalid Last name");
			return false;
		}

		if (this.getPhone().equals("")) {
			// Just a little note
			OutputBox.alert(Constants.CONT_MOD_TITLE, "Invalid Phone");
			return false;
		}

		
		return true;
	}


	/**
	 * @see Module::loadRecord()
	 */
	void saveRecord(ContactList cl) {

		cl.addContactList(this);
		cl.saveFile();

	}


	/**
	 * @see Module::dump()
	 */
	void dump() {
		super.dump();
		if (this.isLoaded()) {
			System.out.println("The contact name is: " + this.getLastname()
					+ ", " + this.getFirstname());
		} else {
			System.out.println("Contact not loaded yet.");
		}
	}


	/**
	 * Returns string formated as csv
	 * 
	 * @return String
	 */
	public String writeString() {
		return (this.getFirstname() + Constants.CSV_FIELD_SEPARATOR
				+ this.getLastname() + Constants.CSV_FIELD_SEPARATOR
				+ this.getPhone() + Constants.CSV_FIELD_SEPARATOR
				+ this.getFax() + Constants.CSV_FIELD_SEPARATOR
				+ this.getContactType() + Constants.CSV_FIELD_SEPARATOR
				+ this.getCustomerStatus() + Constants.CSV_FIELD_SEPARATOR);
		
	}
	
	// --------------------------------
	// Assessors and setters
	// --------------------------------

	public void setFirstname(String firstname) {
		this.firstname = Module.strScape(firstname);
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = Module.strScape(lastname);
	}

	public String getLastname() {
		return this.lastname;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = Module.strScape(phone);
	}

	
	public String getFax(){
		return this.fax;
	}
	
	public void setFax(String fax){
		if(fax.equals("")){
			fax = "NONE";
		}
		this.fax = Module.strScape(fax);
	}
	
	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	
	


	
	
	

}// end class
