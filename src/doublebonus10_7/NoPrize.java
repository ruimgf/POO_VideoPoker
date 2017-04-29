package doublebonus10_7;

/**
 * extension of the FinalHand class to a NoPrize hand
 * the name it's gonna be loose and the tostring is different
 * @author cande
 *
 */
public class NoPrize extends FinalHand{

	public NoPrize(){
	
		super("loses",0);
		
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
	
	
	
}
