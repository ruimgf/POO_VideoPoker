package cards;
/**
 * Exception to take of no more cards in {@link Deck}
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo
 *
 */
public class EndOfDeck extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EndOfDeck() {
		// TODO Auto-generated constructor stub
	}

	public EndOfDeck(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EndOfDeck(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EndOfDeck(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EndOfDeck(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
