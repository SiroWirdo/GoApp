package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import options.OptionPanelControl;
import options.OptionPanelView;
import board.BoardView;

public class MainBoard extends JFrame{
	private BoardView board;
	private OptionPanelControl optionPanelControl;
	private OptionPanelView optionPanelView;
	
	public MainBoard(BoardView board){
		this.board = board;
		
		setSize(800, 600);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(true);
    	
    	optionPanelControl = new OptionPanelControl();
    	
    	optionPanelView = optionPanelControl.getOptionPanelView();
    	
    	add(BorderLayout.CENTER, board);
    	add(BorderLayout.EAST, optionPanelView);
    	
    	setVisible(true);    	
    	repaint();
	}
	
}
