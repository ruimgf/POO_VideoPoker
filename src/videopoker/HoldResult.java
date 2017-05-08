package videopoker;

import cards.HandCards;

/**
 * Class that represents the Result of the hold playmove
 * @author Alexandre , Rui , Pedro
 * @see Result @see ResultWithHand
 */
public class HoldResult extends ResultWithHand{
	
	/**
	 * This class have also a string field with the final hand name that the player receives after the hold
	 */
	protected String finalhand;
	
	/**
	 * Constructor receives the hand of the player after the hold, the credit and the finalhand name of the hand
	 * @param hand HandCards object that represente the hand of the player @see HandCards
	 * @param credits int that is the credits of the player after the hold
	 * @param finalhand String the is the name of the final hand of the player
	 */
	public HoldResult(HandCards hand, int credits , String finalhand){
		
		super(credits,hand);
		
		this.finalhand = finalhand;
		
	}

	@Override
	public String toString() {
			
		String aux = new String();
		
		if(this.finalhand == "Other"){
			aux = "Player loses and his credit is " + this.credits;
		}else{
			aux = "Player wins with a " + this.finalhand + " and his credit is " + this.credits;
		}
		
		
		return "Player's hand:" + this.hand + "\n" + aux;
	}
	
	
	

}
