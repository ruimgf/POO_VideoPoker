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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suit != other.suit)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public static void main(String[] args) {
		for (Suit S : Suit.values()){
			for (CardValue CV : CardValue.values()){
				Card first = new Card(S,CV);
				System.out.println(first);
			}
		}
		
		Card second = new Card( Suit.DIAMONS,CardValue.TWO );
		Card first = new Card(Suit.DIAMONS,CardValue.TWO);
		
		if(first.equals(second)){
			System.out.print("equals a funcionar ");
			System.out.println(first);
		}
		
		
	}
	
	
	
	
	
}
