import java.awt.Dimension;
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
	
	JTextField fl1, fl2, fl3, fl5;
	
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
	
	Contact (String[] row)
	{
		
		if(row.length==0) return;
		this.setFirstname(row[0]);
		this.setLastname(row[1]);
		this.setPhone(row[2]);
	}
	
	/**
	 * @see Module::formEdit()
	 */
	//void formEdit(JPanel panel)
	void formEdit(ContactList cl)
	{
		
		
		//Build a panel
		JPanel panel1 = new JPanel(new GridLayout(0, 2));

		panel1.setPreferredSize(new Dimension(400,200));
		//Adding Fields to the form
		
		panel1.add(new JLabel("Contact Form"));
		panel1.add(new JLabel(""));
		
		JTextField fl1 = new JTextField(this.getFirstname());
	    panel1.add(new JLabel(Constants.CONT_FIRSTNAME+":"));
	    panel1.add(fl1);
	    
	    JTextField fl2 = new JTextField(this.getLastname());
	    panel1.add(new JLabel("Lastname:"));
	    panel1.add(fl2);
	    
	    JTextField fl5 = new JTextField(this.getPhone());
	    panel1.add(new JLabel("Phone:"));
	    panel1.add(fl5);
		
	    JTextField fl6 = new JTextField(this.getLastname());
	    panel1.add(new JLabel("Fax:"));
	    panel1.add(fl6);
	    
	    
	    String[] items1 = {"Customer", "Attorney", "Administrator", "Paralegal", "Other"};
	    JComboBox fl3 = new JComboBox(items1);
	    panel1.add(new JLabel("Contact Type:"));
		panel1.add(fl3);
		
		String[] items2 = { "Inactive", "Active"};
	    JComboBox fl4 = new JComboBox(items2);
	    panel1.add(new JLabel("Contact Status:"));
		panel1.add(fl4);
		
		panel1.add(new JLabel(""));
		panel1.add(new JLabel(""));
	   
	    
	    int result = JOptionPane.showConfirmDialog(null, panel1, Constants.CONT_MOD_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    if (result == JOptionPane.OK_OPTION) {
	    	
	    	this.setFirstname(fl1.getText());
	    	this.setLastname(fl2.getText());
	    	this.setPhone(fl5.getText());
	    	if(this.validateRecord())
	    	{
	    		this.setLoaded(true);//setting flag.
	    		
	    		
	    		this.saveRecord(cl);
	    		
	    		OutputBox.display(0, Constants.CONT_MOD_TITLE, Constants.GEN_LBL_THANKYOU);
	    		
	    	}else{
	    		
	    		this.setLoaded(false);//setting flag.
	    		this.formEdit(cl);
	    		
	    	}
	    	
	    } else {
	    	
	    	//Set defaults and close
	    	this.initRecord();
	    	this.setLoaded(false);

	    }
	     
		
		
	}
	
	
	/**
	 * @see Module::validateRecord()
	 */
	boolean validateRecord(){
		
		//Validating first name
		if(this.getFirstname().equals("")){
			//Just a little note
			OutputBox.alert(Constants.CONT_MOD_TITLE, "Invalid First name");
			return false;
		}
		
		if(this.getLastname().equals("")){
			//Just a little note
			OutputBox.alert(Constants.CONT_MOD_TITLE, "Invalid Last name");
			return false;
		}
		
		
		if(this.getPhone().equals("")){
			//Just a little note
			OutputBox.alert(Constants.CONT_MOD_TITLE, "Invalid Phone");
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
	void saveRecord(ContactList cl){
		
		cl.addContactList(this);
		cl.saveFile();
		
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
