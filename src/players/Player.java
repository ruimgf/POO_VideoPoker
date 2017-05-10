package players;

import videopoker.*;


/**
 * Class that represents the game Player that can be played in four different modes 
 * (Debug, Interactive, Simulation or GUI) 
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo 
 */
public abstract class Player {
	
	/**
	 * Object that allow us to play the game
	 */
	protected Videopoker game;
	/**
	 * Construct the player by initializing a game as null to be used as an auxiliar to the GUI mode VERREREREREERERRERE
	 */
	protected Player(){
		game = null;
	};
	/**
	 * Construct the player by initializing game with a initial number of credits
	 * @param credits number of credits in the beginning of the game
	 */
	public Player(Videopoker game) {
		this.game = game;

	}
	
	/**
	 * Abstract Method to be used by the subclasses in order to play the game VEEEEEEEEEEEEEERRRRRRRRRRRR!!
	 */
	
	abstract void Play();
	
	/**
	 * Method that receives a command, analyze it and turns it into a game action
	 * @param cmd command word to an action in the game
	 */
	
	public void Comand (String cmd){
		
		try {
			switch (cmd.charAt(0)) {
			
			case 'b':
				if(cmd.length()>1){
					int BetValue=Integer.parseInt(cmd.substring(1));				
					System.out.println(game.bet(BetValue));
			
				}else{	
						System.out.println(game.bet());	
				}
				break;
				
			case 'h':
				boolean[] hold_cards = new boolean[5];
				boolean flag = true; // flag to know if there was an error on try catch
				int j = 1;
				while(j<cmd.length()){
					flag = false;
					try {
						int index = Character.getNumericValue(cmd.charAt(j));
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
				break;
				
			case '$' :
				System.out.println(game.credit());
				break;
				
			case 'd':
				System.out.println(game.deal());
				break;
				
			case 's':
				System.out.println(game.statistics());
				break;
				
			case 'a':
				System.out.println(game.advice());
				break;
				
			case 'q':
				game.quit(); // if not permited an exception in throwed
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid instruction!");
				System.out.println("\n Please insert a command of type: b [amount(1-5)], h [cards to hold], $, d, a, s, q");
				break;
			}
			
		} catch (InvalidPlayException e) {
			System.out.println(e.getMessage());
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Invalid instruction!");
			System.out.println("\n Please insert a command of type: b [amount(1-5)], h [cards to hold], $, d, a, s, q");
		}
	}
}
