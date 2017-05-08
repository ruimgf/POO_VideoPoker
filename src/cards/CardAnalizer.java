package cards;

import java.util.Arrays;
import java.util.Comparator;
/**
 * Class that permits analyze one HandCards 
 * @author rui
 *
 */
public class CardAnalizer {
	// index i,j of vector count is -1 if card j of suit i don't exist in h or is equal to index of card i,j on hand
	private int [][] indexCard;
	// total count of card of value i on h
	private int [] totalcount;
	// number of cards of suit n on h
	private int [] nSuit;
	// analised cards
	private HandCards h;
	private int [] IndexLastClass; // Important indexes used in last classification
	
	
	/**
	 * Construct a card analyzer, make all counts to make more easy to analyze
	 * 
	 * 
	 * @param h - hand to analyze
	 */
	public CardAnalizer(HandCards h){
		this.h = h;
		
		indexCard = new int[4][14];
		totalcount = new int[14];
		nSuit = new int[4];
		
		IndexLastClass = new int[5];
		
		int indexSuit;
		int indexValue;
		
		Arrays.fill(indexCard,-1);
		
		
		for(int i=0; i<h.length(); i++){
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
			indexCard[indexSuit][indexValue]=i;
			nSuit[indexSuit] ++;
			if(indexValue==13){ // ace 
				indexCard[indexSuit][0]=i;
			}
		}
		
		for(int i=0;i < 14;i++){
			for(int k=0;k<4;k++){
				if(indexCard[k][i] != -1){
					totalcount[i]++;
				}
			}
			
			
		}
	}

	/**
	 * Reinitialize vector that save important indexes
	 */
	private void resetIndexLastClass(){
		for (int i = 0; i < IndexLastClass.length; i++) {
			IndexLastClass[i] = -1;
		}
	}
	
	/**
	 * Save an important card index
	 * @param index - index to save
	 */
	private void saveIndex(int index){
		for (int i = 0; i < IndexLastClass.length; i++) {
			if(IndexLastClass[i] == -1){
				IndexLastClass[i] = index;
				break;
			}
		}
	}
	
	/**
	 * Debug Function - print important indexes of last classification
	 */
	public void printHoldIndex(){
		java.util.Arrays.sort(IndexLastClass);
		for (int i = 0; i < IndexLastClass.length; i++) {
			if(IndexLastClass[i] != -1){
				System.out.print(IndexLastClass[i] + " ");
			}
		}
		System.out.println("");
	}
	/**
	 * Function that analysis if there is N cards to a Straight
	 * 
	 * Example: If i want to know if i have a straight, make N = 5
	 * 
	 * 
	 * @param N - number of cards that i have to a Straight
	 * @return true if is a NStraight
	 */
	public boolean NStraight(int N){
		int countToStr = 0;
		resetIndexLastClass();
		for(int i=13;i>=4;i--){ // iterate over an window of length 5 
			
			for(int j=i;j>i-5;j--){ 
				if(totalcount[j]!=0){
					for (int j2 = 0; j2 < 4; j2++) {
						if(indexCard[j2][j] != -1){ // look for card index on Hand
							saveIndex(indexCard[j2][j]);
						}
					}
					countToStr ++;
				}
			}
			if(countToStr>=N){ // if there are N cards on window, we have a straight
				return true;
			}
			countToStr = 0;
			resetIndexLastClass();
		}
		return false;
	}
	
	/**
	 * Function that analysis if there is N cards to a Flush
	 * 
	 * Example: If i want to know if i have a Flush, make N = 5
	 * 
	 * 
	 * @param N - number of cards that i have to a flush
	 * @return true if is a NFlush
	 */
	public boolean NFlush(int N){
		resetIndexLastClass();
		for (int i = 0; i < 4; i++) {
			if(nSuit[i]==N){
				for (int j = 1; j < 14; j++) {
					if(indexCard[i][j] != -1){ // look for card index
						saveIndex(indexCard[i][j]);
					}
				}
				return true;
			}
			resetIndexLastClass();
		}
		return false;
	}
	/**
	 * Function that analysis if there is N cards to a Straight Flush
	 * 
	 * Example: If i want to know if i have a straight Flush, make N = 5
	 * 
	 * 
	 * @param N - number of cards that i have to a Straight Flush
	 * @return true if is a NStrFlush
	 */
	public boolean NtoStrFlush(int N){
		int counter = 0;
		resetIndexLastClass();
		for(int i=0;i < 4;i++){ // iterate over suits
			for(int k=13;k>=4;k--){
				for(int j=k;j>k-5;j--){ // iterate of a window with length 5
					if(indexCard[i][j]!=-1){
						saveIndex(indexCard[i][j]);
						
						counter ++;
					}
					
				}
				
				if(counter==N){ // if there are N cards on window, we have a straight flush
					return true;
				}
				counter = 0;
				resetIndexLastClass();
			}
		
		}
		
		return false;
	}
	/**
	 * Function that analysis if there is N cards to a Royal Flush
	 * 
	 * Example: If i want to know if i have a Royal Flush, make N = 5
	 * 
	 * 
	 * @param N - number of cards that i have to a Royal Flush
	 * @return true if is a NtoRoyalFlush
	 */
	public boolean NtoRoyalFlush(int N){
		int counter = 0;
		resetIndexLastClass();
		for(int i=0;i < 4;i++){
			
			for(int j=13;j>8;j--){
				if(indexCard[i][j]!=-1){
					saveIndex(indexCard[i][j]);
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

	
	/**
	 * Function that analysis if there are N cards of value v
	 * 
	 * 
	 * @param N - Number of cards
	 * @param v - value of card
	 * @return true if there is a N equal value cards
	 */
	public boolean NequalValueCards(int N,CardValue v){
		resetIndexLastClass();
		if(totalcount[v.intValue()]>=N){
			for (int i = 0; i < 4; i++) {
				if(indexCard[i][v.intValue()]!=-1)
					saveIndex(indexCard[i][v.intValue()]);
			}
			return true;
		}
		return false;
	}
	/**
	 * Function that analysis if there are N cards of same value
	 * @param N - Number of cards
	 * @return true if there is a N equal value cards
	 */
	public boolean NequalValueCards(int N){
		resetIndexLastClass();
		for(int i = 0; i < totalcount.length; i++) {
			if(totalcount[i]>=N){
				for (int j = 0; j < 4; j++) {
					if(indexCard[j][i]!=-1)
						saveIndex(indexCard[j][i]);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Function that analyze if there are a Full House
	 * @return true if there is a Full House
	 */
	public boolean fullHouse(){
		boolean three = false;
		boolean two = false;
		resetIndexLastClass();
		for (int i = 0; i < totalcount.length; i++) {
			if(totalcount[i]==3){
				three = true;
				for (int j = 0; j < 4; j++) {
					if (indexCard[j][i]!=-1) {
						saveIndex(indexCard[j][i]);
					}	
				}
			}
			if(totalcount[i]==2){
				for (int j = 0; j < 4; j++) {
					if (indexCard[j][i]!=-1) {
						saveIndex(indexCard[j][i]);
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
	/**
	 * Function that analyze if there are N high cards on HandCards h
	 * @param N Number of High Cards
	 * @return true if there is a N High Cards
	 */
	public boolean NHighCards(int N){
		int count=0;
		resetIndexLastClass();
		for(int i = 10; i < totalcount.length; i++) {
			if(totalcount[i]!= 0){
				count = count + totalcount[i];
				for (int j = 0; j < 4; j++) {
					if(indexCard[j][i]!=-1)
						saveIndex(indexCard[j][i]);
				}
			}
				
		}
		if(count>=N){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Function that analyze if there is a high pair
	 * @return true if there is a High Pair
	 */
	public boolean HighPair(){
		resetIndexLastClass();
		for(int i = 10; i < totalcount.length; i++) {
			if(totalcount[i]==2){
				for (int j = 0; j < 4; j++) {
					if(indexCard[j][i]!=-1)
						saveIndex(indexCard[j][i]);
				}
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Function that analyze if there is a low pair
	 * @return true if there is a Low Pair
	 */
	public boolean LowPair(){
		resetIndexLastClass();
		for(int i = 1; i < 10; i++) {
			if(totalcount[i]==2){
				for (int j = 0; j < 4; j++) {
					if(indexCard[j][i]!=-1)
						saveIndex(indexCard[j][i]);
				}
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Function that analyze if i have a Inside Straight
	 * @return true if there is a Inside Straight
	 */
	public boolean InsideStraight(){
		resetIndexLastClass();
		int counter = 0;
		for(int i=13;i>=4;i--){
			
			for(int j=i;j>i-5;j--){
				if(totalcount[j]!=0){
					for (int k = 0; k < 4; k++) {
						if(indexCard[k][j]!=-1){
							saveIndex(indexCard[k][j]);
							
						}
					}
					counter ++;
				}
			}
			
			if(counter == 4){
				if (totalcount[i] !=  0 && totalcount[i-4] != 0){ // if cards of outside are present
					return true;
				}else{ // case AKQJ and A234
					if(i==13 || i==4){
						return true;
					}
				}
			}
			counter = 0;
			resetIndexLastClass();
		}
		return false;
	}
	
	
	/**
	 * Function that analyze if i have a OutSide Straight
	 * @return true if there is an OutSideStraight
	 */
	public boolean OutsideStraight(){
		int counter = 0;
		resetIndexLastClass();
		for(int i=12;i>=5;i--){ // Windows begin on King and end on Six
			for(int j=i;j>i-5;j--){
				if(totalcount[j]!=0){
					for (int k = 0; k < 4; k++) {
						if(indexCard[k][j]!=-1)
							saveIndex(indexCard[k][j]);
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
	/**
	 * Function that analyze if i have a two Pair
	 * @return true if there is a Two Pair
	 */
	public boolean TwoPair(){
		int counter = 0;
		resetIndexLastClass();
		for (int i = 0; i < totalcount.length - 1; i++) {
			if(totalcount[i]==2){
				for (int k = 0; k < 4; k++) {
					if(indexCard[k][i]!=-1)
						saveIndex(indexCard[k][i]);
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
	
	
	/**
	 * Function that analyze if i have a AKQJ suited
	 * @return true if there is a AKQJ suited
	 */
	public boolean AKQJunsuited(){
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
					if(indexCard[k][i]!=-1){
						saveIndex(indexCard[k][i]);
						break; 
					}
				}
		}
		return true;	
	}
	/**
	 * Function that analyze if i have a KQJ unsuited
	 * @return true if there is a KQJ unsuited
	 */
	public boolean KQJunsuited(){
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
					if(indexCard[k][i]!=-1){
						saveIndex(indexCard[k][i]);
						break; 
					}
				}
		}
		return true;	
	}
	
	
	/**
	 * Function that sees if a card is an High Card
	 * @param c
	 * @return true if there is a HighCard
	 */
	private boolean isaHighCard(Card c){
		if(c.getValue().intValue() > CardValue.TEN.intValue())
			return true;
		return false;
	}
	
	
	/**
	 * Function that count number of High Cards in vector IndexLastClass
	 * @return number of High cards on Index
	 */
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
	
	/**
	 * Function to see type of Straight flush 
	 * @return type of Straight Flush or -1 in case of no Straight Flush
	 */
	private int threeToStrType(){
		Card[] c  = new Card[3]; 
		
		
		if(!NtoStrFlush(3)){
			return -1;
		}
		
		
		int count=0;
		for (int i = 0; i < IndexLastClass.length; i++) {
			if(IndexLastClass[i]!=-1){
				c[count] = h.getCardN(IndexLastClass[i]);
				count++;
			}
		}
		
		Arrays.sort(c, new Comparator<Card>() {
	        @Override
	        public int compare(Card c1, Card c2) {
	            return c1.value.intValue() - c2.value.intValue();
	        }
	    });
		// now we have the cards to straight in order
		
		if(c[2].value == CardValue.ACE){
			if(nrOfHighCardsOnIndex()<2){ // Ace Low // there are no more High cards than Ace
				return 2;
			}
		}
		if(c[0].value == CardValue.TWO && c[1].value == CardValue.THREE && c[2].value == CardValue.FOUR)
			return 2;
		
		// count gapes
		int gapes = 0;
		for (int i = 0; i < 2; i++) {
			if(c[i+1].value.intValue() > c[i].value.intValue() + 1){
				gapes = gapes + (c[i+1].value.intValue() - c[i].value.intValue() - 1) ;
			}
		}
		
		if(nrOfHighCardsOnIndex()>=gapes){
			return 1;
		}
		
		if(nrOfHighCardsOnIndex()==1){
			return 2;
		}
		
		return 3;
	}
	
	/**
	 * See if there is an Straight Flush type 1
	 * @return true if there is an Straight type 1
	 */
	public boolean threeToStrType1(){
		
		if(threeToStrType()==1){
			return true;
		}
		resetIndexLastClass();
		return false;

	}
	/**
	 * See if there is an Straight Flush type 2
	 * @return true if there is a Straight type 2
	 */
	public boolean threeToStrType2(){
		
		if(threeToStrType()==2){
			return true;
		}
		resetIndexLastClass();
		return false;
	}
	/**
	 * See if there is an Straight Flush type 3
	 * @return true if there is a Straight type 3
	 */
	public boolean threeToStrType3(){
		
		if(threeToStrType()==3){
			return true;
		}
		resetIndexLastClass();
		return false;
		
	}
	
	/**
	 * See if there is a 4 to inside Straight with N high Cards
	 * @param N - Number of High Cards
	 * @return true if there is a 4 to inside Straight
	 */
	public boolean fourInStrWithNHighCards(int N){
		
		if(!InsideStraight()){
			return false;
		}
		if(nrOfHighCardsOnIndex()>=N){
			return true;
		}
		resetIndexLastClass();
		return false;
	}
	
	/**
	 * See if there is a 3 to Flush with N high Cards
	 * @param N - Number of High cards
	 * @return true if there is a three to Flush with N High Cards
	 */
	public boolean threeToFlushWithNHighCards(int N){
		
		if(!NFlush(3)){
			return false;
		}
		if(nrOfHighCardsOnIndex()>=N){
			return true;
		}
		resetIndexLastClass();
		return false;
	}
	/**
	 * See if there the cards of value c1 and c2 of same suit are present
	 * @param c1 - Card 1
	 * @param c2 - Card 2
	 * @return true if there is an card C1 and C2 from same suit
	 */
	public boolean C1C2Suited(CardValue c1, CardValue c2){
		int c1Index = c1.intValue();
		int c2Index = c2.intValue();
		resetIndexLastClass();
		
		for(int i=0;i<4;i++){
			if(indexCard[i][c1Index] != -1 && indexCard[i][c2Index] != -1){
				saveIndex(indexCard[i][c1Index]);
				saveIndex(indexCard[i][c2Index]);
				return true;
			}
		}
		resetIndexLastClass();
		return false;
	}
	/**
	 * See if the cards of value c1 and c2 of with different suit are present
	 * @param c1 - Card 1
	 * @param c2 - Card 2
	 * @return true if cards of value c1 and c2 of with different suit are present
	 */
	public boolean C1C2Unsuited(CardValue c1, CardValue c2){
		int c1Index = c1.intValue();
		int c2Index = c2.intValue();
		resetIndexLastClass();
		
		if(totalcount[c1Index] == 0 || totalcount[c2Index] == 0)
			return false;
		
		for(int i=0;i<4;i++){
			if(indexCard[i][c1Index] != -1){
				saveIndex(indexCard[i][c1Index]);
			}
			if(indexCard[i][c2Index] != -1){
				saveIndex(indexCard[i][c2Index]);
			}
		}
		return true;
	}
	/**
	 * See if there are present two Suited high cards
	 * @return true if there is two Suited High Cards
	 */
	public boolean twoSuitedHighCards(){
		resetIndexLastClass();
		int counter = 0;
		for(int i=0;i<4;i++){
			for(int j=10;j<14;j++){
				if(indexCard[i][j] != -1 && indexCard[i][j] != -1){
					saveIndex(indexCard[i][j]);
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
	
	/**
	 * 
	 * Return index important in last Classification
	 * @return boolean vector with true in positions to hold
	 */
	public boolean[]holdCards(){
		boolean[] ret = new boolean[5];
		
		for (int i = 0; i < ret.length; i++) {
			ret[i] = false;
		}
		
		for (int i = 0; i < IndexLastClass.length; i++) {
			
			if(IndexLastClass[i]!=-1){
				ret[IndexLastClass[i]] = true;
			}
		
		}
		return ret;
	}
	
	public static void main(String[] args) {
		CardAnalizer analise;
		HandCards h;
		
		h = new HandCards();
		
		h.addCard(new Card(Suit.HEARTS,CardValue.ACE), 0);
		h.addCard(new Card(Suit.HEARTS,CardValue.TWO), 1);
		h.addCard(new Card(Suit.HEARTS,CardValue.THREE), 2);
		h.addCard(new Card(Suit.HEARTS,CardValue.FOUR), 3);
		h.addCard(new Card(Suit.HEARTS,CardValue.SEVEN), 4);
		
		for (int i = 0; i < h.length(); i++) {
			System.out.print(h.getCardN(i) + " ");
			
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
			System.out.println("One ace");
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
	
		analise.threeToStrType();
	}
	
}
