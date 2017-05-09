package players;
import java.util.*;

import videopoker.DoubleBonus10_7;
import videopoker.OurVideoPokerFile;

import java.io.*;

public class DebugPlayer extends Player10_7 {
	
	ArrayList<String> commandsDebug; 
	
	public DebugPlayer(int credits, String cmdfile,String cardfile) {
		super(credits);
		
		try {
			game = new OurVideoPokerFile(credits,cardfile, new DoubleBonus10_7());
			ReadCmds(cmdfile);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public void ReadCmds (String filename) throws IOException{
		
		BufferedReader cmdBuffer = new BufferedReader(new FileReader("filename"));
		String allCmds = new String();
		String cmd = new String();
		String line = cmdBuffer.readLine();
		ArrayList<String> cmds =new ArrayList<String>();
		int i=0,index;
	    
		
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
					//TODO code for catch of the exception
				}
			}else if(cmdsAux[i].contains("h")){
				cmd=cmdsAux[i];
				while(i+1 < cmdsAux.length && Character.isDigit(cmdsAux[i+1].charAt(0))){
					index=Integer.parseInt(cmdsAux[i+1]);
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
	
	public void Play(){
		for(String temp : commandsDebug){
			Comand(temp);
		}
	}
	
	public static void main(String[] args) throws IOException {

	}

}
