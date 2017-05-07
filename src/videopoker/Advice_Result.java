package videopoker;



public class Advice_Result extends Result {

	public Advice_Result(boolean[] holdCards) {
		// TODO Auto-generated constructor stub
		this.holdcards = holdCards;
	}
	
	public String toString() {
		String str; // left see case when is to discard everything
		str = "Player should hold cards " ;
		for (int i = 0; i < holdcards.length; i++) {
			if(holdcards[i]==true){
				str += " " + (i+1) ;
			}
		}
		if(str == "Player should hold cards "){
			return "Player should discard all cards";
		}else{
			return str;
		}
		
	}

}