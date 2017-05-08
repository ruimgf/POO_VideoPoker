package videopoker;

/**
 * TODO
 * @author Alexandre
 *
 */
public class StatisticsResult extends Result {
	
	protected Statistics game_stats;
	
	/**
	 * TODO
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
