package doublebonus10_7;

import cards.Card;
import cards.DebugDeck;
import cards.Deck;

public class DebugDoubleBonus10_7 extends DoubleBonus10_7 {

	public DebugDoubleBonus10_7(int credits,String cardfile) {
		super(credits);
		game_deck = new DebugDeck(cardfile);
		
	}
	
	public Result deal(){
		
		if(this.gamestate == 2){
			
			/*pick 5 cards*/
			Card[] aux = new Card[5];
			for(int i=0;i<5;i++){
				aux[i] = this.game_deck.get_card();
			}
			/*pass cards to the player hand*/
			this.game_cards.newCards(aux);
			
			/*change the game state to the next state = 3*/
			this.gamestate = 3;
			/*update the number of deals in the statisctics*/
			this.game_stats.addDeal();
			/*return result of the bet*/
			return new Deal_Result(this.game_cards,this.credits);
			
			
			
		}
		return new Invalid_Result("d: illegal command",this.credits);
	}

}
