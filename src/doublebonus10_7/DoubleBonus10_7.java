package doublebonus10_7;

import videopoker.Result;
import cards.Card;
import cards.Deck;
import cards.HandCards;
import videopoker.Invalid_Result;
import videopoker.Videopoker;

public class DoubleBonus10_7 implements Videopoker{
	
	int credits;
	/* represents the state were the game is 1- begin 2 - after the deal 3 - after the hold 4 - gameover*/
	int gamestate = 1;
	
	Deck game_deck;
	HandCards game_cards;
	//TODO INSERIR OBJECTO ESTATISTICAS
	
	//TODO METODO QUE CRIA OBJECTO A PARTIR DO FICHEIRO
	
	public DoubleBonus10_7(int credits){
		
		this.credits = credits;
		
		/*initialize the deck with the 52 cards in a random order*/
		game_deck = new Deck();
		/*initialize the player hand , with null cards*/
		game_cards = new HandCards();
		
		//TODO INSERIR INICIALIZAÇÂO ESTATISCTICAS
		
	}

	
	public Result bet(int credits){
		
		if(gamestate == 1){
			
			
			if(this.credits-credits < 0){
				/*player cannot bet*/
				return new Invalid_Result("Illegal amount");
			}			
			
			this.credits = this.credits - credits;
			
			
			
		}else{
			
			return new Invalid_Result("b: illegal command");
			
		}
		
		
		
		return new Invalid_Result("b: illegal command");
		
		
	}
	

}
