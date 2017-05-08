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
		
		public void Intructions (int betValue){
			
			try {
				game.bet(betValue);
				game.deal();
				game.hold(game.advice().getHoldCards());
			} catch (InvalidPlayException e) {
				System.out.println(e.getMessage());
			}
			
			
		}
		
		public void Play () {
			int currentCredit=0;
			try{
				while(numPlays>0 && game.credit().getCredits()>0){
					if((currentCredit=game.credit().getCredits())<betValue){
						Intructions(currentCredit);
					}else{
						Intructions(betValue);
					}
					numPlays--;
				}
				System.out.println(game.statistics());
			}catch(InvalidPlayException e){
				System.out.println(e.getMessage());
			}
		}
		
		public static void main(String[] args){
			
			SimulationPlayer player= new SimulationPlayer(10000,5,100000);
			player.Play();
			
		}
}
