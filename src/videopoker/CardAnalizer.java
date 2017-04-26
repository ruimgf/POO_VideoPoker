package videopoker;

import cards.*;

public class CardAnalizer {
	
	int [][] countCard;
	int [] totalcount;
	int [] nSuit;
	Hand h;
	int [] IndexLastClass; // Important indexes used in last classification
	
	public CardAnalizer(Hand h){
		this.h = h;
		
		countCard = new int[4][14];
		totalcount = new int[14];
		nSuit = new int[4];
		
		IndexLastClass = new int[5];
		
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
	
	private void resetIndexLastClass(){
		for (int i = 0; i < IndexLastClass.length; i++) {
			IndexLastClass[i] = -1;
		}
	}
	
	private void saveIndex(int index){
		for (int i = 0; i < IndexLastClass.length; i++) {
			if(IndexLastClass[i] == -1){
				IndexLastClass[i] = index;
				break;
			}
		}
	}
	
	void printHoldIndex(){
		java.util.Arrays.sort(IndexLastClass);
		for (int i = 0; i < IndexLastClass.length; i++) {
			if(IndexLastClass[i] != -1){
				System.out.print(IndexLastClass[i] + " ");
			}
		}
		System.out.println("");
	}
	
	boolean NStraight(int N){
		int countToStr = 0;
		resetIndexLastClass();
		for(int i=13;i>=4;i--){
			
			for(int j=i;j>i-5;j--){
				if(totalcount[j]!=0){
					for (int j2 = 0; j2 < 4; j2++) {
						if(countCard[j2][j] != -1){
							saveIndex(countCard[j2][j]);
						}
					}
					countToStr ++;
				}
			}
			if(countToStr>=N){
				return true;
			}
			countToStr = 0;
			resetIndexLastClass();
		}
		return false;
	}
	
	boolean NFlush(int N){
		resetIndexLastClass();
		for (int i = 0; i < 4; i++) {
			if(nSuit[i]==N){
				for (int j = 1; j < 14; j++) {
					if(countCard[i][j] != -1){
						saveIndex(countCard[i][j]);
					}
				}
				return true;
			}
			resetIndexLastClass();
		}
		return false;
	}
	
	boolean NtoStrFlush(int N){
		int counter = 0;
		resetIndexLastClass();
		for(int i=0;i < 4;i++){
			for(int k=13;k>=4;k--){
				for(int j=k;j>k-5;j--){
					if(countCard[i][j]!=-1){
						saveIndex(countCard[i][j]);
						counter ++;
					}
					
				}
				
				if(counter>=N){
					return true;
				}
				counter = 0;
				resetIndexLastClass();
			}
		
		}
		
		return false;
	}
	
	boolean NtoRoyalFlush(int N){
		int counter = 0;
		resetIndexLastClass();
		for(int i=0;i < 4;i++){
			
			for(int j=13;j>8;j--){
				if(countCard[i][j]!=-1){
					saveIndex(countCard[i][j]);
					counter ++;
				}
			}
			if(counter>=N){
				return true;
			}
			counter = 0;
			resetIndexLastClass();
		}
		return false;
	}
	
	boolean NequalValueCards(int N,CardValue v){
		resetIndexLastClass();
		if(totalcount[v.intValue()]>=N){
			for (int i = 0; i < 4; i++) {
				if(countCard[i][v.intValue()]!=-1)
					saveIndex(countCard[i][v.intValue()]);
			}
			return true;
		}
		return false;
	}
	
	boolean NequalValueCards(int N){
		resetIndexLastClass();
		for(int i = 0; i < totalcount.length; i++) {
			if(totalcount[i]>=N){
				for (int j = 0; j < 4; j++) {
					if(countCard[j][i]!=-1)
						saveIndex(countCard[j][i]);
				}
				return true;
			}
		}
		return false;
	}
	
	boolean fullHouse(){
		boolean three = false;
		boolean two = false;
		resetIndexLastClass();
		for (int i = 0; i < totalcount.length; i++) {
			if(totalcount[i]==3){
				three = true;
				for (int j = 0; j < 4; j++) {
					if (countCard[j][i]!=-1) {
						saveIndex(countCard[j][i]);
					}	
				}
			}
			if(totalcount[i]==2){
				for (int j = 0; j < 4; j++) {
					if (countCard[j][i]!=-1) {
						saveIndex(countCard[j][i]);
					}	
				}
				two = true;
			}
		
		}
		
		if(three && two){
			return true;
		}
		
		return false;
	}
	
	boolean NHighCards(int N){
		int count=0;
		resetIndexLastClass();
		for(int i = 10; i < totalcount.length; i++) {
			if(totalcount[i]!= 0){
				count = count + totalcount[i];
				for (int j = 0; j < 4; j++) {
					if(countCard[j][i]!=-1)
						saveIndex(countCard[j][i]);
				}
			}
				
		}
		if(count>=N){
			return true;
		}
		return false;
	}
	
	boolean HighPair(){
		resetIndexLastClass();
		for(int i = 10; i < totalcount.length; i++) {
			if(totalcount[i]==2){
				for (int j = 0; j < 4; j++) {
					if(countCard[j][i]!=-1)
						saveIndex(countCard[j][i]);
				}
				return true;
			}
		}
		return false;
	}
	
	boolean LowPair(){
		resetIndexLastClass();
		for(int i = 1; i < 10; i++) {
			if(totalcount[i]==2){
				for (int j = 0; j < 4; j++) {
					if(countCard[j][i]!=-1)
						saveIndex(countCard[j][i]);
				}
				return true;
			}
		}
		return false;
	}
	
	boolean InsideStraight(){
		resetIndexLastClass();
		int counter = 0;
		for(int i=13;i>=4;i--){
			
			for(int j=i;j>i-5;j--){
				if(totalcount[j]!=0){
					for (int k = 0; k < 4; k++) {
						if(countCard[k][j]!=-1){
							saveIndex(countCard[k][j]);
						}
					}
					counter ++;
				}
			}
			
			if(counter == 4 && (totalcount[i] !=  0 && totalcount[i-4] != 0)){
				return true;
			}
			counter = 0;
			resetIndexLastClass();
		}
		return false;
	}
	
	boolean OutsideStraight(){
		int counter = 0;
		resetIndexLastClass();
		for(int i=12;i>=5;i--){ // Windows begin on King and end on Six
			for(int j=i;j>i-5;j--){
				if(totalcount[j]!=0){
					for (int k = 0; k < 4; k++) {
						if(countCard[k][j]!=-1)
							saveIndex(countCard[k][j]);
					}
					counter ++;
				}
			}
			if(counter == 4 && (totalcount[i]== 0 || totalcount[i-4] == 0)){
				return true;
			}
			resetIndexLastClass();
			counter = 0;
		}
		return false;
	}
	
	boolean TwoPair(){
		int counter = 0;
		resetIndexLastClass();
		for (int i = 0; i < totalcount.length - 1; i++) {
			if(totalcount[i]==2){
				for (int k = 0; k < 4; k++) {
					if(countCard[k][i]!=-1)
						saveIndex(countCard[k][i]);
				}
				counter += 1;
			}
			
		}
		
		if(counter == 2){
			return true;
		}
		resetIndexLastClass();
		return false;
		
	}
	
	boolean AKQJunsuited(){
		int JIndex  = CardValue.JACK.intValue();
		int QIndex  = CardValue.QUEEN.intValue();
		int KIndex  = CardValue.KING.intValue();
		int AIndex  = CardValue.ACE.intValue();
		
		if(totalcount[JIndex] == 0 || totalcount[QIndex] == 0 || totalcount[KIndex]==0 || totalcount[AIndex]==0 ){
			return false;
		}
		
		if(NtoRoyalFlush(4)){ // If i have a A K Q J of same suited i have a 4 to Royal Flush 
			return false;
		}
		
		resetIndexLastClass();
		
		for (int i = 10; i < 14; i++) {
				for (int k = 0; k < 4; k++) {
					if(countCard[k][i]!=-1){
						saveIndex(countCard[k][i]);
						break; 
					}
				}
		}
		return true;	
	}
	
	boolean KQJunsuited(){
		int JIndex  = CardValue.JACK.intValue();
		int QIndex  = CardValue.QUEEN.intValue();
		int KIndex  = CardValue.KING.intValue();
		
		
		if(totalcount[JIndex] == 0 || totalcount[QIndex] == 0 || totalcount[KIndex]==0){
			return false;
		}
		
		if(NtoRoyalFlush(3)){ // If i have a K Q J of same suited i have a 4 to Royal Flush 
			return false;
		}
		
		resetIndexLastClass();
		
		for (int i = 10; i < 13; i++) {
				for (int k = 0; k < 4; k++) {
					if(countCard[k][i]!=-1){
						saveIndex(countCard[k][i]);
						break; 
					}
				}
		}
		return true;	
	}
	
	private boolean isaHighCard(Card c){
		if(c.getValue().intValue() > CardValue.TEN.intValue())
			return true;
		return false;
	}
	
	private int nrOfHighCardsOnIndex(){
		int counter =0;
		for(int i=0; i < IndexLastClass.length; i++){
			if(IndexLastClass[i]==-1)
				break;
			if(isaHighCard(h.getCardN(IndexLastClass[i]))){
				counter++;
			}
		}
		return counter;
	}
	boolean threeToStrType1(){
		
		if(!NtoStrFlush(3)){
			return false;
		}
		
		if(nrOfHighCardsOnIndex()>=2){
			return true;
		}
		resetIndexLastClass();
		return false;
	}
	// isto ainda não está be,
	boolean threeToStrType2(){
		
		if(!NtoStrFlush(3)){
			return false;
		}
		
		if(nrOfHighCardsOnIndex()>=1){
			return true;
		}
		resetIndexLastClass();
		return false;
	}
	
	boolean threeToStrType3(){
		
		if(!NtoStrFlush(3)){
			return false;
		}
		
		if(nrOfHighCardsOnIndex()==0){
			return true;
		}
		resetIndexLastClass();
		return false;
	}
	
	
	boolean fourInStrWithNHighCards(int N){
		
		if(!InsideStraight()){
			return false;
		}
		if(nrOfHighCardsOnIndex()>=N){
			return true;
		}
		resetIndexLastClass();
		return false;
	}
	
	boolean threeToFlushWithNHighCards(int N){
		
		if(!NFlush(3)){
			return false;
		}
		if(nrOfHighCardsOnIndex()>=N){
			return true;
		}
		resetIndexLastClass();
		return false;
	}
	
	boolean C1C2Suited(CardValue c1, CardValue c2){
		int c1Index = c1.intValue();
		int c2Index = c2.intValue();
		resetIndexLastClass();
		
		for(int i=0;i<4;i++){
			if(countCard[i][c1Index] != -1 && countCard[i][c2Index] != -1){
				saveIndex(countCard[i][c1Index]);
				saveIndex(countCard[i][c2Index]);
				return true;
			}
		}
		resetIndexLastClass();
		return false;
	}
	
	boolean C1C2Unsuited(CardValue c1, CardValue c2){
		int c1Index = c1.intValue();
		int c2Index = c2.intValue();
		resetIndexLastClass();
		
		if(totalcount[c1Index] == 0 || totalcount[c2Index] == 0)
			return false;
		
		for(int i=0;i<4;i++){
			if(countCard[i][c1Index] != -1){
				saveIndex(countCard[i][c1Index]);
			}
			if(countCard[i][c2Index] != -1){
				saveIndex(countCard[i][c2Index]);
			}
		}
		return true;
	}
	
	boolean twoSuitedHighCards(){
		resetIndexLastClass();
		int counter = 0;
		for(int i=0;i<4;i++){
			for(int j=10;j<14;j++){
				if(countCard[i][j] != -1 && countCard[i][j] != -1){
					saveIndex(countCard[i][j]);
					counter ++ ;
				}
			}
			if(counter == 2){
				return true;
			}
			counter = 0;
			resetIndexLastClass();
		}
		return false;
	}
	
	public static void main(String[] args) {
		CardAnalizer analise;
		Hand h;
		
		h = new Hand();
		
		h.mycards[0] = new Card(Suit.HEARTS,CardValue.ACE);
		h.mycards[1] = new Card(Suit.HEARTS,CardValue.KING);
		h.mycards[2] = new Card(Suit.HEARTS,CardValue.QUEEN);
		h.mycards[3] = new Card(Suit.HEARTS,CardValue.JACK);
		h.mycards[4] = new Card(Suit.HEARTS,CardValue.TEN);
		
		for (int i = 0; i < h.mycards.length; i++) {
			System.out.print(h.mycards[i] + " ");
			
		}
		System.out.println("");
		analise = new CardAnalizer(h);
		
		if(analise.NFlush(5)){
			System.out.println("N Flush");
			analise.printHoldIndex();
		}else{
			System.out.println("No N Flush");
		}
		
		if(analise.NStraight(5)){
			System.out.println("N Straight");
			analise.printHoldIndex();
		}else{
			System.out.println("No N Straight");
		}
		
		if(analise.NtoRoyalFlush(5)){
			System.out.println("Royal Flush");
			analise.printHoldIndex();
		}else{
			System.out.println("No Royal Flush");
		}
		
		if(analise.NtoStrFlush(5)){
			System.out.println("Straight Flush");
			analise.printHoldIndex();
		}else{
			System.out.println("No Straigh Flush");
		}
		
		if(analise.NequalValueCards(1,CardValue.ACE)){
			System.out.println("ONe ace");
			analise.printHoldIndex();
		}else{
			System.out.println("No ace");
		}
	
		if(analise.NHighCards(1)){
			System.out.println("High Cards");
			analise.printHoldIndex();
		}else{
			System.out.println("No High Cards");
		}
	
		if(analise.HighPair()){
			System.out.println("High Pair");
			analise.printHoldIndex();
		}else{
			System.out.println("No High Pair");
		}
		
		if(analise.LowPair()){
			System.out.println("Low Pair");
			analise.printHoldIndex();
		}else{
			System.out.println("No Low Pair");
		}
		
		
		if(analise.OutsideStraight()){
			System.out.println("Out side Straight");
			analise.printHoldIndex();
		}else{
			System.out.println("No Outside Straight");
		}
		
		if(analise.InsideStraight()){
			analise.printHoldIndex();
			System.out.println("inside Straight");
		}else{
			System.out.println("No Inside Straight");
		}
		
		if(analise.TwoPair()){
			analise.printHoldIndex();
			System.out.println("Two Pair");
		}else{
			System.out.println("No Two Pair");
		}
		
	}
	
}
