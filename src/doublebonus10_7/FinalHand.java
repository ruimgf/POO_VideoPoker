package doublebonus10_7;
/**
 * Class that represents the finalhand(after the hold) of the player after classification through
 *  the cardanalizer class
 * @author cande
 *
 */
public class FinalHand {
	
	String name;
	int multiplier;
	
	public FinalHand(String name, int multiplier){
		
		this.name = name;
		this.multiplier = multiplier;
		
	}
	
	public int payout(int credits_bet){
		
		return multiplier * credits_bet;
		
	}

	@Override
	public String toString() {
		return "wins with a" + name;
	}
	
	
	

}
