import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
* Class ButtonAction
*  
* Legal Case Management System
* Florida State University
* @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos
* @version 1
* @since 2013-03-01
*/

public class ButtonAction implements MouseListener
{
	int btn_id;
	
	/**
	 * Constructor
	 * @param b
	 */
	ButtonAction(int b){
		btn_id = b;
	}
	
	/**
	 * Action for button is handled here
	 */
	public void mouseClicked(MouseEvent e){
		
		//Process button action
		Contact c = new Contact();
		switch(btn_id){
			case 1://Drawing
				c.formEdit();
				c.dump();
				break;
			case 2:
				c.searchRecord();
				
				break;
			case 3://Image
				//
				break;
			case 4://Browser
				break;
			case 5:
				break;
			case 99://Close
				//Start.end();
				break;
		}
	}//end method
	
	public void mouseEntered(MouseEvent e){}//end method
	public void mouseExited(MouseEvent e){}//end method
	public void mousePressed(MouseEvent e){}//end method
	public void mouseReleased(MouseEvent e){}//end method
}//end class
