import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* Class Action
*  
* Legal Case Management System
* Florida State University
* @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos
* @version 1
* @since 2013-03-01
*/
public class Action implements ActionListener
{
	
	//public static DisplayText dt;
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getActionCommand().equalsIgnoreCase("New Contact")){
			
			//dt = new DisplayText(Constants.MAIN_WIN_TITLE, "");
			
			Contact c = new Contact();
			c.formEdit();
			c.dump();
			
		}else if(e.getActionCommand().equalsIgnoreCase("New Case")){
			
			//Another option see Constants options
			
		}else if(e.getActionCommand().equalsIgnoreCase("Help")){
			//This may be help
			OutputBox.display(0, Constants.MAIN_WIN_TITLE, Constants.HELP);
			
		}else if(e.getActionCommand().equalsIgnoreCase("About Us")){
			
			//This may be about us
			OutputBox.display(0, Constants.GEN_LBL_ABOUT_US, Constants.ABOUT_US);
			
		}else if(e.getActionCommand().equalsIgnoreCase("Quit")){
			
			System.exit(0);
			
		}else{
			//Something else			
		}
		
	}//end method
}//end class
