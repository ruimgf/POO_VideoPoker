package videopoker;

/**
 * TODO
 * @author Alexandre
 *
 */
public class BetResult extends Result {
	
	int bet;
	
	/**
	 * TODO
	 * @param credits
	 * @param bet
	 */
	public BetResult(int credits,int bet){
		
		super(credits);
		
		this.bet = bet;
		
		
	}
	
	/**
	 * TODO
	 * @return
	 */
	public int getBet(){
		return this.bet;
	}
	
	@Override
	public String toString() {
		return "Player is betting " + this.bet ;
	}
	
	
	
}

