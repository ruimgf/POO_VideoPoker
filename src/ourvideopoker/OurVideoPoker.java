package ourvideopoker;


import cards.Card;
import cards.Deck;
import cards.EndOfDeck;
import cards.HandCards;

import videopoker.*;
/**
 * This Class is our implementation of a videopoker machine, it implements the Videopooker interface
 * @author Alexandre, Rui , Pedro
 * @see videopoker.Videopoker
 *
 */
public class OurVideoPoker implements Videopoker{
	
	/**
	 * This field represents the credits that the player have in the Videopooker machine
	 */
	int credits;
	
	/**
	 * To implement the game we use a simple StateMachine, this field is the state of the game at each time
	 * 0 - begin
	 * 1 - after first bet 
	 * 2 - after deal
	 * 3 - after the hold - and end of play
	 * 4 - after the bet but not the first bet
	 */
	int gamestate = 0;
	
	/**
	 * This filed represents the last bet placed by the player in the machine
	 */
	int lastbet = 5;
	
	/**
	 * This field is the Deck of the videopoker machine.
	 * @see Deck
	 */
	Deck game_deck;
	
	/**
	 * This filed represents the cards that he machine have , for that it uses a object of type HandCards
	 * @see HandCards
	 */
	HandCards game_cards;
	
	/**
	 * For save the machine Statistics we use a object of type Statistics to handl the hands that had been
	 * given in the past
	 * @see Statistics
	 */
	Statistics game_stats;
	
	/**
	 * Must important thing it uses a object that implements the VideoPokerVariation Interface, is with that
	 * object that the machine knows what variation of videopoker is being played
	 * @see VideoPokerVariation
	 */
	final VideoPokerVariation variation;
	
	/**
	 * Constructor of the Videopoker, it receives the initial credits that a player put in the machine
	 * and a object with the variation that is to be played
	 * @param credits the initial credits in the machine
	 * @param variation the VideoPokerVariation that is going to be played in the machine
	 * @see VideoPokerVariation
	 */
	public OurVideoPoker(int credits,VideoPokerVariation variation){//TODO throw exception if variation is null or if credits are negative
		
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
	 * Method that implements the move for bet , it receives the credits that the player want to bet 
	 * and if the bet is valid returns a BetResult otherwise throw a exception with a message of the error
	 * occurred
	 * @param credits - credits that the player want to bet between [1-5]
	 * @return BetResult - object with information of the bet playmove
	 * @throws InvalidPlayException in case of error the bet is not placed and this method throws a InvalidPlayException
	 * with a message string that can be as it follows:
	 *	<p> - if the bet is not in the range [1 - 5] : 'b: illegal command, please make a bet in the range [1,5]'</p>	
	 *	<p>- if the player dont have credits : 'b: illegal command, player without credits'</p>
	 * 	<p>- if the player dont have credtis for that bet : 'b: illegal command, player without credits for that bet'</p>
	 *  <p>- if the gamestate don't permit that play (gamestate diferent from 0 and 3) : 'b: illegal command' </p>
	 * @see BetResult
	 * 
	 */
	public BetResult bet(int credits) throws InvalidPlayException{
		/*it can only bet in the gamestate 1*/
		if(gamestate == 0 || gamestate == 3){
			
			/*checks if that it can bet or have sufficient credits*/
			if(credits <0 || credits >5){
				throw new InvalidPlayException("b: illegal command, please make a bet in the range [1,5]");
			}
			if(this.credits == 0){
				throw new InvalidPlayException("b: illegal command, player without credits");
			}
			
			if(this.credits-credits < 0){
				/*player cannot bet*/
				throw new InvalidPlayException("b: illegal command, player without credits for that bet");
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
			
			
			
			return new BetResult(this.credits,this.lastbet);
			
			
		}
			
		throw new InvalidPlayException("b: illegal command");
		
	}

	/**
	 * Method that implements the move for bet with no arg, so it places a bet with the last valid bet
	 * that had occurred.If the bet is valid returns a BetResult otherwise throw a exception with a message of the error
	 * occurred
	 * @return BetResult - object with information of the bet playmove
	 * @throws InvalidPlayException in case of error the bet is not placed and this method throws a InvalidPlayException
	 * with a message string that can be as it follows:
	 *	<p> - if the bet is not in the range [1 - 5] : 'b: illegal command, please make a bet in the range [1,5]'</p>	
	 *	<p>- if the player dont have credits : 'b: illegal command, player without credits'</p>
	 * 	<p>- if the player dont have credtis for that bet : 'b: illegal command, player without credits for that bet'</p>
	 *  <p>- if the gamestate don't permit that play (gamestate diferent from 0 and 3) : 'b: illegal command' </p>
	 * @see BetResult
	 * 
	 */
	public BetResult bet() throws InvalidPlayException{
		
		return bet(lastbet);	
		
	}
	
	/**
	 * Method that implements the move for deal , if the deal
	 * is valid returns a DealResult otherwise throw a exception with a message of the error
	 * occurred
	 * @return DealResult - object with information of the deal playmove
	 * @throws InvalidPlayException in case of error this method throws a InvalidPlayException
	 * with a message string that can be as it follows:
	 *	<p>- if the player dont have credits : 'd: illegal command, player without credits'</p>
	 * 	<p>- if the player dont have credtis for that bet : 'd: illegal command, player without credits for that bet'</p>
	 *  <p>- if the gamestate don't permit that play (gamestate diferent from 1,3 and 4) : 'd: illegal command' </p>
	 * @see DealResult
	 * 
	 */
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
			
			this.game_deck.shuffle(); /*shuffle the deck*/
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

	/**
	 * Method that implements the move for hold , it receives a boolean array of size 5 whit true in the index's
	 * with the cards that the player want to hold and false in the ones that the player want to discard
	 * if the hold is valid returns a HoldResult otherwise throw a exception with a message of the error
	 * occurred
	 * @param to_hold - boolean array of size 5
	 * @return HoldResult - object with information of the hold playmove
	 * @throws InvalidPlayException in case of error the hold is not placed and this method throws a InvalidPlayException
	 * with a message string that can be as it follows:
	 *	<p> - if the array is not of size 5: 'ERROR: ARRAY OF HOLD DONT HAVE 5 FIELDS'</p>	
	 *  <p>- if the gamestate don't permit that play (gamestate diferent from 2) : 'h: illegal command' </p>
	 *  <p> - if the deck had finished: 'ERRO: END OF DECK'</p>
	 * @see EndOfDeck
	 * @see HoldResult
	 * 
	 */
	public HoldResult hold(boolean[] to_hold) throws InvalidPlayException{
		
		/* it can only do hold if the game is in the state 3*/
		if(this.gamestate == 2){
			
			/*only to avoid errors*/
			if(to_hold.length != 5){
				throw new InvalidPlayException("ERROR: ARRAY OF HOLD DONT HAVE 5 FIELDS");
			}
			
			/*keep the cards that are to hold and give new cards to the player, only at the ones that it dont 
			 * want to hold
			 */
			for(int i=0;i<5;i++){
				
				if(to_hold[i] == false){
					
					/*if the card is to modify : ask a new card from the deck and change the index i of the
					 * game cards
					 */
					try {
						this.game_cards.modifyCard(this.game_deck.get_card(), i);
					} catch (EndOfDeck e) {
						throw new InvalidPlayException("ERRO: END OF DECK");						
					}
					
				}
				
			}
			
			/*analize the players hand*/
			String finalhand = this.variation.evaluateHandName(this.game_cards);
			
			/*update the credits*/
			this.credits = this.credits + this.variation.getPayout(this.game_cards,this.lastbet);
			
			/*update gamestate*/
			this.gamestate = 3;
			
			/*update statistics*/
			this.game_stats.updateActualCredit(this.credits);
			this.game_stats.update_hand_stats(this.variation.evaluateHandName(this.game_cards));
			/*return the result of the play*/
			return new HoldResult(this.game_cards,this.credits,finalhand);	
			
		}
		
		/*if not in the right game state*/
		throw new InvalidPlayException("h: illegal command");
	}
	
	
	/**
	 * Method that implements the credit command
	 * @return it returns a CreditResult object with the information of the credits in the machine
	 * @see CreditResult
	 */
	public CreditResult credit(){
		
		return new CreditResult(this.credits);
		
	}
	
	/**
	 * Method that implements the move for advice, if the advice is valid returns a 
	 * AdviceResult otherwise throw a exception with a message of the error occurred
	 * @return AdviceResult - object with information of the AdviceResult
	 * @throws InvalidPlayException in case of error this method throws a InvalidPlayException
	 * with a message string that can be as it follows:
	 *  <p>- if the gamestate don't permit that play (gamestate diferent from 2) : 'a: illegal command' </p>
	 * @see AdviceResult
	 * 
	 */
	public AdviceResult advice() throws InvalidPlayException{
		
		/*if it is not on gamestate 2 then it cannot do advice*/
		if(this.gamestate !=2){
			throw new InvalidPlayException("a: illegal command");
		}
		
		return new AdviceResult(this.credits,variation.evaluateHandAdvice(this.game_cards));
		
	}
	
	
	/**
	 * Method that implements the statistics command
	 * @return it returns a StatisticsResult object with the information of the statistics in the machine
	 * @see StatisticsResult
	 */
	public StatisticsResult statistics(){
		
		return new StatisticsResult(this.credits,this.game_stats.toString());
		
	}
	
	/**
	 * Method that implements the quit command it returns nothing but if the command is invalid throws an
	 * InvalidPlayException
	 * @throws InvalidPlayException in case of error this method throws a InvalidPlayException
	 * with a message string that can be as it follows:
	 *  <p>- if the gamestate don't permit that play (gamestate diferent from 3 and 0) : 'q: illegal command' </p>
	 */
	public void quit() throws InvalidPlayException{
		/*it can only quit if it is in the gamestate 0 or 3*/
		if(this.gamestate == 3 || this.gamestate == 0){
		
			return;
		
		}
		
		throw new InvalidPlayException("q: illegal command");
	}
	

	public static void main(String[] args){
		
		DoubleBonus10_7 variation = new DoubleBonus10_7();
		OurVideoPoker jogo = new OurVideoPoker(10000,variation);
		boolean[] to_hold = new boolean[5];
		to_hold[0] = true;
		to_hold[1] = true;
		to_hold[2] = true;
		to_hold[3] = true;
		to_hold[4] = false;
		
		
		try {
			System.out.println(jogo.bet()+"");
		} catch (InvalidPlayException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(jogo.bet()+"");
		} catch (InvalidPlayException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
		
	}
	

}
