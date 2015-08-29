package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.MainMenu;
import board.BoardControl;

public class OptionPanelControl {
	private OptionPanelView optionPanelView;
	private BoardControl boardControl;
	
	public OptionPanelControl(BoardControl board){
		this.boardControl = board;
		optionPanelView = new OptionPanelView(this);
	}
	
	public OptionPanelView getOptionPanelView(){
		return optionPanelView;
	}
	
	public void refresh(){
		optionPanelView.repaint();
	}
	
	public void setBlackPoints(int points){
		optionPanelView.setBlackPoints(points);
	}
	
	public void setWhitePoints(int points){
		optionPanelView.setWhitePoints(points);
	}
	
	public ResignListener getResignListener(){
		return new ResignListener();
	}
	
	public PassListener getPassListener(){
		return new PassListener();
	}
	
	private class ResignListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(boardControl.isBlackTurn()){
				JOptionPane.showMessageDialog(new JFrame(),
					    "Czarny siê podda³",
					    "Wynik",
					    JOptionPane.WARNING_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(new JFrame(),
					    "Bia³y siê podda³",
					    "Wynik",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			boardControl.endGame();
			MainMenu.getInstance().changeVisible(true);
		}
		
	}
	
	private class PassListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			boardControl.setBlackTurn(!boardControl.isBlackTurn());
			boardControl.increasePassCounter();
			if(boardControl.getPassCounter() >= 2){
				boardControl.startCounting();
			}
		}
		
	}

}
