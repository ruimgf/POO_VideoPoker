package cards;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class DebugDeck extends Deck {

	public DebugDeck(String filename) throws Throwable {
		// Open the file
				cards = new LinkedList<Card>() ;
				FileInputStream fstream;
				BufferedReader br =null;
				
				fstream = new FileInputStream(filename);
				br = new BufferedReader(new InputStreamReader(fstream));
				
				
				String strLine;
				//Read File Line By Line
				while ((strLine = br.readLine()) != null)   {
				  
					String[] parts = strLine.split(" ");
					for (int i = 0; i < parts.length; i++) {
						cards.add(new Card(parts[i]));
					} 
					
				}
				

				//Close the input stream
		
				br.close();
			
	}
	
	public Card get_card() throws EndOfDeck{
		try {
			return cards.pop();
		} catch (Exception e) {
			throw new EndOfDeck("No more cards on Deck");
		}
		
	}

}
