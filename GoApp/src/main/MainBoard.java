package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import options.OptionPanelControl;
import options.OptionPanelView;
import board.BoardView;

public class MainBoard extends JFrame{
	private BoardView boardView;
	private OptionPanelControl optionPanelControl;
	private OptionPanelView optionPanelView;
	
	public MainBoard(BoardView board, OptionPanelControl optionPanel){
		this.boardView = board;
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
	
}
