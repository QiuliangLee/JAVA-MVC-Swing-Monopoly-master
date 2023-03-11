package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoBuildEcoCard extends EcoCard {

	public EcoBuildEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoBuildCard";
		this.cName = "EcoBuildCard";
		this.price = 30;

	}

	@Override
	public int useCard() {
		return GameState.CARD_ADDLEVEL;
	}

}
