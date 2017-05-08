package videopoker;

import cards.HandCards;

/**
 * TODO
 * @author Alexandre
 * @see Bet_Result @see Invalid_Result @see Credit_Result @see Advice_Result @see Hold_Result
 */
public abstract class Result {
	
	protected int credits;
		
	/**
	 * TODO
	 * @param credits
	 */
	Result(int credits){
		this.credits = credits;
	}
	
	/**
	 * TODO
	 * @return
	 */
	int getCredits(){
		return credits;
	}
	
	 
}
