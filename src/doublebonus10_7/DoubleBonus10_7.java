package doublebonus10_7;

import cards.Card;
import cards.Deck;
import cards.HandCards;
import videopoker.Videopoker;
/**
 * Class that implements the DoubleBonus10_7 version of video Poker
 * gamestate represent the state of the game:
 * 		- 1 begin
 * 		- 2 after the bet
 * 		- 3 after the deal
 * 		- 4 after the hold
 * 
 * @author Alexandre
 *
 */
public class DoubleBonus10_7 implements Videopoker{
	
	int credits;
	/* represents the state were the game is 1- begin 2 - after the deal 3 - after the hold 4 - gameover*/
	int gamestate = 1;
	/*last bet of the player by default is 5*/
	int lastbet = 5;
	Deck game_deck;
	HandCards game_cards;
	//TODO INSERIR OBJECTO ESTATISTICAS
	
	
	public DoubleBonus10_7(int credits){
		
		this.credits = credits;
		
		/*initialize the deck with the 52 cards in a random order*/
		game_deck = new Deck();
		/*initialize the player hand , with null cards*/
		game_cards = new HandCards();
		
		//TODO INSERIR INICIALIZAÇÂO ESTATISCTICAS
		
	}
	
	/**
	 * Method to handle the bet with no args, default behavior is to bet the last valid bet
	 * @return the result of the bet
	 */
	public Result bet(){
		
		return bet(lastbet);
		
		
	}
	
	
	/**
	 * Method that implements the interface bet method returns a invalid_result if bet not valid
	 * or bet result if valid
	 * @param credits int that represent the credits to bet
	 */
	public Result bet(int credits){
		/*it can only bet in the gamestate 1*/
		if(gamestate == 1){
			
			
			if(this.credits-credits < 0){
				/*player cannot bet*/
				return new Invalid_Result("Illegal amount");
			}			
			
			this.credits = this.credits - credits;
			this.lastbet = credits;
			
			this.gamestate = 2;
			return new Bet_Result(this.credits);
			
			
		}else{
			
			return new Invalid_Result("b: illegal command");
			
		}
		
		
		
		return new Invalid_Result("b: illegal command");
		
		
	}
	
	public Result deal(){
		
		if(this.gamestate = 2){
			
			this.game_deck.shuffle(); /*shuffle the deck*/
			/*pick 5 cards*/
			Card[] aux = new Card[5];
			for(int i=0;i<5;i++){
				aux[i] = this.game_deck.get_card();
			}
			/*pass cards to the player hand*/
			this.game_cards.newCards(aux);
			
			
			
			
		}
		
		return new 
		
		
	}
	
	
	//TODO implement hold method
	public Result hold(boolean[] to_hold){
		
		
		
		
	}
	

}
