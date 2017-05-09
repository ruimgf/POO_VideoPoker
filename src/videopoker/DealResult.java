package videopoker;

import cards.HandCards;

/**
 * DealResult is a Class that extends ResultWithHand and represents the result of a
 * deal play move in the videopoker machine
 * @author Alexandre , Rui , Pedro
 * @see Result
 * @see ResultWithHand
 */
public class DealResult extends ResultWithHand {

	/**
	 * Constructor takes the HandCards object that represente the player hand after the deal play move 
	 * and the int credits that is the  credits that the player have after the deal.
	 * @param hand HandCards that the player have after the deal
	 * @param credits that the player have after the deal
	 * @see HandCards
	 */
	DealResult(HandCards hand,int credits){
		
		super(credits,hand);
		
	}

	
	@Override
	public String toString() {
		return "Player's hand: " + this.hand;
	}
	

}
