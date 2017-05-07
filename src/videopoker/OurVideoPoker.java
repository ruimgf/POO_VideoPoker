package videopoker;


import cards.Card;
import cards.Deck;
import cards.HandCards;
/**
 * Class that implements the videopoker interface, this class needs a 
 * variation of the video poker to be given in is cronstructor
 * 
 * @author Alexandre
 *
 */
public class OurVideoPoker implements Videopoker{
	
	int credits;
	/* represents the state were the game is 
	 * 0 - begin
	 * 1 - after first bet 
	 * 2 - after deal
	 * 3 - after the hold - and end of play
	 * 4 - after the bet but not the first bet
	 * */
	int gamestate = 0;
	/*last bet of the player by default is 5*/
	int lastbet = 5;
	Deck game_deck;
	HandCards game_cards;
	Statistics game_stats;
	VideoPokerVariation variation;
	
	public OurVideoPoker(int credits,VideoPokerVariation variation){
		
		this.credits = credits;
		
		/*initialize the deck with the 52 cards in a random order*/
		game_deck = new Deck();
		/*initialize the player hand , with null cards*/
		game_cards = new HandCards();
		
		game_stats = new Statistics(this.credits);
		/*Variation of the videoPoker*/
		this.variation = variation;
		
	}
	
	
	/**
	 * Method that implements the interface bet method returns a invalid_result if bet not valid
	 * or bet result if valid
	 * @param credits int that represent the credits to bet
	 */
	public Result bet(int credits){
		/*it can only bet in the gamestate 1*/
		if(gamestate == 0 || gamestate == 3){
			
			if(credits <0 || credits >5){
				return new Invalid_Result("Invalid number of credits to bet!",this.credits);
			}
			if(this.credits == 0){
				return new Invalid_Result("No more credits please quit!",this.credits);
			}
			
			if(this.credits-credits < 0){
				/*player cannot bet*/
				return new Invalid_Result("Illegal amount",this.credits);
			}			
			
			this.credits = this.credits - credits;
			this.lastbet = credits;
			/*update the statistics because the money draw*/
			this.game_stats.updateActualCredit(this.credits);
			
			/*if it is in the gamestate 0 it goes to gamestate 1*/
			if(this.gamestate == 0){
				this.gamestate = 1;
			}else{
				/*if it is in the gamestate 3 it goes to gamestate 4*/
				this.gamestate = 4;
			}
			
			
			
			return new Bet_Result(this.credits,this.lastbet);
			
			
		}else{
			
			return new Invalid_Result("b: illegal command",this.credits);
			
		}
		
	}

	/**
	 * Method to handle the bet with no args, default behavior is to bet the last valid bet
	 * @return the result of the bet
	 */
	public Result bet(){
		
		return bet(lastbet);
		
		
	}
	
	/**
	 * method that implements the deal in the interface, return a Deal_Result with the info with the cards that
	 * have been given to the player
	 * @return the result of the deal
	 */
	public Result deal(){
		
		if(this.gamestate == 1 || this.gamestate == 3 || this.gamestate == 4){
			
			this.game_deck.shuffle(); /*shuffle the deck*/
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
		
		return new Invalid_Result("d: illegal command",this.credits);
		
		
	}

	/**
	 * method that implements the hold play.
	 * @param to_hold - boolean array with false in the index that are not to hold and true in the ones that
	 * are to hold
	 */
	public Result hold(boolean[] to_hold){
		
		/* it can only do hold if the game is in the state 3*/
		if(this.gamestate == 2){
			
			/*only to avoid errors*/
			if(to_hold.length != 5){
				return new Invalid_Result("h: illegal command - error in hold",this.credits);
			}
			
			/*keep the cards that are to hold and give new cards to the player, only at the ones that it dont 
			 * want to hold
			 */
			for(int i=0;i<5;i++){
				
				if(to_hold[i] == false){
					
					/*if the card is to modify : ask a new card from the deck and change the index i of the
					 * game cards
					 */
					this.game_cards.modifyCard(this.game_deck.get_card(), i);
					
				}
				
			}
			
			/*analize the players hand*/
			String finalhand = this.variation.evaluate_hand_name(this.game_cards);
			
			/*update the credits*/
			this.credits = this.credits + this.variation.get_payout(this.game_cards,this.lastbet);
			
			/*update gamestate*/
			this.gamestate = 3;
			
			/*update statistics*/
			this.game_stats.updateActualCredit(this.credits);
			//TODO update the hands table of statistics
			/*return the result of the play*/
			return new Hold_Result(this.game_cards,this.credits,finalhand);	
			
		}
		
		/*if not in the right game state*/
		return new Invalid_Result("h: illegal command",this.credits);
		
	}
	
	
		
	public Result credit(){
		
		return new Credit_Result(this.credits);
		
	}
	
	public Result advice(){
		
		/*if it is not on gamestate 2 then it cannot do advice*/
		if(this.gamestate !=2){
			return new Invalid_Result("a: illegal command!",this.credits);
		}
		
		return new Advice_Result(variation.evaluate_hand_advice(this.game_cards));
		
	}
	
	public Result statistics(){
		
		return new Statistics_Result(this.game_stats);
		
	}
	
	public Result quit(){
		/*it can only quit if it is in the gamestate 0 or 3*/
		if(this.gamestate == 3 || this.gamestate == 0){
		
		System.out.println("QUIT");
		System.exit(1);
		return new Invalid_Result("No more credits please quit!",this.credits);
		}
		
		return new Invalid_Result("q: illegal command!",this.credits);
	}
	
	
	/**
	 * MAIN METHOD USED TO TEST THIS CLASS
	 * @param args
	 */
	public static void main(String[] args) {
		
		DoubleBonus10_7 variation = new DoubleBonus10_7();
		OurVideoPoker jogo = new OurVideoPoker(10000,variation);
		boolean[] to_hold = new boolean[5];
		to_hold[0] = true;
		to_hold[1] = true;
		to_hold[2] = true;
		to_hold[3] = true;
		to_hold[4] = false;
		
		System.out.println(jogo.hold(to_hold));
		System.out.println(jogo.credit());
		System.out.println(jogo.bet());
		System.out.println(jogo.credit());
		System.out.println(jogo.hold(to_hold));
		System.out.println(jogo.credit());
		System.out.println(jogo.deal());
		System.out.println(jogo.credit());
		System.out.println(jogo.bet());
		System.out.println(jogo.hold(to_hold));
		System.out.println(jogo.deal());
		System.out.println(jogo.bet());
		System.out.println(jogo.deal());
	}
	

}