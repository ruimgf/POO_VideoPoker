package videopoker;

import cards.*;

public class CardAnalizer {
	
	int [][] countCard;
	int [] totalcount;
	int [] nSuit;
	Hand h;
	int [] lastIndexCarts; // Important indexes used in last classification
	
	public CardAnalizer(Hand h){
		this.h = h;
		
		countCard = new int[4][14];
		totalcount = new int[14];
		nSuit = new int[4];
		
		int indexSuit;
		int indexValue;
		
		for(int i=0;i<4;i++){
			for(int j=0;j<14;j++){
				countCard[i][j]  = -1 ;
			}
			
		}
		
		for(int i=0;i<4;i++){
			nSuit[i] = 0;
		}
		
		for(int i=0; i<h.length; i++){
			Card temp = h.getCardN(i);
			
			switch (temp.getSuit()) {
			case DIAMONS:
				indexSuit = 0;
				break;
			case CLUBS:
				indexSuit = 1;
				break;
			case HEARTS :
				indexSuit = 2;
				break;
			case SPADES:
				indexSuit = 3;
				break;
			default:
				indexSuit = -1;
				break;
			}
			indexValue = temp.getValue().intValue();
			countCard[indexSuit][indexValue]=i;
			nSuit[indexSuit] ++;
			if(indexValue==13){ // ace 
				countCard[indexSuit][0]=i;
			}
		}
		
		for(int i=0;i < 14;i++){
			totalcount[i] = 0;
		}
		
		for(int i=0;i < 14;i++){
			for(int k=0;k<4;k++){
				if(countCard[k][i] != -1){
					totalcount[i]++;
				}
			}
			
			
		}
	}
	
	
	boolean NStraight(int N){
		int countToStr = 0;
	
		for(int i=13;i>=4;i--){
			for(int j=i;j>i-5;j--){
				if(totalcount[j]!=0){
					countToStr ++;
				}
			}
			if(countToStr>=N){
				return true;
			}
			countToStr = 0;
		}
		return false;
	}
	
	boolean NFlush(int N){
		for (int i = 0; i < 4; i++) {
			if(nSuit[i]==N){
				return true;
			}
		}
		return false;
	}
	
	boolean NtoStrFlush(int N){
		int counter = 0;
		for(int i=0;i < 4;i++){
			for(int k=13;k>=4;k--){
				for(int j=k;j>k-5;j--){
					if(countCard[i][j]!=-1){
						counter ++;
					}
					
				}
				
				if(counter>=N){
					return true;
				}
				counter = 0;
			}
		}
		
		return false;
	}
	
	boolean NtoRoyalFlush(int N){
		int counter = 0;
		for(int i=0;i < 4;i++){
			
			for(int j=13;j>8;j--){
				if(countCard[i][j]!=-1){
					counter ++;
				}
			}
			if(counter>=N){
				return true;
			}
			counter = 0;
			
		}
		return false;
	}
	
	boolean NequalValueCards(int N,CardValue v){
		if(totalcount[v.intValue()]>=N){
			return true;
		}
		return false;
	}
	
	boolean NequalValueCards(int N){
		for(int i = 0; i < totalcount.length; i++) {
			if(totalcount[i]>=N){
				return true;
			}
		}
		return false;
	}
	
	boolean NHighCards(int N){
		int count=0;
		for(int i = 10; i < totalcount.length; i++) {
			count = count + totalcount[i];	
		}
		if(count>=N){
			return true;
		}
		return false;
	}
	
	boolean HighPair(){
		
		for(int i = 10; i < totalcount.length; i++) {
			if(totalcount[i]==2){
				return true;
			}
		}
		return false;
	}
	
	boolean LowPair(){
		for(int i = 1; i < 10; i++) {
			if(totalcount[i]==2){
				return true;
			}
		}
		return false;
	}
	
	boolean InsideStraight(){
		int counter = 0;
		for(int i=13;i>=4;i--){
			for(int j=i;j>i-5;j--){
				if(totalcount[j]!=0){
					counter ++;
				}
			}
			
			if(counter == 4 && (totalcount[i] !=  0 && totalcount[i-4] != 0)){
				return true;
			}
			counter = 0;
		}
		return false;
	}
	
	boolean OutsideStraight(){
		int counter = 0;
		
		for(int i=13;i>=4;i--){
			for(int j=i;j>i-5;j--){
				if(totalcount[j]!=0){
					counter ++;
				}
			}
			if(counter == 4 && (totalcount[i]== 0 || totalcount[i-4] == 0)){
				return true;
			}
			counter = 0;
		}
		return false;
	}
	
	boolean TwoPair(){
		int counter = 0;
		for (int i = 0; i < totalcount.length - 1; i++) {
			if(totalcount[i]==2){
				counter += 1;
			}
			
		}
		
		if(counter == 2){
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		CardAnalizer analise;
		Hand h;
		
		h = new Hand();
		
		h.mycards[0] = new Card(Suit.HEARTS,CardValue.SEVEN);
		h.mycards[1] = new Card(Suit.CLUBS,CardValue.FIVE);
		h.mycards[2] = new Card(Suit.SPADES,CardValue.TWO);
		h.mycards[3] = new Card(Suit.CLUBS,CardValue.NINE);
		h.mycards[4] = new Card(Suit.HEARTS,CardValue.EIGTH);
		
		analise = new CardAnalizer(h);
		
		if(analise.NFlush(5)){
			System.out.println("N Flush");
		}else{
			System.out.println("No N Flush");
		}
		
		if(analise.NStraight(5)){
			System.out.println("N Straight");
		}else{
			System.out.println("No N Straight");
		}
		
		if(analise.NtoRoyalFlush(5)){
			System.out.println("Royal Flush");
		}else{
			System.out.println("No Royal Flush");
		}
		
		if(analise.NtoStrFlush(5)){
			System.out.println("Straight Flush");
		}else{
			System.out.println("No Straigh Flush");
		}
		
		if(analise.NequalValueCards(2,CardValue.ACE)){
			System.out.println("ONe ace");
		}else{
			System.out.println("No ace");
		}
	
		if(analise.NHighCards(1)){
			System.out.println("High Cards");
		}else{
			System.out.println("No High Cards");
		}
	
		if(analise.HighPair()){
			System.out.println("High Pair");
		}else{
			System.out.println("No High Pair");
		}
		
		if(analise.LowPair()){
			System.out.println("Low Pair");
		}else{
			System.out.println("No Low Pair");
		}
		
		
		if(analise.OutsideStraight()){
			System.out.println("Out side Straight");
		}else{
			System.out.println("No Outside Straight");
		}
		
		if(analise.InsideStraight()){
			System.out.println("inside Straight");
		}else{
			System.out.println("No Inside Straight");
		}
		
		if(analise.TwoPair()){
			System.out.println("Two Pair");
		}else{
			System.out.println("No Two Pair");
		}
		
	}
	
}
