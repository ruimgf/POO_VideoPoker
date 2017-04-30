package players;
import java.util.*;
import java.io.*;


public class DebugPlayer extends Player10_7 {
	
	ArrayList<String> commandsDebug; 
	/*
	public DebugPlayer(String CardsFile){
		DoubleBonus_7 videopoker = new DoubleBonus_7(CardsFile);
	}
	
	//Metodo para ler o ficheiro das cartas
	//Não é para usar aqui, mas pode-se aproveitar!
	public String[] ReadCards (String filename){
		
		BufferedReader card_buffer = new BufferedReader(new FileReader("src/files/" + filename));
		String all_cards = new String();
		
		String line = card_buffer.readLine();

	    while (line != null) {
	    	all_cards=all_cards.concat(line);
	        if(!all_cards.endsWith(" ")){
	        	all_cards=all_cards.concat(" ");
	        }
	        line = card_buffer.readLine();
	    }
	    String[] cards = all_cards.split(" ");
	    System.out.println(cards[2]);
		
	    return cards;
		
		card_buffer.close();
	}*/
	public DebugPlayer() {
		// TODO Auto-generated constructor stub
		super(1000); // just to compile
	}
	
	public void ReadCmds (String filename) throws IOException{
		System.out.println(filename);
		BufferedReader cmdBuffer = new BufferedReader(new FileReader("src/files/"+filename));
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
		
	}
	
	public static void main(String[] args) throws IOException {
		//DoubleBonus_7 videopoker = new DoubleBonus_7();
		DebugPlayer player= new DebugPlayer();
		//String[] cmds = player.ReadCmds (args[2]);< 
		
		player.ReadCmds(args[0]);
		for(String temp : player.commandsDebug){
			player.Comand(temp);
		}
	}
			
		
		/*while(cmds[i]!=null){
			
			curr_c=cmds[i].charAt(0);
			switch(curr_c){
			case('b'):			
				try {
					bet_value = Integer.parseInt(cmds[i+1]);
					//Ver o que é retornado nesta função e organizar consoante isso
					text=videopoker.bet(bet_value);
					if(text.compareTo("b: illegal amount")!=0)						
						last_value=bet_value;
					System.out.println(text);
				}catch(NumberFormatException e){
					text=videopoker.bet(last_value);
					System.out.println(text);
					break;
				}
		    	break;
			case('h'):
				j=1;
				while(Character.isDigit(cmds[i+j].charAt(0))){
					try {
						card_index = Integer.parseInt(cmds[i+j]);
						hold_cards[card_index-1]=1;
						if(text.compareTo("b: illegal amount")!=0)						
							last_value=bet_value;
						j++;
					}catch(NumberFormatException e){
						//Ver o que é retornado nesta função e organizar consoante isso
						text=videopoker.hold(hold_cards);
						System.out.println(text);
						for(int x=0;x<5;x++){
							hold_cards[x]=0;
						}
						break;
					}		
				}				
				break;
			case('$'):
				text=videopoker.cash();
				System.out.println(text);
		    	break;
		    case('d'):
		    	text=videopoker.deal();
		    	System.out.println(text);
		    	break;
		    case('a'):
		    	text=videopoker.advice();
		    	break;
		    case('s'):
		    	text=videopoker.statistics();
		    	break;
		    default:
		    	break;
			}		
			i++;		
		}	
	}*/
}
