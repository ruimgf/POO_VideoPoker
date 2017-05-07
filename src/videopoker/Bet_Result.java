package videopoker;

public class Bet_Result extends Result {
	
	int bet;
	
	public Bet_Result(int credits,int bet){
		
		super(credits);
		
		this.bet = bet;
		
		
	}

	@Override
	public String toString() {
		return "Player is betting " + this.bet ;
	}
	
	
	
}

