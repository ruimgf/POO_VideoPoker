package players;

	public class SimulationPlayer extends Player10_7 {
		
		public SimulationPlayer() {
			super();
		}
		
		public void Intructions (int betValue){
			
			game.bet(betValue);
			game.deal();
			game.hold(game.advice().getHoldcards());
		
		}
		
		public void Play (int numPlays, int betValue) {
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
			
			SimulationPlayer player= new SimulationPlayer();
			player.Play(5,5);
			
		}
}
