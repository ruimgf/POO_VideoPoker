package videopoker;

/**
 * Class that represents the result of the Statistics command insert in the videopoker machine
 * @author Alexandre , Rui , Pedro
 * @see Result
 *
 */
public class StatisticsResult extends Result {
	
	/**
	 * This class have a a object of type Statistics that represent the statistics of the machine when the command is called @see Statistics
	 */
	protected Statistics game_stats;
	
	/**
	 * Constructor requires the objectt of Statistics of the game at the time that the command is called
	 * @param credits
	 * @param game_stats
	 */
	StatisticsResult(int credits, Statistics game_stats){
		
		super(credits);
		this.game_stats = game_stats;
		
	}

	@Override
	public String toString() {
		return "" + game_stats;
	}
	
	
}
