package videopoker;

import doublebonus10_7.Result;

public interface Videopoker {
	public Result bet(int credits);
	public Result credit();
	public Result deal();
	public Result hold(boolean[] indexs);
	public Result advice();
	public Result statistics();	
	public Result quit();
}