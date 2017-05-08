package videopoker;

import cards.HandCards;

public interface VideoPokerVariation {
 
	public int get_payout(HandCards hand,int bet);
	public String evaluate_hand_name(HandCards hand);
	public boolean[] evaluate_hand_advice(HandCards hand);
	
}
