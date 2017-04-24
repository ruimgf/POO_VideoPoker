package cards;

public enum CardValue {
	TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGTH,NINE,TEN,JACK,QUEEN,KING,ACE;
	
	public static CardValue parse(char value) {
		switch(value) {
			case '2': return TWO;
			case '3': return THREE;
			case '4': return FOUR;
			case '5': return FIVE;
			case '6': return SIX;
			case '7': return SEVEN;
			case '8': return EIGTH;
			case '9': return NINE;
			case 'T': return TEN;
			case 'J': return JACK;
			case 'Q': return QUEEN;
			case 'K': return KING;
			case 'A': return ACE;
			default: return TWO; // gerar excepcao
		}
	}
	
	public int intValue() {
		switch(this) {
			case TWO: return 2;
			case THREE: return 3;
			case FOUR: return 4;
			case FIVE: return 5;
			case SIX: return 6;
			case SEVEN: return 7;
			case EIGTH: return 8;
			case NINE: return 9;
			case TEN: return 10;
			case JACK: return 11;
			case QUEEN: return 12;
			case KING: return 13;
			case ACE: return 14;
			default: return -1; // gerar expecao
		}
	}
	
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
			case TEN: return "T";
			case JACK: return "J";
			case QUEEN: return "Q";
			case KING: return "K";
			case ACE: return "A";
			default: return "null"; // gerar expecao
		}
	}

}


