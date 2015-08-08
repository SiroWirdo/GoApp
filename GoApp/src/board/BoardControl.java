package board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.acl.Group;
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
	
	public void removeStones(GroupOfStones group){
		for(Stone stone : group.getStones()){
			if(whiteStones.contains(stone)){
				whiteStones.remove(stone);
			}
			if(blackStones.contains(stone)){
				blackStones.remove(stone);
			}
		}
	}
	
	public int numberOfBreaths(int x, int y){
		int number = 4;
		if(x-1 < 0 || occupiedPosition[x-1][y]){
			number -= 1;
		}
		if(x+1 > occupiedPosition.length - 1 || occupiedPosition[x+1][y]){
			number -= 1;
		}
		if(y-1 < 0 || occupiedPosition[x][y-1]){
			number -= 1;
		}
		if(y+1 >  occupiedPosition[x].length - 1 || occupiedPosition[x][y+1]){
			number -= 1;
		}
		
		return number;
	}

	private class Move extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			ArrayList<GroupOfStones> groupsToRemove = new ArrayList<GroupOfStones>();

			for(int i = 0; i < crossPoints.length; i++){
				for(int j = 0; j < crossPoints[i].length; j++){
					int crossX = crossPoints[i][j].getX();
					int crossY = crossPoints[i][j].getY();

					if(crossX - 20 < x && x < crossX + 20 && 
							crossY - 20 < y  && y < crossY + 20){
						boolean isNewGroup = true;
						Stone stone = new Stone(i, j, isBlackTurn, numberOfBreaths(i, j));
						GroupOfStones newGroup = new GroupOfStones(stone);
						
						for(GroupOfStones group : groupsOfStones){
							if(isBlackTurn == group.isBlack() && group != newGroup && group.isGroupNextToPosition(i, j) && 
									(stone.getNumberOfFreeBreaths() > 0 || group.getNumberFreeBreaths() > 1 || !isNewGroup)){
								newGroup = group.mergeGroups(newGroup);
								group.removeBreathsInPosition(i, j);
								isNewGroup = false;
							}else{

								group.removeBreathsInPosition(i, j);
								if(group.getNumberFreeBreaths() == 0){
									groupsToRemove.add(group);
								}
							}
						}
						
						for(GroupOfStones groupToRemove : groupsToRemove){
							removeStones(groupToRemove);
							groupsOfStones.remove(groupToRemove);
						}

						if(isNewGroup){
							groupsOfStones.add(newGroup);
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
