package cards;
/**
 * 
 * enum type that represents possible suit of a Card
 * 
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo
 *
 */
public enum Suit {
	
	DIAMONS,
    CLUBS,
    HEARTS,
    SPADES;
	/**
	 * 
	 * @param Value to parse
	 * @return Correspond CardValue
	 * @throws InvalidCard if parse is invalid
	 */
	public static Suit parse(char Value) throws InvalidCard {
		switch(Value) {
			case 'D': return DIAMONS;
			case 'C': return CLUBS;
			case 'H': return HEARTS;
			case 'S': return SPADES;
			default: throw new InvalidCard("Invalid Card Suit");
		}
	}
	
	@Override
	public String toString() {
		switch(this) {
			case DIAMONS: return "D";
			case CLUBS: return "C";
			case HEARTS: return "H";
			case SPADES: return "S";
			default: return "";
		}
	}
}
