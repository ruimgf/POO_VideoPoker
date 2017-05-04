package players;

import doublebonus10_7.DoubleBonus10_7;

public abstract class Player10_7 {
	DoubleBonus10_7 game;
	
	public Player10_7(int credits) {
		// TODO Auto-generated constructor stub
		this.game = new DoubleBonus10_7(credits);
	}
	
	abstract void Play();
	
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
			boolean flag = true; // flag to know if there was a error in try
			while(j<cmd.length()){
				flag = false;
				try {
					index=Character.getNumericValue(cmd.charAt(j));
					hold_cards[index-1]=true;
					j++;
					flag = true;
				}catch (Exception e) {
					System.out.println("Invalid card to hold");
					break;
				}
				
			}
			if(flag == true) 
				System.out.println(game.hold(hold_cards));
		}else if(cmd.contains("$")){
			System.out.println(game.credit());
		}else if(cmd.contains("d")){
			System.out.println(game.deal());
		}else if(cmd.contains("a")){
			System.out.println(game.advice());
		}else if(cmd.contains("s")){
			System.out.println(game.statistics());
		}else if(cmd.contains("q")){
			System.out.println(game.quit());			
		}else{
			System.out.println("Invalid instruction!\n Please insert a command of type: b [amount(1-5)], h [cards to hold], $, d, a, s, q");	
		} 
		//System.out.println(text);	
	}
}
