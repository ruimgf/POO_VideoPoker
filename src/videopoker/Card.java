package videopoker;

public class Card {
	
	final Suit suit;
	final CardValue value;
	
	public Card(Suit inSuit, CardValue inValue) {
		suit = inSuit;
		value = inValue;
	}
	
	@Override
	public String toString() {
		return "Card "+value+suit+"";
	}

	public static void main(String[] args) {
		for (Suit S : Suit.values()){
			for (CardValue CV : CardValue.values()){
				Card first = new Card(S,CV);
				System.out.println(first);
			}
		}
		
	}
	
	
	
	
	
}
