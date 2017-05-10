package players;

import videopoker.InvalidPlayException;
import videopoker.Videopoker;

/**
 * Class that represents a game in Simulation mode that extends from the superclass Player10_7 
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo 
 */
public class SimulationPlayer extends Player {
		/**
		 * Number of plays that be done in Simulation mode
		 */
		int numPlays;
		/**
		 * Bet value for all the plays that are going to take place in Simulation mode
		 */
		int betValue;
		/**
		 * Construct that extends from the class 
		 * @param betValue bet value 
		 * @param numPlays  number of plays
		 * @param VVVVEEEEEEEEEERRRRRRRRRRRRRRRRR
		 */
		public SimulationPlayer(Videopoker game,int betValue , int numPlays) {
			super(game);
			this.betValue = betValue;
			this.numPlays = numPlays;
		}
		/**
		* Method with the commands to be done in each play of the simulation mode
		* Bet -> Deal -> Hold the cards returned by the advice  
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

		
	
	public static void main(String[] args){
		
		//SimulationPlayer player= new SimulationPlayer(10000,5,100000);
		//player.Play();
		
	}
}
