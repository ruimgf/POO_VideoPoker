package videopoker;

import cards.HandCards;

/**
 * TODO
 * @author Alexandre
 *
 */
public class Deal_Result extends ResultWithHand {

	/**
	 * TODO
	 * @param hand
	 * @param credits
	 */
	Deal_Result(HandCards hand,int credits){
		
		super(credits,hand);
		
	}

	
	@Override
	public String toString() {
		return "Player's hand: " + this.hand;
	}
	

}
