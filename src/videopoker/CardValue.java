package videopoker;

public enum CardValue {
	TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGTH,NINE,TEN,JACK,QUEEN,KING,ACE;
	
	public String toString() {
		switch(this) {
			case TWO: return "2";
			case THREE: return "3";
			case FOUR: return "4";
			case FIVE: return "5";
			case SIX: return "6";
			case SEVEN: return "7";
			case EIGTH: return "8";
			case NINE: return "9";
			case TEN: return "10";
			case JACK: return "J";
			case QUEEN: return "Q";
			case KING: return "K";
			case ACE: return "A";
			default: return "null";
		}
	}
}


