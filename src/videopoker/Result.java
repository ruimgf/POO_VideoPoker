package videopoker;

/**
 * The Result class is an abstract class that represents the result(valid output) of a play in the videopoker machine.
 * @author Alexandre , Rui , Pedro
 * @see BetResult
 * @see CreditResult 
 * @see AdviceResult
 * @see HoldResult
 * @see StatisticsResult
 */
public abstract class Result {
	
	/**
	 * All Results in our implementation have a int field credits that represent the credits
	 * that the player have in the machine after that play.
	 */
	int credits;
		
	/**
	 * Constructor of the Class Result, all results in this implementation have the filed credits
	 * @param credits int that is the credits of the player in the machine after a play move.
	 */
	Result(int credits){
		this.credits = credits;
	}
	
	/**
	 * Getter method for the field credits
	 * @return a int that is the credits of the player after that play move.
	 */
	public int getCredits(){
		return credits;
	}
	
	 
}
