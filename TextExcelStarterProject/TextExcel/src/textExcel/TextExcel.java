package textExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Spreadsheet thing = new Spreadsheet();
	    boolean commandRunning = true;
	    while (commandRunning){
	    	String i  = sc.nextLine();
	    	if (i.equals("quit")){
	    		commandRunning = false;
	    	}
	    	thing.processCommand(i);
	    	//FIXME CHANGE THE NAME OF THING
	    }
	    
	}
}