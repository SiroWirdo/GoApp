package board;

import java.util.ArrayList;

public class GroupOfStones {
	private ArrayList<Stone> stones;
	private int numberFreeBreaths;
	private boolean[][] stonesPosition;
	private boolean isBlack;
	
	public GroupOfStones(Stone stone){
		this.stonesPosition = new boolean[9][9];
		this.stones = new ArrayList<Stone>();
		this.stones.add(stone);
		this.stonesPosition[stone.getX()][stone.getY()] = true;
		this.isBlack = stone.isBlack();		
		this.numberFreeBreaths = stone.getNumberOfFreeBreaths();		
	}
	
	public ArrayList<Stone> getStones(){
		return stones;
	}
	
	public int getNumberFreeBreaths(){
		return numberFreeBreaths;
	}
	
	public void addBreaths(int breaths){
		numberFreeBreaths += breaths;
	}
	
	public void removeBreaths(int breaths){
		numberFreeBreaths -= breaths;
	}
	
	public void addStone(Stone stone){
		stones.add(stone);
		stonesPosition[stone.getX()][stone.getY()] = true;
		numberFreeBreaths = stone.getNumberOfFreeBreaths();
	}
	
	public boolean isBlack(){
		return isBlack;
	}
	
	public void mergeGroups(GroupOfStones gos){
		for(Stone stone : gos.getStones()){
			stones.add(stone);
		}
		numberFreeBreaths += gos.getNumberFreeBreaths();
	}
	
	public boolean isGroupOnPosition(int x, int y){
		return stonesPosition[x][y];
	}
	
	public void refreshBreaths(){
		numberFreeBreaths = 0;
		for(Stone stone : stones){
			numberFreeBreaths += stone.getNumberOfFreeBreaths();
		}
	}
}
