import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();

	ContactList cl;
	
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public Window() {

		super(Constants.MAIN_WIN_TITLE);
		
		panel.setBackground(Color.white);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		// set dimension of the window to the screen size
		//setPreferredSize(getToolkit().getScreenSize());
		setPreferredSize(new Dimension(720,420));
		
		//Set panel size for other to work
		panel.setPreferredSize(new Dimension(700,400));
		
		//Contact module list
		cl = new ContactList();
		
		//Case module list
		caselist = new CaseList();
		
		JTextArea welcome = new JTextArea("Welcome to Case Management System\nCDDF Enterprises");
		welcome.setBackground(Color.white);
		panel.add(welcome, BorderLayout.CENTER);
		changePanel(panel);
		
		
		// Setting Panes and menu
		initMenu();
		
				
		// Sets a little icon on top
		setIconImage(new ImageIcon("icon.png").getImage());

		this.pack();

		addWindowListener(new WindowListener() {

			// What to do when window is closing
			public void windowClosing(WindowEvent e) {
				appExit();
			}

			public void windowActivated(WindowEvent e) {
				System.out.println("Activating Window");
			}

			public void windowClosed(WindowEvent e) {
				// closing window
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
				System.out.println("Loading...");
				/*
				 * I think here we can add the login control since this is
				 * opened at the beginning first test if property is enables
				 * if(valid_user){ do nothing will start the program else
				 * request login if login_ok set session and continue else
				 * System.out.println("Invalid user name or password");
				 * System.exit(0); }
				 */
			}
		});

	}

	private class MenuAction implements ActionListener {

		private JPanel panel;

		private MenuAction(JPanel p) {
			this.setPanel(p);
		}

		void setPanel(JPanel p){
			this.panel = p;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Contact actions
			if(e.getActionCommand().equalsIgnoreCase("List Contacts")){
					
				
				cl.getDisplayList(panel);
				
			}else if(e.getActionCommand().equalsIgnoreCase("Create Contact")){
			
				Contact c = new Contact();
				//c.formEdit(panel);
				c.formEdit(cl);
				
			}else if(e.getActionCommand().equalsIgnoreCase("Create Case")){
				Case cs = new Case();
				cs.FormEdit(caselist);

			}else if(e.getActionCommand().equalsIgnoreCase("List Cases")){
				
				caselist.getCaseList(panel);
				
			//etc
			}else if(e.getActionCommand().equalsIgnoreCase("Exit")){
				appExit();
			}else{
				//do nothing
				return;
			}
			
			changePanel(panel);
		}
	}

	
	
	/**
	 * All menus here
	 */
	private void initMenu() {

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Main");
		menubar.add(menu);
		
		//Main Menus
		JMenuItem menuItem1 = new JMenuItem("List Contacts");
		menuItem1.addActionListener(new MenuAction(panel));
		menu.add(menuItem1);
		
		JMenuItem menuItem2 = new JMenuItem("Activities");
		menuItem2.addActionListener(new MenuAction(panel));
		menu.add(menuItem2);
		
		JMenuItem menuItem3 = new JMenuItem("List Cases");
		menuItem3.addActionListener(new MenuAction(panel));
		menu.add(menuItem3);
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new MenuAction(panel));
		menu.addSeparator();
		menu.add(menuItemExit);
		
		
		//Contact menu specific
		JMenu contmenu = new JMenu("Contacts");
		menubar.add(contmenu);
		
		JMenuItem ContMenuItem1 = new JMenuItem("List Contacts");
		ContMenuItem1.addActionListener(new MenuAction(panel));
		contmenu.add(ContMenuItem1);
		
		//Contact Menus sub options
		JMenuItem ContmenuItem2 = new JMenuItem("Create Contact");
		ContmenuItem2.addActionListener(new MenuAction(panel));
		contmenu.add(ContmenuItem2);
		
		//Case Menu
		JMenu casemenu = new JMenu("Cases");
		menubar.add(casemenu);
				
		JMenuItem CaseMenuItem1 = new JMenuItem("List Cases");
		CaseMenuItem1.addActionListener(new MenuAction(panel));
		casemenu.add(CaseMenuItem1);
		
		JMenuItem CaseMenuItem2 = new JMenuItem("Create Case");
		CaseMenuItem2.addActionListener(new MenuAction(panel));
		casemenu.add(CaseMenuItem2);

		
		
		setJMenuBar(menubar);


		
		
		
		

	}

	
	
	/**
	 * Method called in action to repaint screen
	 * @param panel
	 */
 	private void changePanel(JPanel panel) 
	{
		getContentPane().removeAll();
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().doLayout();
		update(getGraphics());
		pack();
	}

	/**
	 * Just end the application
	 */
	void appExit(){
		System.out.println("Have a nice day");
		System.exit(0);
	}
}
