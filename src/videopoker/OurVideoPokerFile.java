package videopoker;

import cards.Card;
import cards.DebugDeck;
import cards.Deck;

public class OurVideoPokerFile extends OurVideoPoker {

	public OurVideoPokerFile(int credits,String cardfile, VideoPokerVariation variation) {
		super(credits,variation);
		game_deck = new DebugDeck(cardfile);
		
	}
	
	public Deal_Result deal(){
		

		if(this.gamestate == 1 || this.gamestate == 3 || this.gamestate == 4){
			
			/*pick 5 cards*/
			Card[] aux = new Card[5];
			for(int i=0;i<5;i++){
				aux[i] = this.game_deck.get_card();
			}
			/*pass cards to the player hand*/
			this.game_cards.newCards(aux);
			
			/*if it is on the gamestate 1 it goes to gamestate 2 and the money draw as been already done by bet*/
			if(this.gamestate == 1){
				this.gamestate =2;
			}else if(this.gamestate == 3){
				/*if it is on the gamestate 3 then we have to do the money draw whit the last_bet there and move to state 2*/
				this.credits = this.credits - this.lastbet;
				/*update the statistics because the money draw*/
				this.game_stats.updateActualCredit(this.credits);
				this.gamestate = 2;
			}else{
				/*if it is oh the gamestate 4 the money draw as already done in bet and we go to gamestate  2*/
				this.gamestate = 2;
			}
			
			
			
			/*update the number of deals in the statisctics*/
			this.game_stats.addDeal();
			/*return result of the bet*/
			return new Deal_Result(this.game_cards,this.credits);
			
			
			
		}
		
		//TODO THROW EXEPTION;
		return null;
	}

}
