package doublebonus10_7;

import cards.Card;
import cards.CardAnalizer;
import cards.CardValue;
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
	 * Method that implements the interface bet method returns a invalid_result if bet not valid
	 * or bet result if valid
	 * @param credits int that represent the credits to bet
	 */
	public Result bet(int credits){
		/*it can only bet in the gamestate 1*/
		if(gamestate == 1){
			
			
			if(this.credits == 0){
				return new Invalid_Result("No more credits please quit!",this.credits);
			}
			
			if(this.credits-credits < 0){
				/*player cannot bet*/
				return new Invalid_Result("Illegal amount",this.credits);
			}			
			
			this.credits = this.credits - credits;
			this.lastbet = credits;
			
			this.gamestate = 2;
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
	 */
	public Result deal(){
		
		if(this.gamestate == 2){
			
			this.game_deck.shuffle(); /*shuffle the deck*/
			/*pick 5 cards*/
			Card[] aux = new Card[5];
			for(int i=0;i<5;i++){
				aux[i] = this.game_deck.get_card();
			}
			/*pass cards to the player hand*/
			this.game_cards.newCards(aux);
			
			/*change the game state to the next state = 3*/
			this.gamestate = 3;
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
		if(this.gamestate == 3){
			
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
			FinalHand aux = analize_player_hand();
			
			/*update the credits*/
			this.credits = this.credits + aux.payout(this.lastbet);
			
			/*update gamestate*/
			this.gamestate = 1;
			
			/*return the result of the play*/
			return new Hold_Result(this.game_cards,this.credits,aux);	
			
		}
		
		/*if not in the right game state*/
		return new Invalid_Result("h: illegal command",this.credits);
		
	}
	
	/**
	 * private method that are used to check the hand of the player and return a final hand aproperly choosen
	 * with cardanalizer class
	 * @return
	 */
	FinalHand analize_player_hand()
	{
		
		CardAnalizer aux = new CardAnalizer(this.game_cards);
		/*vector of constants only to check the four of a kind and three of a kind in a four loop*/
		
		
		/*check if it is a RoyalFlush*/
		if(aux.NtoRoyalFlush(5)){
			return new RoyalFlush();
		}
		
		/*check if it is Straight Flush - the multiplier is 50*/
		if(aux.NtoStrFlush(5)){
			return new FinalHand("Straigh Flush",50);
		}
		
		
		/*check if 4 equal cards*/
		for(int i=1;i<14;i++){
			
			if(aux.NequalValueCards(4, CardValue.parse(i))){
				
				switch(CardValue.parse(i)) {
					case TWO: return new FinalHand("Four TWO's",80);
					case THREE: return new FinalHand("Four THREE's",80);
					case FOUR: return new FinalHand("Four FOUR's",80);
					case FIVE: return new FinalHand("Four FIVE's", 50);
					case SIX: return new FinalHand("Four SIX's", 50);
					case SEVEN: return new FinalHand("Four SEVEN's", 50);
					case EIGTH: return new FinalHand("Four EIGTH's", 50);
					case NINE: return new FinalHand("Four NINE's", 50);
					case TEN: return new FinalHand("Four TEN's", 50);
					case JACK: return new FinalHand("Four JACK's", 50);
					case QUEEN: return new FinalHand("Four QUEEN's", 50);
					case KING: return new FinalHand("Four KING's", 50);
					case ACE: return new FinalHand("Four ACE's", 160);
					default: ;
				}
			}
		}
		
		/*check if FullHouse*/
		if(aux.fullHouse()){
			return new FinalHand("Full House", 10);
		}
		
		/*check if flush*/
		if(aux.NFlush(5)){
			return new FinalHand("Flush", 7);
		}
		
		/*Straight*/
		if(aux.NStraight(5)){
			return new FinalHand("Straight", 5);
		}
		
		/*check if 3 equal cards*/
		for(int i=1;i<14;i++){
			
			if(aux.NequalValueCards(3, CardValue.parse(i))){
				
				return new FinalHand("Four" + CardValue.parse(i), 3);
				
			}
			
		}
		
		/*two pair*/
		if(aux.TwoPair()){
			return new FinalHand("Two Pair" , 1);
		}
		/*jacks or better*/
		if(aux.HighPair()){
			return new FinalHand("Jacks or Better" , 1);
		}
		
		/*no prize hand*/
		
		return new NoPrize();
		
		
	}
		
	public Result credit(){
		
		return new Credit_Result(this.credits);
		
	}
	
	public Result advice(){
		
		return new Invalid_Result("NOT IMPLEMENTE YET",this.credits);
		
	}
	
	public Result statistics(){
		
		return new Invalid_Result("NOT IMPLEMENTE YET",this.credits);
		
	}
	
	public Result quit(){
		
		System.out.println("QUIT");
		System.exit(1);
		
		return new Invalid_Result("NOT IMPLEMENTE YET",this.credits);
				
	}
	
	
	/**
	 * MAIN METHOD USED TO TEST THIS CLASS
	 * @param args
	 */
	public static void main(String[] args) {
		
		DoubleBonus10_7 jogo = new DoubleBonus10_7(10000);
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
