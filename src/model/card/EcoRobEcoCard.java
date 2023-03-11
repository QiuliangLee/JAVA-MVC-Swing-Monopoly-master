package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoRobEcoCard extends EcoCard {

	public EcoRobEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoRobEcoCard";
		this.cName = "EcoRobEcoCard";
		this.price = 50;
	}

	@Override
	public int useCard() {
		return GameState.CARD_ROB;
	}

}
