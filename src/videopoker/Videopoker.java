package videopoker;

public interface Videopoker {
	public Bet_Result bet(int credits);
	public Bet_Result bet();
	public Credit_Result credit();
	public Deal_Result deal();
	public Hold_Result hold(boolean[] indexs);
	public Advice_Result advice();
	public Statistics_Result statistics();	
	public void quit();
}