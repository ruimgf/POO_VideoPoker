package videopoker;

import cards.HandCards;
/**
 * Class that extends the results for a hold result - a hold result has a final hand that the player hand with 
 * the information if it wins or not
 * @author Alexandre
 *
 */
public class Hold_Result extends Result{
	
	private String finalhand;
	
	public Hold_Result(HandCards player_cards, int credits , String finalhand){
		
		super(player_cards,credits);
		
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
