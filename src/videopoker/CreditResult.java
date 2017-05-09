package videopoker;
/**
 * Class that represents the Result of the credit command of the videopoker it extends the Result Class
 * @author Alexandre, Rui , Pedro
 * @see Result
 */
public class CreditResult extends Result{
	
	/**
	 * Constructor for the credit result only takes the credits that the player have
	 * @param credits credits that the player have at the time of the command credit
	 */
	public CreditResult(int credits){
		
		super(credits);
		
	}

	@Override
	public String toString() {
		return "Player's credit is " + this.credits;
	}

	
	
}
