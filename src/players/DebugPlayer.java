package players;
import java.util.*;

import videopoker.Videopoker;

import java.io.*;

/**
 * Class that allow you to play the {@link videopoker.Videopoker} in Debug mode. This class is important to test your game. when facing 
 * some peculiar situations. You can test your game by considering a file with the commands you want 
 * to check and the cards that will be played during this mode
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo 
 */
public class DebugPlayer extends Player {
	
	/**
	 * Array of strings used to store all the commands in the commands file
	 */
	private ArrayList<String> commandsDebug; 
	
	/**
	 * Construct a Debug mode player 
	 * @param game game that implements the interface of the {@link videopoker.Videopoker}
	 */
	public DebugPlayer(Videopoker game, String cmdfile) {
		super(game);
		
		try {
			ReadCmds(cmdfile);
		} catch (FileNotFoundException e) {
			System.out.println("Cmd File not found");
			System.exit(-1);
		} catch (Throwable e){
			System.out.println("File format invalid");
			System.exit(-1);
		}
		
	}
	
	/**
	* Method used to read an input file with commands and store those commands into an 
	* ArrayList of strings
	* @param filename name of the file from where we want to read the commands 
	*/
	public void ReadCmds (String filename) throws IOException{
		
		BufferedReader cmdBuffer = new BufferedReader(new FileReader(filename));
		String allCmds = new String();
		String cmd = new String();
		String line = cmdBuffer.readLine();
		ArrayList<String> cmds =new ArrayList<String>();
		int i=0;
	    
		while (line != null) {
	    	allCmds=allCmds.concat(line);
	        if(!allCmds.endsWith(" ")){
	        	allCmds=allCmds.concat(" ");
	        }
	        line = cmdBuffer.readLine();
	    }
	    cmdBuffer.close();
	    String[] cmdsAux = allCmds.split(" ");
	    for(int x=0; x< cmdsAux.length;x++){
	    	cmdsAux[x].replace(" ","");
	    }
	    while(i<cmdsAux.length){
	    	cmd=cmdsAux[i];
		    if(cmdsAux[i].contains("b")){
		    	try {
					Integer.parseInt(cmdsAux[i+1]);
					cmd=cmd.concat(cmdsAux[i+1]);
					i++;
				}catch(NumberFormatException e){
				}
			}else if(cmdsAux[i].contains("h")){
				cmd=cmdsAux[i];
				while(i+1 < cmdsAux.length && Character.isDigit(cmdsAux[i+1].charAt(0))){
					//Integer.parseInt(cmdsAux[i+1]);
					cmd=cmd.concat(cmdsAux[i+1]);
					i++;
				}
			}else
				cmd=cmdsAux[i];
			cmds.add(cmd);
			i++;
		}	
	    commandsDebug = cmds;
	} 	
	
	/**
	* Method used to play the game in Debug mode. Considering the given commands the game is going 
	* to be played until the all the commands are executed or the cards provided in the card file 
	* are finished
	*/
	public void Play(){
		for(String temp : commandsDebug){
			System.out.println();
			System.out.println("-cmd " + temp.replace("", " ").trim() );
			Comand(temp);
		}
		System.exit(0);
	}
}
