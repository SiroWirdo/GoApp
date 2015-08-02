package options;

import javax.swing.JPanel;

public class OptionPanelView extends JPanel{
	private OptionPanelControl optionPanelControl;
	
	public OptionPanelView(OptionPanelControl opc){
		optionPanelControl = opc;
		
		setSize(200,600);
		setLayout(null);
	}

}
