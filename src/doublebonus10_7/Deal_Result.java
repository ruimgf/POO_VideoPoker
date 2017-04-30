package doublebonus10_7;

import cards.HandCards;

public class Deal_Result extends Result {
	
	Deal_Result(HandCards hand,int credits){
		
		super(hand,credits);
		
	}

	
	@Override
	public String toString() {
		return "player's hand: " + this.hand;
	}
	

}
