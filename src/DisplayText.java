/**
* Class DisplayText
*  
* Legal Case Management System
* Florida State University
* @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos
* @version 1
* @since 2013-03-01
*/
public class DisplayText extends OutputBox{
	
	static int pos;
	static String selected_text;
	
	DisplayText(String title, String txt)//See OutputBox
	{
		super(title, txt);
	}//End method
	
	
	static void selectText(){
		selected_text = text.getSelectedText();
		pos = text.getCaretPosition();
	}

	static void insertText(){
		text.insert(selected_text, pos);
	}
	
	
}//end class
