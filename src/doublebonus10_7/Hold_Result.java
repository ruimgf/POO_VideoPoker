package doublebonus10_7;

import cards.HandCards;

/**
 * Class that extends the results for a hold result - a hold result has a final hand that the player hand with 
 * the information if it wins or not
 * @author Alexandre
 *
 */
public class Hold_Result extends Result{
	
	FinalHand result_of_play;
	
	public Hold_Result(HandCards player_cards, int credits , FinalHand result_of_play){
		
		super(player_cards,credits);
		
		this.result_of_play = result_of_play;
		
	}

	@Override
	public String toString() {
		return "Player Hand:" + this.hand + "\n" + "Player" + result_of_play + " and is credit is " + this.credits;
	}
	
	
	

}
