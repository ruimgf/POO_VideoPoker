package videopoker;

public interface Videopoker {
	public Result bet(int credits);
	public Result credit();
	public Result deal();
	public Result hold(int[] indexs);
	public Result advice();
	public Result statistics();	
}