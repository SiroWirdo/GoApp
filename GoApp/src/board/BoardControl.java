package board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import settings.Settings;

public class BoardControl {
	private CrossPoint[][] crossPoints;
	private boolean onePlayer;
	private BoardModel boardModel;
	private BoardView boardView;
	
	public BoardControl(boolean one){
		this.crossPoints = new CrossPoint[9][9];
		
		this.onePlayer = one;
		this.boardModel = new BoardModel();
		this.boardView = new BoardView(this, boardModel);
		
		setCrossPoints();
		
	}
	
	public BoardView getBoardView(){
		return boardView;
	}
	
	public void setCrossPoints(){
		int lineLength= (int) (boardView.getHeight() - 50)/9;
		int stPointX = Settings.startPointX;
		int stPointY = Settings.startPointY;
		
		for(int i = 0; i < 9; i++){			
			for(int j = 0; j < 9; j++){
				crossPoints[i][j] = new CrossPoint(stPointX, stPointY);
				stPointY += lineLength;
			}
			stPointX += lineLength;
			stPointY = Settings.startPointY;
		}
	}
	
	public Move getMoveListener(){
		return new Move();
	}
	
	private class Move extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e){
			
		}
	}
}
