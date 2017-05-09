package cards;
/**
 *  Exception to take of invalid {@link Card} Construction
 */

public class InvalidCard extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCard() {
		// TODO Auto-generated constructor stub
	}

	public InvalidCard(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidCard(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCard(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCard(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
