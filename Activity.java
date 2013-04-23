// Activity.java
// Dean Burgos

import java.util.Date;

public class Activity {

	// Class parameters
	
	String actType, 		// Activity Type such as Phone Call, Meeting, Delivery
	actDesc, 				// Activity Description, short text describing activity like a small note in an agenda
	actCase,				// Title of case associated with Activity can be none
	actAttorney;			// Attorney assigned to Activity
	Date startDate, endDate;	// Beginning and ending dates associated with activity
	Boolean actStatus = false;	// Toggle activity status true if Activity has been completed
	
	// Default Constructor
	Activity(){
		actType = "Act Type";
		actDesc = "Description";
		actCase = "Case";
		actAttorney = "Attorney";
		startDate = new Date();
		endDate = new Date();
		actStatus = false;   
	}
	
	Activity( String t, String d, String c, String a, Date startD, Date endD, Boolean aS ){
		actType = t;
		actDesc = d;
		actCase = c;
		actAttorney = a;
		startDate = startD;
		endDate = endD;
		actStatus = aS;   
	}
	
	// Setters
	void setActType(String type){
		actType = type;
	}
	void setActDesc(String desc){
		actDesc = desc;
	}
	void setActCase(String desc){
		actDesc = desc;
	}
	void setActAttorney(String actAtt){
		actAttorney = actAtt;
	}
	void setStartDate(Date actStart){
		startDate = actStart;
	}
	void setEndDate(Date actEnd){
		endDate = actEnd;
	}
	void setStatus(Boolean status){
		actStatus = status;
	}
	
	// Getters
	String getActType(){
		return actType;
	}
	String getActDesc(){
		return actDesc;
		
	}
	String getActCase(){
		return actCase;
	}
	String getActAttorney(){
		return actAttorney;
	}
	Date getStartDate(){
		return startDate;
	}
	Date getEndDate(){
		return endDate;
	}
	Boolean getStatus(){
		return actStatus;
	}
	
	// Activity Functions/ potential ActivityManager class Functions
	void actForm(){
		
		// Button to create Activities			"Create New Activity"
		
		// Button To list All Activities			"List All Activities"
		
		// Button to list Uncompleted Activities	"List Activities in progress"
		
		// Button to list Completed Activities 		"List Completed Activity History"
		
		
	}
	
	// Display Java window to create a new Activity
	void actCreateForm(){
		
	}
	
	// Display a list of all activities
	void listAllActs(){	
		//connect to database
			// retrieve all activities from db
				// store into an array of activities
					// orderByDate()
		// display activity list
	}
	
	
	// Delete Activity
	void deleteActivity(Activity actDel){
		// activity chosen by mouse click
			// db should still be open
				// confirm deletion
				// search database for activity
				// remove db entry for activity
	}
	
	public String toString(){
		return ("Act Type: "	+ actType 		+ "\n" +
				"Description: " + actDesc 		+ "\n" +
				"Case: " 		+ actCase 		+ "\n" +
				"Attorney: " 	+ actAttorney 	+ "\n" +
				"Start Date: " 	+ startDate		+ "\n" +
				"End Date: " 	+ endDate		+ "\n" +
				"Status: " 		+ actStatus		+ "\n");
	}
	
	public String writeString(){
		return (actType 		+ "\t" +
				actDesc 		+ "\t" +
				actCase 		+ "\t" +
				actAttorney 	+ "\t" +
				startDate		+ "\t" +
				endDate			+ "\t" +
				actStatus		+ "\t" );
	}
	
}