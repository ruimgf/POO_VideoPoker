package videopoker;

public class Credit_Result extends Result{
	
	Credit_Result(int credits){
		
		super(credits);
		
	}

	@Override
	public String toString() {
		return "Player's credit is " + this.credits;
	}

	
	
}
