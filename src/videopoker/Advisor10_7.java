package videopoker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cards.Card;
import cards.CardValue;

public class Advisor10_7 {
	
	static void getAdvise(Hand h){
		CardAnalizer analise = new CardAnalizer(h);
		
		if(analise.NtoRoyalFlush(5)){ // 1 - Royal Flush
			System.out.println("Royal Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.NtoStrFlush(5)){ // 1 - Straight Flush
			System.out.println("Straight Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.NequalValueCards(4)){// 1 - Four of a Kind
			System.out.println("4 of a Kind");
			analise.printHoldIndex(); return;
		}
		
		if(analise.NtoRoyalFlush(4)){ // 2 - 4 to Royal Flush
			System.out.println("4 Royal Flush");
			analise.printHoldIndex(); return;
		}
		
		if(analise.NequalValueCards(3,CardValue.ACE)){ // 3 - Three Aces
			System.out.println("Three Aces");
			analise.printHoldIndex(); return;
		}
		
		if(analise.NFlush(5)){ // 4 - Flush
			System.out.println("5 Flush");
			analise.printHoldIndex(); return;
		}
		
		if(analise.NStraight(5)){ // 4 - Straight
			System.out.println("5 Straight");
			analise.printHoldIndex(); return;
		}
		
		if(analise.fullHouse()){ // 4- Full House
			System.out.println("Full House");
			analise.printHoldIndex(); return;
		} 
		
		if(analise.NequalValueCards(3)){ // 5 - Three of a kind
			System.out.println("Three of a kind");
			analise.printHoldIndex(); return;
		}
		if(analise.NtoStrFlush(4)){ // 6 - 4 to Straight Flush
			System.out.println("4 to Straight Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.TwoPair()){ // 7 - Two Pair
			System.out.println("Two Pair");
			analise.printHoldIndex(); return;
		}
		if(analise.HighPair()){ // 8 -  High Pair
			System.out.println("High Pair");
			analise.printHoldIndex(); return;
		}
		if(analise.NFlush(4)){ // 9 - 4 to a flush
			System.out.println("4 to Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.NtoRoyalFlush(3)){ // 10 - 3 to Royal Flush
			System.out.println("3 to Royal Flush");
			analise.printHoldIndex(); return;
		}
		if(analise.OutsideStraight()){ // 11 - 4 to an outside straight
			System.out.println("4 to outside Straight");
			analise.printHoldIndex(); return;
		}
		if(analise.LowPair()){ // 12 - Low Pair
			System.out.println("Low Pair");
			analise.printHoldIndex(); return;
		}
	
		if(analise.AKQJunsuited()){ // 13 - AKQJ unsuited
			System.out.println("AKQJ unsuited");
			analise.printHoldIndex(); return;
		}
		
		if(analise.threeToStrType1()){ // 14 - 3 to straight type 1
			System.out.println("Three to a straight (type 1)");
			analise.printHoldIndex(); return;
		}
		
		if(analise.fourInStrWithNHighCards(3)){ // 15 - 4 inside straight with 3 high cards
			System.out.println("4 inside straight with 3 high cards");
			analise.printHoldIndex(); return;
		}
		
		if(analise.C1C2Suited(CardValue.QUEEN, CardValue.JACK)){ // 16 - QJ Suited
			System.out.println("QJ suited");
			analise.printHoldIndex(); return;
		}
	
		if(analise.threeToFlushWithNHighCards(2)){ // 17 - 3 to flush with 2 HIGH Cards
			System.out.println("3 to flush with 2 HIGH Cards");
			analise.printHoldIndex(); return;
		}
		
		if(analise.twoSuitedHighCards()){  // 18 - 2 Suited HIGH Cards
			System.out.println("2 Suited High Cards");
			analise.printHoldIndex(); return;
		}
	
		
		if(analise.fourInStrWithNHighCards(2)){ // 19 - 4 inside straight with 2 high cards
			System.out.println("4 inside straight with 2 high cards");
			analise.printHoldIndex(); return;
		}
		// TODO 
		/* 3 to str type 2
		if(){ // 4 inside straight with 2 high cards
			System.out.println("4 inside straight with 2 high cards");
			analise.printHoldIndex(); return;
		}
		*/
		
		if(analise.fourInStrWithNHighCards(1)){ // 21 - 4 inside straight with 1 high cards
			System.out.println("4 inside straight with 1 high cards");
			analise.printHoldIndex(); return;
		}
	
		if(analise.KQJunsuited()){ // 22 - KQJ unsuited
			System.out.println("KQJ unsuited");
			analise.printHoldIndex(); return;
		}
		
		if(analise.C1C2Suited(CardValue.JACK, CardValue.TEN)){ // 23 - JT Suited
			System.out.println("JT suited");
			analise.printHoldIndex(); return;
		}
		
		if(analise.C1C2Unsuited(CardValue.QUEEN, CardValue.JACK)){ // 24 - QJ UnSuited
			System.out.println("QJ Unsuited");
			analise.printHoldIndex(); return;
		}
		
		if(analise.threeToFlushWithNHighCards(1)){ // 25 - 3 to flush with 1 HIGH Cards
			System.out.println("3 to flush with 1 HIGH Cards");
			analise.printHoldIndex(); return;
		}
		
		if(analise.C1C2Suited(CardValue.QUEEN, CardValue.TEN)){ // 26 - QT Suited
			System.out.println("QT suited");
			analise.printHoldIndex(); return;
		}
	
		if(analise.threeToStrType3()){ // 27 - 3 to a straight flush type 3
			System.out.println("3 to straight flush type 3");
			analise.printHoldIndex(); return;
		}
		
		if(analise.C1C2Unsuited(CardValue.JACK, CardValue.KING)){ // 28 - KJ UnSuited
			System.out.println("KJ Unsuited");
			analise.printHoldIndex(); return;
		}
		
		if(analise.C1C2Unsuited(CardValue.QUEEN, CardValue.KING)){ // 28 - KQ UnSuited
			System.out.println("KQ Unsuited");
			analise.printHoldIndex(); return;
		}
		
		if(analise.NequalValueCards(1, CardValue.ACE)){ // 29 - ACE
			System.out.println("ACE");
			analise.printHoldIndex(); return;
		}
		
		if(analise.C1C2Suited(CardValue.KING, CardValue.TEN)){ // 30 - KT Suited
			System.out.println("KT suited");
			analise.printHoldIndex(); return;
		}
		
		if(analise.NHighCards(1)){ // 31 J or Q or K
			System.out.println("Jack or Queen or King");
			analise.printHoldIndex(); return;
		}
		
		if(analise.fourInStrWithNHighCards(0)){ // 32 - 4 inside straight with 0 high cards
			System.out.println("4 inside straight with 0 high cards");
			analise.printHoldIndex(); return;
		}
		
		if(analise.threeToFlushWithNHighCards(0)){ // 33 - 3 to flush with 0 HIGH Cards
			System.out.println("3 to flush with 1 HIGH Cards");
			analise.printHoldIndex(); return;
		}
		
		System.out.println("Discard Everything");
		
	}
	
	
	public static void main(String[] args) throws IOException {
		Hand h;
		h = new Hand();
		
		
		// Open the file
		FileInputStream fstream = new FileInputStream("/Users/rui/code/pooproject/src/videopoker/cardtestadvisor.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		int counter = 1;
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  
			String[] parts = strLine.split(" ");
			for (int i = 0; i < parts.length; i++) {
				h.mycards[i] = new Card(parts[i]);
				
			} 
			System.out.print(counter + ": " );
			Advisor10_7.getAdvise(h);
			counter ++;
		}

		//Close the input stream
		br.close();
	
		System.out.println("");
		
		
	}

}
