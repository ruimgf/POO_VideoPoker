package doublebonus10_7;

/**
 * extension of the final hand to a royal flush, only neede because the payout method is diferent
 * @author cande
 *
 */
public class RoyalFlush extends FinalHand{
	
	/**
	 * Royal Flush constructor need no args, only call super("Royal Flush" , 250)
	 */
	public RoyalFlush(){
		
		super("Royal Flush", 250 );
		
	}
	
	/**
	 * Overiide the payout method because for 5 credits is diferrent form the one in supeer class
	 * @return payout of the bet
	 */
	public int payout(int credits_bet){
		
		if(credits_bet == 5){
			
			return 4000;
			
		}
		
		return super.payout(credits_bet);
		
		
	}
	
	
	
	

}
