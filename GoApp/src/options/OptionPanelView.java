package options;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionPanelView extends JPanel{
	private OptionPanelControl optionPanelControl;
	private JButton resignButton;
	private JButton passButton;
	
	
	public OptionPanelView(OptionPanelControl opc){
		optionPanelControl = opc;
		
		setSize(200,600);
		setLayout(null);
		
		resignButton = new JButton("Rezygnuj");
		resignButton.setBounds(40, 300, 100, 30);
		resignButton.addActionListener(optionPanelControl.getResignListener());
		add(resignButton);
		
		passButton = new JButton("Spasuj");
		passButton.setBounds(40, 350, 100, 30);
		passButton.addActionListener(optionPanelControl.getPassListener());
		add(passButton);
		
		setVisible(true);
		repaint();
	}

}
