package board;

public class BoardControl {
	private boolean onePlayer;
	private BoardModel boardModel;
	private BoardView boardView;
	
	public BoardControl(boolean one){
		this.onePlayer = one;
		boardModel = new BoardModel();
		boardView = new BoardView(this, boardModel);
		
	}
	
	public BoardView getBoardView(){
		return boardView;
	}
}
