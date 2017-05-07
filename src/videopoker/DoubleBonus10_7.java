package videopoker;

import java.util.HashMap;

import cards.CardAnalizer;
import cards.CardValue;
import cards.HandCards;

public class DoubleBonus10_7 implements VideoPokerVariation{
	
	private HashMap<String, Integer > PayoutMap = new HashMap<String, Integer>();
	
	
	public DoubleBonus10_7(){
		
		/*create the payout table and put in the payoutmap the payouttable have the multiplier of each hand
		 * 
		 */
		PayoutMap.put("Royal Flush", 250);
		PayoutMap.put("Straight Flush", 50);
		PayoutMap.put("Four "+CardValue.ACE, 160);
		for(int i=1; i<4 ; i++){
			PayoutMap.put("Four "+CardValue.parse(i), 80);
		}
		for(int i=4;i<13;i++){
			PayoutMap.put("Four "+CardValue.parse(i), 50);
		}
		
		PayoutMap.put("Full House",10);
		PayoutMap.put("Flush",7);
		PayoutMap.put("Straight",5);
		for(int i=1;i<14;i++){
			PayoutMap.put("Three "+CardValue.parse(i), 3);
		}
		PayoutMap.put("Two Pair",1);
		PayoutMap.put("Jacks or Better",1);
		PayoutMap.put("Other",0);
		
		
	}
	
	/**
	 * Method that give the hand cards of the game gives the name of the and
	 * @return a string with the name of the hand according with DoubleBonus10_7
	 */
	public String evaluate_hand_name(HandCards hand)
	{
		
		CardAnalizer aux = new CardAnalizer(hand);
		/*vector of constants only to check the four of a kind and three of a kind in a four loop*/
		
		
		/*check if it is a RoyalFlush*/
		if(aux.NtoRoyalFlush(5)){
			return "Royal Flush";
		}
		
		/*check if it is Straight Flush - the multiplier is 50*/
		if(aux.NtoStrFlush(5)){
			return "Straight Flush";
		}
		
		
		/*check if 4 equal cards*/
		for(int i=1;i<14;i++){
			
			if(aux.NequalValueCards(4, CardValue.parse(i))){
				
				return "Four " + CardValue.parse(i);
				
			}
		}
		
		/*check if FullHouse*/
		if(aux.fullHouse()){
			return "Full House";
		}
		
		/*check if flush*/
		if(aux.NFlush(5)){
			return "Flush";
		}
		
		/*Straight*/
		if(aux.NStraight(5)){
			return "Straight";
		}
		
		/*check if 3 equal cards*/
		for(int i=1;i<14;i++){
			
			if(aux.NequalValueCards(3, CardValue.parse(i))){
				return "Three " + CardValue.parse(i);
				
			}
			
		}
		
		/*two pair*/
		if(aux.TwoPair()){
			return "Two Pair";
		}
		/*jacks or better*/
		if(aux.HighPair()){
			return "Jacks or Better";
		}
		
		/*no prize hand*/
		return "Other";
		
		
	}

	
	/**
	 * Method that given the hand and the bet gives the payout of the bet
	 * @param hand - player hand , bet - bet that the player had place
	 * @return payout of the bet
	 */
	@Override
	public int get_payout(HandCards hand, int bet) {
		
		CardAnalizer aux = new CardAnalizer(hand);
		/*vector of constants only to check the four of a kind and three of a kind in a four loop*/
		
		
		/*check if it is a RoyalFlush beacuse payout for royal flush and 5 bet is not payouttable * bet*/
		if(aux.NtoRoyalFlush(5)){
			if(bet == 5){
				return 4000;
			}
		}
		
		return bet * this.PayoutMap.get(this.evaluate_hand_name(hand));
		
		
		
		
	}

	@Override
	public boolean[] evaluate_hand_advice(HandCards hand) {
		
		CardAnalizer analise = new CardAnalizer(hand);
		while(true){
			
		
			if(analise.NtoRoyalFlush(5)){ // 1 - Royal Flush
				//System.out.println(" Royal Flush");
				break;
				
			}
			if(analise.NtoStrFlush(5)){ // 1 - Straight Flush
				//System.out.println("Straight Flush");
				break;
			}
			if(analise.NequalValueCards(4)){// 1 - Four of a Kind
				//System.out.println("4 of a Kind");
				break;
			}
			
			if(analise.NtoRoyalFlush(4)){ // 2 - 4 to Royal Flush
				//System.out.println("4 Royal Flush");
				break;
			}
			
			if(analise.NequalValueCards(3,CardValue.ACE)){ // 3 - Three Aces
				//System.out.println("Three Aces");
				break;
			}
			
			if(analise.NFlush(5)){ // 4 - Flush
				//System.out.println("5 Flush");
				break;
			}
			
			if(analise.NStraight(5)){ // 4 - Straight
				//System.out.println("5 Straight");
				break;
			}
			
			if(analise.fullHouse()){ // 4- Full House
				//System.out.println("Full House");
				break;
			} 
			
			if(analise.NequalValueCards(3)){ // 5 - Three of a kind
				//System.out.println("Three of a kind");
				break;
			}
			if(analise.NtoStrFlush(4)){ // 6 - 4 to Straight Flush
				//System.out.println("4 to Straight Flush");
				break;
			}
			if(analise.TwoPair()){ // 7 - Two Pair
				//System.out.println("Two Pair");
				break;
			}
			if(analise.HighPair()){ // 8 -  High Pair
				//System.out.println("High Pair");
				break;
			}
			if(analise.NFlush(4)){ // 9 - 4 to a flush
				//System.out.println("4 to Flush");
				break;
			}
			if(analise.NtoRoyalFlush(3)){ // 10 - 3 to Royal Flush
				//System.out.println("3 to Royal Flush");
				break;
			}
			if(analise.OutsideStraight()){ // 11 - 4 to an outside straight
				//System.out.println("4 to outside Straight");
				break;
			}
			if(analise.LowPair()){ // 12 - Low Pair
				//System.out.println("Low Pair");
				break;
			}
		
			if(analise.AKQJunsuited()){ // 13 - AKQJ unsuited
				//System.out.println("AKQJ unsuited");
				break;
			}
			
			if(analise.threeToStrType1()){ // 14 - 3 to straight type 1
				//System.out.println("Three to a straight (type 1)");
				break;
			}
			
			if(analise.fourInStrWithNHighCards(3)){ // 15 - 4 inside straight with 3 high cards
				//System.out.println("4 inside straight with 3 high cards");
				break;
			}
			
			if(analise.C1C2Suited(CardValue.QUEEN, CardValue.JACK)){ // 16 - QJ Suited
				//System.out.println("QJ suited");
				break;
			}
		
			if(analise.threeToFlushWithNHighCards(2)){ // 17 - 3 to flush with 2 HIGH Cards
				//System.out.println("3 to flush with 2 HIGH Cards");
				break;
			}
			
			if(analise.twoSuitedHighCards()){  // 18 - 2 Suited HIGH Cards
				//System.out.println("2 Suited High Cards");
				break;
			}
		
			
			if(analise.fourInStrWithNHighCards(2)){ // 19 - 4 inside straight with 2 high cards
				//System.out.println("4 inside straight with 2 high cards");
				break;
			}
			
			
			if(analise.threeToStrType2()){ // 20 -3 straight flush type 2
				//System.out.println("3 straight flush type 2");
				break;
			}
			
			
			if(analise.fourInStrWithNHighCards(1)){ // 21 - 4 inside straight with 1 high cards
				//System.out.println("4 inside straight with 1 high cards");
				break;
			}
		
			if(analise.KQJunsuited()){ // 22 - KQJ unsuited
				//System.out.println("KQJ unsuited");
				break;
			}
			
			if(analise.C1C2Suited(CardValue.JACK, CardValue.TEN)){ // 23 - JT Suited
				//System.out.println("JT suited");
				break;
			}
			
			if(analise.C1C2Unsuited(CardValue.QUEEN, CardValue.JACK)){ // 24 - QJ UnSuited
				//System.out.println("QJ Unsuited");
				break;
			}
			
			if(analise.threeToFlushWithNHighCards(1)){ // 25 - 3 to flush with 1 HIGH Cards
				//System.out.println("3 to flush with 1 HIGH Cards");
				break;
			}
			
			if(analise.C1C2Suited(CardValue.QUEEN, CardValue.TEN)){ // 26 - QT Suited
				//System.out.println("QT suited");
				break;
			}
		
			if(analise.threeToStrType3()){ // 27 - 3 to a straight flush type 3
				//System.out.println("3 to straight flush type 3");
				break;
			}
			
			if(analise.C1C2Unsuited(CardValue.JACK, CardValue.KING)){ // 28 - KJ UnSuited
				//System.out.println("KJ Unsuited");
				break;
			}
			
			if(analise.C1C2Unsuited(CardValue.QUEEN, CardValue.KING)){ // 28 - KQ UnSuited
				//System.out.println("KQ Unsuited");
				break;
			}
			
			if(analise.NequalValueCards(1, CardValue.ACE)){ // 29 - ACE
				//System.out.println("ACE");
				break;
			}
			
			if(analise.C1C2Suited(CardValue.KING, CardValue.TEN)){ // 30 - KT Suited
				//System.out.println("KT suited");
				break;
			}
			
			if(analise.NHighCards(1)){ // 31 J or Q or K
				//System.out.println("Jack or Queen or King");
				break;
			}
			
			if(analise.fourInStrWithNHighCards(0)){ // 32 - 4 inside straight with 0 high cards
				//System.out.println("4 inside straight with 0 high cards");
				break;
			}
			
			if(analise.threeToFlushWithNHighCards(0)){ // 33 - 3 to flush with 0 HIGH Cards
				//System.out.println("3 to flush with 1 HIGH Cards");
				break;
			}
			
			break;
			
		}
		
		return analise.holdCards();
		
		
	}

}
