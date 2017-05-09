package cards;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Class that represents a Card Deck with Cards of all possible combinations between {@link CardValue} and {@link Suit}
 * @author rui
 *
 */

public class Deck {
	/**
	 * List of cards present in deck
	 */
	protected LinkedList<Card> cards;
	
	
	/**
	 * Get first Card of Deck
	 * @return First card
	 * @throws EndOfDeck if deck is empty
	 */
	public Card get_card() throws EndOfDeck{
		try {
			Card aux = cards.pop();
			cards.addLast(aux);
			return aux;
		} catch (Exception e) {
			throw new EndOfDeck("No more cards on Deck");
		}
	
		
	}
	/**
	 * Create a new Deck
	 * 
	 */
	public Deck() {
		cards = new LinkedList<Card>() ;
		for (Suit S : Suit.values()){
			for (CardValue CV : CardValue.values()){
				cards.add(new Card(S,CV));
			}
		}
		Collections.shuffle(cards);
	}
	
	/**
	 * Shuffle Deck
	 */
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
