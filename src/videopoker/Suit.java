package videopoker;

public enum Suit {
	
	DIAMONS,
    CLUBS,
    HEARTS,
    SPADES;
	
	@Override
	public String toString() {
		switch(this) {
			case DIAMONS: return "D";
			case CLUBS: return "C";
			case HEARTS: return "H";
			case SPADES: return "S";
			default: return "null";
		}
	}
}
