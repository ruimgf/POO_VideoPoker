package videopoker;

import cards.HandCards;

/**
 * TODO
 * @author Alexandre
 *
 */
public class DealResult extends ResultWithHand {

	/**
	 * TODO
	 * @param hand
	 * @param credits
	 */
	DealResult(HandCards hand,int credits){
		
		super(credits,hand);
		
	}

	
	@Override
	public String toString() {
		return "Player's hand: " + this.hand;
	}
	

}
