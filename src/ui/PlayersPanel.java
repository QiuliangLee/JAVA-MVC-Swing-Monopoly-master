package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;

import model.PlayerModel;


public class PlayersPanel extends Layer {

	private List<PlayerModel> players = null;

	protected PlayersPanel(int x, int y, int w, int h, List<PlayerModel> players) {
		super(x, y, w, h);
		this.players = players;
	}

	
	public void paintPlayerInformation(Graphics g) {
		int tempX = 0;
		tempX += 30;
		for (PlayerModel temp : players) {

			paintPlayerPanel(temp, g, tempX, 15);
			tempX += 80;
		}
	}

	
	private void paintPlayerPanel(PlayerModel player, Graphics g, int x,
			int y) {

		String[] information = { player.getName(),
				Integer.toString(player.getCash()) + "  EC ",
				Integer.toString(player.getNx()) + "  Ec ",
				Integer.toString(player.getBuildings().size()) + "  EP ",
				Integer.toString(player.getCards().size()) + " Ep " };

		g.drawImage(player.getIMG("mini_02"), x -26 + 15 , y - 10, x -26 + 15 +player.getIMG("mini_02").getWidth(null) ,
				 y - 10 +player
					.getIMG("mini_02").getHeight(null) , 0, 0, player.getIMG("mini_02").getWidth(null), player
						.getIMG("mini_02").getHeight(null), null);
		y += 48;
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font(null,0,14));

		FontMetrics fm = g.getFontMetrics();
		for (int k = 0; k < information.length; g.drawString(information[k], x
				+ (45 - fm.stringWidth(information[k])), y += 30), k++)
			;

	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);

		this.paintPlayerInformation(g);
		
	}

	@Override
	public void startPanel() {
	}

}
