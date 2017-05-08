package videopoker;

import cards.HandCards;

/**
 * TODO
 * @author Alexandre
 *
 */
public abstract class ResultWithHand extends Result {
	
	protected HandCards hand;
	
	/**
	 * TODO
	 * @param credits
	 * @param hand
	 */
	public ResultWithHand(int credits, HandCards hand){
		
		super(credits);
		this.hand = hand;
		
	}
	
	/**
	 * TODO
	 * @return
	 */
	public HandCards getHand(){
		
		return this.hand;
		
	}

}
