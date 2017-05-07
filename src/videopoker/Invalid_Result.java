package videopoker;

/**
 * Invalid_Result Class represents a type of Result that is a invalid play
 * @author Alexandre
 *
 */
public class Invalid_Result extends Result {
	String message;
	
	public Invalid_Result(String message,int credits){
		super(credits);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
	
	
	
	
}
