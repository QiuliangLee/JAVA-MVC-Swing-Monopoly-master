package model.buildings;

import model.PlayerModel;

import context.GameState;


public class EcoHouse extends EcoPark {

	private int upPrice;
	private String[] nameString = { "open space", "bungalow", "store", "mall", "commercial building", "mo d building" };

	private PlayerModel player;

	public EcoHouse(int posX, int posY) {
		super(posX, posY);
		this.maxLevel = 5;
	}

	public int getUpLevelPrice() {
		if (this.ecoParkLevel == 0) {
			this.upPrice = 500;
		} else {
			this.upPrice = 1000 * this.ecoParkLevel;
		}
		return upPrice;
	}
	
	
	public int getEcoParkAllPrice() {
		int price = 0;
		for (int i = 0; i <= ecoParkLevel; i++) {
			if (this.ecoParkLevel == 0) {
				price +=500;
			} else {
				price += 1000 * i;
			}
		}
		return price;
	}

	
	public int getEcoParkRevenue() {
		
		this.ecoParkRevenue = this.ecoParkLevel * (int) (Math.random() * 1000)
				+ (this.ecoParkLevel * 300);
		return ecoParkRevenue;
	}

	public String getEcoPark() {
		return this.nameString[this.ecoParkLevel];
	}

	
	public String getUpName() {
		if (this.ecoParkLevel >= this.nameString.length - 1) {
			return "null";
		}
		return this.nameString[this.ecoParkLevel + 1];
	}

	@Override
	public int getEcoParkEvent() {
		return GameState.HUOSE_EVENT;
	}
}
