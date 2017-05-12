package videopoker;
/**
 * This Interface represents the methods that a videopoker machine has to implement to provide
 * the minimum game functionalities, in our point of view.
 * @author Alexandre, Rui , Pedro
 *
 */
public interface Videopoker {
	/**
	 * This method is called when we want to place a bet in the videopoker machine
	 * @param credits integer with the credits of the bet
	 * @return BetResult object with the result of the bet move if it is valid
	 * @throws InvalidPlayException throws this exception if the bet is not valid, with a message that
	 * characterizes the error.
	 * @see BetResult
	 */
	public BetResult bet(int credits) throws InvalidPlayException;
	
	/**
	 * This method is called when we want to place a bet in the videopoker machine without any bet credit
	 * it should place the same credits of the last previous valid bet in the videopoker machine
	 * @return BetResult object with the result of the bet move if it is valid
	 * @throws InvalidPlayException throws this exception if the bet is not valid, with a message that
	 * characterizes the error.
	 * @see BetResult
	 */
	public BetResult bet() throws InvalidPlayException;
	
	/**
	 * This method is called when we know how much credit does the videopoker machine have.
	 * @return CreditResult object with the result command if it is valid
	 * @throws InvalidPlayException throws this exception if the command is not valid, with a message that
	 * characterizes the error.
	 * @see CreditResult
	 */
	public CreditResult credit() throws InvalidPlayException;
	
	/**
	 * This method is called when we want to do the deal in the videopoker machine
	 * @return DealResult object with the result of the deal move if it is valid
	 * @throws InvalidPlayException throws this exception if the deal is not valid, with a message that
	 * characterizes the error.
	 * @see DealResult
	 */
	public DealResult deal() throws InvalidPlayException;
	
	/**
	 * This method is called when we want to do the hold command in the videopoker machine, that is
	 * give the information of what cards do we want to hold.
	 * @param indexs boolean array that is true in the index of the cards that we want to hold and false in
	 * the ones that we want to discard
	 * @return HoldResult object with the result of the Hold move if it is valid
	 * @throws InvalidPlayException throws this exception if the hold is not valid, with a message that
	 * characterizes the error.
	 * @see HoldResult
	 */
	public HoldResult hold(boolean[] indexs) throws InvalidPlayException;
	
	/**
	 * This method is called when we want to ask a advice to the videopoker machine
	 * @return AdviceResult object with the result of the hold move if it is valid
	 * @throws InvalidPlayException throws this exception if the advice command is not valid, with a message that
	 * characterizes the error.
	 * @see AdviceResult
	 */
	public AdviceResult advice() throws InvalidPlayException;
	
	/**
	 * This method is called when we want to know what are the statistics of the videopoker machine
	 * @return StatisticsResult object with the result of the command move if it is valid
	 * @throws InvalidPlayException throws this exception if the command is not valid, with a message that
	 * characterizes the error.
	 * @see StatisticsResult
	 */
	public StatisticsResult statistics() throws InvalidPlayException;
	
	/**
	 * This method is called when we want to quit the videopoker machine
	 * @throws InvalidPlayException if it cannot quit it throws a exception with a message that
	 * characterizes the error.
	 */
	public void quit() throws InvalidPlayException;
}