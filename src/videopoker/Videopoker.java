package videopoker;

public interface Videopoker {
	public bet_result bet();
	public credit_result credit();
	public deal_result deal();
	public hold_result hold();
	public advice_result advice();
	public statistics_result statistics();	
}