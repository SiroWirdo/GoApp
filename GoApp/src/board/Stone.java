package board;

public class Stone {
	private int x;
	private int y;
	private boolean isBlack;
	private int numberFreeBreaths;
	
	public Stone(int x, int y, boolean isBlack){
		this.x = x;
		this.y = y;
		this.isBlack = isBlack;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isBlack(){
		return isBlack;
	}
}
