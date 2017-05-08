package videopoker;

import cards.HandCards;

/**
 * TODO
 * @author Alexandre
 *
 */
public class HoldResult extends ResultWithHand{
	
	protected String finalhand;
	
	/**
	 * TODO
	 * @param hand
	 * @param credits
	 * @param finalhand
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
