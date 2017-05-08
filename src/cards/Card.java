/**
 * cards is a group of utilities for operating on card things.
 */
package cards;

public class Card {
	
	

	final Suit suit;
	final CardValue value;
	
	public Card(Suit inSuit, CardValue inValue) {
		suit = inSuit;
		value = inValue;
	}
	
	public Card(String desc){
		value = CardValue.parse(desc.charAt(0));
		suit = Suit.parse(desc.charAt(1));
	}
	
	
	public Suit getSuit() {
		return suit;
	}

	public CardValue getValue() {
		return value;
	}
	
	
	@Override
	public String toString() {
		return "" + value + suit + "";
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
	
	public boolean equalsSuit(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suit != other.suit)
			return false;
		return true;
	}
	
	public boolean equalsValue(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
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
		Card thirth = new Card(Suit.DIAMONS,CardValue.THREE);
		Card fourth = new Card(Suit.HEARTS,CardValue.THREE);
		if(first.equals(second)){
			System.out.print("Card 1 equals to card 2 ");
			System.out.println(first);
		}
		
		if(first.equalsSuit(second)){
			System.out.println("Card 1 is of same suit of card 2 ");
		}
		
		if(!first.equalsSuit(fourth)){
			System.out.println("Card 1 isn't of same suit of card 4 ");
			
		}
		
		if(!first.equalsValue(thirth)){
			System.out.println("Card 1 isn't of same value of card 3 ");
			
		}
		
		Card c2 = new Card("TD");
		System.out.println("Card new constructor : " +  c2);
		
	}
	
	
	
	
	
}
