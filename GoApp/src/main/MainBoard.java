package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import options.BottomPanelControl;
import options.BottomPanelView;
import options.OptionPanelControl;
import options.OptionPanelView;
import board.BoardView;

public class MainBoard extends JFrame{
	private BoardView board;
	private OptionPanelControl optionPanelControl;
	private BottomPanelControl bottomPanelControl;
	private OptionPanelView optionPanelView;
	private BottomPanelView bottomPanelView;
	
	public MainBoard(BoardView board){
		this.board = board;
		
		setSize(800, 600);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
    	
    	optionPanelControl = new OptionPanelControl();
    	bottomPanelControl = new BottomPanelControl();
    	
    	optionPanelView = optionPanelControl.getOptionPanelView();
    	bottomPanelView = bottomPanelControl.getBottomPanelView();
    	
    	add(BorderLayout.CENTER, board);
    	add(BorderLayout.EAST, optionPanelView);
    	add(BorderLayout.SOUTH, bottomPanelView);
    	
    	setVisible(true);    	
    	repaint();
	}
	
}
