package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoHaveEcoCard extends EcoCard {

	public EcoHaveEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoHaveEcoCard";
		this.cName = "EcoHaveEcoCard";
		this.price = 50;
	}

	@Override
	public int useCard() {
		return GameState.CARD_HAVE;
	}

}
