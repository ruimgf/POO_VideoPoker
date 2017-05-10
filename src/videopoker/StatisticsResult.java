package videopoker;

/**
 * Class that represents the result of the Statistics command insert in the videopoker machine
 * @author Alexandre , Rui , Pedro
 * @see Result
 *
 */
public class StatisticsResult extends Result {
	
	/**
	 * This class have a string description of the statistics at the time of the command
	 */
	String game_stats;
	
	/**
	 * Constructor requires the string description of Statistics of the game at the time that the command is called
	 * @param credits the credits that the player have at the time of the command
	 * @param game_stats string description of the game statistics ate the time of the command
	 */
	public StatisticsResult(int credits, String game_stats){
		
		super(credits);
		this.game_stats = game_stats;
		
	}

	@Override
	public String toString() {
		return "" + game_stats;
	}
	
	
}
