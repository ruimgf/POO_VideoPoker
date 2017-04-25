package videopoker;

import cards.CardValue;

public class Advisor {

	public Advisor() {
		// TODO Auto-generated constructor stub
	}
	
	static void getAdvise(Hand h){
		CardAnalizer analise = new CardAnalizer(h);
		
		if(analise.NtoRoyalFlush(5)) // Royal Flush
			analise.printHoldIndex();
		if(analise.NtoStrFlush(5)) // Straight Flush
			analise.printHoldIndex();
		if(analise.NequalValueCards(4)) // Four of a Kind
			analise.printHoldIndex();
		if(analise.NequalValueCards(3,CardValue.ACE)) // Tree Aces
			analise.printHoldIndex();
		if(analise.NequalValueCards(3)) // Three of a kind
			analise.printHoldIndex();
	
		
	}

}
