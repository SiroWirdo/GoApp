package board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import settings.Settings;

public class BoardControl {
	private CrossPoint[][] crossPoints;
	private boolean onePlayer;
	private BoardModel boardModel;
	private BoardView boardView;
	private boolean isBlackTurn;
	private boolean[][] occupiedPosition;
	private ArrayList<GroupOfStones> groupsOfStones;
	private ArrayList<Stone> blackStones;
	private ArrayList<Stone> whiteStones;
	
	public BoardControl(boolean one){
		this.crossPoints = new CrossPoint[9][9];
		this.occupiedPosition = new boolean[9][9];
		this.groupsOfStones = new ArrayList<GroupOfStones>();
		
		this.blackStones = new ArrayList<Stone>();
		this.whiteStones = new ArrayList<Stone>();
		
		clearPositions();
		
		isBlackTurn = true;
		
		this.onePlayer = one;
		this.boardModel = new BoardModel();
		this.boardView = new BoardView(this, boardModel);
		
		setCrossPoints();
		
	}
	
	public BoardView getBoardView(){
		return boardView;
	}
	
	public ArrayList<Stone> getBlackStones(){
		return blackStones;
	}
	
	public ArrayList<Stone> getWhiteStones(){
		return whiteStones;
	}
	
	public CrossPoint[][] getCrossPoints(){
		return crossPoints;
	}
	
	public void setCrossPoints(){
		int stPointX = Settings.startPointX;
		int stPointY = Settings.startPointY;
		
		Settings.lineLength= (int) (boardView.getHeight() - Settings.startPointX)/9;
		
		for(int i = 0; i < 9; i++){			
			for(int j = 0; j < 9; j++){
				crossPoints[i][j] = new CrossPoint(stPointX, stPointY);
				stPointY += Settings.lineLength;
			}
			stPointX += Settings.lineLength;
			stPointY = Settings.startPointY;
		}
	}
	
	public void clearPositions(){
		for(int i = 0; i < occupiedPosition.length; i++){
			for(int j = 0; j < occupiedPosition[i].length; j++){
				occupiedPosition[i][j] = false;
			}
		}
	}
	
	public Move getMoveListener(){
		return new Move();
	}
	
	public void drawStone(Stone stone){
		if(stone.isBlack()){
			blackStones.add(stone);
		}else{
			whiteStones.add(stone);
		}
		
		boardView.repaint();
	}
	
	private class Move extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			
			for(int i = 0; i < crossPoints.length; i++){
				for(int j = 0; j < crossPoints[i].length; j++){
					int crossX = crossPoints[i][j].getX();
					int crossY = crossPoints[i][j].getY();
					
					if(crossX - 20 < x && x < crossX + 20 && 
							crossY - 20 < y  && y < crossY + 20){
						
						//System.out.println("X: " + x + " Y: " + y + " CX: " + crossX + " CY: " + crossY);
						Stone stone = new Stone(i, j, isBlackTurn, 4);
						GroupOfStones newGroup = new GroupOfStones(stone);
						for(GroupOfStones group : groupsOfStones){
							if(isBlackTurn = group.isBlack() && group != newGroup && group.isGroupNextToPosition(x, y)){
								newGroup = group.mergeGroups(newGroup);
							}
						}
						drawStone(stone);
						occupiedPosition[i][j] = true;
						isBlackTurn = !isBlackTurn;
					}
				}
			}
		}
	}
}
