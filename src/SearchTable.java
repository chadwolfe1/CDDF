/**
 * SearchTable Class
 * @author Dean Burgos
 *
 */
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class SearchTable {
	
	DefaultTableModel searchModel = new DefaultTableModel() {
		// Needed to make sure JTable cannot be edited in display
		// regular Jtable allows text in cells to be edited, this turns that option off

		@Override
		public boolean isCellEditable(int row, int column) {
			//all cells false
			return false;
		}
	};
	
	SearchTable(){
		JTable resultTable = new JTable(searchModel);
		searchModel.addColumn("Act Type");
		searchModel.addColumn("Description");
		searchModel.addColumn("Start Date");
		searchModel.addColumn("End Date");
		searchModel.addColumn("Case");
		searchModel.addColumn("Attorney");
		searchModel.addColumn("Status");
		JFrame frame = new JFrame();
		
		frame.setPreferredSize(new Dimension(700,400));
		frame.add( new JScrollPane( resultTable ));
		frame.pack();
		
		frame.setVisible( true ); 
	}

	void SearchForAtt(ActivityList a, String find){
		//find = "No Attorney";
		for(int row = 0; row <  a.activityList.getRowCount(); row++){
			
			if(find.equals(a.getRowActivity(row).getActAttorney())){
				searchModel.addRow(a.getRowData(row));
			}
			
		}
	}
	void SearchForCase(ActivityList a, String find){
		//find = "No Attorney";
		for(int row = 0; row <  a.activityList.getRowCount(); row++){
			
			if(find.equals(a.getRowActivity(row).getActCase())){
				searchModel.addRow(a.getRowData(row));
			}
			
		}
	}
	void SearchForStatus(ActivityList a, Boolean find){
		//find = "No Attorney";
		for(int row = 0; row <  a.activityList.getRowCount(); row++){
			
			if(find == a.getRowActivity(row).getStatus()){
				searchModel.addRow(a.getRowData(row));
			}
			
		}
	}
}

// End SearchTable.java
