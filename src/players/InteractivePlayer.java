package players;
import java.util.*;

public class InteractivePlayer extends Player10_7 {
	
	String cmd;
	Scanner scan = new Scanner(System.in);
	public InteractivePlayer(int credits) {
		super(credits);
	}
	
	public void ReadCmd () {
		String line = scan.nextLine();
	    cmd=line.replace(" ","");
	    
	} 
	
	public void Play(){
		while(true){
			System.out.print(">> ");
			System.out.flush();
			ReadCmd();
			Comand(cmd);
		}
	}
	public static void main(String[] args){
		
		InteractivePlayer player= new InteractivePlayer(1000);
		 
		player.Play();
	}
}
