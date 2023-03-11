package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoTortoiseEcoCard extends EcoCard {

	private int life = 3;

	public EcoTortoiseEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoTortoiseEcoCard";
		this.cName = "EcoTortoiseEcoCard";
		this.price = 50;
	}

	@Override
	public int useCard() {
		return GameState.CARD_TORTOISE;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	
	@Override
	public int cardBuff() {
		
		return GameState.CARD_BUFF_TORTOISE;
	}
}
