package options;

public class BottomPanelControl {
	private BottomPanelView bottomPanelView;
	
	public BottomPanelControl(){
		bottomPanelView = new BottomPanelView(this);
	}
	
	public BottomPanelView getBottomPanelView(){
		return bottomPanelView;
	}

}
