package ui;

import javax.swing.JFrame;

import util.FrameUtil;

@SuppressWarnings("serial")
public class JFrameGame extends JFrame {

	
	private JPanelGame panelGame = null;
	
	public JFrameGame() {

		this.setTitle("Eco-Adventure");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(750 + 200, 650);

		this.setResizable(false);

		FrameUtil.setFrameCenter(this);

		this.panelGame = new JPanelGame();
		add(this.panelGame);
		

		this.setUndecorated(false);
	}

	public JPanelGame getPanelGame() {
		return panelGame;
	}
}
