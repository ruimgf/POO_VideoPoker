package videopoker;

public class Statistics_Result extends Result {
	Statistics game_stats;
	
	Statistics_Result(Statistics game_stats){
		
		this.game_stats = game_stats;
		
	}

	@Override
	public String toString() {
		return "" + game_stats;
	}
	
	
}
