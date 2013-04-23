import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
* MyJFrame
* 
* Legal Case Management System
* Florida State University
* @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos 
* @version 1
* @since 2013-03-01
*/
public  class MyJFrame extends JFrame 
{
	static final long serialVersionUID = 0;
	JMenuBar mb;
	JMenu m;
	JMenuItem mi;
	Action ac;
	
	/**
	 * Constructor
	 * @param title
	 */
	public MyJFrame (String title){
		super(title);
		mb = new JMenuBar();
		setJMenuBar(mb);
		ac = new Action();
		buildMenu();
	}//end method

	/**
	 * Constructor
	 * @param title
	 * @param single_frame
	 */
	public MyJFrame(String title, Boolean single_frame){
		super(title);
	}
	
	/**
	 * Menu builder
	 */
	void buildMenu(){
		
		for(int i = 0; i < Constants.menus.length; i++){
			m = new JMenu(Constants.menus[i]);
			switch(i){
				case 0:
					//file menu+3
					for(int j = 0; j < Constants.filemenu.length; j++){
						if(Constants.filemenu[j].equals("-")){
							m.addSeparator();
						}else{
							m.add(mi = new JMenuItem(Constants.filemenu[j]));
							mi.addActionListener(ac);
						}
					}
					break;
				case 1:
					//tool menu
					for(int j = 0; j < Constants.tool.length; j++){
						if(Constants.tool[j].equals("-")){
							m.addSeparator();
						}else if(j==3){
							
							JMenu t = new JMenu(Constants.tool[j]);
							for(int k = 0; k < Constants.edit.length; k++){
								if(Constants.edit[k].equals("-")){
									t.addSeparator();
								}else{
									t.add(mi = new JMenuItem(Constants.edit[k]));
									mi.addActionListener(ac);
								}
							}
							m.add(t);
							
						}else{
							m.add(mi = new JMenuItem(Constants.tool[j]));
							mi.addActionListener(ac);
						}
					}
					break;
				case 2:
					//help window
					//m.add(mi = new JMenuItem(Constants.menus[i]));
					//mi.addActionListener(ac);
					
					//file menu+3
					for(int j = 0; j < Constants.helpmenu.length; j++){
						if(Constants.helpmenu[j].equals("-")){
							m.addSeparator();
						}else{
							m.add(mi = new JMenuItem(Constants.helpmenu[j]));
							mi.addActionListener(ac);
						}
					}
					
					
					break;
					
			}//end switch
			
			mb.add(m);
			
		}//end for i
		
	}//end method
	
	
}//end class
