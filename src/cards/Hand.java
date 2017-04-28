package cards;


public class Hand {

	public Card[] mycards;
	int length = 5;
	
	public Hand (){
		
		mycards = new Card[5];
		
	}
	
	public Card getCardN(int n){
		
		if(n < 0 || n > 4){
			return null;
		}
		
		return mycards[n];
		
	}
	
}
