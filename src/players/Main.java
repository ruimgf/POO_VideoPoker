package players;

import videopoker.*;

import java.io.FileNotFoundException;

import ourvideopoker.*;



public class Main {

	public static void main(String[] args) {
		if(args.length < 1){
			System.out.println("Wrong usage");
			System.exit(-1);
		}
		Player player=null;
		Videopoker game=null;
		switch(args[0]){
			case "-d":
				if(args.length < 4){
					System.out.println("Wrong usage");
					System.exit(-1);
				}
				
				try {
					if(Integer.parseInt(args[1])<=0){
						System.out.println("Invalid initial credit");
						System.exit(-1);
					}
					game = new OurVideoPokerFile(Integer.parseInt(args[1]),args[3], new DoubleBonus10_7());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("parse error: initial credit invalid");
					System.exit(-1);
				} catch (FileNotFoundException e) {
					System.out.println("Card File not Found");
					System.exit(-1);
				} catch (Throwable e){
					e.printStackTrace();
					System.exit(-1);
				}
				player = new DebugPlayer(game,args[2]);
				break;
			case "-s":
				if(args.length < 4){
					System.out.println("Wrong usage");
					System.exit(-1);
				}
				try {
					if(Integer.parseInt(args[1])<=0){
						System.out.println("Invalid initial credit");
						System.exit(-1);
					}
					if(Integer.parseInt(args[2])>5 || Integer.parseInt(args[2])<1){
						System.out.println("Invalid Bet Value");
						System.exit(-1);
					}
					if(Integer.parseInt(args[3])<0){
						System.out.println("Invalid Number of deals");
						System.exit(-1);
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid initial values");
					System.exit(-1);;
				}
				
				game = new OurVideoPoker(Integer.parseInt(args[1]), new DoubleBonus10_7());
				player = new SimulationPlayer(game,Integer.parseInt(args[2]),Integer.parseInt(args[3]));
				break;
			case "-i":
				if(args.length < 2){
					System.out.println("Wrong usage");
					System.exit(-1);
				}
				try {
					game = new OurVideoPoker(Integer.parseInt(args[1]), new DoubleBonus10_7());
					player = new InteractivePlayer(game);
					if(Integer.parseInt(args[1])<=0){
						System.out.println("Invalid initial credit");
						System.exit(-1);
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid initial credit");
					System.exit(-1);;
				}
			
				break;
			case "-g":
				player = new GuiPlayer();
				break;
			default:
				System.out.println("Wrong usage");
				System.exit(-1);
		
		}
		player.Play();
	}

}
