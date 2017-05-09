package videopoker;

import cards.HandCards;

public interface VideoPokerVariation {
 
	public int getPayout(HandCards hand,int bet);
	public String evaluateHandName(HandCards hand);
	public boolean[] evaluateHandAdvice(HandCards hand);
	
}
