package ourvideopoker;

/**
 * This Class provides a implementation to deal with statistics in {@link OurVideoPoker} implementation.
 * It only hands statistics with the following hands:
 *  <p>- Jacks or Better</p>
 *  <p>- Two Pair</p>
 *  <p>- Three of a Kind</p>
 *  <p>- Straight</p>
 *  <p>- Flush</p>
 *  <p>- Full House</p>
 *  <p>- Four of a Kind</p>
 *  <p>- Straight Flush</p>
 *  <p>- Royal Flush</p>
 * @author Alexandre, Rui , Pedro
 *
 */
public class Statistics {
	/* position 0 representes jacks --- 9 represents 0ther hands*/
	/**
	 * int array that represents the table of statistics, it contains the number of time each hand have appeard
	 * in our videopoker machine
	 */
	private int[] hands_stats = new int[10];
	/**
	 * the initial credit that the player insert int he videopoker machine
	 */
	private int initial_credit;
	/**
	 * the last update credit in the statisctics
	 */
	private int actual_credit;
	/**
	 * number of deals that have appen in the machine
	 */
	private int number_deals;
	
	/**
	 * Constructor only requires the initial credit that has been introduced in the machine.
	 * @param initial_credit initial credit introduced in the machine
	 */
	public Statistics(int initial_credit){
		
		this.initial_credit=initial_credit;
	}
	
	/**
	 * Method that receives the string with the hand_name and increments the handstats based on the name
	 * @param hand_name string with the name of the hand to add in statistics
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
	 * Method that add 1 in the Jacks stats
	 */
	private void addJacks(){
		this.hands_stats[0] = this.hands_stats[0] + 1;
		
	}
	
	/**
	 * Method that add 1 in the TwoPair stats
	 */
	private void addTwoPair(){
		this.hands_stats[1] = this.hands_stats[1] + 1;
		
	}
	
	/**
	 * Method that add 1 in the Three of a Kind stats
	 * 
	 */
	private void addThree(){
		this.hands_stats[2] = this.hands_stats[2] + 1;
		
	}
	
	/**
	 * Method that add 1 in the Straight stats
	 */
	private void addStraight(){
		this.hands_stats[3] = this.hands_stats[3] + 1;
		
	}
	
	/**
	 * Method that add 1 in the Flush stats
	 */
	private void addFlush(){
		this.hands_stats[4] = this.hands_stats[4] + 1;
		
	}
	
	/**
	 * Method that add 1 in the Full House stats
	 */
	private void addFullHouse(){
		this.hands_stats[5] = this.hands_stats[5] + 1;
		
	}
	
	/**
	 * Method that add 1 in the Four of Kind stats
	 */
	private void addFour(){
		this.hands_stats[6] = this.hands_stats[6] + 1;
		
	}
	
	/**
	 * Method that add 1 in the Straight Flush stats
	 */
	private void addStraightFlush(){
		this.hands_stats[7] = this.hands_stats[7] + 1;
		
	}
	
	/**
	 * Method that add 1 in the Royal Flush stats
	 */
	private void addRoyalFlush(){
		this.hands_stats[8] = this.hands_stats[8] + 1;
		
	}
	
	/**
	 * Method that add 1 in the other type of hands stats
	 */
	private void addOther(){
		this.hands_stats[9] = this.hands_stats[9] + 1;
		
	}
	
	/**
	 * Method that return update the actual credit of the machine in the machine stats
	 * @param actual_credit new credit of the player
	 */
	public void updateActualCredit(int actual_credit){
		
		this.actual_credit = actual_credit;
		
		
	}
	
	/**
	 *	method that add a deal in the stats
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
