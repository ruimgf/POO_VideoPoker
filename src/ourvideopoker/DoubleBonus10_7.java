package ourvideopoker;

import java.util.HashMap;
import cards.CardAnalizer;
import cards.CardValue;
import cards.HandCards;
import cards.InvalidCard;

import videopoker.*;

/**
 * Class that implements the VideoPokerVariation Interface with the DoubleBonus 10_7 variation of a videopoker
 * game.
 * @author Alexandre, Rui , Pedro
 * @see VideoPokerVariation
 */
public class DoubleBonus10_7 implements VideoPokerVariation{
	/**
	 * This variation is equipped with a object for analyze handcards and give a framework 
	 * of methods to give advices for the player.
	 * @see CardAnalizer
	 */
	CardAnalizer analizer = new CardAnalizer();

	/**
	 * This object is a private hashmap that represents the payout table for this variation.
	 */
	final HashMap<String, Integer > PayoutMap;
	
	/**
	 * Constructor of the DoubleBonus10_7 variation, it only initializes the payout hashmap 
	 */
	public DoubleBonus10_7(){
		
		PayoutMap = new HashMap<String, Integer>();
		
		/*create the payout table and put in the payoutmap the payouttable have the multiplier of each hand
		 * 
		 */
		PayoutMap.put("Royal Flush", 250);
		PayoutMap.put("Straight Flush", 50);
		PayoutMap.put("Four "+CardValue.ACE, 160);
		for(int i=1; i<4 ; i++){
			try {
				PayoutMap.put("Four "+CardValue.parse(i), 80);
			} catch (InvalidCard e) {
				
				e.printStackTrace();
			}
		}
		for(int i=4;i<13;i++){
			try {
				PayoutMap.put("Four "+CardValue.parse(i), 50);
			} catch (InvalidCard e) {
				e.printStackTrace();
			}
		}
		
		PayoutMap.put("Full House",10);
		PayoutMap.put("Flush",7);
		PayoutMap.put("Straight",5);
		for(int i=1;i<14;i++){
			try {
				PayoutMap.put("Three "+CardValue.parse(i), 3);
			} catch (InvalidCard e) {
				e.printStackTrace();
			}
		}
		PayoutMap.put("Two Pair",1);
		PayoutMap.put("Jacks or Better",1);
		PayoutMap.put("Other",0);
		
		
	}
	
	/**
	 * Method that returns the name of the hand in this varitation given a hand
	 * @param hand - it receives a HandCards object with a hand of cards to be evaluated
	 * @return returns a string that is the name of the given hand in this variation
	 * @see HandCards
	 */
	public String evaluateHandName(HandCards hand)
	{
		analizer.reinitializeCount(hand);
		/*vector of constants only to check the four of a kind and three of a kind in a four loop*/
		
		
		/*check if it is a RoyalFlush*/
		if(analizer.NtoRoyalFlush(5)){
			return "Royal Flush";
		}
		
		/*check if it is Straight Flush - the multiplier is 50*/
		if(analizer.NtoStrFlush(5)){
			return "Straight Flush";
		}
		
		
		/*check if 4 equal cards*/
		for(int i=1;i<14;i++){
			
			try {
				if(analizer.NequalValueCards(4, CardValue.parse(i))){
					
					return "Four " + CardValue.parse(i);
					
				}
			} catch (InvalidCard e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*check if FullHouse*/
		if(analizer.fullHouse()){
			return "Full House";
		}
		
		/*check if flush*/
		if(analizer.NFlush(5)){
			return "Flush";
		}
		
		/*Straight*/
		if(analizer.NStraight(5)){
			return "Straight";
		}
		
		/*check if 3 equal cards*/
		for(int i=1;i<14;i++){
			
			try {
				if(analizer.NequalValueCards(3, CardValue.parse(i))){
					return "Three " + CardValue.parse(i);
					
				}
			} catch (InvalidCard e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/*two pair*/
		if(analizer.TwoPair()){
			return "Two Pair";
		}
		/*jacks or better*/
		if(analizer.HighPair()){
			return "Jacks or Better";
		}
		
		/*no prize hand*/
		return "Other";
		
		
	}

	
	/**
	 * Method that takes a hand of cards and the bet that the player have placed and returns the respective payout
	 * @param hand it receives a HandCards object with a hand of cards to be evaluated
	 * @param bet the value that the player is betting in the hand
	 * @return it returns a int whit the payout for the given hand
	 * @see HandCards
	 */
	@Override
	public int getPayout(HandCards hand, int bet) {
		
		analizer.reinitializeCount(hand);
		/*vector of constants only to check the four of a kind and three of a kind in a four loop*/
		
		
		/*check if it is a RoyalFlush beacuse payout for royal flush and 5 bet is not payouttable * bet*/
		if(analizer.NtoRoyalFlush(5)){
			if(bet == 5){
				return 4000;
			}
		}
		
		return bet * this.PayoutMap.get(this.evaluateHandName(hand));
		
		
		
		
	}

	/**
	 * This method gives advices of cards that a player have to hold
	 * for the DoubleBonus variation of the VideoPoker based on a given hand
	 * @param hand it receives a HandCards object with a hand of cards to be evaluated
	 * @return it returns a fixed 5 size boolean array that is true in the index's of the hand the player have to hold
	 * and false in the index's that are not to hold
	 */
	@Override
	public boolean[] evaluateHandAdvice(HandCards hand) {
		analizer.reinitializeCount(hand);
		
		while(true){
			
		
			if(analizer.NtoRoyalFlush(5)){ // 1 - Royal Flush
				//System.out.println(" Royal Flush");
				break;
				
			}
			if(analizer.NtoStrFlush(5)){ // 1 - Straight Flush
				//System.out.println("Straight Flush");
				break;
			}
			if(analizer.NequalValueCards(4)){// 1 - Four of a Kind
				//System.out.println("4 of a Kind");
				break;
			}
			
			if(analizer.NtoRoyalFlush(4)){ // 2 - 4 to Royal Flush
				//System.out.println("4 Royal Flush");
				break;
			}
			
			if(analizer.NequalValueCards(3,CardValue.ACE)){ // 3 - Three Aces
				//System.out.println("Three Aces");
				break;
			}
			
			if(analizer.NFlush(5)){ // 4 - Flush
				//System.out.println("5 Flush");
				break;
			}
			
			if(analizer.NStraight(5)){ // 4 - Straight
				//System.out.println("5 Straight");
				break;
			}
			
			if(analizer.fullHouse()){ // 4- Full House
				//System.out.println("Full House");
				break;
			} 
			
			if(analizer.NequalValueCards(3)){ // 5 - Three of a kind
				//System.out.println("Three of a kind");
				break;
			}
			if(analizer.NtoStrFlush(4)){ // 6 - 4 to Straight Flush
				//System.out.println("4 to Straight Flush");
				break;
			}
			if(analizer.TwoPair()){ // 7 - Two Pair
				//System.out.println("Two Pair");
				break;
			}
			if(analizer.HighPair()){ // 8 -  High Pair
				//System.out.println("High Pair");
				break;
			}
			if(analizer.NFlush(4)){ // 9 - 4 to a flush
				//System.out.println("4 to Flush");
				break;
			}
			if(analizer.NtoRoyalFlush(3)){ // 10 - 3 to Royal Flush
				//System.out.println("3 to Royal Flush");
				break;
			}
			if(analizer.OutsideStraight()){ // 11 - 4 to an outside straight
				//System.out.println("4 to outside Straight");
				break;
			}
			if(analizer.LowPair()){ // 12 - Low Pair
				//System.out.println("Low Pair");
				break;
			}
		
			if(analizer.AKQJunsuited()){ // 13 - AKQJ unsuited
				//System.out.println("AKQJ unsuited");
				break;
			}
			
			if(analizer.threeToStrType1()){ // 14 - 3 to straight type 1
				//System.out.println("Three to a straight (type 1)");
				break;
			}
			
			if(analizer.fourInStrWithNHighCards(3)){ // 15 - 4 inside straight with 3 high cards
				//System.out.println("4 inside straight with 3 high cards");
				break;
			}
			
			if(analizer.C1C2Suited(CardValue.QUEEN, CardValue.JACK)){ // 16 - QJ Suited
				//System.out.println("QJ suited");
				break;
			}
		
			if(analizer.threeToFlushWithNHighCards(2)){ // 17 - 3 to flush with 2 HIGH Cards
				//System.out.println("3 to flush with 2 HIGH Cards");
				break;
			}
			
			if(analizer.twoSuitedHighCards()){  // 18 - 2 Suited HIGH Cards
				//System.out.println("2 Suited High Cards");
				break;
			}
		
			
			if(analizer.fourInStrWithNHighCards(2)){ // 19 - 4 inside straight with 2 high cards
				//System.out.println("4 inside straight with 2 high cards");
				break;
			}
			
			
			if(analizer.threeToStrType2()){ // 20 -3 straight flush type 2
				//System.out.println("3 straight flush type 2");
				break;
			}
			
			
			if(analizer.fourInStrWithNHighCards(1)){ // 21 - 4 inside straight with 1 high cards
				//System.out.println("4 inside straight with 1 high cards");
				break;
			}
		
			if(analizer.KQJunsuited()){ // 22 - KQJ unsuited
				//System.out.println("KQJ unsuited");
				break;
			}
			
			if(analizer.C1C2Suited(CardValue.JACK, CardValue.TEN)){ // 23 - JT Suited
				//System.out.println("JT suited");
				break;
			}
			
			if(analizer.C1C2Unsuited(CardValue.QUEEN, CardValue.JACK)){ // 24 - QJ UnSuited
				//System.out.println("QJ Unsuited");
				break;
			}
			
			if(analizer.threeToFlushWithNHighCards(1)){ // 25 - 3 to flush with 1 HIGH Cards
				//System.out.println("3 to flush with 1 HIGH Cards");
				break;
			}
			
			if(analizer.C1C2Suited(CardValue.QUEEN, CardValue.TEN)){ // 26 - QT Suited
				//System.out.println("QT suited");
				break;
			}
		
			if(analizer.threeToStrType3()){ // 27 - 3 to a straight flush type 3
				//System.out.println("3 to straight flush type 3");
				break;
			}
			
			if(analizer.C1C2Unsuited(CardValue.JACK, CardValue.KING)){ // 28 - KJ UnSuited
				//System.out.println("KJ Unsuited");
				break;
			}
			
			if(analizer.C1C2Unsuited(CardValue.QUEEN, CardValue.KING)){ // 28 - KQ UnSuited
				//System.out.println("KQ Unsuited");
				break;
			}
			
			if(analizer.NequalValueCards(1, CardValue.ACE)){ // 29 - ACE
				//System.out.println("ACE");
				break;
			}
			
			if(analizer.C1C2Suited(CardValue.KING, CardValue.TEN)){ // 30 - KT Suited
				//System.out.println("KT suited");
				break;
			}
			
			if(analizer.NHighCards(1)){ // 31 J or Q or K
				//System.out.println("Jack or Queen or King");
				break;
			}
			
			if(analizer.fourInStrWithNHighCards(0)){ // 32 - 4 inside straight with 0 high cards
				//System.out.println("4 inside straight with 0 high cards");
				break;
			}
			
			if(analizer.threeToFlushWithNHighCards(0)){ // 33 - 3 to flush with 0 HIGH Cards
				//System.out.println("3 to flush with 1 HIGH Cards");
				break;
			}
			
			break;
			
		}
		
		return analizer.holdCards();
		
		
	}

}
