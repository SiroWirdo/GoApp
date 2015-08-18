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
	private Stone[][] occupiedPosition;
	private Stone[][] historyPosition;
	private Stone[][] secondHistoryPosition;
	private ArrayList<GroupOfStones> groupsOfStones;
	private ArrayList<Stone> blackStones;
	private ArrayList<Stone> whiteStones;
	private boolean firstMove;

	public BoardControl(boolean one){
		this.crossPoints = new CrossPoint[9][9];
		this.occupiedPosition = new Stone[9][9];
		this.historyPosition = new Stone[9][9];
		this.secondHistoryPosition = new Stone[9][9];
		this.groupsOfStones = new ArrayList<GroupOfStones>();
		this.firstMove = true;

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
				occupiedPosition[i][j] = null;
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
				occupiedPosition[stone.getX()][stone.getY()] = null;
			}
			if(blackStones.contains(stone)){
				blackStones.remove(stone);
				occupiedPosition[stone.getX()][stone.getY()] = null;
			}
		}
	}

	public int numberOfBreaths(int x, int y){
		int number = 4;
		if(x-1 < 0 || occupiedPosition[x-1][y] != null){
			number -= 1;
		}
		if(x+1 > occupiedPosition.length - 1 || occupiedPosition[x+1][y] != null){
			number -= 1;
		}
		if(y-1 < 0 || occupiedPosition[x][y-1] != null){
			number -= 1;
		}
		if(y+1 >  occupiedPosition[x].length - 1 || occupiedPosition[x][y+1] != null){
			number -= 1;
		}

		return number;
	}

	public void refreshStonesBreaths(){
		for(Stone stone : blackStones){
			int breaths = numberOfBreaths(stone.getX(), stone.getY());
			stone.setBreaths(breaths);
		}

		for(Stone stone : whiteStones){
			int breaths = numberOfBreaths(stone.getX(), stone.getY());
			stone.setBreaths(breaths);
		}

		for(GroupOfStones group : groupsOfStones){
			group.refreshBreaths();
		}
	}

	public void updateHistory(){
		if(!firstMove){
			for(int i = 0; i < historyPosition.length; i++){
				for(int j = 0; j < historyPosition.length; j++){
					secondHistoryPosition[i][j] = historyPosition[i][j];
				}
			}
		}
		
		for(int i = 0; i < occupiedPosition.length; i++){
			for(int j = 0; j < occupiedPosition.length; j++){
				historyPosition[i][j] = occupiedPosition[i][j];
			}
		}
	}

	public boolean isSamePosition(int x, int y){
		boolean same = true;
		Stone tempStone = new Stone(x, y, isBlackTurn, 1);
		occupiedPosition[x][y] = tempStone;
		
		for(int i = 0; i < occupiedPosition.length; i++){
			for(int j = 0; j < occupiedPosition.length; j++){
				if((occupiedPosition[i][j] != null && secondHistoryPosition[i][j] == null) ||
						(occupiedPosition[i][j] == null && secondHistoryPosition[i][j] != null)){
					same = false;
					System.out.println("same posit: " + i + " "  + j);
				}
			}
		}

		occupiedPosition[x][y] = null;
		return same;
	}
	
	public void addStones(GroupOfStones group){
		for(Stone stone : group.getStones()){
			if(!stone.isBlack()){
				whiteStones.add(stone);
				occupiedPosition[stone.getX()][stone.getY()] = stone;
			}
			if(stone.isBlack()){
				blackStones.add(stone);
				occupiedPosition[stone.getX()][stone.getY()] = stone;
			}
		}
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
							crossY - 20 < y  && y < crossY + 20 && occupiedPosition[i][j] == null){
						boolean isNewGroup = true;
						Stone stone = new Stone(i, j, isBlackTurn, numberOfBreaths(i, j));
						GroupOfStones newGroup = new GroupOfStones(stone);

						for(GroupOfStones group : groupsOfStones){
							if(isBlackTurn == group.isBlack() && group != newGroup && group.isGroupNextToPosition(i, j) && 
									(stone.getNumberOfFreeBreaths() > 0 || group.getNumberFreeBreaths() > 1 || !isNewGroup)){
								group.removeBreathsInPosition(i, j);
								newGroup = newGroup.mergeGroups(group);

								groupsToRemove.add(group);

								isNewGroup = false;
							}else{
								if(isBlackTurn != group.isBlack()){
									group.removeBreathsInPosition(i, j);
									if(group.getNumberFreeBreaths() == 0){
										groupsToRemove.add(group);
									}
								}
							}
						}

						for(GroupOfStones groupToRemove : groupsToRemove){
							if(groupToRemove.isBlack() != isBlackTurn){
								removeStones(groupToRemove);
							}
							groupsOfStones.remove(groupToRemove);
						}

						if(!isSamePosition(i, j)){
							groupsOfStones.add(newGroup);
							occupiedPosition[i][j] = stone;
						//	System.out.println(stone.getNumberOfFreeBreaths());
							drawStone(stone);
							refreshStonesBreaths();
						//	System.out.println("2: " + stone.getNumberOfFreeBreaths());
							updateHistory();
							isBlackTurn = !isBlackTurn;
							firstMove = false;
						}else{
							for(GroupOfStones groupToRemove : groupsToRemove){
								if(groupToRemove.isBlack() != isBlackTurn){
									addStones(groupToRemove);
								}
								groupsOfStones.add(groupToRemove);
							}
							refreshStonesBreaths();
						}
					}
				}
			}
		}
	}
}
