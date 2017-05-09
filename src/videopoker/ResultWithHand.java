package videopoker;

import cards.HandCards;

/**
 * ResultWithHand Abstract Class that extends the Class Result, this class had been made to
 * extend the result for class's that have also a hand field.
 * @author Alexandre , Rui , Pedro
 *
 */
public abstract class ResultWithHand extends Result {
	
	/**
	 * The ResultWithHand class have also a HandCards field with the hand that the player have
	 * after the play
	 * @see HandCards
	 */
	protected HandCards hand;
	
	/**
	 * Constructor takes the credits of the player after the play move and the hand
	 * @param credits int that represents the credits of the player after the play move.
	 * @param hand HandCards object that is the hand of the player after the play move.
	 * @see HandCards
	 */
	public ResultWithHand(int credits, HandCards hand){
		
		super(credits);
		this.hand = hand;
		
	}
	
	/**
	 * Getter for the player hand after the play move
	 * @return HandCards object with the hand of the player
	 * @see HandCards
	 */
	public HandCards getHand(){
		
		return this.hand;
		
	}

}
