import java.util.LinkedList;



abstract class Module {

	String modulecode;
	
	LinkedList<String>columnNames;
	LinkedList<Object>moduleData;
	
	boolean loaded = false;
	String[] errors = {};
	/**
	 * Constructor
	 */
	Module(){
		
		this.columnNames = new LinkedList<String>();
		this.moduleData = new LinkedList<Object>();
		
		this.setModuleCode("BASE");
	}
	
	/**
	 * Sets module code
	 * @param value
	 */
	void setModuleCode(String value){
		this.modulecode = value;
	}
	
	/**
	 * Returns module code
	 * @return String
	 */
	String getModuleCode(){
		return this.modulecode;
	}
	
	/**
	 * Sets if record was loaded
	 * @param value
	 */
	void setLoaded(boolean value){
		this.loaded = value;
	}
	
	/**
	 * Return true if information was loaded 
	 * @return
	 */
	boolean isLoaded(){
		return this.loaded;
	}
	
	/**
	 * Builds Module form
	 */
	void formEdit()
	{
		
		
		
	}//end method
	
	/**
	 * Build Module Display Form
	 */
	void formView()
	{	
	}
	
	/**
	 * This method should allow the user to search for a record
	 */
	void searchRecord(){

	}
	
	/**
	 * This method should be called on form validation prior to 
	 * storing the information.
	 * @return boolean
	 */
	boolean validateRecord(){
		return true;	
	}
	
	/**
	 * Loads default values to module.
	 * This should be called before the form
	 * is loaded in creation
	 */
	void initRecord(){
		
	}
	
	/**
	 * Reads storage and loads a record into object
	 * this method should be loaded with the information 
	 * to display in the form while modifiying
	 */
	void loadRecord(){
		
	}
	
	/**
	 * Writes record to permanent storage.
	 */
	void saveRecord(){
		
	}
	
	/**
	 * Sets module column
	 * @param cn
	 */
	void setColumNames(String cn){
		this.columnNames.add(cn);
	}
	
	/**
	 * Returns list of columns 
	 * @return
	 */
	String[] getColumNamesArray(){
		
		String[] columns = this.columnNames.toArray(new String[this.columnNames.size()]);
		return columns;
	}
	

	/**
	 * Sets module column
	 * @param cn
	 */
	void setModuleData(Object record){
		this.moduleData.add(record);
	}
	
	
	Object[][] getModuleDataArray(){
		Object[][] data = {
				{ "Kathy", "Smith", "Snowboarding", new Integer(5),	new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),	new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),	new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
		//String[][] data = this.moduleData.toArray(new String[this.columnNames.size()]);
		return data;
	}
	
	/**
	 * Just a method to debug
	 */
	void dump(){
		System.out.println("The debug method for Module: "+this.getModuleCode());
	}
}//end class
