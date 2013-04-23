
import java.io.Serializable;


@SuppressWarnings("serial")
public class CaseData implements Serializable {

	private String caseNumber;
	private String caseName;
	private String caseDescription;
	private String lawyer;
	private String paralegal;
	private String status;
	
	
	//Empty Constructor
	public CaseData()
	{
		this("","","","","","");
	}
	
	//6 Parameter Constructor
	public CaseData(String num, String name, String desc, String law, String para, String stat)
	{
		setCaseNumber(num);
		setCaseName(name);
		setCaseDescription(desc);
		setLawyer(law);
		setParalegal(para);
		setStatus(stat);
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

}

