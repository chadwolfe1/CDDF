import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Case List Class
 * @author Chad Wolfe
 *
 */
public class CaseList extends Module{

  DefaultTableModel caseList = null;
	
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
		caseList.addColumn("Case Lawyer");
		caseList.addColumn("Case Paralegal");
		caseList.addColumn("Case Status");
		
		readFile();
	}
	
	Case parseLine(String sline){
		String[] line = sline.split(Constants.CSV_FIELD_SEPARATOR);
		return new Case(line[0], line[1], line[2], line[3], line[4], line[5]);		
	}
	
	void addCaseList(Case cs){
		String[] row = { cs.getCaseNumber(), cs.getCaseName(), cs.getCaseDescription(), cs.getLawyer(), cs.getParalegal(), cs.getStatus() };
		caseList.addRow(row);
	}
	
	/**
	 * Display the contact list
	 */
	void getCaseList(JPanel panel){
		
		JTable table = new JTable(caseList);
		
		table.setPreferredScrollableViewportSize(panel.getPreferredSize());
		table.setFillsViewportHeight(true);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Add the scroll pane to this panel.
		panel.removeAll();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		
		
	}//end method
	
	
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
	
	
