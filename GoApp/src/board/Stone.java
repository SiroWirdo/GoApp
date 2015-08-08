package board;

public class Stone {
	private int x;
	private int y;
	private boolean isBlack;
	private int numberFreeBreaths;
	
	public Stone(int x, int y, boolean isBlack, int numberBreaths){
		this.x = x;
		this.y = y;
		this.isBlack = isBlack;
		this.numberFreeBreaths = numberBreaths;
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
	
	public int getNumberOfFreeBreaths(){
		return numberFreeBreaths;
	}
	
	public void addBreaths(int breaths){
		this.numberFreeBreaths += breaths;
	}
	
	public void removeBreaths(int breaths){
		this.numberFreeBreaths -= breaths;
	}
	
	public boolean isStoneNearPosition(int x, int y){
		if(this.x == x-1 && this.y == y){
			return true;
		}
		if(this.x == x+1 && this.y == y){
			return true;
		}
		if(this.x == x && this.y == y-1){
			return true;
		}
		if(this.x == x && this.y == y+1){
			return true;
		}
		return false;
	}
}
