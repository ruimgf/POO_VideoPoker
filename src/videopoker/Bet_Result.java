package videopoker;

/**
 * TODO
 * @author Alexandre
 *
 */
public class Bet_Result extends Result {
	
	int bet;
	
	/**
	 * TODO
	 * @param credits
	 * @param bet
	 */
	public Bet_Result(int credits,int bet){
		
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

