package board;

public class Stone {
	private int x;
	private int y;
	boolean isBlack;
	
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
}
