package players;
import java.util.*;


import videopoker.Videopoker;

/**
 * Class that allow you to play the {@link videopoker.Videopoker} in Interactive mode. With this game 
 * mode you can play your game directly from the terminal by follow the expect commands.
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo 
 */
public class InteractivePlayer extends Player {
	
	/**
	 * Command given in the command line in the expected format
	 */
	private String cmd;
	
	/**
	 * Command line extract from the terminal line 
	 */
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * Construct that extends from the class Player10_7
	 * @param game game that implements the interface of the {@link videopoker.Videopoker}
	 */
	public InteractivePlayer(Videopoker game) {
		super(game);
	}
		
	/**
	 * Method that reads the command line and turns it into the expected format
	 */
	public void ReadCmd () {
		String line = scan.nextLine();
	    cmd=line.replace(" ","");    
	} 
	
	/**
	* Method used to play the game in Interactive mode waiting for new instructions from the command line 
	*/
	public void Play(){
		while(true){
			System.out.print(">> ");
			System.out.flush();
			ReadCmd();
			Comand(cmd);
		}
	}
}
