package videopoker;

import cards.Card;
 
/**
 * 
 * @author Alexandre
 * Abstract Class that returns the result of a play, it can be a bet, hold , credit , deal, advice  and statistics
 *
 *
 */
public abstract class Result {
	
	int credits;
	Card[] hand;
		
	public Result(){
		
		this.credits = -1;
		this.hand = null;
	}
	
	
}
