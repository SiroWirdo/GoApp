package main;

import javax.swing.JFrame;

import options.OptionPanelControl;
import options.OptionPanelView;
import board.BoardView;
import board.CountingBoardView;

public class MainBoard extends JFrame{
	private BoardView boardView;
	private CountingBoardView countingBoardView;
	private OptionPanelControl optionPanelControl;
	private OptionPanelView optionPanelView;
	private boolean counting;
	
	public MainBoard(BoardView board, OptionPanelControl optionPanel){
		this.boardView = board;
		this.counting = false;
		boardView.getBoardControl().setMainBoard(this);
		
		setSize(800, 600);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(null);
    	setResizable(true);
    	
    	optionPanelControl = optionPanel;
    	
    	optionPanelView = optionPanelControl.getOptionPanelView();
    	
    	boardView.setBounds(0, 0, 600, 600);
    	optionPanelView.setBounds(600, 0, 200, 600);
    	
    	add(boardView);
    	add(optionPanelView);
    	
    	setVisible(true);
    	repaint();
	}
	
	public void changeToCountingBoard(CountingBoardView board){
		this.countingBoardView = board;
		this.countingBoardView.setBounds(0, 0, 600, 600);
		this.counting = true;
		remove(boardView);
		add(countingBoardView);
		repaint();
	}
	
	public void changeToBoardView(){
		if(counting){
			remove(countingBoardView);
			add(boardView);
			repaint();
		}
	}
	
}
