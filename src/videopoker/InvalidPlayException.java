package videopoker;

/**
 * This Class implements a Exception for a invalid play in a videopooker machine implemented by the
 * VideoPoker interface. it extends the Exception class
 * @see Videopoker
 * @see Exception
 * @author Alexandre , Rui , Pedro
 *
 */
public class InvalidPlayException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with the message of the error
	 * @param message string with the error message type
	 */
	public InvalidPlayException(String message){
		
		super(message);
		
	}
	
	
}
