import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * Case List Class
 * @author Chad Wolfe
 *
 */
public class CaseList extends Module{

  DefaultTableModel caseList = null;
  JTable caseTable = null;
  
  	
	CaseList()
	{
		this.caseList = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
				
		caseList.addColumn("Case Number");
		caseList.addColumn("Case Name");
		caseList.addColumn("Case Description");
		caseList.addColumn("Client");
		caseList.addColumn("Case Lawyer");
		caseList.addColumn("Case Paralegal");
		caseList.addColumn("Case Status");
		
		caseTable = new JTable(caseList);
		caseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		readFile();
	}
	
	Case parseLine(String sline){
		String[] line = sline.split(Constants.CSV_FIELD_SEPARATOR);
		//return new Case(line[0], line[1], line[2], line[3], line[4], line[5], line[6]);		
		return new Case(getArrayIndex(line, 0), getArrayIndex(line, 1), getArrayIndex(line, 2), 
				getArrayIndex(line, 3), getArrayIndex(line, 4), getArrayIndex(line, 5), getArrayIndex(line, 6));
	}
	
	void addCaseList(Case cs){
		String[] row = { cs.getCaseNumber(), cs.getCaseName(), cs.getCaseDescription(), cs.getClientName(), cs.getLawyer(), cs.getParalegal(), cs.getStatus() };
		caseList.addRow(row);
	}
	
	void updateCaseListRow(Case cs, int row)
	{
		caseList.setValueAt(cs.getCaseNumber(),  row,  0);
		caseList.setValueAt(cs.getCaseName(),  row,  1);
		caseList.setValueAt(cs.getCaseDescription(),  row,  2);
		caseList.setValueAt(cs.getClientName(),  row,  3);
		caseList.setValueAt(cs.getLawyer(),  row,  4);
		caseList.setValueAt(cs.getParalegal(),  row,  5);
		caseList.setValueAt(cs.getStatus(),  row,  6);
	}
	
	
	/**
	 * Display the case list
	 */
	void getCaseList(JPanel panel, final ContactList contactlist){
		
		// Add the scroll pane to this panel.
		panel.removeAll();

			
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		caseTable.setPreferredScrollableViewportSize(panel
				.getPreferredSize());
		caseTable.setFillsViewportHeight(true);
		
		

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(caseTable);
		panel.add(scrollPane);

		JPanel panelb = new JPanel();
		// Update Button
		JButton updateBtn = new JButton("Update");
		// updateBtn.setPreferredSize(new Dimension(200,30));
		panelb.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Update
				
				int selected = caseTable.getSelectedRow();
				Case x = getRowCase(selected);
				x.FormEdit(new CaseList(), contactlist, false);
				updateCaseListRow(x, selected);
				saveFile();
			}
		});

		// Delete Button
		JButton deleteBtn = new JButton("Delete");
		// deleteBtn.setPreferredSize(new Dimension(30,30));
		panelb.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this case?",
						"Delete Contact", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					// Delete and update
					caseList.removeRow(caseTable.getSelectedRow());
					saveFile();
				}
			}
		});

		panel.add(panelb);

	}// end method

	
	
	/**
	 * Reads the file
	 */
	void readFile()
	{
		
		//Populate list
		String line = "";
				
		try{
			BufferedReader reader = new BufferedReader(new FileReader("cases.txt"));
			try {
				
				while ((line = reader.readLine()) != null) {
					
					Case c = this.parseLine(line);
					addCaseList(c);
				}
				
			}catch (IOException e) {
				
				System.err.println("Error: " + e);
				
			}finally{
				reader.close();
			}

		}catch (FileNotFoundException e1) {
			
			 //Initialize data file if not exists
			 this.initModuleDataFile("cases.txt");
			 
		}catch (IOException e3) {
			
			System.out.println(Constants.GEN_ERR_CANNOT_OPEN_FILE);
			
		}
	}
	
	
	/**
	 * Saves Contact List to File
	 */
	void saveFile(){
		 try{
				FileOutputStream fos = new FileOutputStream("cases.txt", false);
				PrintWriter dos = new PrintWriter(fos);
				
				for(int i=0; i<caseList.getRowCount(); i++)
			    {
					Case cs = getRowCase(i);
					dos.println((cs.writeString()));					
			    }				
				
				dos.close();
				fos.close();

			}
			catch (IOException e) {
				System.out.println("File Error");
			}
	}
	
	
	/**
	 * Returns contact from List
	 * @param rowIndex
	 * @return
	 */
	public String[] getRowData(int rowIndex)
    {
        //test the index
        if (caseList.getRowCount()==0 || rowIndex < 0)
        {
            return null;
        }
        
        ArrayList < String >  data = new ArrayList < String > ();
        for (int col = 0; col  <  caseList.getColumnCount(); col++)
        {
            data.add((String) caseList.getValueAt(rowIndex, col));
        }
        
        String[] retVal = new String[data.size()];
        for (int i = 0; i  <  retVal.length; i++)
        {
            retVal[i] = data.get(i);
        }
        return retVal;
    }

	/**
	 * Builds contact object from row
	 * @param rowIndex
	 * @return
	 */
	public Case getRowCase(int rowIndex){
		
		return new Case(getRowData(rowIndex));
		
	}
	

	
	
}
		
