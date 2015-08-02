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
			int x = e.getX();
			int y = e.getY();
			
			for(int i = 0; i < crossPoints.length; i++){
				for(int j = 0; j < crossPoints[i].length; j++){
					int crossX = crossPoints[i][j].getX();
					int crossY = crossPoints[i][j].getY();
					
					if(crossX - 35 < x && x < crossX + 35 && 
							crossY - 35 < y  && y < crossY + 35){
						boardView.drawStone(crossX, crossY, true);
					}
				}
			}
		}
	}
}
