package videopoker;

import cards.HandCards;

/**
 * TODO
 * @author Alexandre
 *
 */
public class Hold_Result extends ResultWithHand{
	
	protected String finalhand;
	
	/**
	 * TODO
	 * @param hand
	 * @param credits
	 * @param finalhand
	 */
	public Hold_Result(HandCards hand, int credits , String finalhand){
		
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
