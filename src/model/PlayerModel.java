package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import model.buildings.EcoPark;
import model.card.EcoCard;
import control.Control;
import control.GameRunning;


public class PlayerModel extends Tick implements Port {

	
	private String name;
	
	private int part = 0;
	
	private int cash;
	
	private int nx;

	
	private int x;
	
	private int y;

	
	private int inHospital;
	
	private int inPrison;

	
	private int number = 0;

	
	private List<EcoPark> ecoParks = new ArrayList<EcoPark>();

	
	private List<EcoCard> ecoCards = new ArrayList<EcoCard>();

	
	public static int MAX_CAN_HOLD_CARDS = 8;

	
	private List<EcoCard> effectEcoCards = new ArrayList<EcoCard>();

	private Image[] playerIMG = new Image[100];

	
	private PlayerModel otherPlayer = null;
	
	private Control control = null;

	public PlayerModel(int number, Control control) {
		this.name = "";
		this.number = number;
		this.control = control;
	}

	public List<EcoCard> getCards() {
		return ecoCards;
	}

	public void setCards(List<EcoCard> ecoCards) {
		this.ecoCards = ecoCards;
	}

	public List<EcoCard> getEffectCards() {
		return effectEcoCards;
	}

	public List<EcoPark> getBuildings() {
		return ecoParks;
	}

	public int getInPrison() {
		return inPrison;
	}

	public void setInPrison(int inPrison) {
		this.inPrison = inPrison;
	}

	
	private void initPlayerIMG() {

		this.playerIMG[0] = new ImageIcon("images/player/" + this.getPart()
				+ "/logo.png").getImage();

		this.playerIMG[1] = new ImageIcon("images/player/" + this.getPart()
				+ "/mini_01.png").getImage();

		this.playerIMG[2] = new ImageIcon("images/player/" + this.getPart()
				+ "/mini_01_on.png").getImage();

		this.playerIMG[3] = new ImageIcon("images/player/" + this.getPart()
				+ "/head_h5.png").getImage();

		this.playerIMG[4] = new ImageIcon("images/player/" + this.getPart()
				+ "/smile.png").getImage();

		this.playerIMG[5] = new ImageIcon("images/player/" + this.getPart()
				+ "/sad.png").getImage();

		this.playerIMG[6] = new ImageIcon("images/player/" + this.getPart()
				+ "/mini_02.png").getImage();
	}

	
	public Image getIMG(String str) {
		if (str.equals("logo"))
			return this.playerIMG[0];
		else if (str.equals("mini"))
			return this.playerIMG[1];
		else if (str.equals("mini_on"))
			return this.playerIMG[2];
		else if (str.equals("h5"))
			return this.playerIMG[3];
		else if (str.equals("smile"))
			return this.playerIMG[4];
		else if (str.equals("sad"))
			return this.playerIMG[5];
		else if (str.equals("mini_02"))
			return this.playerIMG[6];
		else
			return null;
	}

	public PlayerModel getOtherPlayer() {
		return otherPlayer;
	}

	public void setOtherPlayer(PlayerModel otherPlayer) {
		this.otherPlayer = otherPlayer;
	}

	public int getNumber() {
		return number;
	}

	public int getInHospital() {
		return inHospital;
	}

	public void setInHospital(int inHospital) {
		this.inHospital = inHospital;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getNx() {
		return nx;
	}

	public void setNx(int nx) {
		this.nx = nx;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void debug() {
		System.out.println("Íæ¼Ò:" + name + ",×ø±ê£º[" + x + "," + y + "].");
	}

	
	public void startGameInit() {

		this.initPlayerIMG();

		this.lastTime = Control.rate / 3;

		this.cash = GameRunning.PLAYER_CASH;
	}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;

		if (this.startTick < this.nowTick && this.nextTick >= this.nowTick) {
			this.control.movePlayer();

			if (this.nextTick != this.nowTick) {
				this.control.prassBuilding();
			}

			if (this.nextTick == this.nowTick) {
				this.control.playerStopJudge();
			}
		}
	}

}
