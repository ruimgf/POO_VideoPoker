package doublebonus10_7;

/**
 * Invalid_Result Class represents a type of Result that is a invalid play
 * @author Alexandre
 *
 */
public class Invalid_Result extends Result {
	String message;
	
	public Invalid_Result(String message){
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
	
	
	
	
}
