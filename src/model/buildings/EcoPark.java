package model.buildings;

import java.awt.Graphics;

import model.PlayerModel;


public class EcoPark {
	
	protected PlayerModel owner = null;

	
	protected String EcoPark;

	
	protected boolean isEcoParkPurchasable = false;

	
	protected int ecoParkPrice;
	
	protected int ecoParkRevenue;
	
	protected int ecoParkLevel;

	
	protected int posX;
	protected int posY;
	
	protected int maxLevel;

	
	public EcoPark(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public boolean isEcoParkPurchasable() {
		return isEcoParkPurchasable;
	}

	public void setEcoParkPurchasable(boolean ecoParkPurchasable) {
		this.isEcoParkPurchasable = ecoParkPurchasable;
	}

	
	public boolean canEcoParkUpLevel() {
		return this.ecoParkLevel < maxLevel;
	}

	public PlayerModel getOwner() {
		return owner;
	}

	public void setOwner(PlayerModel owner) {
		this.owner = owner;
	}

	public int getEcoParkLevel() {
		return ecoParkLevel;
	}

	public void setEcoParkLevel(int ecoParkLevel) {
		this.ecoParkLevel = ecoParkLevel;
	}

	public String getEcoPark() {
		return EcoPark;
	}
	public String getUpName() {
		return EcoPark;
	}

	public int getUpLevelPrice() {
		return ecoParkPrice;
	}
	
	public int getEcoParkAllPrice() {
		return 0;
	}
	public int getEcoParkRevenue() {
		return ecoParkRevenue;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public int getEcoParkEvent() { return 0;}
	
	
	public int getEcoParkPassEvent() { return 0;}
	
	public void paint(Graphics g){}

	
}	
