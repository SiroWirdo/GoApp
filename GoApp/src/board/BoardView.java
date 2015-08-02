package board;

import javax.swing.JPanel;

public class BoardView extends JPanel{
	private BoardControl boardController;
	private BoardModel boardModel;
	
	public BoardView(BoardControl bc, BoardModel bm){
		setSize(600, 600);
		setLayout(null);
		
	}
}
