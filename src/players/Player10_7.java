package players;

import doublebonus10_7.DoubleBonus10_7;

public abstract class Player10_7 {
	DoubleBonus10_7 game;
	
	public Player10_7() {
		// TODO Auto-generated constructor stub
		this.game = new DoubleBonus10_7(1000);
	}
	
	public void Comand (String cmd){
		
		int BetValue;
		int j=0;
		int index=0;
		boolean[] hold_cards = new boolean[5];
	
		for(j=0; j<5; j++){
			hold_cards[j]=false;
		}
		j=1;
		
		if(cmd.contains("b")){
			
			if(cmd.length()>1){
				BetValue=Integer.parseInt(cmd.substring(1));
				System.out.println(game.bet(BetValue));
			}else{
				
				System.out.println(game.bet());
			}
		}else if(cmd.contains("h")){
			while(j<cmd.length()){
				index=Character.getNumericValue(cmd.charAt(j));
				hold_cards[index-1]=true;
				j++;
			}
			
			System.out.println(game.hold(hold_cards));
		}else if(cmd.contains("$")){
			System.out.println(game.credit());
		}else if(cmd.contains("d")){
			System.out.println(game.deal());
		}else if(cmd.contains("a")){
			System.out.println(game.advice());
		}else if(cmd.contains("s")){
			System.out.println(game.statistics());	
		}else{
			System.out.println("Invalid instruction!\nPlease insert a command of type: b [amount(1-5)], h [cards to hold], $, d, a, s");	
		} 
		//System.out.println(text);	
	}
}
