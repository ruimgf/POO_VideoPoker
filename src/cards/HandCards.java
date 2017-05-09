package cards;

/**
 * Class that represent the 5 cards in the game, that are owned by the player.
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo
 *
 */
public class HandCards {
	
	/*
	 * hand of the player
	 * */
	private Card[] gamecards;
	/*
	 * lenght of the hand
	 * */
	private int length = 5;
	
	/**
	 * Default constructor for HandCards, it only initialize gamecards with null and create space for 5 cards
	 */
	public HandCards(){
		gamecards = new Card[5];
	}
	/**
	 * Constructor for HandCards, it only initialize gamecards with null and create space for length cards
	 * @param length number of cards in hand of player
	 */
	public HandCards(int length){
		this.length = length;
		gamecards = new Card[length];
	}
	
	public int length(){
		return this.length;
	}
	
	/**
	 * Method that give a new hand to the player
	 * @param new_hand 5 cards array that are the new cards of the player;
	 */
	public void newCards(Card[] new_hand){

		gamecards = new_hand;
		
	}
	
	/**
	 * method to add a card only - only to debug i think
	 * @param to_add card to add
	 * @param index index of card
	 */
	public void addCard(Card to_add,int index){
		
		if(index < 0 || index > 4){
			return;
		}
		
		this.gamecards[index] = to_add;
		
	}
	
	/**
	 * method that modify a card of the player hand, that card is specified by the index
	 * @param to_modify card that we want to add
	 * @param index where we put the card
	 */
	public void modifyCard(Card to_modify,int index){
		
		if(index > 4){
			System.out.println("ERROR: HAND CARD INDEX INVALID");
			System.exit(-1);
		}
		
		gamecards[index] = to_modify ;
		
		
	}

	/**
	 * method that return the card specified in the index
	 * @param index index of card to get
	 * @return Card at index
	 */
	public Card getCardN(int index){
		
		if(index < 0 || index > 4){
			return null;
		}
		
		return gamecards[index];
		
	}
	/**
	 * getter method for the game cards
	 * @return Game Cards
	 */
	public Card[] getGameCards(){
		
		return gamecards;
	}
	
	/**
	 * method that given a card check gives it index in the hand, or -1 if not present
	 * @param to_test card that we want to test
	 * @return index of the to_test card in the hand, or -1 if not present
	 */
	public int getIndex(Card to_test){
		
		
		for(int i = 0;i<5;i++){
			
			if(gamecards[i].equals(to_test)){
				return i;
			}
			
		}
		
		return -1;/*if not present*/
		
	}

	@Override
	public String toString() {
		return " " + gamecards[0] + " " + gamecards[1] + " " + gamecards[2] + " " + gamecards[3] + " " + gamecards[4];
	}
	
	
	
	
}
