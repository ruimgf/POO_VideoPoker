package videopoker;


/**
 * Class that represent the Result of a advice play move in the videopoker machine
 * @author Alexandre, Rui , Pedro
 * @see Result
 */
public class AdviceResult extends Result {
	
	/**
	 * This Result type class have also a boolean field holdcards that is true in the 
	 * index's of the player hand that are to hold by the advice ruller
	 */
	boolean[] holdcards;

	/**
	 * Constructor for a Advice
	 * @param credits the credits that the player have after the advice move
	 * @param holdCards boolean array that is true in the indexs of cards to hold
	 */
	public AdviceResult(int credits, boolean[] holdCards) {
		super(credits);
		this.holdcards = holdCards;	
	}
	
	/**
	 * Getter method for the boolean array hold_cards - that is the cards that the adviser
	 * recomends to the player to hold
	 * @return boolean array that is true in the index's of the hand that are to hold and false
	 * in the index's that are to discard
	 */
	public boolean[] getHoldCards(){
		return this.holdcards;
	}

	
	public String toString() {
		String str; // left see case when is to discard everything
		str = "Player should hold cards " ;
		for (int i = 0; i < holdcards.length; i++) {
			if(holdcards[i]==true){
				str += " " + (i+1) ;
			}
		}
		if(str == "Player should hold cards "){
			return "Player should discard all cards";
		}else{
			return str;
		}
		
	}

}
