package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			
		}
		
	}
	
	private class PassListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			boardControl.setBlackTurn(!boardControl.isBlackTurn());
		}
		
	}

}
