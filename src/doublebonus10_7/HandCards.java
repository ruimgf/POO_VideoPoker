package doublebonus10_7;

import cards.Card;

/**
 * Class that represent the 5cards in the game, that are owned by the player.
 * @author Alexandre
 *
 */
public class HandCards {
	
	/*hand of the player*/
	private Card[] gamecards;
	
	/**
	 * Default constructor for HandCards, it only initialise gamecards with null
	 */
	HandCards(){
		gamecards = new Card[5];
	}
	
	/**
	 * Method that give a new hand to the player
	 * @param new_hand 5 cards array that are the new cards of the player;
	 */
	void newCards(Card[] new_hand){

		gamecards = new_hand;
		
	}
	
	
	/**
	 * method that modify a card of the player hand, that card is specified by the index
	 * @param to_modify card that we want to add
	 * @param index where we put the card
	 */
	void modifyCard(Card to_modify,int index){
		
		if(index > 4){
			System.out.println("ERROR: HAND CARD INDEX INVALID");
			System.exit(-1);
		}
		
		gamecards[index] = to_modify ;
		
		
	}

	/**
	 * method that return the card specified in the index
	 * @param index
	 * @return
	 */
	Card getCard(int index){
		
		return gamecards[index];
		
	}

}
