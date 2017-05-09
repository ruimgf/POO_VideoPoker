package videopoker;

/**
 * Class that represents the result of a bet play move in the videopoker machine , it extends the abstract class
 * Result.
 * @author Alexandre , Rui , Pedro
 * @see Result
 */
public class BetResult extends Result {
	
	/**
	 * The BetResult have also a bet field, that is the bet that the player had place.
	 */
	int bet;
	
	/**
	 * Constructor for a BetResult  it receives the credits of the player after the bet play move and also the bet that the player had placed.
	 * @param credits int that is the credits of the player after the bet
	 * @param bet int that is the bet that the player had placed
	 */
	public BetResult(int credits,int bet){
		
		super(credits);
		
		this.bet = bet;
		
		
	}
	
	/**
	 * Getter Method for the bet field
	 * @return int with the bet placed by the player
	 */
	public int getBet(){
		return this.bet;
	}
	
	@Override
	public String toString() {
		return "Player is betting " + this.bet ;
	}
	
	
	
}

