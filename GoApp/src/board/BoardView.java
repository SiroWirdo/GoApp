package board;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import settings.Settings;

public class BoardView extends JPanel{
	private BoardControl boardControl;
	private BoardModel boardModel;
	private BufferedImage board;
	private ArrayList<Stone> stones;
	
	public BoardView(BoardControl bc, BoardModel bm){
		setSize(600, 600);
		setLayout(null);
		
		this.boardControl = bc;
		this.boardModel = bm;
		
		stones = new ArrayList<Stone>();
		
		try {
		File f = new File("./resources/board.png");		
			board = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("BoardView: b³¹d wczytywania t³a planszy");
		}
		
		this.addMouseListener(boardControl.getMoveListener());
		
		repaint();
	}
	
	public void drawStone(int x, int y, boolean isBlack){
		stones.add(new Stone(x, y, isBlack));
		repaint();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(board, 0, 0, null);
		
		int lineLength= (int) (this.getHeight() - 50)/9;
		
		char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
		char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
		
		int linePointX = Settings.startPointX;
		int linePointY = Settings.startPointY;
		int lineEndPointX = linePointX + lineLength * 8;
		int lineEndPointY = linePointY + lineLength * 8;
		int letterStartX = 45;
		int letterStartY = 25;
		int numberStartX = 15;
		int numberStartY = 60;
		for(int i = 0; i < 9; i++){
			g.drawLine(linePointX, linePointY, lineEndPointX, linePointY);
			g.drawLine(linePointY, linePointX, linePointY, lineEndPointY);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 17));
			g.drawString(String.valueOf(letters[i]), letterStartX, letterStartY);
			g.drawString(String.valueOf(numbers[i]), numberStartX, numberStartY);
			
			letterStartX += lineLength;
			numberStartY += lineLength;
			linePointY += lineLength;
		}
		
		for(Stone stone : stones){
			g.fillOval(stone.getX(), stone.getY(), 35, 35);
		}
	}
}
