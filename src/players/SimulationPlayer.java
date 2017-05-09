package players;

import videopoker.InvalidPlayException;

public class SimulationPlayer extends Player10_7 {
		int numPlays;
		int betValue;
		
		public SimulationPlayer(int credits,int betValue , int numPlays) {
			super(credits);
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
			} catch (InvalidPlayException e) {
				System.out.println(e.getMessage());
				System.exit(-1);
			}
		} 

		
		public static void main(String[] args){
			
			SimulationPlayer player= new SimulationPlayer(10000,5,100000);
			player.Play();
			
		}
}
