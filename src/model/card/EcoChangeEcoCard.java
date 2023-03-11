package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoChangeEcoCard extends EcoCard {

	public EcoChangeEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoChangeEcoCard";
		this.cName = "EcoChangeEcoCard";
		this.price = 70;
	}

	@Override
	public int useCard() {
		return GameState.CARD_CHANGE;
	}

}
