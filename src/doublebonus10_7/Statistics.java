package doublebonus10_7;

/**
 * Class that implements the statisctics of the game, that is , the number of hands of each wining hand and 
 * the tools to keep tracking of player wins
 * @author Alexandre
 *
 */
public class Statistics {
	/* position 0 representes jacks --- 9 represents 0ther hands*/
	private int[] hands_stats = new int[10];
	private int initial_credit;
	private int actual_credit;
	private int number_deals;
	/**
	 * constructor of statistics
	 * @param initial_credit the initial credit of the player
	 */
	Statistics(int initial_credit){
		
		this.initial_credit=initial_credit;
	}
	/**
	 * method that increment the number of Jacks or Better hands
	 */
	void addJacks(){
		this.hands_stats[0] = this.hands_stats[0] + 1;
		
	}
	
	/**
	 * method that increment the number of two pair hands
	 */
	void addTwoPair(){
		this.hands_stats[1] = this.hands_stats[1] + 1;
		
	}
	
	void addThree(){
		this.hands_stats[2] = this.hands_stats[2] + 1;
		
	}
	
	void addStraight(){
		this.hands_stats[3] = this.hands_stats[3] + 1;
		
	}
	
	void addFlush(){
		this.hands_stats[4] = this.hands_stats[4] + 1;
		
	}
	
	void addFullHouse(){
		this.hands_stats[5] = this.hands_stats[5] + 1;
		
	}
	
	void addFour(){
		this.hands_stats[6] = this.hands_stats[6] + 1;
		
	}
	
	void addStraightFlush(){
		this.hands_stats[7] = this.hands_stats[7] + 1;
		
	}
	
	void addRoyalFlush(){
		this.hands_stats[8] = this.hands_stats[8] + 1;
		
	}
	
	void addOther(){
		this.hands_stats[9] = this.hands_stats[9] + 1;
		
	}
	
	void updateActualCredit(int actual_credit){
		
		this.actual_credit = actual_credit;
		
		
	}
	void addDeal(){
		this.number_deals = this.number_deals +1;
	}

	@Override
	public String toString() {
		double credit_return = ((double)this.actual_credit/(double)this.initial_credit);
		credit_return = credit_return * 100;
		return  "Hand                        Nb" + "\n" +
				"-------------------------------------" + "\n" +
				"Jacks or Better             " + this.hands_stats[0] + "\n" + 
				"Two Pair                    " + this.hands_stats[1] + "\n" +
				"Three of a Kind             " + this.hands_stats[2] + "\n" + 
				"Straight                    " + this.hands_stats[3] + "\n" + 
				"Flush                       " + this.hands_stats[4] + "\n" + 
				"Full House                  " + this.hands_stats[5] + "\n" + 
				"Four of a Kind              " + this.hands_stats[6] + "\n" + 
				"Straight Flush              " + this.hands_stats[7] + "\n" +
				"Royal Flush                 " + this.hands_stats[8] + "\n" +
				"Other                       " + this.hands_stats[9] + "\n" + 
				"-------------------------------------" + "\n" +
				"Total                       " + this.number_deals + "\n" +
				"-------------------------------------" + "\n" +
				"Credit                      " + this.actual_credit + " (" + credit_return + ")" +
				"\n";
	}
	

}
