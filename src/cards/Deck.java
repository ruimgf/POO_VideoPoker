package cards;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {

	protected LinkedList<Card> cards;
	
	public Card get_card() throws EndOfDeck{
		try {
			Card aux = cards.pop();
			cards.addLast(aux);
			return aux;
		} catch (Exception e) {
			throw new EndOfDeck("No more cards on Deck");
		}
	
		
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
