import java.util.LinkedList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


abstract class Module {

	String modulecode = "BASE";
	String module_filename = "";
	LinkedList<String>columnNames = new LinkedList<String>();
	
	
	boolean loaded = false;
	String[] errors = {};
	/**
	 * Constructor
	 */
	Module(){
		
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
	
	int getColumNameSize(){
		return this.columnNames.size();
	}
	
	String getColumNameIndex(int index){
		return this.columnNames.get(index);
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
	 * Just a method to debug
	 */
	void dump(){
		System.out.println("The debug method for Module: "+this.getModuleCode());
	}
	
	
	void initModuleDataFile(String filename){
				
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			PrintWriter dos = new PrintWriter(fos);
			dos.close();
			fos.close();
		}catch (IOException e) {
			System.out.println(Constants.GEN_ERR_CANNOT_OPEN_FILE);
		}
		
	}
	
		
}//end class
