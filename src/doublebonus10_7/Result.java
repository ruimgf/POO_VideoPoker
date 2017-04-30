package doublebonus10_7;

import cards.HandCards;
 
/**
 * 
 * @author Alexandre
 * Abstract Class that returns the result of a play, it can be a bet, hold , credit , deal, advice  and statistics
 * or invalid the plays are to be implemented by subcalss's
 * @see Bet_Result @see Invalid_Result
 *
 */
public abstract class Result {
	
	int credits;
	HandCards hand;
	boolean[] holdcards;
	
	public boolean[] getHoldcards() {
		return holdcards;
	}	
	
	public Result(){
		
		this.credits = -1;
		this.hand = null;
	}
	
	
	// nao sei se gosto de tantos construtores
	public Result(int credits){
		this.credits = credits;
	}
	
	public Result(HandCards hand){
		this.hand = hand;
	}
	
	public Result(HandCards hand,int credits){
		this.credits = credits;
		this.hand = hand;
	}
	
	
}
