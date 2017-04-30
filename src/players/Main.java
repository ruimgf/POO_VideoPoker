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
				
				break;
			case "-s":
				player = new SimulationPlayer(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
				break;
			case "-i":
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
