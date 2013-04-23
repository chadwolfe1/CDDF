import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StreamTokenizer;
import java.io.IOException;
/**
 *  Token Class
 *  
 * Legal Case Management System
 * Florida State University
 * @author Francisco Santana, Chad Wolfe, Daniel Weston, Dean Burgos
 * @version 1
 * @since 2013-03-01
 */
public class TheToken {
	
	String file;
	long fileNoLines,fileNoWordTokens, fileNoNumberTokens, fileNochars;
	
	
	TheToken(String file){
		this.file = file;
		this.fileNoLines = 0;
		this.fileNoWordTokens = 0;
		this.fileNoNumberTokens = 0;
		this.fileNochars = 0;
	}//End method
	
	/**
	 * File Parsing
	 */
	void parse(){
		
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StreamTokenizer token = new StreamTokenizer(br);
			token.eolIsSignificant(true);	
			while(token.nextToken() != StreamTokenizer.TT_EOF){
				switch(token.ttype){
					case StreamTokenizer.TT_WORD:
						fileNochars +=token.sval.length();
				case StreamTokenizer.TT_NUMBER:
						this.fileNoNumberTokens++;
						fileNochars += String.valueOf(token.nval).length();						
					case StreamTokenizer.TT_EOL:
						this.fileNoLines++;
						break;
					default:
						break;
				}
			}
		
			br.close();
		}catch(IOException e){
			
		}
		
	}//end method
	
	
	/**
	 * Token Report
	 * @return
	 */
	String report(){
		
		String report = "";
		report += "File:"+this.file+" has ";
		report += this.fileNoLines+ " Lines, ";
		report += this.fileNoWordTokens + " words, ";
		report += this.fileNoNumberTokens + " numbers, ";
		report += this.fileNochars + " characters";
		return report;
		
	}//end method
}//end class
