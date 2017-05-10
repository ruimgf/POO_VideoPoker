package players;
import java.util.*;


import videopoker.Videopoker;

/**
 * Class that represents a game in Interactive mode that extends from the superclass Player10_7 
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo 
 */
public class InteractivePlayer extends Player {
	/**
	 * Command given in the command line in the expected format
	 */
	private String cmd;
	/**
	 * Command line extract from the command line 
	 */
	private Scanner scan = new Scanner(System.in);
	/**
	 * Construct that extends from the class Player10_7
	 * @param VVVVEEEEEEEEEERRRRRRRRRRRRRRRRR
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
	* Method used to play the game in debug mode 
	*/
	public void Play(){
		while(true){
			System.out.print(">> ");
			System.out.flush();
			ReadCmd();
			Comand(cmd);
		}
	}
	
	public static void main(String[] args){
		
		//InteractivePlayer player= new InteractivePlayer(1000);
		 
		//player.Play();
	}
}
