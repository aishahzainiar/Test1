
package readcsvfile;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class ReadCsvFile {

	public static void main(String[] args) throws IOException 
	{

		String csvFileName = "A.csv"; 
		String txtFileName = "B.txt";
		String binFileName = "C.bin";

		BufferedReader readCSV = null; 
		BufferedWriter writeTXT = null;
		BufferedWriter writeBIN = null;

		String words;
		String separator = ",";

		readCSV = new BufferedReader(new FileReader(csvFileName));
		writeTXT = new BufferedWriter(new FileWriter(txtFileName));
		writeBIN = new BufferedWriter(new FileWriter(binFileName));		

		Desktop desktop = Desktop.getDesktop();
              
                File opencsvFile = new File(csvFileName);	
                if(opencsvFile.exists()) desktop.open(opencsvFile); // open csvFileName
                
                File opentxtFile = new File(txtFileName);	
                if(opentxtFile.exists()) desktop.open(opentxtFile); // open txtFileName
                
                File openbinFile = new File(binFileName);	
                if(openbinFile.exists()) desktop.open(openbinFile); // open binFileName
                
                
		try 
		{
			int rowCount = 0;

			Stack <String> linebyline = new Stack<>();	

			while((words = readCSV.readLine()) != null)
			{
				linebyline.push(words);
				rowCount++;
			}	

			for(int i = 0; i < rowCount; i++)
			{
				String [] word = linebyline.pop().split(separator);

				for(int j = rowCount - 1; j >= 0; j--)
				{
					writeTXT.write(word[j]); //write word as text to txt file

					String wordInBinary = "";
					char [] convBinary = word[j].toCharArray();
					
					for(int k = 0; k < convBinary.length; k++)
					{
                                                wordInBinary = wordInBinary + Integer.toBinaryString(convBinary[k]) + " ";
					}
					
					writeBIN.write(wordInBinary);
					
					if(j != 0)
					{
						writeTXT.write(" ");
						writeBIN.write(" ");
						
					}
					else
					{
						writeTXT.newLine();
						writeBIN.newLine();
					}
				}
			}	
		} 

		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		catch (IOException e) 
		{
			e.printStackTrace();
		}

		finally 
		{
			readCSV.close();
			writeTXT.close();
			writeBIN.close();
		}	
	}

}

