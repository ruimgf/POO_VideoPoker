package videopoker;

import cards.HandCards;

/**
 * This Interface Represents the basic methods that a VideoPokerVariation Class has to give
 * @author Alexandre, Rui, Pedro
 *
 */
public interface VideoPokerVariation {
	
	/**
	 * given a hand and a bet this method gives the payout of that hand based in the variation that we implement
	 * @param hand receive a HandCards object that represents a hand
	 * @param bet the credits that had been placed in the bet
	 * @return int with the payout credits
	 */
	public int getPayout(HandCards hand,int bet);
	/**
	 * This method provides a text representation (string) of the hand 
	 * @param hand receive a HandCards object that represents a hand
	 * @return a string with the name of the hand
	 */
	public String evaluateHandName(HandCards hand);
	/**
	 * This method provides advice about how the player should play given a specific hand, this 
	 * have to be provided because the advice changes with the payouts of the variation
	 * @param hand receive a HandCards object that represents a hand
	 * @return a boolean array with that is true in the index's of the cards  that the play should hold
	 */
	public boolean[] evaluateHandAdvice(HandCards hand);
	
}
