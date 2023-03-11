package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoTallageEcoCard extends EcoCard {

	public EcoTallageEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoTallageEcoCard";
		this.cName = "EcoTallageEcoCard";
		this.price = 100;
	}

	@Override
	public int useCard() {
		return GameState.CARD_TALLAGE;
	}

}
