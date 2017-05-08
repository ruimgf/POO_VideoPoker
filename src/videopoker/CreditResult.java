package videopoker;
/**
 * Class that represents the Result of the credit command of the videopoker it extends the Result Class
 * @author Alexandre
 *
 */
public class CreditResult extends Result{
	
	CreditResult(int credits){
		
		super(credits);
		
	}

	@Override
	public String toString() {
		return "Player's credit is " + this.credits;
	}

	
	
}
