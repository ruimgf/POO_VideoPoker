package cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> cards;
	
	public Deck() {
		for (Suit S : Suit.values()){
			for (CardValue CV : CardValue.values()){
				cards.add(new Card(S,CV));
				
			}
		}
		Collections.shuffle(cards);
	}
	
	public Deck(String cardFile) {
		// TODO Auto-generated constructor stub
	}
	
	

}
