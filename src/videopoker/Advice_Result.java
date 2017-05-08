package videopoker;


/**
TODO
 * @author Alexandre
 *
 */
public class Advice_Result extends Result {
	
	protected boolean[] holdcards;

	/**
	 * TODO
	 * @param credits
	 * @param holdCards
	 */
	public Advice_Result(int credits, boolean[] holdCards) {
		super(credits);
		this.holdcards = holdCards;	
	}
	
	/**
	 * TODO
	 * @return
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
