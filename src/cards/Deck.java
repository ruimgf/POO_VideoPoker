package cards;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {

	private LinkedList<Card> cards;
	
	public Card get_card(){
		
		Card aux = cards.pop();
		cards.addLast(aux);
		
		return aux;
		
	}
	

	
	public Deck() {
		cards = new LinkedList<Card>() ;
		for (Suit S : Suit.values()){
			for (CardValue CV : CardValue.values()){
				cards.add(new Card(S,CV));
			}
		}
		Collections.shuffle(cards);
	}
	
	public Deck(String cardFile) {
		// TODO insert here code to read file
	}

	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	@Override
	public String toString() {
		return "Deck " + cards;
	}
	
	public static void main(String[] args) {
		Deck mydeck = new Deck();
		System.out.println(mydeck);
	}
	
	

}
