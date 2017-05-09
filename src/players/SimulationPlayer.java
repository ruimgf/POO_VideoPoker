package players;

import videopoker.InvalidPlayException;
import videopoker.Videopoker;

public class SimulationPlayer extends Player10_7 {
		int numPlays;
		int betValue;
		
		public SimulationPlayer(Videopoker game,int betValue , int numPlays) {
			super(game);
			this.betValue = betValue;
			this.numPlays = numPlays;
		}
		
		public void Intructions (int betValue) throws InvalidPlayException{
		
			game.bet(betValue);
			game.deal();
			game.hold(game.advice().getHoldCards());
		

		}
		
		public void Play () {
		
			while(numPlays>0 ){
			
				try {
					Intructions(betValue);
					numPlays--;
				} catch (InvalidPlayException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				
				
			}
			try {
				System.out.println(game.statistics());
			} catch (InvalidPlayException e) {
				e.printStackTrace();
			}
		} 

		
		public static void main(String[] args){
			
			//SimulationPlayer player = new SimulationPlayer(10000,5,100000);
			//player.Play();
			
		}
}
