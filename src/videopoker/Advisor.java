package videopoker;

import cards.Card;
import cards.CardValue;
import cards.Suit;

public class Advisor {

	public Advisor() {
		// TODO Auto-generated constructor stub
	}
	
	static void getAdvise(Hand h){
		CardAnalizer analise = new CardAnalizer(h);
		
		if(analise.NtoRoyalFlush(5)){ // Royal Flush
			System.out.println("Royal Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.NtoStrFlush(5)){ // Straight Flush
			System.out.println("Straight Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.NequalValueCards(4)){// Four of a Kind
			System.out.println("4 of a Kind");
			analise.printHoldIndex(); return;
		}
		if(analise.NequalValueCards(3,CardValue.ACE)){ // Three Aces
			System.out.println("Three Aces");
			analise.printHoldIndex(); return;
		}
		if(analise.NequalValueCards(3)){ // Three of a kind
			System.out.println("Three of a kind");
			analise.printHoldIndex(); return;
		}
		if(analise.NtoStrFlush(4)){ // 4 to Straight Flush
			System.out.println("4 to Straight Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.TwoPair()){ // Two Pair
			System.out.println("Two Pair");
			analise.printHoldIndex(); return;
		}
		if(analise.HighPair()){ // High Pair
			System.out.println("High Pair");
			analise.printHoldIndex(); return;
		}
		if(analise.NFlush(4)){ // 4 to a flush
			System.out.println("4 to Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.NtoRoyalFlush(3)){ // 3 to Royal Flush
			System.out.println("3 to Royal Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.OutsideStraight()){ // 4 to an outside straight
			System.out.println("4 to outside Straight");
			analise.printHoldIndex(); return;
		}
		if(analise.LowPair()){ // Low Pair
			System.out.println("Low Pair");
			analise.printHoldIndex(); return;
		}
	
		if(analise.AKQJunsuited()){ // AKQJ unsuited
			System.out.println("AKQJ unsuited");
			analise.printHoldIndex(); return;
		}
		
		if(analise.threeToStrType1()){ // 3 to straight type 1
			System.out.println("Tree to a straight (type 1)");
			analise.printHoldIndex(); return;
		}
		
		
	
	}
	
	
	public static void main(String[] args) {
		Hand h;
		h = new Hand();
		
		h.mycards[0] = new Card(Suit.SPADES,CardValue.ACE);
		h.mycards[1] = new Card(Suit.HEARTS,CardValue.QUEEN);
		h.mycards[2] = new Card(Suit.HEARTS,CardValue.THREE);
		h.mycards[3] = new Card(Suit.SPADES,CardValue.TWO);
		h.mycards[4] = new Card(Suit.HEARTS,CardValue.EIGTH);
		for (int i = 0; i < h.mycards.length; i++) {
			System.out.print(h.mycards[i] + " ");
			
		}
		System.out.println("");
		
		Advisor.getAdvise(h);
	}

}
