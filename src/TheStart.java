import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Application Main Class
 * 
 * Legal Case Management System Florida State University
 * 
 * @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos
 * @version 1
 * @since 2013-03-01
 * 
 */
public class TheStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Init main window container and set a background
		MyJFrame f = new MyJFrame(Constants.MAIN_WIN_TITLE);
		f.setBackground(Color.WHITE);

		// Setting toolkit of awt to deal with the resize and
		// other window properties
		Toolkit tk = f.getToolkit();
		Dimension size = tk.getScreenSize();

		// Add listener to the window so it will trigger action
		// when the something happens like click
		f.addWindowListener(new WindowListener() {

			// What to do when window is closing
			public void windowClosing(WindowEvent e) {
				end();// call method to end the app
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}
		});

		
		
		
		// Adding buttons
		MyJButton b = new MyJButton(f);
		b.add_buttons();
		
		//set dimension of the w
		Rectangle r = new Rectangle(Constants.X_POS, Constants.Y_POS,
				size.width / Constants.X_FACTOR, size.height
						/ Constants.Y_FACTOR);
		f.setBounds(r);

		if (!f.isVisible())
			f.setVisible(true);

	}

	/**
	 * Ending application
	 */
	public static void end() {

		// This is a simple pop up
		// OutputBox.display(0, "CDDF", "Window is closing");
		// End the application
		System.exit(0);
	}

}
