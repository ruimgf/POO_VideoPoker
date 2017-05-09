package ourvideopoker;

/**
 * TODO
 * @author Alexandre, Rui , Pedro
 *
 */
public class Statistics {
	/* position 0 representes jacks --- 9 represents 0ther hands*/
	/**
	 * TODO
	 */
	private int[] hands_stats = new int[10];
	/**
	 * TODO
	 */
	private int initial_credit;
	/**
	 * TODO
	 */
	private int actual_credit;
	/**
	 * TODO
	 */
	private int number_deals;
	
	/**
	 * TODO
	 * @param initial_credit
	 */
	public Statistics(int initial_credit){
		
		this.initial_credit=initial_credit;
	}
	
	/**
	 * TODO
	 * @param hand_name
	 */
	public void update_hand_stats(String hand_name){
		
		if(hand_name.equals("Jacks or Better")){
			this.addJacks();
			return;
		}
		if(hand_name.equals("Two Pair")){
			this.addTwoPair();
			return;
		}
		if(hand_name.contains("Three")){
			this.addThree();
			return;
		}
		if(hand_name.equals("Straight")){
			this.addStraight();
			return;
		}
		if(hand_name.equals("Flush")){
			this.addFlush();
			return;
		}
		if(hand_name.equals("Full House")){
			this.addFullHouse();
			return;
		}
		if(hand_name.contains("Four")){
			this.addFour();
			return;
		}
		if(hand_name.equals("Straight Flush")){
			this.addStraightFlush();
			return;
		}
		if(hand_name.equals("Royal Flush")){
			this.addRoyalFlush();
			return;
		}
		
		/*if not one of the previus hands that are to be in the table*/
		this.addOther();
		return;
		
	}
	
	/**
	 * TODO
	 */
	private void addJacks(){
		this.hands_stats[0] = this.hands_stats[0] + 1;
		
	}
	
	/**
	 * TODO
	 */
	private void addTwoPair(){
		this.hands_stats[1] = this.hands_stats[1] + 1;
		
	}
	
	/**
	 * TODO
	 * 
	 */
	private void addThree(){
		this.hands_stats[2] = this.hands_stats[2] + 1;
		
	}
	
	/**
	 * TODO
	 */
	private void addStraight(){
		this.hands_stats[3] = this.hands_stats[3] + 1;
		
	}
	
	/**
	 * TODO
	 */
	private void addFlush(){
		this.hands_stats[4] = this.hands_stats[4] + 1;
		
	}
	
	/**
	 * TODO
	 */
	private void addFullHouse(){
		this.hands_stats[5] = this.hands_stats[5] + 1;
		
	}
	
	/**
	 * TODO
	 */
	private void addFour(){
		this.hands_stats[6] = this.hands_stats[6] + 1;
		
	}
	
	/**
	 * TODO
	 */
	private void addStraightFlush(){
		this.hands_stats[7] = this.hands_stats[7] + 1;
		
	}
	
	/**
	 * TODO
	 */
	private void addRoyalFlush(){
		this.hands_stats[8] = this.hands_stats[8] + 1;
		
	}
	
	/**
	 * TODO
	 */
	private void addOther(){
		this.hands_stats[9] = this.hands_stats[9] + 1;
		
	}
	
	/**
	 * TODO
	 * @param actual_credit
	 */
	public void updateActualCredit(int actual_credit){
		
		this.actual_credit = actual_credit;
		
		
	}
	
	/**
	 *	TODO
	 */
	public void addDeal(){
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
