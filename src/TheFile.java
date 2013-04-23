import java.io.File;
import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * The File Class 
 * General class to handle file actions such as write, read and much more.
 * 
 * @Legal Case Management System
 * Florida State University
 * @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos
 * @version 1.0.0
 * @since 2013-03-01
*/
public class TheFile{

	/**
	 * File property
	 */
	File f;
	
	/**
	 * Constructor
	 */
	TheFile(){
		f = null;
	}//End method
	
	/**
	 * Constructor
	 * @param f
	 */
	TheFile(String filename){
		this.f = new File(filename);
	}//End method
	
	/**
	 * File Selection
	 * @param required
	 * @param value
	 */
	void selectFile(boolean required, String value){
		
				
		JFileChooser choose = new JFileChooser(value);
		int status = choose.showOpenDialog(null);
		if(status == JFileChooser.APPROVE_OPTION){
			f = choose.getSelectedFile();
			if(required && !exists()){
				OutputBox.alert(status, "Invalid File, Please Try Again.");
				selectFile(true, value);
			}
		}else{
			OutputBox.alert(status, "No file selected.");		
		}
	
	}//End method
	
	

	/**
	 * File Name
	 * @return
	 */
	String getName()
	{
		
		return f.getName();			
	}//End method
	
	/**
	 * Return Path
	 * @return
	 */
	String getPath(){
		return f.getAbsolutePath();
	}//End method
	
	/**
	 * Return Size
	 * @return
	 */
	long getFileSize()
	{
		return f.length();
		
	}//End method
	
	/**
	 * File Exists?
	 * @return
	 */
	boolean exists(){
		try{
			return f.exists();
		}catch(NullPointerException e){
			return false;
		}

	}//End method
	
	/**
	 * Return file
	 * @return
	 */
	File getFile(){
		return f;
	}//End method
	
	
	/**
	 * Returns file information as string
	 */
	String fileInfo(){
		String info="";
		info +="File Name: " + this.getName();
		info +="\nFile path: " + this.getPath();
		info +="\nFile size: " + this.getFileSize();
		
		return info; 
		
	}//end method
	
	
	/**
	 * File Contents
	 */
	String getContent(){
		String s = "", text ="";
		try
		{
			BufferedReader bf = new BufferedReader(new FileReader(f));
			while((s = bf.readLine()) != null){
				text += s+"\n";
			}
			bf.close();
		}catch(IOException e){
			OutputBox.display(0, e.toString());
		}
		
		return text;
	}//End method
	
	/**
	 * Outputs the current file
	 */
	void showFile(){
		OutputBox.display(0, getContent());
	}//End method
	
	/**
	 * Initialize the current file
	 *
	 */
	void init(){
		
		f = null;
	}//End method
	
	/**
	 * Changes the current file
	 * @param file
	 */
	void setFile(String file){
		f = new File(file);
	}//End method
	
	/**
	 * File copy
	 * @param from
	 * @param to
	 */
	void copyFile(File from, String to){
		
		try{
			DataInputStream inSrc = new DataInputStream(new FileInputStream(from));
			DataOutputStream outSrc = new DataOutputStream(new FileOutputStream(to));
			try{
				while(true){
					byte data = inSrc.readByte();
					outSrc.writeByte(data);
				}	
		
			}catch(IOException e){
			}finally{
				inSrc.close();
				outSrc.close();
			}
		}catch(IOException e){
			
			e.printStackTrace();
		}
		
	}//End method
	
	
	/**
	 * Simgle key file search
	 * @param key
	 * @return
	 */
	String searchFile(String key){
		String s, result = "";
		int i=0;
		try
		{
			BufferedReader bf = new BufferedReader(new FileReader(f));
			
			while((s = bf.readLine()) != null){
				i++;
				if(s.toLowerCase().contains(key.toLowerCase())){
					result += i+": "+s.toLowerCase().replace(key.toLowerCase(), key.toUpperCase())+"\n\n";
				}
			}
			bf.close();
		}catch(IOException e){
			OutputBox.display(0, e.toString());
		}
		if(result.length()==0) result = "Key: "+key+" Not Found;";
		return result;
	}//End method
	
	
	/**
	 * Builds token Report
	 * @return
	 */
	String tokenReport(boolean saveReport){
		
		
		TheToken t = new TheToken(f+"");
		t.parse();
		String report = t.report();
		if(saveReport){
			writeFile(this.getContent()+"\n\n"+report);
		}
		return report;

	}//End method
	
	
	/**
	 * Writes to a file
	 * @param contents
	 */
	void writeFile(String contents){
		try{
			
			FileWriter fw = new FileWriter(this.getName());
			try{
				fw.write(contents);
			}catch(IOException e){
			}finally{
				fw.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}//End method

	/**
	 * Writes to a file
	 * @param contents
	 */
	void appendFile(String contents){
		
		try{
			
			FileWriter fw = new FileWriter(this.getPath());
			try{
				fw.append(contents);
			}catch(IOException e){
			}finally{
				fw.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}//End method
	
}//end class


