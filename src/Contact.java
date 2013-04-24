import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contact extends Module
{

	
	
	String modulecode = "CONT";
	String firstname = ""; 
	String lastname = "";
	Integer contactType = 0;
	String phone = "";
	String fax = "";
	Date creationDate = null;
	Integer customerId = 0;
	Integer customerStatus = 0;
	
	
	/**
	 * Constructor
	 */
	Contact(){
		
	}
	
	Contact (String firstname, String lastname, String phone)
	{
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setPhone(phone);
	}
	

	
	/**
	 * @see Module::formEdit()
	 */
	void formEdit()
	{
		
		
		//Build a panel
		JPanel panel = new JPanel(new GridLayout(0, 1));
		
		
		//Adding Fields to the form
	    JTextField fl1 = new JTextField(this.getFirstname());
	    panel.add(new JLabel(Constants.CONT_FIRSTNAME+":"));
	    panel.add(fl1);
	    
	    JTextField fl2 = new JTextField(this.getLastname());
	    panel.add(new JLabel("Lastname:"));
	    panel.add(fl2);
	    
	    String[] items1 = {"Customer", "Attorney", "Administrator", "Paralegal", "Other"};
	    JComboBox fl3 = new JComboBox(items1);
	    panel.add(new JLabel("Contact Type:"));
		panel.add(fl3);
		
		String[] items2 = { "Inactive", "Active"};
	    JComboBox fl4 = new JComboBox(items2);
	    panel.add(new JLabel("Contact Status:"));
		panel.add(fl4);
	    
		JTextField fl5 = new JTextField(this.getLastname());
	    panel.add(new JLabel("Phone:"));
	    panel.add(fl5);
		
	    JTextField fl6 = new JTextField(this.getLastname());
	    panel.add(new JLabel("Fax:"));
	    panel.add(fl6);

	    
	    int result = JOptionPane.showConfirmDialog(null, panel, Constants.CONT_MOD_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    if (result == JOptionPane.OK_OPTION) {
	    	
	    	this.setFirstname(fl1.getText());
	    	this.setLastname(fl2.getText());
	    	this.setPhone(fl5.getText());
	    	if(this.validateRecord())
	    	{
	    		this.setLoaded(true);//setting flag.
	    		this.saveRecord();
	    		OutputBox.display(0, Constants.CONT_MOD_TITLE, Constants.GEN_LBL_THANKYOU);
	    		
	    	}else{
	    		
	    		this.setLoaded(false);//setting flag.
	    		this.formEdit();
	    		
	    	}
	    	
	    } else {
	    	
	    	//Set defaults and close
	    	this.initRecord();
	    	this.setLoaded(false);

	    }

		
		
	}
	
	
	/**
	 * @see Module::searchRecord()
	 */
	void searchRecord(){
		
		ContactList list = new ContactList();
		list.displayList();
		
	}
	
	/**
	 * @see Module::validateRecord()
	 */
	boolean validateRecord(){
		
		//Validating first name
		if(this.getFirstname().equals("")){
			//Just a little note
			OutputBox.alert(Constants.CONT_MOD_TITLE, "Invalid Firstname");
			return false;
		}
		
		return true;	
	}
	
	/**
	 * @see Module::initRecord()
	 */
	void initRecord(){
		
	}
	
	/**
	 * @see Module::loadRecord()
	 */
	void loadRecord(){
		
	}
	
	/**
	 * @see Module::loadRecord()
	 */
	void saveRecord(){
		
		ContactList list = new ContactList();
		list.setContact(this);
		list.saveFile();
		
	}
	
	
	/**
	 * @see Module::formView()
	 */
	void formView()
	{
		System.out.println("formView");
	}
	
	/**
	 * @see Module::dump()
	 */
	void dump(){
		super.dump();
		if(this.isLoaded()){
			System.out.println("The contact name is: "+this.getLastname()+", "+this.getFirstname());
		}else{
			System.out.println("Contact not loaded yet.");
		}
	}
	
	//--------------------------------
	//Assessors and setters
	//--------------------------------
	
	
	void setFirstname(String value){
		this.firstname = value;
	}
	
	String getFirstname(){
		return this.firstname;
	}
	
	void setLastname(String value){
		this.lastname = value;
	}
	
	String getLastname(){
		return this.lastname;
	}
	
	String getPhone(){
		return this.phone;
	}
	
	void setPhone(String phone){
		this.phone = phone;
	}
	
	/**
	 * Returns string formated as csv
	 * @return
	 */
	public String writeString(){
		return (this.getFirstname()+Constants.CSV_FIELD_SEPARATOR +
				this.getLastname()+Constants.CSV_FIELD_SEPARATOR +
				this.getPhone()+Constants.CSV_FIELD_SEPARATOR);
	}
	
}//end class
