package videopoker;


public interface Videopoker {
	public BetResult bet(int credits) throws InvalidPlayException;
	public BetResult bet() throws InvalidPlayException;
	public CreditResult credit() throws InvalidPlayException;
	public DealResult deal() throws InvalidPlayException;
	public HoldResult hold(boolean[] indexs) throws InvalidPlayException;
	public AdviceResult advice() throws InvalidPlayException;
	public StatisticsResult statistics() throws InvalidPlayException;	
	public void quit() throws InvalidPlayException;
}