package players;

import videopoker.InvalidPlayException;
import videopoker.Videopoker;

/**
 * Class that allow you to play the {@link videopoker.Videopoker} in Interactive mode. With this 
 * game mode allows the study of the game in terms of statistics. By setting the initial credit 
 * and the number of plays you want to apply, the game will automatically play until all the plays 
 * are done or the credit is finished. In every play, the game is always going to do the best move possible.
 * In the end, it will return the statistics of the game     
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo 
 */
public class SimulationPlayer extends Player {
	
	/**
	 * Number of plays that be applied in Simulation mode
	 */
	int numPlays;
	
	/**
	 * Bet value for all the plays that are going to take place in Simulation mode
	 */
	int betValue;
	
	/**
	 * Construct that extends from the class 
	 * @param game game that implements the interface of the {@link videopoker.Videopoker}
	 * @param Bet value for all the plays that are going to take place in Simulation mode
	 * @param Number of plays that be applied in Simulation mode
	 */
	public SimulationPlayer(Videopoker game ,int betValue , int numPlays) {
		super(game);
		this.betValue = betValue;
		this.numPlays = numPlays;
	}
	
	/**
	* Method with all the commands to be applied in each play of the Simulation Mode 
	* in order to have the best move done (Bet -> Deal -> Hold of the cards returned by the Advice)  
	* @param betValue bet value 
	*/
	public void Intructions (int betValue) throws InvalidPlayException{
	
		game.bet(betValue);
		game.deal();
		game.hold(game.advice().getHoldCards());
	}

	/**
	* Method used to play the game in Simulation mode
	*/		
	public void Play () {
		while(numPlays>0 ){
		
			try {
				Intructions(betValue);
				numPlays--;
			} catch (InvalidPlayException e) {
				System.out.println(e.getMessage());
				try {
					System.out.println(game.statistics());
				} catch (InvalidPlayException e1) {
					e1.printStackTrace();
				}
				System.exit(-1);
			}
			
			
		}
		try {
			System.out.println(game.statistics());
		} catch (InvalidPlayException e1) {
			e1.printStackTrace();
		}
	}

}
