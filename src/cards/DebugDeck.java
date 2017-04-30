package cards;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class DebugDeck extends Deck {

	public DebugDeck(String filename) {
		// Open the file
				cards = new LinkedList<Card>() ;
				FileInputStream fstream;
				BufferedReader br =null;
				try {
					fstream = new FileInputStream(filename);
					 br = new BufferedReader(new InputStreamReader(fstream));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(-1);
				}
				
				

				String strLine;
				//Read File Line By Line
				try {
					while ((strLine = br.readLine()) != null)   {
					  
						String[] parts = strLine.split(" ");
						for (int i = 0; i < parts.length; i++) {
							cards.add(new Card(parts[i]));
							
						} 
						
						
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Close the input stream
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		// TODO Auto-generated constructor stub
	}
	
	public Card get_card(){
		return cards.pop();
	}

}
