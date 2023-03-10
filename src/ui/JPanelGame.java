package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import ui.massage.MassageOk;
import ui.massage.MassageSimple;
import ui.massage.MassageYesNo;
import control.Control;

@SuppressWarnings("serial")
public class JPanelGame extends JPanel{

	private JFrameGame gameFrame = null;
	private JLayeredPane layeredPane;

	private List<Layer> lays = null;
	private Background backgroundUI = null;
	private Lands landsUI = null;
	private Buildings buildingsUI = null;
	private Players playersUI = null;
	private TextTip textTip = null;
	private PlayersPanel layerPlayersPanel = null;
	private Dice dice = null;
	private Event event = null;
	private Shop shop = null;
	private Running running = null;
	private Effect effect = null;

	private PlayerInfo playerInfo = null;
	
	private MassageYesNo massageYesNo = null;
	private MassageOk massageOk = null;
	private MassageSimple massageSimple = null;

	private Control control = null;

	
	public int posX = 100;
	
	public int posY = 100;

	public JPanelGame() {
		setLayout(new BorderLayout());

		initGame();
	}

	
	private void initGame() {

		control = new Control();

		initUI();

		control.setPanel(this);
	}

	public Control getControl() {
		return control;
	}

	
	private void initUI() {

		this.backgroundUI = new Background(0, 0, 950, 650,
				control.getBackground(),this);

		this.landsUI = new Lands(posX, posY, 950, 650, control.getLand());

		this.buildingsUI = new Buildings(posX, posY, 950, 650,
				control.getBuilding());

		this.playersUI = new Players(posX, posY, 950, 650,control.getRunning(), control.getPlayers());

		this.layerPlayersPanel = new PlayersPanel(posX + 64, posY + 66, 170,
				250, control.getPlayers());

		this.textTip = new TextTip(0,0,950,650,control.getTextTip());

		this.dice = new Dice(posX + 64, posY + 320, 170, 90, control);

		this.event = new Event(0, 0, 950, 650, control.getEvents());

		this.shop = new Shop(0, 0, 750, 650, control, this);

		this.running = new Running(780, 0, 200, 80, control.getRunning(),this);

		this.effect = new Effect(0, 0, 950, 650, control.getEffect(),this);

		this.playerInfo = new PlayerInfo(control.getPlayers(),this);

		this.massageYesNo = new MassageYesNo("??????", "??????????????", this);

		this.massageOk = new MassageOk("sure??", "??????????????", this);

		this.massageSimple = new MassageSimple("??????", "??????????????", this);


		lays = new ArrayList<Layer>();
		lays.add(backgroundUI);
		lays.add(dice);
		lays.add(playersUI);

		lays.add(layerPlayersPanel);
		lays.add(buildingsUI);
		lays.add(landsUI);
		lays.add(backgroundUI);
		lays.add(running);
		lays.add(effect);



		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);

		int add = 1;

		layeredPane.add(this.event, add++);
		layeredPane.add(this.effect, add++);
		layeredPane.add(this.textTip, add++);
		layeredPane.add(this.dice, add++);
		layeredPane.add(this.playersUI, add++);
		layeredPane.add(this.layerPlayersPanel, add++);
		layeredPane.add(this.buildingsUI, add++);
		layeredPane.add(this.landsUI, add++);
		layeredPane.add(this.running, add++);
		layeredPane.add(this.backgroundUI, add++);
		layeredPane.add(this.shop, add++);
		layeredPane.add(this.playerInfo,add++);

		


		
		add(layeredPane);
	}

	
	public MassageYesNo getMassageYesNo() {
		return massageYesNo;
	}

	public MassageOk getMassageOk() {
		return massageOk;
	}

	public MassageSimple getMassageSimple() {
		return massageSimple;
	}

	public Running getRunning() {
		return running;
	}

	public Dice getDice() {
		return dice;
	}

	public Shop getShop() {
		return this.shop;
	}

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	public Background getBackgroundUI() {
		return backgroundUI;
	}

	public Effect getEffect() {
		return effect;
	}

	public JFrameGame getGameFrame() {
		return gameFrame;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public void setGameFrame(JFrameGame gameFrame) {
		this.gameFrame = gameFrame;
	}

	
	public void startGamePanelInit() {
		for (Layer temp : this.lays) {

			temp.startPanel();
		}
	}

}
