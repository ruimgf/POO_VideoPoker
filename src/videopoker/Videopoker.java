package videopoker;

public interface Videopoker {
	public Result bet(int credits);
	public Result bet();
	public Result credit();
	public Result deal();
	public Result hold(boolean[] indexs);
	public Result advice();
	public Result statistics();	
	public Result quit();
}