package players;
import java.util.*;

public class InteractivePlayer extends Player10_7 {
	
	String cmd;
	Scanner scan = new Scanner(System.in);
	public InteractivePlayer() {
		super();
	}
	
	public void ReadCmd () {
		String line = scan.nextLine();
	    cmd=line.replace(" ","");
	    
	} 
	public static void main(String[] args){
		
		InteractivePlayer player= new InteractivePlayer();
		 
		while(true){
			player.ReadCmd();
			player.Comand(player.cmd);
		}
	}
}
