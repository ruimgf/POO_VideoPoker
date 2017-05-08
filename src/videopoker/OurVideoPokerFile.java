package videopoker;

import cards.Card;
import cards.DebugDeck;
import cards.EndOfDeck;

public class OurVideoPokerFile extends OurVideoPoker {

	public OurVideoPokerFile(int credits,String cardfile, VideoPokerVariation variation) throws Throwable {
		super(credits,variation);
		
		game_deck = new DebugDeck(cardfile);
		
	}
	
	public DealResult deal() throws InvalidPlayException{
		
		/*In gamestate 3 we have to check if the player have money to deal*/
		if(this.gamestate == 3){
			if(this.credits == 0){
				throw new InvalidPlayException("d: illegal command, player without credits");
			}
			
			if(this.credits-credits < 0){
				/*player cannot bet*/
				throw new InvalidPlayException("d: illegal command, player without credits for that bet");
			}			
		}
		
		if(this.gamestate == 1 || this.gamestate == 3 || this.gamestate == 4){
			
			/*pick 5 cards*/
			Card[] aux = new Card[5];
			for(int i=0;i<5;i++){
				try {
					aux[i] = this.game_deck.get_card();
				} catch (EndOfDeck e) {
					throw new InvalidPlayException( e.getMessage());
				}
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
			return new DealResult(this.game_cards,this.credits);
			
			
			
		}
		
		throw new InvalidPlayException("d: illegal command");	

	}

}
