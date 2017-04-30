package players;

	public class SimulationPlayer extends Player10_7 {
		int numPlays;
		int betValue;
		
		public SimulationPlayer(int credits,int betValue , int numPlays) {
			super(credits);
			this.betValue = betValue;
			this.numPlays = numPlays;
		}
		
		public void Intructions (int betValue){
			
			game.bet(betValue);
			game.deal();
			game.hold(game.advice().getHoldcards());
		
		}
		
		public void Play () {
			int currentCredit=0;
			while(numPlays>0){
				if((currentCredit=game.credit().getCredits())<betValue){
					Intructions(currentCredit);
				}else{
					Intructions(betValue);
				}
				numPlays--;
			}
		} 
		
		public static void main(String[] args){
			
			SimulationPlayer player= new SimulationPlayer(1000,5,5);
			player.Play();
			
		}
}
