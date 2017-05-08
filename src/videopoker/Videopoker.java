package videopoker;


public interface Videopoker {
	public Bet_Result bet(int credits) throws InvalidPlayException;
	public Bet_Result bet() throws InvalidPlayException;
	public Credit_Result credit() throws InvalidPlayException;
	public Deal_Result deal() throws InvalidPlayException;
	public Hold_Result hold(boolean[] indexs) throws InvalidPlayException;
	public Advice_Result advice() throws InvalidPlayException;
	public Statistics_Result statistics() throws InvalidPlayException;	
	public void quit() throws InvalidPlayException;
}