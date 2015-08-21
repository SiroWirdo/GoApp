package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import options.OptionPanelControl;
import board.BoardControl;
import board.BoardView;

public class MainMenu extends JFrame {
	private static MainMenu instance = null;
	private JPanel mainMenu;
	private JButton onePlayer;
	private JButton twoPlayers;
	private JButton exit;
	
    public static MainMenu getInstance() {
    	if (instance == null) {
    		instance = new MainMenu();
    	}
    	return instance;
    }
    
	public static void main(String[] args) {
		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainMenu.getInstance();
            }
        });
    }
	
	public MainMenu(){
		initUI();
	}
	
	public void initUI(){
		setSize(200, 300);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
    	
    	mainMenu = new JPanel();
    	mainMenu.setLayout(new GridLayout(0,1));
    	
    	onePlayer = new JButton("Jeden gracz");
    	onePlayer.addActionListener(new OnePlayer());
    	mainMenu.add(onePlayer);
    	
    	twoPlayers = new JButton("Dwóch graczy");
    	twoPlayers.addActionListener(new TwoPlayers());
    	mainMenu.add(twoPlayers);
    	
    	exit = new JButton("WyjdŸ");
    	exit.addActionListener(new Exit());
    	mainMenu.add(exit);
    	
    	this.add(mainMenu);
    	
    	setVisible(true);    	
    	repaint();
	}
	
	private class OnePlayer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			BoardControl boardControl = new BoardControl(true);
			BoardView boardView = boardControl.getBoardView();
			OptionPanelControl optionPanel = new OptionPanelControl(boardControl);
			boardControl.setOptionPanelControl(optionPanel);
			MainBoard mainBoard = new MainBoard(boardView, optionPanel);
			
			MainMenu.instance.dispose();
		}		
	}
	
	private class TwoPlayers implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			BoardControl boardControl = new BoardControl(false);
			BoardView boardView = boardControl.getBoardView();
			OptionPanelControl optionPanel = new OptionPanelControl(boardControl);
			boardControl.setOptionPanelControl(optionPanel);
			MainBoard mainBoard = new MainBoard(boardView, optionPanel);
			
			MainMenu.instance.dispose();
		}
		
	}
	
	private class Exit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			MainMenu.instance.dispose();
		}
		
	}
}
