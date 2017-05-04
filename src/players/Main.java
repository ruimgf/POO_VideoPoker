package players;

public class Main {

	public static void main(String[] args) {
		if(args.length < 1){
			System.out.println("Wrong usage");
			System.exit(-1);
		}
		Player10_7 player=null;
		switch(args[0]){
			case "-d":
				if(args.length < 4){
					System.out.println("Wrong usage");
					System.exit(-1);
				}
				if(Integer.parseInt(args[1])<=0){
					System.out.println("Invalid initial credit");
					System.exit(-1);
				}
				player = new DebugPlayer(Integer.parseInt(args[1]),args[2],args[3]);
				break;
			case "-s":
				if(args.length < 4){
					System.out.println("Wrong usage");
					System.exit(-1);
				}
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
				player = new SimulationPlayer(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
				break;
			case "-i":
				if(args.length < 2){
					System.out.println("Wrong usage");
					System.exit(-1);
				}
				if(Integer.parseInt(args[1])<=0){
					System.out.println("Invalid initial credit");
					System.exit(-1);
				}	
				player = new InteractivePlayer(Integer.parseInt(args[1]));
				break;
			case "-g":
				break;
			default:
				System.out.println("Wrong usage");
				System.exit(-1);
		
		}
		player.Play();
	}

}
