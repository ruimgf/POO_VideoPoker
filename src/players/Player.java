package players;

import videopoker.*;


/**
 * Superclass that allow you to interact with the {@link videopoker.Videopoker} game  
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo 
 */
public abstract class Player {
	
	/**
	 * Object that allow you to interact with the game
	 */
	protected Videopoker game;
	
	/**
	 * Constructor that initializes a game as null
	 */
	protected Player(){
		game = null;
	};
	
	/**
	 * Constructor that initializes a game with a initial number of credits
	 * @param game videopoker game to play
	 */
	public Player(Videopoker game) {
		this.game = game;

	}
	
	/**
	 * Abstract Method to be used to initialize the game mode
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
					try {
						int BetValue=Integer.parseInt(cmd.substring(1));				
						System.out.println(game.bet(BetValue));
					} catch (NumberFormatException e) {
						System.out.println("Invalid file format");
						System.exit(-1);
					}
					
			
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
			if(e.getMessage().contains("b: illegal command, player without credits")){
				System.exit(0);
			}
			
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Invalid instruction!");
			System.out.println("\n Please insert a command of type: b [amount(1-5)], h [cards to hold], $, d, a, s, q");
		}
	}
}
