package options;

public class OptionPanelControl {
	private OptionPanelView optionPanelView;
	
	public OptionPanelControl(){
		optionPanelView = new OptionPanelView(this);
	}
	
	public OptionPanelView getOptionPanelView(){
		return optionPanelView;
	}

}
