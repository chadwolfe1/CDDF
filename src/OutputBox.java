import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Container;

/**
* Various Output Forms
* Legal Case Management System
* Florida State University
* @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos
* @version 1
* @since 2013-03-01
*/
public class OutputBox extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JScrollPane panel;
	static JTextArea text;
	
	OutputBox(String title, String txt)
	{
		
		super(title);
		text = new JTextArea(txt);
		panel = new JScrollPane(text);
		
		
		Container c = getContentPane();
		c.add(panel);
		
		
		setBounds(Constants.EX_POS, Constants.EY_POS, 
				Constants.EX_W, Constants.EY_H);
		setVisible(true);
	}//End method
	
	static void  alert(int i, String s){
		JOptionPane.showMessageDialog(null, s, s, JOptionPane.ERROR_MESSAGE);
	}
	
	static void  alert(String t, String s){
		JOptionPane.showMessageDialog(null, s, t, JOptionPane.ERROR_MESSAGE);
	}
	
	static void display(int i, String s){
		JOptionPane.showMessageDialog(null, s, s, JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	static void display(int i, String title, String s){
		JOptionPane.showMessageDialog(null, s, title, JOptionPane.INFORMATION_MESSAGE);
	
	}
}//end class
