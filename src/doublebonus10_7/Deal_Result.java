package doublebonus10_7;

import cards.HandCards;

/**
 * Class that implements the extension of the result to the deal results
 * @author Alexandre
 *
 */
public class Deal_Result extends Result {
	
	/**
	 * Constructor only needs the hand of the player after the deal and the credits that the player have
	 * @param hand player hand after the deal of the cards
	 * @param credits of the player
	 */
	Deal_Result(HandCards hand,int credits){
		
		super(hand,credits);
		
	}

	
	@Override
	public String toString() {
		return "player's hand: " + this.hand;
	}
	

}
