package options;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanelView extends JPanel{
	private OptionPanelControl optionPanelControl;
	private JButton resignButton;
	private JButton passButton;
	private JLabel whitePoints;
	private JLabel blackPoints;
	private JLabel whitePointsLabel;
	private JLabel blackPointsLabel;
	
	
	public OptionPanelView(OptionPanelControl opc){
		optionPanelControl = opc;
		
		setSize(200,600);
		setLayout(null);

		blackPointsLabel = new JLabel("Czarny: ");
		blackPointsLabel.setBounds(40, 200, 60, 30);
		add(blackPointsLabel);
		
		blackPoints = new JLabel("0");
		blackPoints.setBounds(110, 200, 60, 30);
		add(blackPoints);
		
		whitePointsLabel = new JLabel("Bia³y: ");
		whitePointsLabel.setBounds(40, 230, 60, 30);
		add(whitePointsLabel);
		
		whitePoints = new JLabel("0");
		whitePoints.setBounds(110, 230, 40, 30);
		add(whitePoints);
		
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
	
	public void setBlackPoints(int points){
		this.blackPoints.setText(new Integer(points).toString());
	}
	
	public void setWhitePoints(int points){
		this.whitePoints.setText(new Integer(points).toString());
	}
	
}
