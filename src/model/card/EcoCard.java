package model.card;

import java.awt.Image;

import model.PlayerModel;


public abstract class EcoCard {

	
	protected String name;
	
	protected String cName;
	
	
	protected Image img;
	
	
	protected PlayerModel owner;
	
	
	protected PlayerModel eOwner;
	
	
	protected int price = 100;
	
	protected EcoCard(PlayerModel owner) {
		this.owner =owner;
	}
	
	
	public abstract int useCard ();
	
	public int cardBuff(){ return 0;}


	public String getName() {
		return name;
	}

	public Image getImg() {
		return img;
	}

	public PlayerModel getOwner() {
		return owner;
	}

	public void setOwner(PlayerModel owner) {
		this.owner = owner;
	}

	public int getPrice() {
		return price;
	}

	public String getcName() {
		return cName;
	}
	

	public PlayerModel geteOwner() {
		return eOwner;
	}

	public void seteOwner(PlayerModel eOwner) {
		this.eOwner = eOwner;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
	
	
}
