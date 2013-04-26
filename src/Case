

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Case Class
 * @author Chad Wolfe
 *
 */

public class Case extends Module {

  private String caseNumber;
	private String caseName;
	private String caseDescription;
	private String lawyer;
	private String paralegal;
	private String status;
	
	String modulecode = "CASE";
	Integer casenumber = 0;
	String casename = "";
	String casedesc = "";
	Date createdate = null;
	
	JTextField cf0, cf1, cf2, cf3, cf4;

	private ObjectOutputStream output;
	private ObjectInputStream input;


public Case(String num, String name, String desc, String law, String para, String stat)
{
		setCaseNumber(num);
		setCaseName(name);
		setCaseDescription(desc);
		setLawyer(law);
		setParalegal(para);
		setStatus(stat);
}

public Case()
{
	this("","","","","","");
}

public Case(String[] row)
{
	if(row.length==0)
		return;
	this.setCaseNumber(row[0]);
	this.setCaseName(row[1]);
	this.setCaseDescription(row[2]);
	this.setLawyer(row[3]);
	this.setParalegal(row[4]);
	this.setStatus(row[5]);

}

public void FormEdit(CaseList caselist)
{
	
	JPanel panel2 = new JPanel(new GridLayout(0,2));
	
	panel2.setPreferredSize(new Dimension(400,200));
	
	panel2.add(new JLabel("Case Add Form"));
	panel2.add(new JLabel (""));
	
	JTextField cf0 = new JTextField(this.getCaseNumber());
	panel2.add(new JLabel("Case Number: "));
	panel2.add(cf0);
	
	JTextField cf1 = new JTextField(this.getCaseName());
	panel2.add(new JLabel("Case Name: "));
	panel2.add(cf1);
	
	JTextField cf2 = new JTextField(this.getCaseDescription());
	panel2.add(new JLabel("Case Description: "));
	panel2.add(cf2);
	
	JTextField cf3 = new JTextField(this.getLawyer());
	panel2.add(new JLabel("Case Lawyer: "));
	panel2.add(cf3);
	
	JTextField cf4 = new JTextField(this.getParalegal());
	panel2.add(new JLabel("Case Paralegal: "));
	panel2.add(cf4);
	
	String[] items3 = {"Inactive", "Active"};
	JComboBox cf5 = new JComboBox(items3);
	panel2.add(new JLabel("Case Status: "));
	panel2.add(cf5);
	
	panel2.add(new JLabel (""));
	panel2.add(new JLabel (""));
	
	
	int result = JOptionPane.showConfirmDialog(null,  panel2, "Case Module", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	
	if (result == JOptionPane.OK_OPTION)
	{
		this.setCaseNumber(cf0.getText());
		this.setCaseName(cf1.getText());
		this.setCaseDescription(cf2.getText());
		this.setLawyer(cf3.getText());
		this.setParalegal(cf4.getText());
		if (cf5.getSelectedIndex()==0)
			this.setStatus("Inactive");
		else
			this.setStatus("Active");
		
		
		if(this.validateRecord())
		{
			this.setLoaded(true);
			this.saveRecord(caselist);
			OutputBox.display(0, "Case Module", "Case has been added");
		}
		else
		{
			this.setLoaded(false);
			this.FormEdit(caselist);
		}
	}
		else
		{
			this.initRecord();
			this.setLoaded(false);
		}
	
	}

	boolean validateRecord()
	{
		if(this.getCaseNumber().equals("") || this.getCaseName().equals("") || this.getCaseDescription().equals("") || this.getLawyer().equals("") || this.getParalegal().equals(""))
		{
			OutputBox.alert("Case Module",  "Please fill in all fields");
			return false;
		}
		else
			return true;
	}
	
	public void saveRecord(CaseList caselist)
	{
		caselist.addCaseList(this);
		caselist.saveFile();
	}


	public void OpenFile()
	{
		try
		{
			output = new ObjectOutputStream(new FileOutputStream ("cases.ser", true));
			
		}
		catch (IOException ioException)
		{
			System.err.println("File Error");
		}
		
	}
	
	public void AddRecord(List list)
	{
		try
		{
			output.writeObject(list);
			System.out.println("Record added\n");
		}
		catch (IOException ioException)
		{
			System.err.println("Write Error");
		}
	}
	
	
	public void CloseFile()
	{
		
		try
		{
			if (output != null)
				output.close();
		}
		catch(IOException ioexception)
		{
			System.err.println("File Error");
			System.exit(1);
		}
	
	}
	
//Setters and Getters

	public void setCaseNumber(String num)
	{
		caseNumber = num;
	}
	
	public String getCaseNumber()
	{
		return caseNumber;
	}
	
	public void setCaseName(String name)
	{
		caseName = name;	
	}
	
	public String getCaseName()
	{
		return caseName;
	}

	public void setCaseDescription(String desc)
	{
		caseDescription = desc;
	}

	public String getCaseDescription()
	{
		return caseDescription;
	}

	public void setLawyer(String law)
	{
		lawyer = law;
	}
	
	public String getLawyer()
	{
		return lawyer;
	}

	public void setParalegal(String para)
	{
		paralegal = para;
	}
	
	public String getParalegal()
	{
		return paralegal;
	}
	
	public void setStatus(String stat)
	{
		status = stat;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public String writeString(){
		return (this.getCaseNumber()+Constants.CSV_FIELD_SEPARATOR +
				this.getCaseName()+Constants.CSV_FIELD_SEPARATOR +
				this.getCaseDescription()+Constants.CSV_FIELD_SEPARATOR +
				this.getLawyer()+Constants.CSV_FIELD_SEPARATOR +
				this.getParalegal()+Constants.CSV_FIELD_SEPARATOR +
				this.getStatus()+Constants.CSV_FIELD_SEPARATOR );
	}
}
