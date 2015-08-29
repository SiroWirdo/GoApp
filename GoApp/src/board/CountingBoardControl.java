package board;

import java.util.ArrayList;

import main.MainBoard;

public class CountingBoardControl {
	private MainBoard mainBoard;
	private BoardControl boardControl;
	private CountingBoardView countingBoardView;
	
	public CountingBoardControl(MainBoard main, BoardControl board){
		this.mainBoard = main;
		this.boardControl = board;
		this.countingBoardView = new CountingBoardView(this);
		
	}
	
	public CountingBoardView getCountingBoardView(){
		return countingBoardView;
	}
	
	public ArrayList<Stone> getBlackStones(){
		return boardControl.getBlackStones();
	}
	
	public ArrayList<Stone> getWhiteStones(){
		return boardControl.getWhiteStones();
	}
	
	public CrossPoint[][] getCrossPoints(){
		return boardControl.getCrossPoints();
	}
}
